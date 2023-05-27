package br.com.quizapp.quizapp.services;

import br.com.quizapp.quizapp.models.Usuario;
import br.com.quizapp.quizapp.repositorys.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    UsuarioService usuarioService;

    @Test
    public void when_TestFindAll() {
        Usuario user = new Usuario();
        user.setNota(new ArrayList<>());
        user.setNome("Luffy");
        user.setId(7L);

        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(user);

        when(usuarioRepository.findAll()).thenReturn(usuarioList);

        List<Usuario> response = usuarioService.findAll();

        assertNotNull(response);
        assertEquals(user.getNota(), response.get(0).getNota());
        assertEquals(user.getNome(), response.get(0).getNome());
        assertEquals(user.getId(), response.get(0).getId());
    }

    @Test
    public void when_TestFindById() {
        Long id = 49L;
        Usuario user = new Usuario();
        user.setNota(new ArrayList<>());
        user.setNome("Luffy");
        user.setId(7L);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<Usuario> response = Optional.ofNullable(usuarioService.findById(id));

        assertNotNull(response);
    }

    @Test()
    public void when_TestFindByIdThrowException() {
        Long id = 1L;
        Optional<Usuario> optionalUsuario = Optional.empty();

        when(usuarioRepository.findById(id)).thenReturn(optionalUsuario);

        assertThrows(NullPointerException.class, () -> {
            usuarioService.findById(id);
        });

        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    public void when_TestInsert() {
        Usuario user = new Usuario();
        user.setNota(new ArrayList<>());
        user.setNome("Nami");
        user.setId(27L);

        when(usuarioRepository.save(user)).thenReturn(user);

        Usuario response = usuarioService.insert(user);

        assertNotNull(response);
        assertEquals(user.getNota(), response.getNota());
        assertEquals(user.getNome(), response.getNome());
        assertEquals(user.getId(), response.getId());
    }

    @Test
    public void when_TestDelete() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        usuarioService.delete(id);

        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    public void when_TestOrdenaDecrescente() {
        List<Integer> expect = new ArrayList<>();
        expect.add(10);
        expect.add(8);
        expect.add(5);
        expect.add(4);
        expect.add(3);
        expect.add(2);
        expect.add(1);
        expect.add(0);

        usuarioService.ordenaListaDecrescente(expect);
    }

}