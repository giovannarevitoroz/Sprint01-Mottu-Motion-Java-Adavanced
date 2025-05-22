package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Gerente;
import br.com.fiap.sprint1.model.Patio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {

    // Busca por CPF (não precisa de paginação pois CPF é único)
    Optional<Gerente> findByCpfGerente(String cpf);

    // JPQL paginado para buscar todos os gerentes
    @Query("SELECT g FROM Gerente g")
    Page<Gerente> findAll(Pageable pageable);

    Page<Gerente> findByPatio(Patio patio, Pageable pageable);

    // Native Query paginada para buscar por ID do Pátio
    @Query(value = "SELECT * FROM gerente WHERE patio_id_patio = :idPatio",
            countQuery = "SELECT COUNT(*) FROM gerente WHERE patio_id_patio = :idPatio",
            nativeQuery = true)
    Page<Gerente> buscarPorPatio(@Param("idPatio") Long idPatio, Pageable pageable);

    // Usando o findById padrão do JpaRepository
    Optional<Gerente> findById(Long id);
}