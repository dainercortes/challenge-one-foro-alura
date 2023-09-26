CREATE TABLE IF NOT EXISTS `foro_alura`.`responses` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(500) NOT NULL,
  `id_topic` BIGINT NOT NULL,
  `creation_date` DATETIME NOT NULL,
  `id_author` VARCHAR(100) NOT NULL,
  `solution` TINYINT NOT NULL,
  `active` TINYINT NOT NULL,

  PRIMARY KEY (`id`))
ENGINE = InnoDB;