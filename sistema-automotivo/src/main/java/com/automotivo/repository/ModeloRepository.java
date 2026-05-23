package com.automotivo.repository;

import com.automotivo.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

    /** Busca todos os modelos de uma marca específica pelo ID da marca */
    List<Modelo> findByMarcaId(Long marcaId);

    /** Busca modelos pelo nome da marca (navegação pelo relacionamento) */
    List<Modelo> findByMarcaNome(String nomeMarca);

    /** Busca modelos por categoria (ex.: SUV, SEDAN) */
    List<Modelo> findByCategoria(String categoria);
}
