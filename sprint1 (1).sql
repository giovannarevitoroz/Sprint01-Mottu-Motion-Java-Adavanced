-- ===============================
-- REMOVER TABELAS SE EXISTIREM
-- ===============================
DROP TABLE IF EXISTS MOVIMENTACAO;
DROP TABLE IF EXISTS VAGA;
DROP TABLE IF EXISTS SETOR;
DROP TABLE IF EXISTS GERENTE;
DROP TABLE IF EXISTS FUNCIONARIO;
DROP TABLE IF EXISTS MOTO;
DROP TABLE IF EXISTS CLIENTE;
DROP TABLE IF EXISTS PATIO;
DROP TABLE IF EXISTS CARGO;

-- ===============================
-- CRIAR TABELAS
-- ===============================

-- CLIENTE
CREATE TABLE CLIENTE (
    id_cliente BIGINT AUTO_INCREMENT PRIMARY KEY,
    telefone_cliente VARCHAR(11) NOT NULL,
    nome_cliente VARCHAR(100) NOT NULL,
    sexo_cliente CHAR(1) NOT NULL,
    email_cliente VARCHAR(100) NOT NULL,
    cpf_cliente VARCHAR(11) NOT NULL UNIQUE
);

-- PATIO
CREATE TABLE PATIO (
    id_patio BIGINT AUTO_INCREMENT PRIMARY KEY,
    localizacao_patio VARCHAR(100) NOT NULL,
    nome_patio VARCHAR(100) NOT NULL,
    descricao_patio VARCHAR(255)
);

-- CARGO
CREATE TABLE CARGO (
    id_cargo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_cargo VARCHAR(50) NOT NULL,
    descricao_cargo VARCHAR(255)
);

-- MOTO
CREATE TABLE MOTO (
    id_moto BIGINT AUTO_INCREMENT PRIMARY KEY,
    placa_moto VARCHAR(7) UNIQUE,
    modelo_moto VARCHAR(70) NOT NULL,
    situacao_moto VARCHAR(50) NOT NULL,
    chassi_moto VARCHAR(17) NOT NULL UNIQUE,
    cliente_id_cliente BIGINT,
    CONSTRAINT cliente_fk FOREIGN KEY (cliente_id_cliente) REFERENCES CLIENTE(id_cliente),
    CONSTRAINT chk_modelo_moto CHECK (modelo_moto IN ('Mottu Pop', 'Mottu Sport', 'Mottu-E')),
    CONSTRAINT chk_situacao_moto CHECK (situacao_moto IN ('Inativa', 'Ativa', 'Manutenção', 'Em Trânsito'))
);

-- SETOR
CREATE TABLE SETOR (
    id_setor BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_setor VARCHAR(70) NOT NULL,
    patio_id_patio BIGINT NOT NULL,
    status_setor VARCHAR(50) NOT NULL,
    CONSTRAINT patio_fk FOREIGN KEY (patio_id_patio) REFERENCES PATIO(id_patio),
    CONSTRAINT chk_tipo_setor CHECK (tipo_setor IN (
        'Pendência', 'Reparos Simples', 'Danos Estruturais Graves',
        'Motor Defeituoso', 'Agendada Para Manutenção', 'Pronta para Aluguel',
        'Sem Placa', 'Minha Mottu'
    )),
    CONSTRAINT chk_status_setor CHECK (status_setor IN ('Cheia', 'Parcial', 'Livre'))
);

-- VAGA
CREATE TABLE VAGA (
    id_vaga BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_vaga VARCHAR(10) NOT NULL,
    status_ocupada TINYINT(1) DEFAULT 0 NOT NULL,
    setor_id_setor BIGINT NOT NULL,
    CONSTRAINT setor_fk_vaga FOREIGN KEY (setor_id_setor) REFERENCES SETOR(id_setor),
    CONSTRAINT chk_status_ocupada CHECK (status_ocupada IN (0, 1))
);

-- MOVIMENTACAO
CREATE TABLE MOVIMENTACAO (
    id_movimentacao BIGINT AUTO_INCREMENT PRIMARY KEY,
    dt_entrada DATE NOT NULL,
    dt_saida DATE NULL,
    descricao_movimentacao VARCHAR(255),
    moto_id_moto BIGINT NOT NULL,
    vaga_id_vaga BIGINT NOT NULL,
    CONSTRAINT moto_fk FOREIGN KEY (moto_id_moto) REFERENCES MOTO(id_moto) ON DELETE CASCADE,
    CONSTRAINT vaga_fk FOREIGN KEY (vaga_id_vaga) REFERENCES VAGA(id_vaga) ON DELETE CASCADE
);

