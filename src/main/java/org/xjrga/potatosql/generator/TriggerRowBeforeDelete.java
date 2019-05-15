package org.xjrga.potatosql.generator;

public class TriggerRowBeforeDelete implements Code
{

    private Table table;
    private TriggerStuff triggerStuff;
    private StringBuilder sqlbuild;


    public TriggerRowBeforeDelete(Table table, TriggerStuff triggerStuff)
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
            sqlbuild.append(table.getName());
            sqlbuild.append("_RowLevelBeforeDelete_Trigger");
            sqlbuild.append("\n");
            sqlbuild.append("BEFORE DELETE ON");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append(" ");
            sqlbuild.append("REFERENCING OLD ROW AS oldrow");
            sqlbuild.append("\n");
            sqlbuild.append("FOR EACH ROW");
            sqlbuild.append("\n");
            sqlbuild.append("BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = '");
            sqlbuild.append("Row Level Before Delete Event Triggered");
            sqlbuild.append("';");
            sqlbuild.append("\n");
            sqlbuild.append("END");
            sqlbuild.append("\n");
            sqlbuild.append("/");
        }
        return sqlbuild.toString();
    }

}
