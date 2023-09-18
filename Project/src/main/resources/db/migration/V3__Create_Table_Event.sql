CREATE TABLE IF NOT EXISTS `event` (
                         `event_id` bigint NOT NULL AUTO_INCREMENT,
                         `date_created` datetime(6) NOT NULL,
                         `event_date` datetime(6) NOT NULL,
                         `event_name` varchar(255) DEFAULT NULL,
                         `event_type` varchar(255) DEFAULT NULL,
                         `student` bigint DEFAULT NULL,
                         PRIMARY KEY (`event_id`),
                         KEY `FKbom7197g8cg9qgg2cebrfr01h` (`student`),
                         CONSTRAINT `FKbom7197g8cg9qgg2cebrfr01h` FOREIGN KEY (`student`) REFERENCES `student` (`student_id`)
);