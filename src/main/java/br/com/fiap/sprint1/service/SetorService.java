package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.SetorDTO;
import br.com.fiap.sprint1.model.Patio;
import br.com.fiap.sprint1.model.Setor;
import br.com.fiap.sprint1.repository.PatioRepository;
import br.com.fiap.sprint1.repository.SetorRepository;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private PatioRepository patioRepository;

    @Cacheable("setores")
    public Page<SetorDTO> listarTodos(Pageable pageable) {
        return setorRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Cacheable(value = "setor", key = "#id")
    public SetorDTO findById(Long id) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Setor não encontrado com ID: " + id));
        return convertToDTO(setor);
    }

    @CachePut(value = "setor", key = "#result.idSetor")
    @CacheEvict(value = "setores", allEntries = true)
    public SetorDTO cadastrar(SetorDTO setorDTO) {
        Patio patio = patioRepository.findById(setorDTO.getIdPatio())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pátio não encontrado com ID: " + setorDTO.getIdPatio()));

        Setor setor = new Setor();
        setor.setTipoSetor(setorDTO.getTipoSetor());
        setor.setStatusSetor(setorDTO.getStatusSetor());
        setor.setPatio(patio);

        return convertToDTO(setorRepository.save(setor));
    }

    @CachePut(value = "setor", key = "#id")
    @CacheEvict(value = "setores", allEntries = true)
    public SetorDTO atualizar(Long id, SetorDTO setorDTO) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Setor não encontrado com ID: " + id));

        Patio patio = patioRepository.findById(setorDTO.getIdPatio())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pátio não encontrado com ID: " + setorDTO.getIdPatio()));

        setor.setTipoSetor(setorDTO.getTipoSetor());
        setor.setStatusSetor(setorDTO.getStatusSetor());
        setor.setPatio(patio);

        return convertToDTO(setorRepository.save(setor));
    }

    @CacheEvict(value = {"setor", "setores"}, key = "#id", allEntries = true)
    public void deletar(Long id) {
        if (!setorRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Setor não encontrado com ID: " + id);
        }
        setorRepository.deleteById(id);
    }

    private SetorDTO convertToDTO(Setor setor) {
        return new SetorDTO(
                setor.getIdSetor(),
                setor.getTipoSetor(),
                setor.getStatusSetor(),
                setor.getPatio().getIdPatio()
        );
    }
}
