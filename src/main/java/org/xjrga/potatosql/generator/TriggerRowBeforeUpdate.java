package org.xjrga.potatosql.generator;

public class TriggerRowBeforeUpdate implements Code {

    private final Table table;
    private final TriggerStuff triggerStuff;
    private final StringBuilder sqlbuild;


    public TriggerRowBeforeUpdate(Table table, TriggerStuff triggerStuff) {
        this.table = table;
        this.triggerStuff = triggerStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode() {
        if (table.containsNonPrimaryKeys()) {
            sqlbuild.append("CREATE TRIGGER");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("_RowLevelBeforeUpdate_Trigger");
            sqlbuild.append("\n");
            sqlbuild.append("BEFORE UPDATE ON");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append(" ");
            sqlbuild.append("REFERENCING NEW ROW AS row OLD as oldrow");
            sqlbuild.append("\n");
            sqlbuild.append("FOR EACH ROW");
            sqlbuild.append("\n");
            sqlbuild.append(triggerStuff.getTriggerSetNewRowNPKVariables());
            sqlbuild.append("\n");

        }
        return sqlbuild.toString();
    }

}
