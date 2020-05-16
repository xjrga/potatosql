package org.xjrga.potatosql.generator;


public class MethodProcedureInsert implements Code {

    private final Table table;
    private final JavaStuff javaStuff;
    private final ProcedureStuff procedureStuff;

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
        String methodType = "";
        String objectName = "";

        if (table.identityExists()) {

            methodParameters = javaStuff.getDataObject();

            setParameters = procedureStuff.getSetParametersAllMinusIdentity();
            ident = javaStuff.getIdent02();
            methodType = javaStuff.getMethodType();
            objectName = javaStuff.getDataObjectName();

            method = "public " + methodType + " " + methodName + "(" + methodParameters + ") throws SQLException\n" +
                    "    {\n" +
                    "        CallableStatement proc = connection.prepareCall(" + sql + ");\n" +
                    "        " + setParameters + "\n" +
                    "        proc.execute();\n" +
                    "            " + ident + "\n" +
                    "        proc.close();\n" +
                    "        return " + objectName + ";\n" +
                    "    }";
        } else {
            methodParameters = javaStuff.getDataObject();
            setParameters = procedureStuff.getSetParametersAll();
            method = "public void " + methodName + "(" + methodParameters + ") throws SQLException\n" +
                    "    {\n" +
                    "        CallableStatement proc = connection.prepareCall(" + sql + ");\n" +
                    "        " + setParameters + "\n" +
                    "        proc.execute();\n" +
                    "        proc.close();\n" +
                    "    }";
        }

        StringBuilder sqlbuild = new StringBuilder();
        sqlbuild.append(method);
        return sqlbuild.toString();
    }

}