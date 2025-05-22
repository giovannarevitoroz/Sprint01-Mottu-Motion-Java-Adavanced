package br.com.fiap.sprint1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idCliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @NotNull(message = "O nome do cliente não pode ser nulo.")
    @Size(max = 100, message = "O nome do cliente deve ter no máximo 100 caracteres.")
    @Column(name = "nome_cliente", length = 100, nullable = false)
    private String nomeCliente;

    @NotNull(message = "O telefone do cliente não pode ser nulo.")
    @Size(max = 11, message = "O telefone do cliente deve ter no máximo 11 caracteres.")
    @Column(name = "telefone_cliente", length = 11, nullable = false)
    private String telefoneCliente;

    @NotNull(message = "O CPF do cliente não pode ser nulo.")
    @Size(max = 11, message = "O CPF do cliente deve ter no máximo 11 caracteres.")
    @Column(name = "cpf_cliente", length = 11, nullable = false, unique = true)
    private String cpfCliente;

    @Email(message = "O e-mail do cliente deve ser válido.")
    @NotNull(message = "O e-mail do cliente não pode ser nulo.")
    @Size(max = 100, message = "O e-mail do cliente deve ter no máximo 100 caracteres.")
    @Column(name = "email_cliente", length = 100, nullable = false)
    private String emailCliente;

    @NotNull(message = "O sexo do cliente não pode ser nulo.")
    @Size(max = 1, message = "O sexo do cliente deve conter apenas 1 caractere.")
    @Column(name = "sexo_cliente", length = 1, nullable = false)
    private String sexoCliente;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Moto> motos;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getSexoCliente() {
        return sexoCliente;
    }

    public void setSexoCliente(String sexoCliente) {
        this.sexoCliente = sexoCliente;
    }

    public List<Moto> getMotos() {
        return motos;
    }

    public void setMotos(List<Moto> motos) {
        this.motos = motos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", telefoneCliente='" + telefoneCliente + '\'' +
                ", cpfCliente='" + cpfCliente + '\'' +
                ", emailCliente='" + emailCliente + '\'' +
                ", sexoCliente='" + sexoCliente + '\'' +
                ", motos=" + motos +
                '}';
    }
}
