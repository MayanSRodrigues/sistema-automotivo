package com.automotivo.service;

import com.automotivo.exception.RecursoNaoEncontradoException;
import com.automotivo.model.Marca;
import com.automotivo.repository.MarcaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Camada de serviço para Marca.
 * Contém as regras de negócio — separada do Controller e do Repository.
 *
 * @Service: informa ao Spring que esta classe é um componente de serviço.
 *
 * Conceito POO: Separação de Responsabilidades (cada classe tem uma função)
 */
@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    /** Injeção de dependência via construtor (boa prática) */
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    /** Lista todas as marcas */
    public List<Marca> listarTodas() {
        return marcaRepository.findAll();
    }

    /** Busca marca por ID. Lança exceção se não encontrar */
    public Marca buscarPorId(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Marca", id));
    }

    /** Cadastra nova marca */
    public Marca cadastrar(Marca marca) {
        return marcaRepository.save(marca);
    }

    /** Atualiza marca existente */
    public Marca atualizar(Long id, Marca marcaAtualizada) {
        Marca marca = buscarPorId(id);
        marca.setNome(marcaAtualizada.getNome());
        marca.setPaisOrigem(marcaAtualizada.getPaisOrigem());
        return marcaRepository.save(marca);
    }

    /** Remove marca por ID */
    public void remover(Long id) {
        buscarPorId(id); // lança exceção se não existir
        marcaRepository.deleteById(id);
    }
}
