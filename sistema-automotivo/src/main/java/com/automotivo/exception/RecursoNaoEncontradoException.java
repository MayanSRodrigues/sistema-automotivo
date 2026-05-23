package com.automotivo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando um recurso não é encontrado no banco de dados.
 * A anotação @ResponseStatus faz o Spring retornar HTTP 404 automaticamente.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RecursoNaoEncontradoException(String entidade, Long id) {
        super(entidade + " com ID " + id + " não encontrado(a).");
    }
}
