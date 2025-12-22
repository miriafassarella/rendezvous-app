CREATE TABLE client_profiles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    phone VARCHAR(20),
    user_id BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_client_user
        FOREIGN KEY (user_id) REFERENCES user(id)
);