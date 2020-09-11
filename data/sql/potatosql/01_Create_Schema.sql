DROP SCHEMA IF EXISTS Public CASCADE;
/
CREATE TABLE Public.TableKey
(
SchemaId INTEGER,
TableId INTEGER,
KeyId IDENTITY,
Name LONGVARCHAR,
Label LONGVARCHAR,
IsPK BOOLEAN,
TypeId INTEGER,
Prcsn INTEGER,
Scale INTEGER,
Orden INTEGER,
CONSTRAINT TableKey_primaryKey PRIMARY KEY (SchemaId, TableId, KeyId)
);
/

CREATE TABLE Public.DatabaseSchema
(
SchemaId IDENTITY,
Name LONGVARCHAR,
CONSTRAINT DatabaseSchema_primaryKey PRIMARY KEY (SchemaId)
);
/

CREATE TABLE Public.RelationshipType
(
RelationshipTypeId INTEGER,
Name LONGVARCHAR,
CONSTRAINT RelationshipType_primaryKey PRIMARY KEY (RelationshipTypeId)
);
/

CREATE TABLE Public.DatabaseTable
(
SchemaId INTEGER,
TableId IDENTITY,
Name LONGVARCHAR,
CONSTRAINT DatabaseTable_primaryKey PRIMARY KEY (SchemaId, TableId)
);
/

CREATE TABLE Public.Relationship
(
SchemaId INTEGER,
Parent_TableId INTEGER,
Child_TableId INTEGER,
RelationshipId IDENTITY,
RelationshipTypeId INTEGER,
Name LONGVARCHAR,
ForwardVerbPhrase LONGVARCHAR,
ReverseVerbPhrase LONGVARCHAR,
CONSTRAINT Relationship_primaryKey PRIMARY KEY (SchemaId, Parent_TableId, Child_TableId, RelationshipId)
);
/

CREATE TABLE Public.RelationshipKeyPair
(
SchemaId INTEGER,
Parent_TableId INTEGER,
Child_TableId INTEGER,
RelationshipId INTEGER,
Parent_KeyId INTEGER,
Child_KeyId INTEGER,
CONSTRAINT RelationshipKeyPair_primaryKey PRIMARY KEY (SchemaId, Parent_TableId, Child_TableId, RelationshipId, Parent_KeyId, Child_KeyId)
);
/

CREATE TABLE Public.KeyType
(
TypeId INTEGER,
Name LONGVARCHAR,
PrecisionRequired BOOLEAN,
ScaleRequired BOOLEAN,
CONSTRAINT KeyType_primaryKey PRIMARY KEY (TypeId)
);
/

ALTER TABLE Public.RelationshipKeyPair ADD CONSTRAINT R0_TableKey_RelationshipKeyPair FOREIGN KEY ( SchemaId,Parent_TableId,Parent_KeyId ) REFERENCES Public.TableKey ( SchemaId,TableId,KeyId ) ON DELETE CASCADE;
/
ALTER TABLE Public.RelationshipKeyPair ADD CONSTRAINT R1_TableKey_RelationshipKeyPair FOREIGN KEY ( SchemaId,Child_TableId,Child_KeyId ) REFERENCES Public.TableKey ( SchemaId,TableId,KeyId ) ON DELETE CASCADE;
/
ALTER TABLE Public.DatabaseTable ADD CONSTRAINT R2_DatabaseSchema_DatabaseTable FOREIGN KEY ( SchemaId ) REFERENCES Public.DatabaseSchema ( SchemaId ) ON DELETE CASCADE;
/
ALTER TABLE Public.Relationship ADD CONSTRAINT R3_RelationshipType_Relationship FOREIGN KEY ( RelationshipTypeId ) REFERENCES Public.RelationshipType ( RelationshipTypeId ) ON DELETE SET NULL;
/
ALTER TABLE Public.TableKey ADD CONSTRAINT R4_DatabaseTable_TableKey FOREIGN KEY ( SchemaId,TableId ) REFERENCES Public.DatabaseTable ( SchemaId,TableId ) ON DELETE CASCADE;
/
ALTER TABLE Public.Relationship ADD CONSTRAINT R5_DatabaseTable_Relationship FOREIGN KEY ( SchemaId,Parent_TableId ) REFERENCES Public.DatabaseTable ( SchemaId,TableId ) ON DELETE CASCADE;
/
ALTER TABLE Public.Relationship ADD CONSTRAINT R6_DatabaseTable_Relationship FOREIGN KEY ( SchemaId,Child_TableId ) REFERENCES Public.DatabaseTable ( SchemaId,TableId ) ON DELETE CASCADE;
/
ALTER TABLE Public.RelationshipKeyPair ADD CONSTRAINT R7_Relationship_RelationshipKeyPair FOREIGN KEY ( SchemaId,Parent_TableId,Child_TableId,RelationshipId ) REFERENCES Public.Relationship ( SchemaId,Parent_TableId,Child_TableId,RelationshipId ) ON DELETE CASCADE;
/
ALTER TABLE Public.TableKey ADD CONSTRAINT R8_KeyType_TableKey FOREIGN KEY ( TypeId ) REFERENCES Public.KeyType ( TypeId ) ON DELETE SET NULL;
/