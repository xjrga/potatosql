CREATE PROCEDURE select_relationship_list_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc = '';
SET doc2 = '';
SELECT '<relationship_list>' INTO doc FROM (VALUES (0));
SET doc2 = doc2 + doc + CHAR(10) ;
SET doc = '';
FOR SELECT
schema_id,
relationship_id,
table_id_parent,
table_id_child,
CASEWHEN( is_identifying, 'true', 'false' ) as identifying
FROM relationship WHERE schema_id = v_schema_id DO
SET doc = doc
+ '<relationship>' + CHAR(10)
+ '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
+ '<relationship_id>' + relationship_id + '</relationship_id>' + CHAR(10)
+ '<table_id_parent>' + table_id_parent + '</table_id_parent>' + CHAR(10)
+ '<table_id_child>' + table_id_child + '</table_id_child>' + CHAR(10)
+ '<is_identifying>' + identifying + '</is_identifying>' + CHAR(10)
+ '</relationship>' + CHAR(10);
SET doc2 = doc2 + doc;
SET doc = '';
END FOR;
SET doc = '</relationship_list>';
SET v_doc = doc2 + doc + CHAR(10);
END;
/
