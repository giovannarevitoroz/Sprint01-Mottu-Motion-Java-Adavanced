package br.com.fiap.sprint1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CargoDTO(
        Long idCargo,

        @NotBlank(message = "O nome do cargo não pode estar em branco.")
        @Size(min = 1, max = 50, message = "O nome do cargo deve ter entre 1 e 50 caracteres.")
        String nomeCargo,

        String descricaoCargo
) {}