package com.ednaldo.ecommerce.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    @Getter
    private List<String> error;

    public ApiError(List<String> error) {
        this.error = error;
    }

    public ApiError(String mensagemError) {
        this.error = Arrays.asList(mensagemError);
    }
}
