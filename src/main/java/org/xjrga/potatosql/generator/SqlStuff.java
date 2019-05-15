package org.xjrga.potatosql.generator;

import java.util.Iterator;

public class SqlStuff
{
    private Table table;

    public SqlStuff(Table table)
    {
        this.table = table;
    }


    public String getSqlColumnDefinitionsForTable()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            sb.append(column.getName());
            sb.append(" ");
            sb.append(column.getTypeName());

            if (column.isPrecisionRequired())
            {
                sb.append("(");
                sb.append(column.getPrecision());
                sb.append(")");
            }
            sb.append(",");
            sb.append("\n");
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }


    public String getSqlProcedureVariablesPrimary()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (column.isPrimaryKey())
            {
                sb.append("IN");
                sb.append(" ");
                sb.append("v_" + column.getName());
                sb.append(" ");

                if (column.isIdentity())
                {
                    sb.append("INTEGER");
                } else
                {
                    sb.append(column.getTypeName());
                }

                if (column.isPrecisionRequired())
                {
                    sb.append("(");
                    sb.append(column.getPrecision());
                    sb.append(")");
                }
                sb.append(",");
                sb.append("\n");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }


    public String getSqlProcedureVariablesAll()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            sb.append("IN");
            sb.append(" ");
            sb.append("v_" + column.getName());
            sb.append(" ");

            if (column.isIdentity())
            {
                sb.append("INTEGER");
            } else
            {
                sb.append(column.getTypeName());
            }

            if (column.isPrecisionRequired())
            {
                sb.append("(");
                sb.append(column.getPrecision());
                sb.append(")");
            }
            sb.append(",");
            sb.append("\n");
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }


    public String getSqlProcedureVariablesAllMinusIdent()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            if (column.isIdentity())
            {
                sb.append("OUT newid INTEGER");
                sb.append(",");
                sb.append("\n");
            }

            if (!column.isIdentity())
            {
                sb.append("IN");
                sb.append(" ");
                sb.append("v_" + column.getName());
                sb.append(" ");
                sb.append(column.getTypeName());

                if (column.isPrecisionRequired())
                {
                    sb.append("(");
                    sb.append(column.getPrecision());
                    sb.append(")");
                }
                sb.append(",");
                sb.append("\n");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }


    public String getSqlParametersPrimary()
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
                sb.append(" ");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }


    public String getSqlParametersNonPrimary()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (!column.isPrimaryKey())
            {
                sb.append(column.getName());
                sb.append(",");
                sb.append("\n");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }


    public String getSqlParametersAll()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            sb.append(column.getName());
            sb.append(",");
            sb.append("\n");

        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }


    public String getSqlWhereStuff_Primary()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (column.isPrimaryKey())
            {
                sb.append(column.getName());
                sb.append(" = ");
                sb.append("v_" + column.getName());
                sb.append("\n");
                sb.append("AND");
                sb.append("\n");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 5);
        }

        return sb.toString();
    }


    public String getSqlWhereStuff_NonPrimary()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (!column.isPrimaryKey())
            {
                sb.append(column.getName());
                sb.append(" = ");
                sb.append("v_" + column.getName());
                sb.append("\n");
                sb.append("AND");
                sb.append("\n");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 5);
        }

        return sb.toString();
    }


    public String getSqlWhereStuff_All()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (column.isPrimaryKey())
            {
                sb.append(column.getName());
                sb.append(" = ");
                sb.append("v_" + column.getName());
                sb.append("\n");
                sb.append("AND");
                sb.append("\n");
            }
        }

        it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (!column.isPrimaryKey())
            {
                sb.append(column.getName());
                sb.append(" = ");
                sb.append("v_" + column.getName());
                sb.append("\n");
                sb.append("AND");
                sb.append("\n");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 5);
        }

        return sb.toString();
    }


    public String getSqlVariablesAll()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            sb.append("v_" + column.getName());
            sb.append(",");
            sb.append("\n");

        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public String getSqlVariablesAllSubIdent()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (!column.isIdentity())
            {
                sb.append("v_" + column.getName());
                sb.append(",");
                sb.append("\n");
            } else
            {
                sb.append("DEFAULT");
                sb.append(",");
                sb.append("\n");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public String getSqlProcedureMarksPrimary()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (column.isPrimaryKey())
            {
                sb.append("?");
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


    public String getSqlProcedureMarksAll()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            sb.append("?");
            sb.append(",");
            sb.append(" ");
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }


    public String getSqlProcedureMarksAllMinusIdent()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (!column.isIdentity())
            {
                sb.append("?");
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

    public String getSqlSetStuff_NonPrimary()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (!column.isPrimaryKey())
            {
                sb.append(column.getName());
                sb.append(" = ");
                sb.append("v_" + column.getName());
                sb.append(",");
                sb.append("\n");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

}
