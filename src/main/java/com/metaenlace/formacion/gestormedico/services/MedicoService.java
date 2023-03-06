package com.metaenlace.formacion.gestormedico.services;

import com.metaenlace.formacion.gestormedico.exceptions.BadFormatException;
import com.metaenlace.formacion.gestormedico.mapper.MedicoMapper;
import com.metaenlace.formacion.gestormedico.dto.MedicoDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import com.metaenlace.formacion.gestormedico.exceptions.NotFoundException;
import com.metaenlace.formacion.gestormedico.repositories.CitaRepository;
import com.metaenlace.formacion.gestormedico.repositories.MedicoRepository;
import com.metaenlace.formacion.gestormedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepo;
    @Autowired
    private PacienteRepository pacienteRepo;
    @Autowired
    private CitaRepository citaRepo;

    public MedicoDTO find(Long id){
        try {
            Optional<Medico> optMedico= medicoRepo.findById(id);
            if (optMedico.isEmpty()){
                throw new NotFoundException("No se ha encontrado al medico");
            }
            Medico medico = optMedico.get();
            MedicoDTO medicoDTO = MedicoMapper.INSTANCE.medicoToMedicoDTO(medico);
            return medicoDTO;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public ArrayList<MedicoDTO> findAll(){
        ArrayList<MedicoDTO> listMedicos = new ArrayList<>();
        Iterable<Medico> optListMedicos = medicoRepo.findAll();

        for (Medico m : optListMedicos) {
            MedicoDTO medicoDTO = MedicoMapper.INSTANCE.medicoToMedicoDTO(m);
            listMedicos.add(medicoDTO);
        }
        return listMedicos;
    }

    public void crear(MedicoDTO medicoDTO){
        try {
            /***
             * comprobar contraseña segura
             */
            if(!medicoDTO.getNumColegiado().matches("\\d{9}")){
                throw new BadFormatException("El numero de colegiado no es valido. Debe tener 9 digitos.");
            }
            Medico medico = MedicoMapper.INSTANCE.medicoDTOToMedico(medicoDTO);
            medicoRepo.save(medico);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public void modificar(MedicoDTO medicoDTO){
        try {
            if(!medicoDTO.getNumColegiado().matches("\\d{9}")){
                throw new BadFormatException("El numero de colegiado no es valido. Debe tener 9 digitos.");
            }
            Medico medico = MedicoMapper.INSTANCE.medicoDTOToMedico(medicoDTO);
            medicoRepo.save(medico);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public void borrar(Long id){
        try {
            Optional<Medico> optMedico = medicoRepo.findById(id);
            if (optMedico.isEmpty()){
                throw new NotFoundException("No se ha encontrado al medico");
            }
            medicoRepo.deleteById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
