CREATE TABLE payment_student (
    payment_id INTEGER NOT NULL,
    student_id INTEGER NOT NULL,
    PRIMARY KEY (payment_id, student_id),
    FOREIGN KEY (payment_id) REFERENCES payments(id),
    FOREIGN KEY (student_id) REFERENCES student(id)
);