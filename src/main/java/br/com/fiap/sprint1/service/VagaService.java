package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.VagaDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.model.Setor;
import br.com.fiap.sprint1.model.Vaga;
import br.com.fiap.sprint1.repository.SetorRepository;
import br.com.fiap.sprint1.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Cacheable("vagas")
    public Page<VagaDTO> listarTodos(Pageable pageable) {
        return vagaRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Cacheable(value = "vaga", key = "#id")
    public VagaDTO findById(Long id) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Vaga não encontrada com o ID: " + id));
        return convertToDTO(vaga);
    }

    @CachePut(value = "vaga", key = "#result.idVaga")
    @CacheEvict(value = "vagas", allEntries = true)
    public VagaDTO cadastrar(VagaDTO vagaDTO) {
        Setor setor = setorRepository.findById(vagaDTO.getIdSetor())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Setor não encontrado com o ID: " + vagaDTO.getIdSetor()));

        Vaga vaga = new Vaga();
        vaga.setNumeroVaga(vagaDTO.getNumeroVaga());
        vaga.setStatusOcupada(vagaDTO.getStatusOcupada());
        vaga.setSetor(setor);

        Vaga savedVaga = vagaRepository.save(vaga);
        return convertToDTO(savedVaga);
    }

    @CachePut(value = "vaga", key = "#id")
    @CacheEvict(value = "vagas", allEntries = true)
    public VagaDTO atualizar(Long id, VagaDTO vagaDTO) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Vaga não encontrada com o ID: " + id));

        Setor setor = setorRepository.findById(vagaDTO.getIdSetor())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Setor não encontrado com o ID: " + vagaDTO.getIdSetor()));

        vaga.setNumeroVaga(vagaDTO.getNumeroVaga());
        vaga.setStatusOcupada(vagaDTO.getStatusOcupada());
        vaga.setSetor(setor);

        Vaga updatedVaga = vagaRepository.save(vaga);
        return convertToDTO(updatedVaga);
    }

    @CacheEvict(value = {"vaga", "vagas"}, key = "#id", allEntries = true)
    public void deletar(Long id) {
        if (!vagaRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Vaga não encontrada com o ID: " + id);
        }
        vagaRepository.deleteById(id);
    }

    @Cacheable(value = "vagaByStatus", key = "#status + '_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<VagaDTO> findByStatus(Integer status, Pageable pageable) {
        if (status != 0 && status != 1) {
            throw new IllegalArgumentException("Status deve ser 0 (livre) ou 1 (ocupado)");
        }
        return vagaRepository.findByStatusOcupada(status, pageable).map(this::convertToDTO);
    }

    @Cacheable(value = "vagaBySetor", key = "#setorId + '_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<VagaDTO> findBySetor(Long setorId, Pageable pageable) {
        if (!setorRepository.existsById(setorId)) {
            throw new RecursoNaoEncontradoException("Setor não encontrado com o ID: " + setorId);
        }
        return vagaRepository.findBySetorId(setorId, pageable).map(this::convertToDTO);
    }

    @Cacheable(value = "vagaByNumero", key = "#numero + '_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<VagaDTO> findByNumero(String numero, Pageable pageable) {
        return vagaRepository.findByNumeroContaining(numero, pageable).map(this::convertToDTO);
    }

    @Cacheable(value = "countByStatusAndSetor", key = "#status + '_' + #setorId")
    public int countByStatusAndSetor(Integer status, Long setorId) {
        if (status != 0 && status != 1) {
            throw new IllegalArgumentException("Status deve ser 0 (livre) ou 1 (ocupado)");
        }
        if (!setorRepository.existsById(setorId)) {
            throw new RecursoNaoEncontradoException("Setor não encontrado com o ID: " + setorId);
        }
        return vagaRepository.countByStatusAndSetor(status, setorId);
    }

    @Cacheable(value = "isVagaDisponivel", key = "#id")
    public boolean isVagaDisponivel(Long id) {
        if (!vagaRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Vaga não encontrada com o ID: " + id);
        }
        return vagaRepository.isVagaDisponivel(id);
    }

    private VagaDTO convertToDTO(Vaga vaga) {
        VagaDTO dto = new VagaDTO();
        dto.setIdVaga(vaga.getIdVaga());
        dto.setNumeroVaga(vaga.getNumeroVaga());
        dto.setStatusOcupada(vaga.getStatusOcupada());
        dto.setIdSetor(vaga.getSetor() != null ? vaga.getSetor().getIdSetor() : null);
        return dto;
    }
}
