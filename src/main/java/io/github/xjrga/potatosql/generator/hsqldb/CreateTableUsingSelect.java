package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class CreateTableUsingSelect implements Code {

    private final Table table;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;

    public CreateTableUsingSelect(Table table, SqlStuff sqlStuff) {
        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE TABLE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("2");
            sqlbuild.append("\n");
            sqlbuild.append("(");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlParametersAll());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append("AS (\nSELECT * FROM");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append(" ");
            sqlbuild.append("\n) WITH DATA");
            sqlbuild.append(";");
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
