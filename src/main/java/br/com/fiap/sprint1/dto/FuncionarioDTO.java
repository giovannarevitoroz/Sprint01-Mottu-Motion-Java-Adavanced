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

        public Long getIdFuncionario() {
                return idFuncionario;
        }

        public void setIdFuncionario(Long idFuncionario) {
                this.idFuncionario = idFuncionario;
        }

        public String getNomeFuncionario() {
                return nomeFuncionario;
        }

        public void setNomeFuncionario(String nomeFuncionario) {
                this.nomeFuncionario = nomeFuncionario;
        }

        public String getTelefoneFuncionario() {
                return telefoneFuncionario;
        }

        public void setTelefoneFuncionario(String telefoneFuncionario) {
                this.telefoneFuncionario = telefoneFuncionario;
        }

        public Cargo getCargo() {
                return cargo;
        }

        public void setCargo(Cargo cargo) {
                this.cargo = cargo;
        }

        public Patio getPatio() {
                return patio;
        }

        public void setPatio(Patio patio) {
                this.patio = patio;
        }
}
