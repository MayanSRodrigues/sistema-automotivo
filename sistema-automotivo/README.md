# 🚗 Sistema Automotivo — Gestão de Estoque de Veículos
**Disciplina:** Programação Orientada a Objetos | **Instituição:** UniFECAF

---

## 📋 Sobre o Projeto

Sistema CRUD em Java com Spring Boot para gerenciamento de estoque de veículos em concessionárias. Permite cadastrar, consultar, atualizar e remover veículos, marcas e modelos, com filtros por marca, modelo, preço, ano e status.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia      | Versão | Função                          |
|-----------------|--------|---------------------------------|
| Java            | 17     | Linguagem principal             |
| Spring Boot     | 3.2.0  | Framework backend               |
| Spring Data JPA | 3.2.0  | Persistência / ORM              |
| MySQL           | 8+     | Banco de dados relacional       |
| Maven           | 3.9+   | Gerenciador de dependências     |

---

## 🏗️ Arquitetura MVC

```
src/main/java/com/automotivo/
├── SistemaAutomotivoApplication.java   ← Classe principal
├── model/
│   ├── Marca.java                      ← Entidade Marca
│   ├── Modelo.java                     ← Entidade Modelo
│   ├── Veiculo.java                    ← Entidade Veiculo
│   └── StatusVeiculo.java              ← Enum de status
├── repository/
│   ├── MarcaRepository.java            ← Acesso ao banco (Marca)
│   ├── ModeloRepository.java           ← Acesso ao banco (Modelo)
│   └── VeiculoRepository.java          ← Acesso ao banco + filtros
├── service/
│   ├── MarcaService.java               ← Regras de negócio (Marca)
│   ├── ModeloService.java              ← Regras de negócio (Modelo)
│   └── VeiculoService.java             ← Regras de negócio (Veículo)
├── controller/
│   ├── MarcaController.java            ← Endpoints REST (Marca)
│   ├── ModeloController.java           ← Endpoints REST (Modelo)
│   └── VeiculoController.java          ← Endpoints REST (Veículo)
└── exception/
    ├── RecursoNaoEncontradoException.java
    └── GlobalExceptionHandler.java
```

---

## ⚙️ Como Executar

### Pré-requisitos
- JDK 17 instalado
- MySQL 8+ rodando localmente
- Maven 3.9+ (ou use o wrapper `./mvnw`)

### Passos

**1. Clone o repositório**
```bash
git clone https://github.com/SEU_USUARIO/sistema-automotivo.git
cd sistema-automotivo
```

**2. Configure o banco de dados**

Abra `src/main/resources/application.properties` e altere:
```properties
spring.datasource.username=root
spring.datasource.password=SUA_SENHA_AQUI
```

**3. Crie o banco e insira dados de exemplo**
```bash
mysql -u root -p < banco_de_dados.sql
```

**4. Execute a aplicação**
```bash
./mvnw spring-boot:run
# ou: mvn spring-boot:run
```

**5. Teste no navegador ou Postman**
```
http://localhost:8080/api/veiculos
```

---

## 🌐 Endpoints da API

### Marcas — `/api/marcas`
| Método | URL              | Descrição         |
|--------|------------------|-------------------|
| GET    | /api/marcas      | Listar todas      |
| GET    | /api/marcas/{id} | Buscar por ID     |
| POST   | /api/marcas      | Cadastrar         |
| PUT    | /api/marcas/{id} | Atualizar         |
| DELETE | /api/marcas/{id} | Remover           |

### Modelos — `/api/modelos`
| Método | URL                           | Descrição              |
|--------|-------------------------------|------------------------|
| GET    | /api/modelos                  | Listar todos           |
| GET    | /api/modelos/{id}             | Buscar por ID          |
| GET    | /api/modelos/por-marca/{id}   | Listar por marca       |
| POST   | /api/modelos/{marcaId}        | Cadastrar              |
| PUT    | /api/modelos/{id}             | Atualizar              |
| DELETE | /api/modelos/{id}             | Remover                |

### Veículos — `/api/veiculos`
| Método | URL                          | Descrição                         |
|--------|------------------------------|-----------------------------------|
| GET    | /api/veiculos                | Listar todos                      |
| GET    | /api/veiculos/disponiveis    | Listar disponíveis (por preço)    |
| GET    | /api/veiculos/{id}           | Buscar por ID                     |
| GET    | /api/veiculos/filtrar        | **Filtro combinado** (ver abaixo) |
| POST   | /api/veiculos/{modeloId}     | Cadastrar                         |
| PUT    | /api/veiculos/{id}           | Atualizar (preço, km, status)     |
| PATCH  | /api/veiculos/{id}/vender    | Marcar como vendido               |
| DELETE | /api/veiculos/{id}           | Remover permanentemente           |

### Filtro Combinado
```
GET /api/veiculos/filtrar?marca=Toyota&precoMax=100000&status=DISPONIVEL
GET /api/veiculos/filtrar?anoMin=2020&anoMax=2023&precoMin=50000
```
Parâmetros disponíveis: `marca`, `modelo`, `status`, `anoMin`, `anoMax`, `precoMin`, `precoMax`

---

## 📦 Exemplos de JSON

### Cadastrar Marca
```json
POST /api/marcas
{
  "nome": "Toyota",
  "paisOrigem": "Japão"
}
```

### Cadastrar Veículo
```json
POST /api/veiculos/1
{
  "placa": "ABC1D23",
  "ano": 2023,
  "cor": "Prata",
  "preco": 95000.00,
  "quilometragem": 0
}
```

### Atualizar Preço e Status
```json
PUT /api/veiculos/1
{
  "preco": 89000.00,
  "status": "RESERVADO"
}
```

---

## 🎓 Conceitos POO Aplicados

- **Encapsulamento**: atributos `private` com getters/setters em todas as entidades
- **Herança**: `VeiculoEletrico extends Veiculo` (demonstrado no relatório)
- **Polimorfismo**: `@Override toString()` em cada entidade; tratamento polimórfico de exceções
- **Abstração**: interfaces `Repository`; métodos de negócio como `marcarComoVendido()`
- **Classes e Objetos**: `Marca`, `Modelo`, `Veiculo`, `StatusVeiculo`
