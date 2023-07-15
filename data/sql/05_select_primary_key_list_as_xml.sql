CREATE PROCEDURE select_primary_key_list_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc = '';
SET doc2 = '';
SELECT '<primary_key_list>' INTO doc FROM (VALUES (0));
SET doc2 = doc2 + doc + CHAR(10) ;
SET doc = '';
FOR SELECT
    schema_id,
    table_id,
    key_id
FROM primary_key WHERE schema_id = v_schema_id DO
SET doc = doc
 + '<primary_key>' + CHAR(10)
 + '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
 + '<table_id>' + table_id + '</table_id>' + CHAR(10)
 + '<key_id>' + key_id + '</key_id>' + CHAR(10)
 + '</primary_key>' + CHAR(10);
SET doc2 = doc2 + doc;
SET doc = '';
END FOR;
SET doc = '</primary_key_list>';
SET v_doc = doc2 + doc + CHAR(10);
END;
/
