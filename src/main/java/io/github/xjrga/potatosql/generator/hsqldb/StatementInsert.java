package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class StatementInsert implements Code {

    private final Table table;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;

    public StatementInsert(Table table, SqlStuff sqlStuff) {
        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("INSERT INTO");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append(" ");
            sqlbuild.append("(");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlParametersAll());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append(" ");
            sqlbuild.append("VALUES");
            sqlbuild.append(" ");
            sqlbuild.append("(");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlVariablesAllSubIdent());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append(";");
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
