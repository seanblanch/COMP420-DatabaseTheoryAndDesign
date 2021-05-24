use imdb;

select query_runner(now());

CREATE VIEW tv_seasons as 
	SELECT
    titles.primary_title as 'Show Title',
    max(episodes.season_number) as Seasons,
    count(*) as Episodes,
    query_runner(now()) as 'Query Runner / Date'
    from titles
    join episodes on titles.title_id = episodes.show_title_id
    group by show_title_id
    order by Seasons asc, 
    Episodes desc;
    
    select * from tv_seasons;
    
       DROP VIEW tv_seasons;
       
	select * from titles;
    select * from episodes;
    select * from people;