package com.automotivo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidade que representa a Marca de um veículo (ex.: Toyota, Honda, Volkswagen).
 *
 * Conceito POO aplicado: Encapsulamento
 * Todos os atributos são private. O acesso externo só é possível
 * por meio dos métodos getters e setters públicos.
 *
 * @Entity: informa ao JPA que esta classe é uma tabela no banco
 * @Table: define o nome da tabela no MySQL
 */
@Entity
@Table(name = "marcas")
public class Marca {

    // ==================== ATRIBUTOS (privados - encapsulamento) ====================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT no MySQL
    private Long id;

    @NotBlank(message = "O nome da marca é obrigatório")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @Size(max = 50)
    @Column(name = "pais_origem", length = 50)
    private String paisOrigem;

    // ==================== CONSTRUTORES ====================

    /** Construtor padrão exigido pelo JPA */
    public Marca() {}

    /** Construtor para facilitar a criação de objetos */
    public Marca(String nome, String paisOrigem) {
        this.nome = nome;
        this.paisOrigem = paisOrigem;
    }

    // ==================== GETTERS E SETTERS (encapsulamento) ====================

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    // ==================== MÉTODO toString (Polimorfismo) ====================

    @Override
    public String toString() {
        return "Marca{id=" + id + ", nome='" + nome + "', paisOrigem='" + paisOrigem + "'}";
    }
}
