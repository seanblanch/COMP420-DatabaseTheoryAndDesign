create database bike_sharing;

use bike_sharing;

CREATE TABLE station(
station_id smallint not null,
station_name text,
latitude real,
longitude real,
dock_count smallint,
city text,
installation_date date,
zip_code char(5),
PRIMARY KEY (station_id)
);

CREATE TABLE trip(
id integer not null,
duration integer,
start_time timestamp,
start_station_name text,
start_station_id smallint,
end_time timestamp,
end_station_name text,
end_station_id smallint,
bike_id smallint,
PRIMARY KEY (id),
FOREIGN KEY (start_station_id) REFERENCES  station(station_id),
FOREIGN KEY (end_station_id) REFERENCES  station(station_id)
);

CREATE TABLE weather(
weather_date date not null,
max_temp real,
mean_temp real,
min_temp real,
max_visibility_miles real,
mean_visibility_miles real,
min_visibility_miles real,
max_wind_speed_mph real,
mean_wind_speed_mph real,
max_gust_speed_mph real,
cloud_cover real,
events text,
wind_dir_degrees real,
zip_code char(5) not null,
PRIMARY KEY (weather_date, zip_code)
);
