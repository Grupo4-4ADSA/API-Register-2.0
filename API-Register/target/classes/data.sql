-- Tabela Company - Empresa
insert into Empresa (id_empresa, razao_social, cnpj, telefone, email, data_abertura, ativa)
values (2000, 'Clarice e Lorena Contábil ME', '40298826000101', '1129361399',
'estoque@clariceelorenacontabilme.com.br', '2022-03-12', true);

insert into Empresa (id_empresa, razao_social, cnpj, telefone, email, data_abertura, ativa)
values (2001,'São Paulo Tech School', '07165496000100', '1135894043',
'atendimento@sptech.school', '2022-02-12', true);

-- Tabela Gestor - Gestor
insert into Gestor (id_gestor, nome_gestor, login, senha, fk_empresa) values (500, 'Lorena Machado',
'lmachado@oncln.com', '#Tortalaranja123', 2000);

insert into Gestor (id_gestor, nome_gestor, login, senha, fk_empresa) values (501, 'Marcos Santos',
'msantos@oncln.com', '#Tortalaranja123', 2001);

-- Tabela Building - Prédio
insert into Predio (id_predio, nome_predio, fk_empresa) values (250, 'Centro Empresarial', 2000);
insert into Predio (id_predio, nome_predio, fk_empresa) values (251, 'Digital Building', 2001);


-- Tabela Address - Endereco
insert into Endereco (logradouro, numero, bairro, cidade, uf, cep, fk_predio) values
('Rua Tiugibe Inoue', 251, 'Cidade dos Bandeirantes', 'São Paulo', 'SP', '05596060',250);

insert into Endereco (logradouro, numero, bairro, cidade, uf, cep, fk_predio) values
('Rua Haddock Lobo', 595, 'Cerqueira César', 'São Paulo', 'SP', '01414905', 251);

-- Tabela Room - Sala
insert into Sala (nome, andar, fk_predio) values ('Sala de reuniões', 2, 250);
insert into Sala (nome, andar, fk_predio) values ('Recursos Humanos', 4, 250);
insert into Sala (nome, andar, fk_predio) values ('A', 1, 251);
insert into Sala (nome, andar, fk_predio) values ('B', 1, 251);

-- Tabela Scheduling - Agendamento
insert into Agendamento (data, horario, ligar, fk_sala) values ('2022-05-20', '07:30:00', true, 1);
insert into Agendamento (data, horario, ligar, fk_sala) values ('2022-05-20', '12:00:00', false, 1);
insert into Agendamento (data, horario, ligar, fk_sala) values ('2022-05-20', '09:00:00', true, 2);
insert into Agendamento (data, horario, ligar, fk_sala) values ('2022-05-20', '17:00:00', false, 2);
insert into Agendamento (data, horario, ligar, fk_sala) values ('2022-05-20', '16:30:00', true, 3);
insert into Agendamento (data, horario, ligar, fk_sala) values ('2022-05-20', '21:45:00', false, 3);
insert into Agendamento (data, horario, ligar, fk_sala) values ('2022-05-20', '16:30:00', true, 4);
insert into Agendamento (data, horario, ligar, fk_sala) values ('2022-05-20', '21:45:00', false, 4);

-- Tabela CLNBox
insert into CLNBox (qr_code, ip, fk_sala) values ('1-box-contabilidade-sala-de-reuniao', '97.178.103.78', 1);
insert into CLNBox (qr_code, ip, fk_sala) values ('2-box-contabilidade-sala-rh', '20.69.203.245', 2);
insert into CLNBox (qr_code, ip, fk_sala) values ('1-box-faculdade-sala-1a', '3.242.208.236', 3);
insert into CLNBox (qr_code, ip, fk_sala) values ('2-box-faculdade-sala-1b', '206.131.15.178', 4);

-- Tabela Equipment - Equipamento
insert into Equipamento (nome, tipo, instalacao, vida_util, potencia, qtd_equipamento, porta, fk_Clnbox)
values ('Springer Midea Inverter 12.000 Btus','Ar-condicionado', '2022-03-25', 3650,
220, 1, 1, 1);
insert into Equipamento (nome, tipo, instalacao, vida_util, potencia, qtd_equipamento, porta, fk_Clnbox)
values ('Springer Midea Inverter 12.000 Btus','Ar-condicionado', '2022-03-25', 3650,
220, 2, 5, 2);
insert into Equipamento (nome, tipo, instalacao, vida_util, potencia, qtd_equipamento, porta, fk_Clnbox)
values ('Springer Midea Inverter 12.000 Btus','Ar-condicionado', '2022-03-25', 3650,
220, 2, 5, 2);
insert into Equipamento (nome, tipo, instalacao, vida_util, potencia, qtd_equipamento, porta, fk_Clnbox)
values ('Lampada LED Philips','Conjunto de Lampadas', '2022-03-25', 2000,
220, 4, 1, 1);
insert into Equipamento (nome, tipo, instalacao, vida_util, potencia, qtd_equipamento, porta, fk_Clnbox)
values ('Lampada LED Philips','Conjunto de Lampadas', '2022-03-25', 2000,
220, 12, 5, 2);

