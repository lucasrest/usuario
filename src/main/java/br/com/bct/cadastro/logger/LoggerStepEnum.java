package br.com.bct.cadastro.logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LoggerStepEnum {
    TES00001("[TesteController] - [buscartodos]"),
    TES00002("[TesteController] - [buscaPorId]"),
    TES00003("[TesteController] - [incluir]"),
    TES00004("[TesteController] - [atualizar]"),
    TES00005("[TesteController] - [deletar]"),

    USE0090("Erro");

    private String message;

    public String getMessage() {
        return message;
    }

}
