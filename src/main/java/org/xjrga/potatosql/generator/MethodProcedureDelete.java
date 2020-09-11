package org.xjrga.potatosql.generator;

public class MethodProcedureDelete implements Code {
    private final Table table;
    private final JavaStuff javaStuff;
    private final ProcedureStuff procedureStuff;

    public MethodProcedureDelete(Table table) {
        this.table = table;
        this.javaStuff = new JavaStuff(table);
        this.procedureStuff = new ProcedureStuff(table);
    }

    @Override
    public String getCode() {
        String procedureKind = "Delete";
        String methodName = table.getName() + "_" + procedureKind;
        String methodParameters = javaStuff.getDataObject();
        String sql = procedureStuff.getProcedureSqlStringPrimaryKeyOnly(procedureKind);
        String setParameters = procedureStuff.getSetParametersPrimaryKeyOnly();
        StringBuilder sqlbuild = new StringBuilder();
        String method = "public void " + methodName + "( " + methodParameters + " ) throws SQLException\n" +
                "    {\n" +
                "            CallableStatement proc = connection.prepareCall(" + sql + ");\n" +
                "            " + setParameters + "\n" +
                "            proc.execute();\n" +
                "            proc.close();\n" +
                "     }";
        sqlbuild.append(method);
        return sqlbuild.toString();
    }
}