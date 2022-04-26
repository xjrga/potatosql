package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureStuff;

public class MethodProcedureUpdate implements Code {

    private final Table table;
    private final ProcedureStuff procedureStuff;
    private final StringBuilder sqlbuild;

    public MethodProcedureUpdate(Table table) {
        this.table = table;
        this.procedureStuff = new ProcedureStuff(table);
        this.sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (table.containsNonPrimaryKeys()) {
            String procedureKind = "update";
            String transfer_object_name = "O_" + table.getName();
            String methodParameters = transfer_object_name + " " + table.getName();
            String sql = procedureStuff.getProcedureSqlStringAll(procedureKind);
            String setParameters = procedureStuff.getSetParametersAll();

            String method = "public void update(" + methodParameters + ") throws SQLException\n"
                    + "    {\n"
                    + "            CallableStatement proc = connection.prepareCall(" + sql + ");\n"
                    + "            " + setParameters + "\n"
                    + "            proc.execute();\n"
                    + "            proc.close();\n"
                    + "    }";
            sqlbuild.append(method);
            sqlbuild.append("\n");
            sqlbuild.append("\n");
        }

        return sqlbuild.toString();
    }

}
