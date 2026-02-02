CREATE TABLE appointment (
      id BIGINT PRIMARY KEY AUTO_INCREMENT,
      provider_id BIGINT NOT NULL,
      service_id BIGINT NOT NULL,
      client_id BIGINT NOT NULL,
      day_of_week VARCHAR(10) NOT NULL,
      start_time TIME NOT NULL,
      end_time TIME NOT NULL,
      status VARCHAR(20) NOT NULL, -- SCHEDULED / CANCELLED / DONE
      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (client_id) REFERENCES client_profile(id),
      FOREIGN KEY (provider_id) REFERENCES provider_profile(id),
      FOREIGN KEY (service_id) REFERENCES provider_service(id)
  );