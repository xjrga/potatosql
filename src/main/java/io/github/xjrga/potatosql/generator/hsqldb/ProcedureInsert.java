package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class ProcedureInsert implements Code {

    private final Table table;
    private final Code statementInsert;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;

    public ProcedureInsert(Table table, SqlStuff sqlStuff) {
        this.table = table;
        this.statementInsert = new StatementInsert(table, sqlStuff);
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE PROCEDURE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_insert (");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlProcedureVariablesAllMinusIdent());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append("MODIFIES SQL DATA BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append(statementInsert.get_code());
            sqlbuild.setLength(sqlbuild.length() - 1);
            sqlbuild.append("END;");
            sqlbuild.append("\n");
            sqlbuild.append("/");
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
