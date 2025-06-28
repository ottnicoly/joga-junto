CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    payment_date DATE NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,

    responsible_id INTEGER NOT NULL,
    student_id INTEGER,

    CONSTRAINT fk_responsible FOREIGN KEY (responsible_id) REFERENCES financial_responsible(id),
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student(id)
);