DROP SCHEMA Public IF EXISTS CASCADE;
/
CREATE TABLE Database_schema
(
Schema_id INTEGER,
Schema_name LONGVARCHAR,
CONSTRAINT Database_schema_primaryKey PRIMARY KEY (Schema_id)
);
/
CREATE TABLE Database_table
(
Schema_id INTEGER,
Table_id INTEGER,
Table_name LONGVARCHAR,
CONSTRAINT Database_table_primaryKey PRIMARY KEY (Schema_id, Table_id)
);
/
CREATE TABLE Table_key
(
Schema_id INTEGER,
Table_id INTEGER,
Table_key_id INTEGER,
Table_key_name LONGVARCHAR,
Table_key_label LONGVARCHAR,
Table_key_is_pk BOOLEAN,
Table_key_type_id INTEGER,
Table_key_order INTEGER,
CONSTRAINT Table_key_primaryKey PRIMARY KEY (Schema_id, Table_id, Table_key_id)
);
/
CREATE TABLE Key_type
(
Key_type_id INTEGER,
Key_type_name LONGVARCHAR,
CONSTRAINT Key_type_primaryKey PRIMARY KEY (Key_type_id)
);
/
CREATE TABLE Relationship_type
(
Relationship_type_id INTEGER,
Relationship_type_name LONGVARCHAR,
CONSTRAINT Relationship_type_primaryKey PRIMARY KEY (Relationship_type_id)
);
/
CREATE TABLE Relationship
(
Schema_id INTEGER,
Parent_table_id INTEGER,
Child_table_id INTEGER,
Relationship_id INTEGER,
Relationship_type_id INTEGER,
CONSTRAINT Relationship_primaryKey PRIMARY KEY (Schema_id, Parent_table_id, Child_table_id, Relationship_id)
);
/
CREATE TABLE Relationship_key_pair
(
Schema_id INTEGER,
Parent_table_id INTEGER,
Child_table_id INTEGER,
Relationship_id INTEGER,
Parent_key_id INTEGER,
Child_key_id INTEGER,
CONSTRAINT Relationship_key_pair_primaryKey PRIMARY KEY (Schema_id, Parent_table_id, Child_table_id, Relationship_id, Parent_key_id, Child_key_id)
);
/
ALTER TABLE Database_table ADD CONSTRAINT R0_Database_schema_Database_table FOREIGN KEY ( Schema_id ) REFERENCES Database_schema ( Schema_id ) ON DELETE CASCADE;
/
ALTER TABLE Table_key ADD CONSTRAINT R1_Database_table_Table_key FOREIGN KEY ( Schema_id,Table_id ) REFERENCES Database_table ( Schema_id,Table_id ) ON DELETE CASCADE;
/
ALTER TABLE Relationship ADD CONSTRAINT R2_Database_table_Relationship FOREIGN KEY ( Schema_id,Parent_table_id ) REFERENCES Database_table ( Schema_id,Table_id ) ON DELETE CASCADE;
/
ALTER TABLE Relationship ADD CONSTRAINT R3_Database_table_Relationship FOREIGN KEY ( Schema_id,Child_table_id ) REFERENCES Database_table ( Schema_id,Table_id ) ON DELETE CASCADE;
/
ALTER TABLE Relationship_key_pair ADD CONSTRAINT R4_Table_key_Relationship_key_pair FOREIGN KEY ( Schema_id,Parent_table_id,Parent_key_id ) REFERENCES Table_key ( Schema_id,Table_id,Table_key_id ) ON DELETE CASCADE;
/
ALTER TABLE Relationship_key_pair ADD CONSTRAINT R5_Table_key_Relationship_key_pair FOREIGN KEY ( Schema_id,Child_table_id,Child_key_id ) REFERENCES Table_key ( Schema_id,Table_id,Table_key_id ) ON DELETE CASCADE;
/
ALTER TABLE Table_key ADD CONSTRAINT R6_Key_type_Table_key FOREIGN KEY ( Table_key_type_id ) REFERENCES Key_type ( Key_type_id ) ON DELETE SET NULL;
/
ALTER TABLE Relationship ADD CONSTRAINT R7_Relationship_type_Relationship FOREIGN KEY ( Relationship_type_id ) REFERENCES Relationship_type ( Relationship_type_id ) ON DELETE SET NULL;
/
ALTER TABLE Relationship_key_pair ADD CONSTRAINT R8_Relationship_Relationship_key_pair FOREIGN KEY ( Schema_id,Parent_table_id,Child_table_id,Relationship_id ) REFERENCES Relationship ( Schema_id,Parent_table_id,Child_table_id,Relationship_id ) ON DELETE CASCADE;
/


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
CREATE FUNCTION is_it_a_dependent_table(
--
IN V_Schema_id INTEGER,
--
IN V_Table_id INTEGER
--

) RETURNS BOOLEAN
--
READS SQL DATA
BEGIN ATOMIC
--
DECLARE V_Is_dependent BOOLEAN;
--
SELECT
CASE WHEN COUNT(DISTINCT A.Child) > 0 THEN TRUE ELSE FALSE END INTO V_Is_dependent
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
                  AND   A.Schema_id = V_Schema_id
                  AND   A.Child_Table_id = V_Table_id) A,
                 Relationship_key_pair B
            WHERE A.Schema_id = B.Schema_id
            AND   A.Parent_Table_id = B.Parent_Table_id
            AND   A.Child_Table_id = B.Child_Table_id
            AND   A.Relationship_id = B.Relationship_id
            AND   A.Relationship_type_id = 0) A,
           Table_key B,
           Table_key C
      WHERE A.Schema_id = B.Schema_id
      AND   A.Parent_Table_id = B.Table_id
      AND   A.Parent_key_id = B.Table_key_id
      AND   A.Schema_id = C.Schema_id
      AND   A.Child_Table_id = C.Table_id
      AND   A.Child_key_id = C.Table_key_id) A;
