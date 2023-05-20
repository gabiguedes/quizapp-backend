package br.com.quizapp.quizapp.controllers;

import br.com.quizapp.quizapp.models.Usuario;
import br.com.quizapp.quizapp.models.dto.UsuarioResponseDto;
import br.com.quizapp.quizapp.services.UsuarioService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_TestFindAll() {
        List<Usuario> usuarios = Arrays.asList(
                new Usuario(),
                new Usuario()
        );

        when(usuarioService.findAll()).thenReturn(usuarios);
        ResponseEntity<List<Usuario>> response = usuarioController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuarios, response.getBody());
    }

    @Test
    public void when_TestFindById() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Gabi");
        usuario.setNota(new ArrayList<>());

        when(usuarioService.findById(18L)).thenReturn(usuario);
        ResponseEntity<Usuario> response = usuarioController.findById(18L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuario.getId(), response.getBody().getId());
    }

    @Test
    public void when_TestInsert() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Usuário Teste");

        Integer expected = 201;
        String expectedMsg = "Usuário e nota salvos no banco de dados com sucesso!";

        when(usuarioService.insert(usuario)).thenReturn(usuario);

        ResponseEntity<UsuarioResponseDto> response = usuarioController.insert(usuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expected.intValue(), response.getStatusCodeValue());

        UsuarioResponseDto responseDto = response.getBody();
        assertEquals(expected, responseDto.getStatusCode());
        assertEquals(expectedMsg, responseDto.getStatusMessage());
    }

    @Test
    public void when_TestDelete() {
        Long id = 50L;

        doNothing().when(usuarioService).delete(id);
        ResponseEntity<Void> response = usuarioController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(204, response.getStatusCodeValue());

        verify(usuarioService).delete(id);
    }

}