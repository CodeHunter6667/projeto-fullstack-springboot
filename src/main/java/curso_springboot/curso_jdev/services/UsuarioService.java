package curso_springboot.curso_jdev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import curso_springboot.curso_jdev.dto.UsuarioDTO;
import curso_springboot.curso_jdev.model.Usuario;
import curso_springboot.curso_jdev.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> getAll(){

        List<Usuario> entity = repository.findAll();

        return entity.stream().map(x -> new UsuarioDTO(x)).toList();
    }

    @Transactional
    public UsuarioDTO novoUsuario(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        copyDtoToEntity(dto, usuario);
        repository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    private static void copyDtoToEntity(UsuarioDTO dto, Usuario entity){
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
    }
}
