package br.com.fiap.sprint1.controller;

import br.com.fiap.sprint1.dto.VagaDTO;
import br.com.fiap.sprint1.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @GetMapping
    public ResponseEntity<?> listar(Pageable pageable) {
        return ResponseEntity.ok(vagaService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vagaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid VagaDTO vagaDTO) {
        return ResponseEntity.status(201).body(vagaService.cadastrar(vagaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid VagaDTO vagaDTO) {
        return ResponseEntity.ok(vagaService.atualizar(id, vagaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        vagaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status")
    public ResponseEntity<?> buscarPorStatus(@RequestParam Integer status, Pageable pageable) {
        return ResponseEntity.ok(vagaService.findByStatus(status, pageable));
    }

    @GetMapping("/setor/{setorId}")
    public ResponseEntity<?> buscarPorSetor(@PathVariable Long setorId, Pageable pageable) {
        return ResponseEntity.ok(vagaService.findBySetor(setorId, pageable));
    }

    @GetMapping("/numero")
    public ResponseEntity<?> buscarPorNumero(@RequestParam String numero, Pageable pageable) {
        return ResponseEntity.ok(vagaService.findByNumero(numero, pageable));
    }

    @GetMapping("/disponivel/{id}")
    public ResponseEntity<?> verificarDisponibilidade(@PathVariable Long id) {
        return ResponseEntity.ok(vagaService.isVagaDisponivel(id));
    }

    @GetMapping("/contagem")
    public ResponseEntity<?> contarPorStatusEPorSetor(
            @RequestParam Integer status,
            @RequestParam Long setorId
    ) {
        return ResponseEntity.ok(vagaService.countByStatusAndSetor(status, setorId));
    }
}
