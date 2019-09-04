package org.xjrga.potatosql.generator;

public class StatementSelect implements Code
{

    private Table table;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;


    public StatementSelect(Table table, SqlStuff sqlStuff)
    {

        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode()
    {

        if (!table.isEmpty())
        {
            sqlbuild.append("SELECT");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlParametersAll());
            sqlbuild.append("\n");
            sqlbuild.append("FROM");
            sqlbuild.append("\n");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("\n");
            sqlbuild.append("WHERE");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlWhereStuff_Primary());
            sqlbuild.append(";");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }

}
