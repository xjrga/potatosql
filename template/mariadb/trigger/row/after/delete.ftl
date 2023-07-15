<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_rlad_trigger
AFTER DELETE ON ${table.table_name} FOR EACH ROW
BEGIN
INSERT INTO ${table.table_name}_audit (
timing,
statement,
identifier,
<#list table.key_iterator as key>
${key.key_name},
</#list>
tstamp
) VALUES (
'after',
'delete',
'old',
<#list table.key_iterator as key>
old.${key.key_name},
</#list>
now()
);
END;
/
</#list>
