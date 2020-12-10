-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bdrepublica
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdrepublica
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdrepublica` DEFAULT CHARACTER SET utf8 ;
USE `bdrepublica` ;

-- -----------------------------------------------------
-- Table `bdrepublica`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(11) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `apelido` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `linkSociais` VARCHAR(100) NOT NULL,
  `responsavelUm` VARCHAR(100) NOT NULL,
  `responsavelDois` VARCHAR(100) NOT NULL,
  `estado` VARCHAR(45) NOT NULL DEFAULT 'Sem Teto',
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUsuario` ASC) VISIBLE,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`feedback` (
  `idFeedback` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `dataCriacao` DATE NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `dataSolucao` DATE NOT NULL,
  `excluida` TINYINT NOT NULL DEFAULT '0',
  `concluida` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`idFeedback`, `idUsuario`),
  UNIQUE INDEX `idFeedback_UNIQUE` (`idFeedback` ASC) VISIBLE,
  INDEX `fk_Feedback_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Feedback_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `bdrepublica`.`usuario` (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`denuncia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`denuncia` (
  `idFeedback` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`idFeedback`, `idUsuario`),
  INDEX `fk_Feedback_has_Usuario_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  INDEX `fk_Feedback_has_Usuario_Feedback1_idx` (`idFeedback` ASC) VISIBLE,
  CONSTRAINT `fk_Feedback_has_Usuario_Feedback1`
    FOREIGN KEY (`idFeedback`)
    REFERENCES `bdrepublica`.`feedback` (`idFeedback`),
  CONSTRAINT `fk_Feedback_has_Usuario_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `bdrepublica`.`usuario` (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`geolocalizacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`geolocalizacao` (
  `idGeolocalizacao` INT NOT NULL AUTO_INCREMENT,
  `latitude` VARCHAR(45) NULL DEFAULT NULL,
  `longitude` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idGeolocalizacao`),
  UNIQUE INDEX `idGeolocalizacao_UNIQUE` (`idGeolocalizacao` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`endereco` (
  `idEndereco` INT NOT NULL AUTO_INCREMENT,
  `idGeolocalizacao` INT NOT NULL,
  `cEP` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(100) NOT NULL,
  `logradouro` VARCHAR(100) NOT NULL,
  `numero` INT NOT NULL,
  `referencia` VARCHAR(100) NULL DEFAULT NULL,
  `uf` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`idEndereco`, `idGeolocalizacao`),
  UNIQUE INDEX `idEndereco_UNIQUE` (`idEndereco` ASC) VISIBLE,
  INDEX `fk_Endereco_Geolocalizacao1_idx` (`idGeolocalizacao` ASC) VISIBLE,
  CONSTRAINT `fk_Endereco_Geolocalizacao1`
    FOREIGN KEY (`idGeolocalizacao`)
    REFERENCES `bdrepublica`.`geolocalizacao` (`idGeolocalizacao`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`republica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`republica` (
  `idRepublica` INT NOT NULL AUTO_INCREMENT,
  `idEndereco` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `dataFundacao` DATE NOT NULL,
  `dataExtinsao` DATE NULL DEFAULT NULL,
  `vantagens` VARCHAR(400) NOT NULL,
  `despesasMedias` DOUBLE NOT NULL,
  `vagasTotais` INT NOT NULL,
  `vagasOcupadas` INT NOT NULL,
  `saldoTotal` DOUBLE NOT NULL,
  `codEtica` VARCHAR(500) NOT NULL,
  `estado` VARCHAR(45) NOT NULL DEFAULT 'Aberta',
  PRIMARY KEY (`idRepublica`, `idEndereco`),
  UNIQUE INDEX `idRepublica_UNIQUE` (`idRepublica` ASC) VISIBLE,
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE,
  INDEX `fk_Republica_Endereco1_idx` (`idEndereco` ASC) VISIBLE,
  CONSTRAINT `fk_Republica_Endereco1`
    FOREIGN KEY (`idEndereco`)
    REFERENCES `bdrepublica`.`endereco` (`idEndereco`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`historico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`historico` (
  `idUsuario` INT NOT NULL,
  `idRepublica` INT NOT NULL,
  `dataSaida` DATE NOT NULL,
  `nomeRepresentante` VARCHAR(100) NOT NULL,
  `mediaReputacao` DOUBLE NOT NULL,
  `nomeRepublica` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idUsuario`, `idRepublica`),
  INDEX `fk_Usuario_has_Republica_Republica1_idx` (`idRepublica` ASC) VISIBLE,
  INDEX `fk_Usuario_has_Republica_Usuario_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_has_Republica_Republica1`
    FOREIGN KEY (`idRepublica`)
    REFERENCES `bdrepublica`.`republica` (`idRepublica`),
  CONSTRAINT `fk_Usuario_has_Republica_Usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `bdrepublica`.`usuario` (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`lancamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`lancamento` (
  `idLancamento` INT NOT NULL AUTO_INCREMENT,
  `idEndereco` INT NOT NULL,
  `idRepublica` INT NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `dataVencimento` DATE NOT NULL,
  `valor` DOUBLE NOT NULL,
  `periodicidade` VARCHAR(45) NOT NULL,
  `valorParcela` DOUBLE NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `dataPagamento` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idLancamento`, `idEndereco`, `idRepublica`),
  UNIQUE INDEX `idLancamento_UNIQUE` (`idLancamento` ASC) VISIBLE,
  INDEX `fk_Lancamento_Republica1_idx` (`idRepublica` ASC, `idEndereco` ASC) VISIBLE,
  CONSTRAINT `fk_Lancamento_Republica1`
    FOREIGN KEY (`idRepublica` , `idEndereco`)
    REFERENCES `bdrepublica`.`republica` (`idRepublica` , `idEndereco`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`rateio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`rateio` (
  `idrateio` INT NOT NULL AUTO_INCREMENT,
  `idLancamento` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  `valor` DOUBLE NOT NULL,
  PRIMARY KEY (`idrateio`, `idLancamento`, `idUsuario`),
  UNIQUE INDEX `idrateio_UNIQUE` (`idrateio` ASC) VISIBLE,
  INDEX `fk_rateio_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  INDEX `fk_rateio_Lancamento1_idx` (`idLancamento` ASC) VISIBLE,
  CONSTRAINT `fk_rateio_Lancamento1`
    FOREIGN KEY (`idLancamento`)
    REFERENCES `bdrepublica`.`lancamento` (`idLancamento`),
  CONSTRAINT `fk_rateio_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `bdrepublica`.`usuario` (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`reputacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`reputacao` (
  `idReputacao` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `indice` DOUBLE NOT NULL,
  `data` DATE NOT NULL,
  PRIMARY KEY (`idReputacao`),
  UNIQUE INDEX `idReputacao_UNIQUE` (`idReputacao` ASC) VISIBLE,
  INDEX `fk_Reputacao_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Reputacao_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `bdrepublica`.`usuario` (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`tarefa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`tarefa` (
  `idTarefa` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(200) NOT NULL,
  `dataAgendamento` DATE NOT NULL,
  `dataTermino` DATE NOT NULL,
  `finalizada` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`idTarefa`),
  UNIQUE INDEX `idTarefa_UNIQUE` (`idTarefa` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`tarefausuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`tarefausuario` (
  `idTarefa` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`idTarefa`, `idUsuario`),
  INDEX `fk_Tarefa_has_Usuario_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  INDEX `fk_Tarefa_has_Usuario_Tarefa1_idx` (`idTarefa` ASC) VISIBLE,
  CONSTRAINT `fk_Tarefa_has_Usuario_Tarefa1`
    FOREIGN KEY (`idTarefa`)
    REFERENCES `bdrepublica`.`tarefa` (`idTarefa`),
  CONSTRAINT `fk_Tarefa_has_Usuario_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `bdrepublica`.`usuario` (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
