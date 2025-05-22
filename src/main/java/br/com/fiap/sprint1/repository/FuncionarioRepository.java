package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    // Busca todos os funcionários de um cargo específico (por ID)
    @Query("SELECT f FROM Funcionario f WHERE f.cargo.idCargo = :idCargo")
    Page<Funcionario> findByCargoId(@Param("idCargo") Long idCargo, Pageable pageable);

    // Busca todos os funcionários alocados em um pátio específico
    @Query("SELECT f FROM Funcionario f WHERE f.patio.id = :idPatio")
    Page<Funcionario> findByPatioId(@Param("idPatio") Long idPatio, Pageable pageable);

    // Busca todos os funcionários que pertencem a um setor específico (por ID), via pátio
    @Query("SELECT f FROM Funcionario f JOIN f.patio p JOIN p.setores s WHERE s.idSetor = :idSetor")
    Page<Funcionario> findBySetorId(@Param("idSetor") Long idSetor, Pageable pageable);

    // Busca funcionários cujo nome contenha parte do texto (ignora maiúsculas/minúsculas)
    Page<Funcionario> findByNomeFuncionarioContainingIgnoreCase(String nome, Pageable pageable);

    // Busca funcionário por ID (paginado — retornará 0 ou 1 resultado)
    @Query("SELECT f FROM Funcionario f WHERE f.idFuncionario = :idFuncionario")
    Page<Funcionario> findByIdFuncionario(@Param("idFuncionario") Long idFuncionario, Pageable pageable);

}
