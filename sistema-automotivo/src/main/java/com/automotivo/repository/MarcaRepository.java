package com.automotivo.repository;

import com.automotivo.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório de Marca.
 *
 * Conceito POO: Abstração + Interface
 * Ao estender JpaRepository, ganhamos automaticamente os métodos:
 *   - save(), findById(), findAll(), deleteById(), count(), existsById()
 *
 * Os métodos abaixo são gerados automaticamente pelo Spring Data JPA
 * a partir dos nomes dos métodos (convenção: findBy + NomeDoAtributo).
 */
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    /** Busca uma marca pelo nome (case-sensitive) */
    Optional<Marca> findByNome(String nome);

    /** Verifica se já existe uma marca com esse nome */
    boolean existsByNome(String nome);
}
