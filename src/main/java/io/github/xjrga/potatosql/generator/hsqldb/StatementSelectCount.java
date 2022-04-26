package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class StatementSelectCount implements Code {

    private final Table table;
    private final StringBuilder sqlbuild;

    public StatementSelectCount(Table table) {
        this.table = table;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("SELECT COUNT(*)");
            sqlbuild.append("\n");
            sqlbuild.append("FROM");
            sqlbuild.append("\n");
            sqlbuild.append(table.getName());
            sqlbuild.append(";");
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
