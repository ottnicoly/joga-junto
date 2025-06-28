CREATE TABLE presence (
    id SERIAL PRIMARY KEY,
    presence BOOLEAN NOT NULL,
    date DATE NOT NULL,
    student_id INTEGER NOT NULL,
    classroom_id INTEGER NOT NULL,
    CONSTRAINT fk_presence_student FOREIGN KEY (student_id) REFERENCES student(id),
    CONSTRAINT fk_presence_classroom FOREIGN KEY (classroom_id) REFERENCES classroom(id)
);