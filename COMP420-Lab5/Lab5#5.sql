use imdb;

DROP PROCEDURE IF EXISTS sp_alternate_titles;

DELIMITER //
CREATE PROCEDURE sp_alternate_titles(IN InputTitle varchar(50))
BEGIN
SELECT
akas.title AS title,
query_runner()
FROM akas
JOIN titles ON akas.title_id = titles.title_id
WHERE InputTitle = titles.primary_title
ORDER BY akas.title;
END //
DELIMITER ;

CALL sp_alternate_titles('Frozen');
