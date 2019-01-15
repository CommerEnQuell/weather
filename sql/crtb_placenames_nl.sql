CREATE TABLE `weather_reports`.`placenames_nl` (
  `name_nl` VARCHAR(40) NOT NULL,
  `city_id` INT NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE INDEX `psnl_uk` (`name_nl` ASC) VISIBLE,
  CONSTRAINT `psnl_cyrs_fk`
    FOREIGN KEY (`city_id`)
    REFERENCES `weather_reports`.`city_reports` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
