CREATE TABLE availability (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    day_of_week DATE NOT NULL,
    provider_id BIGINT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    FOREIGN KEY (provider_id) REFERENCES provider_profile(id)
);