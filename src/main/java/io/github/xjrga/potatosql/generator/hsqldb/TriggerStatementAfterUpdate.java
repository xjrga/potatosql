package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class TriggerStatementAfterUpdate implements Code {

    private final Table table;
    private final StringBuilder sqlbuild;

    public TriggerStatementAfterUpdate(Table table) {
        this.table = table;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE TRIGGER");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_statement_level_after_update_trigger");
            sqlbuild.append("\n");
            sqlbuild.append("AFTER UPDATE ON");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("\n");
            sqlbuild.append("FOR EACH STATEMENT");
            sqlbuild.append("\n");
            sqlbuild.append("BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("DELETE FROM ");
            sqlbuild.append(table.getName()).append("2;");
            sqlbuild.append("\n");
            sqlbuild.append("INSERT INTO ");
            sqlbuild.append(table.getName()).append("2 ");
            sqlbuild.append("(SELECT * FROM ");
            sqlbuild.append(table.getName());
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
