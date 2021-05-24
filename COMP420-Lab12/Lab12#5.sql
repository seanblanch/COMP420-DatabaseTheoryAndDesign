use largeco;

CREATE USER 'SeasonalEmployee'@'localhost' IDENTIFIED BY 'password';

GRANT SELECT ON largeco.lg_recent_cust_purchases TO 'SeasonalEmployee'@'localhost';

select * from mysql.user;