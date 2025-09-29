CREATE TABLE `deposit`(
  `id` int NOT NULL AUTO_INCREMENT,
  `accountnumber` int,
  `amount` decimal(10,2),
  `duedate` DATE,
  PRIMARY KEY (`id`)
);
