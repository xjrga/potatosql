CREATE TRIGGER ${table.name}_statement_level_after_insert_event_trigger
AFTER INSERT ON ${table.name} FOR EACH STATEMENT
BEGIN ATOMIC
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Statement level after insert event trigger was triggered';
END;
/