insert into Equipamento (nome, tipo, instalacao, vida_util, potencia, qtd_equipamento, porta, fk_Clnbox)
values ('Split LG Dual Inverter Voice ARTCOOL UV Nano','Ar-condicionado', '2022-03-01', 3650,
220, 2, 1, 3);
insert into Equipamento (nome, tipo, instalacao, vida_util, potencia, qtd_equipamento, porta, fk_Clnbox)
values ('Split LG Dual Inverter Voice ARTCOOL UV Nano','Ar-condicionado', '2022-03-01', 3650,
220, 2, 5, 3);
insert into Equipamento (nome, tipo, instalacao, vida_util, potencia, qtd_equipamento, porta, fk_Clnbox)
values ('Split LG Dual Inverter Voice ARTCOOL UV Nano','Ar-condicionado', '2022-03-01', 3650,
220, 2, 1, 4);
insert into Equipamento (nome, tipo, instalacao, vida_util, potencia, qtd_equipamento, porta, fk_Clnbox)
values ('Split LG Dual Inverter Voice ARTCOOL UV Nano','Ar-condicionado', '2022-03-01', 3650,
220, 2, 5, 4);

insert into Equipamento (nome, tipo, instalacao, vida_util, potencia, qtd_equipamento, porta, fk_Clnbox)
values ('Lampada LED Philips','Conjunto de Lampadas', '2022-03-01', 2000, 220, 20, 5, 3);
insert into Equipamento (nome, tipo, instalacao, vida_util, potencia, qtd_equipamento, porta, fk_Clnbox)
values ('Lampada LED Philips','Conjunto de Lampadas', '2022-03-01', 2000, 220, 20, 1, 4);

-- Tabela Register - Registro
insert into Registro (fk_equipamento, ligado, data) values (1, true, '2022-05-20 07:30:00');
insert into Registro (fk_equipamento, ligado, data) values (2, true, '2022-05-20 09:00:00');
insert into Registro (fk_equipamento, ligado, data) values (2, true, '2022-05-20 09:00:00');
insert into Registro (fk_equipamento, ligado, data) values (3, true, '2022-05-20 09:00:00');
insert into Registro (fk_equipamento, ligado, data) values (4, true, '2022-05-20 07:30:00');
insert into Registro (fk_equipamento, ligado, data) values (5, true, '2022-05-20 09:00:00');
insert into Registro (fk_equipamento, ligado, data) values (6, true, '2022-05-20 16:30:00');
insert into Registro (fk_equipamento, ligado, data) values (7, true, '2022-05-20 16:30:00');
insert into Registro (fk_equipamento, ligado, data) values (8, true, '2022-05-20 16:30:00');
insert into Registro (fk_equipamento, ligado, data) values (9, true, '2022-05-20 16:30:00');
insert into Registro (fk_equipamento, ligado, data) values (10, true, '2022-05-20 16:30:00');
insert into Registro (fk_equipamento, ligado, data) values (11, true, '2022-05-20 16:30:00');

insert into Registro (fk_equipamento, ligado, data) values (1, false, '2022-05-21 07:30:00');
insert into Registro (fk_equipamento, ligado, data) values (2, false, '2022-05-21 09:00:00');
insert into Registro (fk_equipamento, ligado, data) values (2, false, '2022-05-21 09:00:00');
insert into Registro (fk_equipamento, ligado, data) values (3, false, '2022-05-21 09:00:00');
insert into Registro (fk_equipamento, ligado, data) values (4, false, '2022-05-21 07:30:00');
insert into Registro (fk_equipamento, ligado, data) values (5, false, '2022-05-21 09:00:00');
insert into Registro (fk_equipamento, ligado, data) values (6, false, '2022-05-21 16:30:00');
insert into Registro (fk_equipamento, ligado, data) values (7, false, '2022-05-21 16:30:00');
insert into Registro (fk_equipamento, ligado, data) values (8, false, '2022-05-21 16:30:00');
insert into Registro (fk_equipamento, ligado, data) values (9, false, '2022-05-21 16:30:00');
insert into Registro (fk_equipamento, ligado, data) values (10,false, '2022-05-21 16:30:00');
insert into Registro (fk_equipamento, ligado, data) values (11,false, '2022-05-21 16:30:00');

-- Tabela TemperatureSensor - SensorTemperatura
insert into sensor_temperatura (temperatura, data, fk_equipamento) values (21.0 , '2022-05-20 10:30:00', 1);
insert into sensor_temperatura (temperatura, data, fk_equipamento) values (21.0, '2022-05-20 10:35:00', 2);
insert into sensor_temperatura (temperatura, data, fk_equipamento) values (20.0, '2022-05-20 10:35:00', 3);
insert into sensor_temperatura (temperatura, data, fk_equipamento) values (19.0, '2022-05-20 17:45:00', 6);
insert into sensor_temperatura (temperatura, data, fk_equipamento) values (21.0, '2022-05-20 17:35:00', 7);
insert into sensor_temperatura (temperatura, data, fk_equipamento) values (20.0, '2022-05-20 18:36:00', 8);
insert into sensor_temperatura (temperatura, data, fk_equipamento) values (19.0, '2022-05-20 18:35:00', 9);

-- Tabela RateValue - ValorTarifa
insert into valor_tarifa (bandeira, valor_tarifa_kwh, data) values ('Escassez Hídrica', 14.20, '2022-01-01 00:00:00');
insert into valor_tarifa (bandeira, valor_tarifa_kwh, data) values ('Amarela', 1.87, '2022-02-01 00:00:00');
insert into valor_tarifa (bandeira, valor_tarifa_kwh, data) values ('Vermelha - Patamar 1', 3.97, '2022-03-01 00:00:00');
insert into valor_tarifa (bandeira, valor_tarifa_kwh, data) values ('Vermelha - Patamar 2', 9.49, '2022-04-01 00:00:00');
insert into valor_tarifa (bandeira, valor_tarifa_kwh, data) values ('Verde', 0.0, '2022-05-01 00:00:00');
