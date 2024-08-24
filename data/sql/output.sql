DROP SCHEMA potatosql IF EXISTS CASCADE;
/
CREATE SCHEMA potatosql;
/
SET SCHEMA potatosql;
/
--
CREATE FUNCTION id(
) RETURNS INTEGER
READS SQL DATA BEGIN ATOMIC
DECLARE v_id INTEGER;
SELECT  substring(''+rand(),4,5) INTO v_id FROM (VALUES(0));
RETURN v_id;
END;
/
--
CREATE TABLE Identifying_relationship_key_pair
(
        Schema_id INTEGER,
        Relationship_id INTEGER,
        Table_id_parent INTEGER,
        Key_id_parent INTEGER,
        Table_id_child INTEGER,
        Key_id_child INTEGER,
        CONSTRAINT Identifying_relationship_key_pair_primary_key PRIMARY KEY (Schema_id,Relationship_id,Table_id_parent,Key_id_parent,Table_id_child,Key_id_child)
);
/
CREATE TABLE Identifying_relationship
(
        Schema_id INTEGER,
        Relationship_id INTEGER,
        Table_id_parent INTEGER,
        Table_id_child INTEGER,
        CONSTRAINT Identifying_relationship_primary_key PRIMARY KEY (Schema_id,Relationship_id,Table_id_parent,Table_id_child)
);
/
CREATE TABLE Database_schema
(
        Schema_id INTEGER,
        Schema_name LONGVARCHAR,
        CONSTRAINT Database_schema_primary_key PRIMARY KEY (Schema_id)
);
/
CREATE TABLE Datatype
(
        Datatype_id INTEGER,
        Datatype_name LONGVARCHAR,
        CONSTRAINT Datatype_primary_key PRIMARY KEY (Datatype_id)
);
/
CREATE TABLE Table_key
(
        Schema_id INTEGER,
        Table_id INTEGER,
        Key_id INTEGER,
        Key_name LONGVARCHAR,
        Datatype_id INTEGER,
        Key_order INTEGER,
        Is_primary_key BOOLEAN,
        Is_foreign_key BOOLEAN DEFAULT FALSE,
        CONSTRAINT Table_key_primary_key PRIMARY KEY (Schema_id,Table_id,Key_id)
);
/
CREATE TABLE Nonidentifying_relationship_key_pair
(
        Schema_id INTEGER,
        Relationship_id INTEGER,
        Table_id_parent INTEGER,
        Key_id_parent INTEGER,
        Table_id_child INTEGER,
        Key_id_child INTEGER,
        CONSTRAINT Nonidentifying_relationship_key_pair_primary_key PRIMARY KEY (Schema_id,Relationship_id,Table_id_parent,Key_id_parent,Table_id_child,Key_id_child)
);
/
CREATE TABLE Database_table
(
        Schema_id INTEGER,
        Table_id INTEGER,
        Table_name LONGVARCHAR,
        Is_dependent BOOLEAN DEFAULT FALSE,
        CONSTRAINT Database_table_primary_key PRIMARY KEY (Schema_id,Table_id)
);
/
CREATE TABLE Nonidentifying_relationship
(
        Schema_id INTEGER,
        Relationship_id INTEGER,
        Table_id_parent INTEGER,
        Table_id_child INTEGER,
        CONSTRAINT Nonidentifying_relationship_primary_key PRIMARY KEY (Schema_id,Relationship_id,Table_id_parent,Table_id_child)
);
/
CREATE TABLE Primary_key
(
        Schema_id INTEGER,
        Table_id INTEGER,
        Key_id INTEGER,
        CONSTRAINT Primary_key_primary_key PRIMARY KEY (Schema_id,Table_id,Key_id)
);
/
CREATE TABLE Relationship
(
        Schema_id INTEGER,
        Relationship_id INTEGER,
        Table_id_parent INTEGER,
        Table_id_child INTEGER,
        Is_identifying BOOLEAN,
        CONSTRAINT Relationship_primary_key PRIMARY KEY (Schema_id,Relationship_id,Table_id_parent,Table_id_child)
);
/
CREATE TABLE Data_key
(
        Schema_id INTEGER,
        Table_id INTEGER,
        Key_id INTEGER,
        CONSTRAINT Data_key_primary_key PRIMARY KEY (Schema_id,Table_id,Key_id)
);
/
ALTER TABLE Data_key ADD CONSTRAINT R0_Data_key FOREIGN KEY (Schema_id,Key_id,Table_id) REFERENCES Table_key (Schema_id,Key_id,Table_id) ON DELETE CASCADE;
/
ALTER TABLE Database_table ADD CONSTRAINT R1_Database_table FOREIGN KEY (Schema_id) REFERENCES Database_schema (Schema_id) ON DELETE CASCADE;
/
ALTER TABLE Identifying_relationship ADD CONSTRAINT R2_Identifying_relationship FOREIGN KEY (Schema_id,Table_id_child,Table_id_parent,Relationship_id) REFERENCES Relationship (Schema_id,Table_id_child,Table_id_parent,Relationship_id) ON DELETE CASCADE;
/
ALTER TABLE Identifying_relationship_key_pair ADD CONSTRAINT R3_Identifying_relationship_key_pair FOREIGN KEY (Schema_id,Table_id_child,Table_id_parent,Relationship_id) REFERENCES Identifying_relationship (Schema_id,Table_id_child,Table_id_parent,Relationship_id) ON DELETE CASCADE;
/
ALTER TABLE Identifying_relationship_key_pair ADD CONSTRAINT R4_Identifying_relationship_key_pair FOREIGN KEY (Schema_id,Key_id_parent,Table_id_parent) REFERENCES Primary_key (Schema_id,Key_id,Table_id) ON DELETE CASCADE;
/
ALTER TABLE Identifying_relationship_key_pair ADD CONSTRAINT R5_Identifying_relationship_key_pair FOREIGN KEY (Schema_id,Key_id_child,Table_id_child) REFERENCES Primary_key (Schema_id,Key_id,Table_id) ON DELETE CASCADE;
/
ALTER TABLE Nonidentifying_relationship ADD CONSTRAINT R6_Nonidentifying_relationship FOREIGN KEY (Schema_id,Table_id_child,Table_id_parent,Relationship_id) REFERENCES Relationship (Schema_id,Table_id_child,Table_id_parent,Relationship_id) ON DELETE CASCADE;
/
ALTER TABLE Nonidentifying_relationship_key_pair ADD CONSTRAINT R7_Nonidentifying_relationship_key_pair FOREIGN KEY (Schema_id,Key_id_child,Table_id_child) REFERENCES Data_key (Schema_id,Key_id,Table_id) ON DELETE CASCADE;
/
ALTER TABLE Nonidentifying_relationship_key_pair ADD CONSTRAINT R8_Nonidentifying_relationship_key_pair FOREIGN KEY (Schema_id,Table_id_child,Table_id_parent,Relationship_id) REFERENCES Nonidentifying_relationship (Schema_id,Table_id_child,Table_id_parent,Relationship_id) ON DELETE CASCADE;
/
ALTER TABLE Nonidentifying_relationship_key_pair ADD CONSTRAINT R9_Nonidentifying_relationship_key_pair FOREIGN KEY (Schema_id,Key_id_parent,Table_id_parent) REFERENCES Primary_key (Schema_id,Key_id,Table_id) ON DELETE CASCADE;
/
ALTER TABLE Primary_key ADD CONSTRAINT R10_Primary_key FOREIGN KEY (Schema_id,Key_id,Table_id) REFERENCES Table_key (Schema_id,Key_id,Table_id) ON DELETE CASCADE;
/
ALTER TABLE Relationship ADD CONSTRAINT R11_Relationship FOREIGN KEY (Schema_id,Table_id_parent) REFERENCES Database_table (Schema_id,Table_id) ON DELETE CASCADE;
/
ALTER TABLE Relationship ADD CONSTRAINT R12_Relationship FOREIGN KEY (Schema_id,Table_id_child) REFERENCES Database_table (Schema_id,Table_id) ON DELETE CASCADE;
/
ALTER TABLE Table_key ADD CONSTRAINT R13_Table_key FOREIGN KEY (Schema_id,Table_id) REFERENCES Database_table (Schema_id,Table_id) ON DELETE CASCADE;
/
ALTER TABLE Table_key ADD CONSTRAINT R14_Table_key FOREIGN KEY (Datatype_id) REFERENCES Datatype (Datatype_id) ON DELETE SET NULL;
/
--
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
--
CREATE PROCEDURE select_schema_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
SET doc = '';
SELECT
'<database_schema>' +CHAR(10)
+ '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
+ '<schema_name>' + schema_name + '</schema_name>' + CHAR(10)
+ '</database_schema>'
INTO doc FROM database_schema WHERE schema_id = v_schema_id;
SET v_doc = doc + CHAR (10);
END;
/
--
CREATE PROCEDURE select_table_list_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc = '';
SET doc2 = '';
SELECT '<database_table_list>' INTO doc FROM (VALUES (0));
SET doc2 = doc2 + doc + CHAR(10) ;
SET doc = '';
FOR SELECT
schema_id,
table_id,
table_name,
CASEWHEN( is_dependent, 'true', 'false' ) as is_dependent
FROM database_table WHERE schema_id = v_schema_id DO
SET doc = doc
+ '<database_table>' + CHAR(10)
+ '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
+ '<table_id>' + table_id + '</table_id>' + CHAR(10)
+ '<table_name>' + table_name + '</table_name>' + CHAR (10)
+ '<is_dependent>' + is_dependent + '</is_dependent>' + CHAR (10)
+ '</database_table>' + CHAR(10);
SET doc2 = doc2 + doc;
SET doc = '';
END FOR;
SET doc = '</database_table_list>';
SET v_doc = doc2 + doc + CHAR(10);
END;
/
--
CREATE PROCEDURE select_table_key_list_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc = '';
SET doc2 = '';
SELECT '<table_key_list>' INTO doc FROM (VALUES (0));
SET doc2 = doc2 + doc + CHAR(10) ;
SET doc = '';
FOR SELECT
    schema_id,
    table_id,
    key_id,
    key_name,
    datatype_id,
    key_order,
    CASEWHEN( is_primary_key, 'true', 'false' ) as primary_key,
    CASEWHEN( is_foreign_key, 'true', 'false' ) as foreign_key
