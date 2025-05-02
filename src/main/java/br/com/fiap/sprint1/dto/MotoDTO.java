package br.com.fiap.sprint1.dto;

import br.com.fiap.sprint1.model.Cliente;
import org.springframework.hateoas.RepresentationModel;

public class MotoDTO extends RepresentationModel<MotoDTO> {

        private Long idMoto;
        private String modeloMoto;
        private String placaMoto;
        private String situacaoMoto;
        private String chassiMoto;
        private Cliente cliente;

        // Getters e Setters

        public Long getIdMoto() {
                return idMoto;
        }

        public void setIdMoto(Long idMoto) {
                this.idMoto = idMoto;
        }

        public String getModeloMoto() {
                return modeloMoto;
        }

        public void setModeloMoto(String modeloMoto) {
                this.modeloMoto = modeloMoto;
        }

        public String getPlacaMoto() {
                return placaMoto;
        }

        public void setPlacaMoto(String placaMoto) {
                this.placaMoto = placaMoto;
        }

        public String getSituacaoMoto() {
                return situacaoMoto;
        }

        public void setSituacaoMoto(String situacaoMoto) {
                this.situacaoMoto = situacaoMoto;
        }

        public String getChassiMoto() {
                return chassiMoto;
        }

        public void setChassiMoto(String chassiMoto) {
                this.chassiMoto = chassiMoto;
        }

        public Cliente getCliente() {
                return cliente;
        }

        public void setCliente(Cliente cliente) {
                this.cliente = cliente;
        }
}
