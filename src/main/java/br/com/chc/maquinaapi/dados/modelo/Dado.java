package br.com.chc.maquinaapi.dados.modelo;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "dados")
public class Dado {
    @Id
    private String id;
    @DBRef
    private LocalAmostra local;
    @DBRef
    private Cultura cultura;
    @BsonProperty("massa_especifica")
    private Double massaEspecifica;
    private Double temperatura;
    private Double umidade;
    @BsonProperty("data_avaliacao")
    private LocalDate dataAvaliacao = LocalDate.now();
    private Double massa;
    private LocalTime time = LocalTime.now();

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
