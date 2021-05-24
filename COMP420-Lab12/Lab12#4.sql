use largeco;

SET PERSIST partial_revokes = ON;

CREATE USER 'Employee'@'localhost' IDENTIFIED BY 'password';

GRANT SELECT ON * . * TO 'Employee'@'localhost';
GRANT CREATE, UPDATE ON largeco.lginvoice TO 'Employee'@'localhost';
GRANT CREATE, UPDATE ON largeco.lgline TO 'Employee'@'localhost';
GRANT CREATE, UPDATE ON largeco.lgcustomer TO 'Employee'@'localhost';
REVOKE SELECT ON largeco.lgsalary_history TO 'Employee'@'localhost';
