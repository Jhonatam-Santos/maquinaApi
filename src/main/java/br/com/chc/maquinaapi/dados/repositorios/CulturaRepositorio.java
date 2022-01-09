package br.com.chc.maquinaapi.dados.repositorios;

import br.com.chc.maquinaapi.dados.modelo.Cultura;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CulturaRepositorio extends MongoRepository<Cultura,String> {
}
