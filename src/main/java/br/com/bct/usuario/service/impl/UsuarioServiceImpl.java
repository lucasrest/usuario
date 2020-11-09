package br.com.bct.usuario.service.impl;

import br.com.bct.usuario.config.CustomPasswordEncoder;
import br.com.bct.usuario.enums.Status;
import br.com.bct.usuario.exception.EntidadeNaoEncontradaException;
import br.com.bct.usuario.exception.NegocioException;
import br.com.bct.usuario.model.RecuperarConta;
import br.com.bct.usuario.model.Usuario;
import br.com.bct.usuario.model.constants.EXCEPTION;
import br.com.bct.usuario.model.dto.RecuperaSenhaDTO;
import br.com.bct.usuario.model.dto.UsuarioDTO;
import br.com.bct.usuario.repository.PerfilRepository;
import br.com.bct.usuario.repository.RecuperarContaRepository;
import br.com.bct.usuario.repository.UsuarioRepository;
import br.com.bct.usuario.security.CryptoUtil;
import br.com.bct.usuario.service.UsuarioService;
import br.com.bct.usuario.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.bct.usuario.model.constants.EXCEPTION.USUARIO_NAO_ENCONTRADO;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, UsuarioDTO> implements UsuarioService {


    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private CryptoUtil cryptoUtil;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    private RecuperarContaRepository recuperarContaRepository;

    @Override
    public Page<Usuario> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        if (!usuario.isPresent())
            throw new EntidadeNaoEncontradaException(USUARIO_NAO_ENCONTRADO);
        return usuario.get();
    }

    @Override
    public Usuario incluir(Usuario usuario) {
        repository.findByEmailLikeIgnoreCase(usuario.getEmail()).
                ifPresent(s -> {
                    throw new NegocioException(EXCEPTION.USUARIO_JA_EXISTE);
                });

        usuario.getPerfil().forEach(profile -> perfilRepository.findFirstByNomeLikeIgnoreCase(profile).
                orElseThrow(() ->
                        new EntidadeNaoEncontradaException(EXCEPTION.PERFIL_NAO_ENCONTRADO))
        );
        usuario.setSenha(customPasswordEncoder.encode(cryptoUtil.decrypt(usuario.getSenha())));
        return repository.save(usuario);
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        Usuario usuarioBanco = findByUsername(usuario.getEmail());
        String passDescript = cryptoUtil.decrypt(usuario.getSenha());
        usuarioBanco.setSenha(customPasswordEncoder.encode(passDescript));
        return repository.save(usuarioBanco);
    }

    @Override
    public void excluir(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(USUARIO_NAO_ENCONTRADO));
        usuario.setStatus(Status.DESATIVADO.getValue());
        repository.save(usuario);
    }


    @Override
    public Usuario findByUsername(String username) {
        return repository.findByEmailLikeIgnoreCase(username)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(USUARIO_NAO_ENCONTRADO)
                );
    }

    @Override
    public Usuario enviarEmailCodigoRecuperacao(String email) {
        String codigo = RandomUtils.gerarCodigoRecuperacao();
        Usuario usuario = repository.findByEmailLikeIgnoreCase(email).orElseThrow(() -> new EntidadeNaoEncontradaException(USUARIO_NAO_ENCONTRADO));
        RecuperarConta recuperarConta = RecuperarConta.builder().codigoRecuperacao(codigo).usuario(usuario).expiracao(LocalDateTime.now().plusHours(2)).build();
        recuperarContaRepository.save(recuperarConta);
        //TODO
        //Enviar email com o codigo de recuperação
        return usuario;
    }

    @Override
    public Usuario resetarSenha(String email, RecuperaSenhaDTO dto) {
        Usuario usuario = repository.findByEmailLikeIgnoreCase(email).orElseThrow(() ->
                new EntidadeNaoEncontradaException(USUARIO_NAO_ENCONTRADO));
        RecuperarConta recuperarConta = recuperarContaRepository.
                findByCodigoRecuperacaoAndUsuario_IdEquals(dto.getCodigoRecuperacao(), usuario.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Codigo de recuperação não encontrado"));
        recuperarContaRepository.delete(recuperarConta);
        usuario.setSenha(dto.getSenha());
        atualizar(usuario);
        return usuario;
    }
}
