package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    // 1. Buscar cliente por ID (JPQL)
    @Query("SELECT c FROM Cliente c WHERE c.idCliente = :id")
    Optional<Cliente> FindClienteById(@Param("id") Long idCliente);

    // 2. Buscar cliente por CPF (JPQL)
    @Query("SELECT c FROM Cliente c WHERE c.cpfCliente = :cpf")
    Optional<Cliente> FindClienteByCpf(@Param("cpf") String cpf);

    // 3. Buscar cliente por email (Native Query)
    @Query(value = "SELECT * FROM cliente WHERE email_cliente = :email", nativeQuery = true)
    Optional<Cliente> FindClienteByEmail(@Param("email") String email);

    // 4. Buscar cliente por placa da moto (Native Query)
    @Query(value = "SELECT c.* FROM cliente c " +
            "JOIN moto m ON c.id_cliente = m.cliente_id " +
            "WHERE m.placa_moto = :placa", nativeQuery = true)
    Optional<Cliente> FindClienteByPlacaMoto(@Param("placa") String placa);
}