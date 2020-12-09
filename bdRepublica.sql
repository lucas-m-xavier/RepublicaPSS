-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bdrepublica
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdrepublica
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdrepublica` DEFAULT CHARACTER SET utf8 ;
USE `bdrepublica` ;

-- -----------------------------------------------------
-- Table `bdrepublica`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`Endereco` (
  `idEndereco` INT NOT NULL,
  `cEP` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(100) NOT NULL,
  `logradouro` VARCHAR(100) NOT NULL,
  `numero` INT NOT NULL,
  `referencia` VARCHAR(100) NULL,
  `uf` CHAR(2) NOT NULL,
  PRIMARY KEY (`idEndereco`),
  UNIQUE INDEX `idEndereco_UNIQUE` (`idEndereco` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdrepublica`.`Republica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`Republica` (
  `idRepublica` INT NOT NULL,
  `idEndereco` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `dataFundacao` DATE NOT NULL,
  `dataExtinsao` DATE NOT NULL,
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
    REFERENCES `bdrepublica`.`Endereco` (`idEndereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdrepublica`.`rateio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`rateio` (
  `idrateio` INT NOT NULL,
  `valor` DOUBLE NOT NULL,
  PRIMARY KEY (`idrateio`),
  UNIQUE INDEX `idrateio_UNIQUE` (`idrateio` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdrepublica`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`Usuario` (
  `idUsuario` INT NOT NULL,
  `idrateio` INT NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `apelido` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `linkSociais` VARCHAR(100) NOT NULL,
  `reponsavelUm` VARCHAR(100) NOT NULL,
  `responsavelDois` VARCHAR(100) NOT NULL,
  `estado` VARCHAR(45) NOT NULL DEFAULT 'Sem Teto',
  PRIMARY KEY (`idUsuario`, `idrateio`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUsuario` ASC) VISIBLE,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  INDEX `fk_Usuario_rateio1_idx` (`idrateio` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_rateio1`
    FOREIGN KEY (`idrateio`)
    REFERENCES `bdrepublica`.`rateio` (`idrateio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdrepublica`.`Historico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`Historico` (
  `idUsuario` INT NOT NULL,
  `idRepublica` INT NOT NULL,
  `dataSaida` DATE NOT NULL,
  `nomeRepresentante` VARCHAR(100) NOT NULL,
  `mediaReputacao` DOUBLE NOT NULL,
  `nomeRepublica` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idUsuario`, `idRepublica`),
  INDEX `fk_Usuario_has_Republica_Republica1_idx` (`idRepublica` ASC) VISIBLE,
  INDEX `fk_Usuario_has_Republica_Usuario_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_has_Republica_Usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `bdrepublica`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Republica_Republica1`
    FOREIGN KEY (`idRepublica`)
    REFERENCES `bdrepublica`.`Republica` (`idRepublica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdrepublica`.`Feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`Feedback` (
  `idFeedback` INT NOT NULL,
  `dataCriacao` DATE NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `dataSolucao` DATE NOT NULL,
  `estado` VARCHAR(45) NOT NULL DEFAULT 'Em Aberto',
  PRIMARY KEY (`idFeedback`),
  UNIQUE INDEX `idFeedback_UNIQUE` (`idFeedback` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdrepublica`.`FeedbackUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`FeedbackUsuario` (
  `idUsuario` INT NOT NULL,
  `idFeedback` INT NOT NULL,
  PRIMARY KEY (`idUsuario`, `idFeedback`),
  INDEX `fk_Usuario_has_Feedback_Feedback1_idx` (`idFeedback` ASC) VISIBLE,
  INDEX `fk_Usuario_has_Feedback_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_has_Feedback_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `bdrepublica`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Feedback_Feedback1`
    FOREIGN KEY (`idFeedback`)
    REFERENCES `bdrepublica`.`Feedback` (`idFeedback`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdrepublica`.`Tarefa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`Tarefa` (
  `idTarefa` INT NOT NULL,
  `dataAgendamento` DATE NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `dataTermino` DATE NOT NULL,
  `estado` VARCHAR(45) NOT NULL DEFAULT 'pendente',
  PRIMARY KEY (`idTarefa`),
  UNIQUE INDEX `idTarefa_UNIQUE` (`idTarefa` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdrepublica`.`TarefaUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`TarefaUsuario` (
  `idUsuario` INT NOT NULL,
  `idTarefa` INT NOT NULL,
  PRIMARY KEY (`idUsuario`, `idTarefa`),
  INDEX `fk_Usuario_has_Tarefa_Tarefa1_idx` (`idTarefa` ASC) VISIBLE,
  INDEX `fk_Usuario_has_Tarefa_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_has_Tarefa_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `bdrepublica`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Tarefa_Tarefa1`
    FOREIGN KEY (`idTarefa`)
    REFERENCES `bdrepublica`.`Tarefa` (`idTarefa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdrepublica`.`Lancamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`Lancamento` (
  `idLancamento` INT NOT NULL,
  `idrateio` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `dataVencimento` DATE NOT NULL,
  `valor` DOUBLE NOT NULL,
  `periodicidade` VARCHAR(45) NOT NULL,
  `valorParcela` DOUBLE NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL DEFAULT 'Indeferido',
  PRIMARY KEY (`idLancamento`, `idrateio`, `idUsuario`),
  UNIQUE INDEX `idLancamento_UNIQUE` (`idLancamento` ASC) VISIBLE,
  INDEX `fk_Lancamento_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  INDEX `fk_Lancamento_rateio1_idx` (`idrateio` ASC) VISIBLE,
  CONSTRAINT `fk_Lancamento_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `bdrepublica`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lancamento_rateio1`
    FOREIGN KEY (`idrateio`)
    REFERENCES `bdrepublica`.`rateio` (`idrateio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdrepublica`.`Reputacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdrepublica`.`Reputacao` (
  `idReputacao` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  `indice` DOUBLE NOT NULL,
  `data` DATE NOT NULL,
  PRIMARY KEY (`idReputacao`),
  UNIQUE INDEX `idReputacao_UNIQUE` (`idReputacao` ASC) VISIBLE,
  INDEX `fk_Reputacao_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Reputacao_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `bdrepublica`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
