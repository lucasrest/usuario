package br.com.bct.usuario.service.impl;

import br.com.bct.usuario.enums.Status;
import br.com.bct.usuario.exception.CampoObrigatorioException;
import br.com.bct.usuario.exception.EntidadeNaoEncontradaException;
import br.com.bct.usuario.model.EntidadeBase;
import br.com.bct.usuario.model.Teste;
import br.com.bct.usuario.model.dto.TesteDTO;
import br.com.bct.usuario.repository.TesteRepository;
import br.com.bct.usuario.service.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class TesteServiceImpl extends BaseServiceImpl<Teste, TesteDTO> implements TesteService {

    public static final String ENTIDADE_NAO_ENCONTRADA = "Entidade nao encontrada";

    @Autowired
    private TesteRepository repository;

    @Override
    public Page<Teste> buscarTodos(Pageable pageable) {
        return repository.findAllByStatus(pageable, Status.ATIVO.getValue());
    }

    @Override
    public Teste buscarPorId(Long id) {
        Optional<Teste> distribuidor = repository.findByIdAndStatus(id, Status.ATIVO.getValue());
        if (!distribuidor.isPresent())
            throw new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA);
        return distribuidor.get();
    }

    @Override
    public Teste incluir(Teste teste) {
        return repository.save(teste);
    }

    @Override
    public Teste atualizar(Teste teste) {
        validaCampoObrigatorio(teste);
        Teste testeAtualizado = retornaDistribuidorAtualizado(teste);
        return repository.save(testeAtualizado);
    }

    @Override
    public void excluir(Long id) {
        Teste distribuidor = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA));
        distribuidor.setStatus(Status.DESATIVADO.getValue());
        repository.save(distribuidor);
    }

    private Teste retornaDistribuidorAtualizado(Teste teste) {
        Optional<Teste> testeDB = repository.findById(teste.getId());
        teste.setInclusao(testeDB.map(EntidadeBase::getInclusao).orElse(null));
        modelMapper.map(teste, testeDB.orElseThrow(() -> new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA)));
        return testeDB.orElseThrow(() -> new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA));
    }

    private void validaCampoObrigatorio(Teste teste) {
        if (teste.getId() == null) {
            throw new CampoObrigatorioException("Id da entidade vazio!");
        }
    }
}
