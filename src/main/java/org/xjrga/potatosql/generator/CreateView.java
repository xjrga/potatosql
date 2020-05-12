package org.xjrga.potatosql.generator;

public class CreateView implements Code {

    private final Table table;
    private final Code statementSelectAll;
    private final SqlStuff sqlStuff;
    private final StringBuilder sqlbuild;


    public CreateView(Table table, SqlStuff sqlStuff) {

        this.table = table;
        statementSelectAll = new StatementSelectAll(table, sqlStuff);
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode() {

        if (!table.isEmpty()) {
            sqlbuild.append("CREATE View");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("_View");
            sqlbuild.append(" ");
            sqlbuild.append("\n");
            sqlbuild.append("AS");
            sqlbuild.append("\n");
            sqlbuild.append(statementSelectAll.getCode());

        }
        return sqlbuild.toString();
    }

}
