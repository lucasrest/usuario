package br.com.bct.cadastro.exception.handler;


import br.com.bct.cadastro.exception.ApiError;
import br.com.bct.cadastro.exception.EntidadeNaoEncontradaException;
import br.com.bct.cadastro.logger.LoggerStepEnum;
import br.com.bct.cadastro.logger.LoggerUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " Parâmetro não informado";
        return getObjectResponseEntity(HttpStatus.BAD_REQUEST, error);
    }


    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" tipo de midia não suportado. Os tipos suportados são ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return getObjectResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return getObjectResponseEntity(BAD_REQUEST, "Erro de Validação");
    }


    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        return getObjectResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    protected ResponseEntity<Object> handleEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException ex) {
        return getObjectResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<Object> getObjectResponseEntity(HttpStatus status, String message) {
        ApiError apiError = new ApiError(status, message);
        logErro(apiError);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "requisição com JSON Malformado";
        return getObjectResponseEntity(HttpStatus.BAD_REQUEST, error);
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Erro ao escrever saíde do JSON";
        return getObjectResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, error);
    }

    /**
     * Handle NoHandlerFoundException.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getObjectResponseEntity(HttpStatus.BAD_REQUEST, String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return getObjectResponseEntity(HttpStatus.CONFLICT, "Erro na Base de Dados");
        }
        return getObjectResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        return getObjectResponseEntity(HttpStatus.BAD_REQUEST, String.format("O parâmetro '%s' com o valor '%s' não pode ser convertido para o tipo '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
    }


    private ApiError logErro(ApiError apiError) {
        LoggerUtil.logger(LoggerStepEnum.USE0090, apiError);
        return apiError;
    }


}

