package br.com.fiap.sprint1.dto;

import org.springframework.hateoas.RepresentationModel;

public class FuncionarioDTO extends RepresentationModel<FuncionarioDTO> {
    private Long idFuncionario;
    private String nomeFuncionario;
    private String telefoneFuncionario;
    private CargoDTO cargo;
    private PatioDTO patio;


    public Long getIdFuncionario() {
        return idFuncionario;
    }
    
    public FuncionarioDTO() {
    	
    }

    public FuncionarioDTO(Long idFuncionario, String nomeFuncionario, String telefoneFuncionario, CargoDTO cargo, PatioDTO patio) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.telefoneFuncionario = telefoneFuncionario;
        this.cargo = cargo;
        this.patio = patio;
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

    public CargoDTO getCargo() {
        return cargo;
    }

    public void setCargo(CargoDTO cargo) {
        this.cargo = cargo;
    }

    public PatioDTO getPatio() {
        return patio;
    }

    public void setPatio(PatioDTO patio) {
        this.patio = patio;
    }
}
