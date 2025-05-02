package br.com.fiap.sprint1.dto;

import br.com.fiap.sprint1.model.Cargo;
import br.com.fiap.sprint1.model.Patio;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class FuncionarioDTO extends RepresentationModel<FuncionarioDTO> {

        private Long idFuncionario;
        private String nomeFuncionario;
        private String telefoneFuncionario;
        private Cargo cargo;
        private Patio patio;
}
