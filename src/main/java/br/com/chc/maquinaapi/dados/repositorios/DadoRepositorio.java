package br.com.chc.maquinaapi.dados.repositorios;

import br.com.chc.maquinaapi.dados.modelo.Dado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DadoRepositorio extends MongoRepository<Dado,String> {
}
