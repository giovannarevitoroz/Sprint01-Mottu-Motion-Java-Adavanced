package br.com.fiap.sprint1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class SetorDTO extends RepresentationModel<SetorDTO> {

        private Long idSetor;
        private String tipoSetor;
        private String statusSetor;
        private Long idPatio; // Referência simplificada para Patio

        // Construtor sem argumentos (necessário para Spring / Thymeleaf)
        public SetorDTO() {
        }

        // Construtor com todos os campos
        public SetorDTO(Long idSetor, String tipoSetor, String statusSetor, Long idPatio) {
                this.idSetor = idSetor;
                this.tipoSetor = tipoSetor;
                this.statusSetor = statusSetor;
                this.idPatio = idPatio;
        }

        public Long getIdSetor() {
                return idSetor;
        }

        public void setIdSetor(Long idSetor) {
                this.idSetor = idSetor;
        }

        public String getTipoSetor() {
                return tipoSetor;
        }

        public void setTipoSetor(String tipoSetor) {
                this.tipoSetor = tipoSetor;
        }

        public String getStatusSetor() {
                return statusSetor;
        }

        public void setStatusSetor(String statusSetor) {
                this.statusSetor = statusSetor;
        }

        public Long getIdPatio() {
                return idPatio;
        }

        public void setIdPatio(Long idPatio) {
                this.idPatio = idPatio;
        }
}
