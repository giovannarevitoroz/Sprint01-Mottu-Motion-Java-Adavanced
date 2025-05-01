package br.com.fiap.sprint1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SetorDTO(
        Long idSetor,

        @NotBlank(message = "O tipo do setor não pode estar em branco.")
        String tipoSetor,

        @NotNull(message = "O total de vagas não pode ser nulo.")
        Integer totalVagas,

        @NotNull(message = "O número de vagas ocupadas não pode ser nulo.")
        Integer vagasOcupadas,

        @NotBlank(message = "O status do setor não pode estar em branco.")
        String statusSetor,

        @NotNull(message = "O pátio é obrigatório.")
        Long patioId
) {}