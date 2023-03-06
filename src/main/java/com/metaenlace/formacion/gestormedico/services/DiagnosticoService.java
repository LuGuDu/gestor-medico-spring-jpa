package com.metaenlace.formacion.gestormedico.services;

import com.metaenlace.formacion.gestormedico.mapper.DiagnosticoMapper;
import com.metaenlace.formacion.gestormedico.dto.DiagnosticoDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Diagnostico;
import com.metaenlace.formacion.gestormedico.exceptions.NotFoundException;
import com.metaenlace.formacion.gestormedico.repositories.CitaRepository;
import com.metaenlace.formacion.gestormedico.repositories.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepo;
    @Autowired
    private CitaRepository citaRepository;

    public DiagnosticoDTO find(Long id){
        try {
            Optional<Diagnostico> optDiag = diagnosticoRepo.findById(id);

            if (optDiag.isEmpty()) {
                throw new NotFoundException("No se ha encontrado el diagnostico");
            }
            Diagnostico diagnostico = optDiag.get();
            DiagnosticoDTO diagnosticoDTO = DiagnosticoMapper.INSTANCE.diagnosticoToDiagnosticoDTO(diagnostico);

            return diagnosticoDTO;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public ArrayList<DiagnosticoDTO> findAll(){
        ArrayList<DiagnosticoDTO> listDiag = new ArrayList<>();
        Iterable<Diagnostico> optListDiag = diagnosticoRepo.findAll();
        for (Diagnostico d : optListDiag) {
            DiagnosticoDTO diagnosticoDTO = DiagnosticoMapper.INSTANCE.diagnosticoToDiagnosticoDTO(d);
            listDiag.add(diagnosticoDTO);
        }
        return listDiag;
    }

    public void crear(DiagnosticoDTO diagDTO){
        try {
            Optional<Cita> optCita = citaRepository.findById(diagDTO.getCitaId());
            if (optCita.isEmpty()) {
                throw new NotFoundException("No se ha encontrado la cita");
            }
            Diagnostico diag = DiagnosticoMapper.INSTANCE.diagnosticoDTOToDiagnostico(diagDTO);
            diag.setCita(optCita.get());
            diagnosticoRepo.save(diag);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public void modificar(DiagnosticoDTO diagDTO){
        try {
            Optional<Cita> optCita = citaRepository.findById(diagDTO.getCitaId());
            if (optCita.isEmpty()) {
                throw new NotFoundException("No se ha encontrado la cita");
            }
            Diagnostico diag = DiagnosticoMapper.INSTANCE.diagnosticoDTOToDiagnostico(diagDTO);
            diag.setCita(optCita.get());
            diagnosticoRepo.save(diag);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public void borrar(Long id){
        try {
            Optional<Diagnostico> optDiag = diagnosticoRepo.findById(id);
            if (optDiag.isEmpty()) {
                throw new NotFoundException("No se ha encontrado el diagnostico");
            }
            diagnosticoRepo.deleteById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

    }
}
