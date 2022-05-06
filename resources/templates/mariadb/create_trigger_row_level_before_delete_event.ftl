CREATE TRIGGER ${table.name}_row_level_before_delete_event_trigger
BEFORE DELETE ON ${table.name} FOR EACH ROW
BEGIN
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Row level before delete event trigger was triggered';
END;
/
