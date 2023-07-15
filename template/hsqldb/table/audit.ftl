<#list data.table_iterator as table>
CREATE TABLE ${table.table_name}_audit
(
        timing ${dtype.getHsqldb('VARCHAR')},
        statement ${dtype.getHsqldb('VARCHAR')},
        identifier ${dtype.getHsqldb('VARCHAR')},
        <#list table.key_iterator as key>
        ${key.key_name} ${dtype.getHsqldb(key.datatype)},
        </#list>
        tstamp ${dtype.getHsqldb('TIMESTAMP')}
);
/
</#list>
