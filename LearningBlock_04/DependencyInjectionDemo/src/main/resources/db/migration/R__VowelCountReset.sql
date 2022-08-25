-- ${flyway:timestamp}
DROP TABLE IF EXISTS VowelCount;

CREATE TABLE VowelCount
(
    Vowel char(1) NOT NULL PRIMARY KEY,
    Vcount integer NOT NULL
);

INSERT INTO VowelCount (Vowel, Vcount)
VALUES ('A', 0), ('E', 0), ('I', 0), ('O', 0), ('U', 0);