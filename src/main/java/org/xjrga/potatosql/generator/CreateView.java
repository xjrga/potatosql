package org.xjrga.potatosql.generator;

public class CreateView implements Code
{

    private Table table;
    private Code statementSelectAll;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;


    public CreateView(Table table, SqlStuff sqlStuff)
    {

        this.table = table;
        statementSelectAll = new StatementSelectAll(table, sqlStuff);
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode()
    {

        if (!table.isEmpty())
        {
            sqlbuild.append("CREATE View");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("_View");
            sqlbuild.append(" ");
            sqlbuild.append("\n");
            sqlbuild.append("AS");
            sqlbuild.append("\n");
            sqlbuild.append(statementSelectAll.getCode());
            sqlbuild.append("/");
        }
        return sqlbuild.toString();
    }

}
