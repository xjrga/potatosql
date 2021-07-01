package io.github.xjrga.potatosql.generator;

public class StatementSelectAll implements Code {

    private Table table;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;

    public StatementSelectAll(Table table, SqlStuff sqlStuff) {

        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode() {

        if (!table.isEmpty()) {
            sqlbuild.append("SELECT");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlParametersAll());
            sqlbuild.append(" \n");
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
