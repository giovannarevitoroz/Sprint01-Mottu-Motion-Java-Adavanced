package br.com.fiap.sprint1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatioDTO extends RepresentationModel<PatioDTO> {

        private Long idPatio;
        private String localizacaoPatio;
        private String nomePatio;
        private String descricaoPatio;
}