-- FUNCIONARIO
CREATE TABLE FUNCIONARIO (
    id_funcionario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_funcionario VARCHAR(100) NOT NULL,
    telefone_funcionario VARCHAR(11) NOT NULL,
    cargo_id_cargo BIGINT NOT NULL,
    patio_id_patio BIGINT NOT NULL,
    CONSTRAINT cargo_fk_funcionario FOREIGN KEY (cargo_id_cargo) REFERENCES CARGO(id_cargo),
    CONSTRAINT patio_fk_funcionario FOREIGN KEY (patio_id_patio) REFERENCES PATIO(id_patio)
);

-- GERENTE
CREATE TABLE GERENTE (
    id_gerente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_gerente VARCHAR(100) NOT NULL,
    telefone_gerente VARCHAR(11) NOT NULL,
    cpf_gerente VARCHAR(11) NOT NULL UNIQUE,
    patio_id_patio BIGINT NOT NULL UNIQUE,
    CONSTRAINT patio_fk_gerente FOREIGN KEY (patio_id_patio) REFERENCES PATIO(id_patio)
);

-- ===============================
-- INSERTS (ORDEM CORRETA)
-- ===============================

-- 1. CARGO (primeiro porque não tem dependências)
INSERT INTO CARGO (nome_cargo, descricao_cargo) VALUES 
('Auxiliar', 'Responsável por auxiliar nas tarefas gerais da empresa'),
('Mecânico', 'Responsável por realizar reparos e manutenções em motos'),
('Limpador', 'Responsável pela limpeza de áreas veículos'),
('Atendente', 'Atendimento ao cliente e suporte nas atividades da empresa'),
('Supervisor', 'Responsável por supervisionar as atividades operacionais'),
('Zelador', 'Responsável pela conservação e manutenção das instalações');

-- 2. CLIENTE (segundo porque MOTO depende dele)
INSERT INTO CLIENTE (telefone_cliente, nome_cliente, sexo_cliente, email_cliente, cpf_cliente) VALUES 
('11912345678', 'Carlos Silva', 'M', 'carlos@email.com', '12345678900'),
('11987654321', 'Maria Souza', 'F', 'maria@email.com', '23456789011'),
('1188887777', 'João Mendes', 'M', 'joao@email.com', '34567890122'),
('1177776666', 'Ana Paula', 'F', 'ana@email.com', '45678901233'),
('1166665555', 'Bruno Rocha', 'M', 'bruno@email.com', '56789012344'),
('1155554444', 'Juliana Lima', 'F', 'juliana@email.com', '67890123455'),
('1144443333', 'Pedro Costa', 'M', 'pedro@email.com', '78901234566'),
('1133332222', 'Fernanda Alves', 'F', 'fernanda@email.com', '89012345677'),
('1121111111', 'Lucas Martins', 'M', 'lucas@email.com', '90123456788'),
('1122222222', 'Aline Pereira', 'F', 'aline@email.com', '91234567899'),
('1123333333', 'Diego Ramos', 'M', 'diego@email.com', '92345678900'),
('1124444444', 'Bianca Lopes', 'F', 'bianca@email.com', '93456789011'),
('1125555555', 'Thiago Nunes', 'M', 'thiago@email.com', '94567890122'),
('1126666666', 'Marina Dias', 'F', 'marina@email.com', '95678901233'),
('1127777777', 'Eduardo Cunha', 'M', 'eduardo@email.com', '96789012344'),
('1128888888', 'Paula Reis', 'F', 'paula@email.com', '97890123455'),
('1129999999', 'André Barros', 'M', 'andre@email.com', '98901234566'),
('1130000000', 'Camila Torres', 'F', 'camila@email.com', '99012345677'),
('1131111111', 'Fábio Ferreira', 'M', 'fabio@email.com', '90123456778'),
('1132222222', 'Letícia Monteiro', 'F', 'leticia@email.com', '91234567889'),
('1133333333', 'Rafael Duarte', 'M', 'rafael@email.com', '92345678990'),
('1134444444', 'Natalia Gomes', 'F', 'natalia@email.com', '93456789000'),
('1135555555', 'Vinícius Cardoso', 'M', 'vinicius@email.com', '94567890111'),
('1136666666', 'Tatiane Rocha', 'F', 'tatiane@email.com', '95678901222'),
('1137777777', 'Roberto Meireles', 'M', 'roberto@email.com', '96789012333'),
('1138888888', 'Adriana Passos', 'F', 'adriana@email.com', '97890123444'),
('1139999999', 'Marcelo Silva', 'M', 'marcelo@email.com', '98901234555'),
('1140000000', 'Daniela Moraes', 'F', 'daniela@email.com', '99012345666'),
('1141111111', 'Fernando Pires', 'M', 'fernando@email.com', '90123456779'),
('1142222222', 'Patrícia Braga', 'F', 'patricia@email.com', '91234567880'),
('1143333333', 'Henrique Leal', 'M', 'henrique@email.com', '92345678991'),
('1144444444', 'Juliane Castro', 'F', 'juliane@email.com', '93456789002');

