package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.MedicoDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.entities.Paciente;

import java.util.ArrayList;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class MedicoMapperImpl implements MedicoMapper{

    @Override
    public MedicoDTO medicoToMedicoDTO(Medico medico){

        if ( medico == null ) {
            return null;
        }

        MedicoDTO medicoDTO = new MedicoDTO();

        medicoDTO.setId( medico.getId() );
        medicoDTO.setNombre( medico.getNombre() );
        medicoDTO.setApellidos( medico.getApellidos() );
        medicoDTO.setUsuario( medico.getUsuario() );
        medicoDTO.setClave( medico.getClave() );
        medicoDTO.setNumColegiado( medico.getNumColegiado() );

        ArrayList<Long> citasId = new ArrayList<>();
        ArrayList<Long> pacientesId = new ArrayList<>();

        for (Cita c : medico.getCitas()) {
            citasId.add(c.getId());
        }
        for (Paciente p : medico.getPacientes()) {
            pacientesId.add(p.getId());
        }
        medicoDTO.setPacientesId(pacientesId);
        medicoDTO.setCitasId(citasId);
        return medicoDTO;
    }

    @Override
    public Medico medicoDTOToMedico(MedicoDTO medicoDTO, ArrayList<Paciente> pacientes, ArrayList<Cita> citas) {
        if ( medicoDTO == null ) {
            return null;
        }

        Medico medico = new Medico();

        medico.setId( medicoDTO.getId() );
        medico.setNombre( medicoDTO.getNombre() );
        medico.setApellidos( medicoDTO.getApellidos() );
        medico.setUsuario( medicoDTO.getUsuario() );
        medico.setClave( medicoDTO.getClave() );
        medico.setNumColegiado( medicoDTO.getNumColegiado() );

        medico.setCitas(citas);
        medico.setPacientes(pacientes);

        return medico ;
    }
}
