public class O_${table.name}{
<#list table.getIterator() as column>
private ${util.capitalize(column.typename)} ${column.name};
</#list>
public O_${table.name}(){}
<#list table.getIterator() as column>
public void set${util.capitalize(column.name)}(${util.capitalize(column.typename)} ${column.name}){this.${column.name}=${column.name};}
public ${util.capitalize(column.typename)} get${util.capitalize(column.name)}(){return ${column.name};}
</#list>
@Override
public String toString(){return "O_${table.name}{<#list table.getIterator() as column>${column.name}="+${column.name}<#if column?has_next>+", </#if></#list>+"}";}}

