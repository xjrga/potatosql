package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class StatementInsertUsingSelect implements Code {

    private final Table table;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;
    private final StatementSelectAll statementSelectAll;

    public StatementInsertUsingSelect(Table table, SqlStuff sqlStuff) {
        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
        statementSelectAll = new StatementSelectAll(table, sqlStuff);
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("INSERT INTO");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("\n");
            sqlbuild.append("(");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlParametersAll());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append(statementSelectAll.get_code());
        }
        return sqlbuild.toString();

    }
}
