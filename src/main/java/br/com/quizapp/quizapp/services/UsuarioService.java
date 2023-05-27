package br.com.quizapp.quizapp.services;

import br.com.quizapp.quizapp.models.Usuario;
import br.com.quizapp.quizapp.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        Usuario user;

        if (obj.isPresent()) {
            user = obj.get();
            ordenaListaDecrescente(user.getNota());
        }else {
            throw new NullPointerException();
        }

        return user;
    }

    public Usuario insert(Usuario obj) {
        return usuarioRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        usuarioRepository.deleteById(id);
    }

    protected void ordenaListaDecrescente(List<Integer> listaDesordenada) {
        Collections.sort(listaDesordenada, Collections.reverseOrder());
    }
}
