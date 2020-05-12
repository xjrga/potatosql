package org.xjrga.potatosql.generator;

public class StatementDelete implements Code {

    private final Table table;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;


    public StatementDelete(Table table, SqlStuff sqlStuff) {

        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode() {

        sqlbuild.append("DELETE FROM");
        sqlbuild.append("\n");
        sqlbuild.append(table.getSchema());
        sqlbuild.append(".");
        sqlbuild.append(table.getName());
        sqlbuild.append("\n");
        sqlbuild.append("WHERE");
        sqlbuild.append("\n");
        sqlbuild.append(sqlStuff.getSqlWhereStuff_Primary());
        sqlbuild.append(";");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
