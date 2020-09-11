package org.xjrga.potatosql.generator;

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
    public String getCode() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE PROCEDURE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("_Insert (");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlProcedureVariablesAllMinusIdent());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append("MODIFIES SQL DATA BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append(statementInsert.getCode());
            if (table.identityExists()) {
                sqlbuild.append("SET newid = IDENTITY();");
                sqlbuild.append("\n");
            }
            sqlbuild.append("END;");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
