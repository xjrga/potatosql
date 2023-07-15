<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_rlai_trigger
AFTER INSERT ON ${table.table_name} REFERENCING NEW as new FOR EACH ROW
BEGIN ATOMIC
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
'insert',
'new',
<#list table.key_iterator as key>
new.${key.key_name},
</#list>
now()
);
END;
/
</#list>
