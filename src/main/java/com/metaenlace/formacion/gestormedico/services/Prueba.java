package com.metaenlace.formacion.gestormedico.services;

import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.entities.Diagnostico;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.entities.Paciente;
import com.metaenlace.formacion.gestormedico.repositories.CitaRepository;
import com.metaenlace.formacion.gestormedico.repositories.DiagnosticoRepository;
import com.metaenlace.formacion.gestormedico.repositories.MedicoRepository;
import com.metaenlace.formacion.gestormedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class Prueba {

    @Autowired
    private CitaRepository citaRepo;
    @Autowired
    private DiagnosticoRepository diagnosticoRepo;
    @Autowired
    private MedicoRepository medicoRepo;
    @Autowired
    private PacienteRepository pacienteRepo;

    public void prueba(){

        //CREACION DEL MEDICO
        Medico m1 = new Medico();
        m1.setNombre("Alberto");
        m1.setApellidos("Rodriguez");
        m1.setUsuario("AlRo2023");
        m1.setClave("password");
        m1.setNumColegiado("123");

        //CREACION DEL PACIENTE
        Paciente p1 = new Paciente();
        p1.setNombre("John");
        p1.setApellidos("Doe");
        p1.setUsuario("JohnDoe2023");
        p1.setClave("password");
        p1.setNSS("12345");
        p1.setNumTarjeta("6789");
        p1.setTelefono("123456789");
        p1.setDireccion("Calle Espinosa 23");

        //CREACION DE LA CITA
        Date dia_actual = new Date();

        Cita c1 = new Cita();
        c1.setFechaHora(dia_actual);
        c1.setMotivoCita("Dolor de rodilla");

        //CREACION DEL DIAGNOSTICO
        Diagnostico d1 = new Diagnostico();
        d1.setValoracionEspecialista("Amputacion");
        d1.setEnfermedad("Gota");
        d1.setCita(c1);

        c1.setDiagnostico(d1);
        c1.setMedico(m1);
        c1.setPaciente(p1);

        //SET DE LISTAS - RELACIONES ENTRE ENTIDADES
        ArrayList<Medico> medicos = new ArrayList<>();
        ArrayList<Paciente> pacientes = new ArrayList<>();
        ArrayList<Cita> citas = new ArrayList<>();

        medicos.add(m1);
        pacientes.add(p1);
        citas.add(c1);

        m1.setPacientes(pacientes);
        m1.setCitas(citas);

        p1.setMedicos(medicos);
        p1.setCitas(citas);


        //GUARDAR EN BASE DE DATOS
        pacienteRepo.save(p1);
        medicoRepo.save(m1);
        citaRepo.save(c1);
        diagnosticoRepo.save(d1);

        System.out.println("TODO LISTO");

    }
}
