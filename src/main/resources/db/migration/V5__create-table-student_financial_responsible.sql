CREATE TABLE student_financial_responsible (
    student_id INT NOT NULL,
    financial_responsible_id INT NOT NULL,
    PRIMARY KEY (student_id, financial_responsible_id),
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (financial_responsible_id) REFERENCES financial_responsible(id) ON DELETE CASCADE
);