DROP TABLE IF EXISTS person;

CREATE TABLE person (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    createdAt DATETIME
);

INSERT INTO person (name, createdAt) VALUES ('John Doe', '2024-01-01 12:00:00');
