package org.xjrga.potatosql.generator;

public class StatementDeleteAll implements Code
{

    private Table table;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;


    public StatementDeleteAll(Table table, SqlStuff sqlStuff)
    {

        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode()
    {

        sqlbuild.append("DELETE FROM");
        sqlbuild.append(" ");
        sqlbuild.append(table.getName());
        sqlbuild.append(";");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
