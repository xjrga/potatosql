package io.github.xjrga.potatosql.generator;

public class ProcedureDeleteAll implements Code {

    private Table table;
    private Code statementDeleteAll;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;

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
        sqlbuild.append(table.getName());
        sqlbuild.append("_Delete_All ()");
        sqlbuild.append("\n");
        sqlbuild.append("MODIFIES SQL DATA BEGIN ATOMIC");
        sqlbuild.append("\n");
        sqlbuild.append(statementDeleteAll.getCode());
        sqlbuild.setLength(sqlbuild.length()-1);
        sqlbuild.append("END;");
        sqlbuild.append("\n");
        sqlbuild.append("/");
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
