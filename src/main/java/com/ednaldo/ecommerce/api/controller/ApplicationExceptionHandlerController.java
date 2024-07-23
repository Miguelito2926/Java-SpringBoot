package com.ednaldo.ecommerce.api.controller;

import com.ednaldo.ecommerce.exception.ApiError;
import com.ednaldo.ecommerce.exception.ObjetoNotFoundException;
import com.ednaldo.ecommerce.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandlerController {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRegraNegocioException(RegraNegocioException e) {
        String mensagemErro = e.getMessage();
        return new ApiError(mensagemErro);
    }
    @ExceptionHandler(ObjetoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(ObjetoNotFoundException e) {
        String mensagemErro = e.getMessage();
        return new ApiError(mensagemErro);
    }
}
