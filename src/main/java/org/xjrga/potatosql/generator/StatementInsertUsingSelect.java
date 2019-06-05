package org.xjrga.potatosql.generator;

public class StatementInsertUsingSelect implements Code
{
    private Table table;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;
    private StatementSelectAll statementSelectAll;

    public StatementInsertUsingSelect(Table table, SqlStuff sqlStuff)
    {
        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
        statementSelectAll = new StatementSelectAll(table,sqlStuff);
    }

    @Override
    public String getCode()
    {
        if (!table.isEmpty())
        {
            sqlbuild.append("INSERT INTO");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("\n");
            sqlbuild.append("(");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlParametersAll());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append(statementSelectAll.getCode());
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();

    }
}
