package br.com.chc.maquinaapi.controllers.dto;


import br.com.chc.maquinaapi.dados.modelo.Cultura;
import br.com.chc.maquinaapi.dados.modelo.Dado;
import br.com.chc.maquinaapi.dados.modelo.LocalAmostra;

import java.time.LocalDate;
import java.time.LocalTime;

public class DadoAmostraDTO {
    private String id;
    private LocalAmostra local;
    private Cultura cultura;
    private Double massaEspecifica;
    private Double temperatura;
    private Double umidade;
    private LocalDate dataAvaliacao;
    private Double massa;
    private LocalTime time;
    public DadoAmostraDTO(Dado dado) {
        this.id = dado.getId();
        this.local = dado.getLocal();
        this.cultura = dado.getCultura();
        this.massaEspecifica = dado.getMassaEspecifica();
        this.temperatura = dado.getTemperatura();
        this.umidade = dado.getUmidade();
        this.dataAvaliacao = dado.getDataAvaliacao();
        this.massa = dado.getMassa();
        this.time = dado.getTime();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalAmostra getLocal() {
        return local;
    }

    public void setLocal(LocalAmostra local) {
        this.local = local;
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
