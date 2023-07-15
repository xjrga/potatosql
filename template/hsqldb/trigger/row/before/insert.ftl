<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_rlbi_trigger
BEFORE INSERT ON ${table.table_name} REFERENCING NEW as new FOR EACH ROW
BEGIN ATOMIC
DECLARE str LONGVARCHAR;
SET str = now() || ' -> new[' || <#list table.key_iterator as key>new.${key.key_name}<#if key?has_next> || ', ' || </#if></#list> || ']';
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = str;
END;
/
</#list>
