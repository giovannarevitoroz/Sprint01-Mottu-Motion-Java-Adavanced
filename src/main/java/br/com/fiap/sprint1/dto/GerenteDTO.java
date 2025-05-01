package br.com.fiap.sprint1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GerenteDTO(
        Long idGerente,

        @NotBlank(message = "O nome do gerente não pode estar em branco.")
        @Size(max = 100, message = "O nome do gerente deve ter no máximo 100 caracteres.")
        String nomeGerente,

        @NotBlank(message = "O telefone do gerente não pode estar em branco.")
        @Size(max = 11, message = "O telefone do gerente deve ter no máximo 11 caracteres.")
        String telefoneGerente,

        @NotBlank(message = "O CPF do gerente não pode estar em branco.")
        @Size(max = 11, message = "O CPF do gerente deve ter no máximo 11 caracteres.")
        String cpfGerente,

        Long patioId // FK para Patio (único)
) {}