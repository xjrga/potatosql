package org.xjrga.potatosql.generator;

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
    public String getCode() {
        if (!table.isEmpty()) {
            sqlbuild.append("INSERT INTO");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("\n");
            sqlbuild.append("(");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlParametersAll());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append(statementSelectAll.getCode());
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
