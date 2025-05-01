package br.com.fiap.sprint1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idMoto")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moto")
    private Long idMoto;

    @NotNull(message = "O modelo da moto não pode ser nulo.")
    @Size(max = 50, message = "O modelo da moto deve ter no máximo 50 caracteres.")
    @Column(name = "modelo_moto", length = 50, nullable = false)
    private String modeloMoto;

    @NotNull(message = "A placa da moto não pode ser nula.")
    @Size(max = 7, message = "A placa da moto deve ter no máximo 7 caracteres.")
    @Column(name = "placa_moto", length = 7, nullable = false, unique = true)
    private String placaMoto;

    @NotNull(message = "A situação da moto não pode ser nula.")
    @Size(max = 50, message = "A situação da moto deve ter no máximo 50 caracteres.")
    @Column(name = "situacao_moto", length = 50, nullable = false)
    private String situacaoMoto;

    @NotBlank(message = "O chassi da moto não pode estar em branco.")
    @Size(max = 17, message = "O chassi da moto deve ter no máximo 17 caracteres.")
    @Column(name = "chassi_moto", nullable = false, unique = true, length = 17)
    private String chassiMoto;

    @ManyToOne
    @JoinColumn(name = "cliente_id_cliente", nullable = false)
    private Cliente cliente;

    public Long getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(Long idMoto) {
        this.idMoto = idMoto;
    }

    public String getModeloMoto() {
        return modeloMoto;
    }

    public void setModeloMoto(String modeloMoto) {
        this.modeloMoto = modeloMoto;
    }

    public String getPlacaMoto() {
        return placaMoto;
    }

    public void setPlacaMoto(String placaMoto) {
        this.placaMoto = placaMoto;
    }

    public String getSituacaoMoto() {
        return situacaoMoto;
    }

    public void setSituacaoMoto(String situacaoMoto) {
        this.situacaoMoto = situacaoMoto;
    }

    public String getChassiMoto() {
        return chassiMoto;
    }

    public void setChassiMoto(String chassiMoto) {
        this.chassiMoto = chassiMoto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "idMoto=" + idMoto +
                ", modeloMoto='" + modeloMoto + '\'' +
                ", placaMoto='" + placaMoto + '\'' +
                ", situacaoMoto='" + situacaoMoto + '\'' +
                ", chassiMoto='" + chassiMoto + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}