package br.com.fiap.sprint1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PatioDTO(
        Long idPatio,

        @NotBlank(message = "A localização do pátio não pode estar em branco.")
        @Size(max = 100, message = "A localização do pátio deve ter no máximo 100 caracteres.")
        String localizacaoPatio,

        @NotBlank(message = "O nome do pátio não pode estar em branco.")
        @Size(max = 100, message = "O nome do pátio deve ter no máximo 100 caracteres.")
        String nomePatio,

        String descricaoPatio
) {}