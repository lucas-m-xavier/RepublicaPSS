use bdrepublica;

show tables;

drop database bdrepublica;

select * from republica;

select * from endereco;

select * from geolocalizacao;

select * from UsuarioLogin;

INSERT INTO Usuario(nome, apelido, telefone, cpf, linkSociais, responsavelUM, responsavelDois) values ("lucas", "l", "33333333", "3333333", "social", "11111111", "222222222");

INSERT INTO UsuarioLogin(email, senha) values ("admin", "admin");

INSERT INTO GeoLocalizacao(latitude, longitude) values ("123","123");

INSERT INTO Endereco(cEP, bairro, logradouro, numero, referencia, uf, idGeoLocalizacao) values("1234", "wegwh", "wergwrh", 23, "qegqwe", 1, 1);

INSERT INTO Republica (nome, dataFundacao, vantagens, despesasMedias, vagasTotais, vagasOcupadas, saldoTotal, codEtica, idEndereco)
				values ("astolfo", "2000-10-10", "as", 200.10, 6, 1, 0, "asfqegf", 1);
                
SELECT MAX(idGeoLocalizacao) FROM GeoLocalizacao;

