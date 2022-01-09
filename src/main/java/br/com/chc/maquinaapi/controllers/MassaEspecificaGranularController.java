package br.com.chc.maquinaapi.controllers;

import br.com.chc.maquinaapi.controllers.dto.ResponseDTO;
import br.com.chc.maquinaapi.controllers.forms.MassaEspecificaGranularBody;
import br.com.chc.maquinaapi.dados.modelo.MassaEspecificaGranular;
import br.com.chc.maquinaapi.dados.repositorios.CulturaRepositorio;
import br.com.chc.maquinaapi.dados.repositorios.MassaEspecificaGranularRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/massas-especificas-granulares")
public class MassaEspecificaGranularController {
    private final MassaEspecificaGranularRepositorio massaEspecificaGranularRepositorio;
    private final CulturaRepositorio culturaRepositorio;
    public MassaEspecificaGranularController(MassaEspecificaGranularRepositorio massaEspecificaGranularRepositorio, CulturaRepositorio culturaRepositorio) {
        this.massaEspecificaGranularRepositorio = massaEspecificaGranularRepositorio;
        this.culturaRepositorio = culturaRepositorio;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<MassaEspecificaGranular>> cadastra(@RequestBody MassaEspecificaGranularBody data){
        var optimalCultura = culturaRepositorio.findById(data.getCulturaId());
        if(optimalCultura.isEmpty()) return ResponseEntity.notFound().build();
        var cultura = optimalCultura.get();
        var massaEspecificaGranular = new MassaEspecificaGranular();
        massaEspecificaGranular.setMassaEspecifica(data.getMassaEspecifica());
        massaEspecificaGranular.setCulturas(cultura);
        massaEspecificaGranular.setUmidade(data.getUmidade());
        massaEspecificaGranularRepositorio.save(massaEspecificaGranular);
        return ResponseEntity.ok(new ResponseDTO<>("Salvo com sucesso!", massaEspecificaGranular));
    }

    @GetMapping
    public ResponseEntity<Page<MassaEspecificaGranular>> obterTodos(@PageableDefault(sort = "nome",direction = Sort.Direction.ASC) Pageable paginacao){
        return ResponseEntity.ok(massaEspecificaGranularRepositorio.findAll(paginacao));
    }
    @GetMapping("/{id}")
    public ResponseEntity<MassaEspecificaGranular> obter(@PathVariable String id){
        var massaEspecificaGranularOptional = massaEspecificaGranularRepositorio.findById(id);
        if(massaEspecificaGranularOptional.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(massaEspecificaGranularOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<MassaEspecificaGranular>> atualizar(@PathVariable String id,@RequestBody MassaEspecificaGranularBody data){
        var optimalCultura = culturaRepositorio.findById(data.getCulturaId());
        if(optimalCultura.isEmpty()) return ResponseEntity.notFound().build();
        var massaEspecificaGranularOptional = massaEspecificaGranularRepositorio.findByCultura_IdAndAndMassaEspecificaAndAndUmidade(
                data.getCulturaId(),data.getMassaEspecifica(),data.getUmidade());
        if(massaEspecificaGranularOptional.isEmpty()) return ResponseEntity.notFound().build();
        var massaEspecificaGranular = massaEspecificaGranularOptional.get();
        massaEspecificaGranular.setMassaEspecifica(data.getMassaEspecifica());
        massaEspecificaGranular.setCulturas(optimalCultura.get());
        massaEspecificaGranular.setUmidade(data.getUmidade());
        massaEspecificaGranularRepositorio.save(massaEspecificaGranular);
        return ResponseEntity.ok(new ResponseDTO<>("Salvo com sucesso!", massaEspecificaGranular));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<MassaEspecificaGranular>> deletar(@PathVariable String id){
        var massaEspecificaGranularOptional = massaEspecificaGranularRepositorio.findById(id);
        if(massaEspecificaGranularOptional.isEmpty()) return ResponseEntity.notFound().build();
        massaEspecificaGranularRepositorio.delete(massaEspecificaGranularOptional.get());
        return ResponseEntity.ok(new ResponseDTO<>("Deletado com sucesso!", null));
    }

}
