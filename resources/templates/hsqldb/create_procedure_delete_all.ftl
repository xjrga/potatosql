CREATE PROCEDURE ${table.name}_delete_all ()
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM ${table.name};
END;
/
