CREATE PROCEDURE export_as_xml (IN v_schema_id INTEGER)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE TABLE temp ( txt LONGVARCHAR);
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc2 = '<potatosql' + CHAR(10) + 'xmlns:xsi=''http://www.w3.org/2001/XMLSchema-instance''' + CHAR(10) + 'xsi:noNamespaceSchemaLocation=''potatosql.xsd''>' + CHAR (10);
SET doc = '';
call select_schema_as_xml (doc,v_schema_id);
SET doc2 = doc2 + doc;
call select_table_list_as_xml (doc,v_schema_id);
SET doc2 = doc2 + doc;
call select_table_key_list_as_xml (doc,v_schema_id);
SET doc2 = doc2 + doc;
call select_primary_key_list_as_xml (doc,v_schema_id);
SET doc2 = doc2 + doc;
call select_data_key_list_as_xml (doc,v_schema_id);
SET doc2 = doc2 + doc;
call select_relationship_list_as_xml (doc,v_schema_id);
SET doc2 = doc2 + doc;
call select_identifying_relationship_list_as_xml (doc,v_schema_id);
SET doc2 = doc2 + doc;
call select_nonidentifying_relationship_list_as_xml (doc,v_schema_id);
SET doc2 = doc2 + doc;
call select_identifying_relationship_key_pair_list_as_xml (doc,v_schema_id);
SET doc2 = doc2 + doc;
call select_nonidentifying_relationship_key_pair_list_as_xml (doc,v_schema_id);
SET doc2 = doc2 + doc + '</potatosql>';
INSERT INTO temp (txt) VALUES (doc2);
BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT *
FROM temp;
OPEN result;
END;
END;
/
