package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class StatementSelect implements Code {

    private final Table table;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;

    public StatementSelect(Table table, SqlStuff sqlStuff) {
        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("SELECT");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlParametersAll());
            sqlbuild.append("\n");
            sqlbuild.append("FROM");
            sqlbuild.append("\n");
            sqlbuild.append(table.getName());
            sqlbuild.append("\n");
            sqlbuild.append("WHERE");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlWhereStuff_Primary());
            sqlbuild.append(";");
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
