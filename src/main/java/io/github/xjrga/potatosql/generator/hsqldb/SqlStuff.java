package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Column;
import io.github.xjrga.potatosql.generator.Table;
import java.util.Iterator;

public class SqlStuff {

    private final Table table;

    public SqlStuff(Table table) {
        this.table = table;
    }

    public String getSqlColumnDefinitionsForTable() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append(column.get_name());
            sb.append(" ");
            sb.append(column.get_type_name());
            sb.append(",");
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getSqlProcedureVariablesPrimary() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            if (column.isPrimaryKey()) {
                sb.append("IN");
                sb.append(" ");
                sb.append("v_").append(column.get_name());
                sb.append(" ");
                sb.append(column.get_type_name());
                sb.append(",");
                sb.append("\n");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getSqlProcedureVariablesAll() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("IN");
            sb.append(" ");
            sb.append("v_").append(column.get_name());
            sb.append(" ");
            sb.append(column.get_type_name());
            sb.append(",");
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getSqlProcedureVariablesAllMinusIdent() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("IN");
            sb.append(" ");
            sb.append("v_").append(column.get_name());
            sb.append(" ");
            sb.append(column.get_type_name());
            sb.append(",");
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getSqlParametersPrimary() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();

            if (column.isPrimaryKey()) {
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

    public String getSqlParametersAll() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append(column.get_name());
            sb.append(",");
            sb.append("\n");

        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getSqlWhereStuff_Primary() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            if (column.isPrimaryKey()) {
                sb.append(column.get_name());
                sb.append(" = ");
                sb.append("v_").append(column.get_name());
                sb.append("\n");
                sb.append("AND");
                sb.append("\n");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 5);
        }
        return sb.toString();
    }

    public String getSqlVariablesAll() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("v_").append(column.get_name());
            sb.append(",");
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getSqlVariablesAllSubIdent() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("v_").append(column.get_name());
            sb.append(",");
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getSqlSetStuff_NonPrimary() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            if (!column.isPrimaryKey()) {
                sb.append(column.get_name());
                sb.append(" = ");
                sb.append("v_").append(column.get_name());
                sb.append(",");
                sb.append("\n");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

}
