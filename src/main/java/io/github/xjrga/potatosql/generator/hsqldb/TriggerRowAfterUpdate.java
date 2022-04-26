package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class TriggerRowAfterUpdate implements Code {

    private final Table table;
    private final TriggerStuff triggerStuff;
    private final StringBuilder sqlbuild;

    public TriggerRowAfterUpdate(Table table, TriggerStuff triggerStuff) {
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
            sqlbuild.append("_row_level_after_update_trigger");
            sqlbuild.append("\n");
            sqlbuild.append("AFTER UPDATE ON");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append(" ");
            sqlbuild.append("REFERENCING NEW ROW AS row OLD as oldrow");
            sqlbuild.append("\n");
            sqlbuild.append("FOR EACH ROW");
            sqlbuild.append("\n");
            sqlbuild.append("BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("UPDATE");
            sqlbuild.append("\n");
            sqlbuild.append(table.getName()).append("2");
            sqlbuild.append("\n");
            sqlbuild.append("SET");
            sqlbuild.append("\n");
            sqlbuild.append(triggerStuff.getSqlSetStuff_NonPrimary());
            sqlbuild.append("\n");
            sqlbuild.append("WHERE");
            sqlbuild.append("\n");
            sqlbuild.append(triggerStuff.getSqlWhereStuff_Primary());
            sqlbuild.append(";");
            sqlbuild.append("\n");
            sqlbuild.append("END;");
            sqlbuild.append("\n");
            sqlbuild.append("/");
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
