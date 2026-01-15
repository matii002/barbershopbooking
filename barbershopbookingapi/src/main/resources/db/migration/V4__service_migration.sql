CREATE TABLE service
(
    id                INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name              VARCHAR(80) NOT NULL,
    duration_minutes  INT         NOT NULL,
    price DOUBLE (10, 2) NOT NULL,
    creation_date     TIMESTAMP   NOT NULL,
    modification_date TIMESTAMP NULL
);