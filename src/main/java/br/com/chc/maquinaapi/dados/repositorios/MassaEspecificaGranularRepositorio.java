package br.com.chc.maquinaapi.dados.repositorios;

import br.com.chc.maquinaapi.dados.modelo.MassaEspecificaGranular;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MassaEspecificaGranularRepositorio extends MongoRepository<MassaEspecificaGranular,String> {
    Optional<MassaEspecificaGranular> findByCultura_IdAndAndMassaEspecificaAndAndUmidade(String culturaId, Double massaEspecifica, Double umidade);
}
