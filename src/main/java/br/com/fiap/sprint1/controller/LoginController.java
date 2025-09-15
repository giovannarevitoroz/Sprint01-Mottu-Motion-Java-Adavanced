package br.com.fiap.sprint1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        // Dados de exemplo (substituir por serviços reais)
        model.addAttribute("totalMotos", 10);
        model.addAttribute("vagasDisponiveis", 5);
        model.addAttribute("notificacoes", 2);
        return "home";
    }
}
