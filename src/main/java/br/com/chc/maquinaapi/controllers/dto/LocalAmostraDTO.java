package br.com.chc.maquinaapi.controllers.dto;


import br.com.chc.maquinaapi.dados.modelo.LocalAmostra;

public class LocalAmostraDTO {
    private String Id;
    private String usuario;
    private String propriedade;
    private String tamanho;
    public LocalAmostraDTO(LocalAmostra localAmostra) {
        this.Id = localAmostra.getId();
        this.usuario = localAmostra.getUsuario().getId();
        this.propriedade = localAmostra.getPropriedade();
        this.tamanho = localAmostra.getTamanho();
    }

    public String getId() {
        return Id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public String getTamanho() {
        return tamanho;
    }
}
