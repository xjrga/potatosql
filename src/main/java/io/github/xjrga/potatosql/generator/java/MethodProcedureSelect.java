package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureStuff;

public class MethodProcedureSelect implements Code {

    private final Table table;
    private final ProcedureStuff procedureStuff;

    public MethodProcedureSelect(Table table) {
        this.table = table;
        this.procedureStuff = new ProcedureStuff(table);
    }

    @Override
    public String get_code() {
        String procedureKind = "select";
        String transfer_object_name = "O_" + table.getName();
        String methodParameters = transfer_object_name + " " + table.getName();
        String sql = procedureStuff.getProcedureSqlStringPrimaryKeyOnly(procedureKind);
        String setParameters = procedureStuff.getSetParametersPrimaryKeyOnly();
        StringBuilder sqlbuild = new StringBuilder();

        String method = "public List<" + transfer_object_name + "> find(" + methodParameters + ") throws SQLException\n"
                + "     {\n"
                + "        BeanListHandler<" + transfer_object_name + "> beanListHandler = new BeanListHandler<>(" + transfer_object_name + ".class);\n"
                + "        CallableStatement proc = connection.prepareCall(" + sql + ");\n"
                + "        " + setParameters + "\n"
                + "        ResultSet rs = proc.executeQuery();\n"
                + "        proc.close();\n"
                + "        return beanListHandler.handle(rs);\n"
                + "     }";
        sqlbuild.append(method);
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
