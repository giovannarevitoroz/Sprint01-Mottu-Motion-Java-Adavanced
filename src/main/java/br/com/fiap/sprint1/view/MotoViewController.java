package br.com.fiap.sprint1.view;

import br.com.fiap.sprint1.dto.MotoDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/motos")
public class MotoViewController {

    private final MotoService motoService;

    @Autowired
    public MotoViewController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping
    public String listarMotos(Model model,
                              @PageableDefault(size = 10) Pageable pageable,
                              @RequestParam(required = false) String editadoId) {
        Long id = null;
        try {
            if (editadoId != null && !editadoId.equals("null")) {
                id = Long.valueOf(editadoId);
            }
        } catch (NumberFormatException ignored) {}

        model.addAttribute("motos", motoService.listarTodos(pageable)); // ajustar service para retornar Page<MotoDTO>
        model.addAttribute("moto", new MotoDTO());
        model.addAttribute("editadoId", id);
        return "motos";
    }

    @GetMapping("/editar/{id}")
    public String editarMoto(@PathVariable Long id, Model model,
                             @PageableDefault(size = 10) Pageable pageable) {
        try {
            MotoDTO moto = motoService.buscarPorId(id);
            model.addAttribute("moto", moto);
        } catch (RecursoNaoEncontradoException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("moto", new MotoDTO());
        }
        model.addAttribute("motos", motoService.listarTodos(pageable));
        model.addAttribute("editadoId", id);
        return "motos";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMoto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            motoService.deletar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Moto deletada com sucesso!");
        } catch (RecursoNaoEncontradoException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/motos";
    }

    @PostMapping
    public String cadastrarMoto(@ModelAttribute MotoDTO motoDTO, RedirectAttributes redirectAttributes) {
        try {
            motoService.salvar(motoDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Moto cadastrada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao cadastrar moto: " + e.getMessage());
        }
        return "redirect:/motos";
    }

    @PostMapping("/{id}")
    public String atualizarMoto(@PathVariable Long id, @ModelAttribute MotoDTO motoDTO, RedirectAttributes redirectAttributes) {
        try {
            motoService.atualizar(id, motoDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Moto editada com sucesso!");
        } catch (RecursoNaoEncontradoException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar moto: " + e.getMessage());
        }
        return "redirect:/motos";
    }
}
