package br.com.fiap.sprint1.view;

import br.com.fiap.sprint1.dto.CargoDTO;
import br.com.fiap.sprint1.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cargos")
public class CargoViewController {

    private final CargoService cargoService;

    @Autowired
    public CargoViewController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    // Listar cargos
    @GetMapping
    public String listarCargos(Model model, @PageableDefault(size = 10, sort = "nomeCargo") Pageable pageable) {
        Page<CargoDTO> cargos = cargoService.listarTodos(pageable);
        model.addAttribute("cargos", cargos);
        model.addAttribute("cargo", new CargoDTO()); // para o formulário
        return "cargo";
    }

    // Salvar novo cargo
    @PostMapping
    public String salvarCargo(@ModelAttribute CargoDTO cargoDTO, Model model) {
        cargoService.cadastrar(cargoDTO);
        model.addAttribute("successMessage", "Cargo cadastrado com sucesso!");
        return "redirect:/cargos";
    }

    // Editar cargo
    @GetMapping("/editar/{id}")
    public String editarCargo(@PathVariable Long id, Model model, @PageableDefault(size = 10) Pageable pageable) {
        CargoDTO cargo = cargoService.buscarPorId(id);
        Page<CargoDTO> cargos = cargoService.listarTodos(pageable);
        model.addAttribute("cargo", cargo);
        model.addAttribute("cargos", cargos);
        return "cargo";
    }

    // Atualizar cargo
    @PostMapping("/{id}")
    public String atualizarCargo(@PathVariable Long id, @ModelAttribute CargoDTO cargoDTO, Model model) {
        cargoService.atualizarCargo(id, cargoDTO);
        model.addAttribute("successMessage", "Cargo atualizado com sucesso!");
        return "redirect:/cargos";
    }

    // Deletar cargo
    @GetMapping("/deletar/{id}")
    public String deletarCargo(@PathVariable Long id, Model model) {
        cargoService.deletarCargo(id);
        model.addAttribute("successMessage", "Cargo deletado com sucesso!");
        return "redirect:/cargos";
    }
}

