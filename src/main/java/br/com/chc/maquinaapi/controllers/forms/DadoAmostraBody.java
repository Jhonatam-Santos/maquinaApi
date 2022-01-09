package br.com.chc.maquinaapi.controllers.forms;


import br.com.chc.maquinaapi.dados.modelo.Cultura;
import br.com.chc.maquinaapi.dados.modelo.LocalAmostra;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;

public class DadoAmostraBody {
    @NotEmpty(message = "O campo local é obrigatório")
    private LocalAmostra local;
    @NotEmpty(message = "O campo usuario é obrigatório")
    private String usuario;
    @NotEmpty(message = "O campo cultura é obrigatório")
    private Cultura cultura;
    @NotEmpty(message = "O campo massa especifica é obrigatório")
    private Double massaEspecifica;

    @NotEmpty(message = "O campo temperatura é obrigatório")
    private Double temperatura;

    @NotEmpty(message = "O campo umidade é obrigatório")
    private Double umidade;
    private LocalDate dataAvaliacao = LocalDate.now();

    @NotEmpty(message = "O campo massa é obrigatório")
    private Double massa;
    private LocalTime time = LocalTime.now();

    public LocalAmostra getLocal() {
        return local;
    }

    public void setLocal(LocalAmostra local) {
        this.local = local;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Cultura getCultura() {
        return cultura;
    }

    public void setCultura(Cultura cultura) {
        this.cultura = cultura;
    }

    public Double getMassaEspecifica() {
        return massaEspecifica;
    }

    public void setMassaEspecifica(Double massaEspecifica) {
        this.massaEspecifica = massaEspecifica;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getUmidade() {
        return umidade;
    }

    public void setUmidade(Double umidade) {
        this.umidade = umidade;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Double getMassa() {
        return massa;
    }

    public void setMassa(Double massa) {
        this.massa = massa;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
