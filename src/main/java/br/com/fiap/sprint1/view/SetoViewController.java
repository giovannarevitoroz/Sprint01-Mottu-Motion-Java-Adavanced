package br.com.fiap.sprint1.view;

import br.com.fiap.sprint1.dto.PatioDTO;
import br.com.fiap.sprint1.dto.SetorDTO;
import br.com.fiap.sprint1.service.PatioService;
import br.com.fiap.sprint1.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/setores")
public class SetoViewController {

    @Autowired
    private SetorService setorService;

    @Autowired
    private PatioService patioService;

    @GetMapping
    public String listarSetores(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<SetorDTO> setores = setorService.listarTodos(PageRequest.of(page, 5));
        model.addAttribute("setores", setores);
        model.addAttribute("setor", new SetorDTO());
        model.addAttribute("patios", patioService.listarTodos(PageRequest.of(0, 100)).getContent());
        return "setor";
    }

    @GetMapping("/editar/{id}")
    public String editarSetor(@PathVariable Long id, Model model) {
        SetorDTO setor = setorService.findById(id);
        model.addAttribute("setor", setor);
        model.addAttribute("setores", setorService.listarTodos(PageRequest.of(0, 5)));
        model.addAttribute("patios", patioService.listarTodos(PageRequest.of(0, 100)).getContent());
        return "setor";
    }

    @PostMapping
    public String cadastrarSetor(@ModelAttribute SetorDTO setorDTO) {
        if (setorDTO.getIdSetor() != null) {
            setorService.atualizar(setorDTO.getIdSetor(), setorDTO);
        } else {
            setorService.cadastrar(setorDTO);
        }
        return "redirect:/setores";
    }

    @GetMapping("/deletar/{id}")
    public String deletarSetor(@PathVariable Long id) {
        setorService.deletar(id);
        return "redirect:/setores";
    }
}
