package br.com.bct.cadastro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseCRUDService<E, D> {

    Page<E> buscarTodos(Pageable pageable);

    E buscarPorId(Long id);

    E incluir(E entidade);

    E atualizar(E entidade);

    void excluir(Long id);

    E converterDTOParaEntidade(D dto);

    D converterEntidadeParaDTO(E entidade);

}