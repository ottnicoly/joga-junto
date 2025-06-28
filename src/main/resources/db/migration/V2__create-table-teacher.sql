CREATE TABLE teacher (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    user_id UUID UNIQUE,
    CONSTRAINT fk_teacher_user FOREIGN KEY (user_id) REFERENCES "users" (user_id)
);