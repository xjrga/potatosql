package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class MethodFunctionCountPrint implements Code {

    private final Table table;

    public MethodFunctionCountPrint(Table table) {
        this.table = table;
    }

    @Override
    public String get_code() {
        String methodName1 = table.getName() + "_count_print()";
        String methodName2 = table.getName() + "_count()";
        StringBuilder sqlbuild = new StringBuilder();
        String method = "public void " + methodName1 + " throws SQLException {\n"
                + "        System.out.println(" + methodName2 + ");\n"
                + "    }";
        sqlbuild.append(method);
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
