package br.com.fiap.sprint1.view;

import br.com.fiap.sprint1.dto.VagaDTO;
import br.com.fiap.sprint1.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vagas")
public class VagaViewController {

    @Autowired
    private VagaService vagaService;

    // Listar vagas com paginação
    @GetMapping
    public String listarVagas(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {

        Page<VagaDTO> vagas = vagaService.listarTodos(PageRequest.of(page, size));
        model.addAttribute("vagas", vagas);

        // Sempre cria objeto vazio para o form de cadastro
        if (!model.containsAttribute("vaga")) {
            model.addAttribute("vaga", new VagaDTO());
        }

        return "vagas";
    }

    // Carregar vaga para edição
    @GetMapping("/editar/{id}")
    public String editarVaga(@PathVariable Long id, Model model) {

        VagaDTO vaga = vagaService.findById(id);
        model.addAttribute("vaga", vaga);

        // Lista de vagas para a tabela
        Page<VagaDTO> vagas = vagaService.listarTodos(PageRequest.of(0, 10));
        model.addAttribute("vagas", vagas);

        return "vagas";
    }

    // Método para CADASTRAR uma nova vaga
    @PostMapping
    public String cadastrarVaga(@ModelAttribute VagaDTO vaga) {
        vagaService.cadastrar(vaga);
        return "redirect:/vagas";
    }

    // Método para ATUALIZAR uma vaga existente
    @PostMapping("/{id}")
    public String atualizarVaga(@PathVariable Long id, @ModelAttribute VagaDTO vaga) {
        vagaService.atualizar(id, vaga);
        return "redirect:/vagas";
    }

    // Deletar vaga
    @GetMapping("/deletar/{id}")
    public String deletarVaga(@PathVariable Long id) {
        vagaService.deletar(id);
        return "redirect:/vagas";
    }
}