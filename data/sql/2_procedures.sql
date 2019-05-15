CREATE PROCEDURE DatabaseSchema_Insert (
IN v_Name VARCHAR(300)
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO DatabaseSchema (
SchemaId,
Name
) VALUES (
DEFAULT,
v_Name
);
END;
/

CREATE PROCEDURE PUBLIC.DATABASESCHEMA_INSERT (OUT NEWID INTEGER,IN V_NAME VARCHAR(300))
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO PUBLIC.DATABASESCHEMA
(
  SCHEMAID,
  NAME
)
VALUES
(
  DEFAULT,
  V_NAME
);
SET NEWID = IDENTITY();
END;
/


CREATE PROCEDURE DatabaseSchema_Update (
IN v_SchemaId INTEGER,
IN v_Name VARCHAR(300)
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
DatabaseSchema
SET
Name = v_Name
WHERE
SchemaId = v_SchemaId;
END;
/

CREATE PROCEDURE DatabaseSchema_Delete (
IN v_SchemaId INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
DatabaseSchema
WHERE
SchemaId = v_SchemaId;
END;
/

CREATE PROCEDURE DatabaseSchema_Select_All ()
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
Name 
FROM
DatabaseSchema;
OPEN result;
END;
/

CREATE PROCEDURE DatabaseTable_Insert (
IN v_SchemaId INTEGER,
IN v_Name VARCHAR(300)
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO DatabaseTable (
SchemaId,
TableId,
Name
) VALUES (
v_SchemaId,
DEFAULT,
v_Name
);
END;
/

CREATE PROCEDURE DatabaseTable_Insert (
IN v_SchemaId INTEGER,
OUT newid INTEGER,
IN v_Name VARCHAR(8000)
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO DatabaseTable (
SchemaId,
TableId,
Name
) VALUES (
v_SchemaId,
DEFAULT,
v_Name
);
SET newid = IDENTITY();
END;
/

CREATE PROCEDURE DatabaseTable_Update (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER,
IN v_Name VARCHAR(300)
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
DatabaseTable
SET
Name = v_Name
WHERE
SchemaId = v_SchemaId
AND
TableId = v_TableId;
END;
/

CREATE PROCEDURE DatabaseTable_Delete (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
DatabaseTable
WHERE
SchemaId = v_SchemaId
AND
TableId = v_TableId;
END;
/

CREATE PROCEDURE DatabaseTable_Select (
IN v_SchemaId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
TableId,
Name
FROM
DatabaseTable
WHERE
SchemaId = v_SchemaId;
OPEN result;
END;
/

CREATE PROCEDURE DatabaseTable_Select (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
TableId,
Name
FROM
DatabaseTable
WHERE
SchemaId = v_SchemaId
AND
TableId = v_TableId;
OPEN result;
END;
/

CREATE PROCEDURE TableKey_Insert (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER,
IN v_Name VARCHAR(300),
IN v_Label VARCHAR(300),
IN v_IsPk BOOLEAN,
IN v_TypeId INTEGER,
IN v_Precision INTEGER,
IN v_Scale INTEGER,
IN v_Orden INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO TableKey (
SchemaId,
TableId,
KeyId,
Name,
Label,
IsPk,
TypeId,
Precision,
Scale,
Orden
) VALUES (
v_SchemaId,
v_TableId,
DEFAULT,
v_Name,
v_Label,
v_IsPk,
v_TypeId,
v_Precision,
v_Scale,
v_Orden
);
END;
/

CREATE PROCEDURE TableKey_Insert (
OUT newid INTEGER,
IN v_SchemaId INTEGER,
IN v_TableId INTEGER,
IN v_Name VARCHAR(300),
IN v_Label VARCHAR(300),
IN v_IsPk BOOLEAN,
IN v_TypeId INTEGER,
IN v_Precision INTEGER,
IN v_Scale INTEGER,
IN v_Orden INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO TableKey (
SchemaId,
TableId,
KeyId,
Name,
Label,
IsPk,
TypeId,
Precision,
Scale,
Orden
) VALUES (
v_SchemaId,
v_TableId,
DEFAULT,
v_Name,
v_Label,
v_IsPk,
v_TypeId,
v_Precision,
v_Scale,
v_Orden
);
SET NEWID = IDENTITY();
END;
/


CREATE PROCEDURE TableKey_Update (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER,
IN v_KeyId INTEGER,
IN v_Name VARCHAR(300),
IN v_Label VARCHAR(300),
IN v_IsPk BOOLEAN,
IN v_TypeId INTEGER,
IN v_Precision INTEGER,
IN v_Scale INTEGER,
IN v_Orden INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
TableKey
SET
Name = v_Name,
Label = v_Label,
IsPk = v_IsPk,
TypeId = v_TypeId,
Precision = v_Precision,
Scale = v_Scale,
Orden = v_Orden
WHERE
SchemaId = v_SchemaId
AND
TableId = v_TableId
AND
KeyId = v_KeyId;
END;
/

CREATE PROCEDURE TableKey_Delete (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER,
IN v_KeyId INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
TableKey
WHERE
SchemaId = v_SchemaId
AND
TableId = v_TableId
AND
KeyId = v_KeyId;
END;
/

CREATE PROCEDURE TableKey_Select_PK (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
TableId,
KeyId,
Name,
Label,
IsPk,
TypeId,
Precision,
Scale,
Orden
FROM
TableKey
WHERE
SchemaId = v_SchemaId
AND
TableId = v_TableId
AND
IsPk = true;
OPEN result;
END;
/
--
CREATE PROCEDURE TableKey_Select_NPK (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
TableId,
KeyId,
Name,
Label,
IsPk,
TypeId,
Precision,
Scale,
Orden
FROM
TableKey
WHERE
SchemaId = v_SchemaId
AND
TableId = v_TableId
AND
IsPk = false;
OPEN result;
END;
/

CREATE PROCEDURE KeyType_Insert (
IN v_TypeId INTEGER,
IN v_Name VARCHAR(300),
IN v_PrecisionRequired BOOLEAN,
IN v_ScaleRequired BOOLEAN
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO KeyType (
TypeId,
Name,
PrecisionRequired,
ScaleRequired
) VALUES (
v_TypeId,
v_Name,
v_PrecisionRequired,
v_ScaleRequired
);
END;
/

CREATE PROCEDURE KeyType_Select_All ()
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
TypeId,
Name,
PrecisionRequired,
ScaleRequired
FROM
KeyType;
OPEN result;
END;
/

CREATE PROCEDURE Relationship_Insert (
IN v_SchemaId INTEGER,
IN v_Parent_TableId INTEGER,
IN v_Child_TableId INTEGER,
IN v_RelationshipTypeId INTEGER,
IN v_Name VARCHAR(300),
IN v_ForwardVerbPhrase VARCHAR(300),
IN v_ReverseVerbPhrase VARCHAR(300)
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Relationship (
SchemaId,
Parent_TableId,
Child_TableId,
RelationshipId,
RelationshipTypeId,
Name,
ForwardVerbPhrase,
ReverseVerbPhrase
) VALUES (
v_SchemaId,
v_Parent_TableId,
v_Child_TableId,
DEFAULT,
v_RelationshipTypeId,
v_Name,
v_ForwardVerbPhrase,
v_ReverseVerbPhrase
);
END;
/

CREATE PROCEDURE Relationship_Insert (
OUT newid INTEGER,
IN v_SchemaId INTEGER,
IN v_Parent_TableId INTEGER,
IN v_Child_TableId INTEGER,
IN v_RelationshipTypeId INTEGER,
IN v_Name VARCHAR(300),
IN v_ForwardVerbPhrase VARCHAR(300),
IN v_ReverseVerbPhrase VARCHAR(300)
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Relationship (
SchemaId,
Parent_TableId,
Child_TableId,
RelationshipId,
RelationshipTypeId,
Name,
ForwardVerbPhrase,
ReverseVerbPhrase
) VALUES (
v_SchemaId,
v_Parent_TableId,
v_Child_TableId,
DEFAULT,
v_RelationshipTypeId,
v_Name,
v_ForwardVerbPhrase,
v_ReverseVerbPhrase
);
SET NEWID = IDENTITY();
END;
/

CREATE PROCEDURE Relationship_Update (
IN v_SchemaId INTEGER,
IN v_Parent_TableId INTEGER,
IN v_Child_TableId INTEGER,
IN v_RelationshipId INTEGER,
IN v_RelationshipTypeId INTEGER,
IN v_Name VARCHAR(300),
IN v_ForwardVerbPhrase VARCHAR(300),
IN v_ReverseVerbPhrase VARCHAR(300)
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
Relationship
SET
RelationshipTypeId = v_RelationshipTypeId,
Name = v_Name,
ForwardVerbPhrase = v_ForwardVerbPhrase,
ReverseVerbPhrase = v_ReverseVerbPhrase
WHERE
SchemaId = v_SchemaId
AND
Parent_TableId = v_Parent_TableId
AND
Child_TableId = v_Child_TableId
AND
RelationshipId = v_RelationshipId;
END;
/

CREATE PROCEDURE Relationship_Delete (
IN v_SchemaId INTEGER,
IN v_Parent_TableId INTEGER,
IN v_Child_TableId INTEGER,
IN v_RelationshipId INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
Relationship
WHERE
SchemaId = v_SchemaId
AND
Parent_TableId = v_Parent_TableId
AND
Child_TableId = v_Child_TableId
AND
RelationshipId = v_RelationshipId;
END;
/

CREATE PROCEDURE Relationship_Multiple_Select (
IN v_SchemaId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
Parent_TableId,
b.Name as Parent,
Child_TableId,
c.Name as Child,
RelationshipTypeId,
RelationshipId,
d.Name as RelationshipTypeName,
a.Name as RelationshipName,
a.ForwardVerbPhrase,
a.ReverseVerbPhrase
FROM
Relationship a, DatabaseTable b, DatabaseTable c, RelationshipType d
WHERE
SchemaId = v_SchemaId
AND   
A.SCHEMAID = B.SCHEMAID
AND   
A.SCHEMAID = C.SCHEMAID
AND
a.Parent_TableId = b.TableId
AND
a.Child_TableId = c.TableId
AND
a.RelationshipTypeId = d.RelationshipTypeId
ORDER BY 
Parent,RelationshipTypeId,Child;
OPEN result;
END;
/

CREATE PROCEDURE RelationshipType_Insert (
IN v_RelationshipTypeId INTEGER,
IN v_Name VARCHAR(300)
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO RelationshipType (
RelationshipTypeId,
Name
) VALUES (
v_RelationshipTypeId,
v_Name
);
END;
/

CREATE PROCEDURE RelationshipKeyPair_Insert (
IN v_SchemaId INTEGER,
IN v_Parent_TableId INTEGER,
IN v_Child_TableId INTEGER,
IN v_RelationshipId INTEGER,
IN v_Parent_KeyId INTEGER,
IN v_Child_KeyId INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO RelationshipKeyPair (
SchemaId,
Parent_TableId,
Child_TableId,
RelationshipId,
Parent_KeyId,
Child_KeyId
) VALUES (
v_SchemaId,
v_Parent_TableId,
v_Child_TableId,
v_RelationshipId,
v_Parent_KeyId,
v_Child_KeyId
);
END;
/



CREATE PROCEDURE RelationshipKeyPair_Delete (
IN v_SchemaId INTEGER,
IN v_Parent_TableId INTEGER,
IN v_Child_TableId INTEGER,
IN v_RelationshipId INTEGER,
IN v_Parent_KeyId INTEGER,
IN v_Child_KeyId INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
RelationshipKeyPair
WHERE
SchemaId = v_SchemaId
AND
Parent_TableId = v_Parent_TableId
AND
Child_TableId = v_Child_TableId
AND
RelationshipId = v_RelationshipId
AND
Parent_KeyId = v_Parent_KeyId
AND
Child_KeyId = v_Child_KeyId;
END;
/

CREATE PROCEDURE TableKey_KeyType_Select (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
TableId,
KeyId,
Name,
Label,
IsPk,
TypeId,
b.Name as TypeName,
CASE b.Name WHEN 'IDENTITY' THEN true ELSE false END as isIdentity,
PrecisionRequired,
Precision,
ScaleRequired,
Scale,
Orden
FROM
TableKey a, KeyType b
WHERE
a.TypeId = b.TypeId
AND
SchemaId = v_SchemaId
AND
TableId = v_TableId
ORDER BY
Orden,Name;
OPEN result;
END;
/


CREATE PROCEDURE RelationshipKeyPair_Multiple_Select (
IN v_SchemaId INTEGER,
IN v_Parent_TableId INTEGER,
IN v_Child_TableId INTEGER,
IN v_RelationshipId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
Parent_TableId,
Child_TableId,
RelationshipId,
Parent_KeyId,
Child_KeyId,
b.Name as Parent,
c.Name as Child
FROM
RelationshipKeyPair a, TableKey b, TableKey c
WHERE
SchemaId = v_SchemaId
AND
Parent_TableId = v_Parent_TableId
AND
Child_TableId = v_Child_TableId
AND
RelationshipId = v_RelationshipId
AND 
a.SchemaId = b.SchemaId
AND
a.Parent_TableId = b.TableId
AND
a.Parent_KeyId = b.KeyId
AND
a.SchemaId = c.SchemaId
AND
a.Child_TableId = c.TableId
AND
a.Child_KeyId = c.KeyId;
OPEN result;
END;
/

CREATE PROCEDURE Relationship_SelectOnlyNames (
IN v_SchemaId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT a.parent,
       a.child,
       a.relationshipid,
       a.relationshiptypeid,
       a.parent_key,
       a.child_key
FROM (SELECT a.schemaid,
             a.parent_tableid,
             a.child_tableid,
             a.relationshipid,
             a.relationshiptypeid,
             a.parent,
             a.child,
             a.parent_keyid,
             a.child_keyid,
             b.name AS parent_key,
             c.name AS child_key
      FROM (SELECT a.schemaid,
                   a.parent_tableid,
                   a.child_tableid,
                   a.relationshipid,
                   a.relationshiptypeid,
                   a.parent,
                   a.child,
                   b.parent_keyid,
                   b.child_keyid
            FROM (SELECT a.schemaid,
                         a.parent_tableid,
                         a.child_tableid,
                         a.relationshipid,
                         a.relationshiptypeid,
                         b.name AS parent,
                         c.name AS child
                  FROM relationship a,
                       databasetable b,
                       databasetable c
                  WHERE a.schemaid = b.schemaid
                  AND   a.parent_tableid = b.tableid
                  AND   a.schemaid = c.schemaid
                  AND   a.child_tableid = c.tableid
                  AND   a.schemaid = v_SchemaId) a,
                 relationshipkeypair b
            WHERE a.schemaid = b.schemaid
            AND   a.parent_tableid = b.parent_tableid
            AND   a.child_tableid = b.child_tableid
            AND   a.relationshipid = b.relationshipid) a,
           tablekey b,
           tablekey c
      WHERE a.schemaid = b.schemaid
      AND   a.parent_tableid = b.tableid
      AND   a.parent_keyid = b.keyid
      AND   a.schemaid = c.schemaid
      AND   a.child_tableid = c.tableid
      AND   a.child_keyid = c.keyid) a;
OPEN result;
END;
/

CREATE PROCEDURE PUBLIC.DATABASETABLE_COPY (IN V_SCHEMAID_OLD INTEGER,IN V_SCHEMAID_NEW INTEGER) 
MODIFIES SQL DATA BEGIN ATOMIC 
INSERT INTO PUBLIC.DATABASETABLE
(
  SCHEMAID,
  TABLEID,
  NAME
)
SELECT V_SCHEMAID_NEW,
       TABLEID,
       NAME
FROM PUBLIC.DATABASETABLE
WHERE SCHEMAID = V_SCHEMAID_OLD;
END;
/

CREATE PROCEDURE PUBLIC.TABLEKEY_COPY (IN v_SchemaId INTEGER,IN v_TableId_Old INTEGER,IN v_TableId_New INTEGER) MODIFIES SQL DATA BEGIN ATOMIC 
INSERT INTO PUBLIC.TABLEKEY
(
  SCHEMAID,
  TABLEID,
  KEYID,
  NAME,
  LABEL,
  ISPK,
  TYPEID,
  PRECISION,
  SCALE,
  ORDEN
)
SELECT SchemaId,
       v_TableId_New,
       KEYID,
       NAME,
       LABEL,
       ISPK,
       TYPEID,
       PRECISION,
       SCALE,
       ORDEN
FROM PUBLIC.TABLEKEY
WHERE SCHEMAID = v_SchemaId
and
tableid = v_TableId_Old;
END;
/

CREATE PROCEDURE PUBLIC.RELATIONSHIP_COPY (IN V_SCHEMAID_OLD INTEGER,IN V_SCHEMAID_NEW INTEGER) 
MODIFIES SQL DATA BEGIN ATOMIC 
INSERT INTO PUBLIC.RELATIONSHIP
(
  SCHEMAID,
  PARENT_TABLEID,
  CHILD_TABLEID,
  RELATIONSHIPID,
  RELATIONSHIPTYPEID,
  NAME,
  FORWARDVERBPHRASE,
  REVERSEVERBPHRASE
)
SELECT V_SCHEMAID_NEW,
       PARENT_TABLEID,
       CHILD_TABLEID,
       RELATIONSHIPID,
       RELATIONSHIPTYPEID,
       NAME,
       FORWARDVERBPHRASE,
       REVERSEVERBPHRASE
FROM PUBLIC.RELATIONSHIP
WHERE SCHEMAID = V_SCHEMAID_OLD;

END;
/

CREATE PROCEDURE PUBLIC.TABLEKEY_COPY (IN V_SCHEMAID_OLD INTEGER,IN V_SCHEMAID_NEW INTEGER) MODIFIES SQL DATA BEGIN ATOMIC 
INSERT INTO PUBLIC.TABLEKEY
(
  SCHEMAID,
  TABLEID,
  KEYID,
  NAME,
  LABEL,
  ISPK,
  TYPEID,
  PRECISION,
  SCALE,
  ORDEN
)
SELECT V_SCHEMAID_NEW,
       TABLEID,
       KEYID,
       NAME,
       LABEL,
       ISPK,
       TYPEID,
       PRECISION,
       SCALE,
       ORDEN
FROM PUBLIC.TABLEKEY
WHERE SCHEMAID = V_SCHEMAID_OLD;
END;
/

CREATE PROCEDURE PUBLIC.RELATIONSHIPKEYPAIR_COPY (IN V_SCHEMAID_OLD INTEGER,IN V_SCHEMAID_NEW INTEGER) 
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO PUBLIC.RELATIONSHIPKEYPAIR
(
  SCHEMAID,
  PARENT_TABLEID,
  CHILD_TABLEID,
  RELATIONSHIPID,
  PARENT_KEYID,
  CHILD_KEYID
)
SELECT V_SCHEMAID_NEW,
       PARENT_TABLEID,
       CHILD_TABLEID,
       RELATIONSHIPID,
       PARENT_KEYID,
       CHILD_KEYID
FROM PUBLIC.RELATIONSHIPKEYPAIR
WHERE SCHEMAID = V_SCHEMAID_OLD;
END;
/

CREATE PROCEDURE PUBLIC.POTATOSQL_TABLE_COPY (IN v_SchemaId INTEGER,IN v_TableId_Old INTEGER,IN v_TableName VARCHAR(300)) 
MODIFIES SQL DATA BEGIN ATOMIC 
DECLARE v_TableId_New INTEGER;
call DatabaseTable_Insert(v_SchemaId,v_TableId_New,v_TableName);
call TableKey_Copy(v_SchemaId,v_TableId_Old,v_TableId_New);
END;
/

CREATE PROCEDURE PUBLIC.DATABASESCHEMA_COPY (IN V_SCHEMAID_OLD INTEGER,IN V_SCHEMANAME_NEW VARCHAR(300)) 
MODIFIES SQL DATA BEGIN ATOMIC 
DECLARE V_SCHEMAID_NEW INTEGER;
CALL DATABASESCHEMA_INSERT (V_SCHEMAID_NEW,V_SCHEMANAME_NEW);
CALL DATABASETABLE_COPY (V_SCHEMAID_OLD,V_SCHEMAID_NEW);
CALL RELATIONSHIP_COPY (V_SCHEMAID_OLD,V_SCHEMAID_NEW);
CALL TABLEKEY_COPY (V_SCHEMAID_OLD,V_SCHEMAID_NEW);
CALL RELATIONSHIPKEYPAIR_COPY (V_SCHEMAID_OLD,V_SCHEMAID_NEW);
END;
/
