package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Setor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

    // Buscar setores por status
    Page<Setor> findByStatusSetor(String statusSetor, Pageable pageable);

    // Buscar por tipo ignorando case (nome do setor)
    List<Setor> findByTipoSetorIgnoreCase(String tipoSetor);

    // Buscar por tipo com LIKE (JPQL, com paginação)
    @Query("SELECT s FROM Setor s WHERE LOWER(s.tipoSetor) LIKE LOWER(CONCAT('%', :tipoSetor, '%'))")
    Page<Setor> buscarPorTipoSetor(String tipoSetor, Pageable pageable);

    // Buscar setores por ID do pátio (com paginação)
    Page<Setor> findByPatioIdPatio(Long idPatio, Pageable pageable);

    // Listar todos os setores ordenados por tipo (nome), com paginação
    Page<Setor> findAllByOrderByTipoSetorAsc(Pageable pageable);

    // Consulta nativa: contar setores por pátio
    @Query(value = "SELECT COUNT(*) FROM setor WHERE patio_id_patio = :patioId", nativeQuery = true)
    int contarSetoresPorPatio(Long patioId);

    // Consultar um setor por ID
    Optional<Setor> findById(Long id);
}
