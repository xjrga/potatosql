package org.xjrga.potatosql.generator;

public class StatementSelectCount implements Code
{

    private Table table;
    private StringBuilder sqlbuild;


    public StatementSelectCount(Table table)
    {

        this.table = table;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode()
    {

        if (!table.isEmpty())
        {
            sqlbuild.append("SELECT COUNT(*)");
            sqlbuild.append("\n");
            sqlbuild.append("FROM");
            sqlbuild.append("\n");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append(";");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }

}
