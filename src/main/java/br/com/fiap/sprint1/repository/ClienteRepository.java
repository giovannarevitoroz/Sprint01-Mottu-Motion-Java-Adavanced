package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Cliente;
import br.com.fiap.sprint1.model.Moto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // 1. Buscar cliente por ID (JPQL)
    @Query("SELECT c FROM Cliente c WHERE c.idCliente = :id")
    Optional<Cliente> findClienteById(@Param("id") Long idCliente);

    // 2. Buscar cliente por CPF (JPQL)
    @Query("SELECT c FROM Cliente c WHERE c.cpfCliente = :cpf")
    Optional<Cliente> findClienteByCpf(@Param("cpf") String cpf);

    // 3. Buscar cliente por email (Native Query)
    @Query(value = "SELECT * FROM cliente WHERE email_cliente = :email", nativeQuery = true)
    Optional<Cliente> findClienteByEmail(@Param("email") String email);

    // 4. Buscar cliente por placa da moto (Native Query)
    @Query(value = "SELECT c.* FROM cliente c " +
            "JOIN moto m ON c.id_cliente = m.cliente_id " +
            "WHERE m.placa_moto = :placa", nativeQuery = true)
    Optional<Cliente> findClienteByPlacaMoto(@Param("placa") String placa);

    // 5. Listar todos os clientes com paginação
    @Query("SELECT c FROM Cliente c ORDER BY c.nomeCliente")
    Page<Cliente> findAllClientes(Pageable pageable);

    // 6. Buscar moto por placa (retorna a moto com informações do cliente)
    @Query("SELECT m FROM Moto m WHERE m.placaMoto = :placa")
    Optional<Moto> findMotoByPlaca(@Param("placa") String placa);

    // 7. Listar todas as motos de um cliente específico
    @Query("SELECT m FROM Moto m WHERE m.cliente.idCliente = :idCliente")
    List<Moto> findMotosByClienteId(@Param("idCliente") Long idCliente);

    // 8. Buscar cliente por telefone
    @Query("SELECT c FROM Cliente c WHERE c.telefoneCliente = :telefone")
    Optional<Cliente> findByTelefone(@Param("telefone") String telefone);

    @Query("SELECT m FROM Moto m WHERE m.cliente.cpfCliente = :cpf")
    List<Moto> findMotosByCpfCliente(@Param("cpf") String cpf);
}

