package com.automotivo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação.
 * A anotação @SpringBootApplication ativa:
 *   - @Configuration: define a classe como fonte de configurações
 *   - @EnableAutoConfiguration: configura automaticamente o Spring
 *   - @ComponentScan: varre o pacote em busca de componentes
 */
@SpringBootApplication
public class SistemaAutomotivoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaAutomotivoApplication.class, args);
        System.out.println("==============================================");
        System.out.println("  Sistema Automotivo iniciado com sucesso!");
        System.out.println("  Acesse: http://localhost:8080/api");
        System.out.println("==============================================");
    }
}
