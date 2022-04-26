package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class ProcedureDeleteAll implements Code {

    private final Table table;
    private final Code statementDeleteAll;
    private final StringBuilder sqlbuild;

    public ProcedureDeleteAll(Table table) {
        this.table = table;
        statementDeleteAll = new StatementDeleteAll(table);
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        sqlbuild.append("CREATE PROCEDURE");
        sqlbuild.append(" ");
        sqlbuild.append(table.getName());
        sqlbuild.append("_delete_all ()");
        sqlbuild.append("\n");
        sqlbuild.append("MODIFIES SQL DATA BEGIN ATOMIC");
        sqlbuild.append("\n");
        sqlbuild.append(statementDeleteAll.get_code());
        sqlbuild.setLength(sqlbuild.length() - 1);
        sqlbuild.append("END;");
        sqlbuild.append("\n");
        sqlbuild.append("/");
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
