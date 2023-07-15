<#list data.table_iterator as table>
public interface ${table.table_name?cap_first}_dao extends Generic_dao<${table.table_name?cap_first}>{}

</#list>

