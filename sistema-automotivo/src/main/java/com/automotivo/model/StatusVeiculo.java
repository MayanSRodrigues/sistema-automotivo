package com.automotivo.model;

/**
 * Enumeração que representa os possíveis estados de um veículo.
 *
 * Conceito POO aplicado: Abstração
 * Em vez de usar Strings livres (ex.: "disponivel", "vendido"),
 * usamos um enum para garantir que apenas valores válidos sejam usados.
 */
public enum StatusVeiculo {
    DISPONIVEL,   // Veículo está disponível para venda
    VENDIDO,      // Veículo foi vendido (mantido para histórico)
    RESERVADO,    // Veículo está reservado por um cliente
    EM_REVISAO    // Veículo está em manutenção/revisão
}
