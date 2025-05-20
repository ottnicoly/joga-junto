CREATE TABLE student (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    mother_name VARCHAR(100) NOT NULL,
    father_name VARCHAR(100) NOT NULL,
    school VARCHAR(255) NOT NULL,
    grade CHAR(2) NOT NULL,
    period VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    cpf VARCHAR(11)
);