CREATE TABLE provider_profile (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(150),
    phone VARCHAR(20),
    description VARCHAR(100),
    user_id BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_provider_user
        FOREIGN KEY (user_id) REFERENCES user(id)
);