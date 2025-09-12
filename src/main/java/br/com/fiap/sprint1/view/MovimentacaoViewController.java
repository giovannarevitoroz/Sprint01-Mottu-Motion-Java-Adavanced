package br.com.fiap.sprint1.view;

import br.com.fiap.sprint1.dto.MovimentacaoDTO;
import br.com.fiap.sprint1.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movimentacoes")
public class MovimentacaoViewController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    // Listagem de movimentações
    @GetMapping
    public String listar(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarTodas(pageable);
        model.addAttribute("movimentacoes", movimentacoes);
        model.addAttribute("movimentacao", new MovimentacaoDTO()); // Para formulário
        return "movimentacoes";
    }

    // Editar movimentação
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, @PageableDefault(size = 5) Pageable pageable) {
        MovimentacaoDTO movimentacao = movimentacaoService.buscarPorId(id);
        model.addAttribute("movimentacao", movimentacao);
        model.addAttribute("movimentacoes", movimentacaoService.buscarTodas(pageable));
        return "movimentacoes";
    }

    // Deletar movimentação
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        // Aqui você implementa delete no service/repository
        // movimentacaoService.deletar(id);
        return "redirect:/movimentacoes";
    }

    // Salvar (criar/atualizar)
    @PostMapping
    public String salvar(@ModelAttribute MovimentacaoDTO movimentacaoDTO) {
        // Aqui você implementa save/update no service/repository
        // movimentacaoService.salvar(movimentacaoDTO);
        return "redirect:/movimentacoes";
    }
}
