<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_slad_trigger
AFTER DELETE ON ${table.table_name} FOR EACH STATEMENT
BEGIN ATOMIC
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Statement level after delete event trigger was triggered';
END;
/
</#list>
