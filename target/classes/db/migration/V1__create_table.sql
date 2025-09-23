DROP TABLE IF EXISTS movimentacao;
DROP TABLE IF EXISTS vaga;
DROP TABLE IF EXISTS setor;
DROP TABLE IF EXISTS gerente;
DROP TABLE IF EXISTS funcionario;
DROP TABLE IF EXISTS moto;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS patio;
DROP TABLE IF EXISTS cargo;

-- cliente
CREATE TABLE cliente (
    id_cliente BIGINT AUTO_INCREMENT PRIMARY KEY,
    telefone_cliente VARCHAR(11) NOT NULL,
    nome_cliente VARCHAR(100) NOT NULL,
    sexo_cliente CHAR(1) NOT NULL,
    email_cliente VARCHAR(100) NOT NULL,
    cpf_cliente VARCHAR(11) NOT NULL UNIQUE
);

-- patio
CREATE TABLE patio (
    id_patio BIGINT AUTO_INCREMENT PRIMARY KEY,
    localizacao_patio VARCHAR(100) NOT NULL,
    nome_patio VARCHAR(100) NOT NULL,
    descricao_patio VARCHAR(255)
);

-- cargo
CREATE TABLE cargo (
    id_cargo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_cargo VARCHAR(50) NOT NULL,
    descricao_cargo VARCHAR(255)
);

-- moto
CREATE TABLE moto (
    id_moto BIGINT AUTO_INCREMENT PRIMARY KEY,
    placa_moto VARCHAR(7) UNIQUE,
    modelo_moto VARCHAR(70) NOT NULL,
    situacao_moto VARCHAR(50) NOT NULL,
    chassi_moto VARCHAR(17) NOT NULL UNIQUE,
    cliente_id_cliente BIGINT,
    CONSTRAINT cliente_fk FOREIGN KEY (cliente_id_cliente) REFERENCES cliente(id_cliente),
    CONSTRAINT chk_modelo_moto CHECK (modelo_moto IN ('Mottu Pop', 'Mottu Sport', 'Mottu-E')),
    CONSTRAINT chk_situacao_moto CHECK (situacao_moto IN ('Inativa', 'Ativa', 'Manutenção', 'Em Trânsito'))
);

-- setor
CREATE TABLE setor (
    id_setor BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_setor VARCHAR(70) NOT NULL,
    patio_id_patio BIGINT NOT NULL,
    status_setor VARCHAR(50) NOT NULL,
    CONSTRAINT patio_fk FOREIGN KEY (patio_id_patio) REFERENCES patio(id_patio),
    CONSTRAINT chk_tipo_setor CHECK (tipo_setor IN (
        'Pendência', 'Reparos Simples', 'Danos Estruturais Graves',
        'Motor Defeituoso', 'Agendada Para Manutenção', 'Pronta para Aluguel',
        'Sem Placa', 'Minha Mottu'
    )),
    CONSTRAINT chk_status_setor CHECK (status_setor IN ('Cheia', 'Parcial', 'Livre'))
);

-- vaga
CREATE TABLE vaga (
    id_vaga BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_vaga VARCHAR(10) NOT NULL,
    status_ocupada TINYINT(1) DEFAULT 0 NOT NULL,
    setor_id_setor BIGINT NOT NULL,
    CONSTRAINT setor_fk_vaga FOREIGN KEY (setor_id_setor) REFERENCES setor(id_setor),
    CONSTRAINT chk_status_ocupada CHECK (status_ocupada IN (0, 1))
);

-- movimentacao
CREATE TABLE movimentacao (
    id_movimentacao BIGINT AUTO_INCREMENT PRIMARY KEY,
    dt_entrada DATE NOT NULL,
    dt_saida DATE NULL,
    descricao_movimentacao VARCHAR(255),
    moto_id_moto BIGINT NOT NULL,
    vaga_id_vaga BIGINT NOT NULL,
    CONSTRAINT moto_fk FOREIGN KEY (moto_id_moto) REFERENCES moto(id_moto) ON DELETE CASCADE,
    CONSTRAINT vaga_fk FOREIGN KEY (vaga_id_vaga) REFERENCES vaga(id_vaga) ON DELETE CASCADE
);

-- funcionario
CREATE TABLE funcionario (
    id_funcionario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_funcionario VARCHAR(100) NOT NULL,
    telefone_funcionario VARCHAR(11) NOT NULL,
    cargo_id_cargo BIGINT NOT NULL,
    patio_id_patio BIGINT NOT NULL,
    CONSTRAINT cargo_fk_funcionario FOREIGN KEY (cargo_id_cargo) REFERENCES cargo(id_cargo),
    CONSTRAINT patio_fk_funcionario FOREIGN KEY (patio_id_patio) REFERENCES patio(id_patio)
);

-- gerente
CREATE TABLE gerente (
    id_gerente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_gerente VARCHAR(100) NOT NULL,
    telefone_gerente VARCHAR(11) NOT NULL,
    cpf_gerente VARCHAR(11) NOT NULL UNIQUE,
    patio_id_patio BIGINT NOT NULL UNIQUE,
    CONSTRAINT patio_fk_gerente FOREIGN KEY (patio_id_patio) REFERENCES patio(id_patio)
);

CREATE TABLE USUARIO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);