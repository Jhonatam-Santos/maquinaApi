package br.com.chc.maquinaapi.controllers.forms;

import javax.validation.constraints.NotEmpty;

public class LocalAmostraBody {
    @NotEmpty(message = "O campo usuarioId é obrigatório")
    private String usuarioId;
    @NotEmpty(message = "O campo propriedade é obrigatório")
    private String propriedade;
    @NotEmpty(message = "O campo tamanho é obrigatório")
    private String tamanho;

    public String getUsuario() {
        return usuarioId;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
}
