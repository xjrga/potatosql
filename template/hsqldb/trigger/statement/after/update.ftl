<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_slau_trigger
AFTER UPDATE ON ${table.table_name}
FOR EACH STATEMENT
--
BEGIN ATOMIC
--
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Statement level after update event trigger was triggered';
--
END;
/
</#list>
