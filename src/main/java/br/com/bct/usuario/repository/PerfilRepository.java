package br.com.bct.usuario.repository;

import br.com.bct.usuario.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<Perfil> findFirstByNomeLikeIgnoreCase(String nome);

}
