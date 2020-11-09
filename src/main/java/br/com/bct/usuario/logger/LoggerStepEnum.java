package br.com.bct.usuario.logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LoggerStepEnum {
    TES00001("[TesteController] - [buscartodos]"),
    TES00002("[TesteController] - [buscaPorId]"),
    TES00003("[TesteController] - [incluir]"),
    TES00004("[TesteController] - [atualizar]"),
    TES00005("[TesteController] - [deletar]"),
    USU00001("[OauthClientController] - [buscartodos]"),
    USU00002("[OauthClientController] - [buscaPorId]"),
    USU00003("[OauthClientController] - [incluir]"),
    USU00004("[OauthClientController] - [atualizar]"),
    USU00005("[OauthClientController] - [deletar]"),
    USU00006("[UsuarioController] - [buscartodos]"),
    USU00007("[UsuarioController] - [buscaPorId]"),
    USU00008("[UsuarioController] - [incluir]"),
    USU00009("[UsuarioController] - [atualizar]"),
    USU00010("[UsuarioController] - [deletar]"),
    USU00011("[UsuarioController] - [recuperarSenha]"),
    USU00012("[UsuarioController] - [resetarSenha]"),


    USE0090("Erro");

    private String message;

    public String getMessage() {
        return message;
    }

}
