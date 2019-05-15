package org.xjrga.potatosql.generator;

public class FunctionCount implements Code
{

    private Table table;
    private Code statementSelectCountInto;
    private StringBuilder sqlbuild;


    public FunctionCount(Table table)
    {

        this.table = table;
        this.statementSelectCountInto = new StatementSelectCountInto(table);
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode()
    {

        if (!table.isEmpty())
        {
            sqlbuild.append("CREATE FUNCTION");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_Count () RETURNS INTEGER");
            sqlbuild.append("\n");
            sqlbuild.append("READS SQL DATA BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("DECLARE v_count INTEGER;");
            sqlbuild.append("\n");
            sqlbuild.append(statementSelectCountInto.getCode());
            sqlbuild.append("RETURN v_count;");
            sqlbuild.append("\n");
            sqlbuild.append("END;");
            sqlbuild.append("\n");
            sqlbuild.append("/");
        }
        return sqlbuild.toString();
    }

}
