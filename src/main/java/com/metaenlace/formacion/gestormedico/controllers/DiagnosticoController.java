package com.metaenlace.formacion.gestormedico.controllers;

import com.metaenlace.formacion.gestormedico.dto.DiagnosticoDTO;
import com.metaenlace.formacion.gestormedico.services.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/diagnostico")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiagnosticoDTO> getDiagnostico(@PathVariable Long id){
        DiagnosticoDTO diag = diagnosticoService.find(id);
        return ResponseEntity.ok(diag);
    }

    @GetMapping("/todos")
    public ResponseEntity<ArrayList> getTodos(){
        ArrayList<DiagnosticoDTO> diagnosticos = diagnosticoService.findAll();
        return ResponseEntity.ok(diagnosticos);
    }

    @PostMapping()
    public ResponseEntity crearDiagnostico(@RequestBody DiagnosticoDTO diagnostico){

        diagnosticoService.crear(diagnostico);
        return ResponseEntity.ok("200");
    }

    @PutMapping("/{id}")
    public ResponseEntity modificarDiagnostico(@RequestBody DiagnosticoDTO diagnostico, @PathVariable Long id){
        diagnosticoService.modificar(id, diagnostico);
        return ResponseEntity.ok("200");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarDiagnostico(@PathVariable Long id){

        diagnosticoService.borrar(id);
        return ResponseEntity.ok("200");
    }

}
