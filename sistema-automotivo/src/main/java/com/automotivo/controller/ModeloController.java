package com.automotivo.controller;

import com.automotivo.model.Modelo;
import com.automotivo.service.ModeloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Endpoints:
 *   GET    /api/modelos                       → listar todos
 *   GET    /api/modelos/{id}                  → buscar por ID
 *   GET    /api/modelos/por-marca/{marcaId}   → listar por marca
 *   POST   /api/modelos/{marcaId}             → cadastrar (associado a uma marca)
 *   PUT    /api/modelos/{id}                  → atualizar
 *   DELETE /api/modelos/{id}                  → remover
 */
@RestController
@RequestMapping("/api/modelos")
@CrossOrigin(origins = "*")
public class ModeloController {

    private final ModeloService modeloService;

    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @GetMapping
    public ResponseEntity<List<Modelo>> listarTodos() {
        return ResponseEntity.ok(modeloService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(modeloService.buscarPorId(id));
    }

    @GetMapping("/por-marca/{marcaId}")
    public ResponseEntity<List<Modelo>> listarPorMarca(@PathVariable Long marcaId) {
        return ResponseEntity.ok(modeloService.listarPorMarca(marcaId));
    }

    /** O marcaId vem na URL: POST /api/modelos/{marcaId} */
    @PostMapping("/{marcaId}")
    public ResponseEntity<Modelo> cadastrar(@PathVariable Long marcaId,
                                             @Valid @RequestBody Modelo modelo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(modeloService.cadastrar(modelo, marcaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modelo> atualizar(@PathVariable Long id,
                                             @RequestBody Modelo modelo,
                                             @RequestParam(required = false) Long marcaId) {
        return ResponseEntity.ok(modeloService.atualizar(id, modelo, marcaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        modeloService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
