package br.com.fiap.sprint1.dto;

import br.com.fiap.sprint1.model.Funcionario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class CargoDTO extends RepresentationModel<CargoDTO> {

        private Long idCargo;
        private String nomeCargo;
        private String descricaoCargo;
        @JsonIgnoreProperties("cargo") // Ignora o campo 'cargo' dentro dos funcionários
        private List<Funcionario> funcionarios;

        public CargoDTO(Long idCargo, String nomeCargo, String descricaoCargo, List<Funcionario> funcionarios) {
                this.idCargo = idCargo;
                this.nomeCargo = nomeCargo;
                this.descricaoCargo = descricaoCargo;
                this.funcionarios = funcionarios;
        }

        public CargoDTO() {
        }

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
