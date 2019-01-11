CREATE TABLE `weather_reports`.`reports_log` (
  `log_id` INT(11) NOT NULL AUTO_INCREMENT,
  `city_id` INT NOT NULL,
  `request_time` DATETIME NOT NULL,
  `report_time` DATETIME NOT NULL,
  `requested_by` VARCHAR(40) NULL,
  PRIMARY KEY (`log_id`),
  INDEX `rslg_cyrs_fk_idx` (`city_id` ASC) VISIBLE,
  CONSTRAINT `rslg_cyrs_fk`
    FOREIGN KEY (`city_id`)
    REFERENCES `weather_reports`.`city_reports` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
