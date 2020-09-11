package org.xjrga.potatosql.generator;

public class ProcedureDeleteAll implements Code {
    private final Table table;
    private final Code statementDeleteAll;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;

    public ProcedureDeleteAll(Table table, SqlStuff sqlStuff) {
        this.table = table;
        statementDeleteAll = new StatementDeleteAll(table, sqlStuff);
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode() {
        sqlbuild.append("CREATE PROCEDURE");
        sqlbuild.append(" ");
        sqlbuild.append(table.getSchema());
        sqlbuild.append(".");
        sqlbuild.append(table.getName());
        sqlbuild.append("_Delete_All ()");
        sqlbuild.append("\n");
        sqlbuild.append("MODIFIES SQL DATA BEGIN ATOMIC");
        sqlbuild.append("\n");
        sqlbuild.append(statementDeleteAll.getCode());
        sqlbuild.append("END;");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }
}
