package br.com.fiap.sprint1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
public class VagaDTO extends RepresentationModel<VagaDTO> {

        private Long idVaga;
        private String numeroVaga;
        private Integer statusOcupada;
        private Long idSetor; // Referência simplificada para Setor

        public Long getIdVaga() {
                return idVaga;
        }

        public void setIdVaga(Long idVaga) {
                this.idVaga = idVaga;
        }

        public String getNumeroVaga() {
                return numeroVaga;
        }

        public void setNumeroVaga(String numeroVaga) {
                this.numeroVaga = numeroVaga;
        }

        public Integer getStatusOcupada() {
                return statusOcupada;
        }

        public void setStatusOcupada(Integer statusOcupada) {
                this.statusOcupada = statusOcupada;
        }

        public Long getIdSetor() {
                return idSetor;
        }

        public void setIdSetor(Long idSetor) {
                this.idSetor = idSetor;
        }
}
