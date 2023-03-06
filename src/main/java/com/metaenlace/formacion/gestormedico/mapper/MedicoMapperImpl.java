package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.MedicoDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import com.metaenlace.formacion.gestormedico.repositories.CitaRepository;
import com.metaenlace.formacion.gestormedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class MedicoMapperImpl implements MedicoMapper{

    @Autowired
    private PacienteRepository pacienteRepo;
    @Autowired
    private CitaRepository citaRepo;

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
    public Medico medicoDTOToMedico(MedicoDTO medicoDTO) {
        if ( medicoDTO == null ) {
            return null;
        }
        if (medicoDTO.getCitasId() == null){
            medicoDTO.setCitasId(new ArrayList<>());
        }
        if (medicoDTO.getPacientesId() == null){
            medicoDTO.setPacientesId(new ArrayList<>());
        }

        Medico medico = new Medico();

        medico.setId( medicoDTO.getId() );
        medico.setNombre( medicoDTO.getNombre() );
        medico.setApellidos( medicoDTO.getApellidos() );
        medico.setUsuario( medicoDTO.getUsuario() );
        medico.setClave( medicoDTO.getClave() );
        medico.setNumColegiado( medicoDTO.getNumColegiado() );

        ArrayList<Cita> citas = new ArrayList<>();
        ArrayList<Paciente> pacientes = new ArrayList<>();

        if(!medicoDTO.getPacientesId().isEmpty()){
            for (Long id : medicoDTO.getPacientesId()) {
                Optional<Paciente> optPaciente= pacienteRepo.findById(id);
                if(optPaciente.isPresent()){
                    pacientes.add(optPaciente.get());
                }
            }
        }
        if(!medicoDTO.getCitasId().isEmpty()){
            for (Long id : medicoDTO.getCitasId()) {
                Optional<Cita> optCita = citaRepo.findById(id);
                if(optCita.isPresent()){
                    citas.add(optCita.get());
                }
            }
        }

        medico.setCitas(citas);
        medico.setPacientes(pacientes);

        return medico ;
    }
}

