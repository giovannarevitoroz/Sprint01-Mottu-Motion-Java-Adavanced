package br.com.fiap.sprint1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record MovimentacaoDTO(
        Long idMovimentacao,

        @NotNull(message = "A data de entrada não pode ser nula.")
        LocalDate dtEntrada,

        LocalDate dtSaida,

        @Size(max = 255, message = "A descrição da movimentação deve ter no máximo 255 caracteres.")
        String descricaoMovimentacao,

        @NotNull(message = "A moto é obrigatória.")
        Long motoId,

        @NotNull(message = "A vaga é obrigatória.")
        Long vagaId
) {}