ALTER TABLE booking
    ADD COLUMN client_id INT NOT NULL;

ALTER TABLE booking
    ADD COLUMN employee_id INT NOT NULL;

ALTER TABLE booking
    ADD COLUMN service_id INT NOT NULL;