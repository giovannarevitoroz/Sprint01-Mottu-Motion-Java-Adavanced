package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.ClienteDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.model.Cliente;
import br.com.fiap.sprint1.model.Moto;
import br.com.fiap.sprint1.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    private ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setNomeCliente(cliente.getNomeCliente());
        dto.setTelefoneCliente(cliente.getTelefoneCliente());
        dto.setCpfCliente(cliente.getCpfCliente());
        dto.setEmailCliente(cliente.getEmailCliente());
        dto.setSexoCliente(cliente.getSexoCliente());
        dto.setMotos(cliente.getMotos());
        return dto;
    }

    private Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNomeCliente(clienteDTO.getNomeCliente());
        cliente.setTelefoneCliente(clienteDTO.getTelefoneCliente());
        cliente.setCpfCliente(clienteDTO.getCpfCliente());
        cliente.setEmailCliente(clienteDTO.getEmailCliente());
        cliente.setSexoCliente(clienteDTO.getSexoCliente());
        return cliente;
    }

    // Cadastrar novo cliente e atualiza cache
    @CachePut(value = "clientes", key = "#result.idCliente")
    public ClienteDTO cadastrar(ClienteDTO clienteDTO) {
        Cliente cliente = toEntity(clienteDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return toDTO(clienteSalvo);
    }

    // Listar todos os clientes (não é cacheado por ser paginado)
    public Page<ClienteDTO> listarTodos(Pageable pageable) {
        return clienteRepository.findAllClientes(pageable)
                .map(this::toDTO);
    }

    // Buscar cliente por ID com cache
    @Cacheable(value = "clientes", key = "#id")
    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findClienteById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id));
        return toDTO(cliente);
    }

    // Buscar cliente por CPF com cache
    @Cacheable(value = "clientesPorCpf", key = "#cpf")
    public ClienteDTO buscarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.findClienteByCpf(cpf)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o CPF: " + cpf));
        return toDTO(cliente);
    }

    // Buscar cliente por email com cache
    @Cacheable(value = "clientesPorEmail", key = "#email")
    public ClienteDTO buscarPorEmail(String email) {
        Cliente cliente = clienteRepository.findClienteByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o email: " + email));
        return toDTO(cliente);
    }

    // Buscar cliente por telefone (sem cache, mas pode adicionar se quiser)
    public ClienteDTO buscarPorTelefone(String telefone) {
        Cliente cliente = clienteRepository.findByTelefone(telefone)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o telefone: " + telefone));
        return toDTO(cliente);
    }

    // Buscar cliente por placa da moto (sem cache, depende da frequência de uso)
    public ClienteDTO buscarPorPlacaMoto(String placa) {
        Cliente cliente = clienteRepository.findClienteByPlacaMoto(placa)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado para a placa: " + placa));
        return toDTO(cliente);
    }

    // Buscar motos por ID do cliente
    public List<Moto> buscarMotosPorIdCliente(Long idCliente) {
        List<Moto> motos = clienteRepository.findMotosByClienteId(idCliente);
        if (motos.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhuma moto encontrada para o cliente com ID: " + idCliente);
        }
        return motos;
    }

    // Buscar motos por CPF do cliente
    public List<Moto> buscarMotosPorCpfCliente(String cpf) {
        List<Moto> motos = clienteRepository.findMotosByCpfCliente(cpf);
        if (motos.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhuma moto encontrada para o cliente com CPF: " + cpf);
        }
        return motos;
    }

    // Atualizar cliente e atualizar cache
    @CachePut(value = "clientes", key = "#id")
    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id));

        clienteExistente.setNomeCliente(clienteDTO.getNomeCliente());
        clienteExistente.setTelefoneCliente(clienteDTO.getTelefoneCliente());
        clienteExistente.setCpfCliente(clienteDTO.getCpfCliente());
        clienteExistente.setEmailCliente(clienteDTO.getEmailCliente());
        clienteExistente.setSexoCliente(clienteDTO.getSexoCliente());

        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
        return toDTO(clienteAtualizado);
    }

    // Deletar cliente e remover do cache
    @CacheEvict(value = "clientes", key = "#id")
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
