package br.com.fiap.sprint1.dto;

import br.com.fiap.sprint1.model.Patio;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
public class GerenteDTO extends RepresentationModel<GerenteDTO> {

        private Long idGerente;
        private String nomeGerente;
        private String telefoneGerente;
        private String cpfGerente;
        private PatioDTO patio; // DTO para Patio

        public GerenteDTO() {

        }

        public GerenteDTO(Long idGerente, String nomeGerente, String telefoneGerente, String cpfGerente, Patio patio) {
        }

        // Getters e Setters
        public Long getIdGerente() {
                return idGerente;
        }

        public void setIdGerente(Long idGerente) {
                this.idGerente = idGerente;
        }

        public String getNomeGerente() {
                return nomeGerente;
        }

        public void setNomeGerente(String nomeGerente) {
                this.nomeGerente = nomeGerente;
        }

        public String getTelefoneGerente() {
                return telefoneGerente;
        }

        public void setTelefoneGerente(String telefoneGerente) {
                this.telefoneGerente = telefoneGerente;
        }

        public String getCpfGerente() {
                return cpfGerente;
        }

        public void setCpfGerente(String cpfGerente) {
                this.cpfGerente = cpfGerente;
        }

        public PatioDTO getPatio() {
                return patio;
        }

        public void setPatio(PatioDTO patio) {
                this.patio = patio;
        }
}
