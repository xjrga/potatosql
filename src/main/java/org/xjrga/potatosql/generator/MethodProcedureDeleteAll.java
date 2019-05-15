package org.xjrga.potatosql.generator;

public class MethodProcedureDeleteAll implements Code
{
    private Table table;
    private ProcedureStuff procedureStuff;

    public MethodProcedureDeleteAll(Table table)
    {

        this.table = table;
        this.procedureStuff = new ProcedureStuff(table);
    }


    @Override
    public String getCode()
    {
        String procedureKind = "Delete_All";
        String methodName = table.getName() + "_" + procedureKind;
        String methodParameters = "";
        String sql = procedureStuff.getProcedureSqlStringNone(procedureKind);
        String setParameters = "";
        StringBuilder sqlbuild = new StringBuilder();
        String method = "public void " + methodName + "() throws SQLException\n" +
                "    {\n" +
                "            CallableStatement proc = connection.prepareCall(" + sql + ");\n" +
                "            proc.execute();\n" +
                "    }";
        sqlbuild.append(method);
        return sqlbuild.toString();
    }

}