package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.MedicoDTO;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);

    Medico medicoDTOToMedico(MedicoDTO medicoDTO);
    MedicoDTO medicoToMedicoDTO(Medico medico);

}
