package com.metaenlace.formacion.gestormedico.services;

import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import com.metaenlace.formacion.gestormedico.exceptions.BadFormatException;
import com.metaenlace.formacion.gestormedico.mapper.MedicoMapper;
import com.metaenlace.formacion.gestormedico.dto.MedicoDTO;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.exceptions.NotFoundException;
import com.metaenlace.formacion.gestormedico.repositories.CitaRepository;
import com.metaenlace.formacion.gestormedico.repositories.MedicoRepository;
import com.metaenlace.formacion.gestormedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class MedicoService {

    /***
     * REGEX: ^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$
     *
     * ^               # start of the string
     * (?=.*\d)        # min 1 numero
     * (?=.*[a-z])     # min 1 minuscula
     * (?=.*[A-Z])     # min 1 mayuscula
     * .{8,}           # min 8 caracteres
     * $               # end of the string
     */
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

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
            return MedicoMapper.INSTANCE.medicoToMedicoDTO(medico);
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
            if(!medicoDTO.getNumColegiado().matches("\\d{9}")){
                throw new BadFormatException("El numero de colegiado no es valido. Debe tener 9 digitos.");
            }

            if (!PASSWORD_PATTERN.matcher(medicoDTO.getClave()).matches()) {
                throw new BadFormatException("""
                        La clave no es segura. Debe cumplir los siguientes requisitos:
                         min. 1 numero.\s
                         min. 1 minuscula.\s
                         min. 1 mayuscula.\s
                         min. 8 caracteres.""");
            }

            ArrayList<Cita> citas = new ArrayList<>();
            ArrayList<Paciente> pacientes = new ArrayList<>();

            if (medicoDTO.getCitasId() == null){
                medicoDTO.setCitasId(new ArrayList<>());
            }
            if (medicoDTO.getPacientesId() == null){
                medicoDTO.setPacientesId(new ArrayList<>());
            }

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

            Medico medico = MedicoMapper.INSTANCE.medicoDTOToMedico(medicoDTO, pacientes, citas);

            medicoRepo.save(medico);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public void modificar(Long id, MedicoDTO medicoDTO){
        try {
            if(!medicoDTO.getNumColegiado().matches("\\d{9}")){
                throw new BadFormatException("El numero de colegiado no es valido. Debe tener 9 digitos.");
            }
            if (!PASSWORD_PATTERN.matcher(medicoDTO.getClave()).matches()) {
                throw new BadFormatException("""
                        La clave no es segura. Debe cumplir los siguientes requisitos:
                         min. 1 numero.\s
                         min. 1 minuscula.\s
                         min. 1 mayuscula.\s
                         min. 8 caracteres.""");
            }

            ArrayList<Cita> citas = new ArrayList<>();
            ArrayList<Paciente> pacientes = new ArrayList<>();

            if (medicoDTO.getCitasId() == null){
                medicoDTO.setCitasId(new ArrayList<>());
            }
            if (medicoDTO.getPacientesId() == null){
                medicoDTO.setPacientesId(new ArrayList<>());
            }

            if(!medicoDTO.getPacientesId().isEmpty()){
                for (Long ident : medicoDTO.getPacientesId()) {
                    Optional<Paciente> optPaciente= pacienteRepo.findById(ident);
                    if(optPaciente.isPresent()){
                        pacientes.add(optPaciente.get());
                    }
                }
            }

            if(!medicoDTO.getCitasId().isEmpty()){
                for (Long ident : medicoDTO.getCitasId()) {
                    Optional<Cita> optCita = citaRepo.findById(ident);
                    if(optCita.isPresent()){
                        citas.add(optCita.get());
                    }
                }
            }

            Medico medico = MedicoMapper.INSTANCE.medicoDTOToMedico(medicoDTO, pacientes, citas);
            medico.setId(id);
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
