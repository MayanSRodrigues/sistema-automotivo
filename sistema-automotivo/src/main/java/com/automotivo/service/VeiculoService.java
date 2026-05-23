package com.automotivo.service;

import com.automotivo.exception.RecursoNaoEncontradoException;
import com.automotivo.model.Modelo;
import com.automotivo.model.StatusVeiculo;
import com.automotivo.model.Veiculo;
import com.automotivo.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Serviço principal: contém todas as regras de negócio sobre veículos.
 */
@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ModeloService modeloService;

    public VeiculoService(VeiculoRepository veiculoRepository, ModeloService modeloService) {
        this.veiculoRepository = veiculoRepository;
        this.modeloService = modeloService;
    }

    /** Lista todos os veículos */
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    /** Lista apenas veículos disponíveis */
    public List<Veiculo> listarDisponiveis() {
        return veiculoRepository.findByStatusOrderByPrecoAsc(StatusVeiculo.DISPONIVEL);
    }

    /** Busca veículo por ID */
    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veiculo", id));
    }

    /** Cadastra novo veículo */
    public Veiculo cadastrar(Veiculo veiculo, Long modeloId) {
        Modelo modelo = modeloService.buscarPorId(modeloId);
        veiculo.setModelo(modelo);
        veiculo.setStatus(StatusVeiculo.DISPONIVEL);
        return veiculoRepository.save(veiculo);
    }

    /** Atualiza dados editáveis de um veículo */
    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        Veiculo veiculo = buscarPorId(id);
        // Atualiza apenas campos permitidos
        if (veiculoAtualizado.getPreco() != null) veiculo.setPreco(veiculoAtualizado.getPreco());
        if (veiculoAtualizado.getQuilometragem() != null) veiculo.setQuilometragem(veiculoAtualizado.getQuilometragem());
        if (veiculoAtualizado.getStatus() != null) veiculo.setStatus(veiculoAtualizado.getStatus());
        if (veiculoAtualizado.getCor() != null) veiculo.setCor(veiculoAtualizado.getCor());
        if (veiculoAtualizado.getPlaca() != null) veiculo.setPlaca(veiculoAtualizado.getPlaca());
        return veiculoRepository.save(veiculo);
    }

    /** Remove (exclui permanentemente) um veículo */
    public void remover(Long id) {
        buscarPorId(id);
        veiculoRepository.deleteById(id);
    }

    /** Marca veículo como vendido (sem exclusão — preserva histórico) */
    public Veiculo marcarComoVendido(Long id) {
        Veiculo veiculo = buscarPorId(id);
        veiculo.marcarComoVendido(); // método de negócio do modelo
        return veiculoRepository.save(veiculo);
    }

    /**
     * Filtro combinado de veículos.
     * Qualquer parâmetro null é ignorado no filtro.
     */
    public List<Veiculo> filtrar(String marca, String modelo, StatusVeiculo status,
                                  Integer anoMin, Integer anoMax,
                                  Double precoMin, Double precoMax) {
        return veiculoRepository.filtrar(marca, modelo, status, anoMin, anoMax, precoMin, precoMax);
    }
}
