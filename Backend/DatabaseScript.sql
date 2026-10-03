-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema OstfoldHusflidslag
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `OstfoldHusflidslag` DEFAULT CHARACTER SET utf8 ;
USE `OstfoldHusflidslag` ;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Bruker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Bruker` (
  `brukerID` INT NOT NULL,
  `navn` VARCHAR(20) NOT NULL,
  `etternavn` VARCHAR(20) NOT NULL,
  `telefon` VARCHAR(8) NOT NULL,
  `epost` VARCHAR(45) NOT NULL,
  `rolle` ENUM('bruker', 'administrator', 'utvikler') NOT NULL,
  PRIMARY KEY (`brukerID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Byen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Byen` (
  `postnr` INT NOT NULL,
  `navn` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`postnr`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Adresse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Adresse` (
  `gate` VARCHAR(100) NOT NULL,
  `gatenr` INT NOT NULL,
  `By_postnr` INT NOT NULL,
  PRIMARY KEY (`gate`, `gatenr`, `By_postnr`),
  INDEX `fk_Adresse_Byen1_idx` (`By_postnr` ASC),
  CONSTRAINT `fk_Adresse_Byen1`
    FOREIGN KEY (`By_postnr`)
    REFERENCES `OstfoldHusflidslag`.`Byen` (`postnr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Arrangor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Arrangor` (
  `arrangorID` INT NOT NULL,
  `tittel` VARCHAR(100) NOT NULL,
  `Adresse_gate` VARCHAR(100) NOT NULL,
  `Adresse_gatenr` INT NOT NULL,
  `Adresse_By_postnr` INT NOT NULL,
  PRIMARY KEY (`arrangorID`),
  INDEX `fk_Arrangor_Adresse1_idx` (`Adresse_gate` ASC, `Adresse_gatenr` ASC, `Adresse_By_postnr` ASC),
  CONSTRAINT `fk_Arrangor_Adresse1`
    FOREIGN KEY (`Adresse_gate`, `Adresse_gatenr`, `Adresse_By_postnr`)
    REFERENCES `OstfoldHusflidslag`.`Adresse` (`gate`, `gatenr`, `By_postnr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Kategori`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Kategori` (
  `kategoriID` INT NOT NULL,
  `navn` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`kategoriID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Kurs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Kurs` (
  `kursID` INT NOT NULL,
  `tittel` VARCHAR(45) NOT NULL,
  `dato` DATE NOT NULL,
  `minPris` INT NULL,
  `maksPris` INT NULL,
  `beskrivelse` TEXT NULL,
  `bilde` LONGBLOB NULL,
  `Arrangor_arrangorID` INT NOT NULL,
  `Kategori_kategoriID` INT NOT NULL,
  PRIMARY KEY (`kursID`),
  INDEX `fk_Kurs_Arrangor1_idx` (`Arrangor_arrangorID` ASC) VISIBLE,
  INDEX `fk_Kurs_Kategori1_idx` (`Kategori_kategoriID` ASC) VISIBLE,
  CONSTRAINT `fk_Kurs_Arrangor1`
    FOREIGN KEY (`Arrangor_arrangorID`)
    REFERENCES `OstfoldHusflidslag`.`Arrangor` (`arrangorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Kurs_Kategori1`
    FOREIGN KEY (`Kategori_kategoriID`)
    REFERENCES `OstfoldHusflidslag`.`Kategori` (`kategoriID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Billettype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Billettype` (
  `billettypeID` INT NOT NULL,
  `tittel` VARCHAR(45) NOT NULL,
  `antall` INT NOT NULL,
  `pris` DECIMAL(10,2) NOT NULL,
  `Kurs_kursID` INT NOT NULL,
  PRIMARY KEY (`billettypeID`),
  INDEX `fk_Billettype_Kurs1_idx` (`Kurs_kursID` ASC) VISIBLE,
  CONSTRAINT `fk_Billettype_Kurs1`
    FOREIGN KEY (`Kurs_kursID`)
    REFERENCES `OstfoldHusflidslag`.`Kurs` (`kursID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Venteliste`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Venteliste` (
  `ventelisteID` INT NOT NULL,
  `pameldingsdato` DATE NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `Kurs_kursID` INT NOT NULL,
  `Bruker_brukerID` INT NULL, -- NULLable to support guest signups
  `gjestNavn` VARCHAR(45) NULL,   -- Fallback name if guest
  `gjestEpost` VARCHAR(45) NULL,  -- Fallback email if guest
  `gjestTelefon` VARCHAR(8) NULL,
  PRIMARY KEY (`ventelisteID`),
  INDEX `fk_Venteliste_Kurs1_idx` (`Kurs_kursID` ASC) VISIBLE,
  INDEX `fk_Venteliste_Bruker1_idx` (`Bruker_brukerID` ASC) VISIBLE,
  CONSTRAINT `fk_Venteliste_Kurs1`
    FOREIGN KEY (`Kurs_kursID`)
    REFERENCES `OstfoldHusflidslag`.`Kurs` (`kursID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venteliste_Bruker1`
    FOREIGN KEY (`Bruker_brukerID`)
    REFERENCES `OstfoldHusflidslag`.`Bruker` (`brukerID`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Deltakelse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Deltakelse` (
  `deltakelseID` INT NOT NULL,
  `oppmotestatus` TINYINT NOT NULL,
  `dato` DATE NOT NULL,
  `Kurs_kursID` INT NOT NULL,
  `Bruker_brukerID` INT NULL, -- NULLable to support guests who don't have accounts
  `gjestNavn` VARCHAR(45) NULL,   -- Fallback name if guest
  `gjestEpost` VARCHAR(45) NULL,  -- Fallback email if guest
  `gjestTelefon` VARCHAR(8) NULL,
  PRIMARY KEY (`deltakelseID`),
  INDEX `fk_Deltakelse_Kurs1_idx` (`Kurs_kursID` ASC) VISIBLE,
  INDEX `fk_Deltakelse_Bruker1_idx` (`Bruker_brukerID` ASC) VISIBLE,
  CONSTRAINT `fk_Deltakelse_Kurs1`
    FOREIGN KEY (`Kurs_kursID`)
    REFERENCES `OstfoldHusflidslag`.`Kurs` (`kursID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Deltakelse_Bruker1`
    FOREIGN KEY (`Bruker_brukerID`)
    REFERENCES `OstfoldHusflidslag`.`Bruker` (`brukerID`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`KontaktPerson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`KontaktPerson` (
  `kontaktID` INT NOT NULL,
  `navn` VARCHAR(45) NOT NULL,
  `etternavn` VARCHAR(45) NOT NULL,
  `telefon` VARCHAR(45) NOT NULL,
  `epost` VARCHAR(45) NOT NULL,
  `posisjon` VARCHAR(45) NOT NULL,
  `bilde` LONGBLOB NULL,
  `Arrangor_arrangorID` INT NOT NULL,
  PRIMARY KEY (`kontaktID`),
  INDEX `fk_KontaktPerson_Arrangor1_idx` (`Arrangor_arrangorID` ASC) VISIBLE,
  CONSTRAINT `fk_KontaktPerson_Arrangor1`
    FOREIGN KEY (`Arrangor_arrangorID`)
    REFERENCES `OstfoldHusflidslag`.`Arrangor` (`arrangorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Innlegg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Innlegg` (
  `nyhetID` INT NOT NULL,
  `tittel` VARCHAR(45) NOT NULL,
  `tekst` TEXT NULL,
  `publiseringsdato` DATE NOT NULL,
  `Arrangor_arrangorID` INT NOT NULL,
  PRIMARY KEY (`nyhetID`),
  INDEX `fk_Innlegg_Arrangor1_idx` (`Arrangor_arrangorID` ASC) VISIBLE,
  CONSTRAINT `fk_Innlegg_Arrangor1`
    FOREIGN KEY (`Arrangor_arrangorID`)
    REFERENCES `OstfoldHusflidslag`.`Arrangor` (`arrangorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`FAQ`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`FAQ` (
  `faqID` INT NOT NULL,
  `sporsmal` TEXT NULL,
  `svar` TEXT NULL,
  PRIMARY KEY (`faqID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Kjop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Kjop` (
  `kjopID` INT NOT NULL,
  `kjopsDato` DATE NOT NULL,
  `antall` INT NOT NULL,
  `totalPris` VARCHAR(45) NOT NULL,
  `Bruker_brukerID` INT NULL,     -- NULLable to support guest checkouts
  `Kurs_kursID` INT NOT NULL,
  `gjestNavn` VARCHAR(45) NULL,   -- Fallback name if guest
  `gjestEpost` VARCHAR(45) NULL,  -- Fallback email if guest
  `gjestTelefon` VARCHAR(8) NULL, -- Fallback phone if guest
  PRIMARY KEY (`kjopID`),
  INDEX `fk_Kjop_Bruker1_idx` (`Bruker_brukerID` ASC) VISIBLE,
  INDEX `fk_Kjop_Kurs1_idx` (`Kurs_kursID` ASC) VISIBLE,
  CONSTRAINT `fk_Kjop_Bruker1`
    FOREIGN KEY (`Bruker_brukerID`)
    REFERENCES `OstfoldHusflidslag`.`Bruker` (`brukerID`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Kjop_Kurs1`
    FOREIGN KEY (`Kurs_kursID`)
    REFERENCES `OstfoldHusflidslag`.`Kurs` (`kursID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`Billett`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`Billett` (
  `billettID` INT NOT NULL,
  `tittel` VARCHAR(45) NOT NULL,
  `antall` INT NOT NULL,
  `pris` VARCHAR(45) NOT NULL,
  `Kjop_kjopID` INT NOT NULL,
  PRIMARY KEY (`billettID`),
  INDEX `fk_Billett_Kjop1_idx` (`Kjop_kjopID` ASC) VISIBLE,
  CONSTRAINT `fk_Billett_Kjop1`
    FOREIGN KEY (`Kjop_kjopID`)
    REFERENCES `OstfoldHusflidslag`.`Kjop` (`kjopID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OstfoldHusflidslag`.`AdminLogg`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `OstfoldHusflidslag`.`AdminLogg` (
  `loggID` INT NOT NULL,
  `handling` VARCHAR(45) NOT NULL,
  `tidsstempel` TIME NOT NULL,
  `dato` DATE NOT NULL,
  `Bruker_brukerID` INT NOT NULL,
  PRIMARY KEY (`loggID`),
  INDEX `fk_AdminLogg_Bruker1_idx` (`Bruker_brukerID` ASC) VISIBLE,
  CONSTRAINT `fk_AdminLogg_Bruker1`
    FOREIGN KEY (`Bruker_brukerID`)
    REFERENCES `OstfoldHusflidslag`.`Bruker` (`brukerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- STATUS
-- 28.SEP: Ferdig med å fylle tabellene: Byen, Adresse, Arrangor, KontakPerson (for fredrikstad), Kurs, Billett / Billettype, Deltakelse, Venteliste, Kjop
-- ---------------------------------------
-- VERDIER I TABELLENE
-- ---------------------------------------

-- Legg til en by (trengs for adresse)
/*
INSERT INTO Byen (postnr, navn) VALUES (1767, 'Halden');
INSERT INTO Byen (postnr, navn) VALUES (1650, 'Sellebakk');
INSERT INTO Byen (postnr, navn) VALUES (1591, 'Rygge');
INSERT INTO Byen (postnr, navn) VALUES (1640, 'Råde');
INSERT INTO Byen (postnr, navn) VALUES (1715, 'Sarpsborg');
INSERT INTO Byen (postnr, navn) VALUES (1530, 'Moss');
INSERT INTO Byen (postnr, navn) VALUES (1811, 'Askim');
INSERT INTO Byen (postnr, navn) VALUES (1825, 'Hobøl');
*/
/*
INSERT INTO Kategori (kategoriID, navn) VALUES (1, 'Bunad og Folkedrakt');
INSERT INTO Kategori (kategoriID, navn) VALUES (2, 'Tekstil');
INSERT INTO Kategori (kategoriID, navn) VALUES (3, 'Vev');
INSERT INTO Kategori (kategoriID, navn) VALUES (4, 'Trearbeid');
INSERT INTO Kategori (kategoriID, navn) VALUES (5, 'Knivmaker');
INSERT INTO Kategori (kategoriID, navn) VALUES (6, 'Skinn og lær');
INSERT INTO Kategori (kategoriID, navn) VALUES (7, 'Kurvmaker og fletting');
INSERT INTO Kategori (kategoriID, navn) VALUES (8, 'Metallarbbeid og smiing');
INSERT INTO Kategori (kategoriID, navn) VALUES (9, 'Keramikk og glass');
INSERT INTO Kategori (kategoriID, navn) VALUES (10, 'Samisk Håndverk');
*/

/*
-- Legg til en bruker
INSERT INTO Bruker (brukerID, navn, etternavn, telefon, epost, rolle) 
VALUES (1, 'Ola', 'Nordmann', '40000000', 'ola@eksempel.no', 'bruker');
*/

-- Legg til adresse (knyttet til Byen)
/*
INSERT INTO Adresse (gate, gatenr, By_postnr) VALUES ('Svenskegata', 2, 1767);
INSERT INTO Adresse (gate, gatenr, By_postnr) VALUES ('Emil Mørchs vei', 25, 1650);
INSERT INTO Adresse (gate, gatenr, By_postnr) VALUES ('Gubbeskogveien', 37, 1591);
INSERT INTO Adresse (gate, gatenr, By_postnr) VALUES ('Stasjonsveien', 1, 1640);
INSERT INTO Adresse (gate, gatenr, By_postnr) VALUES ('Olavs gate', 1, 1715);
INSERT INTO Adresse (gate, gatenr, By_postnr) VALUES ('Dronningens gate', 12, 1530);
INSERT INTO Adresse (gate, gatenr, By_postnr) VALUES ('Hovedgata', 10, 1811);
INSERT INTO Adresse (gate, gatenr, By_postnr) VALUES ('Vannsjøveien', 5, 1825);
*/

-- Legg til arrangører (med tilhørende adresseinfo)

/*
INSERT INTO Arrangor (arrangorID, tittel, Adresse_gate, Adresse_gatenr, Adresse_By_postnr) 
VALUES (1, 'Halden Husflidslag', 'Svenskegata', 2, 1767);

INSERT INTO Arrangor (arrangorID, tittel, Adresse_gate, Adresse_gatenr, Adresse_By_postnr) 
VALUES (2, 'Fredrikstad Husflidslag', 'Emil Mørchs vei', 25, 1650);

INSERT INTO Arrangor (arrangorID, tittel, Adresse_gate, Adresse_gatenr, Adresse_By_postnr) 
VALUES (3, 'Rygge Husflidslag', 'Gubbeskogveien', 37, 1591);

INSERT INTO Arrangor (arrangorID, tittel, Adresse_gate, Adresse_gatenr, Adresse_By_postnr) 
VALUES (4, 'Råde Husflidslag', 'Stasjonsveien', 1, 1640);

INSERT INTO Arrangor (arrangorID, tittel, Adresse_gate, Adresse_gatenr, Adresse_By_postnr) 
VALUES (5, 'Sarpsborg Husflidslag', 'Olavs gate', 1, 1715);

INSERT INTO Arrangor (arrangorID, tittel, Adresse_gate, Adresse_gatenr, Adresse_By_postnr) 
VALUES (6, 'Moss Husflidslag', 'Dronningens gate', 12, 1530);

INSERT INTO Arrangor (arrangorID, tittel, Adresse_gate, Adresse_gatenr, Adresse_By_postnr) 
VALUES (7, 'Indre Østfold Husflidslag', 'Hovedgata', 10, 1811);

INSERT INTO Arrangor (arrangorID, tittel, Adresse_gate, Adresse_gatenr, Adresse_By_postnr) 
VALUES (8, 'Hobøl Husflidslag', 'Vannsjøveien', 5, 1825);
*/

-- Legg til kontaktpersoner (med riktig kolonnenavn for fremmednøkkel)

/*
INSERT INTO KontaktPerson 
(kontaktID, Arrangor_arrangorID, navn, etternavn, telefon, epost, posisjon) 
VALUES (1, 1, 'Mona', 'Andersen', '91777582', 'mona@sjokkpris.no', 'Styreleder');

INSERT INTO KontaktPerson 
(kontaktID, Arrangor_arrangorID, navn, etternavn, telefon, epost, posisjon) 
VALUES (2, 1, 'Aud Kari', 'Holme', '95879367', 'audkari.holme@gmail.com', 'Nestleder');

INSERT INTO KontaktPerson 
(kontaktID, Arrangor_arrangorID, navn, etternavn, telefon, epost, posisjon) 
VALUES (3, 1, 'Heidi', 'Børstad', '95222155', 'heidi.borstad@gmail.com', 'Sekretær');

INSERT INTO KontaktPerson 
(kontaktID, Arrangor_arrangorID, navn, etternavn, telefon, epost, posisjon) 
VALUES (4, 1, 'Silja Devine', 'Holhjem', '41664904', 'siljadevine@gmail.com', 'Nettansvarlig');

INSERT INTO KontaktPerson 
(kontaktID, Arrangor_arrangorID, navn, etternavn, telefon, epost, posisjon) 
VALUES (5, 1, 'Linda P.', 'Vallner', '97638424', 'lindap.vallner@gmail.com', 'Styremedlem og Ung Husflidskontakt');

INSERT INTO KontaktPerson 
(kontaktID, Arrangor_arrangorID, navn, etternavn, telefon, epost, posisjon) 
VALUES (6, 1, 'Thore', 'Grøtvedt', '90135455', 'thoregro@gmail.com', 'Styremedlem');
*/

/*
INSERT INTO Kurs
(kursID, tittel, dato, minPris, maksPris, beskrivelse, bilde, Arrangor_arrangorID, Kategori_kategoriID) 
VALUES (1, 'RandKurs', '2026-11-15', 0, 300, 'Lorum Ipsum', '', 1, 1);
*/

/*
INSERT INTO Billettype (billettypeID, tittel, antall, pris, Kurs_kursID)
VALUES (1, 'Prisklasse UTEN UTSTYR', 21, 300, 1);

INSERT INTO Billettype (billettypeID, tittel, antall, pris, Kurs_kursID)
VALUES (2, 'Prisklasse MED UTSTYR', 12, 100, 1);
*/

/*
INSERT INTO Deltakelse (deltakelseID, oppmotestatus, dato, Kurs_kursID)
VALUES (1, 1, '2026-11-15', 1);


INSERT INTO Venteliste (ventelisteID, pameldingsdato, `status`, Kurs_kursID)
VALUES (1, '2026-11-10', 'Aktiv', 1);


INSERT INTO Kjop (kjopID, kjopsDato, antall, totalPris, Bruker_brukerID, Kurs_kursID)
VALUES (1, '2026-11-10', 3, 0, 1, 1);
*/

/*
INSERT INTO Innlegg (nyhetID, tittel, tekst, publiseringsdato, Arrangor_arrangorID)
VALUES (1, 'Test', '', '2026-09-30', 1);
*/

/*
INSERT INTO AdminLogg (loggID, handling, tidsstempel, dato, Bruker_brukerID)
VALUES (1, 'Skrive tekst', '11:08:45', '2026-09-30', 1)
*/

/*
INSERT INTO FAQ (faqID, sporsmal, svar)
VALUES (1, 'hmm?', 'nei');
*/
