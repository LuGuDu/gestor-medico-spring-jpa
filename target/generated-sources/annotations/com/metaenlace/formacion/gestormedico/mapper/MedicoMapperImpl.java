package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.MedicoDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-08T11:30:47+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class MedicoMapperImpl implements MedicoMapper {

    @Override
    public Medico medicoDTOToMedico(MedicoDTO medicoDTO, ArrayList<Paciente> pacientes, ArrayList<Cita> citas) {
        if ( medicoDTO == null && pacientes == null && citas == null ) {
            return null;
        }

        Medico medico = new Medico();

        if ( medicoDTO != null ) {
            medico.setId( medicoDTO.getId() );
            medico.setNombre( medicoDTO.getNombre() );
            medico.setApellidos( medicoDTO.getApellidos() );
            medico.setUsuario( medicoDTO.getUsuario() );
            medico.setClave( medicoDTO.getClave() );
            medico.setNumColegiado( medicoDTO.getNumColegiado() );
        }
        if ( pacientes != null ) {
            ArrayList<Paciente> arrayList = pacientes;
            if ( arrayList != null ) {
                medico.setPacientes( new ArrayList<Paciente>( arrayList ) );
            }
        }
        if ( citas != null ) {
            ArrayList<Cita> arrayList1 = citas;
            if ( arrayList1 != null ) {
                medico.setCitas( new ArrayList<Cita>( arrayList1 ) );
            }
        }

        return medico;
    }

    @Override
    public MedicoDTO medicoToMedicoDTO(Medico medico) {
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

        return medicoDTO;
    }
}
