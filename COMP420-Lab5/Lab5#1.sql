DROP FUNCTION IF EXISTS query_runner;

CREATE FUNCTION query_runner()
RETURNS CHAR(50) deterministic
RETURN CONCAT('Queried by Sean Blanchard on ', now());

select query_runner()  as 'Query Runner / Date';

