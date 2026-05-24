# 🚗 Sistema Automotivo — Gestão de Estoque de Veículos
**Disciplina:** Programação Orientada a Objetos | **Instituição:** UniFECAF

## Sobre o Projeto
Sistema CRUD em Java com Spring Boot para gerenciamento de estoque de veículos em concessionárias. Permite cadastrar, consultar, atualizar e remover veículos, marcas e modelos, com filtros por marca, modelo, preço, ano e status.

## Tecnologias
- Java 17+
- Spring Boot 3
- Spring Data JPA / Hibernate
- MySQL 8
- HTML, CSS e JavaScript (Frontend)

## Como Rodar o Projeto

### Pré-requisitos
- Java 17 ou superior instalado
- Maven instalado e configurado no PATH
- MySQL 8 instalado e rodando

### Passo a Passo

**1. Clone o repositório**

Baixe o projeto ou clone via terminal:
git clone https://github.com/MayanSRodrigues/sistema-automotivo.git

**2. Configure o banco de dados**

Abra o MySQL Workbench e execute o arquivo banco_de_dados.sql para criar e popular o banco automaticamente.

**3. Configure sua senha do MySQL**

Abra o arquivo src/main/resources/application.properties e altere a linha:
spring.datasource.password=sua_senha_aqui

**4. Rode o projeto**

No terminal, dentro da pasta do projeto:
mvn clean spring-boot:run

Aguarde aparecer a mensagem: Sistema Automotivo iniciado com sucesso!

**5. Acesse o sistema**

Abra o navegador e acesse:
http://localhost:8080/frontend.html

## Endpoints da API
- GET /api/veiculos — lista todos os veículos
- GET /api/veiculos/disponiveis — lista disponíveis ordenados por preço
- GET /api/veiculos/filtrar?marca=Toyota — filtro combinado
- POST /api/veiculos/{modeloId} — cadastrar veículo
- PUT /api/veiculos/{id} — atualizar veículo
- PATCH /api/veiculos/{id}/vender — marcar como vendido
- DELETE /api/veiculos/{id} — remover veículo

## Conceitos POO Aplicados
- Encapsulamento: atributos private com getters e setters
- Herança: VeiculoEletrico extends Veiculo
- Polimorfismo: @Override toString() em cada entidade
- Abstração: interfaces Repository e métodos de negócio
- Classes e Objetos: Marca, Modelo, Veiculo, StatusVeiculo
