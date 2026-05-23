package com.automotivo.service;

import com.automotivo.exception.RecursoNaoEncontradoException;
import com.automotivo.model.Marca;
import com.automotivo.model.Modelo;
import com.automotivo.repository.ModeloRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;
    private final MarcaService marcaService;

    public ModeloService(ModeloRepository modeloRepository, MarcaService marcaService) {
        this.modeloRepository = modeloRepository;
        this.marcaService = marcaService;
    }

    public List<Modelo> listarTodos() {
        return modeloRepository.findAll();
    }

    public Modelo buscarPorId(Long id) {
        return modeloRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Modelo", id));
    }

    public List<Modelo> listarPorMarca(Long marcaId) {
        return modeloRepository.findByMarcaId(marcaId);
    }

    public Modelo cadastrar(Modelo modelo, Long marcaId) {
        Marca marca = marcaService.buscarPorId(marcaId);
        modelo.setMarca(marca);
        return modeloRepository.save(modelo);
    }

    public Modelo atualizar(Long id, Modelo modeloAtualizado, Long marcaId) {
        Modelo modelo = buscarPorId(id);
        modelo.setNome(modeloAtualizado.getNome());
        modelo.setCategoria(modeloAtualizado.getCategoria());
        if (marcaId != null) {
            Marca marca = marcaService.buscarPorId(marcaId);
            modelo.setMarca(marca);
        }
        return modeloRepository.save(modelo);
    }

    public void remover(Long id) {
        buscarPorId(id);
        modeloRepository.deleteById(id);
    }
}
