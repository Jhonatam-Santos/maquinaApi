package br.com.chc.maquinaapi.controllers.forms;

import javax.validation.constraints.NotEmpty;

public class MassaEspecificaGranularBody {
    @NotEmpty(message = "O campo cultura é obrigatório")
    private String culturaId;
    @NotEmpty(message = "O campo umidade é obrigatório")
    private Double umidade;
    @NotEmpty(message="O campo massaEspecifica é obrigatório")
    private Double massaEspecifica;

    public String getCulturaId() {
        return this.culturaId;
    }

    public Double getUmidade() {
        return umidade;
    }

    public Double getMassaEspecifica() {
        return massaEspecifica;
    }
}
