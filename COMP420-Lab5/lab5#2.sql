use imdb;

select query_runner(now());

CREATE VIEW avg_by_performer as 
    SELECT 
    people.name as 'Name',
	avg(ratings.rating) as AverageRating,
    query_runner(now()) as 'Query Runner / Date'
    from people
	join crew on people.person_id = crew.person_id
    join titles on crew.title_id = titles.title_id
    join ratings on titles.title_id = ratings.title_id
    where crew.category = 'actor' 
    OR crew.category = 'actress'
    group by people.name
	order by AverageRating desc;
    
    select * from avg_by_performer;
    
    DROP VIEW avg_by_performer;