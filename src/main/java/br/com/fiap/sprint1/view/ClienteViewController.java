package br.com.fiap.sprint1.view;

import br.com.fiap.sprint1.dto.ClienteDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteViewController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteViewController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Tela principal de clientes
    @GetMapping
    public String telaClientes(Model model,
                               @PageableDefault(size = 10) Pageable pageable,
                               @RequestParam(required = false) String editadoId) {

        Long id = null;
        try {
            if (editadoId != null && !editadoId.equals("null")) {
                id = Long.valueOf(editadoId);
            }
        } catch (NumberFormatException ignored) {}

        model.addAttribute("clientes", clienteService.listarTodos(pageable));
        model.addAttribute("cliente", new ClienteDTO());
        model.addAttribute("editadoId", id);

        return "clientes";
    }

    // Carregar formulário com dados do cliente para edição
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model,
                                @PageableDefault(size = 10) Pageable pageable) {

        ClienteDTO cliente = clienteService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", clienteService.listarTodos(pageable));
        model.addAttribute("editadoId", id);

        return "clientes";
    }

    // Deletar cliente
    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        clienteService.deletarCliente(id);
        redirectAttributes.addFlashAttribute("successMessage", "Cliente deletado com sucesso!");
        return "redirect:/clientes";
    }

    // Cadastrar novo cliente
    @PostMapping
    public String cadastrarCliente(@ModelAttribute ClienteDTO clienteDTO,
                                   RedirectAttributes redirectAttributes) {

        try {
            boolean cpfDuplicado = false;
            boolean emailDuplicado = false;

            try {
                ClienteDTO existenteCpf = clienteService.buscarPorCpf(clienteDTO.getCpfCliente());
                if (existenteCpf != null) cpfDuplicado = true;
            } catch (RecursoNaoEncontradoException ignored) {}

            try {
                ClienteDTO existenteEmail = clienteService.buscarPorEmail(clienteDTO.getEmailCliente());
                if (existenteEmail != null) emailDuplicado = true;
            } catch (RecursoNaoEncontradoException ignored) {}

            if (cpfDuplicado || emailDuplicado) {
                String erro = (cpfDuplicado ? "CPF já cadastrado. " : "") +
                        (emailDuplicado ? "Email já cadastrado." : "");
                redirectAttributes.addFlashAttribute("erro", erro);
            } else {
                clienteService.cadastrar(clienteDTO);
                redirectAttributes.addFlashAttribute("successMessage", "Usuário cadastrado com sucesso!");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao cadastrar cliente: " + e.getMessage());
        }

        return "redirect:/clientes";
    }

    // Atualizar cliente existente
    @PostMapping("/{id}")
    public String atualizarCliente(@PathVariable Long id,
                                   @ModelAttribute ClienteDTO clienteDTO,
                                   RedirectAttributes redirectAttributes) {

        try {
            boolean cpfDuplicado = false;
            boolean emailDuplicado = false;

            try {
                ClienteDTO existenteCpf = clienteService.buscarPorCpf(clienteDTO.getCpfCliente());
                if (existenteCpf != null && !existenteCpf.getIdCliente().equals(id)) {
                    cpfDuplicado = true;
                }
            } catch (RecursoNaoEncontradoException ignored) {}

            try {
                ClienteDTO existenteEmail = clienteService.buscarPorEmail(clienteDTO.getEmailCliente());
                if (existenteEmail != null && !existenteEmail.getIdCliente().equals(id)) {
                    emailDuplicado = true;
                }
            } catch (RecursoNaoEncontradoException ignored) {}

            if (cpfDuplicado || emailDuplicado) {
                String erro = (cpfDuplicado ? "CPF já cadastrado em outro cliente. " : "") +
                        (emailDuplicado ? "Email já cadastrado em outro cliente." : "");
                redirectAttributes.addFlashAttribute("erro", erro);
            } else {
                clienteService.atualizarCliente(id, clienteDTO);
                redirectAttributes.addFlashAttribute("successMessage", "Usuário editado com sucesso!");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar cliente: " + e.getMessage());
        }

        return "redirect:/clientes";
    }
}
