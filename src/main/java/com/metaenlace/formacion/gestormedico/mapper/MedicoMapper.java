package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.MedicoDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);

    Medico medicoDTOToMedico(MedicoDTO medicoDTO, ArrayList<Paciente> pacientes, ArrayList<Cita> citas);
    MedicoDTO medicoToMedicoDTO(Medico medico);

}
