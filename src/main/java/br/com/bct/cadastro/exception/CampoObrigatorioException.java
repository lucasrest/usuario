package br.com.bct.cadastro.exception;

public class CampoObrigatorioException extends RuntimeException {

    public CampoObrigatorioException(String message) {
        super(message);
    }
}
