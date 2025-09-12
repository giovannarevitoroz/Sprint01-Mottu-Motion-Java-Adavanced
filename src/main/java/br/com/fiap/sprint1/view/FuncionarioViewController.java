package br.com.fiap.sprint1.view;

import br.com.fiap.sprint1.dto.FuncionarioDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioViewController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioViewController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    // Tela principal de funcionários
    @GetMapping
    public String telaFuncionarios(Model model,
                                   @PageableDefault(size = 10) Pageable pageable,
                                   @RequestParam(required = false) String editadoId) {

        Long id = null;
        try {
            if (editadoId != null && !editadoId.equals("null")) {
                id = Long.valueOf(editadoId);
            }
        } catch (NumberFormatException ignored) {}

        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        model.addAttribute("funcionario", new FuncionarioDTO());
        model.addAttribute("editadoId", id);

        return "funcionarios"; // Thymeleaf -> templates/funcionarios.html
    }

    // Carregar formulário para edição
    @GetMapping("/editar/{id}")
    public String editarFuncionario(@PathVariable Long id, Model model,
                                    @PageableDefault(size = 10) Pageable pageable) {

        FuncionarioDTO funcionario = funcionarioService.buscarPorId(id);
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        model.addAttribute("editadoId", id);

        return "funcionarios";
    }

    // Deletar funcionário
    @GetMapping("/deletar/{id}")
    public String deletarFuncionario(@PathVariable Long id,
                                     RedirectAttributes redirectAttributes) {
        funcionarioService.deletarFuncionario(id);
        redirectAttributes.addFlashAttribute("successMessage", "Funcionário deletado com sucesso!");
        return "redirect:/funcionarios";
    }

    // Cadastrar novo funcionário
    @PostMapping
    public String cadastrarFuncionario(@ModelAttribute FuncionarioDTO funcionarioDTO,
                                       RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.salvar(funcionarioDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Funcionário cadastrado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao cadastrar funcionário: " + e.getMessage());
        }
        return "redirect:/funcionarios";
    }

    // Atualizar funcionário existente
    @PostMapping("/{id}")
    public String atualizarFuncionario(@PathVariable Long id,
                                       @ModelAttribute FuncionarioDTO funcionarioDTO,
                                       RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.atualizar(id, funcionarioDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Funcionário atualizado com sucesso!");
        } catch (RecursoNaoEncontradoException e) {
            redirectAttributes.addFlashAttribute("erro", "Funcionário não encontrado.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar funcionário: " + e.getMessage());
        }
        return "redirect:/funcionarios";
    }
}
