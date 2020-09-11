package org.xjrga.potatosql.generator;

public class MethodProcedureMerge implements Code {
    private final Table table;
    private final JavaStuff javaStuff;
    private final ProcedureStuff procedureStuff;
    private final StringBuilder sqlbuild;

    public MethodProcedureMerge(Table table) {
        this.table = table;
        this.javaStuff = new JavaStuff(table);
        this.procedureStuff = new ProcedureStuff(table);
        this.sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode() {
        if (table.containsNonPrimaryKeys()) {
            String procedureKind = "Merge";
            String methodName = table.getName() + "_" + procedureKind;
            String methodParameters = javaStuff.getDataObject();
            String sql = procedureStuff.getProcedureSqlStringAll(procedureKind);
            String setParameters = procedureStuff.getSetParametersAll();
            String method = "public void " + methodName + "(" + methodParameters + ") throws SQLException\n" +
                    "    {\n" +
                    "            CallableStatement proc = connection.prepareCall(" + sql + ");\n" +
                    "            " + setParameters + "\n" +
                    "            proc.execute();\n" +
                    "            proc.close();\n" +
                    "    }\n";
            sqlbuild.append(method);
        }
        return sqlbuild.toString();
    }
}