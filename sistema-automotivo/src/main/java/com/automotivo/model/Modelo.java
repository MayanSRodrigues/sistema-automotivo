package com.automotivo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidade que representa o Modelo de um veículo (ex.: Corolla, Civic, Gol).
 * Um Modelo pertence a uma Marca — este é um relacionamento Many-to-One.
 *
 * Conceito POO aplicado: Associação entre objetos
 * Um objeto Modelo contém uma referência a um objeto Marca.
 * Isso representa o relacionamento do mundo real: um modelo pertence a uma marca.
 */
@Entity
@Table(name = "modelos")
public class Modelo {

    // ==================== ATRIBUTOS ====================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do modelo é obrigatório")
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String nome;

    /**
     * Categoria do veículo.
     * Exemplos: SEDAN, SUV, HATCH, PICKUP, ESPORTIVO, MINIVAN
     */
    @Size(max = 30)
    @Column(length = 30)
    private String categoria;

    /**
     * Relacionamento Many-to-One: muitos modelos pertencem a uma marca.
     * @ManyToOne: cria a foreign key na tabela 'modelos'
     * @JoinColumn: define o nome da coluna da foreign key
     */
    @NotNull(message = "A marca é obrigatória")
    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    // ==================== CONSTRUTORES ====================

    public Modelo() {}

    public Modelo(String nome, String categoria, Marca marca) {
        this.nome = nome;
        this.categoria = categoria;
        this.marca = marca;
    }

    // ==================== GETTERS E SETTERS ====================

    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }

    // ==================== toString ====================

    @Override
    public String toString() {
        return "Modelo{id=" + id + ", nome='" + nome +
               "', categoria='" + categoria +
               "', marca=" + (marca != null ? marca.getNome() : "N/A") + "}";
    }
}
