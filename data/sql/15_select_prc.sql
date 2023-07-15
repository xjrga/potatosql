CREATE PROCEDURE Database_schema_select()
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Schema_id,
Schema_name
FROM
Database_schema
ORDER BY Schema_name;
OPEN result;
END;
/
CREATE PROCEDURE Database_table_select (
IN v_Schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Schema_id,
Table_id,
Table_name,
Is_dependent
FROM
Database_table
WHERE
Schema_id = v_Schema_id
ORDER BY Table_name;
OPEN result;
END;
/
CREATE PROCEDURE Table_key_select (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT schema_id,
       table_id,
       key_id,
       datatype_id,
       key_name,
       is_primary_key,
       b.datatype_name,
       key_order
FROM table_key a,
     datatype b
WHERE a.schema_id = v_Schema_id
AND   a.table_id = v_Table_id
AND   a.datatype_id = b.datatype_id
ORDER BY a.key_order;
OPEN result;
END;
/
CREATE PROCEDURE Datatype_select()
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Datatype_id,
Datatype_name
FROM
Datatype;
OPEN result;
END;
/
CREATE PROCEDURE Relationship_select (
IN v_Schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT schema_id,
       relationship_id,
       table_id_parent,
       table_id_child,
       b.table_name AS parent,
       c.table_name AS child,
       is_identifying
FROM relationship a,
     database_table b,
     database_table c
WHERE a.schema_id = v_Schema_id
AND   a.schema_id = b.schema_id
AND   a.schema_id = c.schema_id
AND   a.table_id_parent = b.table_id
AND   a.table_id_child = c.table_id
ORDER BY b.table_name, c.table_name;
OPEN result;
END;
/
CREATE PROCEDURE Relationship_key_pair_select (
IN v_Schema_id INTEGER,
IN v_Relationship_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT a.schema_id,
       a.relationship_id,
       a.table_id_parent,
       a.table_id_child,
       a.key_id_parent,
       a.key_id_child,
       b.key_name AS parent,
       c.key_name AS child
FROM (SELECT schema_id,
             relationship_id,
             table_id_parent,
             key_id_parent,
             table_id_child,
             key_id_child
      FROM identifying_relationship_key_pair
      WHERE schema_id = v_Schema_id
      AND   relationship_id = v_Relationship_id
      UNION
      SELECT schema_id,
             relationship_id,
             table_id_parent,
             key_id_parent,
             table_id_child,
             key_id_child
      FROM nonidentifying_relationship_key_pair
      WHERE schema_id = v_Schema_id
      AND   relationship_id = v_Relationship_id) a,
     table_key b,
     table_key c
WHERE a.schema_id = b.schema_id
AND   a.schema_id = c.schema_id
AND   a.table_id_parent = b.table_id
AND   a.key_id_parent = b.key_id
AND   a.table_id_child = c.table_id
AND   a.key_id_child = c.key_id
ORDER BY b.key_name, c.key_name;
OPEN result;
END;
/
CREATE PROCEDURE Table_child_key_select (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Is_pk BOOLEAN
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT schema_id,
       table_id,
       key_id,
       datatype_id,
       key_name,
       is_primary_key,
       key_order
FROM table_key a
WHERE a.schema_id = v_Schema_id
AND   a.table_id = v_Table_id
AND   a.is_primary_key = v_Is_pk
ORDER BY a.key_name;
OPEN result;
END;
/
CREATE PROCEDURE Table_parent_key_select (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT schema_id,
       table_id,
       key_id,
       datatype_id,
       key_name,
       is_primary_key,
       key_order
FROM table_key a
WHERE a.schema_id = v_Schema_id
AND   a.table_id = v_Table_id
AND   a.is_primary_key = true
ORDER BY a.key_name;
OPEN result;
END;
/
CREATE PROCEDURE find_table_keys (
IN v_schema_id INTEGER,
IN v_table_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT a.key_name,a.is_primary_key,b.datatype_name, a.key_order, a.is_foreign_key
FROM table_key a, datatype b
WHERE schema_id = v_schema_id
AND a.table_id =  v_table_id
AND a.datatype_id = b.datatype_id
ORDER BY a.key_order;
OPEN result;
END;
/
CREATE FUNCTION get_identifying_relationship_key_ids_for_child(
v_schema_id INTEGER,
v_relationship_id INTEGER
) RETURNS LONGVARCHAR
READS SQL DATA BEGIN ATOMIC
DECLARE pk LONGVARCHAR;
DECLARE txt LONGVARCHAR;
SET pk = '';
FOR SELECT b.key_name as name, b.key_order FROM identifying_relationship_key_pair a, table_key b WHERE a.schema_id = b.schema_id AND a.table_id_child = b.table_id AND a.key_id_child = b.key_id AND a.schema_id = v_schema_id AND  a.relationship_id = v_relationship_id ORDER BY b.key_order DO
SET pk = pk  + name + ',';
END FOR;
RETURN SUBSTRING(pk,0,LENGTH(pk));
END;
/
CREATE FUNCTION get_identifying_relationship_key_ids_for_parent(
v_schema_id INTEGER,
v_relationship_id INTEGER
) RETURNS LONGVARCHAR
READS SQL DATA BEGIN ATOMIC
DECLARE pk LONGVARCHAR;
SET pk = '';
FOR SELECT b.key_name as name, b.key_order FROM identifying_relationship_key_pair a, table_key b WHERE a.schema_id = b.schema_id AND a.table_id_parent = b.table_id AND   a.key_id_parent = b.key_id AND   a.schema_id = v_schema_id AND  a.relationship_id = v_relationship_id ORDER BY b.key_order DO
SET pk = pk  + name + ',';
END FOR;
RETURN SUBSTRING(pk,0,LENGTH(pk));
END;
/
CREATE FUNCTION get_nonidentifying_relationship_key_ids_for_child(
v_schema_id INTEGER,
v_relationship_id INTEGER
) RETURNS LONGVARCHAR
READS SQL DATA BEGIN ATOMIC
DECLARE pk LONGVARCHAR;
DECLARE txt LONGVARCHAR;
SET pk = '';
FOR SELECT b.key_name as name, b.key_order FROM nonidentifying_relationship_key_pair a, table_key b WHERE a.schema_id = b.schema_id AND a.table_id_child = b.table_id AND   a.key_id_child = b.key_id AND   a.schema_id = v_schema_id AND  a.relationship_id = v_relationship_id ORDER BY b.key_order DO
SET pk = pk  + name + ',';
END FOR;
RETURN SUBSTRING(pk,0,LENGTH(pk));
END;
/
CREATE FUNCTION get_nonidentifying_relationship_key_ids_for_parent(
v_schema_id INTEGER,
v_relationship_id INTEGER
) RETURNS LONGVARCHAR
READS SQL DATA BEGIN ATOMIC
DECLARE pk LONGVARCHAR;
SET pk = '';
FOR SELECT b.key_name as name, b.key_order FROM nonidentifying_relationship_key_pair a, table_key b WHERE a.schema_id = b.schema_id AND a.table_id_parent = b.table_id AND   a.key_id_parent = b.key_id AND   a.schema_id = v_schema_id AND  a.relationship_id = v_relationship_id ORDER BY b.key_order DO
SET pk = pk  + name + ',';
END FOR;
RETURN SUBSTRING(pk,0,LENGTH(pk));
END;
/
CREATE PROCEDURE find_relationships (
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT b.table_name AS parent,
       CASEWHEN( a.is_identifying, get_identifying_relationship_key_ids_for_parent(a.schema_id,a.relationship_id), get_nonidentifying_relationship_key_ids_for_parent(a.schema_id,a.relationship_id) ) as parent_key,
       c.table_name AS child,
       CASEWHEN( a.is_identifying, get_identifying_relationship_key_ids_for_child(a.schema_id,a.relationship_id), get_nonidentifying_relationship_key_ids_for_child(a.schema_id,a.relationship_id) ) as child_key,
       a.is_identifying
FROM relationship a,
     database_table b,
     database_table c
WHERE a.schema_id = b.schema_id
AND   a.schema_id = c.schema_id
AND   a.table_id_parent = b.table_id
AND   a.table_id_child = c.table_id
AND   a.schema_id = v_schema_id
ORDER BY child,parent;
OPEN result;
END;
/
