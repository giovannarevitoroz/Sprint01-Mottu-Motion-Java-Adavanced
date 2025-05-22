package br.com.fiap.sprint1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.sprint1.dto.FuncionarioDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.model.Funcionario;
import br.com.fiap.sprint1.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private PatioService patioService;
	
	@CachePut(value="funcionarios", key="#result.idFuncionario")
	public FuncionarioDTO salvar(FuncionarioDTO funcionarioDTO) {
		Funcionario funcionario = toEntity(funcionarioDTO);
		return toDTO(funcionarioRepository.save(funcionario));
	}
	
	@Cacheable(value="funcionariosAll")
	public List<FuncionarioDTO> listarTodos() {
		return funcionarioRepository.findAll()
				.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	@Cacheable(value="funcionarioPorId", key="#id")
	public FuncionarioDTO buscarPorId(Long id) {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário com ID" + id + " não encontrado."));
		return toDTO(funcionario);
	}
	
	
	public Page<FuncionarioDTO> buscarFuncionariosPorCargo(Long idCargo, Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findByCargoId(idCargo, pageable);
		if (funcionarios.isEmpty()) {
			throw new RecursoNaoEncontradoException("Funcionários não encontrados para Cargo de ID: " + idCargo);
		}
		
		return funcionarios.map(this::toDTO);
	}
	
	public Page<FuncionarioDTO> buscarFuncionariosPorPatio(Long idPatio, Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findByPatioId(idPatio, pageable);
		if (funcionarios.isEmpty()) {
			throw new RecursoNaoEncontradoException("Funcionários não encontrados para Pátio de ID: " + idPatio);
		}
		return funcionarios.map(this::toDTO);
	}
	
	public Page<FuncionarioDTO> buscarFuncionariosPorSetor(Long idSetor, Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findBySetorId(idSetor, pageable);
		if (funcionarios.isEmpty()) {
			throw new RecursoNaoEncontradoException("Funcionários não encontrados para Setor de ID: " + idSetor);
		}
		return funcionarios.map(this::toDTO);
	}
	
	public Page<FuncionarioDTO> buscarFuncionariosPorNome(String nome, Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findByNomeFuncionarioContainingIgnoreCase(nome, pageable);
		if (funcionarios.isEmpty()) {
			throw new RecursoNaoEncontradoException("Funcionários não encontrados com nome: " + nome);
		}
		return funcionarios.map(this::toDTO);
	}
	
	@CachePut(value="funcionarioPorId", key="#id")
	public FuncionarioDTO atualizar(Long id, FuncionarioDTO funcionarioDTO) {
		Funcionario funcionarioExistente = funcionarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário com ID " + id + " não encontrado."));
		
		funcionarioExistente.setNomeFuncionario(funcionarioDTO.getNomeFuncionario());
		funcionarioExistente.setTelefoneFuncionario(funcionarioDTO.getTelefoneFuncionario());
		funcionarioExistente.setPatio(patioService.toEntity(funcionarioDTO.getPatio()));
		funcionarioExistente.setCargo(cargoService.toEntity(funcionarioDTO.getCargo()));
		
		return toDTO(funcionarioRepository.save(funcionarioExistente));
	}
	
	@CacheEvict(value="funcionarios", key="#id")
	public void deletarFuncionario(Long id) {
		if (!funcionarioRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Funcionário não encontrado com o ID: " + id);
		}
		
		funcionarioRepository.deleteById(id);
	}
	
	
	private Funcionario toEntity(FuncionarioDTO dto) {
		Funcionario funcionario = new Funcionario();
		funcionario.setIdFuncionario(dto.getIdFuncionario());
		funcionario.setNomeFuncionario(dto.getNomeFuncionario());
		funcionario.setTelefoneFuncionario(dto.getTelefoneFuncionario());
		funcionario.setCargo(cargoService.toEntity(dto.getCargo()));
		funcionario.setPatio(patioService.toEntity(dto.getPatio()));
		
		return funcionario;
	}
	
	private FuncionarioDTO toDTO(Funcionario funcionario) {
		FuncionarioDTO dto = new FuncionarioDTO();
		dto.setIdFuncionario(funcionario.getIdFuncionario());
		dto.setNomeFuncionario(funcionario.getNomeFuncionario());
		dto.setTelefoneFuncionario(funcionario.getTelefoneFuncionario());
		dto.setCargo(cargoService.toDTO(funcionario.getCargo()));
		dto.setPatio(patioService.toDTO(funcionario.getPatio()));
		
		return dto;
	}
}