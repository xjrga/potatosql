CREATE TRIGGER ${table.name}_row_level_after_delete_event_trigger
AFTER DELETE ON ${table.name} REFERENCING OLD as oldrow FOR EACH ROW
BEGIN ATOMIC
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Row level after delete event trigger was triggered';
END;
/
