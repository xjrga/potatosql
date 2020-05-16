package org.xjrga.potatosql.generator;

public class MethodProcedureSelect implements Code {
    private final Table table;
    private final JavaStuff javaStuff;
    private final ProcedureStuff procedureStuff;

    public MethodProcedureSelect(Table table) {
        this.table = table;
        this.javaStuff = new JavaStuff(table);
        this.procedureStuff = new ProcedureStuff(table);
    }


    @Override
    public String getCode() {
        String procedureKind = "Select";
        String methodName = table.getName() + "_" + procedureKind;
        String methodParameters = javaStuff.getDataObject();
        String sql = procedureStuff.getProcedureSqlStringPrimaryKeyOnly(procedureKind);
        String setParameters = procedureStuff.getSetParametersPrimaryKeyOnly();
        StringBuilder sqlbuild = new StringBuilder();
        String rowput = javaStuff.getResultSetObjectString();
        String methodType = javaStuff.getMethodType();
        String objectName = javaStuff.getDataObjectName();

        String method = "    public List<" + methodType + "> " + methodName + "(" + methodParameters + ") throws SQLException\n" +
                "    {\n" +
                "        LinkedList<" + methodType + "> list = new LinkedList<>();\n" +
                "        CallableStatement proc;\n" +
                "            proc = connection.prepareCall(" + sql + ");\n" +
                "            " + setParameters + "\n" +
                "            ResultSet rs = proc.executeQuery();\n" +
                "            while (rs.next())\n" +
                "            {\n" +
                rowput +
                "                list.add(" + objectName + ");\n" +
                "            }\n" +
                "            proc.close();\n" +
                "        return list;\n" +
                "    }\n";
        sqlbuild.append(method);
        return sqlbuild.toString();
    }

}