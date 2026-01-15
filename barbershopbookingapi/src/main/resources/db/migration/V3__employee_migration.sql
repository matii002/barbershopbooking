CREATE TABLE employee
(
    id                INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    first_name        VARCHAR(200) NOT NULL,
    last_name         VARCHAR(200) NOT NULL,
    email             VARCHAR(255) NOT NULL,
    phone             VARCHAR(20)  NOT NULL,
    creation_date     TIMESTAMP    NOT NULL,
    modification_date TIMESTAMP NULL
);