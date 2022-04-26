package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureStuff;

public class MethodFunctionCount implements Code {

    private final ProcedureStuff procedureStuff;
    private final Table table;

    public MethodFunctionCount(Table table) {
        this.table = table;
        this.procedureStuff = new ProcedureStuff(table);
    }

    @Override
    public String get_code() {
        String procedureKind = "count";
        String transfer_object_name = "O_" + table.getName();
        String methodParameters = transfer_object_name + " " + table.getName();
        String sql = procedureStuff.getProcedureSqlStringNone(procedureKind);
        StringBuilder sqlbuild = new StringBuilder();
        String method = "public Integer count(" + methodParameters + ") throws SQLException\n"
                + "    {\n"
                + "        CallableStatement proc = connection.prepareCall(" + sql + ");\n"
                + "        proc.execute();\n"
                + "        ResultSet resultSet = proc.getResultSet();\n"
                + "        resultSet.next();\n"
                + "        Integer out = resultSet.getInt(1);\n"
                + "        proc.close();\n"
                + "        return out;\n"
                + "    }";
        sqlbuild.append(method);
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
