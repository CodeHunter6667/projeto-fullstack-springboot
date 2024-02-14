package curso_springboot.curso_jdev.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import curso_springboot.curso_jdev.dto.UsuarioDTO;
import curso_springboot.curso_jdev.services.UsuarioService;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/listatodos")
    public ResponseEntity<List<UsuarioDTO>> listaUsuarios(){
        List<UsuarioDTO> usuarios = service.getAll();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<UsuarioDTO> cadastraUsuario(@RequestBody UsuarioDTO dto){
        dto = service.novoUsuario(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/atualiza/{id}")
    public ResponseEntity<UsuarioDTO> atualiza(@PathVariable Long id, @RequestBody UsuarioDTO dto){
        service.atualizaUsuario(id, dto);
        return ResponseEntity.ok(dto);
    }
}
