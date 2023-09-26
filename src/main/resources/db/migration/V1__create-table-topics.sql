CREATE TABLE IF NOT EXISTS `foro_alura`.`topics` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `message` VARCHAR(500) NOT NULL,
  `creation_date` DATETIME NOT NULL,
  `status` VARCHAR(100) NOT NULL,
  `id_author` BIGINT NOT NULL,
  `id_course` BIGINT NOT NULL,
  `active` TINYINT NOT NULL,

  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
  UNIQUE INDEX `message_UNIQUE` (`message` ASC) VISIBLE)
ENGINE = InnoDB;