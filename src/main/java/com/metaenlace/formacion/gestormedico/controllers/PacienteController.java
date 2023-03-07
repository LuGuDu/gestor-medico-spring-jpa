package com.metaenlace.formacion.gestormedico.controllers;

import com.metaenlace.formacion.gestormedico.dto.PacienteDTO;
import com.metaenlace.formacion.gestormedico.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteDTO> getPaciente(@PathVariable Long id){
        PacienteDTO pa = pacienteService.find(id);
        return ResponseEntity.ok(pa);
    }

    @GetMapping("/todos")
    public ResponseEntity<ArrayList> getTodos(){
        ArrayList<PacienteDTO> pacientes = pacienteService.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity crearPaciente(@RequestBody PacienteDTO paciente){
        pacienteService.crear(paciente);
        return ResponseEntity.ok("200");
    }

    @PutMapping(path = "/{id}")
    ResponseEntity modificarPaciente(@RequestBody PacienteDTO paciente, @PathVariable Long id){
        pacienteService.modificar(id, paciente);
        return ResponseEntity.ok("200");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarPaciente(@PathVariable Long id){
        pacienteService.borrar(id);
        return ResponseEntity.ok("200");
    }
}
