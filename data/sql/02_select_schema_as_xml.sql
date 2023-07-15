CREATE PROCEDURE select_schema_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
SET doc = '';
SELECT
'<database_schema>' +CHAR(10)
+ '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
+ '<schema_name>' + schema_name + '</schema_name>' + CHAR(10)
+ '</database_schema>'
INTO doc FROM database_schema WHERE schema_id = v_schema_id;
SET v_doc = doc + CHAR (10);
END;
/
