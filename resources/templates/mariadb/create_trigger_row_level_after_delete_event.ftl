CREATE TRIGGER ${table.name}_row_level_after_delete_event_trigger
AFTER DELETE ON ${table.name} FOR EACH ROW
BEGIN
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Row level after delete event trigger was triggered';
END;
/
