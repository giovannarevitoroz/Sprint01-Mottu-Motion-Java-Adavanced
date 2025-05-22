package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.CargoDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.model.Cargo;
import br.com.fiap.sprint1.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @CacheEvict(value = {"cargos", "cargoPorId"}, allEntries = true)
    public CargoDTO cadastrar(CargoDTO cargoDTO) {
        Cargo cargo = new Cargo();
        cargo.setNomeCargo(cargoDTO.getNomeCargo());
        cargo.setDescricaoCargo(cargoDTO.getDescricaoCargo());

        Cargo salvo = cargoRepository.save(cargo);
        return toDTO(salvo);
    }

    @Cacheable("cargos")
    public Page<CargoDTO> listarTodos(Pageable pageable) {
        return cargoRepository.findAllByOrderByNomeCargoAsc(pageable)
                .map(this::toDTO);
    }

    @Cacheable(value = "cargoPorId", key = "#id")
    public CargoDTO buscarPorId(Long id) {
        return cargoRepository.findCargoById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cargo não encontrado com o ID: " + id));
    }

    public List<CargoDTO> buscarPorNomeExato(String nome) {
        List<Cargo> cargos = cargoRepository.findByNomeEqualsIgnoreCase(nome);
        if (cargos.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum cargo encontrado com o nome: " + nome);
        }
        return cargos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Page<CargoDTO> buscarPorParteDoNome(String parteNome, Pageable pageable) {
        Page<Cargo> cargos = cargoRepository.findByNomeCargoContainingIgnoreCase(parteNome, pageable);
        if (cargos.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum cargo encontrado contendo: " + parteNome);
        }
        return cargos.map(this::toDTO);
    }

    public CargoDTO buscarPorIdFuncionario(Long idFuncionario) {
        return cargoRepository.findCargoByIdFuncionario(idFuncionario)
                .map(this::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Nenhum cargo encontrado para o funcionário com ID: " + idFuncionario));
    }

    @CacheEvict(value = {"cargos", "cargoPorId"}, allEntries = true)
    public CargoDTO atualizarCargo(Long id, CargoDTO cargoDTO) {
        Cargo cargoExistente = cargoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cargo não encontrado com o ID: " + id));

        cargoExistente.setNomeCargo(cargoDTO.getNomeCargo());
        cargoExistente.setDescricaoCargo(cargoDTO.getDescricaoCargo());

        Cargo atualizado = cargoRepository.save(cargoExistente);
        return toDTO(atualizado);
    }

    @CacheEvict(value = {"cargos", "cargoPorId"}, allEntries = true)
    public void deletarCargo(Long id) {
        if (!cargoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Cargo não encontrado com o ID: " + id);
        }
        cargoRepository.deleteById(id);
    }

    public List<CargoDTO> buscarPorPrefixo(String prefixo) {
        List<Cargo> cargos = cargoRepository.findByNomeCargoStartingWithIgnoreCase(prefixo);
        if (cargos.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum cargo encontrado com prefixo: " + prefixo);
        }
        return cargos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CargoDTO toDTO(Cargo cargo) {
        CargoDTO dto = new CargoDTO();
        dto.setIdCargo(cargo.getIdCargo());
        dto.setNomeCargo(cargo.getNomeCargo());
        dto.setDescricaoCargo(cargo.getDescricaoCargo());
        return dto;
    }
    
    public Cargo toEntity(CargoDTO dto) {
    	Cargo cargo = new Cargo();
    	cargo.setIdCargo(dto.getIdCargo());
    	cargo.setDescricaoCargo(dto.getDescricaoCargo());
    	cargo.setNomeCargo(dto.getNomeCargo());
    	
    	return cargo;
    	
    }
}
