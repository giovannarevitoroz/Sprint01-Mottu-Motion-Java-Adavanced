package br.com.fiap.sprint1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MotoDTO(
        Long idMoto,

        @NotBlank(message = "A placa da moto não pode estar em branco.")
        @Size(max = 7, message = "A placa da moto deve ter no máximo 7 caracteres.")
        String placaMoto,

        @NotBlank(message = "O modelo da moto não pode estar em branco.")
        @Size(max = 50, message = "O modelo da moto deve ter no máximo 50 caracteres.")
        String modeloMoto,

        @NotBlank(message = "A situação da moto não pode estar em branco.")
        String situacaoMoto,

        @NotBlank(message = "O chassi da moto não pode estar em branco.")
        @Size(max = 17, message = "O chassi da moto deve ter no máximo 17 caracteres.")
        String chassiMoto,

        Long clienteId // FK para Cliente
) {}