package io.github.xjrga.potatosql.generator;

public class MethodProcedureInsert implements Code {

    private Table table;
    private JavaStuff javaStuff;
    private ProcedureStuff procedureStuff;

    public MethodProcedureInsert(Table table) {
        this.table = table;
        this.javaStuff = new JavaStuff(table);
        this.procedureStuff = new ProcedureStuff(table);
    }

    @Override
    public String getCode() {
        String procedureKind = "Insert";
        String methodName = table.getName() + "_" + procedureKind;
        String methodParameters = "";
        String sql = procedureStuff.getProcedureSqlStringAll(procedureKind);
        String setParameters = "";
        String ident = "";
        String method = "";

        if (table.identityExists()) {
            methodParameters = javaStuff.getMethodParametersAllMinusIdent();
            setParameters = procedureStuff.getSetParametersAllMinusIdentity();
            ident = procedureStuff.getIdent();
            method = "public Integer " + methodName + "(" + methodParameters + ") throws SQLException\n"
                    + "    {\n"
                    + "        Integer ident = null;\n"
                    + "        CallableStatement proc = connection.prepareCall(" + sql + ");\n"
                    + "        " + setParameters + "\n"
                    + "        proc.execute();\n"
                    + "            " + ident + "\n"
                    + "        proc.close();\n"
                    + "        return ident;\n"
                    + "    }";
        } else {
            methodParameters = javaStuff.getMethodParametersAll();
            setParameters = procedureStuff.getSetParametersAll();
            method = "public void " + methodName + "(" + methodParameters + ") throws SQLException\n"
                    + "    {\n"
                    + "        CallableStatement proc = connection.prepareCall(" + sql + ");\n"
                    + "        " + setParameters + "\n"
                    + "        proc.execute();\n"
                    + "        proc.close();\n"
                    + "    }";
        }

        StringBuilder sqlbuild = new StringBuilder();
        sqlbuild.append(method);
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }
}
