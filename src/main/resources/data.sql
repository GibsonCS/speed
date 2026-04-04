INSERT INTO users (id, name, lastname, email, password)
VALUES ('1427140f-c970-4a4b-aeaa-2c9398f3e334', 'Gibson', 'Cruz', 'gibson.cruz@gmail.com', '12345678');

INSERT INTO ROLES (id, name)
VALUES ('318df173-32e1-4865-b380-e3f2343955b4', 'USER');

INSERT INTO USER_ROLES (user_id, role_id)
VALUES ('1427140f-c970-4a4b-aeaa-2c9398f3e334', '318df173-32e1-4865-b380-e3f2343955b4');