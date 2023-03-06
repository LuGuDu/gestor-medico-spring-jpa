package com.metaenlace.formacion.gestormedico.exceptions;

public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public NotFoundException(String errorMessage){
        super(errorMessage);
    }
}
