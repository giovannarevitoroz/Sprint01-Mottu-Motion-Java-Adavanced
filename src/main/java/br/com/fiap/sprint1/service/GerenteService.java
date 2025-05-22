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

import br.com.fiap.sprint1.dto.GerenteDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.model.Gerente;
import br.com.fiap.sprint1.repository.GerenteRepository;

@Service
public class GerenteService {
	
	@Autowired
	private GerenteRepository gerenteRepository;
	
	@Autowired
	private PatioService patioService;
	
	@CachePut(value="gerentes", key="#result.idGerente")
	public GerenteDTO salvar(GerenteDTO gerenteDTO) {
		Gerente gerente = toEntity(gerenteDTO);
		return toDTO(gerenteRepository.save(gerente));
	}
	
	@Cacheable(value="gerentesAll")
	public List<GerenteDTO> listarTodos() {
		return gerenteRepository.findAll()
				.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	@Cacheable(value="gerentePorId", key="#id")
	public GerenteDTO buscarPorId(Long id) {
		Gerente gerente = gerenteRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Gerente com ID " + id + " não encontrado."));
		return toDTO(gerente);
	}
	
	public Page<GerenteDTO> buscarPorPatio(Long patioId, Pageable pageable) {
		Page<Gerente> gerente = gerenteRepository.buscarPorPatio(patioId, pageable);
		if (gerente.isEmpty()) {
			throw new RecursoNaoEncontradoException("Gerente não encontrado para Patio de ID: " + patioId);
		}
		return gerente.map(this::toDTO);
	}
	
	@Cacheable(value="gerentePorCpf", key="#cpf")
	public GerenteDTO buscarPorCpf(String cpf) {
		Gerente gerente = gerenteRepository.findByCpfGerente(cpf)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Gerente com CPF " + cpf + " não encontrado."));
		return toDTO(gerente);
	}
	
	@CachePut(value="gerentePorId", key="#id")
	public GerenteDTO atualizar(Long id, GerenteDTO gerenteDTO) {
		Gerente gerenteExistente = gerenteRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Gerente com ID " + id + " não encontrado."));
		
		gerenteExistente.setNomeGerente(gerenteDTO.getNomeGerente());
		gerenteExistente.setCpfGerente(gerenteDTO.getCpfGerente());
		gerenteExistente.setTelefoneGerente(gerenteDTO.getTelefoneGerente());
		gerenteExistente.setPatio(patioService.toEntity(gerenteDTO.getPatio()));
		
		return toDTO(gerenteRepository.save(gerenteExistente));
	}
	
	@CacheEvict(value="gerentes", key="#id")
	public void deletarGerente(Long id) {
		if (!gerenteRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Gerente não encontrado com o ID: " + id);
		}
		
		gerenteRepository.deleteById(id);
	}
	
	private Gerente toEntity(GerenteDTO dto) {
		Gerente gerente = new Gerente();
		gerente.setIdGerente(dto.getIdGerente());
		gerente.setNomeGerente(dto.getNomeGerente());
		gerente.setTelefoneGerente(dto.getTelefoneGerente());
		gerente.setCpfGerente(dto.getCpfGerente());
		gerente.setPatio(patioService.toEntity(dto.getPatio()));
		
		return gerente;
	}
	
	private GerenteDTO toDTO(Gerente gerente) {
		GerenteDTO dto = new GerenteDTO();
		dto.setIdGerente(gerente.getIdGerente());
		dto.setNomeGerente(gerente.getNomeGerente());
		dto.setTelefoneGerente(gerente.getTelefoneGerente());
		dto.setCpfGerente(gerente.getCpfGerente());
		dto.setPatio(patioService.toDTO(gerente.getPatio()));
		
		return dto;
	}
}