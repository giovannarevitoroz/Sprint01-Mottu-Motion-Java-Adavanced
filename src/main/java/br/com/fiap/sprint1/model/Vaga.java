package br.com.fiap.sprint1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idVaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaga")
    private Long idVaga;

    @NotBlank(message = "O número da vaga não pode estar em branco.")
    @Column(name = "numero_vaga", nullable = false, length = 10)
    private String numeroVaga;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "O status da vaga não pode estar em branco.")
    @Column(name = "status_vaga", nullable = false, length = 20)
    private String statusVaga;

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

    public String getStatusVaga() {
        return statusVaga;
    }

    public void setStatusVaga(String statusVaga) {
        this.statusVaga = statusVaga;
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
                ", statusVaga='" + statusVaga + '\'' +
                ", setor=" + setor +
                '}';
    }
}