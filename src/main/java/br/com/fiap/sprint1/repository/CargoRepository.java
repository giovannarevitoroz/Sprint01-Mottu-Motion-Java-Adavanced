package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    // Busca paginada de todos os cargos ordenados por nome
    Page<Cargo> findAllByOrderByNomeCargoAsc(Pageable pageable);

    // Busca paginada por nome contendo ignore case
    Page<Cargo> findByNomeCargoContainingIgnoreCase(String nome, Pageable pageable);

    // 1. Busca por nome ignorando case sensitive (JPQL)
    @Query("SELECT c FROM Cargo c WHERE LOWER(c.nomeCargo) = LOWER(:nome)")
    List<Cargo> FindByNomeEqualsIgnoreCase(@Param("nome") String nomeCargo);

    // 2. Busca cargo por ID (JPQL)
    @Query("SELECT c FROM Cargo c WHERE c.idCargo = :id")
    Optional<Cargo> FindCargoById(@Param("id") Long idCargo);

    // 3. Busca cargo por ID do funcionário (Native Query)
    @Query(value = "SELECT c.* FROM cargo c " +
            "INNER JOIN funcionario f ON c.id_cargo = f.cargo_id " +
            "WHERE f.id_funcionario = :idFuncionario",
            nativeQuery = true)
    Optional<Cargo> FindCargoByIdFuncionario(@Param("idFuncionario") Long idFuncionario);

    // Encontra cargos com nomes que começam com determinado prefixo
    List<Cargo> findByNomeCargoStartingWithIgnoreCase(String prefixo);

    // Encontra cargos criados após determinada data (se tiver campo 'dataCriacao')
    List<Cargo> findByDataCriacaoAfter(LocalDateTime data);
}