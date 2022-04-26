package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class TriggerRowBeforeInsert implements Code {

    private final Table table;
    private final StringBuilder sqlbuild;

    public TriggerRowBeforeInsert(Table table) {
        this.table = table;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE TRIGGER");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_row_level_before_insert_trigger");
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
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }

}
