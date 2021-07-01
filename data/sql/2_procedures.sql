CREATE PROCEDURE DatabaseSchema_Insert (
IN v_Name LONGVARCHAR
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
CREATE PROCEDURE DatabaseSchema_Update (
IN v_SchemaId INTEGER,
IN v_Name LONGVARCHAR
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
CREATE PROCEDURE DatabaseSchema_Merge (
IN v_SchemaId INTEGER,
IN v_Name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
MERGE INTO DatabaseSchema USING ( VALUES (
v_SchemaId,
v_Name
) ) ON (
SchemaId = v_SchemaId
)
WHEN MATCHED THEN UPDATE SET
Name = v_Name
WHEN NOT MATCHED THEN INSERT VALUES
v_SchemaId,
v_Name;
END;
/
CREATE PROCEDURE DatabaseSchema_Select (
IN v_SchemaId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
Name
FROM
DatabaseSchema
WHERE
SchemaId = v_SchemaId;
OPEN result;
END;
/
CREATE PROCEDURE DatabaseSchema_Delete_All ()
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM DatabaseSchema;
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
IN v_Name LONGVARCHAR
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
IN v_Name LONGVARCHAR
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
IN v_Name LONGVARCHAR
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
CREATE PROCEDURE DatabaseTable_Merge (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER,
IN v_Name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
MERGE INTO DatabaseTable USING ( VALUES (
v_SchemaId,
v_TableId,
v_Name
) ) ON (
SchemaId = v_SchemaId
AND
TableId = v_TableId
)
WHEN MATCHED THEN UPDATE SET
Name = v_Name
WHEN NOT MATCHED THEN INSERT VALUES
v_SchemaId,
v_TableId,
v_Name;
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
CREATE PROCEDURE DatabaseTable_Delete_All ()
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM DatabaseTable;
END;
/
CREATE PROCEDURE DatabaseTable_Select_All ()
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
TableId,
Name 
FROM
DatabaseTable;
OPEN result;
END;
/
CREATE PROCEDURE TableKey_Insert (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER,
IN v_Name LONGVARCHAR,
IN v_Label LONGVARCHAR,
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
CREATE PROCEDURE TableKey_Update (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER,
IN v_KeyId INTEGER,
IN v_Name LONGVARCHAR,
IN v_Label LONGVARCHAR,
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
CREATE PROCEDURE TableKey_Merge (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER,
IN v_KeyId INTEGER,
IN v_Name LONGVARCHAR,
IN v_Label LONGVARCHAR,
IN v_IsPk BOOLEAN,
IN v_TypeId INTEGER,
IN v_Precision INTEGER,
IN v_Scale INTEGER,
IN v_Orden INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
MERGE INTO TableKey USING ( VALUES (
v_SchemaId,
v_TableId,
v_KeyId,
v_Name,
v_Label,
v_IsPk,
v_TypeId,
v_Precision,
v_Scale,
v_Orden
) ) ON (
SchemaId = v_SchemaId
AND
TableId = v_TableId
AND
KeyId = v_KeyId
)
WHEN MATCHED THEN UPDATE SET
Name = v_Name,
Label = v_Label,
IsPk = v_IsPk,
TypeId = v_TypeId,
Precision = v_Precision,
Scale = v_Scale,
Orden = v_Orden
WHEN NOT MATCHED THEN INSERT VALUES
v_SchemaId,
v_TableId,
v_KeyId,
v_Name,
v_Label,
v_IsPk,
v_TypeId,
v_Precision,
v_Scale,
v_Orden;
END;
/
CREATE PROCEDURE TableKey_Select (
IN v_SchemaId INTEGER,
IN v_TableId INTEGER,
IN v_KeyId INTEGER
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
KeyId = v_KeyId;
OPEN result;
END;
/
CREATE PROCEDURE TableKey_Delete_All ()
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM TableKey;
END;
/
CREATE PROCEDURE TableKey_Select_All ()
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
TableKey;
OPEN result;
END;
/
CREATE PROCEDURE KeyType_Insert (
IN v_TypeId INTEGER,
IN v_Name LONGVARCHAR,
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
CREATE PROCEDURE KeyType_Update (
IN v_TypeId INTEGER,
IN v_Name LONGVARCHAR,
IN v_PrecisionRequired BOOLEAN,
IN v_ScaleRequired BOOLEAN
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
KeyType
SET
Name = v_Name,
PrecisionRequired = v_PrecisionRequired,
ScaleRequired = v_ScaleRequired
WHERE
TypeId = v_TypeId;
END;
/
CREATE PROCEDURE KeyType_Delete (
IN v_TypeId INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
KeyType
WHERE
TypeId = v_TypeId;
END;
/
CREATE PROCEDURE KeyType_Merge (
IN v_TypeId INTEGER,
IN v_Name LONGVARCHAR,
IN v_PrecisionRequired BOOLEAN,
IN v_ScaleRequired BOOLEAN
)
MODIFIES SQL DATA BEGIN ATOMIC
MERGE INTO KeyType USING ( VALUES (
v_TypeId,
v_Name,
v_PrecisionRequired,
v_ScaleRequired
) ) ON (
TypeId = v_TypeId
)
WHEN MATCHED THEN UPDATE SET
Name = v_Name,
PrecisionRequired = v_PrecisionRequired,
ScaleRequired = v_ScaleRequired
WHEN NOT MATCHED THEN INSERT VALUES
v_TypeId,
v_Name,
v_PrecisionRequired,
v_ScaleRequired;
END;
/
CREATE PROCEDURE KeyType_Select (
IN v_TypeId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
TypeId,
Name,
PrecisionRequired,
ScaleRequired
FROM
KeyType
WHERE
TypeId = v_TypeId;
OPEN result;
END;
/
CREATE PROCEDURE KeyType_Delete_All ()
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM KeyType;
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
CREATE PROCEDURE RelationshipType_Insert (
IN v_RelationshipTypeId INTEGER,
IN v_Name LONGVARCHAR
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
CREATE PROCEDURE RelationshipType_Update (
IN v_RelationshipTypeId INTEGER,
IN v_Name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
RelationshipType
SET
Name = v_Name
WHERE
RelationshipTypeId = v_RelationshipTypeId;
END;
/
CREATE PROCEDURE RelationshipType_Delete (
IN v_RelationshipTypeId INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM
RelationshipType
WHERE
RelationshipTypeId = v_RelationshipTypeId;
END;
/
CREATE PROCEDURE RelationshipType_Merge (
IN v_RelationshipTypeId INTEGER,
IN v_Name LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
MERGE INTO RelationshipType USING ( VALUES (
v_RelationshipTypeId,
v_Name
) ) ON (
RelationshipTypeId = v_RelationshipTypeId
)
WHEN MATCHED THEN UPDATE SET
Name = v_Name
WHEN NOT MATCHED THEN INSERT VALUES
v_RelationshipTypeId,
v_Name;
END;
/
CREATE PROCEDURE RelationshipType_Select (
IN v_RelationshipTypeId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
RelationshipTypeId,
Name
FROM
RelationshipType
WHERE
RelationshipTypeId = v_RelationshipTypeId;
OPEN result;
END;
/
CREATE PROCEDURE RelationshipType_Delete_All ()
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM RelationshipType;
END;
/
CREATE PROCEDURE RelationshipType_Select_All ()
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
RelationshipTypeId,
Name 
FROM
RelationshipType;
OPEN result;
END;
/
CREATE PROCEDURE Relationship_Insert (
IN v_SchemaId INTEGER,
IN v_Parent_TableId INTEGER,
IN v_Child_TableId INTEGER,
IN v_RelationshipTypeId INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO Relationship (
SchemaId,
Parent_TableId,
Child_TableId,
RelationshipId,
RelationshipTypeId
) VALUES (
v_SchemaId,
v_Parent_TableId,
v_Child_TableId,
DEFAULT,
v_RelationshipTypeId
);
END;
/
CREATE PROCEDURE Relationship_Update (
IN v_SchemaId INTEGER,
IN v_Parent_TableId INTEGER,
IN v_Child_TableId INTEGER,
IN v_RelationshipId INTEGER,
IN v_RelationshipTypeId INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
Relationship
SET
RelationshipTypeId = v_RelationshipTypeId
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
CREATE PROCEDURE Relationship_Merge (
IN v_SchemaId INTEGER,
IN v_Parent_TableId INTEGER,
IN v_Child_TableId INTEGER,
IN v_RelationshipId INTEGER,
IN v_RelationshipTypeId INTEGER
)
MODIFIES SQL DATA BEGIN ATOMIC
MERGE INTO Relationship USING ( VALUES (
v_SchemaId,
v_Parent_TableId,
v_Child_TableId,
v_RelationshipId,
v_RelationshipTypeId
) ) ON (
SchemaId = v_SchemaId
AND
Parent_TableId = v_Parent_TableId
AND
Child_TableId = v_Child_TableId
AND
RelationshipId = v_RelationshipId
)
WHEN MATCHED THEN UPDATE SET
RelationshipTypeId = v_RelationshipTypeId
WHEN NOT MATCHED THEN INSERT VALUES
v_SchemaId,
v_Parent_TableId,
v_Child_TableId,
v_RelationshipId,
v_RelationshipTypeId;
END;
/
CREATE PROCEDURE Relationship_Select (
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
RelationshipTypeId
FROM
Relationship
WHERE
SchemaId = v_SchemaId
AND
Parent_TableId = v_Parent_TableId
AND
Child_TableId = v_Child_TableId
AND
RelationshipId = v_RelationshipId;
OPEN result;
END;
/
CREATE PROCEDURE Relationship_Delete_All ()
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM Relationship;
END;
/
CREATE PROCEDURE Relationship_Select_All ()
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
Parent_TableId,
Child_TableId,
RelationshipId,
RelationshipTypeId
FROM
Relationship;
OPEN result;
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
CREATE PROCEDURE RelationshipKeyPair_Select (
IN v_SchemaId INTEGER,
IN v_Parent_TableId INTEGER,
IN v_Child_TableId INTEGER,
IN v_RelationshipId INTEGER,
IN v_Parent_KeyId INTEGER,
IN v_Child_KeyId INTEGER
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
Child_KeyId
FROM
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
OPEN result;
END;
/
CREATE PROCEDURE RelationshipKeyPair_Delete_All ()
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM RelationshipKeyPair;
END;
/
CREATE PROCEDURE RelationshipKeyPair_Select_All ()
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
SchemaId,
Parent_TableId,
Child_TableId,
RelationshipId,
Parent_KeyId,
Child_KeyId 
FROM
RelationshipKeyPair;
OPEN result;
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
CREATE PROCEDURE Relationship_Multiple_Select (IN V_SCHEMAID INTEGER) 
MODIFIES SQL DATA 
DYNAMIC RESULT SETS 1
BEGIN ATOMIC 
DECLARE RESULT CURSOR
FOR
SELECT SCHEMAID,
       PARENT_TABLEID,
       B.NAME AS PARENT,
       CHILD_TABLEID,
       C.NAME AS CHILD,
       RELATIONSHIPTYPEID,
       RELATIONSHIPID,
       D.NAME AS RELATIONSHIPTYPENAME
FROM RELATIONSHIP A,
     DATABASETABLE B,
     DATABASETABLE C,
     RELATIONSHIPTYPE D
WHERE SCHEMAID = V_SCHEMAID
AND   A.SCHEMAID = B.SCHEMAID
AND   A.SCHEMAID = C.SCHEMAID
AND   A.PARENT_TABLEID = B.TABLEID
AND   A.CHILD_TABLEID = C.TABLEID
AND   A.RELATIONSHIPTYPEID = D.RELATIONSHIPTYPEID
ORDER BY PARENT,
RELATIONSHIPTYPEID,
CHILD;
OPEN RESULT;
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
CREATE PROCEDURE RELATIONSHIP_SELECTONLYNAMES(IN V_SCHEMAID INTEGER) SPECIFIC RELATIONSHIP_SELECTONLYNAMES_10343 LANGUAGE SQL NOT DETERMINISTIC MODIFIES SQL DATA NEW SAVEPOINT LEVEL  DYNAMIC RESULT SETS 1 BEGIN ATOMIC DECLARE RESULT CURSOR FOR SELECT A.PARENT,A.CHILD,A.RELATIONSHIPID,A.RELATIONSHIPTYPEID,A.PARENT_KEY,A.CHILD_KEY FROM(SELECT A.SCHEMAID,A.PARENT_TABLEID,A.CHILD_TABLEID,A.RELATIONSHIPID,A.RELATIONSHIPTYPEID,A.PARENT,A.CHILD,A.PARENT_KEYID,A.CHILD_KEYID,B.NAME AS PARENT_KEY,C.NAME AS CHILD_KEY FROM(SELECT A.SCHEMAID,A.PARENT_TABLEID,A.CHILD_TABLEID,A.RELATIONSHIPID,A.RELATIONSHIPTYPEID,A.PARENT,A.CHILD,B.PARENT_KEYID,B.CHILD_KEYID FROM(SELECT A.SCHEMAID,A.PARENT_TABLEID,A.CHILD_TABLEID,A.RELATIONSHIPID,A.RELATIONSHIPTYPEID,B.NAME AS PARENT,C.NAME AS CHILD FROM RELATIONSHIP A,DATABASETABLE B,DATABASETABLE C WHERE A.SCHEMAID=B.SCHEMAID AND A.PARENT_TABLEID=B.TABLEID AND A.SCHEMAID=C.SCHEMAID AND A.CHILD_TABLEID=C.TABLEID AND A.SCHEMAID=V_SCHEMAID)A,RELATIONSHIPKEYPAIR B WHERE A.SCHEMAID=B.SCHEMAID AND A.PARENT_TABLEID=B.PARENT_TABLEID AND A.CHILD_TABLEID=B.CHILD_TABLEID AND A.RELATIONSHIPID=B.RELATIONSHIPID)A,TABLEKEY B,TABLEKEY C WHERE A.SCHEMAID=B.SCHEMAID AND A.PARENT_TABLEID=B.TABLEID AND A.PARENT_KEYID=B.KEYID AND A.SCHEMAID=C.SCHEMAID AND A.CHILD_TABLEID=C.TABLEID AND A.CHILD_KEYID=C.KEYID)A;OPEN RESULT;END
/
CREATE PROCEDURE DATABASESCHEMA_INSERT (OUT NEWID INTEGER,IN V_NAME LONGVARCHAR)
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO DATABASESCHEMA
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
CREATE PROCEDURE DATABASETABLE_COPY (IN V_SCHEMAID_OLD INTEGER,IN V_SCHEMAID_NEW INTEGER) 
MODIFIES SQL DATA BEGIN ATOMIC 
INSERT INTO DATABASETABLE
(
  SCHEMAID,
  TABLEID,
  NAME
)
SELECT V_SCHEMAID_NEW,
       TABLEID,
       NAME
FROM DATABASETABLE
WHERE SCHEMAID = V_SCHEMAID_OLD;
END;
/
CREATE PROCEDURE TABLEKEY_COPY (IN v_SchemaId INTEGER,IN v_TableId_Old INTEGER,IN v_TableId_New INTEGER) MODIFIES SQL DATA BEGIN ATOMIC 
INSERT INTO TABLEKEY
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
FROM TABLEKEY
WHERE SCHEMAID = v_SchemaId
and
tableid = v_TableId_Old;
END;
/
CREATE PROCEDURE RELATIONSHIP_COPY (IN V_SCHEMAID_OLD INTEGER,IN V_SCHEMAID_NEW INTEGER) 
MODIFIES SQL DATA BEGIN ATOMIC 
INSERT INTO RELATIONSHIP
(
  SCHEMAID,
  PARENT_TABLEID,
  CHILD_TABLEID,
  RELATIONSHIPID,
  RELATIONSHIPTYPEID)
SELECT V_SCHEMAID_NEW,
       PARENT_TABLEID,
       CHILD_TABLEID,
       RELATIONSHIPID,
       RELATIONSHIPTYPEID
FROM RELATIONSHIP
WHERE SCHEMAID = V_SCHEMAID_OLD;

END;
/
CREATE PROCEDURE TABLEKEY_COPY (IN V_SCHEMAID_OLD INTEGER,IN V_SCHEMAID_NEW INTEGER) MODIFIES SQL DATA BEGIN ATOMIC 
INSERT INTO TABLEKEY
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
FROM TABLEKEY
WHERE SCHEMAID = V_SCHEMAID_OLD;
END;
/
CREATE PROCEDURE RELATIONSHIPKEYPAIR_COPY (IN V_SCHEMAID_OLD INTEGER,IN V_SCHEMAID_NEW INTEGER) 
MODIFIES SQL DATA BEGIN ATOMIC
INSERT INTO RELATIONSHIPKEYPAIR
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
FROM RELATIONSHIPKEYPAIR
WHERE SCHEMAID = V_SCHEMAID_OLD;
END;
/
CREATE PROCEDURE TABLE_COPY (IN v_SchemaId INTEGER,IN v_TableId_Old INTEGER,IN v_TableName LONGVARCHAR) 
MODIFIES SQL DATA BEGIN ATOMIC 
DECLARE v_TableId_New INTEGER;
call DatabaseTable_Insert(v_SchemaId,v_TableId_New,v_TableName);
call TableKey_Copy(v_SchemaId,v_TableId_Old,v_TableId_New);
END;
/
CREATE PROCEDURE DATABASESCHEMA_COPY (IN V_SCHEMAID_OLD INTEGER,IN V_SCHEMANAME_NEW LONGVARCHAR) 
MODIFIES SQL DATA BEGIN ATOMIC 
DECLARE V_SCHEMAID_NEW INTEGER;
CALL DATABASESCHEMA_INSERT (V_SCHEMAID_NEW,V_SCHEMANAME_NEW);
CALL DATABASETABLE_COPY (V_SCHEMAID_OLD,V_SCHEMAID_NEW);
CALL RELATIONSHIP_COPY (V_SCHEMAID_OLD,V_SCHEMAID_NEW);
CALL TABLEKEY_COPY (V_SCHEMAID_OLD,V_SCHEMAID_NEW);
CALL RELATIONSHIPKEYPAIR_COPY (V_SCHEMAID_OLD,V_SCHEMAID_NEW);
END;
/
