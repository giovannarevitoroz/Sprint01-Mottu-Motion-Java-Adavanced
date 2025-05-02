package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    // Busca todos os funcionários de um cargo específico (por ID)
    @Query("SELECT f FROM Funcionario f WHERE f.cargo.idCargo = :idCargo")
    List<Funcionario> findByCargoId(@Param("idCargo") Long idCargo);

    // Busca todos os funcionários alocados em um pátio específico
    @Query("SELECT f FROM Funcionario f WHERE f.patio.id = :idPatio")
    List<Funcionario> findByPatioId(@Param("idPatio") Long idPatio);

    // Busca todos os funcionários que pertencem a um setor específico (por ID), via pátio
    @Query("SELECT f FROM Funcionario f JOIN f.patio p JOIN p.setores s WHERE s.idSetor = :idSetor")
    List<Funcionario> findBySetorId(@Param("idSetor") Long idSetor);

    // Busca funcionários por tipo de setor via native query
    @Query(value = "SELECT f.* FROM tb_funcionario f " +
            "JOIN tb_patio p ON f.patio_id_patio = p.id_patio " +
            "JOIN tb_setor s ON p.id_patio = s.patio_id_patio " +
            "WHERE s.tipo_setor = :tipoSetor", nativeQuery = true)
    List<Funcionario> findByTipoSetor(@Param("tipoSetor") String tipoSetor);

    // Busca funcionários cujo nome contenha parte do texto (ignora maiúsculas/minúsculas)
    List<Funcionario> findByNomeFuncionarioContainingIgnoreCase(String nome);

    // Busca funcionários cujo telefone começa com determinado DDD
    List<Funcionario> findByTelefoneFuncionarioStartingWith(String ddd);

    // Busca funcionários com nome exato e nome de cargo exato
    @Query("SELECT f FROM Funcionario f WHERE f.nomeFuncionario = :nome AND f.cargo.nomeCargo = :cargo")
    List<Funcionario> findByNomeAndCargo(@Param("nome") String nome, @Param("cargo") String cargo);
}
