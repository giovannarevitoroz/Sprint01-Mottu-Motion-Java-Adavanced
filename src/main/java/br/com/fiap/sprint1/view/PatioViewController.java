package br.com.fiap.sprint1.view;

import br.com.fiap.sprint1.dto.PatioDTO;
import br.com.fiap.sprint1.service.PatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patios")
public class PatioViewController {

    @Autowired
    private PatioService patioService;

    // Listar todos com paginação
    @GetMapping
    public String listarTodos(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              Model model) {
        Page<PatioDTO> patiosPage = patioService.listarTodos(PageRequest.of(page, size));
        model.addAttribute("patios", patiosPage);
        model.addAttribute("patio", new PatioDTO()); // ⚠️ Isso resolve o erro
        return "patio";
    }

    // Formulário para novo Pátio
    @GetMapping("/novo")
    public String novoPatio(Model model) {
        model.addAttribute("patio", new PatioDTO());
        return "patio-form";
    }

    // Salvar novo Pátio
    @PostMapping
    public String salvarPatio(@ModelAttribute PatioDTO patioDTO) {
        patioService.salvar(patioDTO);
        return "redirect:/patios";
    }

    // Formulário de edição
    @GetMapping("/editar/{id}")
    public String editarPatio(@PathVariable Long id, Model model) {
        // Buscar Pátio pelo ID usando serviço
        PatioDTO patioDTO = patioService.listarTodos(PageRequest.of(0, Integer.MAX_VALUE)) // pegar todos
                .stream()
                .filter(p -> p.getIdPatio().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado"));
        model.addAttribute("patio", patioDTO);
        return "patio-form";
    }

    // Atualizar Pátio
    @PostMapping("/atualizar/{id}")
    public String atualizarPatio(@PathVariable Long id, @ModelAttribute PatioDTO patioDTO) {
        patioService.atualizar(id, patioDTO);
        return "redirect:/patios";
    }

    // Deletar Pátio
    @GetMapping("/deletar/{id}")
    public String deletarPatio(@PathVariable Long id) {
        patioService.deletar(id);
        return "redirect:/patios";
    }
}
