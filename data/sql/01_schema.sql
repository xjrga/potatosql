DROP SCHEMA Public IF EXISTS CASCADE;

CREATE TABLE Database_schema
(
Schema_id INTEGER,
Schema_name LONGVARCHAR,
CONSTRAINT Database_schema_primaryKey PRIMARY KEY (Schema_id)
);

CREATE TABLE Database_table
(
Schema_id INTEGER,
Table_id INTEGER,
Table_name LONGVARCHAR,
CONSTRAINT Database_table_primaryKey PRIMARY KEY (Schema_id, Table_id)
);

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

CREATE TABLE Key_type
(
Key_type_id INTEGER,
Key_type_name LONGVARCHAR,
CONSTRAINT Key_type_primaryKey PRIMARY KEY (Key_type_id)
);

CREATE TABLE Relationship_type
(
Relationship_type_id INTEGER,
Relationship_type_name LONGVARCHAR,
CONSTRAINT Relationship_type_primaryKey PRIMARY KEY (Relationship_type_id)
);

CREATE TABLE Relationship
(
Schema_id INTEGER,
Parent_table_id INTEGER,
Child_table_id INTEGER,
Relationship_id INTEGER,
Relationship_type_id INTEGER,
CONSTRAINT Relationship_primaryKey PRIMARY KEY (Schema_id, Parent_table_id, Child_table_id, Relationship_id)
);

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

ALTER TABLE Database_table ADD CONSTRAINT R0_Database_schema_Database_table FOREIGN KEY ( Schema_id ) REFERENCES Database_schema ( Schema_id ) ON DELETE CASCADE;

ALTER TABLE Table_key ADD CONSTRAINT R1_Database_table_Table_key FOREIGN KEY ( Schema_id,Table_id ) REFERENCES Database_table ( Schema_id,Table_id ) ON DELETE CASCADE;

ALTER TABLE Relationship ADD CONSTRAINT R2_Database_table_Relationship FOREIGN KEY ( Schema_id,Parent_table_id ) REFERENCES Database_table ( Schema_id,Table_id ) ON DELETE CASCADE;

ALTER TABLE Relationship ADD CONSTRAINT R3_Database_table_Relationship FOREIGN KEY ( Schema_id,Child_table_id ) REFERENCES Database_table ( Schema_id,Table_id ) ON DELETE CASCADE;

ALTER TABLE Relationship_key_pair ADD CONSTRAINT R4_Table_key_Relationship_key_pair FOREIGN KEY ( Schema_id,Parent_table_id,Parent_key_id ) REFERENCES Table_key ( Schema_id,Table_id,Table_key_id ) ON DELETE CASCADE;

ALTER TABLE Relationship_key_pair ADD CONSTRAINT R5_Table_key_Relationship_key_pair FOREIGN KEY ( Schema_id,Child_table_id,Child_key_id ) REFERENCES Table_key ( Schema_id,Table_id,Table_key_id ) ON DELETE CASCADE;

ALTER TABLE Table_key ADD CONSTRAINT R6_Key_type_Table_key FOREIGN KEY ( Table_key_type_id ) REFERENCES Key_type ( Key_type_id ) ON DELETE SET NULL;

ALTER TABLE Relationship ADD CONSTRAINT R7_Relationship_type_Relationship FOREIGN KEY ( Relationship_type_id ) REFERENCES Relationship_type ( Relationship_type_id ) ON DELETE SET NULL;

ALTER TABLE Relationship_key_pair ADD CONSTRAINT R8_Relationship_Relationship_key_pair FOREIGN KEY ( Schema_id,Parent_table_id,Child_table_id,Relationship_id ) REFERENCES Relationship ( Schema_id,Parent_table_id,Child_table_id,Relationship_id ) ON DELETE CASCADE;

