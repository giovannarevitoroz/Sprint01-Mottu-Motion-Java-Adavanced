package br.com.fiap.sprint1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idSetor")
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_setor")
    private Long idSetor;

    @NotBlank(message = "O tipo do setor não pode estar em branco.")
    @Column(name = "tipo_setor", nullable = false, length = 70)
    private String tipoSetor;
    @NotBlank(message = "O status do setor não pode estar em branco.")
    @Column(name = "status_setor", nullable = false, length = 50)
    private String statusSetor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patio_id_patio", nullable = false)
    private Patio patio;

    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL)
    private List<Vaga> vagas;



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

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }

    @Override
    public String toString() {
        return "Setor{" +
                "idSetor=" + idSetor +
                ", tipoSetor='" + tipoSetor + '\'' +
                ", statusSetor='" + statusSetor + '\'' +
                ", patio=" + patio +
                ", vagas=" + vagas +
                '}';
    }
}