package com.metaenlace.formacion.gestormedico.exceptions;

public class BadFormatException extends RuntimeException {
    public BadFormatException(String errorMessage) {
        super(errorMessage);
    }
}
