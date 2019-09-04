package org.xjrga.potatosql.generator;

public class TriggerStatementAfterInsert implements Code
{

    private Table table;
    private TriggerStuff triggerStuff;
    private StringBuilder sqlbuild;


    public TriggerStatementAfterInsert(Table table, TriggerStuff triggerStuff)
    {
        this.table = table;
        this.triggerStuff = triggerStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode()
    {
        if (!table.isEmpty())
        {
            sqlbuild.append("CREATE TRIGGER");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("_StatementLevelAfterInsert_Trigger");
            sqlbuild.append("\n");
            sqlbuild.append("AFTER INSERT ON");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("\n");
            sqlbuild.append("FOR EACH STATEMENT");
            sqlbuild.append("\n");
            sqlbuild.append("BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("DELETE FROM ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName() + "2;");
            sqlbuild.append("\n");
            sqlbuild.append("INSERT INTO ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName() + "2 ");
            sqlbuild.append("(SELECT * FROM ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append(");");
            sqlbuild.append("\n");
            sqlbuild.append("END;");
            sqlbuild.append("\n");
            sqlbuild.append("/");
        }
        return sqlbuild.toString();
    }

}
