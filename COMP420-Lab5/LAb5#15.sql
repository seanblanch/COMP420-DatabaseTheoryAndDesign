use imdb;

DELIMITER //
CREATE TRIGGER people_upd
BEFORE 
INSERT ON people
FOR EACH ROW
BEGIN
	SET NEW.duplicate_insert_count = duplicate_insert_count + 1;
END; //
delimiter ;