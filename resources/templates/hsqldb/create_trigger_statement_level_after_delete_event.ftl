CREATE TRIGGER ${table.name}_statement_level_after_delete_event_trigger
AFTER DELETE ON ${table.name} FOR EACH STATEMENT
BEGIN ATOMIC
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Statement level after delete event trigger was triggered';
END;
/
