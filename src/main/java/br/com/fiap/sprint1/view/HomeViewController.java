package br.com.fiap.sprint1.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping("/")
    public String home(Model model) {
        // valores de exemplo, futuramente podem vir do banco de dados
        model.addAttribute("totalMotos", 150);
        model.addAttribute("vagasDisponiveis", 45);
        model.addAttribute("notificacoes", 3);

        return "home"; // procura em templates/home.html
    }
}

