package br.com.fiap.sprint1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "idVaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaga")
    private Long idVaga;

    @NotBlank(message = "O número da vaga não pode estar em branco.")
    @Column(name = "numero_vaga", nullable = false, length = 10)
    private String numeroVaga;

    @NotNull(message = "O status da vaga não pode estar em branco. Precisa ser 0 para não ocupada, e 1 para ocupada")
    @Column(name = "status_ocupada", nullable = false, length = 1)
    private Integer statusOcupada;

    @ManyToOne
    @JoinColumn(name = "setor_id_setor", nullable = false)
    private Setor setor;

    // Getters, Setters e toString



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

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "idVaga=" + idVaga +
                ", numeroVaga='" + numeroVaga + '\'' +
                ", statusOcupada='" + statusOcupada + '\'' +
                ", setor=" + setor +
                '}';
    }
}