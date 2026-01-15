CREATE TABLE booking
(
    id                INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    start_time        DATETIME    NOT NULL,
    status            VARCHAR(80) NOT NULL,
    creation_date     TIMESTAMP   NOT NULL,
    modification_date TIMESTAMP NULL
);