package com.metaenlace.formacion.gestormedico.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PacienteDTO {
    private long id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String clave;
    private List<Long> medicosId;
    private List<Long> citasId;
    private String nSS;
    private String numTarjeta;
    private String telefono;
    private String direccion;
}
