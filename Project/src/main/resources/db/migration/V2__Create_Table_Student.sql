CREATE TABLE IF NOT EXISTS `student` (
                           `student_id` bigint NOT NULL AUTO_INCREMENT,
                           `cpf` varchar(255) DEFAULT NULL,
                           `date_created` datetime(6) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `ra` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`student_id`)
);