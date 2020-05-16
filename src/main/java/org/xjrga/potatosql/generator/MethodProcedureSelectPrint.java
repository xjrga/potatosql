package org.xjrga.potatosql.generator;

public class MethodProcedureSelectPrint implements Code {
    private final Table table;
    private final JavaStuff javaStuff;

    public MethodProcedureSelectPrint(Table table) {
        this.table = table;
        this.javaStuff = new JavaStuff(table);
    }


    @Override
    public String getCode() {
        String methodName1 = table.getName() + "_Select_Print";
        String methodName2 = table.getName() + "_Select";
        String methodParameters = javaStuff.getDataObject();
        String methodVariables = javaStuff.getDataObjectName();
        String rowGetAllKeys = javaStuff.getRowGetAllKeys();
        StringBuilder sqlbuild = new StringBuilder();
        String sout = javaStuff.getSout();
        String methodType = javaStuff.getMethodType();
        String objectName = javaStuff.getDataObjectName();

        String method = "public void " + methodName1 + "(" + methodParameters + ") throws SQLException\n" +
                "    {\n" +
                "        LinkedList<" + methodType + "> list = (LinkedList) this." + methodName2 + "(" + methodVariables + ");\n" +
                "        Iterator it = list.listIterator();\n" +
                "        while(it.hasNext()){\n" +
                "            " + methodType + " " + objectName + " = (" + methodType + ") it.next();\n" +
                "            " + rowGetAllKeys + "\n" +
                "            " + sout + "\n" +
                "        }\n" +
                "    }";
        sqlbuild.append(method);
        return sqlbuild.toString();
    }

}