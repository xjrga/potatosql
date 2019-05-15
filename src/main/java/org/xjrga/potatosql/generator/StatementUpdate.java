package org.xjrga.potatosql.generator;

public class StatementUpdate implements Code
{

    private Table table;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;


    public StatementUpdate(Table table, SqlStuff sqlStuff)
    {

        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode()
    {

        if (table.containsNonPrimaryKeys())
        {
            sqlbuild.append("UPDATE");
            sqlbuild.append("\n");
            sqlbuild.append(table.getName());
            sqlbuild.append("\n");
            sqlbuild.append("SET");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlSetStuff_NonPrimary());
            sqlbuild.append("\n");
            sqlbuild.append("WHERE");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlWhereStuff_Primary());
            sqlbuild.append(";");
        }
        return sqlbuild.toString();
    }

}
