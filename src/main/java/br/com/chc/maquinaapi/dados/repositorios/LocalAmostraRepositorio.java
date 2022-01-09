package br.com.chc.maquinaapi.dados.repositorios;

import br.com.chc.maquinaapi.dados.modelo.LocalAmostra;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LocalAmostraRepositorio extends MongoRepository<LocalAmostra,String> {
    Optional<LocalAmostra> findLocalAmostraByUsuario_Id(String usuarioId);
}
