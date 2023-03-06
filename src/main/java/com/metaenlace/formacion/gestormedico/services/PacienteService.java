package com.metaenlace.formacion.gestormedico.services;

import com.metaenlace.formacion.gestormedico.dto.PacienteDTO;
import com.metaenlace.formacion.gestormedico.exceptions.BadFormatException;
import com.metaenlace.formacion.gestormedico.exceptions.NotFoundException;
import com.metaenlace.formacion.gestormedico.mapper.PacienteMapper;
import com.metaenlace.formacion.gestormedico.repositories.PacienteRepository;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepo;

    public PacienteDTO find(Long id){
        try {
            Optional<Paciente> optPaciente= pacienteRepo.findById(id);

            if (optPaciente.isEmpty()){
                throw new NotFoundException("No se ha encontrado al paciente");
            }
            Paciente paciente = optPaciente.get();
            PacienteDTO pacienteDTO = PacienteMapper.INSTANCE.pacienteToPacienteDTO(paciente);
            return pacienteDTO;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

    }

    public ArrayList<PacienteDTO> findAll(){
        ArrayList<PacienteDTO> listPacientes = new ArrayList<>();
        Iterable<Paciente> optListPacientes = pacienteRepo.findAll();
        for (Paciente p : optListPacientes) {
            PacienteDTO pacienteDTO = PacienteMapper.INSTANCE.pacienteToPacienteDTO(p);
            listPacientes.add(pacienteDTO);
        }
        return listPacientes;
    }

    public void crear(PacienteDTO pacienteDTO){

        try {
            /***
             * comprobar:
             * contraseña segura y cifrar despues
             */
            if(!pacienteDTO.getNumTarjeta().matches("[0-9]+")){
                throw new BadFormatException("El numero de tarjeta no es valido");
            }
            if(!pacienteDTO.getNSS().matches("\\d{9}")){
                throw new BadFormatException("El nss no es valido");
            }
            if(!pacienteDTO.getTelefono().matches("\\d{9}")){
                throw new BadFormatException("El telefono no es valido");
            }

            Paciente paciente = PacienteMapper.INSTANCE.pacienteDTOToPaciente(pacienteDTO);
            pacienteRepo.save(paciente);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public void modificar(PacienteDTO pacienteDTO){
        try {
            Paciente paciente = PacienteMapper.INSTANCE.pacienteDTOToPaciente(pacienteDTO);
            pacienteRepo.save(paciente);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public void borrar(Long id){
        try {
            Optional<Paciente> optPaciente = pacienteRepo.findById(id);
            if(optPaciente.isEmpty()){
                throw new NotFoundException("No se ha encontrado al paciente");
            }
            pacienteRepo.deleteById(id);

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
