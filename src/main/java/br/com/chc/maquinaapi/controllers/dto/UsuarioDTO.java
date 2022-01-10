package br.com.chc.maquinaapi.controllers.dto;

import br.com.chc.maquinaapi.dados.modelo.Usuario;

public class UsuarioDTO {
    private String id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private boolean isEnable;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.isEnable = usuario.isEnabled();
    }

}
