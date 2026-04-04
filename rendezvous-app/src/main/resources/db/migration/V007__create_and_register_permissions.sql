CREATE TABLE permissions (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR (50) NOT NULL UNIQUE
);

CREATE TABLE role_permissions (
    permission_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (permission_id, role_id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

INSERT INTO permissions (id, name) VALUES (1, 'CREATE_APPOINTMENT');
INSERT INTO permissions (id, name) VALUES (2, 'CANCEL_APPOINTMENT');
INSERT INTO permissions (id, name) VALUES (3, 'SEARCH_APPOINTMENT');
INSERT INTO permissions (id, name) VALUES (4, 'UPDATE_APPOINTMENT');
INSERT INTO permissions (id, name) VALUES (5, 'CREATE_AVAIBILITY');
INSERT INTO permissions (id, name) VALUES (6, 'CANCEL_AVAIBILITY');
INSERT INTO permissions (id, name) VALUES (7, 'SEARCH_AVAIBILITY');
INSERT INTO permissions (id, name) VALUES (8, 'UPDATE_AVAIBILITY');
INSERT INTO permissions (id, name) VALUES (9, 'CREATE_CLIENT_PROFILE');
INSERT INTO permissions (id, name) VALUES (10, 'CANCEL_CLIENT_PROFILE');
INSERT INTO permissions (id, name) VALUES (11, 'SEARCH_CLIENT_PROFILE');
INSERT INTO permissions (id, name) VALUES (12, 'UPDATE_CLIENT_PROFILE');
INSERT INTO permissions (id, name) VALUES (13, 'CREATE_PROVIDER_PROFILE');
INSERT INTO permissions (id, name) VALUES (14, 'CANCEL_PROVIDER_PROFILE');
INSERT INTO permissions (id, name) VALUES (15, 'SEARCH_PROVIDER_PROFILE');
INSERT INTO permissions (id, name) VALUES (16, 'UPDATE_PROVIDER_PROFILE');
INSERT INTO permissions (id, name) VALUES (17, 'CREATE_PROVIDER_SERVICE');
INSERT INTO permissions (id, name) VALUES (18, 'CANCEL_PROVIDER_SERVICE');
INSERT INTO permissions (id, name) VALUES (19, 'SEARCH_PROVIDER_SERVICE');
INSERT INTO permissions (id, name) VALUES (20, 'UPDATE_PROVIDER_SERVICE');


INSERT INTO role_permissions (role_id, permission_id) values (1, 1);
INSERT INTO role_permissions (role_id, permission_id) values (1, 2);
INSERT INTO role_permissions (role_id, permission_id) values (1, 4);
INSERT INTO role_permissions (role_id, permission_id) values (1, 9);
INSERT INTO role_permissions (role_id, permission_id) values (1, 10);
INSERT INTO role_permissions (role_id, permission_id) values (1, 12);
INSERT INTO role_permissions (role_id, permission_id) values (1, 15);

INSERT INTO role_permissions (role_id, permission_id) values (2, 1);
INSERT INTO role_permissions (role_id, permission_id) values (2, 2);
INSERT INTO role_permissions (role_id, permission_id) values (2, 3);
INSERT INTO role_permissions (role_id, permission_id) values (2, 4);
INSERT INTO role_permissions (role_id, permission_id) values (2, 5);
INSERT INTO role_permissions (role_id, permission_id) values (2, 6);
INSERT INTO role_permissions (role_id, permission_id) values (2, 13);
INSERT INTO role_permissions (role_id, permission_id) values (2, 14);
INSERT INTO role_permissions (role_id, permission_id) values (2, 16);
INSERT INTO role_permissions (role_id, permission_id) values (2, 17);
INSERT INTO role_permissions (role_id, permission_id) values (2, 18);
INSERT INTO role_permissions (role_id, permission_id) values (2, 19);
INSERT INTO role_permissions (role_id, permission_id) values (2, 20);

INSERT INTO role_permissions (role_id, permission_id) values (3, 1);
INSERT INTO role_permissions (role_id, permission_id) values (3, 2);
INSERT INTO role_permissions (role_id, permission_id) values (3, 3);
INSERT INTO role_permissions (role_id, permission_id) values (3, 4);
INSERT INTO role_permissions (role_id, permission_id) values (3, 5);
INSERT INTO role_permissions (role_id, permission_id) values (3, 6);
INSERT INTO role_permissions (role_id, permission_id) values (3, 7);
INSERT INTO role_permissions (role_id, permission_id) values (3, 8);
INSERT INTO role_permissions (role_id, permission_id) values (3, 9);
INSERT INTO role_permissions (role_id, permission_id) values (3, 10);
INSERT INTO role_permissions (role_id, permission_id) values (3, 11);
INSERT INTO role_permissions (role_id, permission_id) values (3, 12);
INSERT INTO role_permissions (role_id, permission_id) values (3, 13);
INSERT INTO role_permissions (role_id, permission_id) values (3, 14);
INSERT INTO role_permissions (role_id, permission_id) values (3, 15);
INSERT INTO role_permissions (role_id, permission_id) values (3, 16);
INSERT INTO role_permissions (role_id, permission_id) values (3, 17);
INSERT INTO role_permissions (role_id, permission_id) values (3, 18);
INSERT INTO role_permissions (role_id, permission_id) values (3, 19);
INSERT INTO role_permissions (role_id, permission_id) values (3, 20);



