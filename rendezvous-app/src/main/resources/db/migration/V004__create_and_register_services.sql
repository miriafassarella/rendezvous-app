CREATE TABLE service (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    duration_minutes INTEGER NOT NULL,
    buffer_minutes INTEGER DEFAULT 0,
    price DECIMAL(10, 2) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    provider_profile_id BIGINT NOT NULL,

    CONSTRAINT fk_service_provider
            FOREIGN KEY (provider_profile_id)
            REFERENCES provider_profile(id)
    );

