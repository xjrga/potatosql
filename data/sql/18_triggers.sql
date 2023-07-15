SET SCHEMA potatosql;
/
CREATE TRIGGER Relationship_rlai_trigger AFTER INSERT ON Relationship REFERENCING NEW ROW AS newrow FOR EACH ROW
IF (newrow.is_identifying) THEN
CALL SET_DEPENDENT_ENTITY_TRUE(newrow.schema_id,newrow.table_id_child);
END IF;
/
CREATE TRIGGER Relationship_rlad_trigger AFTER DELETE ON Relationship REFERENCING OLD ROW as oldrow FOR EACH ROW
BEGIN ATOMIC
DECLARE v_count INTEGER;
SELECT COUNT(*) INTO v_count FROM Relationship WHERE table_id_child = oldrow.table_id_child;
IF (v_count = 0) THEN
CALL SET_DEPENDENT_ENTITY_FALSE(oldrow.schema_id,oldrow.table_id_child);
END IF;
END;
/
CREATE TRIGGER Identifying_relationship_key_pair_rlai_trigger AFTER INSERT ON Identifying_relationship_key_pair REFERENCING NEW ROW AS newrow FOR EACH ROW
call set_foreign_key_true(newrow.schema_id,newrow.table_id_child,newrow.key_id_child);
/
CREATE TRIGGER Nonidentifying_relationship_key_pair_rlai_trigger AFTER INSERT ON Nonidentifying_relationship_key_pair REFERENCING NEW ROW AS newrow FOR EACH ROW
call set_foreign_key_true(newrow.schema_id,newrow.table_id_child,newrow.key_id_child);
/
CREATE TRIGGER Identifying_relationship_key_pair_rlad_trigger AFTER DELETE ON Identifying_relationship_key_pair REFERENCING OLD ROW AS oldrow FOR EACH ROW
call set_foreign_key_false(oldrow.schema_id,oldrow.table_id_child,oldrow.key_id_child);
/
CREATE TRIGGER Nonidentifying_relationship_key_pair_rlad_trigger AFTER DELETE ON Nonidentifying_relationship_key_pair REFERENCING OLD ROW AS oldrow FOR EACH ROW
call set_foreign_key_false(oldrow.schema_id,oldrow.table_id_child,oldrow.key_id_child);
/

