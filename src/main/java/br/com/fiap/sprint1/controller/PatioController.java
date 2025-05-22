package br.com.fiap.sprint1.controller;

import br.com.fiap.sprint1.dto.FuncionarioDTO;
import br.com.fiap.sprint1.dto.GerenteDTO;
import br.com.fiap.sprint1.dto.PatioDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.service.PatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patios")
public class PatioController {

    @Autowired
    private PatioService patioService;

    // Listar todos os pátios (GET)
    @GetMapping
    public ResponseEntity<Page<PatioDTO>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(patioService.listarTodos(pageable));
    }

    // Buscar pátios por nome (GET com parâmetro de query)
    @GetMapping("/buscar-por-nome")
    public ResponseEntity<Page<PatioDTO>> buscarPorNome(
            @RequestParam String nome,
            Pageable pageable) {
        return ResponseEntity.ok(patioService.buscarPorNome(nome, pageable));
    }

    // Buscar pátios por localização (GET com parâmetro de query)
    @GetMapping("/buscar-por-localizacao")
    public ResponseEntity<Page<PatioDTO>> buscarPorLocalizacao(
            @RequestParam String localizacao,
            Pageable pageable) {
        return ResponseEntity.ok(patioService.buscarPorLocalizacao(localizacao, pageable));
    }

    // Listar funcionários de um pátio (GET)
    @GetMapping("/{id}/funcionarios")
    public ResponseEntity<Page<FuncionarioDTO>> buscarFuncionariosPorPatio(
            @PathVariable Long id,
            Pageable pageable) {
        return ResponseEntity.ok(patioService.buscarFuncionariosPorPatio(id, pageable));
    }

    // Listar gerentes de um pátio (GET)
    @GetMapping("/{id}/gerentes")
    public ResponseEntity<Page<GerenteDTO>> buscarGerentesPorPatio(
            @PathVariable Long id,
            Pageable pageable) {
        return ResponseEntity.ok(patioService.buscarGerentesPorPatio(id, pageable));
    }

    // Cadastrar novo pátio (POST)
    @PostMapping
    public ResponseEntity<PatioDTO> salvar(@RequestBody PatioDTO dto) {
        return ResponseEntity.created(null).body(patioService.salvar(dto));
    }

    // Atualizar pátio (PUT)
    @PutMapping("atualizar/{id}")
    public ResponseEntity<PatioDTO> atualizar(
            @PathVariable Long id,
            @RequestBody PatioDTO dto) {
        return ResponseEntity.ok(patioService.atualizar(id, dto));
    }

    // Deletar pátio (DELETE)
    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        patioService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}