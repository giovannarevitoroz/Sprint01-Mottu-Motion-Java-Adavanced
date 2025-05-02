package br.com.fiap.sprint1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VagaDTO extends RepresentationModel<VagaDTO> {

        private Long idVaga;
        private String numeroVaga;
        private String statusVaga;
        private Long idSetor; // Referência simplificada para Setor
}
