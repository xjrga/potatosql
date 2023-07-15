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
