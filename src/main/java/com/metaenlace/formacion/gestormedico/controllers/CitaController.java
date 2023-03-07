package com.metaenlace.formacion.gestormedico.controllers;

import com.metaenlace.formacion.gestormedico.dto.CitaDTO;
import com.metaenlace.formacion.gestormedico.entities.Cita;
import com.metaenlace.formacion.gestormedico.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CitaDTO> getCita(@PathVariable Long id){
        CitaDTO cita = citaService.find(id);
        return ResponseEntity.ok(cita);
    }

    @GetMapping(path = "/todos", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList> getTodos(){
        ArrayList<CitaDTO> citas = citaService.findAll();
        return ResponseEntity.ok(citas);
    }

    @PostMapping()
    public ResponseEntity crearCita(@RequestBody CitaDTO cita){
        citaService.crear(cita);
        return ResponseEntity.ok("200");
    }

    @PutMapping("/{id}")
    public ResponseEntity modificarCita(@RequestBody CitaDTO cita, @PathVariable Long id){
        citaService.modificar(id, cita);
        return ResponseEntity.ok("200");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarCita(@PathVariable Long id){

        citaService.borrar(id);
        return ResponseEntity.ok("200");
    }
}
