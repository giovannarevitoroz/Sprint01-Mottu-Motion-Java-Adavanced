package br.com.fiap.sprint1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idFuncionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long idFuncionario;

    @NotBlank(message = "O nome do funcionário não pode estar em branco.")
    @Size(max = 100, message = "O nome do funcionário deve ter no máximo 100 caracteres.")
    @Column(name = "nome_funcionario", nullable = false, length = 100)
    private String nomeFuncionario;

    @NotBlank(message = "O telefone do funcionário não pode estar em branco.")
    @Size(max = 11, message = "O telefone do funcionário deve ter no máximo 11 caracteres.")
    @Column(name = "telefone_funcionario", nullable = false, length = 11)
    private String telefoneFuncionario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cargo_id_cargo", nullable = false)
    @JsonIgnoreProperties("funcionarios") // como temos uma lista de funcionarios no cargo, para evitar recursividade infinita na requisição, utilizaremos anotação JsonIgnoreProperties
    private Cargo cargo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patio_id_patio", nullable = false)
    private Patio patio;

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getTelefoneFuncionario() {
        return telefoneFuncionario;
    }

    public void setTelefoneFuncionario(String telefoneFuncionario) {
        this.telefoneFuncionario = telefoneFuncionario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "idFuncionario=" + idFuncionario +
                ", nomeFuncionario='" + nomeFuncionario + '\'' +
                ", telefoneFuncionario='" + telefoneFuncionario + '\'' +
                ", cargo=" + cargo +
                ", patio=" + patio +
                '}';
    }
}