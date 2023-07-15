CREATE PROCEDURE select_identifying_relationship_key_pair_list_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc = '';
SET doc2 = '';
SELECT '<identifying_relationship_key_pair_list>' INTO doc FROM (VALUES (0));
SET doc2 = doc2 + doc + CHAR(10) ;
SET doc = '';
FOR SELECT
schema_id,
relationship_id,
table_id_parent,
key_id_parent,
table_id_child,
key_id_child
FROM identifying_relationship_key_pair WHERE schema_id = v_schema_id DO
SET doc = doc
+ '<identifying_relationship_key_pair>' + CHAR(10)
+ '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
+ '<relationship_id>' + relationship_id + '</relationship_id>' + CHAR(10)
+ '<table_id_parent>' + table_id_parent + '</table_id_parent>' + CHAR(10)
+ '<key_id_parent>' + key_id_parent + '</key_id_parent>' + CHAR(10)
+ '<table_id_child>' + table_id_child + '</table_id_child>' + CHAR(10)
+ '<key_id_child>' + key_id_child + '</key_id_child>' + CHAR(10)
+ '</identifying_relationship_key_pair>' + CHAR(10);
SET doc2 = doc2 + doc;
SET doc = '';
END FOR;
SET doc = '</identifying_relationship_key_pair_list>';
SET v_doc = doc2 + doc + CHAR(10);
END;
/
