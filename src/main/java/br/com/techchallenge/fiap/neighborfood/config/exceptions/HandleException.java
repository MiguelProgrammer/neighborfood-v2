/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.config.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleException extends RuntimeException {

    @ExceptionHandler(UsuarioException.class)
    public Error usuarioException(Exception exception) {
        return new Error(exception.getMessage(), exception.getCause());
    }
}
