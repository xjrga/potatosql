package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureStuff;

public class MethodProcedureInsert implements Code {

    private final Table table;
    private final ProcedureStuff procedureStuff;

    public MethodProcedureInsert(Table table) {
        this.table = table;
        this.procedureStuff = new ProcedureStuff(table);
    }

    @Override
    public String get_code() {
        String procedureKind = "insert";
        String transfer_object_name = "O_" + table.getName();
        String methodParameters = transfer_object_name + " " + table.getName();
        String sql = procedureStuff.getProcedureSqlStringAll(procedureKind);
        String setParameters = procedureStuff.getSetParametersAll();
        String method = "public void insert(" + methodParameters + ") throws SQLException\n"
                + "    {\n"
                + "        CallableStatement proc = connection.prepareCall(" + sql + ");\n"
                + "        " + setParameters + "\n"
                + "        proc.execute();\n"
                + "        proc.close();\n"
                + "    }";

        StringBuilder sqlbuild = new StringBuilder();
        sqlbuild.append(method);
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }
}
