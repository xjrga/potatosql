package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureStuff;

public class MethodProcedureDeleteAll implements Code {

    private final ProcedureStuff procedureStuff;
    private final Table table;

    public MethodProcedureDeleteAll(Table table) {
        this.table = table;
        this.procedureStuff = new ProcedureStuff(table);
    }

    @Override
    public String get_code() {
        String procedureKind = "delete_all";
        String transfer_object_name = "O_" + table.getName();
        String methodParameters = transfer_object_name + " " + table.getName();
        String sql = procedureStuff.getProcedureSqlStringNone(procedureKind);
        StringBuilder sqlbuild = new StringBuilder();
        String method = "public void delete_all(" + methodParameters + ") throws SQLException\n"
                + "    {\n"
                + "            CallableStatement proc = connection.prepareCall(" + sql + ");\n"
                + "            proc.execute();\n"
                + "            proc.close();\n"
                + "    }";
        sqlbuild.append(method);
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }
}