--
RETURN V_Is_dependent;
--
END;
/


call key_type_insert(0,'INTEGER');
call key_type_insert(1,'DOUBLE');
call key_type_insert(2,'STRING');
call key_type_insert(3,'DATE');
call key_type_insert(4,'BOOLEAN');
call key_type_insert(5,'TIME');
call key_type_insert(6,'TIMESTAMP');

call relationship_type_insert(0,'Identifying');
call relationship_type_insert(1,'Nonidentifying');


CREATE PROCEDURE select_schema_as_xml (
--
OUT v_doc LONGVARCHAR,
--
IN v_schema_id INTEGER
--
)
--
MODIFIES SQL DATA
--
BEGIN ATOMIC
--
DECLARE doc LONGVARCHAR;
--
SET doc = '';
--
SELECT
'<schema>' +CHAR(10)
+ '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
+ '<schema_name>' + schema_name + '</schema_name>' + CHAR(10)
+ '</schema>'
INTO doc FROM database_schema WHERE schema_id = v_schema_id;
--
SET v_doc = doc + CHAR (10);
--
END
/

CREATE PROCEDURE select_table_list_as_xml (
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
SELECT '<table_list>' INTO doc FROM (VALUES (0));
--
SET doc2 = doc2 + doc + CHAR(10) ;
--
SET doc = '';
------------------------------------------------------------
FOR SELECT
schema_id,
table_id,
table_name
FROM database_table WHERE schema_id = v_schema_id DO
--
SET doc = doc
+ '<table>' + CHAR(10)
+ '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
+ '<table_id>' + table_id + '</table_id>' + CHAR(10)
+ '<table_name>' + table_name + '</table_name>' + CHAR (10)
+ '</table>' + CHAR(10);
--
SET doc2 = doc2 + doc;
--
SET doc = '';
--
END FOR;
--
SET doc = '</table_list>';
--
SET v_doc = doc2 + doc + CHAR(10);
--
END
/


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


CREATE PROCEDURE select_relationship_list_as_xml (
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
SET doc2 = '<relationship_list>' + CHAR(10) ;
SET doc = '';
------------------------------------------------------------
FOR SELECT
schema_id,
parent_table_id,
child_table_id,
relationship_id,
relationship_type_id
FROM relationship WHERE schema_id = v_schema_id DO
--
SET doc = '<relationship>' + CHAR(10)
+ '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
+ '<parent_table_id>' + parent_table_id + '</parent_table_id>' + CHAR(10)
+ '<child_table_id>' + child_table_id + '</child_table_id>' + CHAR (10)
+ '<relationship_id>' + relationship_id + '</relationship_id>' + CHAR (10)
+ '<relationship_type_id>' + relationship_type_id + '</relationship_type_id>' + CHAR (10)
+ '</relationship>' + CHAR(10);
--
SET doc2 = doc2 + doc;
--
END FOR;
--
SET v_doc = doc2 + '</relationship_list>' + CHAR(10);
--
END
/


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


CREATE PROCEDURE export_xml (IN v_schema_id INTEGER)
--
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
--
BEGIN ATOMIC
--
DECLARE TABLE temp ( txt LONGVARCHAR);
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
--
SET doc2 = '<potatosql' + CHAR(10) + 'xmlns:xsi=''http://www.w3.org/2001/XMLSchema-instance''' + CHAR(10) + 'xsi:noNamespaceSchemaLocation=''https://xjrga.github.io/schemas/potatosql.xsd''>' + CHAR (10);
SET doc = '';
--
call select_schema_as_xml (doc,v_schema_id);
--
SET doc2 = doc2 + doc;
--
call select_table_list_as_xml (doc,v_schema_id);
--
SET doc2 = doc2 + doc;
--
call select_key_list_as_xml (doc,v_schema_id);
--
SET doc2 = doc2 + doc;
--
call select_relationship_list_as_xml (doc,v_schema_id);
--
SET doc2 = doc2 + doc;
--
call select_key_pair_list_as_xml (doc,v_schema_id);
--
SET doc2 = doc2 + doc + '</potatosql>';
--
INSERT INTO temp (txt) VALUES (doc2);
--
BEGIN ATOMIC
--
DECLARE result CURSOR
FOR
SELECT *
FROM temp;
--
OPEN result;
--
END;
--
END
/


