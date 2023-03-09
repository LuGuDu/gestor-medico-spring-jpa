package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.PacienteDTO;
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
public class PacienteMapperImpl implements PacienteMapper {

    @Override
    public Paciente pacienteDTOToPaciente(PacienteDTO pacienteDTO, ArrayList<Medico> medicos, ArrayList<Cita> citas) {
        if ( pacienteDTO == null && medicos == null && citas == null ) {
            return null;
        }

        Paciente paciente = new Paciente();

        if ( pacienteDTO != null ) {
            paciente.setId( pacienteDTO.getId() );
            paciente.setNombre( pacienteDTO.getNombre() );
            paciente.setApellidos( pacienteDTO.getApellidos() );
            paciente.setUsuario( pacienteDTO.getUsuario() );
            paciente.setClave( pacienteDTO.getClave() );
            paciente.setNSS( pacienteDTO.getNSS() );
            paciente.setNumTarjeta( pacienteDTO.getNumTarjeta() );
            paciente.setTelefono( pacienteDTO.getTelefono() );
            paciente.setDireccion( pacienteDTO.getDireccion() );
        }
        if ( medicos != null ) {
            ArrayList<Medico> arrayList = medicos;
            if ( arrayList != null ) {
                paciente.setMedicos( new ArrayList<Medico>( arrayList ) );
            }
        }
        if ( citas != null ) {
            ArrayList<Cita> arrayList1 = citas;
            if ( arrayList1 != null ) {
                paciente.setCitas( new ArrayList<Cita>( arrayList1 ) );
            }
        }

        return paciente;
    }

    @Override
    public PacienteDTO pacienteToPacienteDTO(Paciente paciente) {
        if ( paciente == null ) {
            return null;
        }

        PacienteDTO pacienteDTO = new PacienteDTO();

        pacienteDTO.setId( paciente.getId() );
        pacienteDTO.setNombre( paciente.getNombre() );
        pacienteDTO.setApellidos( paciente.getApellidos() );
        pacienteDTO.setUsuario( paciente.getUsuario() );
        pacienteDTO.setClave( paciente.getClave() );
        pacienteDTO.setNSS( paciente.getNSS() );
        pacienteDTO.setNumTarjeta( paciente.getNumTarjeta() );
        pacienteDTO.setTelefono( paciente.getTelefono() );
        pacienteDTO.setDireccion( paciente.getDireccion() );

        return pacienteDTO;
    }
}
