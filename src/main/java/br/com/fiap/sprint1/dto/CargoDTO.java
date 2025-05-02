package br.com.fiap.sprint1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
public class CargoDTO extends RepresentationModel<CargoDTO> {

        private Long idCargo;

        private String nomeCargo;

        private String descricaoCargo;

        public Long getIdCargo() {
                return idCargo;
        }

        public void setIdCargo(Long idCargo) {
                this.idCargo = idCargo;
        }

        public String getNomeCargo() {
                return nomeCargo;
        }

        public void setNomeCargo(String nomeCargo) {
                this.nomeCargo = nomeCargo;
        }

        public String getDescricaoCargo() {
                return descricaoCargo;
        }

        public void setDescricaoCargo(String descricaoCargo) {
                this.descricaoCargo = descricaoCargo;
        }

        @Override
        public String toString() {
                return "CargoDTO{" +
                        "idCargo=" + idCargo +
                        ", nomeCargo='" + nomeCargo + '\'' +
                        ", descricaoCargo='" + descricaoCargo + '\'' +
                        '}';
        }
}
