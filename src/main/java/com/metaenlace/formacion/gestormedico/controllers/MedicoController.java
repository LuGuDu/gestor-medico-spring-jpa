package com.metaenlace.formacion.gestormedico.controllers;

import com.metaenlace.formacion.gestormedico.dto.MedicoDTO;
import com.metaenlace.formacion.gestormedico.entities.Medico;
import com.metaenlace.formacion.gestormedico.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicoDTO> getMedico(@PathVariable Long id){
        MedicoDTO me = medicoService.find(id);
        return ResponseEntity.ok(me);
    }

    @GetMapping("/todos")
    public ResponseEntity<ArrayList> getTodos(){
        ArrayList<MedicoDTO> medicos = medicoService.findAll();
        return ResponseEntity.ok(medicos);
    }

    @PostMapping
    public ResponseEntity crearMedico(@RequestBody MedicoDTO medico){
        medicoService.crear(medico);
        return ResponseEntity.ok("200");
    }

    @PutMapping
    ResponseEntity modificarMedico(@RequestBody MedicoDTO medico){
        medicoService.modificar(medico);
        return ResponseEntity.ok("200");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarMedico(@PathVariable Long id){
        medicoService.borrar(id);
        return ResponseEntity.ok("200");
    }
}
