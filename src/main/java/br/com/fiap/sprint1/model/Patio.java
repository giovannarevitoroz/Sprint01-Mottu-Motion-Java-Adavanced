package br.com.fiap.sprint1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idPatio")
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patio")
    private Long idPatio;

    @NotBlank(message = "A localização do pátio não pode estar em branco.")
    @Size(max = 100, message = "A localização do pátio deve ter no máximo 100 caracteres.")
    @Column(name = "localizacao_patio", nullable = false, length = 100)
    private String localizacaoPatio;

    @NotBlank(message = "O nome do pátio não pode estar em branco.")
    @Size(max = 100, message = "O nome do pátio deve ter no máximo 100 caracteres.")
    @Column(name = "nome_patio", nullable = false, length = 100)
    private String nomePatio;

    @Column(name = "descricao_patio", length = 255)
    private String descricaoPatio;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    private List<Setor> setores;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    private List<Gerente> gerentes;

    public Long getIdPatio() {
        return idPatio;
    }

    public void setIdPatio(Long idPatio) {
        this.idPatio = idPatio;
    }

    public String getLocalizacaoPatio() {
        return localizacaoPatio;
    }

    public void setLocalizacaoPatio(String localizacaoPatio) {
        this.localizacaoPatio = localizacaoPatio;
    }

    public String getNomePatio() {
        return nomePatio;
    }

    public void setNomePatio(String nomePatio) {
        this.nomePatio = nomePatio;
    }

    public String getDescricaoPatio() {
        return descricaoPatio;
    }

    public void setDescricaoPatio(String descricaoPatio) {
        this.descricaoPatio = descricaoPatio;
    }

    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Gerente> getGerentes() {
        return gerentes;
    }

    public void setGerentes(List<Gerente> gerentes) {
        this.gerentes = gerentes;
    }

    @Override
    public String toString() {
        return "Patio{" +
                "idPatio=" + idPatio +
                ", localizacaoPatio='" + localizacaoPatio + '\'' +
                ", nomePatio='" + nomePatio + '\'' +
                ", descricaoPatio='" + descricaoPatio + '\'' +
                ", setores=" + setores +
                ", funcionarios=" + funcionarios +
                ", gerentes=" + gerentes +
                '}';
    }
}