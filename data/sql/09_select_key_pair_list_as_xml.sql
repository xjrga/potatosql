CREATE PROCEDURE select_key_pair_list_as_xml (
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
SET doc2 = '<key_pair_list>' + CHAR(10);
SET doc = '';
------------------------------------------------------------
FOR SELECT
schema_id,
parent_table_id,
child_table_id,
relationship_id,
parent_key_id,
child_key_id
FROM relationship_key_pair WHERE schema_id = v_schema_id DO
--
SET doc = '<key_pair>' + CHAR(10)
 + '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
 + '<parent_table_id>' + parent_table_id + '</parent_table_id>' + CHAR(10)
 + '<child_table_id>' + child_table_id + '</child_table_id>' + CHAR(10)
 + '<relationship_id>' + relationship_id + '</relationship_id>' + CHAR(10)
 + '<parent_key_id>' + parent_key_id + '</parent_key_id>' + CHAR(10)
 + '<child_key_id>' + child_key_id + '</child_key_id>' + CHAR(10)
 + '</key_pair>' + CHAR(10);
--
SET doc2 = doc2 + doc;
--
END FOR;
--
SET v_doc = doc2 + '</key_pair_list>' + CHAR(10);
--
END
/
