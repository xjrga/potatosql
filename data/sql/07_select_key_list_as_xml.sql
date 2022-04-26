CREATE PROCEDURE select_key_list_as_xml (
--
OUT v_doc LONGVARCHAR,
--
IN v_schema_id INTEGER
--
)
--
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
--
BEGIN ATOMIC
--
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
--
SET doc = '';
SET doc2 = '';
--
SELECT '<key_list>' INTO doc FROM (VALUES (0));
--
SET doc2 = doc2 + doc + CHAR(10) ;
--
SET doc = '';
------------------------------------------------------------
FOR SELECT
    schema_id,
    table_id,
    table_key_id,
    table_key_name,
    table_key_label,
    lcase(cast(table_key_is_pk as LONGVARCHAR)) as table_key_is_pk,
    table_key_type_id,
    table_key_order
FROM table_key WHERE schema_id = v_schema_id DO
--
SET doc = doc
 + '<key>' + CHAR(10)
 + '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
 + '<table_id>' + table_id + '</table_id>' + CHAR(10)
 + '<key_id>' + table_key_id + '</key_id>' + CHAR(10)
 + '<key_name>' + table_key_name + '</key_name>' + CHAR(10)
 + '<key_label>' + table_key_label + '</key_label>' + CHAR(10)
 + '<key_is_pk>' + table_key_is_pk + '</key_is_pk>' + CHAR(10)
 + '<key_type_id>' + table_key_type_id + '</key_type_id>' + CHAR(10)
 + '<key_order>' + table_key_order + '</key_order>' + CHAR(10)
 + '</key>' + CHAR(10);
--
SET doc2 = doc2 + doc;
--
SET doc = '';
--
END FOR;
--
SET doc = '</key_list>';
--
SET v_doc = doc2 + doc + CHAR(10);
--
END
/
