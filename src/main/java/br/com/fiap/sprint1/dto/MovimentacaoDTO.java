package br.com.fiap.sprint1.dto;

import br.com.fiap.sprint1.model.Moto;
import br.com.fiap.sprint1.model.Vaga;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class MovimentacaoDto extends RepresentationModel<MovimentacaoDto> {

        private Long idMovimentacao;
        private LocalDate dtEntrada;
        private LocalDate dtSaida;
        private String descricaoMovimentacao;
        private Moto moto;
        private Vaga vaga;

        // Getters e Setters

        public Long getIdMovimentacao() {
                return idMovimentacao;
        }

        public void setIdMovimentacao(Long idMovimentacao) {
                this.idMovimentacao = idMovimentacao;
        }

        public LocalDate getDtEntrada() {
                return dtEntrada;
        }

        public void setDtEntrada(LocalDate dtEntrada) {
                this.dtEntrada = dtEntrada;
        }

        public LocalDate getDtSaida() {
                return dtSaida;
        }

        public void setDtSaida(LocalDate dtSaida) {
                this.dtSaida = dtSaida;
        }

        public String getDescricaoMovimentacao() {
                return descricaoMovimentacao;
        }

        public void setDescricaoMovimentacao(String descricaoMovimentacao) {
                this.descricaoMovimentacao = descricaoMovimentacao;
        }

        public Moto getMoto() {
                return moto;
        }

        public void setMoto(Moto moto) {
                this.moto = moto;
        }

        public Vaga getVaga() {
                return vaga;
        }

        public void setVaga(Vaga vaga) {
                this.vaga = vaga;
        }
}
