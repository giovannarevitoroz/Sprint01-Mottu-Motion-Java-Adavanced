package br.com.fiap.sprint1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.sprint1.dto.GerenteDTO;
import br.com.fiap.sprint1.service.GerenteService;

@RestController
@RequestMapping("api/gerentes")
public class GerenteController {
	
	@Autowired
	private GerenteService gerenteService;
	
	// Criar novo gerente
	@PostMapping
	public ResponseEntity<GerenteDTO> criar(@RequestBody GerenteDTO gerenteDTO) {
		GerenteDTO novoGerente = gerenteService.salvar(gerenteDTO);
		return ResponseEntity.status(201).body(novoGerente);
	}
	
	// Listar todos os gerentes
	@GetMapping
	public ResponseEntity<List<GerenteDTO>> listarTodos() {
		return ResponseEntity.ok(gerenteService.listarTodos());
	}
	
	// Buscar gerente por ID
	@GetMapping("/{id}")
	public ResponseEntity<GerenteDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(gerenteService.buscarPorId(id));
	}
	
	// Buscar gerente por CPF
	@GetMapping("/buscar-por-cpf/{cpf}") 
	public ResponseEntity<GerenteDTO> buscarPorCpf(@PathVariable String cpf) {
		return ResponseEntity.ok(gerenteService.buscarPorCpf(cpf));
	}
	
	// Buscar gerente pelo ID do Patio
	@GetMapping("/buscar-por-patio/{patioId}") 
	public ResponseEntity<Page<GerenteDTO>> buscarPorPatio(@PathVariable Long patioId, Pageable pageable) {
		return ResponseEntity.ok(gerenteService.buscarPorPatio(patioId, pageable));
	}
	
	// Atualizar gerente
	@PutMapping("/{id}") 
	public ResponseEntity<GerenteDTO> atualizar(@PathVariable Long id, @RequestBody GerenteDTO gerenteDTO) {
		return ResponseEntity.ok(gerenteService.atualizar(id, gerenteDTO));
	}
	
	// Deletar o Gerente
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		gerenteService.deletarGerente(id);
		return ResponseEntity.noContent().build();
	}
	
}