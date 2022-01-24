package br.com.chc.maquinaapi.controllers.dto;

import br.com.chc.maquinaapi.dados.modelo.Usuario;

public class UsuarioLoginDTO {

    private String userName;
    private String token;

    public UsuarioLoginDTO(Usuario usuario, String token)
    {
        this.userName = usuario.getUsername();
        this.token = token;
    }
}
