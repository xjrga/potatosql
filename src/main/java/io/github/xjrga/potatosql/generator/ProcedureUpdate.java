package io.github.xjrga.potatosql.generator;

public class ProcedureUpdate implements Code {

    private Table table;
    private Code statementUpdate;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;

    public ProcedureUpdate(Table table, SqlStuff sqlStuff) {

        this.table = table;
        this.statementUpdate = new StatementUpdate(table, sqlStuff);
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode() {

        if (table.containsNonPrimaryKeys()) {
            sqlbuild.append("CREATE PROCEDURE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_Update (");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlProcedureVariablesAll());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append("MODIFIES SQL DATA BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append(statementUpdate.getCode());
            sqlbuild.setLength(sqlbuild.length()-1);
            sqlbuild.append("END;");
            sqlbuild.append("\n");
            sqlbuild.append("/");
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }

}
