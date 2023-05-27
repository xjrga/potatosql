/* courtesy Jorge Garcia de Alba */

digraph erd {

    labelloc = "t";
    label = "${schema_name} Entity Relationship Diagram";
    fontsize = 20;
    fontname = "Liberation Mono Bold"
    size = "100,100";
    nodesep = 0.25;
    ranksep = 1.5;
    /*layout=neato*/

node [color = "#000000", style = solid,fontname = "Liberation Mono"];
<#list tables as table>
${table.name} [shape=<#if table.is_dependent()>Mrecord<#else>record</#if>, label=<
        <table border="0" cellborder="0" cellspacing="0" cellpadding="4">
            <tr><td bgcolor="#CECFC3"><b>${table.name}</b></td></tr>
             <#list table.getIterator() as column>
              <#if column.isPrimaryKey()>
               <tr><td bgcolor="#ECEEE9" align="left">${column.name}: ${java_data_type.get_hsqldb_data_type(column.typename)} (PK)</td></tr>
              <#else>
               <tr><td align="left">${column.name}: ${java_data_type.get_hsqldb_data_type(column.typename)}</td></tr>
              </#if>
             </#list>
        </table>
    >]
</#list>

<#list relationships as relationship>
edge [penwidth=1,fontname="Liberation Mono" len=5, arrowsize=0.5, arrowhead=dot, arrowtail=none, dir=forward, style=<#if relationship.relationship_type_id == 0>solid<#else>dashed</#if>]
${relationship.parent} -> ${relationship.child} [dir=forward,weight=1];
</#list>

}
