package org.xjrga.potatosql.generator;

public class ProcedureSelectAll implements Code {

    private final Table table;
    private final Code statementSelectAll;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;


    public ProcedureSelectAll(Table table, SqlStuff sqlStuff) {

        this.table = table;
        statementSelectAll = new StatementSelectAll(table, sqlStuff);
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode() {

        if (!table.isEmpty()) {
            sqlbuild.append("CREATE PROCEDURE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("_Select_All ()");
            sqlbuild.append("\n");
            sqlbuild.append("MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("DECLARE result CURSOR");
            sqlbuild.append("\n");
            sqlbuild.append("FOR");
            sqlbuild.append("\n");
            sqlbuild.append(statementSelectAll.getCode());
            sqlbuild.append("OPEN result;");
            sqlbuild.append("\n");
            sqlbuild.append("END;");
            sqlbuild.append("\n");

        }
        return sqlbuild.toString();
    }

}
