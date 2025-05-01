package br.com.fiap.sprint1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idGerente")
public class Gerente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gerente")
    private Long idGerente;

    @NotBlank(message = "O nome do gerente não pode estar em branco.")
    @Size(max = 100, message = "O nome do gerente deve ter no máximo 100 caracteres.")
    @Column(name = "nome_gerente", nullable = false, length = 100)
    private String nomeGerente;

    @NotBlank(message = "O telefone do gerente não pode estar em branco.")
    @Size(max = 11, message = "O telefone do gerente deve ter no máximo 11 caracteres.")
    @Column(name = "telefone_gerente", nullable = false, length = 11)
    private String telefoneGerente;

    @NotBlank(message = "O CPF do gerente não pode estar em branco.")
    @Size(max = 11, message = "O CPF do gerente deve ter no máximo 11 caracteres.")
    @Column(name = "cpf_gerente", nullable = false, unique = true, length = 11)
    private String cpfGerente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patio_id_patio", nullable = false, unique = true)
    private Patio patio;

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

    @Override
    public String toString() {
        return "Gerente{" +
                "idGerente=" + idGerente +
                ", nomeGerente='" + nomeGerente + '\'' +
                ", telefoneGerente='" + telefoneGerente + '\'' +
                ", cpfGerente='" + cpfGerente + '\'' +
                ", patio=" + patio +
                '}';
    }
}