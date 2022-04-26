package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class CreateTable implements Code {

    private final Table table;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;

    public CreateTable(Table table, SqlStuff sqlStuff) {
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
            sqlbuild.append("\n");
            sqlbuild.append("(");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlColumnDefinitionsForTable());
            if (table.containsPrimaryKeys()) {
                sqlbuild.append(",");
                sqlbuild.append("\n");
                sqlbuild.append("CONSTRAINT");
                sqlbuild.append(" ");
                sqlbuild.append(table.getName());
                sqlbuild.append("_primaryKey");
                sqlbuild.append(" ");
                sqlbuild.append("PRIMARY KEY");
                sqlbuild.append(" ");
                sqlbuild.append("(");
                sqlbuild.append(sqlStuff.getSqlParametersPrimary());
                sqlbuild.append(")");
            }
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append(";");
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        } else {
            sqlbuild.append("--Please add fields to ");
            sqlbuild.append(table.getName());
            sqlbuild.append(" table");
        }
        return sqlbuild.toString();
    }
}
