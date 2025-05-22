package br.com.fiap.sprint1.controller;

import br.com.fiap.sprint1.dto.CargoDTO;
import br.com.fiap.sprint1.service.CargoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargo")
public class CargoController {

    private final CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    // Cadastrar novo cargo
    @PostMapping
    public ResponseEntity<CargoDTO> cadastrar(@RequestBody @Valid CargoDTO cargoDTO) {
        CargoDTO novoCargo = cargoService.cadastrar(cargoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCargo);
    }

    // Listar todos os cargos (paginado)
    @GetMapping
    public ResponseEntity<Page<CargoDTO>> listarTodos(
            @PageableDefault(size = 10, sort = "nomeCargo") Pageable pageable) {
        Page<CargoDTO> cargos = cargoService.listarTodos(pageable);
        return ResponseEntity.ok(cargos);
    }

    // Buscar cargo por ID
    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> buscarPorId(@PathVariable Long id) {
        CargoDTO cargo = cargoService.buscarPorId(id);
        return ResponseEntity.ok(cargo);
    }

    // Buscar cargo por nome exato
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CargoDTO>> buscarPorNome(@PathVariable String nome) {
        List<CargoDTO> cargos = cargoService.buscarPorNomeExato(nome);
        return ResponseEntity.ok(cargos);
    }

    // Buscar cargos por parte do nome (paginado)
    @GetMapping("/busca")
    public ResponseEntity<Page<CargoDTO>> buscarPorParteDoNome(
            @RequestParam String nome,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<CargoDTO> cargos = cargoService.buscarPorParteDoNome(nome, pageable);
        return ResponseEntity.ok(cargos);
    }

    // Buscar cargo pelo ID do funcionário
    @GetMapping("/funcionario/{idFuncionario}")
    public ResponseEntity<CargoDTO> buscarPorIdFuncionario(@PathVariable Long idFuncionario) {
        CargoDTO cargo = cargoService.buscarPorIdFuncionario(idFuncionario);
        return ResponseEntity.ok(cargo);
    }

    // Atualizar cargo
    @PutMapping("/{id}")
    public ResponseEntity<CargoDTO> atualizarCargo(
            @PathVariable Long id,
            @RequestBody @Valid CargoDTO cargoDTO) {
        CargoDTO cargoAtualizado = cargoService.atualizarCargo(id, cargoDTO);
        return ResponseEntity.ok(cargoAtualizado);
    }

    // Deletar cargo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCargo(@PathVariable Long id) {
        cargoService.deletarCargo(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar cargos por prefixo
    @GetMapping("/prefixo/{prefixo}")
    public ResponseEntity<List<CargoDTO>> buscarPorPrefixo(@PathVariable String prefixo) {
        List<CargoDTO> cargos = cargoService.buscarPorPrefixo(prefixo);
        return ResponseEntity.ok(cargos);
    }
}