FROM table_key WHERE schema_id = v_schema_id DO
SET doc = doc
 + '<table_key>' + CHAR(10)
 + '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
 + '<table_id>' + table_id + '</table_id>' + CHAR(10)
 + '<key_id>' + key_id + '</key_id>' + CHAR(10)
 + '<key_name>' + key_name + '</key_name>' + CHAR(10)
 + '<datatype_id>' + datatype_id + '</datatype_id>' + CHAR(10)
 + '<key_order>' + key_order + '</key_order>' + CHAR(10)
 + '<is_primary_key>' + primary_key + '</is_primary_key>' + CHAR(10)
 + '<is_foreign_key>' + foreign_key + '</is_foreign_key>' + CHAR(10)
 + '</table_key>' + CHAR(10);
SET doc2 = doc2 + doc;
SET doc = '';
END FOR;
SET doc = '</table_key_list>';
SET v_doc = doc2 + doc + CHAR(10);
END;
/
--
CREATE PROCEDURE select_primary_key_list_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc = '';
SET doc2 = '';
SELECT '<primary_key_list>' INTO doc FROM (VALUES (0));
SET doc2 = doc2 + doc + CHAR(10) ;
SET doc = '';
FOR SELECT
    schema_id,
    table_id,
    key_id
FROM primary_key WHERE schema_id = v_schema_id DO
SET doc = doc
 + '<primary_key>' + CHAR(10)
 + '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
 + '<table_id>' + table_id + '</table_id>' + CHAR(10)
 + '<key_id>' + key_id + '</key_id>' + CHAR(10)
 + '</primary_key>' + CHAR(10);
