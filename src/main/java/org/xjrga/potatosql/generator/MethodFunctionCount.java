package org.xjrga.potatosql.generator;

public class MethodFunctionCount implements Code
{
    private Table table;
    private ProcedureStuff procedureStuff;

    public MethodFunctionCount(Table table)
    {
        this.table = table;
        this.procedureStuff = new ProcedureStuff(table);
    }

    @Override
    public String getCode()
    {
        String procedureKind = "Count";
        String methodName = table.getName() + "_" + procedureKind;
        String methodParameters = "";
        String sql = procedureStuff.getProcedureSqlStringNone(procedureKind);
        String setParameters = "";
        StringBuilder sqlbuild = new StringBuilder();
        String method = "public Integer " + methodName + "() throws SQLException\n" +
                "    {\n" +
                "        CallableStatement proc;\n" +
                "        Integer out = null;\n" +
                "        proc = connection.prepareCall(" + sql + ");\n" +
                "        proc.execute();\n" +
                "        ResultSet resultSet = proc.getResultSet();\n" +
                "        resultSet.next();\n" +
                "        out = resultSet.getInt(1);\n" +
                "        proc.close();\n" +
                "        return out;\n" +
                "    }";
        sqlbuild.append(method);
        return sqlbuild.toString();
    }

}