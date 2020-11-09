package br.com.bct.usuario.controller;


import br.com.bct.usuario.exception.ApiError;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping(path = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ErrorController extends AbstractErrorController {

    public ErrorController(ErrorAttributes errorAttributes,
                           ObjectProvider<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers.orderedStream().collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity error(HttpServletRequest request) {
        return getResponseEntity(request);
    }

    @PostMapping
    public ResponseEntity errorPost(HttpServletRequest request) {
        return getResponseEntity(request);
    }

    @PutMapping
    public ResponseEntity errorPut(HttpServletRequest request) {
        return getResponseEntity(request);
    }

    @DeleteMapping
    public ResponseEntity errorDelete(HttpServletRequest request) {
        return getResponseEntity(request);
    }

    @PatchMapping
    public ResponseEntity errorPatch(HttpServletRequest request) {
        return getResponseEntity(request);
    }

    private ResponseEntity getResponseEntity(HttpServletRequest request) {
        HttpStatus status;
        try {
            status = getStatus(request);
            if (status == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(status);
            } else if (status == NOT_FOUND) {
                return getErrorResponseEntity(status, "Recurso não encontrado");
            } else if (status == INTERNAL_SERVER_ERROR) {
                return getErrorResponseEntity(status, "Erro interno");
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(getErrorAttributes(request, true), status);
    }

    private ResponseEntity getErrorResponseEntity(HttpStatus internalServerError, String s) {
        ApiError apiError = new ApiError(internalServerError);
        apiError.setMessage(s);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}