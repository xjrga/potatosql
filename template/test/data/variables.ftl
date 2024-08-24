data.schema_name: ${data.schema_name}

data.table_iterator

<#list data.table_iterator as table>
Table variables
------------------------
table.table_name: ${table.table_name}
table.table_name: ${table.table_name?c_lower_case}
table.table_name?cap_first: ${table.table_name?cap_first}
table.is_dependent: ${table.is_dependent?string('true','false')}
table.contains_primary_keys(): ${table.contains_primary_keys()?string('true','false')}
table.contains_data_keys(): ${table.contains_data_keys()?string('true','false')}
table.contains_foreign_keys(): ${table.contains_foreign_keys()?string('true','false')}

table.key_iterator

<#list table.key_iterator as key>
Key variables
--------------------
key.key_name ${key.key_name}
key.key_name?cap_first: ${key.key_name?cap_first}
key.is_primary_key ${key.is_primary_key?string('true','false')}
key.datatype ${key.datatype}
key.key_order ${key.key_order}
key.datatype ${key.datatype}
key?index: ${key?index}
key?counter: ${key?counter}
dtype.getHsqldb(key.datatype): ${dtype.getHsqldb(key.datatype)}
dtype.getJava(key.datatype): ${dtype.getJava(key.datatype)}
dtype.getMariadb(key.datatype): ${dtype.getMariadb(key.datatype)}
dtype.getPostgresql(key.datatype): ${dtype.getPostgresql(key.datatype)}
dtype.getOracle(key.datatype): ${dtype.getOracle(key.datatype)}
key.is_foreign_key: ${key.is_foreign_key?string('true','false')}

</#list>
Primary keys
-------------------
<#list table.key_iterator?filter(k -> k.is_primary_key) as key>
key.key_name: ${key.key_name}<#if key?has_next>,</#if>
</#list>

Data keys
---------------
<#list table.key_iterator?filter(k -> !k.is_primary_key) as key>
key.key_name: ${key.key_name}<#if key?has_next>,</#if>
</#list>

Foreign keys
--------------------
<#list table.key_iterator?filter(k -> k.is_foreign_key) as key>
key.key_name: ${key.key_name}<#if key?has_next>,</#if>
</#list>

</#list>
data.relationship_iterator

<#list data.relationship_iterator as relationship>
Relationship variables
----------------------------------
relationship.parent: ${relationship.parent}
relationship.child: ${relationship.child}
relationship.is_identifying: ${relationship.is_identifying?string('true','false')}

</#list>

https://freemarker.apache.org/docs/ref.html