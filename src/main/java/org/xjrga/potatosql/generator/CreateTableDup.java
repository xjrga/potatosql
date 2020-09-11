package org.xjrga.potatosql.generator;

public class CreateTableDup implements Code {
    private final Table table;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;

    public CreateTableDup(Table table, SqlStuff sqlStuff) {
        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE TABLE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("2");
            sqlbuild.append("\n");
            sqlbuild.append("(");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlColumnDefinitionsForTable());
            sqlbuild.append(",");
            sqlbuild.append("\n");
            sqlbuild.append("CONSTRAINT");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("2");
            sqlbuild.append("_primaryKey");
            sqlbuild.append(" ");
            sqlbuild.append("PRIMARY KEY");
            sqlbuild.append(" ");
            sqlbuild.append("(");
            sqlbuild.append(sqlStuff.getSqlParametersPrimary());
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append(";");
            sqlbuild.append("\n");
        } else {
            sqlbuild.append("--Please add fields to ");
            sqlbuild.append(table.getName());
            sqlbuild.append("2");
            sqlbuild.append(" table");
        }
        return sqlbuild.toString();
    }
}
