package org.xjrga.potatosql.generator;

import java.util.Iterator;

public class DataObjectStuff {
    private final Table table;

    public DataObjectStuff(Table table) {
        this.table = table;
    }

    public String getConstructor() {
        StringBuilder sb = new StringBuilder();
        sb.append("public ");
        sb.append(table.getName());
        sb.append("DataObject");
        sb.append("()");
        sb.append("\n");
        sb.append("{");
        sb.append("\n\n");
        sb.append("}");
        return sb.toString();
    }

    public String getFields() {
        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("private ");
            String javaType = hsqldbTypeToJavaType.getJavaType(column.getTypeName());
            sb.append(javaType);
            sb.append(" ");
            sb.append(column.getName());
            if (javaType == "Integer") {
                sb.append(" = -1");
            }
            if (javaType == "Double") {
                sb.append(" = -1.0");
            }
            sb.append(";");
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public String getSetters() {
        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("public void set");
            sb.append(column.getName());
            sb.append("(");
            sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
            sb.append(" ");
            sb.append(column.getName());
            sb.append(")");
            sb.append("\n");
            sb.append("{");
            sb.append("\n");
            sb.append("this.");
            sb.append(column.getName() + "=" + column.getName() + ";");
            sb.append("\n");
            sb.append("}");
            sb.append("\n");
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getGetters() {
        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("public ");
            sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
            sb.append(" get");
            sb.append(column.getName());
            sb.append("()");
            sb.append("\n");
            sb.append("{");
            sb.append("\n");
            sb.append("return ");
            sb.append(column.getName() + ";");
            sb.append("\n");
            sb.append("}");
            sb.append("\n");
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("public String toString()");
        sb.append("\n");
        sb.append("{");
        sb.append("\n");
        sb.append("StringBuilder sb = new StringBuilder();");
        sb.append("\n");
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("sb.append(");
            sb.append(column.getName());
            sb.append(");");
            sb.append("\n");
            sb.append("sb.append(\":\");");
            sb.append("\n");
        }
        sb.setLength(sb.length() - 16);
        sb.append("return sb.toString();");
        sb.append("\n");
        sb.append("}");
        return sb.toString();
    }

}
