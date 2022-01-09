package br.com.chc.maquinaapi.dados.modelo;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "massa_especifica_granular")
public class MassaEspecificaGranular {
    @Id
    private String id;
    @DBRef
    private Cultura cultura;
    private Double umidade;
    @BsonProperty("massa_especifica")
    private Double massaEspecifica;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cultura getCultura() {
        return cultura;
    }

    public void setCulturas(Cultura cultura) {
        this.cultura = cultura;
    }

    public Double getUmidade() {
        return umidade;
    }

    public void setUmidade(Double umidade) {
        this.umidade = umidade;
    }

    public Double getMassaEspecifica() {
        return massaEspecifica;
    }

    public void setMassaEspecifica(Double massaEspecifica) {
        this.massaEspecifica = massaEspecifica;
    }
}
