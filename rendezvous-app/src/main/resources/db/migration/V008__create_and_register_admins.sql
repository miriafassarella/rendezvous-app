CREATE TABLE admin_profile (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    phone VARCHAR(20),
    user_id BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_admin_user
        FOREIGN KEY (user_id) REFERENCES user(id)
);


INSERT INTO admin_profile (first_name, last_name, phone, user_id)
VALUES ('Miria', 'Fassarela', '4188887777', 1);