ALTER TABLE `foro_alura`.`responses` ADD `active` tinyint;
UPDATE `foro_alura`.`responses` SET `active` = 1;