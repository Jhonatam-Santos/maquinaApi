package br.com.chc.maquinaapi.controllers;

import br.com.chc.maquinaapi.controllers.dto.ResponseDTO;
import br.com.chc.maquinaapi.controllers.dto.UsuarioLoginDTO;
import br.com.chc.maquinaapi.controllers.forms.AuthBody;
import br.com.chc.maquinaapi.dados.modelo.Usuario;
import br.com.chc.maquinaapi.providers.JwtAuthProvider;
import br.com.chc.maquinaapi.servicos.CustomDetalhesUsuarioServico;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autenticar")
public class AutenticaoController {

    private final AuthenticationManager authenticationManager;
    private final JwtAuthProvider jwtAuthProvider;
    private final CustomDetalhesUsuarioServico usuarioRepositorio;

    public AutenticaoController(JwtAuthProvider jwtAuthProvider, CustomDetalhesUsuarioServico usuarioRepositorio, AuthenticationManager authenticationManager)
    {
        this.jwtAuthProvider = jwtAuthProvider;
        this.usuarioRepositorio = usuarioRepositorio;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<UsuarioLoginDTO>> login(@RequestBody AuthBody data)
    {
        AuthBody authBody = new AuthBody();
        authBody.setEmail(data.getEmail());
        authBody.setPassword(data.getPassword());

        var usuario = usuarioRepositorio.findUserByEmail(authBody.getEmail());
        if (usuario == null) return ResponseEntity.notFound().build();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authBody.getEmail(), authBody.getPassword()));
        String token = jwtAuthProvider.createToken(usuario.getUsername(), usuario.getRoles());
        return ResponseEntity.ok(new ResponseDTO<>("Login Efetuado com sucesso!", new UsuarioLoginDTO(usuario, token)));
    }

}
