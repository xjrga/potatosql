CREATE PROCEDURE Database_schema_delete (
IN v_Schema_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM Database_schema
WHERE
Schema_id = v_Schema_id;
END;
/
CREATE PROCEDURE Database_table_delete (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM Database_table
WHERE
Schema_id = v_Schema_id AND
Table_id = v_Table_id;
END;
/
CREATE PROCEDURE Table_key_delete (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Key_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM Table_key
WHERE
Schema_id = v_Schema_id AND
Table_id = v_Table_id AND
Key_id = v_Key_id;
END;
/
CREATE PROCEDURE Relationship_delete (
IN v_Schema_id INTEGER,
IN v_Relationship_id INTEGER,
IN v_Table_id_parent INTEGER,
IN v_Table_id_child INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM Relationship
WHERE
Schema_id = v_Schema_id AND
Relationship_id = v_Relationship_id AND
Table_id_parent = v_Table_id_parent AND
Table_id_child = v_Table_id_child;
END;
/
CREATE PROCEDURE Identifying_relationship_key_pair_delete (
IN v_Schema_id INTEGER,
IN v_Relationship_id INTEGER,
IN v_Table_id_parent INTEGER,
IN v_Key_id_parent INTEGER,
IN v_Table_id_child INTEGER,
IN v_Key_id_child INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM Identifying_relationship_key_pair
WHERE
Schema_id = v_Schema_id AND
Relationship_id = v_Relationship_id AND
Table_id_parent = v_Table_id_parent AND
Key_id_parent = v_Key_id_parent AND
Table_id_child = v_Table_id_child AND
Key_id_child = v_Key_id_child;
END;
/
CREATE PROCEDURE Nonidentifying_relationship_key_pair_delete (
IN v_Schema_id INTEGER,
IN v_Relationship_id INTEGER,
IN v_Table_id_parent INTEGER,
IN v_Key_id_parent INTEGER,
IN v_Table_id_child INTEGER,
IN v_Key_id_child INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM Nonidentifying_relationship_key_pair
WHERE
Schema_id = v_Schema_id AND
Relationship_id = v_Relationship_id AND
Table_id_parent = v_Table_id_parent AND
Key_id_parent = v_Key_id_parent AND
Table_id_child = v_Table_id_child AND
Key_id_child = v_Key_id_child;
END;
/
