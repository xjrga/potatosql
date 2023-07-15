CREATE PROCEDURE Database_schema_update (
IN v_Schema_id INTEGER,
IN v_Schema_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE Database_schema
SET
Schema_name = v_Schema_name
WHERE
Schema_id = v_Schema_id;
END;
/
CREATE PROCEDURE Database_table_update (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Table_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE Database_table
SET
Table_name = v_Table_name
WHERE
Schema_id = v_Schema_id AND
Table_id = v_Table_id;
END;
/
CREATE PROCEDURE Table_key_update(
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Key_id INTEGER,
IN v_Datatype_id INTEGER,
IN v_Key_name LONGVARCHAR,
IN v_Key_order INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE Table_key
SET
Key_name = v_Key_name,
Datatype_id = v_Datatype_id,
Key_order = v_Key_order
WHERE
Schema_id = v_Schema_id AND
Table_id = v_Table_id AND
Key_id = v_Key_id;
END;
/
CREATE PROCEDURE set_foreign_key_true (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Key_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE Table_key
SET
Is_foreign_key = TRUE
WHERE
Schema_id = v_Schema_id AND
Table_id = v_Table_id AND
Key_id = v_Key_id;
END;
/
CREATE PROCEDURE set_foreign_key_false (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Key_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE Table_key
SET
Is_foreign_key = FALSE
WHERE
Schema_id = v_Schema_id AND
Table_id = v_Table_id AND
Key_id = v_Key_id;
END;
/
CREATE PROCEDURE set_dependent_entity_true (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE Database_table
SET
Is_dependent = TRUE
WHERE
Schema_id = v_Schema_id AND
Table_id = v_Table_id;
END;
/
CREATE PROCEDURE set_dependent_entity_false (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE Database_table
SET
Is_dependent = FALSE
WHERE
Schema_id = v_Schema_id AND
Table_id = v_Table_id;
END;
/

