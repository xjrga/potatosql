package org.xjrga.potatosql.generator;

public class ProcedureDelete implements Code
{

    private Table table;
    private Code statementDelete;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;


    public ProcedureDelete(Table table, SqlStuff sqlStuff)
    {

        this.table = table;
        this.statementDelete = new StatementDelete(table, sqlStuff);
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode()
    {

        if (!table.isEmpty())
        {
            sqlbuild.append("CREATE PROCEDURE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("_Delete (");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlProcedureVariablesPrimary());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append("MODIFIES SQL DATA BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append(statementDelete.getCode());
            sqlbuild.append("END;");
            sqlbuild.append("\n");
            sqlbuild.append("/");
        }
        return sqlbuild.toString();
    }

}
