package br.com.bct.usuario.service;

import br.com.bct.usuario.model.Usuario;
import br.com.bct.usuario.model.dto.RecuperaSenhaDTO;
import br.com.bct.usuario.model.dto.UsuarioDTO;

public interface UsuarioService extends BaseCRUDService<Usuario, UsuarioDTO> {

    Usuario findByUsername(String username);

    Usuario enviarEmailCodigoRecuperacao(String email);

    Usuario resetarSenha(String email, RecuperaSenhaDTO dto);

}