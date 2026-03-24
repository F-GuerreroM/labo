package com.laboratorio.labo.service;

import com.laboratorio.labo.exception.ResourceNotFoundException;
import com.laboratorio.labo.model.Laboratorio;
import com.laboratorio.labo.repository.LaboratorioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public Laboratorio guardarLaboratorio(Laboratorio laboratorio) {
        log.info("Creando/Actualizando laboratorio: {}", laboratorio.getNombre());
        return laboratorioRepository.save(laboratorio);
    }

    public List<Laboratorio> obtenerTodos() {
        log.info("Obteniendo la lista de todos los laboratorios");
        return laboratorioRepository.findAll();
    }

public Laboratorio obtenerPorId(Long id) {
    return laboratorioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Laboratorio no encontrado con ID: " + id));
}

    public void eliminarLaboratorio(Long id) {
        log.info("Eliminando laboratorio con ID: {}", id);
        laboratorioRepository.deleteById(id);
    }
}