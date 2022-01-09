package br.com.chc.maquinaapi.dados.repositorios;

import br.com.chc.maquinaapi.dados.modelo.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends MongoRepository<Usuario,String> {
    Optional<Usuario> findByEmail(String email);
}
