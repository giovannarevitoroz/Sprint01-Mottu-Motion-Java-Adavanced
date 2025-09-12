package br.com.fiap.sprint1.view;

import br.com.fiap.sprint1.dto.GerenteDTO;
import br.com.fiap.sprint1.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gerentes")
public class GerenteViewController {

    @Autowired
    private GerenteService gerenteService;

    // Tela principal de gerentes
    @GetMapping
    public String telaGerentes(Model model,
                               @PageableDefault(size = 10) Pageable pageable,
                               @RequestParam(required = false) String editadoId) {

        Long id = null;
        try {
            if (editadoId != null && !editadoId.equals("null")) {
                id = Long.valueOf(editadoId);
            }
        } catch (NumberFormatException ignored) {}

        model.addAttribute("gerentes", gerenteService.listarTodos());
        model.addAttribute("gerente", new GerenteDTO());
        model.addAttribute("editadoId", id);

        return "gerentes"; // procura gerentes.html em templates
    }

    // Carregar formulário com dados do gerente para edição
    @GetMapping("/editar/{id}")
    public String editarGerente(@PathVariable Long id, Model model) {
        GerenteDTO gerente = gerenteService.buscarPorId(id);
        model.addAttribute("gerente", gerente);
        model.addAttribute("gerentes", gerenteService.listarTodos());
        model.addAttribute("editadoId", id);

        return "gerentes";
    }

    // Deletar gerente
    @GetMapping("/deletar/{id}")
    public String deletarGerente(@PathVariable Long id) {
        gerenteService.deletarGerente(id);
        return "redirect:/gerentes";
    }

    // Cadastrar ou atualizar gerente
    @PostMapping
    public String salvarGerente(@ModelAttribute GerenteDTO gerenteDTO) {
        if (gerenteDTO.getIdGerente() != null) {
            gerenteService.atualizar(gerenteDTO.getIdGerente(), gerenteDTO);
        } else {
            gerenteService.salvar(gerenteDTO);
        }
        return "redirect:/gerentes";
    }
}
