CREATE PROCEDURE Data_key_insert (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Key_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Data_key (
Schema_id,
Table_id,
Key_id
) VALUES (
v_Schema_id,
v_Table_id,
v_Key_id
);
END;
/
CREATE PROCEDURE Database_schema_insert (
IN v_Schema_id INTEGER,
IN v_Schema_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Database_schema (
Schema_id,
Schema_name
) VALUES (
v_Schema_id,
v_Schema_name
);
END;
/
CREATE PROCEDURE Database_schema_insert_auto (
OUT newid INTEGER,
IN v_Schema_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
DECLARE id INTEGER;
SELECT id() INTO id FROM (VALUES (0));
INSERT INTO Database_schema (
Schema_id,
Schema_name
) VALUES (
id,
v_Schema_name
);
SET newid = id;
END;
/
CREATE PROCEDURE Database_table_insert (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Table_name LONGVARCHAR,
IN v_Is_dependent BOOLEAN
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Database_table (
Schema_id,
Table_id,
Table_name,
Is_dependent
) VALUES (
v_Schema_id,
v_Table_id,
v_Table_name,
v_Is_dependent
);
END;
/
CREATE PROCEDURE Database_table_insert_auto (
OUT newid INTEGER,
IN v_Schema_id INTEGER,
IN v_Table_name LONGVARCHAR,
IN v_Is_dependent BOOLEAN
)
MODIFIES SQL DATA BEGIN ATOMIC
DECLARE id INTEGER;
SELECT id() INTO id FROM (VALUES (0));
INSERT INTO Database_table (
Schema_id,
Table_id,
Table_name,
Is_dependent
) VALUES (
v_Schema_id,
id,
v_Table_name,
v_Is_dependent
);
SET newid = id;
END;
/
CREATE PROCEDURE Datatype_insert (
IN v_Datatype_id INTEGER,
IN v_Datatype_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Datatype (
Datatype_id,
Datatype_name
) VALUES (
v_Datatype_id,
v_Datatype_name
);
END;
/
CREATE PROCEDURE Identifying_relationship_insert (
IN v_Schema_id INTEGER,
IN v_Relationship_id INTEGER,
IN v_Table_id_parent INTEGER,
IN v_Table_id_child INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Identifying_relationship (
Schema_id,
Relationship_id,
Table_id_parent,
Table_id_child
) VALUES (
v_Schema_id,
v_Relationship_id,
v_Table_id_parent,
v_Table_id_child
);
END;
/
CREATE PROCEDURE Identifying_relationship_key_pair_insert (
IN v_Schema_id INTEGER,
IN v_Relationship_id INTEGER,
IN v_Table_id_parent INTEGER,
IN v_Key_id_parent INTEGER,
IN v_Table_id_child INTEGER,
IN v_Key_id_child INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Identifying_relationship_key_pair (
Schema_id,
Relationship_id,
Table_id_parent,
Key_id_parent,
Table_id_child,
Key_id_child
) VALUES (
v_Schema_id,
v_Relationship_id,
v_Table_id_parent,
v_Key_id_parent,
v_Table_id_child,
v_Key_id_child
);
END;
/
CREATE PROCEDURE Nonidentifying_relationship_insert (
IN v_Schema_id INTEGER,
IN v_Relationship_id INTEGER,
IN v_Table_id_parent INTEGER,
IN v_Table_id_child INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Nonidentifying_relationship (
Schema_id,
Relationship_id,
Table_id_parent,
Table_id_child
) VALUES (
v_Schema_id,
v_Relationship_id,
v_Table_id_parent,
v_Table_id_child
);
END;
/
CREATE PROCEDURE Nonidentifying_relationship_key_pair_insert (
IN v_Schema_id INTEGER,
IN v_Relationship_id INTEGER,
IN v_Table_id_parent INTEGER,
IN v_Key_id_parent INTEGER,
IN v_Table_id_child INTEGER,
IN v_Key_id_child INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Nonidentifying_relationship_key_pair (
Schema_id,
Relationship_id,
Table_id_parent,
Key_id_parent,
Table_id_child,
Key_id_child
) VALUES (
v_Schema_id,
v_Relationship_id,
v_Table_id_parent,
v_Key_id_parent,
v_Table_id_child,
v_Key_id_child
);
END;
/
CREATE PROCEDURE Primary_key_insert (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Key_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Primary_key (
Schema_id,
Table_id,
Key_id
) VALUES (
v_Schema_id,
v_Table_id,
v_Key_id
);
END;
/
CREATE PROCEDURE Relationship_insert (
IN v_Schema_id INTEGER,
IN v_Relationship_id INTEGER,
IN v_Table_id_parent INTEGER,
IN v_Table_id_child INTEGER,
IN v_Is_identifying BOOLEAN
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Relationship (
Schema_id,
Relationship_id,
Table_id_parent,
Table_id_child,
Is_identifying
) VALUES (
v_Schema_id,
v_Relationship_id,
v_Table_id_parent,
v_Table_id_child,
v_Is_identifying
);
END;
/
CREATE PROCEDURE Relationship_insert_auto (
OUT newid INTEGER,
IN v_Schema_id INTEGER,
IN v_Table_id_parent INTEGER,
IN v_Table_id_child INTEGER,
IN v_Is_identifying BOOLEAN
)
MODIFIES SQL DATA BEGIN ATOMIC
DECLARE id INTEGER;
SELECT id() INTO id FROM (VALUES (0));
INSERT INTO Relationship (
Schema_id,
Relationship_id,
Table_id_parent,
Table_id_child,
Is_identifying
) VALUES (
v_Schema_id,
id,
v_Table_id_parent,
v_Table_id_child,
v_Is_identifying
);
SET newid = id;
END;
/
CREATE PROCEDURE Table_key_insert (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Key_id INTEGER,
IN v_Key_name LONGVARCHAR,
IN v_Datatype_id INTEGER,
IN v_Key_order INTEGER,
IN v_Is_primary_key BOOLEAN,
IN v_Is_foreign_key BOOLEAN
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Table_key (
Schema_id,
Table_id,
Key_id,
Key_name,
Datatype_id,
Key_order,
Is_primary_key,
Is_foreign_key
) VALUES (
v_Schema_id,
v_Table_id,
v_Key_id,
v_Key_name,
v_Datatype_id,
v_Key_order,
v_Is_primary_key,
v_Is_foreign_key
);
END;
/
CREATE PROCEDURE Table_key_insert_auto (
OUT newid INTEGER,
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Key_name LONGVARCHAR,
IN v_Datatype_id INTEGER,
IN v_Key_order INTEGER,
IN v_Is_primary_key BOOLEAN
)
MODIFIES SQL DATA BEGIN ATOMIC
DECLARE id INTEGER;
SELECT id() INTO id FROM (VALUES (0));
INSERT INTO Table_key (
Schema_id,
Table_id,
Key_id,
Key_name,
Datatype_id,
Key_order,
Is_primary_key
) VALUES (
v_Schema_id,
v_Table_id,
id,
v_Key_name,
v_Datatype_id,
v_Key_order,
v_Is_primary_key
);
SET newid = id;
END;
/
