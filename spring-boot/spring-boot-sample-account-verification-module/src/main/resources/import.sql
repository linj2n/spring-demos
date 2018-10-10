INSERT INTO vm_authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO vm_authority (id, name) VALUES (2, 'ROLE_USER');
INSERT INTO vm_user (activated, activation_key, email, login, password, reset_key, reset_date, username) VALUES (1, NULL, 'admin@example.com', 'admin', '$2a$10$7minjtSHWO3bDAgaCBzpnelcsE6e6D4ZZ4O.dPqzqdPg5Hxfr..Cu', NULL, NULL,'admin');
INSERT INTO vm_user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO vm_user (activated, activation_key, email, login, password, reset_key, reset_date, username) VALUES (1, NULL, 'linj2n@163.com', 'linj2n', '$2a$10$7minjtSHWO3bDAgaCBzpnelcsE6e6D4ZZ4O.dPqzqdPg5Hxfr..Cu', NULL, NULL,'linj2n');
INSERT INTO vm_user_authority (user_id, authority_id) VALUES (2, 1);