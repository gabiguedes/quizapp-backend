package br.com.quizapp.quizapp.controllers;

import br.com.quizapp.quizapp.models.Usuario;
import br.com.quizapp.quizapp.models.dto.UsuarioResponseDto;
import br.com.quizapp.quizapp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/find_all", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> list = usuarioService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <Usuario> findById(@PathVariable Long id) {
        Usuario obj = usuarioService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    public ResponseEntity<UsuarioResponseDto> insert(@RequestBody Usuario usuario) {
        usuario.setId(null);
        Usuario obj = usuarioService.insert(usuario);

        UsuarioResponseDto responseDto = new UsuarioResponseDto();
        responseDto.setStatusCode(HttpStatus.CREATED.value());
        responseDto.setStatusMessage("Usuário e nota salvos no banco de dados com sucesso!");

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity <Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
