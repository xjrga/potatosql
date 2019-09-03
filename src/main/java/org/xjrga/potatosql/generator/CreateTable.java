package org.xjrga.potatosql.generator;

public class CreateTable implements Code
{

    private Table table;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;


    public CreateTable(Table table, SqlStuff sqlStuff)
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
            sqlbuild.append("CREATE TABLE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("\n");
            sqlbuild.append("(");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlColumnDefinitionsForTable());
            if (table.containsPrimaryKeys())
            {
                sqlbuild.append(",");
                sqlbuild.append("\n");
                sqlbuild.append("CONSTRAINT");
                sqlbuild.append(" ");
                sqlbuild.append(table.getName());
                sqlbuild.append("_primaryKey");
                sqlbuild.append(" ");
                sqlbuild.append("PRIMARY KEY");
                sqlbuild.append(" ");
                sqlbuild.append("(");
                sqlbuild.append(sqlStuff.getSqlParametersPrimary());
                sqlbuild.append(")");
            }
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append(";");
            sqlbuild.append("\n");
            sqlbuild.append("/");
        } else
        {
            sqlbuild.append("--Please add fields to ");
            sqlbuild.append(table.getName());
            sqlbuild.append(" table");
        }

        return sqlbuild.toString();
    }

}
