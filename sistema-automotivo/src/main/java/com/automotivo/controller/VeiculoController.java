package com.automotivo.controller;

import com.automotivo.model.StatusVeiculo;
import com.automotivo.model.Veiculo;
import com.automotivo.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller principal: Veículos.
 *
 * Endpoints:
 *   GET    /api/veiculos                → listar todos
 *   GET    /api/veiculos/disponiveis    → listar disponíveis
 *   GET    /api/veiculos/{id}           → buscar por ID
 *   GET    /api/veiculos/filtrar        → filtro combinado (query params)
 *   POST   /api/veiculos/{modeloId}     → cadastrar
 *   PUT    /api/veiculos/{id}           → atualizar
 *   PATCH  /api/veiculos/{id}/vender    → marcar como vendido
 *   DELETE /api/veiculos/{id}           → remover permanentemente
 */
@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    /** Lista todos os veículos */
    @GetMapping
    public ResponseEntity<List<Veiculo>> listarTodos() {
        return ResponseEntity.ok(veiculoService.listarTodos());
    }

    /** Lista apenas veículos disponíveis, ordenados por preço */
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Veiculo>> listarDisponiveis() {
        return ResponseEntity.ok(veiculoService.listarDisponiveis());
    }

    /** Busca veículo por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.buscarPorId(id));
    }

    /**
     * Filtro combinado — todos os parâmetros são opcionais.
     *
     * Exemplo de uso:
     *   GET /api/veiculos/filtrar?marca=Toyota&precoMax=80000&status=DISPONIVEL
     *   GET /api/veiculos/filtrar?anoMin=2020&anoMax=2023
     */
    @GetMapping("/filtrar")
    public ResponseEntity<List<Veiculo>> filtrar(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) StatusVeiculo status,
            @RequestParam(required = false) Integer anoMin,
            @RequestParam(required = false) Integer anoMax,
            @RequestParam(required = false) Double precoMin,
            @RequestParam(required = false) Double precoMax) {

        List<Veiculo> resultado = veiculoService.filtrar(
                marca, modelo, status, anoMin, anoMax, precoMin, precoMax);
        return ResponseEntity.ok(resultado);
    }

    /** Cadastra novo veículo. O modeloId vem na URL */
    @PostMapping("/{modeloId}")
    public ResponseEntity<Veiculo> cadastrar(@PathVariable Long modeloId,
                                              @Valid @RequestBody Veiculo veiculo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(veiculoService.cadastrar(veiculo, modeloId));
    }

    /** Atualiza preço, km, status e/ou cor de um veículo */
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id,
                                              @RequestBody Veiculo veiculo) {
        return ResponseEntity.ok(veiculoService.atualizar(id, veiculo));
    }

    /**
     * Marca veículo como VENDIDO sem excluir.
     * @PATCH: operação parcial (só muda o status)
     */
    @PatchMapping("/{id}/vender")
    public ResponseEntity<Veiculo> marcarComoVendido(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.marcarComoVendido(id));
    }

    /** Remove permanentemente (para duplicatas ou erros de cadastro) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        veiculoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
