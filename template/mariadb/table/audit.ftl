<#list data.table_iterator as table>
CREATE TABLE ${table.table_name}_audit
(
        timing ${dtype.getMariadb('VARCHAR')},
        statement ${dtype.getMariadb('VARCHAR')},
        identifier ${dtype.getMariadb('VARCHAR')},
        <#list table.key_iterator as key>
        ${key.key_name} ${dtype.getMariadb(key.datatype)},
        </#list>
        tstamp ${dtype.getMariadb('TIMESTAMP')}
);
/
</#list>
