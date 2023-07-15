<#list data.table_iterator as table>
CREATE TABLE ${table.table_name}_audit
(
        timing ${dtype.getPostgresql('VARCHAR')},
        statement ${dtype.getPostgresql('VARCHAR')},
        identifier ${dtype.getPostgresql('VARCHAR')},
        <#list table.key_iterator as key>
        ${key.key_name} ${dtype.getPostgresql(key.datatype)},
        </#list>
        tstamp ${dtype.getPostgresql('TIMESTAMP')}
);
/
</#list>
