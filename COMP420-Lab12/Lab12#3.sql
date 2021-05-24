use largeco;

CREATE USER 'Manager'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON * . * TO 'Manager'@'localhost';