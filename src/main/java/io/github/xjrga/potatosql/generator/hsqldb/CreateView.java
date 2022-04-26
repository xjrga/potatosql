package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class CreateView implements Code {

    private final Table table;
    private final Code statementSelectAll;
    private final StringBuilder sqlbuild;

    public CreateView(Table table, SqlStuff sqlStuff) {
        this.table = table;
        statementSelectAll = new StatementSelectAll(table, sqlStuff);
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE View");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_View");
            sqlbuild.append(" ");
            sqlbuild.append("\n");
            sqlbuild.append("AS");
            sqlbuild.append("\n");
            sqlbuild.append(statementSelectAll.get_code());
        }
        return sqlbuild.toString();
    }

}
