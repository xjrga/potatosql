package io.github.xjrga.potatosql.generator;

public class MethodProcedureSelect implements Code {

    private Table table;
    private JavaStuff javaStuff;
    private ProcedureStuff procedureStuff;

    public MethodProcedureSelect(Table table) {
        this.table = table;
        this.javaStuff = new JavaStuff(table);
        this.procedureStuff = new ProcedureStuff(table);
    }

    @Override
    public String getCode() {
        String procedureKind = "Select";
        String methodName = table.getName() + "_" + procedureKind;
        String methodParameters = javaStuff.getMethodParametersPrimaryKeyOnly();
        String sql = procedureStuff.getProcedureSqlStringPrimaryKeyOnly(procedureKind);
        String setParameters = procedureStuff.getSetParametersPrimaryKeyOnly();
        StringBuilder sqlbuild = new StringBuilder();
        String rowput = javaStuff.getResultSetObjectString();
        String method = "    public List<Map<String, Object>> " + methodName + "(" + methodParameters + ") throws SQLException\n"
                + "    {\n"
                + "        List<Map<String, Object>> list = new LinkedList<>();\n"
                + "        CallableStatement proc;\n"
                + "        List<String> columnLabelList = new LinkedList();\n"
                + "            proc = connection.prepareCall(" + sql + ");\n"
                + "            " + setParameters + "\n"
                + "            ResultSet rs = proc.executeQuery();\n"
                + "            while (rs.next())\n"
                + "            {\n"
                + "                Map<String, Object> row = new HashMap<>();\n"
                + rowput
                + "                list.add(row);\n"
                + "            }\n"
                + "            proc.close();\n"
                + "        return list;\n"
                + "    }";
        sqlbuild.append(method);
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
