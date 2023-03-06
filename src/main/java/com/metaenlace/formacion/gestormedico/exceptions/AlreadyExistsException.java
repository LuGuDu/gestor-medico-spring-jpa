package com.metaenlace.formacion.gestormedico.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
