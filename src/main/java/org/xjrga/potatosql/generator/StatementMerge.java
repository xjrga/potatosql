package org.xjrga.potatosql.generator;

public class StatementMerge implements Code {

    private final Table table;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;


    public StatementMerge(Table table, SqlStuff sqlStuff) {

        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode() {

        if (table.containsNonPrimaryKeys()) {
            sqlbuild.append("MERGE INTO");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append(" ");
            sqlbuild.append("USING");
            sqlbuild.append(" ");
            sqlbuild.append("(");
            sqlbuild.append(" ");
            sqlbuild.append("VALUES");
            sqlbuild.append(" ");
            sqlbuild.append("(");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlVariablesAll());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append(" ");
            sqlbuild.append(")");
            sqlbuild.append(" ");
            sqlbuild.append("ON");
            sqlbuild.append(" ");
            sqlbuild.append("(");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlWhereStuff_Primary());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append("WHEN MATCHED THEN UPDATE SET");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlSetStuff_NonPrimary());
            sqlbuild.append("\n");
            sqlbuild.append("WHEN NOT MATCHED THEN INSERT VALUES");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlVariablesAll());
            sqlbuild.append(";");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }

}
