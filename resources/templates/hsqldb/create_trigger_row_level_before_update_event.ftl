CREATE TRIGGER ${table.name}_row_level_before_update_event_trigger
BEFORE UPDATE ON ${table.name} REFERENCING NEW ROW AS newrow OLD as oldrow FOR EACH ROW
BEGIN ATOMIC
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Row level before update event trigger was triggered';
END;
/
