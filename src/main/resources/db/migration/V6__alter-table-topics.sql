ALTER TABLE `foro_alura`.`topics` ADD `active` tinyint;
UPDATE `foro_alura`.`topics` SET `active` = 1;