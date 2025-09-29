CREATE TABLE `crdcard`(
  `id` int NOT NULL AUTO_INCREMENT,
  `accountnumber` int,
  `crdcardnumber` varchar(16),
  `crdcardlimit` decimal(10,2),
  PRIMARY KEY (`id`)
);
