CREATE TABLE DatabaseSchema
(
SchemaId IDENTITY,
Name VARCHAR(300),
CONSTRAINT DatabaseSchema_primaryKey PRIMARY KEY (SchemaId)
);
/

CREATE TABLE DatabaseTable
(
SchemaId INTEGER,
TableId IDENTITY,
Name VARCHAR(300),
CONSTRAINT DatabaseTable_primaryKey PRIMARY KEY (SchemaId, TableId)
);
/

CREATE TABLE TableKey
(
SchemaId INTEGER,
TableId INTEGER,
KeyId IDENTITY,
Name VARCHAR(300),
Label VARCHAR(300),
IsPk BOOLEAN,
TypeId INTEGER,
Precision INTEGER,
Scale INTEGER,
Orden INTEGER,
CONSTRAINT TableKey_primaryKey PRIMARY KEY (SchemaId, TableId, KeyId)
);
/

CREATE TABLE KeyType
(
TypeId INTEGER,
Name VARCHAR(300),
PrecisionRequired BOOLEAN,
ScaleRequired BOOLEAN,
CONSTRAINT KeyType_primaryKey PRIMARY KEY (TypeId)
);
/

CREATE TABLE RelationshipType
(
RelationshipTypeId INTEGER,
Name VARCHAR(300),
CONSTRAINT RelationshipType_primaryKey PRIMARY KEY (RelationshipTypeId)
);
/

CREATE TABLE Relationship
(
SchemaId INTEGER,
Parent_TableId INTEGER,
Child_TableId INTEGER,
RelationshipId IDENTITY,
RelationshipTypeId INTEGER,
Name VARCHAR(300),
ForwardVerbPhrase VARCHAR(300),
ReverseVerbPhrase VARCHAR(300),
CONSTRAINT Relationship_primaryKey PRIMARY KEY (SchemaId, Parent_TableId, Child_TableId, RelationshipId)
);
/

CREATE TABLE RelationshipKeyPair
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

ALTER TABLE DatabaseTable ADD CONSTRAINT DatabaseSchema_DatabaseTable_Relationship FOREIGN KEY ( SchemaId ) REFERENCES DatabaseSchema ( SchemaId ) ON DELETE CASCADE;
/
ALTER TABLE TableKey ADD CONSTRAINT DatabaseTable_TableKey_Relationship FOREIGN KEY ( SchemaId,TableId ) REFERENCES DatabaseTable ( SchemaId,TableId ) ON DELETE CASCADE;
/
ALTER TABLE RelationshipKeyPair ADD CONSTRAINT TableKey_RelationshipKeyPair_Relationship_1 FOREIGN KEY ( SchemaId,Parent_TableId,Parent_KeyId ) REFERENCES TableKey ( SchemaId,TableId,KeyId ) ON DELETE CASCADE;
/
ALTER TABLE RelationshipKeyPair ADD CONSTRAINT TableKey_RelationshipKeyPair_Relationship_2 FOREIGN KEY ( SchemaId,Child_TableId,Child_KeyId ) REFERENCES TableKey ( SchemaId,TableId,KeyId ) ON DELETE CASCADE;
/
ALTER TABLE TableKey ADD CONSTRAINT KeyType_TableKey_Relationship FOREIGN KEY ( TypeId ) REFERENCES KeyType ( TypeId ) ON DELETE CASCADE;
/
ALTER TABLE Relationship ADD CONSTRAINT RelationshipType_Relationship_Relationship FOREIGN KEY ( RelationshipTypeId ) REFERENCES RelationshipType ( RelationshipTypeId ) ON DELETE CASCADE;
/
ALTER TABLE RelationshipKeyPair ADD CONSTRAINT Relationship_RelationshipKeyPair_Relationship FOREIGN KEY ( SchemaId,Parent_TableId,Child_TableId,RelationshipId ) REFERENCES Relationship ( SchemaId,Parent_TableId,Child_TableId,RelationshipId ) ON DELETE CASCADE;
/




