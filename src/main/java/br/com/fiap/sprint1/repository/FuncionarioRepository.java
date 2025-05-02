package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    // Método para listar todos os funcionários (já herdado do JpaRepository)
    @Override
    List<Funcionario> findAll();

    // Método para encontrar um funcionário por ID (já herdado do JpaRepository)
    @Override
    Optional<Funcionario> findById(Long id);

    // Consulta JPQL para buscar funcionários por cargo
    @Query("SELECT f FROM Funcionario f WHERE f.cargo.id = :idCargo")
    List<Funcionario> findByCargo(@Param("idCargo") Long idCargo);

    // Consulta JPQL para buscar funcionários por setor (assumindo que Cargo tem um campo 'setor')
    @Query("SELECT f FROM Funcionario f WHERE f.cargo.setor = :setor")
    List<Funcionario> findBySetor(@Param("setor") String setor);

    // Consulta JPQL alternativa para buscar funcionários por setor (com JOIN explícito)
    @Query("SELECT f FROM Funcionario f JOIN f.cargo c WHERE c.setor = :setor")
    List<Funcionario> findBySetorComJoin(@Param("setor") String setor);

    // Consulta nativa para buscar funcionários por setor
    @Query(value = "SELECT f.* FROM tb_funcionario f " +
            "INNER JOIN tb_cargo c ON f.cargo_id_cargo = c.id_cargo " +
            "WHERE c.setor_cargo = :setor", nativeQuery = true)
    List<Funcionario> findBySetorNative(@Param("setor") String setor);

    // Consulta derivada do Spring Data JPA para buscar por cargo
    List<Funcionario> findByCargo_Id(Long cargoId);

    // Consulta derivada alternativa para buscar por setor (se o relacionamento estiver configurado)
    List<Funcionario> findByCargo_Setor(String setor);
}