SET doc2 = doc2 + doc;
SET doc = '';
END FOR;
SET doc = '</primary_key_list>';
SET v_doc = doc2 + doc + CHAR(10);
END;
/
--
CREATE PROCEDURE select_data_key_list_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc = '';
SET doc2 = '';
SELECT '<data_key_list>' INTO doc FROM (VALUES (0));
SET doc2 = doc2 + doc + CHAR(10) ;
SET doc = '';
FOR SELECT
    schema_id,
    table_id,
    key_id
FROM data_key WHERE schema_id = v_schema_id DO
SET doc = doc
 + '<data_key>' + CHAR(10)
 + '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
 + '<table_id>' + table_id + '</table_id>' + CHAR(10)
 + '<key_id>' + key_id + '</key_id>' + CHAR(10)
 + '</data_key>' + CHAR(10);
SET doc2 = doc2 + doc;
SET doc = '';
END FOR;
SET doc = '</data_key_list>';
SET v_doc = doc2 + doc + CHAR(10);
END;
/
--
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
--
CREATE PROCEDURE select_identifying_relationship_list_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc = '';
SET doc2 = '';
SELECT '<identifying_relationship_list>' INTO doc FROM (VALUES (0));
SET doc2 = doc2 + doc + CHAR(10) ;
SET doc = '';
FOR SELECT
schema_id,
relationship_id,
table_id_parent,
table_id_child
FROM identifying_relationship WHERE schema_id = v_schema_id DO
SET doc = doc
+ '<identifying_relationship>' + CHAR(10)
+ '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
+ '<relationship_id>' + relationship_id + '</relationship_id>' + CHAR(10)
+ '<table_id_parent>' + table_id_parent + '</table_id_parent>' + CHAR(10)
+ '<table_id_child>' + table_id_child + '</table_id_child>' + CHAR(10)
+ '</identifying_relationship>' + CHAR(10);
SET doc2 = doc2 + doc;
SET doc = '';
END FOR;
SET doc = '</identifying_relationship_list>';
SET v_doc = doc2 + doc + CHAR(10);
END;
/
--
CREATE PROCEDURE select_nonidentifying_relationship_list_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc = '';
SET doc2 = '';
SELECT '<nonidentifying_relationship_list>' INTO doc FROM (VALUES (0));
SET doc2 = doc2 + doc + CHAR(10);
SET doc = '';
FOR SELECT
schema_id,
relationship_id,
table_id_parent,
table_id_child
FROM nonidentifying_relationship WHERE schema_id = v_schema_id DO
SET doc = doc
+ '<nonidentifying_relationship>' + CHAR(10)
+ '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
+ '<relationship_id>' + relationship_id + '</relationship_id>' + CHAR(10)
+ '<table_id_parent>' + table_id_parent + '</table_id_parent>' + CHAR(10)
+ '<table_id_child>' + table_id_child + '</table_id_child>' + CHAR(10)
+ '</nonidentifying_relationship>' + CHAR(10);
SET doc2 = doc2 + doc;
SET doc = '';
END FOR;
SET doc = '</nonidentifying_relationship_list>';
SET v_doc = doc2 + doc + CHAR(10);
END;
/
--
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
--
CREATE PROCEDURE select_nonidentifying_relationship_key_pair_list_as_xml (
OUT v_doc LONGVARCHAR,
IN v_schema_id INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE doc LONGVARCHAR;
DECLARE doc2 LONGVARCHAR;
SET doc = '';
SET doc2 = '';
SELECT '<nonidentifying_relationship_key_pair_list>' INTO doc FROM (VALUES (0));
SET doc2 = doc2 + doc + CHAR(10) ;
SET doc = '';
FOR SELECT
schema_id,
relationship_id,
table_id_parent,
key_id_parent,
table_id_child,
key_id_child
FROM nonidentifying_relationship_key_pair WHERE schema_id = v_schema_id DO
SET doc = doc
+ '<nonidentifying_relationship_key_pair>' + CHAR(10)
+ '<schema_id>' + schema_id + '</schema_id>' + CHAR(10)
+ '<relationship_id>' + relationship_id + '</relationship_id>' + CHAR(10)
+ '<table_id_parent>' + table_id_parent + '</table_id_parent>' + CHAR(10)
+ '<key_id_parent>' + key_id_parent + '</key_id_parent>' + CHAR(10)
+ '<table_id_child>' + table_id_child + '</table_id_child>' + CHAR(10)
+ '<key_id_child>' + key_id_child + '</key_id_child>' + CHAR(10)
+ '</nonidentifying_relationship_key_pair>' + CHAR(10);
SET doc2 = doc2 + doc;
SET doc = '';
END FOR;
SET doc = '</nonidentifying_relationship_key_pair_list>';
SET v_doc = doc2 + doc + CHAR(10);
END;
/
--
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
--
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

--
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
--
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
--
CREATE FUNCTION is_it_a_dependent_table (
IN V_Schema_id INTEGER,
IN V_Table_id INTEGER
) RETURNS INTEGER
READS SQL DATA BEGIN ATOMIC
DECLARE v_count INTEGER;
SELECT COUNT(DISTINCT table_id_child) INTO v_count
FROM identifying_relationship
WHERE schema_id = V_Schema_id
AND   table_id_child = V_Table_id;
RETURN v_count;
END;
/
CREATE FUNCTION compare_datatype (
IN v_schema_id INTEGER,
IN v_parent_table_id INTEGER,
IN v_parent_key_id INTEGER,
IN v_child_table_id INTEGER,
IN v_child_key_id INTEGER
) RETURNS BOOLEAN
READS SQL DATA BEGIN ATOMIC
DECLARE v_check BOOLEAN;
SELECT CASE
         WHEN a.datatype_id = b.datatype_id THEN TRUE
         ELSE FALSE
       END
INTO v_check
FROM (SELECT *
      FROM table_key a
      WHERE a.schema_id = v_schema_id
      AND   a.table_id = v_parent_table_id
      AND   a.key_id = v_parent_key_id) a,
     (SELECT *
      FROM table_key a
      WHERE a.schema_id = v_schema_id
      AND   a.table_id = v_child_table_id
      AND   a.key_id = v_child_key_id) b
WHERE a.schema_id = b.schema_id;
RETURN v_check;
END;
/
CREATE PROCEDURE copy_schema (
OUT newid INTEGER,
IN v_schema_id_old INTEGER
)
MODIFIES SQL DATA
BEGIN ATOMIC
DECLARE v_schema_id_new INTEGER;
SET v_schema_id_new = id();
INSERT INTO database_schema
(
  schema_id,
  schema_name
)
SELECT v_schema_id_new,
       concat(schema_name,'_dup')
FROM database_schema
WHERE schema_id = v_schema_id_old;
INSERT INTO database_table
(
  schema_id,
  table_id,
  table_name
)
SELECT v_schema_id_new,
       table_id,
       table_name
FROM database_table
WHERE schema_id = v_schema_id_old;
INSERT INTO table_key
(
  schema_id,
  table_id,
  key_id,
  key_name,
  datatype_id,
  key_order,
  is_primary_key
)
SELECT v_schema_id_new,
       table_id,
       key_id,
       key_name,
       datatype_id,
       key_order,
       is_primary_key
FROM table_key
WHERE schema_id = v_schema_id_old;
INSERT INTO primary_key
(
  schema_id,
  table_id,
  key_id
)
SELECT v_schema_id_new,
       table_id,
       key_id
FROM primary_key
WHERE schema_id = v_schema_id_old;
INSERT INTO data_key
(
  schema_id,
  table_id,
  key_id
)
SELECT v_schema_id_new,
       table_id,
       key_id
FROM data_key
WHERE schema_id = v_schema_id_old;
INSERT INTO relationship
(
  schema_id,
  relationship_id,
  table_id_parent,
  table_id_child,
  is_identifying
)
SELECT v_schema_id_new,
       relationship_id,
       table_id_parent,
       table_id_child,
       is_identifying
FROM relationship
WHERE schema_id = v_schema_id_old;
INSERT INTO identifying_relationship
(
  schema_id,
  relationship_id,
  table_id_parent,
  table_id_child
)
SELECT v_schema_id_new,
       relationship_id,
       table_id_parent,
       table_id_child
FROM identifying_relationship
WHERE schema_id = v_schema_id_old;
INSERT INTO nonidentifying_relationship
(
  schema_id,
  relationship_id,
  table_id_parent,
  table_id_child
)
SELECT v_schema_id_new,
       relationship_id,
       table_id_parent,
       table_id_child
FROM nonidentifying_relationship
WHERE schema_id = v_schema_id_old;
INSERT INTO identifying_relationship_key_pair
(
  schema_id,
  relationship_id,
  table_id_parent,
  key_id_parent,
  table_id_child,
  key_id_child
)
SELECT v_schema_id_new,
       relationship_id,
       table_id_parent,
       key_id_parent,
       table_id_child,
       key_id_child
FROM identifying_relationship_key_pair
WHERE schema_id = v_schema_id_old;
INSERT INTO nonidentifying_relationship_key_pair
(
  schema_id,
  relationship_id,
  table_id_parent,
  key_id_parent,
  table_id_child,
  key_id_child
)
SELECT v_schema_id_new,
       relationship_id,
       table_id_parent,
       key_id_parent,
       table_id_child,
       key_id_child
FROM nonidentifying_relationship_key_pair
WHERE schema_id = v_schema_id_old;
SET newid = v_schema_id_new;
END;
/
CREATE PROCEDURE copy_table (
OUT newid INTEGER,
IN v_schema_id INTEGER,
IN v_table_id INTEGER
)
MODIFIES SQL DATA
BEGIN ATOMIC
DECLARE v_table_id_new INTEGER;
SET v_table_id_new = id();
INSERT INTO database_table
(
  schema_id,
  table_id,
  table_name
)
SELECT schema_id,
       v_table_id_new,
       concat(table_name,'_dup')
FROM database_table
WHERE schema_id = v_schema_id
AND table_id = v_table_id;
INSERT INTO table_key
(
  schema_id,
  table_id,
  key_id,
  key_name,
  datatype_id,
  key_order,
  is_primary_key
)
SELECT schema_id,
       v_table_id_new,
       key_id,
       key_name,
       datatype_id,
       key_order,
       is_primary_key
FROM table_key
WHERE schema_id = v_schema_id
AND table_id = v_table_id;
INSERT INTO primary_key
(
  schema_id,
  table_id,
  key_id
)
SELECT schema_id,
       v_table_id_new,
       key_id
FROM primary_key
WHERE schema_id = v_schema_id
AND table_id = v_table_id;
INSERT INTO data_key
(
  schema_id,
  table_id,
  key_id
)
SELECT schema_id,
       v_table_id_new,
       key_id
FROM data_key
WHERE schema_id = v_schema_id
AND table_id = v_table_id;
SET newid = v_table_id_new;
END;
/
CREATE FUNCTION find_empty_relationships (
--
IN v_schema_id INTEGER
--
)
RETURNS TABLE (
--
relationship_id INTEGER
--
)
--
READS SQL DATA
--
BEGIN ATOMIC
--
RETURN TABLE (
--
SELECT a.relationship_id
FROM identifying_relationship a
  LEFT JOIN identifying_relationship_key_pair b
         ON a.schema_id = b.schema_id
        AND a.relationship_id = b.relationship_id
        AND a.table_id_parent = b.table_id_parent
        AND a.table_id_child = b.table_id_child
        AND schema_id = v_schema_id
WHERE b.key_id_parent IS NULL
UNION
SELECT a.relationship_id
FROM nonidentifying_relationship a
  LEFT JOIN nonidentifying_relationship_key_pair b
         ON a.schema_id = b.schema_id
        AND a.relationship_id = b.relationship_id
        AND a.table_id_parent = b.table_id_parent
        AND a.table_id_child = b.table_id_child
        AND schema_id = v_schema_id
WHERE b.key_id_parent IS NULL
--
);
--
END;
/
--
--INTEGER
call datatype_insert(2,'INTEGER');
/
--DECIMAL
call datatype_insert(4,'DECIMAL');
/
--DOUBLE
call datatype_insert(6,'DOUBLE');
/
--VARCHAR
call datatype_insert(8,'VARCHAR');
/
--LONGVARCHAR
call datatype_insert(9,'LONGVARCHAR');
/
--DATE
call datatype_insert(10,'DATE');
/
--TIME
call datatype_insert(11,'TIME');
/
--TIMESTAMP
call datatype_insert(12,'TIMESTAMP');
/
--BOOLEAN
call datatype_insert(15,'BOOLEAN');
/

--
SET SCHEMA potatosql;
/
CREATE TRIGGER Relationship_rlai_trigger AFTER INSERT ON Relationship REFERENCING NEW ROW AS newrow FOR EACH ROW
IF (newrow.is_identifying) THEN
CALL SET_DEPENDENT_ENTITY_TRUE(newrow.schema_id,newrow.table_id_child);
END IF;
/
CREATE TRIGGER Relationship_rlad_trigger AFTER DELETE ON Relationship REFERENCING OLD ROW as oldrow FOR EACH ROW
BEGIN ATOMIC
DECLARE v_count INTEGER;
SELECT COUNT(*) INTO v_count FROM Relationship WHERE table_id_child = oldrow.table_id_child AND Is_identifying = TRUE;
IF (v_count = 0) THEN
CALL SET_DEPENDENT_ENTITY_FALSE(oldrow.schema_id,oldrow.table_id_child);
END IF;
END;
/
CREATE TRIGGER Identifying_relationship_key_pair_rlai_trigger AFTER INSERT ON Identifying_relationship_key_pair REFERENCING NEW ROW AS newrow FOR EACH ROW
call set_foreign_key_true(newrow.schema_id,newrow.table_id_child,newrow.key_id_child);
/
CREATE TRIGGER Nonidentifying_relationship_key_pair_rlai_trigger AFTER INSERT ON Nonidentifying_relationship_key_pair REFERENCING NEW ROW AS newrow FOR EACH ROW
call set_foreign_key_true(newrow.schema_id,newrow.table_id_child,newrow.key_id_child);
/
CREATE TRIGGER Identifying_relationship_key_pair_rlad_trigger AFTER DELETE ON Identifying_relationship_key_pair REFERENCING OLD ROW AS oldrow FOR EACH ROW
call set_foreign_key_false(oldrow.schema_id,oldrow.table_id_child,oldrow.key_id_child);
/
CREATE TRIGGER Nonidentifying_relationship_key_pair_rlad_trigger AFTER DELETE ON Nonidentifying_relationship_key_pair REFERENCING OLD ROW AS oldrow FOR EACH ROW
call set_foreign_key_false(oldrow.schema_id,oldrow.table_id_child,oldrow.key_id_child);
/

--
