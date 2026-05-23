package com.automotivo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Entidade principal do sistema: representa um veículo no estoque.
 *
 * Conceitos POO aplicados:
 * - Encapsulamento: atributos private com getters/setters
 * - Associação: referência ao objeto Modelo
 * - Abstração: método de negócio marcarComoVendido()
 */
@Entity
@Table(name = "veiculos")
public class Veiculo {

    // ==================== ATRIBUTOS ====================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 10, message = "Placa deve ter no máximo 10 caracteres")
    @Column(unique = true, length = 10)
    private String placa;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1900, message = "Ano inválido")
    @Max(value = 2030, message = "Ano inválido")
    @Column(nullable = false)
    private Integer ano;

    @Size(max = 30)
    @Column(length = 30)
    private String cor;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser positivo")
    @Column(nullable = false)
    private Double preco;

    @Min(value = 0, message = "Quilometragem não pode ser negativa")
    @Column(name = "quilometragem")
    private Integer quilometragem = 0;

    /**
     * @Enumerated: armazena o enum como String no banco (ex.: "DISPONIVEL")
     * Em vez de um número (0, 1, 2), que seria difícil de entender.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusVeiculo status = StatusVeiculo.DISPONIVEL;

    /**
     * Relacionamento Many-to-One: muitos veículos podem ter o mesmo modelo.
     */
    @NotNull(message = "O modelo é obrigatório")
    @ManyToOne
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo modelo;

    // ==================== CONSTRUTORES ====================

    public Veiculo() {}

    public Veiculo(Modelo modelo, Integer ano, Double preco, String cor) {
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.cor = cor;
        this.status = StatusVeiculo.DISPONIVEL;
    }

    // ==================== MÉTODOS DE NEGÓCIO (Abstração) ====================

    /**
     * Marca o veículo como vendido.
     * Encapsula a lógica de mudança de status.
     */
    public void marcarComoVendido() {
        this.status = StatusVeiculo.VENDIDO;
    }

    /**
     * Verifica se o veículo está disponível para venda.
     * @return true se o status for DISPONIVEL
     */
    public boolean isDisponivel() {
        return this.status == StatusVeiculo.DISPONIVEL;
    }

    /**
     * Atualiza o preço do veículo com validação.
     * @param novoPreco novo valor do veículo
     */
    public void atualizarPreco(Double novoPreco) {
        if (novoPreco != null && novoPreco > 0) {
            this.preco = novoPreco;
        }
    }

    // ==================== GETTERS E SETTERS ====================

    public Long getId() { return id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public Integer getQuilometragem() { return quilometragem; }
    public void setQuilometragem(Integer quilometragem) { this.quilometragem = quilometragem; }

    public StatusVeiculo getStatus() { return status; }
    public void setStatus(StatusVeiculo status) { this.status = status; }

    public Modelo getModelo() { return modelo; }
    public void setModelo(Modelo modelo) { this.modelo = modelo; }

    // ==================== toString ====================

    @Override
    public String toString() {
        return "Veiculo{" +
               "id=" + id +
               ", placa='" + placa + "'" +
               ", modelo=" + (modelo != null ? modelo.getNome() : "N/A") +
               ", ano=" + ano +
               ", cor='" + cor + "'" +
               ", preco=" + preco +
               ", km=" + quilometragem +
               ", status=" + status +
               "}";
    }
}
