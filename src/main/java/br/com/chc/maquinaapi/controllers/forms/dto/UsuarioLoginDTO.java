package br.com.chc.maquinaapi.controllers.dto;

import br.com.chc.maquinaapi.dados.modelo.Usuario;

public class UsuarioLoginDTO {

    private String username;
    private String token;

    public UsuarioLoginDTO(Usuario usuario, String token)
    {
        this.username = usuario.getNome();
        this.token = token;
    }
}
