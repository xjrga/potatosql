CREATE TRIGGER ${table.name}_row_level_before_delete_event_trigger
BEFORE DELETE ON ${table.name} REFERENCING OLD ROW AS oldrow FOR EACH ROW
BEGIN ATOMIC
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Row level before delete event trigger was triggered';
END;
/
