package org.xjrga.potatosql.generator;

public class ProcedureSelect implements Code
{

    private Table table;
    private Code statementSelect;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;


    public ProcedureSelect(Table table, SqlStuff sqlStuff)
    {

        this.table = table;
        statementSelect = new StatementSelect(table, sqlStuff);
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
            sqlbuild.append("_Select (");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlProcedureVariablesPrimary());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append("MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("DECLARE result CURSOR");
            sqlbuild.append("\n");
            sqlbuild.append("FOR");
            sqlbuild.append("\n");
            sqlbuild.append(statementSelect.getCode());
            sqlbuild.append("OPEN result;");
            sqlbuild.append("\n");
            sqlbuild.append("END;");
            sqlbuild.append("\n");
            sqlbuild.append("/");
        }
        return sqlbuild.toString();
    }

}
