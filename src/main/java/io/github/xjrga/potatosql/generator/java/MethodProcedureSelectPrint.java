package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class MethodProcedureSelectPrint implements Code {

    private final Table table;

    public MethodProcedureSelectPrint(Table table) {
        this.table = table;
    }

    @Override
    public String get_code() {
        String transfer_object_name = "O_" + table.getName();
        String methodParameters = transfer_object_name + " " + table.getName();
        StringBuilder sqlbuild = new StringBuilder();
        String method = "public void print_find(" + methodParameters + ") throws SQLException\n"
                + "    {\n"
                + "        List<" + transfer_object_name + "> list = (" + "List<" + transfer_object_name + ">" + ")find(" + table.getName() + ");\n"
                + "        list.forEach(element -> System.out.println(element));\n"
                + "    }";
        sqlbuild.append(method);
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
