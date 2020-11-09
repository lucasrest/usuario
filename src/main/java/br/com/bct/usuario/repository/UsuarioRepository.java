package br.com.bct.usuario.repository;

import br.com.bct.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailLikeIgnoreCase(String email);

    Optional<Usuario> findByEmailAndIdIsNot(String email, Long id);

}
