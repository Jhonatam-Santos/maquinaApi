package br.com.chc.maquinaapi.controllers;

import br.com.chc.maquinaapi.controllers.dto.ResponseDTO;
import br.com.chc.maquinaapi.controllers.forms.RoleBody;
import br.com.chc.maquinaapi.dados.modelo.Role;
import br.com.chc.maquinaapi.dados.repositorios.RoleRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleRepositorio roleRepositorio;
    public RoleController(RoleRepositorio roleRepositorio) {
        this.roleRepositorio = roleRepositorio;
    }

    @PostMapping("/nova")
    public ResponseEntity<ResponseDTO<Role>> cadastra(@RequestBody RoleBody data){
        System.out.println("entrou " + data);
        var role = roleRepositorio.findByRole(data.getName()).orElse(new Role());
        role.setRole(data.getName());
        roleRepositorio.save(role);
        return ResponseEntity.ok(new ResponseDTO<>("Role cadastrada com sucesso!", role));
    }

    @GetMapping("/")
    public Page<Role> obterRoles(@PageableDefault(sort = "id",direction = Sort.Direction.ASC,page = 0,size = 10) Pageable paginacao){
        return roleRepositorio.findAll(paginacao);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Role> obterUsuario(@PathVariable String id){
        var optimalRole = roleRepositorio.findById(id);
        if (optimalRole.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optimalRole.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<Role>> atualizarUsuario(@PathVariable String id,@RequestBody RoleBody data){
        var role = roleRepositorio.findByRole(data.getName()).orElse(new Role());
        role.setRole(data.getName());
        roleRepositorio.save(role);
        return ResponseEntity.ok(new ResponseDTO<>("Role atualizada com sucesso!", role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Role>> remove(@PathVariable String id){
        var role = roleRepositorio.findById(id).orElse(null);
        if (role == null) return ResponseEntity.notFound().build();
        roleRepositorio.deleteById(id);
        return ResponseEntity.ok(new ResponseDTO<>("Role excluida com sucesso!", null));
    }
}
