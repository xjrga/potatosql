CREATE TRIGGER ${table.name}_row_level_before_insert_event_trigger
BEFORE INSERT ON ${table.name} REFERENCING NEW ROW AS newrow FOR EACH ROW
BEGIN ATOMIC
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Row level before insert event trigger was triggered';
END;
/
