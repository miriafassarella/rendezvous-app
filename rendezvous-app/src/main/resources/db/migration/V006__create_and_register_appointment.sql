CREATE TABLE appointment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    provider_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL, -- SCHEDULED / CANCELLED / DONE
    FOREIGN KEY (client_id) REFERENCES client_profile(id),
    FOREIGN KEY (provider_id) REFERENCES provider_profile(id),
    FOREIGN KEY (service_id) REFERENCES service(id)
);