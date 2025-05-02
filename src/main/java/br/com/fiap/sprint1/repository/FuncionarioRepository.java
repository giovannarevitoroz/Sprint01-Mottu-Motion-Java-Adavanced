package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    // Métodos básicos (herdados)
    @Override
    List<Funcionario> findAll();

    @Override
    Optional<Funcionario> findById(Long id);

    // Consultas por Cargo
    @Query("SELECT f FROM Funcionario f WHERE f.cargo.idCargo = :idCargo")
    List<Funcionario> findByCargoId(@Param("idCargo") Long idCargo);

    @Query("SELECT f FROM Funcionario f WHERE f.cargo.nomeCargo = :nomeCargo")
    List<Funcionario> findByNomeCargo(@Param("nomeCargo") String nomeCargo);

    // Consultas por Patio
    @Query("SELECT f FROM Funcionario f WHERE f.patio.id = :idPatio")
    List<Funcionario> findByPatioId(@Param("idPatio") Long idPatio);

    // Consultas por Setor (através do Patio)
    @Query("SELECT f FROM Funcionario f JOIN f.patio p JOIN p.setores s WHERE s.idSetor = :idSetor")
    List<Funcionario> findBySetorId(@Param("idSetor") Long idSetor);

    @Query("SELECT f FROM Funcionario f JOIN f.patio p JOIN p.setores s WHERE s.tipoSetor = :tipoSetor")
    List<Funcionario> findByTipoSetor(@Param("tipoSetor") String tipoSetor);

    // Consultas nativas
    @Query(value = "SELECT f.* FROM tb_funcionario f " +
            "JOIN tb_cargo c ON f.cargo_id_cargo = c.id_cargo " +
            "WHERE c.nome_cargo LIKE %:nomeCargo%", nativeQuery = true)
    List<Funcionario> findByNomeCargoContaining(@Param("nomeCargo") String nomeCargo);

    @Query(value = "SELECT f.* FROM tb_funcionario f " +
            "JOIN tb_patio p ON f.patio_id_patio = p.id_patio " +
            "JOIN tb_setor s ON p.id_patio = s.patio_id_patio " +
            "WHERE s.tipo_setor = :tipoSetor", nativeQuery = true)
    List<Funcionario> findByTipoSetorNative(@Param("tipoSetor") String tipoSetor);

    // Consultas derivadas
    List<Funcionario> findByNomeFuncionarioContainingIgnoreCase(String nome);

    List<Funcionario> findByTelefoneFuncionarioStartingWith(String ddd);

    // Consulta para funcionários com nome específico e cargo específico
    @Query("SELECT f FROM Funcionario f WHERE f.nomeFuncionario = :nome AND f.cargo.nomeCargo = :cargo")
    List<Funcionario> findByNomeAndCargo(@Param("nome") String nome, @Param("cargo") String cargo);

    // Consulta para contar funcionários por cargo
    @Query("SELECT COUNT(f) FROM Funcionario f WHERE f.cargo.idCargo = :idCargo")
    Long countByCargoId(@Param("idCargo") Long idCargo);

    // Consulta para verificar existência por telefone
    boolean existsByTelefoneFuncionario(String telefone);

}