package com.metaenlace.formacion.gestormedico.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedicoDTO {

    private long id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String clave;
    private String numColegiado;
    private List<Long> pacientesId;
    private List<Long> citasId;
}
