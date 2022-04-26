package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class ProcedureSelect implements Code {

    private final Table table;
    private final Code statementSelect;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;

    public ProcedureSelect(Table table, SqlStuff sqlStuff) {
        this.table = table;
        statementSelect = new StatementSelect(table, sqlStuff);
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE PROCEDURE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_select (");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlProcedureVariablesPrimary());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append("MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("DECLARE result CURSOR");
            sqlbuild.append("\n");
            sqlbuild.append("FOR");
            sqlbuild.append("\n");
            sqlbuild.append(statementSelect.get_code());
            sqlbuild.setLength(sqlbuild.length() - 1);
            sqlbuild.append("OPEN result;");
            sqlbuild.append("\n");
            sqlbuild.append("END;");
            sqlbuild.append("\n");
            sqlbuild.append("/");
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
