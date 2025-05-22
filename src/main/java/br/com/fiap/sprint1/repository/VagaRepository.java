package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Vaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    // Consulta derivada - corrigido tipo para Integer
    Page<Vaga> findByStatusOcupada(Integer status, Pageable pageable);

    // Consulta JPQL - correto
    @Query("SELECT v FROM Vaga v WHERE v.setor.idSetor = :idSetor ORDER BY v.numeroVaga")
    Page<Vaga> findBySetorId(Long idSetor, Pageable pageable);

    // Consulta JPQL com LIKE - correto
    @Query("SELECT v FROM Vaga v WHERE LOWER(v.numeroVaga) LIKE LOWER(CONCAT('%', :numero, '%'))")
    Page<Vaga> findByNumeroContaining(String numero, Pageable pageable);

    // Consulta nativa - corrigido nome da coluna
    @Query(value = "SELECT COUNT(*) FROM vaga WHERE status_ocupada = :status AND setor_id_setor = :setorId", nativeQuery = true)
    int countByStatusAndSetor(Integer status, Long setorId);

    // Consulta para verificar disponibilidade - correto
    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN TRUE ELSE FALSE END FROM Vaga v WHERE v.id = :id AND v.statusOcupada = 0")
    boolean isVagaDisponivel(@Param("id") Long id);

    // Consulta padrão com paginação - correto
    Page<Vaga> findAll(Pageable pageable);

    // Consulta por ID - correto
    Optional<Vaga> findById(Long id);
}