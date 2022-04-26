package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Column;
import io.github.xjrga.potatosql.generator.Table;
import io.github.xjrga.potatosql.other.Utilities;
import java.util.Iterator;

public class Data_transfer_object_stuff {

    private final Table table;

    public Data_transfer_object_stuff(Table table) {
        this.table = table;
    }

    public String getConstructor() {
        StringBuilder sb = new StringBuilder();
        sb.append("public ");
        sb.append("O_").append(table.getName());
        sb.append("()");
        sb.append("{");
        sb.append("}");
        sb.append("\n");
        return sb.toString();
    }

    public String getFields() {
        Jdbc_type_to_java_type jdbc_type_to_java_type = new Jdbc_type_to_java_type();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("private ");
            sb.append(jdbc_type_to_java_type.get_java_type(column.get_type_name()));
            sb.append(" ");
            sb.append(column.get_name());
            sb.append(";");
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public String getSetters() {
        Jdbc_type_to_java_type hsqldbTypeToJavaType = new Jdbc_type_to_java_type();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("public void set");
            sb.append(Utilities.capitalize(column.get_name()));
            sb.append("(");
            sb.append(hsqldbTypeToJavaType.get_java_type(column.get_type_name()));
            sb.append(" ");
            sb.append(column.get_name());
            sb.append(")");
            sb.append("{");
            sb.append("this.");
            sb.append(column.get_name()).append("=").append(column.get_name()).append(";");
            sb.append("}");
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getGetters() {
        Jdbc_type_to_java_type hsqldbTypeToJavaType = new Jdbc_type_to_java_type();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("public ");
            sb.append(hsqldbTypeToJavaType.get_java_type(column.get_type_name()));
            sb.append(" get");
            sb.append(Utilities.capitalize(column.get_name()));
            sb.append("()");
            sb.append("{");
            sb.append("return ");
            sb.append(column.get_name()).append(";");
            sb.append("}");
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("@Override");
        sb.append("\n");
        sb.append("public String toString()");
        sb.append("{");
        Iterator it = table.getIterator();
        sb.append("return ");
        sb.append("\"");
        sb.append("O_").append(table.getName());
        sb.append("{");
        sb.append("\"");
        sb.append(" + ");
        sb.append("\"");
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append(column.get_name());
            sb.append("=");
            sb.append("\"");
            sb.append(" + ");
            sb.append(column.get_name());
            sb.append(" + ");
            sb.append("\"");
            sb.append(", ");
        }
        sb.setLength(sb.length() - 6);
        sb.append(" + ");
        sb.append("\"");
        sb.append("}");
        sb.append("\"");
        sb.append(";");
        return sb.toString();
    }

}
