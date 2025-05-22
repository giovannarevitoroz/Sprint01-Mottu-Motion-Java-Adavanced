package br.com.fiap.sprint1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idMovimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")
    private Long idMovimentacao;

    @NotNull(message = "A data de entrada não pode ser nula.")
    @Column(name = "dt_entrada", nullable = false)
    private LocalDate dtEntrada;

    @Column(name = "dt_saida")
    private LocalDate dtSaida;

    @Size(max = 255, message = "A descrição da movimentação deve ter no máximo 255 caracteres.")
    @Column(name = "descricao_movimentacao", length = 255)
    private String descricaoMovimentacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "moto_id_moto", nullable = false)
    private Moto moto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vaga_id_vaga", nullable = false)
    private Vaga vaga;

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

    @Override
    public String toString() {
        return "Movimentacao{" +
                "idMovimentacao=" + idMovimentacao +
                ", dtEntrada=" + dtEntrada +
                ", dtSaida=" + dtSaida +
                ", descricaoMovimentacao='" + descricaoMovimentacao + '\'' +
                ", moto=" + moto +
                ", vaga=" + vaga +
                '}';
    }
}