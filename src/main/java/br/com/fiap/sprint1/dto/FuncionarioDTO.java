package br.com.fiap.sprint1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FuncionarioDTO(
        Long idFuncionario,

        @NotBlank(message = "O nome do funcionário não pode estar em branco.")
        @Size(max = 100, message = "O nome do funcionário deve ter no máximo 100 caracteres.")
        String nomeFuncionario,

        @NotBlank(message = "O telefone do funcionário não pode estar em branco.")
        @Size(max = 11, message = "O telefone do funcionário deve ter no máximo 11 caracteres.")
        String telefoneFuncionario,

        Long cargoId, // FK para Cargo
        Long patioId  // FK para Patio
) {}