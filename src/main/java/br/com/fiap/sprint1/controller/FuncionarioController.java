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

import br.com.fiap.sprint1.dto.FuncionarioDTO;
import br.com.fiap.sprint1.service.FuncionarioService;

@RestController
@RequestMapping("api/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	// Criar novo funcionário
	@PostMapping
	public ResponseEntity<FuncionarioDTO> criar(@RequestBody FuncionarioDTO funcionarioDTO) {
		FuncionarioDTO novoFuncionario = funcionarioService.salvar(funcionarioDTO);
		return ResponseEntity.status(201).body(novoFuncionario);
	}
	
	// Listar todos os funcionários
	@GetMapping
	public ResponseEntity<List<FuncionarioDTO>> listarTodos() {
		return ResponseEntity.ok(funcionarioService.listarTodos());
	}
	
	// Buscar Funcionário por ID
	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(funcionarioService.buscarPorId(id));
	}
	
	// buscar funcionários por Cargo
	@GetMapping("/buscar-por-cargo/{idCargo}")
	public ResponseEntity<Page<FuncionarioDTO>> buscarFuncionariosPorCargo(@PathVariable Long idCargo, Pageable pageable) {
		return ResponseEntity.ok(funcionarioService.buscarFuncionariosPorCargo(idCargo, pageable));
	}
	
	// buscar funcionários por Pátio
	@GetMapping("/buscar-por-patio/{idPatio}")
	public ResponseEntity<Page<FuncionarioDTO>> buscarFuncionariosPorPatio(@PathVariable Long idPatio, Pageable pageable) {
		return ResponseEntity.ok(funcionarioService.buscarFuncionariosPorPatio(idPatio, pageable));
	}
	
	// buscar funcionários por Setor
	@GetMapping("/buscar-por-setor/{idSetor}")
	public ResponseEntity<Page<FuncionarioDTO>> buscarFuncionariosPorSetor(@PathVariable Long idSetor, Pageable pageable) {
		return ResponseEntity.ok(funcionarioService.buscarFuncionariosPorSetor(idSetor, pageable));
	}
	
	// buscar funcionários pelo nome
	@GetMapping("/buscar-por-nome/{nome}") 
	public ResponseEntity<Page<FuncionarioDTO>> buscarFuncionariosPorNome(@PathVariable String nome, Pageable pageable) {
		return ResponseEntity.ok(funcionarioService.buscarFuncionariosPorNome(nome, pageable));
	}
	
	// atualiza o funcionário
	@PutMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioDTO) {
		return ResponseEntity.ok(funcionarioService.atualizar(id, funcionarioDTO));
	}
	
	// deletar o funcionário
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		funcionarioService.deletarFuncionario(id);
		return ResponseEntity.noContent().build();
	}
}