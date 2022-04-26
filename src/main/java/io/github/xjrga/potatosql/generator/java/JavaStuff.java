package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Column;
import io.github.xjrga.potatosql.generator.Table;
import java.util.Iterator;

public class JavaStuff {

    private final Table table;

    public JavaStuff(Table table) {
        this.table = table;
    }

    public String getMethodParametersAll() {
        Jdbc_type_to_java_type hsqldbTypeToJavaType = new Jdbc_type_to_java_type();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append(hsqldbTypeToJavaType.get_java_type(column.get_type_name()));
            sb.append(" ");
            sb.append(column.get_name());
            sb.append(",");
            sb.append(" ");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public String getMethodParametersPrimaryKeyOnly() {
        Jdbc_type_to_java_type hsqldbTypeToJavaType = new Jdbc_type_to_java_type();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext()) {
            Column column = (Column) it.next();

            if (column.isPrimaryKey()) {
                sb.append(hsqldbTypeToJavaType.get_java_type(column.get_type_name()));
                sb.append(" ");
                sb.append(column.get_name());
                sb.append(",");
                sb.append(" ");
            }
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public String getRowGetAllKeys() {

        Jdbc_type_to_java_type hsqldbTypeToJavaType = new Jdbc_type_to_java_type();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append(hsqldbTypeToJavaType.get_java_type(column.get_type_name()));
            sb.append(" ");
            sb.append(column.get_name());
            sb.append(" = ");
            sb.append("(");
            sb.append(hsqldbTypeToJavaType.get_java_type(column.get_type_name()));
            sb.append(")");
            sb.append("row.get(");
            sb.append("\"");
            sb.append(column.get_name().toUpperCase());
            sb.append("\"");
            sb.append(");\n");
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    public String getRowGetNonPrimaryKeys() {

        Jdbc_type_to_java_type hsqldbTypeToJavaType = new Jdbc_type_to_java_type();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext()) {
            Column column = (Column) it.next();
            if (!column.isPrimaryKey()) {
                sb.append(hsqldbTypeToJavaType.get_java_type(column.get_type_name()));
                sb.append(" ");
                sb.append(column.get_name());
                sb.append(" = ");
                sb.append("(");
                sb.append(hsqldbTypeToJavaType.get_java_type(column.get_type_name()));
                sb.append(")");
                sb.append("row.get(");
                sb.append("\"");
                sb.append(column.get_name().toUpperCase());
                sb.append("\"");
                sb.append(");\n");
            }
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }

    public String getSout() {

        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        sb.append("System.out.println(");

        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append(column.get_name());
            sb.append("+");
            sb.append("\",\"");
            sb.append("+");
        }

        sb.setLength(sb.length() - 5);
        sb.append(");");

        return sb.toString();
    }

    public String getMethodVariablesPrimaryKeyOnly() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext()) {
            Column column = (Column) it.next();

            if (column.isPrimaryKey()) {
                sb.append(column.get_name());
                sb.append(",");
            }
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    public String getResultSetObjectString() {
        StringBuilder sb = new StringBuilder();
        int count = table.getCountKey();
        for (int i = 0; i < count; i++) {
            sb.append("row.put(\"");
            sb.append(table.getColumn(i).get_name().toUpperCase());
            sb.append("\", ");
            sb.append("rs.getObject(");
            sb.append(i + 1);
            sb.append("));");
            sb.append("\n");
        }
        return sb.toString();
    }
}
