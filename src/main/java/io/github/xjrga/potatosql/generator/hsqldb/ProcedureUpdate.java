package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class ProcedureUpdate implements Code {

    private final Table table;
    private final Code statementUpdate;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;

    public ProcedureUpdate(Table table, SqlStuff sqlStuff) {

        this.table = table;
        this.statementUpdate = new StatementUpdate(table, sqlStuff);
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {

        if (table.containsNonPrimaryKeys()) {
            sqlbuild.append("CREATE PROCEDURE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_update (");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlProcedureVariablesAll());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append("MODIFIES SQL DATA BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append(statementUpdate.get_code());
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
