package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.CitaDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    CitaMapper INSTANCE = Mappers.getMapper(CitaMapper.class);

    Cita citaDTOToCita(CitaDTO citaDTO);
    @Mapping(target = "diagnosticoId", source = "cita.diagnostico.id")
    @Mapping(target = "medicoId", source = "cita.medico.id")
    @Mapping(target = "pacienteId", source = "cita.paciente.id")
    CitaDTO citaToCitaDTO(Cita cita);
}
