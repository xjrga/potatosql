package io.github.xjrga.potatosql.generator;

public class PrintProcedureInsertCall implements Code {

    private Table table;
    private StringBuilder sqlbuild;
    private String str;

    public PrintProcedureInsertCall(Table table) {

        this.table = table;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode() {
        if (!table.isEmpty()) {
            sqlbuild.append("CALL ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_Insert(");
            sqlbuild.append(" ");
            sqlbuild.append(str);
            sqlbuild.append(" ");
            sqlbuild.append(");\n");
        }
        return sqlbuild.toString();
    }

    public void setStr(String str) {
        this.str = str;
    }

}
