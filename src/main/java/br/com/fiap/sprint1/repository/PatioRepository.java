package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Patio;
import br.com.fiap.sprint1.model.Funcionario;
import br.com.fiap.sprint1.model.Gerente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatioRepository extends JpaRepository<Patio, Long> {

    // Buscar todos os pátios
    @Override
    Page<Patio> findAll(Pageable pageable);

    // Buscar pátio por nome
    @Query("SELECT p FROM Patio p WHERE LOWER(p.nomePatio) LIKE LOWER(CONCAT('%', :nomePatio, '%'))")
    Page<Patio> findByNomePatioContainingIgnoreCase(String nomePatio, Pageable pageable);

    // Buscar pátio por localização
    @Query("SELECT p FROM Patio p WHERE LOWER(p.localizacaoPatio) LIKE LOWER(CONCAT('%', :localizacaoPatio, '%'))")
    Page<Patio> findByLocalizacaoPatioContainingIgnoreCase(String localizacaoPatio, Pageable pageable);

    // Buscar funcionários por pátio
    @Query("SELECT f FROM Funcionario f WHERE f.patio.idPatio = :idPatio")
    Page<Funcionario> findFuncionariosByPatioId(Long idPatio, Pageable pageable);

    // Buscar gerentes por pátio
    @Query("SELECT g FROM Gerente g WHERE g.patio.idPatio = :idPatio")
    Page<Gerente> findGerentesByPatioId(Long idPatio, Pageable pageable);
}
