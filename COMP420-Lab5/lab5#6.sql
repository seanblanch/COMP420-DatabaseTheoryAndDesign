use imdb;

DROP PROCEDURE IF EXISTS sp_worked_on;

DELIMITER //
CREATE PROCEDURE sp_worked_on(IN `name` varchar(105), category varchar(20))
BEGIN
	SELECT titles.primary_title,
		query_runner()
	FROM people
		JOIN crew USING(person_id)
		JOIN titles USING(title_id)
	WHERE `name` = people.`name` 
		AND category = crew.category
	ORDER BY titles.primary_title;
END //
DELIMITER ;

CALL sp_worked_on('Ridley Scott', 'director');
