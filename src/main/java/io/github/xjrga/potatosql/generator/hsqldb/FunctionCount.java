package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class FunctionCount implements Code {

    private final Table table;
    private final Code statementSelectCountInto;
    private final StringBuilder sqlbuild;

    public FunctionCount(Table table) {
        this.table = table;
        this.statementSelectCountInto = new StatementSelectCountInto(table);
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE FUNCTION");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_count() RETURNS INTEGER");
            sqlbuild.append("\n");
            sqlbuild.append("READS SQL DATA BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("DECLARE v_count INTEGER;");
            sqlbuild.append("\n");
            sqlbuild.append(statementSelectCountInto.get_code());
            sqlbuild.append("RETURN v_count;");
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
