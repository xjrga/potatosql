package org.xjrga.potatosql.generator;


public class MethodProcedureInsert implements Code
{

    private Table table;
    private JavaStuff javaStuff;
    private ProcedureStuff procedureStuff;

    public MethodProcedureInsert(Table table)
    {
        this.table = table;
        this.javaStuff = new JavaStuff(table);
        this.procedureStuff = new ProcedureStuff(table);
    }

    @Override
    public String getCode()
    {
        String procedureKind = "Insert";
        String methodName = table.getName() + "_" + procedureKind;
        String methodParameters = javaStuff.getMethodParametersAllMinusIdent();
        String sql = procedureStuff.getProcedureSqlStringAll(procedureKind);
        String setParameters = procedureStuff.getSetParametersAllMinusIdentity();
        String ident = procedureStuff.getIdent();
        StringBuilder sqlbuild = new StringBuilder();
        String method = "public Integer " + methodName + "(" + methodParameters + ") throws SQLException\n" +
                "    {\n" +
                "        Integer ident = null;\n" +
                "        CallableStatement proc = connection.prepareCall(" + sql + ");\n" +
                "        " + setParameters + "\n" +
                "        proc.execute();\n" +
                "            " + ident + "\n" +
                "        return ident;\n" +
                "    }";
        sqlbuild.append(method);
        return sqlbuild.toString();
    }

}