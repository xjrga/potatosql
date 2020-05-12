package org.xjrga.potatosql.generator;

public class MethodListStuff {
    private final Table table;
    private final JavaStuff javaStuff;


    public MethodListStuff(Table table) {
        this.table = table;
        this.javaStuff = new JavaStuff(table);
    }

    public String getToString() {
        StringBuilder sb = new StringBuilder();
        String methodName = table.getName() + "_Delete";
        String methodParameters = javaStuff.getMethodParametersPrimaryKeyOnly();
        String method = "this." + methodName + "( " + methodParameters + " );";
        sb.append(method);
        return sb.toString();
    }

}
