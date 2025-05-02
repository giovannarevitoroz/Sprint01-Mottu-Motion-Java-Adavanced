package br.com.fiap.sprint1.dto;

import br.com.fiap.sprint1.model.Patio;
import org.springframework.hateoas.RepresentationModel;

public class GerenteDTO extends RepresentationModel<GerenteDTO> {

        private Long idGerente;
        private String nomeGerente;
        private String telefoneGerente;
        private String cpfGerente;
        private Patio patio;

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

        public Patio getPatio() {
                return patio;
        }

        public void setPatio(Patio patio) {
                this.patio = patio;
        }
}
