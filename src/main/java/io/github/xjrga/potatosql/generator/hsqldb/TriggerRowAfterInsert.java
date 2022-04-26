package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class TriggerRowAfterInsert implements Code {

    private final Table table;
    private final TriggerStuff triggerStuff;
    private final StringBuilder sqlbuild;

    public TriggerRowAfterInsert(Table table, TriggerStuff triggerStuff) {
        this.table = table;
        this.triggerStuff = triggerStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE TRIGGER");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_row_level_after_insert_trigger");
            sqlbuild.append("\n");
            sqlbuild.append("AFTER INSERT ON");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append(" ");
            sqlbuild.append("REFERENCING NEW ROW AS newrow");
            sqlbuild.append("\n");
            sqlbuild.append("FOR EACH ROW");
            sqlbuild.append("\n");
            sqlbuild.append("BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("INSERT INTO ");
            sqlbuild.append(table.getName()).append("2");
            sqlbuild.append(" VALUES (");
            sqlbuild.append(triggerStuff.getTriggerNewRowFields());
            sqlbuild.append(");");
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
