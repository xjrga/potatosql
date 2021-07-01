package io.github.xjrga.potatosql.generator;

public class StatementUpdate implements Code {

    private Table table;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;

    public StatementUpdate(Table table, SqlStuff sqlStuff) {

        this.table = table;
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode() {

        if (table.containsNonPrimaryKeys()) {
            sqlbuild.append("\r");
            sqlbuild.append("UPDATE");
            sqlbuild.append("\r");
            sqlbuild.append(table.getName());
            sqlbuild.append("\r");
            sqlbuild.append("SET");
            sqlbuild.append("\r");
            sqlbuild.append(sqlStuff.getSqlSetStuff_NonPrimary());
            sqlbuild.append("\r");
            sqlbuild.append("WHERE");
            sqlbuild.append("\r");
            sqlbuild.append(sqlStuff.getSqlWhereStuff_Primary());
            sqlbuild.append(";");
            sqlbuild.append("\r");
            sqlbuild.append("\r");
        }
        return sqlbuild.toString();
    }

}
