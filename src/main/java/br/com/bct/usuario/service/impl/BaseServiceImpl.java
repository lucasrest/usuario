package br.com.bct.usuario.service.impl;

import br.com.bct.usuario.service.BaseCRUDService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseServiceImpl<E, D> implements BaseCRUDService<E, D> {

    @Autowired
    protected ModelMapper modelMapper;

    @Override
    public E converterDTOParaEntidade(D dto) {
        Class<?> classeDoTipoParametrizado = getClasseDoTipoParametrizado(0);
        return modelMapper.map(dto, (Type) classeDoTipoParametrizado);
    }

    @Override
    public D converterEntidadeParaDTO(E entidade) {
        Class<?> classeDoTipoParametrizado = getClasseDoTipoParametrizado(1);
        return modelMapper.map(entidade, (Type) classeDoTipoParametrizado);
    }

    private Class<?> getClasseDoTipoParametrizado(Integer posicao) {
        return (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[posicao];
    }

}
