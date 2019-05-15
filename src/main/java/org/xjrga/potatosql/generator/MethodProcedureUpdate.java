package org.xjrga.potatosql.generator;

public class MethodProcedureUpdate implements Code
{
    private Table table;
    private JavaStuff javaStuff;
    private ProcedureStuff procedureStuff;
    private StringBuilder sqlbuild;

    public MethodProcedureUpdate(Table table)
    {
        this.table = table;
        this.javaStuff = new JavaStuff(table);
        this.procedureStuff = new ProcedureStuff(table);
        this.sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode()
    {
        if (table.containsNonPrimaryKeys())
        {
            String procedureKind = "Update";
            String methodName = table.getName() + "_" + procedureKind;
            String methodParameters = javaStuff.getMethodParametersAll();
            String sql = procedureStuff.getProcedureSqlStringAll(procedureKind);
            String setParameters = procedureStuff.getSetParametersAll();

            String method = "public void " + methodName + "(" + methodParameters + ") throws SQLException\n" +
                    "    {\n" +
                    "            CallableStatement proc = connection.prepareCall(" + sql + ");\n" +
                    "            " + setParameters + "\n" +
                    "            proc.execute();\n" +
                    "    }";
            sqlbuild.append(method);
        }

        return sqlbuild.toString();
    }

}