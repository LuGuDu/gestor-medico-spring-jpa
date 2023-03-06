package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.PacienteDTO;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    Paciente pacienteDTOToPaciente(PacienteDTO pacienteDTO);
    PacienteDTO pacienteToPacienteDTO(Paciente paciente);


}
