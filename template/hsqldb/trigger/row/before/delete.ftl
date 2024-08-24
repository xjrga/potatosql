<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_rlbd_trigger
BEFORE DELETE ON ${table.table_name}
REFERENCING OLD as old FOR EACH ROW
--
BEGIN ATOMIC
--
DECLARE str LONGVARCHAR;
--
SET str = CAST(now() AS LONGVARCHAR);
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = str;
--
END;
/
</#list>
