package br.com.fiap.sprint1.dto;

import br.com.fiap.sprint1.model.Funcionario;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class CargoDTO extends RepresentationModel<CargoDTO> {

        private Long idCargo;
        private String nomeCargo;
        private String descricaoCargo;
        private List<Funcionario> funcionarios;
}
