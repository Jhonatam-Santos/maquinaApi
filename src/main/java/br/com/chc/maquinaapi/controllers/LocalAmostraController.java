package br.com.chc.maquinaapi.controllers;


import br.com.chc.maquinaapi.controllers.dto.LocalAmostraDTO;
import br.com.chc.maquinaapi.controllers.dto.ResponseDTO;
import br.com.chc.maquinaapi.controllers.dto.UsuarioDTO;
import br.com.chc.maquinaapi.controllers.forms.LocalAmostraBody;
import br.com.chc.maquinaapi.dados.modelo.LocalAmostra;
import br.com.chc.maquinaapi.dados.repositorios.LocalAmostraRepositorio;
import br.com.chc.maquinaapi.dados.repositorios.UsuarioRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locais-amostras")
public class LocalAmostraController {
    private final LocalAmostraRepositorio localAmostraRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    public LocalAmostraController(LocalAmostraRepositorio localAmostraRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.localAmostraRepositorio = localAmostraRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }


    @PostMapping
    public ResponseEntity<ResponseDTO<LocalAmostraDTO>> cadastra(@RequestBody LocalAmostraBody data){
        var usuarioOptional = usuarioRepositorio.findById(data.getUsuario());
        if(usuarioOptional.isEmpty()) return ResponseEntity.notFound().build();
        var localAmostra = new LocalAmostra();
        localAmostra.setPropriedade(data.getPropriedade());
        localAmostra.setUsuario(usuarioOptional.get());
        localAmostra.setTamanho(data.getTamanho());
        localAmostraRepositorio.save(localAmostra);
        return ResponseEntity.ok(new ResponseDTO<>("Salvo com sucesso!", new LocalAmostraDTO(localAmostra)));
    }

    @GetMapping
    public ResponseEntity<Page<LocalAmostraDTO>> obterTodos(@PageableDefault(sort = "nome",direction = Sort.Direction.ASC) Pageable paginacao){
        return ResponseEntity.ok(localAmostraRepositorio.findAll(paginacao).map(LocalAmostraDTO::new));
    }
    @GetMapping("/{id}")
    public ResponseEntity<LocalAmostraDTO> obter(@PathVariable String id){
        var localAmostraOptimal = localAmostraRepositorio.findById(id);
        if(localAmostraOptimal.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new LocalAmostraDTO(localAmostraOptimal.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<LocalAmostraDTO>> atualizar(@PathVariable String id,@RequestBody LocalAmostraBody data){
        var localAmostraOptional = localAmostraRepositorio.findById(id);
        if(localAmostraOptional.isEmpty()) return ResponseEntity.notFound().build();
        var usuario = usuarioRepositorio.findById(data.getUsuario());
        if(usuario.isEmpty()) return ResponseEntity.notFound().build();
        var localAmostra = localAmostraOptional.get();
        localAmostra.setPropriedade(data.getPropriedade());
        localAmostra.setUsuario(usuario.get());
        localAmostra.setTamanho(data.getTamanho());
        localAmostraRepositorio.save(localAmostra);
        return ResponseEntity.ok(new ResponseDTO<>("Salvo com sucesso!", new LocalAmostraDTO(localAmostra)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<UsuarioDTO>> delete(@PathVariable String id){
        var localAmostraOptional = localAmostraRepositorio.findById(id);
        if(localAmostraOptional.isEmpty()) return ResponseEntity.notFound().build();
        localAmostraRepositorio.delete(localAmostraOptional.get());
        return ResponseEntity.ok(new ResponseDTO<>("Deletado com sucesso!", null));
    }
}
