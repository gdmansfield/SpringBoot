# This repeatable migration will run every time the
# application starts because of the timestamp placeholder
# on line 4
-- ${flyway:timestamp}
DROP TABLE IF EXISTS Pets;

CREATE TABLE Pets
(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  type VARCHAR(10)
);

INSERT INTO Pets(type) VALUES ('Cat'), ('Dog'), ('Reptile');
