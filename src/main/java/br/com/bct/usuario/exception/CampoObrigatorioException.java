package br.com.bct.usuario.exception;

public class CampoObrigatorioException extends RuntimeException {

    public CampoObrigatorioException(String message) {
        super(message);
    }
}
