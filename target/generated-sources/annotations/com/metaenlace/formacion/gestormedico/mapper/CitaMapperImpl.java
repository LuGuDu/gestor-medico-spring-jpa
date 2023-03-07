package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.CitaDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Diagnostico;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-07T12:53:12+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class CitaMapperImpl implements CitaMapper {

    @Override
    public Cita citaDTOToCita(CitaDTO citaDTO) {
        if ( citaDTO == null ) {
            return null;
        }

        Cita cita = new Cita();

        cita.setId( citaDTO.getId() );
        cita.setFechaHora( citaDTO.getFechaHora() );
        cita.setMotivoCita( citaDTO.getMotivoCita() );

        return cita;
    }

    @Override
    public CitaDTO citaToCitaDTO(Cita cita) {
        if ( cita == null ) {
            return null;
        }

        CitaDTO citaDTO = new CitaDTO();

        citaDTO.setMedicoId( citaMedicoId( cita ) );
        citaDTO.setPacienteId( citaPacienteId( cita ) );
        citaDTO.setDiagnosticoId( citaDiagnosticoId( cita ) );
        citaDTO.setId( cita.getId() );
        citaDTO.setFechaHora( cita.getFechaHora() );
        citaDTO.setMotivoCita( cita.getMotivoCita() );

        return citaDTO;
    }

    private long citaMedicoId(Cita cita) {
        if ( cita == null ) {
            return 0L;
        }
        Medico medico = cita.getMedico();
        if ( medico == null ) {
            return 0L;
        }
        long id = medico.getId();
        return id;
    }

    private long citaPacienteId(Cita cita) {
        if ( cita == null ) {
            return 0L;
        }
        Paciente paciente = cita.getPaciente();
        if ( paciente == null ) {
            return 0L;
        }
        long id = paciente.getId();
        return id;
    }

    private long citaDiagnosticoId(Cita cita) {
        if ( cita == null ) {
            return 0L;
        }
        Diagnostico diagnostico = cita.getDiagnostico();
        if ( diagnostico == null ) {
            return 0L;
        }
        long id = diagnostico.getId();
        return id;
    }
}
