package br.com.fiap.sprint1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteDTO(
        Long idCliente,

        @NotBlank(message = "O nome do cliente não pode estar em branco.")
        @Size(max = 100, message = "O nome do cliente deve ter no máximo 100 caracteres.")
        String nomeCliente,

        @NotBlank(message = "O telefone do cliente não pode estar em branco.")
        @Size(max = 11, message = "O telefone do cliente deve ter no máximo 11 caracteres.")
        String telefoneCliente,

        @NotBlank(message = "O CPF do cliente não pode estar em branco.")
        @Size(max = 11, message = "O CPF do cliente deve ter no máximo 11 caracteres.")
        String cpfCliente,

        @Email(message = "O e-mail do cliente deve ser válido.")
        @NotBlank(message = "O e-mail do cliente não pode estar em branco.")
        @Size(max = 100, message = "O e-mail do cliente deve ter no máximo 100 caracteres.")
        String emailCliente,

        @NotBlank(message = "O sexo do cliente não pode estar em branco.")
        @Size(max = 1, message = "O sexo do cliente deve conter apenas 1 caractere.")
        String sexoCliente
) {}