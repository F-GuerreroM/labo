package com.laboratorio.labo.controller;

import com.laboratorio.labo.model.Laboratorio;
import com.laboratorio.labo.service.LaboratorioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping
    public ResponseEntity<Laboratorio> crearLaboratorio(@Valid @RequestBody Laboratorio laboratorio) {
        log.info("Petición REST para crear un laboratorio");
        Laboratorio nuevoLab = laboratorioService.guardarLaboratorio(laboratorio);
        return new ResponseEntity<>(nuevoLab, HttpStatus.CREATED); 
    }
    
    @GetMapping
    public ResponseEntity<List<Laboratorio>> listarLaboratorios() {
        log.info("Petición REST para obtener todos los laboratorios");
        List<Laboratorio> lista = laboratorioService.obtenerTodos();
        return ResponseEntity.ok(lista); // Devuelve 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> obtenerLaboratorioPorId(@PathVariable Long id) {
        log.info("Petición REST para buscar laboratorio por ID: {}", id);
        Laboratorio laboratorio = laboratorioService.obtenerPorId(id);
        return ResponseEntity.ok(laboratorio); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> actualizarLaboratorio(@PathVariable Long id, @Valid @RequestBody Laboratorio labDetalles) {
        log.info("Petición REST para actualizar laboratorio ID: {}", id);
        Laboratorio labExistente = laboratorioService.obtenerPorId(id);
        labExistente.setNombre(labDetalles.getNombre());
        labExistente.setCapacidad(labDetalles.getCapacidad());
        labExistente.setEstado(labDetalles.getEstado());
        
        return ResponseEntity.ok(laboratorioService.guardarLaboratorio(labExistente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLaboratorio(@PathVariable Long id) {
        log.info("Petición REST para eliminar laboratorio ID: {}", id);
        
        // Verificamos que exista antes de borrar
        laboratorioService.obtenerPorId(id); 
        
        laboratorioService.eliminarLaboratorio(id);
        return ResponseEntity.noContent().build(); // Devuelve 204
    }
}