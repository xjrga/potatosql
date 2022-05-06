CREATE TRIGGER ${table.name}_row_level_before_update_event_trigger
BEFORE UPDATE ON ${table.name} FOR EACH ROW
BEGIN
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Row level before update event trigger was triggered';
END;
/
