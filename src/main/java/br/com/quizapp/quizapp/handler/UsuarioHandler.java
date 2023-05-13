package br.com.quizapp.quizapp.handler;

import br.com.quizapp.quizapp.models.Usuario;
import br.com.quizapp.quizapp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/usuarios")
public class UsuarioHandler {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> list = usuarioService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <Usuario> findById(@PathVariable Long id) {
        Usuario obj = usuarioService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> insert(@RequestBody Usuario usuario) {

        usuario.setId(null);
        Usuario obj = usuarioService.insert(usuario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return new ResponseEntity<String>("Usuario e nota salvos com sucesso!", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity <Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
