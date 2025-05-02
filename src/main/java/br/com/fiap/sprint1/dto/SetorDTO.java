package br.com.fiap.sprint1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetorDTO extends RepresentationModel<SetorDTO> {

        private Long idSetor;
        private String tipoSetor;
        private Integer totalVagas;
        private Integer vagasOcupadas;
        private String statusSetor;
        private Long idPatio; // Referência simplificada para Patio
}
