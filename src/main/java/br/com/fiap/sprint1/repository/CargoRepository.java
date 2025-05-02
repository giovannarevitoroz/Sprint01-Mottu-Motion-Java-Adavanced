package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    // 1. JPQL - Busca por nome ignorando case sensitive
    @Query("SELECT c FROM Cargo c WHERE LOWER(c.nomeCargo) = LOWER(:nome)")
    List<Cargo> FindByNomeEqualsIgnoreCase(@Param("nome") String nomeCargo);

    // 2. JPQL - Busca cargo por ID
    @Query("SELECT c FROM Cargo c WHERE c.idCargo = :id")
    Optional<Cargo> FindCargoById(@Param("id") Long idCargo);

    // 3. Native Query - Busca cargo por ID do funcionário
    @Query(value = "SELECT c.* FROM cargo c " +
            "INNER JOIN funcionario f ON c.id_cargo = f.cargo_id " +
            "WHERE f.id_funcionario = :idFuncionario",
            nativeQuery = true)
    Optional<Cargo> FindCargoByIdFuncionario(@Param("idFuncionario") Long idFuncionario);
}