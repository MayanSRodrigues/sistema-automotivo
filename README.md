# 🚗 Sistema Automotivo — Gestão de Estoque de Veículos
**Disciplina:** Programação Orientada a Objetos | **Instituição:** UniFECAF

## Sobre o Projeto
Sistema CRUD em Java com Spring Boot para gerenciamento de estoque de veículos em concessionárias. Permite cadastrar, consultar, atualizar e remover veículos, marcas e modelos, com filtros por marca, modelo, preço, ano e status.

## Tecnologias
- Java 17
- Spring Boot 3
- Spring Data JPA / Hibernate
- MySQL 8
- HTML, CSS e JavaScript (Frontend)

## Como Executar
1. Clone o repositório
2. Configure sua senha do MySQL em `application.properties`
3. Execute o script `banco_de_dados.sql` no MySQL
4. Rode com `mvn spring-boot:run`
5. Acesse `http://localhost:8080/api/veiculos`

## Endpoints principais
- `GET /api/veiculos` — lista todos os veículos
- `GET /api/veiculos/disponiveis` — lista disponíveis
- `GET /api/veiculos/filtrar?marca=Toyota` — filtro combinado
- `POST /api/veiculos/{modeloId}` — cadastrar veículo
- `PUT /api/veiculos/{id}` — atualizar
- `DELETE /api/veiculos/{id}` — remover

## Conceitos POO Aplicados
- Encapsulamento, Herança, Polimorfismo, Abstração e Classes/Objetos
