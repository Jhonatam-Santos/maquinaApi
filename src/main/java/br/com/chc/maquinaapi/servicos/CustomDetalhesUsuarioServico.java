package br.com.chc.maquinaapi.servicos;

import br.com.chc.maquinaapi.dados.modelo.Role;
import br.com.chc.maquinaapi.dados.modelo.Usuario;
import br.com.chc.maquinaapi.dados.repositorios.RoleRepositorio;
import br.com.chc.maquinaapi.dados.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

//TODO:Caso queira mudar para usar o cpf para o login ao invés do email faça Mude no repositorio para ser findByCPF e passe o cpf como parâmetro
@Service
public class CustomDetalhesUsuarioServico implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private RoleRepositorio roleRepositorio;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = findUserByEmail(username);
        if(usuario == null){
            throw  new UsernameNotFoundException("Os dados de login foram inseridos incorretamente!");
        }
        var authorities = getUserAuthority(usuario.getRoles());
        return usuarioAutenticadoBuilder(usuario,authorities);
    }
    private List<GrantedAuthority> getUserAuthority(Set<Role> usuarioRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        usuarioRoles.forEach(role->roles.add(new SimpleGrantedAuthority(role.getRole())));
        return new ArrayList<>(roles);
    }

    private UserDetails usuarioAutenticadoBuilder(Usuario usuario, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(usuario.getUsername(),usuario.getPassword(),authorities);
    }
    public Usuario findUserByEmail(String username){
         var optional = usuarioRepositorio.findByEmail(username);
         if(optional.isEmpty()) return null;
         return optional.get();
    }

    public void saveUser(Usuario usuario,String roleName){
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getPassword()));
        usuario.setEnable(true);
        Optional<Role> optimalRole = roleRepositorio.findByRole(roleName);
        if(optimalRole.isEmpty()) return;
        usuario.setRoles(new HashSet<>(List.of(optimalRole.get())));
        usuarioRepositorio.save(usuario);
    }
}