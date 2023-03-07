package com.metaenlace.formacion.gestormedico.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

import com.metaenlace.formacion.gestormedico.dto.MedicoDTO;
import com.metaenlace.formacion.gestormedico.dto.PacienteDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import com.metaenlace.formacion.gestormedico.exceptions.BadFormatException;
import com.metaenlace.formacion.gestormedico.mapper.MedicoMapper;
import com.metaenlace.formacion.gestormedico.mapper.PacienteMapper;
import com.metaenlace.formacion.gestormedico.repositories.CitaRepository;
import com.metaenlace.formacion.gestormedico.repositories.MedicoRepository;
import com.metaenlace.formacion.gestormedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	/***
	 * REGEX: ^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Medico medico = medicoRepo.findByUsuario(username);
		Paciente paciente = pacienteRepo.findByUsuario(username);
		UserDetails userDetails;
		if (medico == null || paciente == null) {
			throw new UsernameNotFoundException("Medico no encontrado: " + username);
		} else {
			if(medico == null){
				userDetails = new User(paciente.getUsuario(), paciente.getClave(), new ArrayList<>());
			} else {
				userDetails = new User(medico.getUsuario(), medico.getClave(), new ArrayList<>());
			}
		}

		return userDetails;
	}
	
	public Medico saveMedico(MedicoDTO medicoDTO) {
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
					optPaciente.ifPresent(pacientes::add);
				}
			}

			if(!medicoDTO.getCitasId().isEmpty()){
				for (Long id : medicoDTO.getCitasId()) {
					Optional<Cita> optCita = citaRepo.findById(id);
					optCita.ifPresent(citas::add);
				}
			}

			Medico medico = MedicoMapper.INSTANCE.medicoDTOToMedico(medicoDTO, pacientes, citas);

			medicoRepo.save(medico);
			return medico;
		} catch (Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}
	}

	public Paciente savePaciente(PacienteDTO pacienteDTO) {

		try {
			if(!pacienteDTO.getNumTarjeta().matches("[0-9]+")){
				throw new BadFormatException("El numero de tarjeta no es valido");
			}
			if(!pacienteDTO.getNSS().matches("\\d{9}")){
				throw new BadFormatException("El nss no es valido");
			}
			if(!pacienteDTO.getTelefono().matches("\\d{9}")){
				throw new BadFormatException("El telefono no es valido");
			}
			if (!PASSWORD_PATTERN.matcher(pacienteDTO.getClave()).matches()) {
				throw new BadFormatException("""
                        La clave no es segura. Debe cumplir los siguientes requisitos:
                         min. 1 numero.\s
                         min. 1 minuscula.\s
                         min. 1 mayuscula.\s
                         min. 8 caracteres.""");
			}

			ArrayList<Cita> citas = new ArrayList<>();
			ArrayList<Medico> medicos = new ArrayList<>();

			if (pacienteDTO.getCitasId() == null){
				pacienteDTO.setCitasId(new ArrayList<>());
			}
			if (pacienteDTO.getMedicosId() == null){
				pacienteDTO.setMedicosId(new ArrayList<>());
			}

			if(!pacienteDTO.getMedicosId().isEmpty()){
				for (Long id : pacienteDTO.getMedicosId()) {
					Optional<Medico> optMedico= medicoRepo.findById(id);
					optMedico.ifPresent(medicos::add);
				}
			}
			if(!pacienteDTO.getCitasId().isEmpty()){
				for (Long id : pacienteDTO.getCitasId()) {
					Optional<Cita> optCita = citaRepo.findById(id);
					optCita.ifPresent(citas::add);
				}
			}

			Paciente paciente = PacienteMapper.INSTANCE.pacienteDTOToPaciente(pacienteDTO, medicos, citas);
			pacienteRepo.save(paciente);
			return paciente;
		} catch (Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}
	}
}