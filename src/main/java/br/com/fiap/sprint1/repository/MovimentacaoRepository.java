package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.model.Movimentacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    // Buscar todas as movimentações (paginado)
    @Override
    Page<Movimentacao> findAll(Pageable pageable);

    // Buscar movimentações por data de entrada (paginado)
    @Query("SELECT m FROM Movimentacao m WHERE m.dtEntrada = :dtEntrada")
    Page<Movimentacao> findByDtEntrada(LocalDate dtEntrada, Pageable pageable);

    // Buscar movimentações por data de saída (paginado)
    @Query("SELECT m FROM Movimentacao m WHERE m.dtSaida = :dtSaida")
    Page<Movimentacao> findByDtSaida(LocalDate dtSaida, Pageable pageable);

    // Buscar movimentações por moto (paginado)
    @Query("SELECT m FROM Movimentacao m WHERE m.moto.idMoto = :idMoto")
    Page<Movimentacao> findByMotoId(Long idMoto, Pageable pageable);

    // Buscar movimentações por vaga (paginado)
    @Query("SELECT m FROM Movimentacao m WHERE m.vaga.idVaga = :idVaga")
    Page<Movimentacao> findByVagaId(Long idVaga, Pageable pageable);

    // Buscar movimentações com descrição (paginado)
    @Query("SELECT m FROM Movimentacao m WHERE LOWER(m.descricaoMovimentacao) LIKE LOWER(CONCAT('%', :descricao, '%'))")
    Page<Movimentacao> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);

    // Consultar movimentações por data de entrada e saída (native query)
    @Query(value = "SELECT * FROM movimentacao WHERE dt_entrada = :dtEntrada AND dt_saida = :dtSaida", nativeQuery = true)
    List<Movimentacao> findByDtEntradaAndSaida(LocalDate dtEntrada, LocalDate dtSaida);
}
