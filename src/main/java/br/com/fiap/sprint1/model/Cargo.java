package br.com.fiap.sprint1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@EqualsAndHashCode(of = "idCargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCargo;

    @NotBlank(message = "O nome do cargo não deve ser vazio. Tente novamente")
    @Size(min = 1, max = 50, message = "O nome do cargo deve ter entre 1 e 50 caracteres")
    @Column(name = "nome_cargo")
    private String nomeCargo;

    @Column(name = "descricao_cargo")
    private String descricaoCargo;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Funcionario> funcionarios;

    public Cargo(Long idCargo, String nomeCargo, String descricaoCargo, List<Funcionario> funcionarios) {
        this.idCargo = idCargo;
        this.nomeCargo = nomeCargo;
        this.descricaoCargo = descricaoCargo;
        this.funcionarios = funcionarios;
    }

    public Cargo() {

    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public String getDescricaoCargo() {
        return descricaoCargo;
    }

    public void setDescricaoCargo(String descricaoCargo) {
        this.descricaoCargo = descricaoCargo;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id cargo=" + idCargo +
                ", nome cargo='" + nomeCargo + '\'' +
                ", descricao cargo='" + descricaoCargo + '\'' +
                ", funcionarios=" + funcionarios +
                '}';
    }
}
