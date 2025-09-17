package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.CargoDTO;
import br.com.fiap.sprint1.dto.FuncionarioDTO;
import br.com.fiap.sprint1.dto.GerenteDTO;
import br.com.fiap.sprint1.dto.PatioDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.model.Cargo;
import br.com.fiap.sprint1.model.Funcionario;
import br.com.fiap.sprint1.model.Gerente;
import br.com.fiap.sprint1.model.Patio;
import br.com.fiap.sprint1.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatioService {

    @Autowired
    private PatioRepository patioRepository;

    public PatioDTO toDTO(Patio patio) {
        return new PatioDTO(
                patio.getIdPatio(),
                patio.getNomePatio(),
                patio.getLocalizacaoPatio(),
                patio.getDescricaoPatio()
        );
    }

    public Patio toEntity(PatioDTO dto) {
        Patio patio = new Patio();
        patio.setIdPatio(dto.getIdPatio());
        patio.setLocalizacaoPatio(dto.getLocalizacaoPatio());
        patio.setNomePatio(dto.getNomePatio());
        patio.setDescricaoPatio(dto.getDescricaoPatio());
        return patio;
    }

    public Optional<PatioDTO> buscarPorId(Long id) {
        return patioRepository.findById(id)
                .map(this::toDTO);
    }
    @Cacheable(value = "patiosTodos", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<PatioDTO> listarTodos(Pageable pageable) {
        return patioRepository.findAll(pageable).map(this::toDTO);
    }

    @Cacheable(value = "patiosPorNome", key = "#nome + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<PatioDTO> buscarPorNome(String nome, Pageable pageable) {
        return patioRepository.findByNomePatioContainingIgnoreCase(nome, pageable).map(this::toDTO);
    }

    @Cacheable(value = "patiosPorLocalizacao", key = "#localizacao + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<PatioDTO> buscarPorLocalizacao(String localizacao, Pageable pageable) {
        return patioRepository.findByLocalizacaoPatioContainingIgnoreCase(localizacao, pageable).map(this::toDTO);
    }

    @Cacheable(value = "funcionariosPorPatio", key = "#idPatio + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<FuncionarioDTO> buscarFuncionariosPorPatio(Long idPatio, Pageable pageable) {
        return patioRepository.findFuncionariosByPatioId(idPatio, pageable)
                .map(this::toFuncionarioDTO);
    }

    @Cacheable(value = "gerentesPorPatio", key = "#idPatio + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<GerenteDTO> buscarGerentesPorPatio(Long idPatio, Pageable pageable) {
        return patioRepository.findGerentesByPatioId(idPatio, pageable)
                .map(this::toGerenteDTO);
    }

    private FuncionarioDTO toFuncionarioDTO(Funcionario funcionario) {
        return new FuncionarioDTO(
                funcionario.getIdFuncionario(),
                funcionario.getNomeFuncionario(),
                funcionario.getTelefoneFuncionario(),
                toCargoDTO(funcionario.getCargo()),
                toDTO(funcionario.getPatio())
        );
    }

    private CargoDTO toCargoDTO(Cargo cargo) {
        return new CargoDTO(
                cargo.getIdCargo(),
                cargo.getNomeCargo(),
                cargo.getDescricaoCargo(),
                null
        );
    }

    private GerenteDTO toGerenteDTO(Gerente gerente) {
        return new GerenteDTO(
                gerente.getIdGerente(),
                gerente.getNomeGerente(),
                gerente.getTelefoneGerente(),
                gerente.getCpfGerente(),
                gerente.getPatio()
        );
    }

    @CachePut(value = "patioPorId", key = "#result.idPatio")
    @CacheEvict(value = { "patiosTodos", "patiosPorNome", "patiosPorLocalizacao" }, allEntries = true)
    public PatioDTO salvar(PatioDTO dto) {
        Patio salvo = patioRepository.save(toEntity(dto));
        return toDTO(salvo);
    }

    @CachePut(value = "patioPorId", key = "#id")
    @CacheEvict(value = { "patiosTodos", "patiosPorNome", "patiosPorLocalizacao" }, allEntries = true)
    public PatioDTO atualizar(Long id, PatioDTO dto) {
        Patio existente = patioRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Pátio não encontrado com ID: " + id)
        );
        existente.setNomePatio(dto.getNomePatio());
        existente.setDescricaoPatio(dto.getDescricaoPatio());
        existente.setLocalizacaoPatio(dto.getLocalizacaoPatio());
        return toDTO(patioRepository.save(existente));
    }

    @CacheEvict(value = {
            "patioPorId", "patiosTodos", "patiosPorNome", "patiosPorLocalizacao",
            "funcionariosPorPatio", "gerentesPorPatio"
    }, allEntries = true)
    public void deletar(Long id) {
        if (!patioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Pátio não encontrado com ID: " + id);
        }
        patioRepository.deleteById(id);
    }
}
