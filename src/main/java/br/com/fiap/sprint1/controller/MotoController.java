package br.com.fiap.sprint1.controller;

import br.com.fiap.sprint1.dto.MotoDTO;
import br.com.fiap.sprint1.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    // Criar nova moto
    @PostMapping
    public ResponseEntity<MotoDTO> criar(@RequestBody MotoDTO motoDTO) {
        MotoDTO novaMoto = motoService.salvar(motoDTO);
        return ResponseEntity.status(201).body(novaMoto);
    }

    // Listar todas as motos
    @GetMapping
    public ResponseEntity<List<MotoDTO>> listarTodas() {
        return ResponseEntity.ok(motoService.listarTodos());
    }

    // Listar motos por modelo com paginação
    @GetMapping("/buscar-por-modelo")
    public ResponseEntity<Page<MotoDTO>> listarPorModelo(@RequestParam String modelo, Pageable pageable) {
        return ResponseEntity.ok(motoService.listarPorModelo(modelo, pageable));
    }

    // Buscar moto por ID
    @GetMapping("/{id}")
    public ResponseEntity<MotoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoService.buscarPorId(id));
    }

    // Buscar moto por placa
    @GetMapping("/placa/{placa}")
    public ResponseEntity<MotoDTO> buscarPorPlaca(@PathVariable String placa) {
        return ResponseEntity.ok(motoService.buscarPorPlaca(placa));
    }

    // Buscar moto por chassi
    @GetMapping("/chassi/{chassi}")
    public ResponseEntity<MotoDTO> buscarPorChassi(@PathVariable String chassi) {
        return ResponseEntity.ok(motoService.buscarPorChassi(chassi));
    }

    // Atualizar moto
    @PutMapping("/{id}")
    public ResponseEntity<MotoDTO> atualizar(@PathVariable Long id, @RequestBody MotoDTO motoDTO) {
        return ResponseEntity.ok(motoService.atualizar(id, motoDTO));
    }

    // Deletar moto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        motoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
