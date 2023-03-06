package com.metaenlace.formacion.gestormedico.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CitaDTO {

    private long id;
    private Date fechaHora;
    private String motivoCita;
    private long pacienteId;
    private long medicoId;
    private long diagnosticoId;
}
