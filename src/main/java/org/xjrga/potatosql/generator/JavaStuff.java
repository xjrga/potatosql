package org.xjrga.potatosql.generator;

import java.util.Iterator;

public class JavaStuff
{
    private Table table;

    public JavaStuff(Table table)
    {
        this.table = table;
    }

    public String getMethodParametersAllMinusIdent()
    {
        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (!column.isIdentity())
            {
                sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
                sb.append(" ");
                sb.append(column.getName());
                sb.append(",");
                sb.append(" ");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public String getMethodParametersAll()
    {
        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
            sb.append(" ");
            sb.append(column.getName());
            sb.append(",");
            sb.append(" ");
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public String getMethodParametersPrimaryKeyOnly()
    {
        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (column.isPrimaryKey())
            {
                sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
                sb.append(" ");
                sb.append(column.getName());
                sb.append(",");
                sb.append(" ");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public String getRowGetAllKeys()
    {

        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
            sb.append(" ");
            sb.append(column.getName());
            sb.append(" = ");
            sb.append("(");
            sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
            sb.append(")");
            sb.append("row.get(");
            sb.append("\"");
            sb.append(column.getName().toUpperCase());
            sb.append("\"");
            sb.append(");\n");
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    public String getRowGetNonPrimaryKeys()
    {

        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            if (!column.isPrimaryKey())
            {
                sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
                sb.append(" ");
                sb.append(column.getName());
                sb.append(" = ");
                sb.append("(");
                sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
                sb.append(")");
                sb.append("row.get(");
                sb.append("\"");
                sb.append(column.getName().toUpperCase());
                sb.append("\"");
                sb.append(");\n");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }

    public String getSout()
    {

        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        sb.append("System.out.println(");

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            sb.append(column.getName());
            sb.append("+");
            sb.append("\",\"");
            sb.append("+");
        }

        sb.setLength(sb.length() - 5);
        sb.append(");");

        return sb.toString();
    }

    public String getMethodVariablesPrimaryKeyOnly()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (column.isPrimaryKey())
            {
                sb.append(column.getName());
                sb.append(",");
            }
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

}
