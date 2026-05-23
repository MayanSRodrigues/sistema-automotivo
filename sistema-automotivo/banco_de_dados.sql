-- =============================================================
-- SCRIPT SQL — Sistema Automotivo: Gestão de Estoque de Veículos
-- Banco de Dados: MySQL 8+
-- Execute este script para criar e popular o banco de dados
-- =============================================================

-- Cria e seleciona o banco
CREATE DATABASE IF NOT EXISTS sistema_automotivo
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE sistema_automotivo;

-- =============================================================
-- TABELA: marcas
-- =============================================================
CREATE TABLE IF NOT EXISTS marcas (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    nome        VARCHAR(50)  NOT NULL UNIQUE,
    pais_origem VARCHAR(50),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================================
-- TABELA: modelos
-- =============================================================
CREATE TABLE IF NOT EXISTS modelos (
    id        BIGINT      NOT NULL AUTO_INCREMENT,
    nome      VARCHAR(80) NOT NULL,
    categoria VARCHAR(30),
    marca_id  BIGINT      NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_modelo_marca FOREIGN KEY (marca_id)
        REFERENCES marcas(id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================================
-- TABELA: veiculos
-- =============================================================
CREATE TABLE IF NOT EXISTS veiculos (
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    placa         VARCHAR(10)  UNIQUE,
    ano           INT          NOT NULL,
    cor           VARCHAR(30),
    preco         DOUBLE       NOT NULL,
    quilometragem INT          DEFAULT 0,
    status        VARCHAR(20)  NOT NULL DEFAULT 'DISPONIVEL',
    modelo_id     BIGINT       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_veiculo_modelo FOREIGN KEY (modelo_id)
        REFERENCES modelos(id) ON DELETE RESTRICT,
    CONSTRAINT chk_status CHECK (status IN
        ('DISPONIVEL','VENDIDO','RESERVADO','EM_REVISAO')),
    CONSTRAINT chk_preco  CHECK (preco > 0),
    CONSTRAINT chk_km     CHECK (quilometragem >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================================
-- DADOS DE EXEMPLO — Marcas
-- =============================================================
INSERT INTO marcas (nome, pais_origem) VALUES
    ('Toyota',      'Japão'),
    ('Honda',       'Japão'),
    ('Volkswagen',  'Alemanha'),
    ('Chevrolet',   'Estados Unidos'),
    ('Hyundai',     'Coreia do Sul'),
    ('Fiat',        'Itália');

-- =============================================================
-- DADOS DE EXEMPLO — Modelos
-- =============================================================
INSERT INTO modelos (nome, categoria, marca_id) VALUES
    ('Corolla',   'SEDAN',   1),  -- Toyota
    ('Hilux',     'PICKUP',  1),  -- Toyota
    ('Civic',     'SEDAN',   2),  -- Honda
    ('HR-V',      'SUV',     2),  -- Honda
    ('Gol',       'HATCH',   3),  -- Volkswagen
    ('T-Cross',   'SUV',     3),  -- Volkswagen
    ('Onix',      'HATCH',   4),  -- Chevrolet
    ('Tracker',   'SUV',     4),  -- Chevrolet
    ('HB20',      'HATCH',   5),  -- Hyundai
    ('Creta',     'SUV',     5),  -- Hyundai
    ('Pulse',     'SUV',     6),  -- Fiat
    ('Strada',    'PICKUP',  6);  -- Fiat

-- =============================================================
-- DADOS DE EXEMPLO — Veículos
-- =============================================================
INSERT INTO veiculos (placa, ano, cor, preco, quilometragem, status, modelo_id) VALUES
    ('ABC1D23', 2022, 'Prata',    95000.00,  15000, 'DISPONIVEL', 1),  -- Corolla
    ('DEF2E34', 2021, 'Branco',  110000.00,  30000, 'DISPONIVEL', 1),  -- Corolla
    ('GHI3F45', 2023, 'Preto',   195000.00,   5000, 'DISPONIVEL', 2),  -- Hilux
    ('JKL4G56', 2022, 'Cinza',    88000.00,  22000, 'DISPONIVEL', 3),  -- Civic
    ('MNO5H67', 2023, 'Vermelho', 98000.00,   8000, 'RESERVADO',  4),  -- HR-V
    ('PQR6I78', 2020, 'Azul',     42000.00,  60000, 'DISPONIVEL', 5),  -- Gol
    ('STU7J89', 2023, 'Branco',   95000.00,  12000, 'DISPONIVEL', 6),  -- T-Cross
    ('VWX8K90', 2021, 'Prata',    58000.00,  35000, 'DISPONIVEL', 7),  -- Onix
    ('YZA9L01', 2022, 'Preto',    92000.00,  18000, 'VENDIDO',    8),  -- Tracker
    ('BCD0M12', 2023, 'Branco',   68000.00,   3000, 'DISPONIVEL', 9),  -- HB20
    ('EFG1N23', 2022, 'Cinza',    95000.00,  25000, 'DISPONIVEL', 10), -- Creta
    ('HIJ2O34', 2023, 'Vermelho', 82000.00,   9000, 'DISPONIVEL', 11); -- Pulse

-- =============================================================
-- CONSULTAS ÚTEIS PARA DEMONSTRAÇÃO
-- =============================================================

-- Ver todos os veículos disponíveis com marca e modelo
SELECT
    v.id,
    m.nome AS marca,
    mo.nome AS modelo,
    mo.categoria,
    v.ano,
    v.cor,
    v.preco,
    v.quilometragem,
    v.status
FROM veiculos v
JOIN modelos mo ON v.modelo_id = mo.id
JOIN marcas  m  ON mo.marca_id = m.id
WHERE v.status = 'DISPONIVEL'
ORDER BY v.preco ASC;

-- Filtrar por faixa de preço
SELECT v.*, mo.nome AS modelo, m.nome AS marca
FROM veiculos v
JOIN modelos mo ON v.modelo_id = mo.id
JOIN marcas  m  ON mo.marca_id = m.id
WHERE v.preco BETWEEN 50000 AND 100000
  AND v.status = 'DISPONIVEL';

-- Contar veículos por status
SELECT status, COUNT(*) AS quantidade
FROM veiculos
GROUP BY status;
