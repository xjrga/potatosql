package org.xjrga.potatosql.generator;

public class TriggerRowBeforeInsert implements Code
{

    private Table table;
    private TriggerStuff triggerStuff;
    private StringBuilder sqlbuild;


    public TriggerRowBeforeInsert(Table table, TriggerStuff triggerStuff)
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
            sqlbuild.append("_RowLevelBeforeInsert_Trigger");
            sqlbuild.append("\n");
            sqlbuild.append("BEFORE INSERT ON");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append(" ");
            sqlbuild.append("REFERENCING NEW ROW AS newrow");
            sqlbuild.append("\n");
            sqlbuild.append("FOR EACH ROW");
            sqlbuild.append("\n");
            sqlbuild.append("BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Row Level Before Insert Event Triggered';");
            sqlbuild.append("\n");
            sqlbuild.append("END;");
            sqlbuild.append("\n");
            sqlbuild.append("/");
        }
        return sqlbuild.toString();
    }

}
