package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

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
    public String get_code() {
        if (table.containsNonPrimaryKeys()) {
            sqlbuild.append("CREATE TRIGGER");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_row_level_before_update_trigger");
            sqlbuild.append("\n");
            sqlbuild.append("BEFORE UPDATE ON");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append(" ");
            sqlbuild.append("REFERENCING NEW ROW AS row OLD as oldrow");
            sqlbuild.append("\n");
            sqlbuild.append("FOR EACH ROW");
            sqlbuild.append("\n");
            sqlbuild.append(triggerStuff.getTriggerSetNewRowNPKVariables());
            sqlbuild.append("\n");
            sqlbuild.append("/");
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
