package com.metaenlace.formacion.gestormedico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosticoDTO {
    private long id;
    private String valoracionEspecialista;
    private String enfermedad;
    private long citaId;
}
