package br.com.fiap.sprint1.dto;

import br.com.fiap.sprint1.model.Moto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO  extends RepresentationModel<ClienteDTO> {

        private Long idCliente;
        private String nomeCliente;
        private String telefoneCliente;
        private String cpfCliente;
        private String emailCliente;
        private String sexoCliente;
        private List<Moto> motos;

        public Long getIdCliente() {
                return idCliente;
        }

        public void setIdCliente(Long idCliente) {
                this.idCliente = idCliente;
        }

        public String getNomeCliente() {
                return nomeCliente;
        }

        public void setNomeCliente(String nomeCliente) {
                this.nomeCliente = nomeCliente;
        }

        public String getTelefoneCliente() {
                return telefoneCliente;
        }

        public void setTelefoneCliente(String telefoneCliente) {
                this.telefoneCliente = telefoneCliente;
        }

        public String getCpfCliente() {
                return cpfCliente;
        }

        public void setCpfCliente(String cpfCliente) {
                this.cpfCliente = cpfCliente;
        }

        public String getEmailCliente() {
                return emailCliente;
        }

        public void setEmailCliente(String emailCliente) {
                this.emailCliente = emailCliente;
        }

        public String getSexoCliente() {
                return sexoCliente;
        }

        public void setSexoCliente(String sexoCliente) {
                this.sexoCliente = sexoCliente;
        }

        public List<Moto> getMotos() {
                return motos;
        }

        public void setMotos(List<Moto> motos) {
                this.motos = motos;
        }
}
