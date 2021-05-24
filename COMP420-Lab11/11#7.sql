SELECT TABLE_SCHEMA AS DB_Name,
Table_name,
index_length/1024/1024 AS Index_Size
FROM information_schema.tables
WHERE table_type = 'BASE TABLE'
AND table_schema NOT IN ('information_schema', 'sys', 'performance_schema', 'mysql')
AND table_schema = 'largeco' ORDER BY index_size
DESC;