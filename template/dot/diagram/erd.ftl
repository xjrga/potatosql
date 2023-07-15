digraph erd {

    labelloc = "t";
    label = "${data.schema_name} Entity Relationship Diagram";
    fontsize = 20;
    fontname = "Liberation Mono Bold"
    size = "100,100";
    nodesep = 0.25;
    ranksep = 1.5;
    /*layout=neato*/

node [color = "#000000", style = solid,fontname = "Liberation Mono"];
<#list data.table_iterator as table>
${table.table_name} [shape=<#if table.is_dependent>Mrecord<#else>record</#if>, label=<
        <table border="0" cellborder="0" cellspacing="0" cellpadding="4">
            <tr><td bgcolor="#CECFC3"><b>${table.table_name}</b></td></tr>
             <#list table.key_iterator as key>
              <#if key.is_primary_key && key.is_foreign_key>
               <tr><td bgcolor="#ECEEE9" align="left">${key.key_name}: ${dtype.getHsqldb(key.datatype)} (FK)</td></tr>
              </#if>
              <#if key.is_primary_key && !key.is_foreign_key>
               <tr><td bgcolor="#ECEEE9" align="left">${key.key_name}: ${dtype.getHsqldb(key.datatype)} </td></tr>
              </#if>
              </#list>
              <tr><td sides="t" border="1px solid black" bgcolor="#ECEEE9" colspan="100%"></td></tr>              
             <#list table.key_iterator as key>
              <#if !key.is_primary_key && key.is_foreign_key>
               <tr><td bgcolor="#ECEEE9" align="left">${key.key_name}: ${dtype.getHsqldb(key.datatype)} (FK)</td></tr>
              </#if>
              <#if !key.is_primary_key && !key.is_foreign_key>
               <tr><td bgcolor="#ECEEE9" align="left">${key.key_name}: ${dtype.getHsqldb(key.datatype)}</td></tr>
              </#if>
              </#list>
        </table>
    >]
</#list>

<#list data.relationship_iterator as relationship>
edge [penwidth=1,fontname="Liberation Mono" len=5, arrowsize=0.5, arrowhead=dot, arrowtail=none, dir=forward, style=<#if relationship.is_identifying>solid<#else>dashed</#if>]
${relationship.parent} -> ${relationship.child} [dir=forward,weight=1];
</#list>

}
