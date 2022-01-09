package br.com.chc.maquinaapi.dados.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cultura")
public class Cultura {
    @Id
    private String id;
    private String descricao;
    private String cultivar;
}
