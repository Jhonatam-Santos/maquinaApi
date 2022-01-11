package br.com.chc.maquinaapi.controllers;

import br.com.chc.maquinaapi.controllers.dto.ResponseDTO;
import br.com.chc.maquinaapi.controllers.dto.UsuarioDTO;
import br.com.chc.maquinaapi.controllers.forms.UsuarioCadastroBody;
import br.com.chc.maquinaapi.dados.modelo.Role;
import br.com.chc.maquinaapi.dados.modelo.Usuario;
import br.com.chc.maquinaapi.dados.repositorios.RoleRepositorio;
import br.com.chc.maquinaapi.dados.repositorios.UsuarioRepositorio;
import br.com.chc.maquinaapi.servicos.CustomDetalhesUsuarioServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final CustomDetalhesUsuarioServico usuarioServico;
    private final UsuarioRepositorio usuarioRepositorio;
    private final RoleRepositorio roleRepositorio;
    public UsuarioController(CustomDetalhesUsuarioServico usuarioServico, UsuarioRepositorio usuarioRepositorio, RoleRepositorio roleRepositorio) {
        this.usuarioServico = usuarioServico;
        this.usuarioRepositorio = usuarioRepositorio;
        this.roleRepositorio = roleRepositorio;
    }

    @PostMapping("nova")
    public ResponseEntity<ResponseDTO<UsuarioDTO>> cadastra(@RequestBody UsuarioCadastroBody data)
    {
        //eu quero veririficar se há um usuário no banco, caso não haja, eu vou criar um novo usuário
        var emailVerify = usuarioServico.findUserByEmail(data.getEmail());
        if (emailVerify != null) return ResponseEntity.noContent().build();
        var optimalRole = roleRepositorio.findById(data.getRoleId());
        if (optimalRole.isEmpty()) return ResponseEntity.notFound().build();

        Usuario usuario = new Usuario();
        usuario.setNome(data.getNome());
        usuario.setEmail(data.getEmail());
        usuario.setTelefone(data.getTelefone());
        usuario.setCpf(data.getCpf());
        usuario.setSenha(data.getSenha());
        Set<Role> roles = new HashSet<>();
        roles.add(optimalRole.get());
        usuario.setRoles(roles);

        usuarioServico.saveUser(usuario,optimalRole.get().getRole());
        return ResponseEntity.ok(new ResponseDTO<>("Login Efetuado com sucesso!", new UsuarioDTO(usuario)));
    }

    @GetMapping("/")
    public Page<UsuarioDTO> obterUsuarios(@PageableDefault(sort = "nome",direction = Sort.Direction.ASC) Pageable paginacao)
    {
        System.out.println("Called");
        var usuarios = usuarioRepositorio.findAll(paginacao);
        return usuarios.map(UsuarioDTO::new);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuario(@PathVariable String id){
       var optimalUsuario = usuarioRepositorio.findById(id);
       if (optimalUsuario.isEmpty()) return ResponseEntity.notFound().build();
       return ResponseEntity.ok(optimalUsuario.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<UsuarioDTO>> atualizarUsuario(@PathVariable String id,@RequestBody UsuarioCadastroBody data)
    {
        var usuarioOptional = usuarioRepositorio.findById(id);
        var optimalRole = roleRepositorio.findById(data.getRoleId());
        if (usuarioOptional.isEmpty()) return ResponseEntity.notFound().build();
        if (optimalRole.isEmpty()) return ResponseEntity.notFound().build();
        var usuario = usuarioOptional.get();
        usuario.setCpf(data.getCpf());
        usuario.setNome(data.getNome());
        usuario.setTelefone(data.getTelefone());
        usuario.setEmail(data.getEmail());
        usuario.setSenha(data.getSenha());
        usuarioServico.saveUser(usuario,optimalRole.get().getRole());
        return ResponseEntity.ok(new ResponseDTO<>("Usuario atualizado com sucesso!", new UsuarioDTO(usuario)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<UsuarioDTO>> atualizarUsuario(@PathVariable String id)
    {
        var usuarioOptional = usuarioRepositorio.findById(id);
        if (usuarioOptional.isEmpty()) return ResponseEntity.notFound().build();
        usuarioRepositorio.delete(usuarioOptional.get());
        return ResponseEntity.ok(new ResponseDTO<>("Usuario excluido com sucesso!", null));
    }
}
