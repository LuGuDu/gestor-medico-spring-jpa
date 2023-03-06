package com.metaenlace.formacion.gestormedico.mapper;

import com.metaenlace.formacion.gestormedico.dto.PacienteDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import com.metaenlace.formacion.gestormedico.repositories.CitaRepository;
import com.metaenlace.formacion.gestormedico.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class PacienteMapperImpl implements PacienteMapper{

    @Autowired
    private MedicoRepository medicoRepo;
    @Autowired
    private CitaRepository citaRepo;

    @Override
    public PacienteDTO pacienteToPacienteDTO(Paciente paciente){
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

        ArrayList<Long> citasId = new ArrayList<>();
        ArrayList<Long> medicosId = new ArrayList<>();

        for (Cita c : paciente.getCitas()) {
            citasId.add(c.getId());
        }
        for (Medico m : paciente.getMedicos()) {
            medicosId.add(m.getId());
        }

        pacienteDTO.setMedicosId(medicosId);
        pacienteDTO.setCitasId(citasId);

        return pacienteDTO;
    }

    @Override
    public Paciente pacienteDTOToPaciente(PacienteDTO pacienteDTO){

        if ( pacienteDTO == null ) {
            return null;
        }

        Paciente paciente = new Paciente();

        paciente.setId( pacienteDTO.getId() );
        paciente.setNombre( pacienteDTO.getNombre() );
        paciente.setApellidos( pacienteDTO.getApellidos() );
        paciente.setUsuario( pacienteDTO.getUsuario() );
        paciente.setClave( pacienteDTO.getClave() );
        paciente.setNSS( pacienteDTO.getNSS() );
        paciente.setNumTarjeta( pacienteDTO.getNumTarjeta() );
        paciente.setTelefono( pacienteDTO.getTelefono() );
        paciente.setDireccion( pacienteDTO.getDireccion() );

        ArrayList<Cita> citas = new ArrayList<>();
        ArrayList<Medico> medicos = new ArrayList<>();

        if(!pacienteDTO.getMedicosId().isEmpty()){
            for (Long id : pacienteDTO.getMedicosId()) {
                Optional<Medico> optMedico= medicoRepo.findById(id);
                if(optMedico.isPresent()){
                    medicos.add(optMedico.get());
                }
            }
        }
        if(!pacienteDTO.getCitasId().isEmpty()){
            for (Long id : pacienteDTO.getCitasId()) {
                Optional<Cita> optCita = citaRepo.findById(id);
                if(optCita.isPresent()){
                    citas.add(optCita.get());
                }
            }
        }

        paciente.setCitas(citas);
        paciente.setMedicos(medicos);

        return paciente;
    }
}
