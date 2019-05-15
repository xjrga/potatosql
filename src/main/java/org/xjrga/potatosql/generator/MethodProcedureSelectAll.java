package org.xjrga.potatosql.generator;

public class MethodProcedureSelectAll implements Code
{

    private Table table;
    private ProcedureStuff procedureStuff;

    public MethodProcedureSelectAll(Table table)
    {

        this.table = table;
        this.procedureStuff = new ProcedureStuff(table);
    }


    @Override
    public String getCode()
    {
        String procedureKind = "Select_All";
        String methodName = table.getName() + "_" + procedureKind;
        String methodParameters = "";
        String sql = procedureStuff.getProcedureSqlStringNone(procedureKind);
        String setParameters = "";
        StringBuilder sqlbuild = new StringBuilder();
        String method = "public List<Map<String, Object>> " + methodName + "() throws SQLException\n" +
                "    {\n" +
                "        LinkedList<Map<String, Object>> list = new LinkedList<>();\n" +
                "        CallableStatement proc;\n" +
                "        LinkedList<String> columnLabelList = new LinkedList();\n" +
                "        proc = connection.prepareCall(" + sql + ");\n" +
                "        ResultSet rs = proc.executeQuery();\n" +
                "        ResultSetMetaData metaData = rs.getMetaData();\n" +
                "        int columnCount = metaData.getColumnCount();\n" +
                "        for (int columnPos = 0; columnPos < columnCount; columnPos++)\n" +
                "        {\n" +
                "            String columnName = metaData.getColumnLabel(columnPos + 1);\n" +
                "            columnLabelList.add(columnName);\n" +
                "        }\n" +
                "        while (rs.next())\n" +
                "        {\n" +
                "            Map<String, Object> row = new HashMap<>();\n" +
                "            for (int columnPos = 0; columnPos < columnCount; columnPos++)\n" +
                "            {\n" +
                "                Object columnValue = rs.getObject(columnPos + 1);\n" +
                "                row.put(columnLabelList.get(columnPos), columnValue);\n" +
                "            }\n" +
                "            list.add(row);\n" +
                "        }\n" +
                "        proc.close();\n" +
                "        return list;\n" +
                "    }\n";
        sqlbuild.append(method);
        return sqlbuild.toString();
    }

}