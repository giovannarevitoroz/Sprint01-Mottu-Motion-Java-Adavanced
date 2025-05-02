package br.com.fiap.sprint1.dto;

import br.com.fiap.sprint1.model.Moto;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class ClienteDTO extends RepresentationModel<ClienteDTO> {

        private Long idCliente;
        private String nomeCliente;
        private String telefoneCliente;
        private String cpfCliente;
        private String emailCliente;
        private String sexoCliente;
        private List<Moto> motos;
}
