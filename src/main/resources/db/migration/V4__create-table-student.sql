CREATE TABLE student (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    mother_name VARCHAR(100) NOT NULL,
    father_name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    monthly_fee NUMERIC(10,2),
    classroom_id INTEGER,
    teacher_id INTEGER,
    CONSTRAINT fk_classroom FOREIGN KEY (classroom_id) REFERENCES "classroom" (id),
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES "teacher" (id)
);