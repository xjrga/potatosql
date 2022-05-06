CREATE TRIGGER ${table.name}_statement_level_after_update_event_trigger
AFTER UPDATE ON ${table.name} FOR EACH STATEMENT
BEGIN ATOMIC
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Statement level after update event trigger was triggered';
END;
/