-- 3. PATIO (antes de SETOR e FUNCIONARIO/GERENTE)
INSERT INTO PATIO (localizacao_patio, nome_patio, descricao_patio) VALUES 
('Zona Norte', 'Pátio Norte', 'Área ampla e coberta'),
('Zona Sul', 'Pátio Sul', 'Coberto parcialmente'),
('Zona Leste', 'Pátio Leste', 'Perto da oficina'),
('Zona Oeste', 'Pátio Oeste', 'Com iluminação noturna'),
('Centro', 'Pátio Central', 'Mais movimentado'),
('Guarulhos', 'Pátio Aeroporto', 'Próximo ao aeroporto'),
('Osasco', 'Pátio Osasco', 'Área externa'),
('Santo André', 'Pátio ABC', 'Área nova');

-- 4. MOTO (depois de CLIENTE, antes de MOVIMENTACAO)
INSERT INTO MOTO (placa_moto, modelo_moto, situacao_moto, chassi_moto, cliente_id_cliente) VALUES 
('ABC1234', 'Mottu Pop', 'Em Trânsito', 'CHS12345678901234', 1),
('DEF5678', 'Mottu Sport', 'Em Trânsito', 'CHS22345678901234', 2),
('GHI9101', 'Mottu-E', 'Inativa', 'CHS32345678901234', 3),
('JKL2345', 'Mottu-E', 'Inativa', 'CHS42345678901234', 4),
('MNO6789', 'Mottu Pop', 'Em Trânsito', 'CHS52345678901234', 5),
('PQR1011', 'Mottu Sport', 'Em Trânsito', 'CHS62345678901234', 6),
('STU1213', 'Mottu Sport', 'Manutenção', 'CHS72345678901234', 7),
('VWX1415', 'Mottu Pop', 'Manutenção', 'CHS82345678901234', 8),
('AAA1111', 'Mottu Pop', 'Em Trânsito', 'CHS90000000000001', 9),
('AAB1112', 'Mottu-E', 'Em Trânsito', 'CHS90000000000002', 10),
('AAC1113', 'Mottu Sport', 'Manutenção', 'CHS90000000000003', 11),
('AAD2221', 'Mottu Pop', 'Manutenção', 'CHS90000000000004', 12),
('AAE2222', 'Mottu-E', 'Em Trânsito', 'CHS90000000000005', 13),
('AAF2223', 'Mottu Sport', 'Em Trânsito', 'CHS90000000000006', 14),
('AAG3331', 'Mottu-E', 'Manutenção', 'CHS90000000000007', 15),
('AAH3332', 'Mottu Pop', 'Manutenção', 'CHS90000000000008', 16),
('AAI3333', 'Mottu Sport', 'Em Trânsito', 'CHS90000000000009', 17),
('AAJ4441', 'Mottu-E', 'Em Trânsito', 'CHS90000000000010', 18),
('AAK4442', 'Mottu Pop', 'Inativa', 'CHS90000000000011', 19),
('AAL4443', 'Mottu Sport', 'Inativa', 'CHS90000000000012', 20),
('AAM5551', 'Mottu Sport', 'Em Trânsito', 'CHS90000000000013', 21),
('AAN5552', 'Mottu-E', 'Em Trânsito', 'CHS90000000000014', 22),
('AAO5553', 'Mottu Pop', 'Ativa', 'CHS90000000000015', 23),
('AAP6661', 'Mottu Sport', 'Ativa', 'CHS90000000000016', 24),
('AAQ6662', 'Mottu-E', 'Em Trânsito', 'CHS90000000000017', 25),
('AAR6663', 'Mottu Pop', 'Em Trânsito', 'CHS90000000000018', 26),
('AAS7771', 'Mottu Pop', 'Inativa', 'CHS90000000000019', 27),
('AAT7772', 'Mottu-E', 'Inativa', 'CHS90000000000020', 28),
('AAU7773', 'Mottu Sport', 'Em Trânsito', 'CHS90000000000021', 29),
('AAV8881', 'Mottu Sport', 'Em Trânsito', 'CHS90000000000022', 30),
('AAW8882', 'Mottu Pop', 'Ativa', 'CHS90000000000023', 31),
('AAX8883', 'Mottu-E', 'Ativa', 'CHS90000000000024', 32),
('AAX8884', 'Mottu-E', 'Ativa', 'CHS90000000000025', 32);

