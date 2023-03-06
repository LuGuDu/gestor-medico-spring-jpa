package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.DiagnosticoDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Diagnostico;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-06T11:20:37+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class DiagnosticoMapperImpl implements DiagnosticoMapper {

    @Override
    public Diagnostico diagnosticoDTOToDiagnostico(DiagnosticoDTO diagnosticoDTO) {
        if ( diagnosticoDTO == null ) {
            return null;
        }

        Diagnostico diagnostico = new Diagnostico();

        diagnostico.setId( diagnosticoDTO.getId() );
        diagnostico.setValoracionEspecialista( diagnosticoDTO.getValoracionEspecialista() );
        diagnostico.setEnfermedad( diagnosticoDTO.getEnfermedad() );

        return diagnostico;
    }

    @Override
    public DiagnosticoDTO diagnosticoToDiagnosticoDTO(Diagnostico diagnostico) {
        if ( diagnostico == null ) {
            return null;
        }

        DiagnosticoDTO diagnosticoDTO = new DiagnosticoDTO();

        diagnosticoDTO.setCitaId( diagnosticoCitaId( diagnostico ) );
        diagnosticoDTO.setId( diagnostico.getId() );
        diagnosticoDTO.setValoracionEspecialista( diagnostico.getValoracionEspecialista() );
        diagnosticoDTO.setEnfermedad( diagnostico.getEnfermedad() );

        return diagnosticoDTO;
    }

    private long diagnosticoCitaId(Diagnostico diagnostico) {
        if ( diagnostico == null ) {
            return 0L;
        }
        Cita cita = diagnostico.getCita();
        if ( cita == null ) {
            return 0L;
        }
        long id = cita.getId();
        return id;
    }
}
