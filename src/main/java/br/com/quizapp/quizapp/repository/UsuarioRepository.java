package br.com.quizapp.quizapp.repository;

import br.com.quizapp.quizapp.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNome(String nome);
}
