package br.com.chc.maquinaapi.dados.repositorios;

import br.com.chc.maquinaapi.dados.modelo.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepositorio extends MongoRepository<Role,String> {
    Optional<Role> findByRole(String roleName);
}
