package io.github.xjrga.potatosql.generator;

public class ProcedureSelectAll implements Code {

    private Table table;
    private Code statementSelectAll;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;

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
            sqlbuild.setLength(sqlbuild.length()-1);
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
