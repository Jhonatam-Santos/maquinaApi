package br.com.chc.maquinaapi.controllers.forms;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UsuarioCadastroBody {

    @NotEmpty(message = "O CPF não pode ser vazio")
    @CPF(message = "O CPF em questão não é válido")
    private String cpf;

    @NotEmpty(message = "O nome não pode ficar em branco")
    private String nome;

    @NotEmpty(message = "O número de telefone não pode ficar em branco.")
    private String telefone;
    @Email(message = "O endereço de e-mail é inválido")
    private String email;
    @NotEmpty(message = "A senha não pode estar em branco!")
    @Length(min = 6,message = "A senha não pode ser menor que 6 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$",message = "A senha não atende aos requisitos")
    private String senha;
    private String roleId;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
