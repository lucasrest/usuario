package br.com.bct.usuario.service.impl;

import br.com.bct.usuario.exception.EntidadeNaoEncontradaException;
import br.com.bct.usuario.model.OauthClientDetails;
import br.com.bct.usuario.model.dto.OauthClientDetailsDTO;
import br.com.bct.usuario.repository.OauthClientDetailsRepository;
import br.com.bct.usuario.service.OauthClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OauthClientServiceImpl extends BaseServiceImpl<OauthClientDetails, OauthClientDetailsDTO>
        implements OauthClientService {

    public static final String CLIENT_NAO_ENCONTRADO = "Client não encontrado";

    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<OauthClientDetails> buscarTodos(Pageable pageable) {
        return oauthClientDetailsRepository.findAll(pageable);
    }

    @Override
    public OauthClientDetails buscarPorId(Long id) {
        return oauthClientDetailsRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(CLIENT_NAO_ENCONTRADO));
    }

    @Override
    public OauthClientDetails incluir(OauthClientDetails oauthClientDetails) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        OauthClientDetails oauthClientDetailsDb = new OauthClientDetails();
        modelMapper.map(oauthClientDetails, oauthClientDetailsDb);
        oauthClientDetailsDb.setClientSecret(passwordEncoder.encode(oauthClientDetails.getSecret()));
        return oauthClientDetailsRepository.save(oauthClientDetailsDb);
    }

    @Override
    public OauthClientDetails atualizar(OauthClientDetails oauthClientDetails) {
        OauthClientDetails oauthClientDetailsDb = oauthClientDetailsRepository
                .findById(oauthClientDetails.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(CLIENT_NAO_ENCONTRADO));

        String senhaEncoded = oauthClientDetailsDb.getClientSecret();
        modelMapper.map(oauthClientDetails, oauthClientDetailsDb);
        oauthClientDetailsDb.setClientSecret(senhaEncoded);

        if (oauthClientDetails.getSecret() != null && !oauthClientDetails.getSecret().isEmpty()) {
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            if (!passwordEncoder.matches(oauthClientDetails.getSecret(), senhaEncoded)) {
                oauthClientDetailsDb.setClientSecret(passwordEncoder.encode(oauthClientDetails.getSecret()));
            }
        }

        oauthClientDetailsRepository.save(oauthClientDetailsDb);

        return oauthClientDetailsDb;
    }

    @Override
    public void excluir(Long id) {
        OauthClientDetails oauthClientDetailsDb = oauthClientDetailsRepository
                .findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(CLIENT_NAO_ENCONTRADO));

        oauthClientDetailsRepository.delete(oauthClientDetailsDb);
    }
}
