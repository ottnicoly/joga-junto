CREATE TABLE classroom (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    class_type VARCHAR(15) NOT NULL,
    class_time VARCHAR(10) NOT NULL,
    teacher_id INTEGER UNIQUE NOT NULL,
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(id)
);

CREATE TABLE classroom_days (
    classroom_id INTEGER NOT NULL,
    day VARCHAR(20) NOT NULL,
    CONSTRAINT fk_classroom_days FOREIGN KEY (classroom_id) REFERENCES classroom(id)
);