package com.automotivo.repository;

import com.automotivo.model.StatusVeiculo;
import com.automotivo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositório de Veiculo com queries de filtro.
 *
 * O Spring Data JPA converte nomes de método em SQL automaticamente.
 * Exemplo: findByStatus → SELECT * FROM veiculos WHERE status = ?
 */
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    /** Lista veículos por status */
    List<Veiculo> findByStatus(StatusVeiculo status);

    /** Lista veículos disponíveis (status = DISPONIVEL) */
    List<Veiculo> findByStatusOrderByPrecoAsc(StatusVeiculo status);

    /** Filtra por faixa de preço */
    List<Veiculo> findByPrecoBetween(Double precoMin, Double precoMax);

    /** Filtra por nome da marca (navega pelo relacionamento: veiculo → modelo → marca) */
    List<Veiculo> findByModeloMarcaNome(String nomeMarca);

    /** Filtra por nome do modelo */
    List<Veiculo> findByModeloNome(String nomeModelo);

    /** Filtra por ano */
    List<Veiculo> findByAno(Integer ano);

    /** Filtra por intervalo de anos */
    List<Veiculo> findByAnoBetween(Integer anoInicio, Integer anoFim);

    /**
     * Query JPQL customizada para filtros combinados.
     * JPQL usa nomes de classes Java, não nomes de tabelas SQL.
     * O uso de @Param torna os parâmetros opcionais (null = ignorado no filtro).
     */
    @Query("SELECT v FROM Veiculo v " +
           "WHERE (:marca IS NULL OR v.modelo.marca.nome = :marca) " +
           "AND (:modelo IS NULL OR v.modelo.nome = :modelo) " +
           "AND (:status IS NULL OR v.status = :status) " +
           "AND (:anoMin IS NULL OR v.ano >= :anoMin) " +
           "AND (:anoMax IS NULL OR v.ano <= :anoMax) " +
           "AND (:precoMin IS NULL OR v.preco >= :precoMin) " +
           "AND (:precoMax IS NULL OR v.preco <= :precoMax) " +
           "ORDER BY v.preco ASC")
    List<Veiculo> filtrar(
            @Param("marca")    String marca,
            @Param("modelo")   String modelo,
            @Param("status")   StatusVeiculo status,
            @Param("anoMin")   Integer anoMin,
            @Param("anoMax")   Integer anoMax,
            @Param("precoMin") Double precoMin,
            @Param("precoMax") Double precoMax
    );
}
