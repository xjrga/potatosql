CREATE TRIGGER ${table.name}_row_level_after_insert_event_trigger
AFTER INSERT ON ${table.name} REFERENCING NEW ROW AS newrow FOR EACH ROW
BEGIN ATOMIC
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Row level after insert event trigger was triggered';
END;
/
