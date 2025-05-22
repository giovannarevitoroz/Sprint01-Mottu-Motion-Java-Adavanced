package br.com.fiap.sprint1.controller;

import br.com.fiap.sprint1.dto.ClienteDTO;
import br.com.fiap.sprint1.model.Moto;
import br.com.fiap.sprint1.service.ClienteService;
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
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Cadastrar novo cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO novoCliente = clienteService.cadastrar(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    // Listar todos os clientes (paginado)
    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> listarTodos(
            @PageableDefault(size = 10, sort = "nomeCliente") Pageable pageable) {
        Page<ClienteDTO> clientes = clienteService.listarTodos(pageable);
        return ResponseEntity.ok(clientes);
    }

    // Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    // Buscar cliente por CPF
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> buscarPorCpf(@PathVariable String cpf) {
        ClienteDTO cliente = clienteService.buscarPorCpf(cpf);
        return ResponseEntity.ok(cliente);
    }

    // Buscar cliente por email
    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteDTO> buscarPorEmail(@PathVariable String email) {
        ClienteDTO cliente = clienteService.buscarPorEmail(email);
        return ResponseEntity.ok(cliente);
    }

    // Buscar cliente por telefone
    @GetMapping("/telefone/{telefone}")
    public ResponseEntity<ClienteDTO> buscarPorTelefone(@PathVariable String telefone) {
        ClienteDTO cliente = clienteService.buscarPorTelefone(telefone);
        return ResponseEntity.ok(cliente);
    }

    // Buscar cliente por placa da moto
    @GetMapping("/placa-moto/{placa}")
    public ResponseEntity<ClienteDTO> buscarPorPlacaMoto(@PathVariable String placa) {
        ClienteDTO cliente = clienteService.buscarPorPlacaMoto(placa);
        return ResponseEntity.ok(cliente);
    }

    // Buscar motos por ID do cliente
    @GetMapping("/{id}/motos")
    public ResponseEntity<List<Moto>> buscarMotosPorIdCliente(@PathVariable Long id) {
        List<Moto> motos = clienteService.buscarMotosPorIdCliente(id);
        return ResponseEntity.ok(motos);
    }

    // Buscar motos por CPF do cliente
    @GetMapping("/buscar-moto-por-cpf/{cpf}/motos")
    public ResponseEntity<List<Moto>> buscarMotosPorCpfCliente(@PathVariable String cpf) {
        List<Moto> motos = clienteService.buscarMotosPorCpfCliente(cpf);
        return ResponseEntity.ok(motos);
    }

    // Atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(id, clienteDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    // Deletar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}