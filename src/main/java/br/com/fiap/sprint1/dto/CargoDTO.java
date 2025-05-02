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

        public List<Funcionario> getFuncionarios() {
                return funcionarios;
        }

        public void setFuncionarios(List<Funcionario> funcionarios) {
                this.funcionarios = funcionarios;
        }
}
