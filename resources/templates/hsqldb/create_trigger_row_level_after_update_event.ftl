CREATE TRIGGER ${table.name}_row_level_after_update_event_trigger
AFTER UPDATE ON ${table.name} REFERENCING NEW ROW AS newrow OLD as oldrow FOR EACH ROW
BEGIN ATOMIC
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Row level after update event trigger was triggered';
END;
/
