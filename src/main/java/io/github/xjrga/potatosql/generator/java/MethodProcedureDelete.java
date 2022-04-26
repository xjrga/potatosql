package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureStuff;

public class MethodProcedureDelete implements Code {

    private final Table table;
    private final ProcedureStuff procedureStuff;

    public MethodProcedureDelete(Table table) {
        this.table = table;
        this.procedureStuff = new ProcedureStuff(table);
    }

    @Override
    public String get_code() {
        String procedureKind = "delete";
        String transfer_object_name = "O_" + table.getName();
        String methodParameters = transfer_object_name + " " + table.getName();
        String sql = procedureStuff.getProcedureSqlStringPrimaryKeyOnly(procedureKind);
        String setParameters = procedureStuff.getSetParametersPrimaryKeyOnly();
        StringBuilder sqlbuild = new StringBuilder();
        String method = "public void delete(" + methodParameters + ") throws SQLException\n"
                + "    {\n"
                + "            CallableStatement proc = connection.prepareCall(" + sql + ");\n"
                + "            " + setParameters + "\n"
                + "            proc.execute();\n"
                + "            proc.close();\n"
                + "     }";
        sqlbuild.append(method);
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
