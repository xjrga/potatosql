package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class StatementDeleteAll implements Code {

    private final Table table;
    private final StringBuilder sqlbuild;

    public StatementDeleteAll(Table table) {
        this.table = table;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        sqlbuild.append("DELETE FROM");
        sqlbuild.append(" ");
        sqlbuild.append(table.getName());
        sqlbuild.append(";");
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
