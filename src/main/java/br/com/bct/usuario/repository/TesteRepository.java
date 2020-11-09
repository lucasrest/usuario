package br.com.bct.usuario.repository;

import br.com.bct.usuario.model.Teste;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TesteRepository extends JpaRepository<Teste, Long> {

    Optional<Teste> findByIdAndStatus(Long id, Integer status);

    Page<Teste> findAllByStatus(Pageable pageable, Integer status);
}
