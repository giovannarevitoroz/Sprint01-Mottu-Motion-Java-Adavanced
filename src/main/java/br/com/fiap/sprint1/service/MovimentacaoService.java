package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.MovimentacaoDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.model.Movimentacao;
import br.com.fiap.sprint1.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    // Conversão Entity -> DTO
    private MovimentacaoDTO converterParaDTO(Movimentacao movimentacao) {
        return new MovimentacaoDTO(
                movimentacao.getIdMovimentacao(),
                movimentacao.getDtEntrada(),
                movimentacao.getDtSaida(),
                movimentacao.getDescricaoMovimentacao(),
                movimentacao.getMoto(),
                movimentacao.getVaga()
        );
    }

    // Conversão DTO -> Entity
    private Movimentacao converterParaEntity(MovimentacaoDTO movimentacaoDTO) {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setIdMovimentacao(movimentacaoDTO.getIdMovimentacao());
        movimentacao.setDtEntrada(movimentacaoDTO.getDatatEntrada());
        movimentacao.setDtSaida(movimentacaoDTO.getDtSaida());
        movimentacao.setDescricaoMovimentacao(movimentacaoDTO.getDescricaoMovimentacao());
        movimentacao.setMoto(movimentacaoDTO.getMoto());
        movimentacao.setVaga(movimentacaoDTO.getVaga());
        return movimentacao;
    }

    @Cacheable(value = "movimentacoesAll", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<MovimentacaoDTO> buscarTodas(Pageable pageable) {
        return movimentacaoRepository.findAll(pageable)
                .map(this::converterParaDTO);
    }

    @Cacheable(value = "movimentacoesPorEntrada", key = "#dtEntrada.toString() + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<MovimentacaoDTO> buscarPorDtEntrada(LocalDate dtEntrada, Pageable pageable) {
        return movimentacaoRepository.findByDtEntrada(dtEntrada, pageable)
                .map(this::converterParaDTO);
    }

    @Cacheable(value = "movimentacoesPorSaida", key = "#dtSaida.toString() + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<MovimentacaoDTO> buscarPorDtSaida(LocalDate dtSaida, Pageable pageable) {
        return movimentacaoRepository.findByDtSaida(dtSaida, pageable)
                .map(this::converterParaDTO);
    }

    @Cacheable(value = "movimentacoesPorMoto", key = "#idMoto + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<MovimentacaoDTO> buscarPorMoto(Long idMoto, Pageable pageable) {
        return movimentacaoRepository.findByMotoId(idMoto, pageable)
                .map(this::converterParaDTO);
    }

    @Cacheable(value = "movimentacoesPorVaga", key = "#idVaga + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<MovimentacaoDTO> buscarPorVaga(Long idVaga, Pageable pageable) {
        return movimentacaoRepository.findByVagaId(idVaga, pageable)
                .map(this::converterParaDTO);
    }

    @Cacheable(value = "movimentacoesPorDescricao", key = "#descricao + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<MovimentacaoDTO> buscarPorDescricao(String descricao, Pageable pageable) {
        return movimentacaoRepository.findByDescricaoContainingIgnoreCase(descricao, pageable)
                .map(this::converterParaDTO);
    }

    @Cacheable(value = "movimentacoesPorEntradaSaida", key = "#dtEntrada.toString() + '-' + #dtSaida.toString()")
    public List<MovimentacaoDTO> buscarPorDtEntradaESaida(LocalDate dtEntrada, LocalDate dtSaida) {
        return movimentacaoRepository.findByDtEntradaAndSaida(dtEntrada, dtSaida)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "movimentacaoPorId", key = "#idMovimentacao")
    public MovimentacaoDTO buscarPorId(Long idMovimentacao) {
        Movimentacao movimentacao = movimentacaoRepository.findById(idMovimentacao)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Movimentação não encontrada com o ID: " + idMovimentacao));
        return converterParaDTO(movimentacao);
    }

}
