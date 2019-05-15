SELECT routine_type,
       routine_name
FROM information_schema.routines
WHERE specific_schema = 'PUBLIC';
/

SELECT table_name
FROM information_schema.tables
WHERE table_schema = 'PUBLIC';
/

SELECT *
FROM information_schema.triggers;
/

SELECT *
FROM information_schema.views
WHERE table_schema =  'PUBLIC';
/


