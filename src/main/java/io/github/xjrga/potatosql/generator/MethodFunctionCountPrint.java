package io.github.xjrga.potatosql.generator;

public class MethodFunctionCountPrint implements Code {

    private Table table;

    public MethodFunctionCountPrint(Table table) {
        this.table = table;
    }

    @Override
    public String getCode() {
        String methodName1 = table.getName() + "_Count_Print()";
        String methodName2 = table.getName() + "__Count()";
        StringBuilder sqlbuild = new StringBuilder();
        String method = "public void "+methodName1+" throws SQLException {\n"
                + "        System.out.println("+methodName2+");\n"
                + "    }";
        sqlbuild.append(method);
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
