package com.metaenlace.formacion.gestormedico.services;

import com.metaenlace.formacion.gestormedico.dto.CitaDTO;
import com.metaenlace.formacion.gestormedico.mapper.CitaMapper;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Diagnostico;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import com.metaenlace.formacion.gestormedico.exceptions.NotFoundException;
import com.metaenlace.formacion.gestormedico.repositories.CitaRepository;
import com.metaenlace.formacion.gestormedico.repositories.DiagnosticoRepository;
import com.metaenlace.formacion.gestormedico.repositories.MedicoRepository;
import com.metaenlace.formacion.gestormedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CitaService {


    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    public CitaDTO find(Long id) {
        try {
            Optional<Cita> optCita = citaRepository.findById(id);
            if (optCita.isEmpty()) {
                throw new NotFoundException("No se ha encontrado la cita");
            }
            Cita cita = optCita.get();
            CitaDTO citaDTO = CitaMapper.INSTANCE.citaToCitaDTO(cita);

            return citaDTO;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public ArrayList<CitaDTO> findAll() {
        ArrayList<CitaDTO> listCitas = new ArrayList<>();
        Iterable<Cita> optListCitas = citaRepository.findAll();
        for (Cita c : optListCitas) {
            CitaDTO citaDTO = CitaMapper.INSTANCE.citaToCitaDTO(c);
            listCitas.add(citaDTO);
        }
        return listCitas;
    }

    public void crear(CitaDTO citaDTO) {
        try {
            Optional<Medico> optMedico= medicoRepository.findById(citaDTO.getMedicoId());
            if (optMedico.isEmpty()) {
                throw new NotFoundException("No se ha encontrado al medico");
            }

            Optional<Paciente>  optPaciente= pacienteRepository.findById(citaDTO.getPacienteId());
            if (optPaciente.isEmpty()) {
                throw new NotFoundException("No se ha encontrado al paciente");
            }

            Cita cita = CitaMapper.INSTANCE.citaDTOToCita(citaDTO);
            cita.setMedico(optMedico.get());
            cita.setPaciente(optPaciente.get());
            citaRepository.save(cita);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public void modificar(Long id, CitaDTO citaDTO) {
        try {
            Cita cita = CitaMapper.INSTANCE.citaDTOToCita(citaDTO);
            cita.setId(id);

            Optional<Medico> optMedico = medicoRepository.findById(citaDTO.getMedicoId());
            if (optMedico.isEmpty()) {
                throw new NotFoundException("No se ha encontrado al medico");
            }

            Optional<Paciente> optPaciente = pacienteRepository.findById(citaDTO.getPacienteId());
            if (optPaciente.isEmpty()) {
                throw new NotFoundException("No se ha encontrado al paciente");
            }

            Optional<Diagnostico> optDiagnostico = diagnosticoRepository.findById(citaDTO.getDiagnosticoId());
            if(optDiagnostico.isPresent()){
                cita.setDiagnostico(optDiagnostico.get());
            }

            cita.setMedico(optMedico.get());
            cita.setPaciente(optPaciente.get());
            citaRepository.save(cita);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public void borrar(Long id) {
        try {
            Optional<Cita> optCita = citaRepository.findById(id);
            if (optCita.isEmpty()) {
                throw new NotFoundException("No se ha encontrado la cita");
            }
            citaRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
