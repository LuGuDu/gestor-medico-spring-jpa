package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.DiagnosticoDTO;
import com.metaenlace.formacion.gestormedico.entities.Diagnostico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DiagnosticoMapper {

    DiagnosticoMapper INSTANCE = Mappers.getMapper(DiagnosticoMapper.class);

    Diagnostico diagnosticoDTOToDiagnostico (DiagnosticoDTO diagnosticoDTO);
    @Mapping(target = "citaId", source = "diagnostico.cita.id")

    DiagnosticoDTO diagnosticoToDiagnosticoDTO (Diagnostico diagnostico);

}
