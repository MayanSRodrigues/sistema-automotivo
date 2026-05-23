package com.automotivo.controller;

import com.automotivo.model.Marca;
import com.automotivo.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller REST para Marca.
 *
 * @RestController: combina @Controller + @ResponseBody (retorna JSON automaticamente)
 * @RequestMapping: prefixo de URL para todos os endpoints desta classe
 *
 * Endpoints disponíveis:
 *   GET    /api/marcas         → listar todas
 *   GET    /api/marcas/{id}    → buscar por ID
 *   POST   /api/marcas         → cadastrar
 *   PUT    /api/marcas/{id}    → atualizar
 *   DELETE /api/marcas/{id}    → remover
 */
@RestController
@RequestMapping("/api/marcas")
@CrossOrigin(origins = "*") // permite requisições de qualquer origem (front-end)
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    /** GET /api/marcas → retorna todas as marcas */
    @GetMapping
    public ResponseEntity<List<Marca>> listarTodas() {
        return ResponseEntity.ok(marcaService.listarTodas());
    }

    /** GET /api/marcas/{id} → retorna uma marca por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(marcaService.buscarPorId(id));
    }

    /** POST /api/marcas → cadastra nova marca */
    @PostMapping
    public ResponseEntity<Marca> cadastrar(@Valid @RequestBody Marca marca) {
        Marca salva = marcaService.cadastrar(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    /** PUT /api/marcas/{id} → atualiza marca existente */
    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizar(@PathVariable Long id,
                                            @Valid @RequestBody Marca marca) {
        return ResponseEntity.ok(marcaService.atualizar(id, marca));
    }

    /** DELETE /api/marcas/{id} → remove marca */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        marcaService.remover(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }
}
