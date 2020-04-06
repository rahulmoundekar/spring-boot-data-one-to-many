# spring-boot-data-one-to-many
spring-boot-data-one-to-many with swagger implementation

Database SQL :

      DROP TABLE IF EXISTS `springboot`.`employee`;
      CREATE TABLE  `springboot`.`employee` (
        `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
        `first_name` varchar(45) NOT NULL,
        `last_name` varchar(45) NOT NULL,
        `email` varchar(45) NOT NULL,
        `salary` double NOT NULL,
        PRIMARY KEY (`id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
      
      DROP TABLE IF EXISTS `springboot`.`account`;
      CREATE TABLE  `springboot`.`account` (
        `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
        `account_number` varchar(45) NOT NULL,
        `employee_id` int(10) unsigned NOT NULL,
        PRIMARY KEY (`id`),
        KEY `FK_account_1` (`employee_id`),
        CONSTRAINT `FK_account_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
      ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


Swagger : run : http://localhost:8080/swagger-ui.html
