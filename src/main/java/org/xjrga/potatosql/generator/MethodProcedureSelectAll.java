package org.xjrga.potatosql.generator;

public class MethodProcedureSelectAll implements Code {

    private final Table table;
    private final ProcedureStuff procedureStuff;
    private final JavaStuff javaStuff;

    public MethodProcedureSelectAll(Table table) {

        this.table = table;
        this.procedureStuff = new ProcedureStuff(table);
        this.javaStuff = new JavaStuff(table);
    }


    @Override
    public String getCode() {
        String procedureKind = "Select_All";
        String methodName = table.getName() + "_" + procedureKind;
        String methodParameters = "";
        String sql = procedureStuff.getProcedureSqlStringNone(procedureKind);
        String setParameters = "";
        StringBuilder sqlbuild = new StringBuilder();
        String rowput = javaStuff.getResultSetObjectString();
        String methodType = javaStuff.getMethodType();
        String objectName = javaStuff.getDataObjectName();
        String instance = javaStuff.getDataObjectInstance();

        String method = "public List<" + methodType + "> " + methodName + "() throws SQLException\n" +
                "    {\n" +
                "        List<" + methodType + "> list = new LinkedList<>();\n" +
                "        CallableStatement proc;\n" +
                "        proc = connection.prepareCall(" + sql + ");\n" +
                "        ResultSet rs = proc.executeQuery();\n" +
                "        while (rs.next())\n" +
                "        {\n" +
                "                " + instance + "\n" +
                "                " + rowput + "\n" +
                "                list.add(" + objectName + ");\n" +
                "        }\n" +
                "        proc.close();\n" +
                "        return list;\n" +
                "    }\n";
        sqlbuild.append(method);
        return sqlbuild.toString();
    }
}