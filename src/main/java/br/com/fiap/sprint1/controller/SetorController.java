package br.com.fiap.sprint1.controller;

import br.com.fiap.sprint1.dto.SetorDTO;
import br.com.fiap.sprint1.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/setores")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @GetMapping
    public ResponseEntity<Page<SetorDTO>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(setorService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(setorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SetorDTO> criar(@RequestBody SetorDTO setorDTO) {
        return ResponseEntity.status(201).body(setorService.cadastrar(setorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetorDTO> atualizar(@PathVariable Long id, @RequestBody SetorDTO setorDTO) {
        return ResponseEntity.ok(setorService.atualizar(id, setorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        setorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
