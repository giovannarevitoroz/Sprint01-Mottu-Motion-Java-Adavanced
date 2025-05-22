package br.com.fiap.sprint1.controller;

import br.com.fiap.sprint1.dto.MovimentacaoDTO;
import br.com.fiap.sprint1.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public ResponseEntity<Page<MovimentacaoDTO>> listarTodos(@PageableDefault(size = 5) Pageable pageable) {
        Page<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarTodas(pageable);
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoDTO> buscarPorId(@PathVariable Long id) {
        MovimentacaoDTO movimentacao = movimentacaoService.buscarPorId(id);
        return ResponseEntity.ok(movimentacao);
    }

    @GetMapping("/por-entrada")
    public ResponseEntity<Page<MovimentacaoDTO>> buscarPorDataEntrada(
            @RequestParam LocalDate dtEntrada,
            @PageableDefault(size = 5) Pageable pageable) {
        Page<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarPorDtEntrada(dtEntrada, pageable);
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/por-saida")
    public ResponseEntity<Page<MovimentacaoDTO>> buscarPorDataSaida(
            @RequestParam LocalDate dtSaida,
            @PageableDefault(size = 5) Pageable pageable) {
        Page<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarPorDtSaida(dtSaida, pageable);
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/por-moto")
    public ResponseEntity<Page<MovimentacaoDTO>> buscarPorMoto(
            @RequestParam Long idMoto,
            @PageableDefault(size = 5) Pageable pageable) {
        Page<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarPorMoto(idMoto, pageable);
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/por-vaga")
    public ResponseEntity<Page<MovimentacaoDTO>> buscarPorVaga(
            @RequestParam Long idVaga,
            @PageableDefault(size = 5) Pageable pageable) {
        Page<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarPorVaga(idVaga, pageable);
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/por-descricao")
    public ResponseEntity<Page<MovimentacaoDTO>> buscarPorDescricao(
            @RequestParam String descricao,
            @PageableDefault(size = 5) Pageable pageable) {
        Page<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarPorDescricao(descricao, pageable);
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/por-periodo")
    public ResponseEntity<List<MovimentacaoDTO>> buscarPorPeriodo(
            @RequestParam LocalDate dtInicio,
            @RequestParam LocalDate dtFim) {
        List<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarPorDtEntradaESaida(dtInicio, dtFim);
        return ResponseEntity.ok(movimentacoes);
    }
}