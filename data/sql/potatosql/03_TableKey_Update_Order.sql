CREATE PROCEDURE TableKey_Update_Order (IN v_SchemaId INTEGER,IN v_TableId INTEGER,IN v_KeyId INTEGER, IN v_Order INTEGER)
--
MODIFIES SQL DATA BEGIN ATOMIC
--
UPDATE TableKey
SET Orden = v_Order
WHERE SchemaId = v_SchemaId
AND   TableId = v_TableId
AND   KeyId = v_KeyId;
--
END;
/

