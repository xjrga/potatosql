CREATE PROCEDURE database_schema_insert (
IN v_Schema_id INTEGER,
IN v_Schema_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
insert INTO Database_schema (
Schema_id,
Schema_name
) VALUES (
v_Schema_id,
v_Schema_name
);
END;
/
CREATE PROCEDURE database_schema_update (
IN v_Schema_id INTEGER,
IN v_Schema_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
Database_schema
SET
Schema_name = v_Schema_name
WHERE
Schema_id = v_Schema_id;
END;
/
CREATE PROCEDURE database_schema_delete (
IN v_Schema_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
Database_schema
WHERE
Schema_id = v_Schema_id;
END;
/
CREATE PROCEDURE database_schema_select_all ()
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

CREATE PROCEDURE database_table_insert (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Table_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
insert INTO Database_table (
Schema_id,
Table_id,
Table_name
) VALUES (
v_Schema_id,
v_Table_id,
v_Table_name
);
END;
/
CREATE PROCEDURE database_table_update (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Table_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
Database_table
SET
Table_name = v_Table_name
WHERE
Schema_id = v_Schema_id
AND
Table_id = v_Table_id;
END;
/
CREATE PROCEDURE database_table_delete (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
Database_table
WHERE
Schema_id = v_Schema_id
AND
Table_id = v_Table_id;
END;
/
CREATE PROCEDURE database_table_select (
IN v_Schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Schema_id,
Table_id,
Table_name
FROM
Database_table
WHERE
Schema_id = v_Schema_id
ORDER BY Table_name;
OPEN result;
END;
/
CREATE PROCEDURE database_table_select (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Schema_id,
Table_id,
Table_name
FROM
Database_table
WHERE
Schema_id = v_Schema_id
AND
Table_id = v_Table_id;
OPEN result;
END;
/

CREATE PROCEDURE table_key_insert (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Table_key_id INTEGER,
IN v_Table_key_name LONGVARCHAR,
IN v_Table_key_label LONGVARCHAR,
IN v_Table_key_is_pk BOOLEAN,
IN v_Table_key_type_id INTEGER,
IN v_Table_key_order INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
insert INTO Table_key (
Schema_id,
Table_id,
Table_key_id,
Table_key_name,
Table_key_label,
Table_key_is_pk,
Table_key_type_id,
Table_key_order
) VALUES (
v_Schema_id,
v_Table_id,
v_Table_key_id,
v_Table_key_name,
v_Table_key_label,
v_Table_key_is_pk,
v_Table_key_type_id,
v_Table_key_order
);
END;
/
CREATE PROCEDURE table_key_update (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Table_key_id INTEGER,
IN v_Table_key_name LONGVARCHAR,
IN v_Table_key_label LONGVARCHAR,
IN v_Table_key_is_pk BOOLEAN,
IN v_Table_key_type_id INTEGER,
IN v_Table_key_order INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
Table_key
SET
Table_key_name = v_Table_key_name,
Table_key_label = v_Table_key_label,
Table_key_is_pk = v_Table_key_is_pk,
Table_key_type_id = v_Table_key_type_id,
Table_key_order = v_Table_key_order
WHERE
Schema_id = v_Schema_id
AND
Table_id = v_Table_id
AND
Table_key_id = v_Table_key_id;
END;
/
CREATE PROCEDURE table_key_delete (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Table_key_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
Table_key
WHERE
Schema_id = v_Schema_id
AND
Table_id = v_Table_id
AND
Table_key_id = v_Table_key_id;
END;
/

CREATE PROCEDURE table_key_select (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Schema_id,
Table_id,
Table_key_id,
Table_key_name,
Table_key_label,
Table_key_is_pk,
Table_key_type_id,
Table_key_order
FROM
Table_key
WHERE
Schema_id = v_Schema_id
AND
Table_id = v_Table_id;
OPEN result;
END;
/

CREATE PROCEDURE table_key_select (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER,
IN v_Table_key_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Schema_id,
Table_id,
Table_key_id,
Table_key_name,
Table_key_label,
Table_key_is_pk,
Table_key_type_id,
Table_key_order
FROM
Table_key
WHERE
Schema_id = v_Schema_id
AND
Table_id = v_Table_id
AND
Table_key_id = v_Table_key_id;
OPEN result;
END;
/
CREATE PROCEDURE table_key_select_pk (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Schema_id,
Table_id,
Table_key_id,
Table_key_name,
Table_key_label,
Table_key_is_pk,
Table_key_type_id,
Table_key_order
FROM
Table_key
WHERE
Schema_id = v_Schema_id
AND
Table_id = v_Table_id
AND
Table_key_is_pk = true;
OPEN result;
END;
/
CREATE PROCEDURE table_key_select_npk (
IN v_Schema_id INTEGER,
IN v_Table_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Schema_id,
Table_id,
Table_key_id,
Table_key_name,
Table_key_label,
Table_key_is_pk,
Table_key_type_id,
Table_key_order
FROM
Table_key
WHERE
Schema_id = v_Schema_id
AND
Table_id = v_Table_id
AND
Table_key_is_pk = false;
OPEN result;
END;
/

CREATE PROCEDURE key_type_insert (
IN v_Key_type_id INTEGER,
IN v_Key_type_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
insert INTO Key_type (
Key_type_id,
Key_type_name
) VALUES (
v_Key_type_id,
v_Key_type_name
);
END;
/
CREATE PROCEDURE key_type_update (
IN v_Key_type_id INTEGER,
IN v_Key_type_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
Key_type
SET
Key_type_name = v_Key_type_name
WHERE
Key_type_id = v_Key_type_id;
END;
/
CREATE PROCEDURE key_type_delete (
IN v_Key_type_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
Key_type
WHERE
Key_type_id = v_Key_type_id;
END;
/

CREATE PROCEDURE relationship_type_insert (
IN v_Relationship_type_id INTEGER,
IN v_Relationship_type_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
insert INTO Relationship_type (
Relationship_type_id,
Relationship_type_name
) VALUES (
v_Relationship_type_id,
v_Relationship_type_name
);
END;
/
CREATE PROCEDURE relationship_type_update (
IN v_Relationship_type_id INTEGER,
IN v_Relationship_type_name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
Relationship_type
SET
Relationship_type_name = v_Relationship_type_name
WHERE
Relationship_type_id = v_Relationship_type_id;
END;
/
CREATE PROCEDURE relationship_type_delete (
IN v_Relationship_type_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
Relationship_type
WHERE
Relationship_type_id = v_Relationship_type_id;
END;
/
CREATE PROCEDURE relationship_type_select_all ()
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Relationship_type_id,
Relationship_type_name 
FROM
Relationship_type;
OPEN result;
END;
/

CREATE PROCEDURE relationship_insert (
IN v_Schema_id INTEGER,
IN v_Parent_table_id INTEGER,
IN v_Child_table_id INTEGER,
IN v_Relationship_id INTEGER,
IN v_Relationship_type_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
insert INTO Relationship (
Schema_id,
Parent_table_id,
Child_table_id,
Relationship_id,
Relationship_type_id
) VALUES (
v_Schema_id,
v_Parent_table_id,
v_Child_table_id,
v_Relationship_id,
v_Relationship_type_id
);
END;
/

CREATE PROCEDURE relationship_delete (
IN v_Schema_id INTEGER,
IN v_Parent_table_id INTEGER,
IN v_Child_table_id INTEGER,
IN v_Relationship_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
Relationship
WHERE
Schema_id = v_Schema_id
AND
Parent_table_id = v_Parent_table_id
AND
Child_table_id = v_Child_table_id
AND
Relationship_id = v_Relationship_id;
END;
/
CREATE PROCEDURE relationship_select (
IN v_Schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Schema_id,
Parent_table_id,
Child_table_id,
Relationship_id,
Relationship_type_id
FROM
Relationship
WHERE
Schema_id = v_Schema_id;
OPEN result;
END;
/
CREATE PROCEDURE relationship_multiple_select (IN V_Schema_id INTEGER) 
MODIFIES SQL DATA 
DYNAMIC RESULT SETS 1
BEGIN ATOMIC 
DECLARE RESULT CURSOR
FOR
SELECT Schema_id,
       Parent_table_id,
       B.Table_name AS Parent,
       Child_table_id,
       C.Table_name AS Child,
       Relationship_type_id,
       Relationship_id,
       D.Relationship_type_name
FROM Relationship A,
     Database_table B,
     Database_table C,
     Relationship_type D
WHERE Schema_id = V_Schema_id
AND   A.Schema_id = B.Schema_id
AND   A.Schema_id = C.Schema_id
AND   A.Parent_table_id = B.Table_id
AND   A.Child_table_id = C.Table_id
AND   A.Relationship_type_id = D.Relationship_type_id
ORDER BY Parent,Relationship_type_id,Child;
OPEN RESULT;
END;
/

CREATE PROCEDURE relationship_key_pair_insert (
IN v_Schema_id INTEGER,
IN v_Parent_table_id INTEGER,
IN v_Child_table_id INTEGER,
IN v_Relationship_id INTEGER,
IN v_Parent_key_id INTEGER,
IN v_Child_key_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
insert INTO Relationship_key_pair (
Schema_id,
Parent_table_id,
Child_table_id,
Relationship_id,
Parent_key_id,
Child_key_id
) VALUES (
v_Schema_id,
v_Parent_table_id,
v_Child_table_id,
v_Relationship_id,
v_Parent_key_id,
v_Child_key_id
);
END;
/
CREATE PROCEDURE relationship_key_pair_delete (
IN v_Schema_id INTEGER,
IN v_Parent_table_id INTEGER,
IN v_Child_table_id INTEGER,
IN v_Relationship_id INTEGER,
IN v_Parent_key_id INTEGER,
IN v_Child_key_id INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
Relationship_key_pair
WHERE
Schema_id = v_Schema_id
AND
Parent_table_id = v_Parent_table_id
AND
Child_table_id = v_Child_table_id
AND
Relationship_id = v_Relationship_id
AND
Parent_key_id = v_Parent_key_id
AND
Child_key_id = v_Child_key_id;
END;
/

CREATE PROCEDURE relationship_key_pair_multiple_select (
IN v_Schema_id INTEGER,
IN v_Parent_table_id INTEGER,
IN v_Child_table_id INTEGER,
IN v_Relationship_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Schema_id,
Parent_table_id,
Child_table_id,
Relationship_id,
Parent_key_id,
Child_key_id,
b.Table_key_name as Parent,
c.Table_key_name as Child
FROM
Relationship_key_pair a, Table_key b, Table_key c
WHERE
Schema_id = v_Schema_id
AND
Parent_table_id = v_Parent_table_id
AND
Child_table_id = v_Child_table_id
AND
Relationship_id = v_Relationship_id
AND 
a.Schema_id = b.Schema_id
AND
a.Parent_table_id = b.Table_id
AND
a.Parent_key_id = b.Table_key_id
AND
a.Schema_id = c.Schema_id
AND
a.Child_table_id = c.Table_id
AND
a.Child_key_id = c.Table_key_id;
OPEN result;
END;
/
CREATE PROCEDURE relationship_select_only_names (IN V_Schema_id INTEGER) 
LANGUAGE SQL 
MODIFIES SQL DATA
DYNAMIC RESULT SETS 1
BEGIN ATOMIC 
DECLARE RESULT CURSOR
FOR
SELECT A.Parent,
       A.Child,
       A.Relationship_id,
       A.Relationship_type_id,
       A.Parent_key_name,
       A.Child_key_name
FROM (SELECT A.Schema_id,
             A.Parent_Table_id,
             A.Child_Table_id,
             A.Relationship_id,
             A.Relationship_type_id,
             A.Parent,
             A.Child,
             A.Parent_key_id,
             A.Child_key_id,
             B.Table_key_name AS Parent_key_name,
             C.Table_key_name AS Child_key_name
      FROM (SELECT A.Schema_id,
                   A.Parent_Table_id,
                   A.Child_Table_id,
                   A.Relationship_id,
                   A.Relationship_type_id,
                   A.Parent,
                   A.Child,
                   B.Parent_key_id,
                   B.Child_key_id
            FROM (SELECT A.Schema_id,
                         A.Parent_Table_id,
                         A.Child_Table_id,
                         A.Relationship_id,
                         A.Relationship_type_id,
                         B.Table_name AS Parent,
                         C.Table_name AS Child
                  FROM Relationship A,
                       Database_table B,
                       Database_table C
                  WHERE A.Schema_id = B.Schema_id
                  AND   A.Parent_Table_id = B.Table_id
                  AND   A.Schema_id = C.Schema_id
                  AND   A.Child_Table_id = C.Table_id
                  AND   A.Schema_id = V_Schema_id) A,
                 Relationship_key_pair B
            WHERE A.Schema_id = B.Schema_id
            AND   A.Parent_Table_id = B.Parent_Table_id
            AND   A.Child_Table_id = B.Child_Table_id
            AND   A.Relationship_id = B.Relationship_id) A,
           Table_key B,
           Table_key C
      WHERE A.Schema_id = B.Schema_id
      AND   A.Parent_Table_id = B.Table_id
      AND   A.Parent_key_id = B.Table_key_id
      AND   A.Schema_id = C.Schema_id
      AND   A.Child_Table_id = C.Table_id
      AND   A.Child_key_id = C.Table_key_id) A;
OPEN RESULT;
END
/
CREATE PROCEDURE database_table_copy (IN V_Schema_id_OLD INTEGER,IN V_Schema_id_NEW INTEGER) 
MODIFIES SQL DATA BEGIN ATOMIC 
insert INTO Database_table
(
  Schema_id,
  Table_id,
  Table_name
)
SELECT V_Schema_id_NEW,
       Table_id,
       Table_name
FROM Database_table
WHERE Schema_id = V_Schema_id_OLD;
END;
/
CREATE PROCEDURE table_key_copy (IN v_Schema_id INTEGER,IN v_Table_id_Old INTEGER,IN v_Table_id_New INTEGER) 
MODIFIES SQL DATA 
BEGIN ATOMIC 
insert INTO Table_key
(
  Schema_id,
  Table_id,
  Table_key_id,
  Table_key_name,
  Table_key_label,
  Table_key_is_pk,
  Table_key_type_id,  
  Table_key_order
)
SELECT Schema_id,
       v_Table_id_New,
       Table_key_id,
       Table_key_name,
       Table_key_label,
       Table_key_is_pk,
       Table_key_type_id,       
       Table_key_order
FROM Table_key
WHERE Schema_id = v_Schema_id
AND
Table_id = v_Table_id_Old;
END;
/
CREATE PROCEDURE relationship_copy (IN V_Schema_id_OLD INTEGER,IN V_Schema_id_NEW INTEGER) 
MODIFIES SQL DATA BEGIN ATOMIC 
insert INTO Relationship
(
  Schema_id,
  Parent_Table_id,
  Child_Table_id,
  Relationship_id,
  Relationship_type_id)
SELECT V_Schema_id_NEW,
       Parent_Table_id,
       Child_Table_id,
       Relationship_id,
       Relationship_type_id
FROM Relationship
WHERE Schema_id = V_Schema_id_OLD;
END;
/
CREATE PROCEDURE table_key_copy (IN V_Schema_id_OLD INTEGER,IN V_Schema_id_NEW INTEGER) 
MODIFIES SQL DATA 
BEGIN ATOMIC 
insert INTO Table_key
(
  Schema_id,
  Table_id,
  Table_key_id,
  Table_key_name,
  Table_key_label,
  Table_key_is_pk,
  Table_key_type_id,  
  Table_key_order
)
SELECT V_Schema_id_NEW,
       Table_id,
       Table_key_id,
       Table_key_name,
       Table_key_label,
       Table_key_is_pk,
       Table_key_type_id,
       Table_key_order
FROM Table_key
WHERE Schema_id = V_Schema_id_OLD;
END;
/
CREATE PROCEDURE relationship_key_pair_copy (IN V_Schema_id_OLD INTEGER,IN V_Schema_id_NEW INTEGER) 
MODIFIES SQL DATA BEGIN ATOMIC
insert INTO Relationship_key_pair
(
  Schema_id,
  Parent_table_id,
  Child_table_id,
  Relationship_id,
  Parent_key_id,
  Child_key_id
)
SELECT V_Schema_id_NEW,
       Parent_table_id,
       Child_table_id,
       Relationship_id,
       Parent_key_id,
       Child_key_id
FROM Relationship_key_pair
WHERE Schema_id = V_Schema_id_OLD;
END;
/

CREATE PROCEDURE table_copy (
--
IN v_Schema_id_old INTEGER,
--
IN v_Table_id_old INTEGER,
--
IN v_Table_id_new INTEGER,
--
IN v_Table_name LONGVARCHAR
--
)
MODIFIES SQL DATA
BEGIN ATOMIC
call database_table_insert(v_Schema_id_old,v_Table_id_new,v_Table_name);
call table_key_copy(v_Schema_id_old,v_Table_id_old,v_Table_id_new);
END;
/

CREATE PROCEDURE schema_copy (
--
IN v_Schema_id_old INTEGER,
--
IN v_Schema_id_new INTEGER,
--
IN v_Schema_name_new LONGVARCHAR
--
)
MODIFIES SQL DATA
BEGIN ATOMIC
CALL database_schema_insert (v_Schema_id_new,v_Schema_name_new);
CALL database_table_copy (v_Schema_id_old,v_Schema_id_new);
CALL relationship_copy (v_Schema_id_old,v_Schema_id_new);
CALL table_key_copy (v_Schema_id_old,v_Schema_id_new);
CALL relationship_key_pair_copy (v_Schema_id_old,v_Schema_id_new);
END;
/

CREATE FUNCTION generate_id(
--
) RETURNS INTEGER
--
READS SQL DATA BEGIN ATOMIC
--
DECLARE v_id INTEGER;
--
SELECT  substring(''+rand(),4,5) INTO v_id FROM (VALUES(0));
--
RETURN v_id;
--
END;
/
CREATE PROCEDURE table_key_select_with_name (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Schema_id,
Table_id,
Table_key_id,
Table_key_name,
Table_key_label,
Table_key_is_pk,
Table_key_type_id,
Key_type_name,
Table_key_order
FROM
Table_key a, Key_type b
WHERE
a.Table_key_type_id = b.Key_type_id
AND
Schema_id = v_SchemaId
AND
Table_id = v_TableId
ORDER BY
Table_key_order,Table_key_name;
OPEN result;
END;
/
CREATE PROCEDURE key_type_select_all ()
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
Key_type_id,
Key_type_name 
FROM
Key_type;
OPEN result;
END;
/
