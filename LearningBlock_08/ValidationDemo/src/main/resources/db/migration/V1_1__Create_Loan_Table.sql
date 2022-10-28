DROP TABLE IF EXISTS Loan;

CREATE TABLE Loan
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    copy_id INTEGER NOT NULL ,
    loan_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE NULL,
    FOREIGN KEY (copy_id) REFERENCES Copy(id)
);
