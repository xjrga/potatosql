CREATE PROCEDURE TableKey_Renumber (IN v_SchemaId INTEGER,IN v_TableId INTEGER,IN v_KeyId INTEGER)
--
MODIFIES SQL DATA BEGIN ATOMIC
--
DECLARE v_Order INTEGER;
SET v_Order = SELECT Orden FROM TableKey WHERE SchemaId = v_SchemaId AND TableId = v_TableId AND KeyId = v_KeyId;
--
FOR
SELECT KeyId
FROM TableKey a
WHERE SchemaId = v_SchemaId
AND   TableId = v_TableId
AND   KeyId != v_KeyId
AND   Orden >= v_Order
DO
--
SET v_Order = v_Order + 1;
CALL TableKey_Update_Order(v_SchemaId,v_TableId,KeyId,v_Order);
--
END FOR;
--
END;
/
