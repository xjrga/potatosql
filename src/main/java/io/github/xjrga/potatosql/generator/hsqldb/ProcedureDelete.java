package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class ProcedureDelete implements Code {

    private final Table table;
    private final Code statementDelete;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;

    public ProcedureDelete(Table table, SqlStuff sqlStuff) {

        this.table = table;
        this.statementDelete = new StatementDelete(table, sqlStuff);
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE PROCEDURE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_delete (");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlProcedureVariablesPrimary());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append("MODIFIES SQL DATA BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append(statementDelete.get_code());
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