-- 5. SETOR (depois de PATIO, antes de VAGA)
INSERT INTO SETOR (tipo_setor, patio_id_patio, status_setor) VALUES 
('Pendência', 1, 'Parcial'),
('Reparos Simples', 1, 'Parcial'),
('Danos Estruturais Graves', 1, 'Parcial'),
('Motor Defeituoso', 1, 'Parcial'),
('Agendada Para Manutenção', 1, 'Parcial'),
('Pronta para Aluguel', 1, 'Parcial'),
('Sem Placa', 1, 'Parcial'),
('Minha Mottu', 1, 'Parcial');

-- 6. VAGA (depois de SETOR, antes de MOVIMENTACAO)
INSERT INTO VAGA (numero_vaga, status_ocupada, setor_id_setor) VALUES 
('A1-V1', 0, 1), ('A1-V2', 0, 1), ('A1-V3', 1, 1), ('A1-V4', 1, 1),
('A2-V1', 0, 2), ('A2-V2', 0, 2), ('A2-V3', 1, 2), ('A2-V4', 1, 2),
('A3-V1', 0, 3), ('A3-V2', 0, 3), ('A3-V3', 1, 3), ('A3-V4', 1, 3),
('A4-V1', 0, 4), ('A4-V2', 0, 4), ('A4-V3', 1, 4), ('A4-V4', 1, 4),
('A5-V1', 0, 5), ('A5-V2', 0, 5), ('A5-V3', 1, 5), ('A5-V4', 1, 5),
('A6-V1', 0, 6), ('A6-V2', 0, 6), ('A6-V3', 1, 6), ('A6-V4', 1, 6),
('A7-V1', 0, 7), ('A7-V2', 0, 7), ('A7-V3', 1, 7), ('A7-V4', 1, 7),
('A8-V1', 0, 8), ('A8-V2', 0, 8), ('A8-V3', 1, 8), ('A8-V4', 1, 8), ('A8-V5', 1, 8);

INSERT INTO MOVIMENTACAO 
(dt_entrada, dt_saida, descricao_movimentacao, moto_id_moto, vaga_id_vaga) 
VALUES 
('2025-01-02 00:00:00', '2025-01-03 00:00:00', 'Aguardando liberação', 1, 1),
('2025-01-04 00:00:00', '2025-01-05 00:00:00', 'Em análise documental', 2, 2),
('2025-01-06 00:00:00', NULL, 'Aguardando vistoria', 3, 3),
('2025-01-07 00:00:00', NULL, 'Pendência com cliente', 4, 4),
('2025-01-08 00:00:00', '2025-01-09 00:00:00', 'Revisão preventiva', 5, 5),
('2025-01-10 00:00:00', '2025-01-11 00:00:00', 'Troca de óleo', 6, 6),
('2025-01-12 00:00:00', NULL, 'Troca de pneu', 7, 7),
('2025-01-13 00:00:00', NULL, 'Correção de freio', 8, 8),
('2025-01-14 00:00:00', '2025-01-15 00:00:00', 'Colisão frontal', 9, 9),
('2025-01-16 00:00:00', '2025-01-17 00:00:00', 'Chassi danificado', 10, 10);

-- 8. FUNCIONARIO (depois de CARGO e PATIO) - CORRIGIDO: só IDs de 1 a 6
INSERT INTO FUNCIONARIO (nome_funcionario, telefone_funcionario, cargo_id_cargo, patio_id_patio) VALUES 
('Ricardo Ramos', '11911112222', 1, 1),
('Tatiane Luz', '11922223333', 2, 2),
('Lucas Moraes', '11933334444', 3, 3),
('Vanessa Souza', '11944445555', 4, 4),
('Eduardo Lima', '11955556666', 5, 5),
('Paula Teixeira', '11966667777', 6, 6);

-- 9. GERENTE (depois de PATIO)
INSERT INTO GERENTE (nome_gerente, telefone_gerente, cpf_gerente, patio_id_patio) VALUES 
('Rodrigo Neves', '11900001111', '99999999900', 1),
('Carla Pires', '11900002222', '88888888801', 2),
('Fernando Lopes', '11900003333', '77777777702', 3),
('Marina Dias', '11900004444', '66666666603', 4),
('Bruno Araújo', '11900005555', '55555555504', 5),
('Isabela Freitas', '11900006666', '44444444405', 6),
('Tiago Faria', '11900007777', '33333333306', 7),
('Luciana Prado', '11900008888', '22222222207', 8);