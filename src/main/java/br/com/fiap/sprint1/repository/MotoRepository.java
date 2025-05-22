package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Moto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MotoRepository extends JpaRepository<Moto, Long> {

    // Buscar por placa (placa é única e ignore case)
    @Query("SELECT m FROM Moto m WHERE LOWER(m.placaMoto) = LOWER(:placa)")
    Optional<Moto> findByPlacaIgnoreCase(@Param("placa") String placa);

    // Buscar por modelo (contendo, ignore case, paginado)
    @Query("SELECT m FROM Moto m WHERE LOWER(m.modeloMoto) LIKE LOWER(CONCAT('%', :modelo, '%'))")
    Page<Moto> findByModeloContainingIgnoreCase(@Param("modelo") String modelo, Pageable pageable);

    // Buscar todos ordenados por modelo (asc)
    @Query("SELECT m FROM Moto m ORDER BY m.modeloMoto ASC")
    Page<Moto> findAllOrderByModelo(Pageable pageable);

    // Buscar cliente por ID da moto (native query)
    @Query(value = "SELECT * FROM cliente WHERE id_cliente = (SELECT cliente_id_cliente FROM moto WHERE id_moto = :idMoto)",
            nativeQuery = true)
    Optional<Object> findClienteByMotoId(@Param("idMoto") Long idMoto);

    // Buscar cliente por placa da moto (native query)
    @Query(value = "SELECT * FROM cliente WHERE id_cliente = (SELECT cliente_id_cliente FROM moto WHERE LOWER(placa_moto) = LOWER(:placa))",
            nativeQuery = true)
    Optional<Object> findClienteByPlacaMoto(@Param("placa") String placa);

    // Buscar moto por chassi (chassi é único, ignore case)
    @Query("SELECT m FROM Moto m WHERE LOWER(m.chassiMoto) = LOWER(:chassi)")
    Optional<Moto> findByChassiMotoIgnoreCase(@Param("chassi") String chassi);

    // Buscar cliente por chassi da moto (native query)
    @Query(value = "SELECT * FROM cliente WHERE id_cliente = (SELECT cliente_id_cliente FROM moto WHERE LOWER(chassi_moto) = LOWER(:chassi))",
            nativeQuery = true)
    Optional<Object> findClienteByChassiMoto(@Param("chassi") String chassi);
}

