CREATE PROCEDURE Public.TableKey_Select (IN v_SchemaId INTEGER,IN v_TableId INTEGER)
--
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
--
DECLARE result CURSOR
FOR
SELECT SchemaId,
       TableId,
       KeyId,
       Name,
       Label,
       IsPK,
       TypeId,
       Prcsn,
       Scale,
       Orden
FROM Public.TableKey
WHERE SchemaId = v_SchemaId
AND   TableId = v_TableId;
--
OPEN result;
--
END;
/
