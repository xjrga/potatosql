CREATE FUNCTION ${table.name}_count() RETURNS INT
BEGIN
DECLARE v_count INT;
SELECT COUNT(*) INTO v_count FROM ${table.name};
RETURN v_count;
END;
/
