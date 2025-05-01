package br.com.fiap.sprint1.dto;

import br.com.fiap.sprint1.model.StatusVaga;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VagaDTO(
        Long idVaga,

        @NotBlank(message = "O número da vaga não pode estar em branco.")
        String numeroVaga,

        @NotNull(message = "O status da vaga não pode ser nulo.")
        StatusVaga statusVaga,

        @NotNull(message = "O setor é obrigatório.")
        Long setorId
) {}