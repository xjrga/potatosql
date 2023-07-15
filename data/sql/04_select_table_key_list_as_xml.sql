CREATE PROCEDURE select_table_key_list_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc = '';
SET doc2 = '';
SELECT '<table_key_list>' INTO doc FROM (VALUES (0));
SET doc2 = doc2 + doc + CHAR(10) ;
SET doc = '';
FOR SELECT
    schema_id,
    table_id,
    key_id,
    key_name,
    datatype_id,
    key_order,
    CASEWHEN( is_primary_key, 'true', 'false' ) as primary_key,
    CASEWHEN( is_foreign_key, 'true', 'false' ) as foreign_key
FROM table_key WHERE schema_id = v_schema_id DO
SET doc = doc
 + '<table_key>' + CHAR(10)
 + '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
 + '<table_id>' + table_id + '</table_id>' + CHAR(10)
 + '<key_id>' + key_id + '</key_id>' + CHAR(10)
 + '<key_name>' + key_name + '</key_name>' + CHAR(10)
 + '<datatype_id>' + datatype_id + '</datatype_id>' + CHAR(10)
 + '<key_order>' + key_order + '</key_order>' + CHAR(10)
 + '<is_primary_key>' + primary_key + '</is_primary_key>' + CHAR(10)
 + '<is_foreign_key>' + foreign_key + '</is_foreign_key>' + CHAR(10)
 + '</table_key>' + CHAR(10);
SET doc2 = doc2 + doc;
SET doc = '';
END FOR;
SET doc = '</table_key_list>';
SET v_doc = doc2 + doc + CHAR(10);
END;
/
