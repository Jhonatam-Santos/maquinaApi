package br.com.chc.maquinaapi.controllers;

import br.com.chc.maquinaapi.controllers.dto.DadoAmostraDTO;
import br.com.chc.maquinaapi.controllers.dto.ResponseDTO;
import br.com.chc.maquinaapi.controllers.forms.DadoAmostraBody;
import br.com.chc.maquinaapi.dados.modelo.Dado;
import br.com.chc.maquinaapi.dados.repositorios.DadoRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dados-amostras")
public class DadosAmostraController {
    private final DadoRepositorio dadoRepositorio;

    public DadosAmostraController(DadoRepositorio dadoRepositorio) {
        this.dadoRepositorio = dadoRepositorio;
    }

    @GetMapping
    public ResponseEntity<Page<DadoAmostraDTO>> listar(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        var dados = dadoRepositorio.findAll(pageable).map(DadoAmostraDTO::new);
        return ResponseEntity.ok(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadoAmostraDTO> buscar(@PathVariable String id) {
        Dado dados = dadoRepositorio.findById(id).orElse(null);
        if (dados == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DadoAmostraDTO(dados));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<DadoAmostraDTO>> salvar(@RequestBody DadoAmostraBody body) {
        Dado dados = new Dado();
        dados.setCultura(body.getCultura());
        dados.setDataAvaliacao(body.getDataAvaliacao());
        dados.setLocal(body.getLocal());
        dados.setMassa(body.getMassa());
        dados.setMassaEspecifica(body.getMassaEspecifica());
        dados.setTemperatura(body.getTemperatura());
        dados.setTime(body.getTime());
        dados.setUmidade(body.getUmidade());
        dadoRepositorio.save(dados);
        return ResponseEntity.ok(new ResponseDTO<>("Dados de amostra salvo com sucesso!", new DadoAmostraDTO(dados)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<DadoAmostraDTO>> atualizar(@PathVariable String id, @RequestBody DadoAmostraBody body) {
        Dado dados = dadoRepositorio.findById(id).orElse(null);
        if (dados == null) return ResponseEntity.notFound().build();
        dados.setCultura(body.getCultura());
        dados.setDataAvaliacao(body.getDataAvaliacao());
        dados.setLocal(body.getLocal());
        dados.setMassa(body.getMassa());
        dados.setMassaEspecifica(body.getMassaEspecifica());
        dados.setTemperatura(body.getTemperatura());
        dados.setTime(body.getTime());
        dados.setUmidade(body.getUmidade());
        dadoRepositorio.save(dados);
        return ResponseEntity.ok(new ResponseDTO<>("Dados de amostra salvo com sucesso!", new DadoAmostraDTO(dados)));
    }
}
