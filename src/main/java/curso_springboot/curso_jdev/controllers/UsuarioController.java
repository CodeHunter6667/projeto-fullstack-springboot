package curso_springboot.curso_jdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
