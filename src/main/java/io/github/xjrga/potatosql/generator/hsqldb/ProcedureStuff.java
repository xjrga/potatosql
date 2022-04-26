package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Column;
import io.github.xjrga.potatosql.generator.Table;
import io.github.xjrga.potatosql.other.Utilities;
import java.util.Iterator;

public class ProcedureStuff {

    private final Table table;

    public ProcedureStuff(Table table) {
        this.table = table;
    }

    private String setParam(int count, Column column) {
        StringBuilder sb = new StringBuilder();
        switch (column.get_type_name()) {
            case "INTEGER":
                sb.append("proc.setInt(");
                sb.append(count);
                sb.append(",");
                sb.append(table.getName()).append(".get").append(Utilities.capitalize(column.get_name())).append("()");
                sb.append(")");
                break;
            case "DOUBLE":
                sb.append("proc.setDouble(");
                sb.append(count);
                sb.append(",");
                sb.append(table.getName()).append(".get").append(Utilities.capitalize(column.get_name())).append("()");
                sb.append(")");
                break;
            case "LONGVARCHAR":
                sb.append("proc.setString(");
                sb.append(count);
                sb.append(",");
                sb.append(table.getName()).append(".get").append(Utilities.capitalize(column.get_name())).append("()");
                sb.append(")");
                break;
            case "BOOLEAN":
                sb.append("proc.setBoolean(");
                sb.append(count);
                sb.append(",");
                sb.append(table.getName()).append(".get").append(Utilities.capitalize(column.get_name())).append("()");
                sb.append(")");
                break;
            case "BINARY":
                sb.append("proc.setBytes(");
                sb.append(count);
                sb.append(",");
                sb.append(table.getName()).append(".get").append(Utilities.capitalize(column.get_name())).append("()");
                sb.append(")");
                break;
            case "DATE":
                sb.append("proc.setDate(");
                sb.append(count);
                sb.append(",");
                sb.append(table.getName()).append(".get").append(Utilities.capitalize(column.get_name())).append("()");
                sb.append(")");
                break;
            case "TIME":
                sb.append("proc.setTime(");
                sb.append(count);
                sb.append(",");
                sb.append(table.getName()).append(".get").append(Utilities.capitalize(column.get_name())).append("()");
                sb.append(")");
                break;
            case "TIMESTAMP":
                sb.append("proc.setTimestamp(");
                sb.append(count);
                sb.append(",");
                sb.append(table.getName()).append(".get").append(Utilities.capitalize(column.get_name())).append("()");
                sb.append(")");
                break;
            case "DECIMAL":
                sb.append("proc.setBigDecimal(");
                sb.append(count);
                sb.append(",");
                sb.append(table.getName()).append(".get").append(Utilities.capitalize(column.get_name())).append("()");
                sb.append(")");
                break;
            default:
                sb.append("proc.setObject(");
                sb.append(count);
                sb.append(",");
                sb.append(table.getName()).append(".get").append(Utilities.capitalize(column.get_name())).append("()");
                sb.append(")");
        }
        return sb.toString();
    }

    public String getSetParametersPrimaryKeyOnly() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        int count = 1;
        while (it.hasNext()) {
            Column column = (Column) it.next();
            if (column.isPrimaryKey()) {
                sb.append(setParam(count, column));
                sb.append(";");
                sb.append("\n");
            }
            count++;
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public String getSetParametersAll() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        int count = 1;
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append(setParam(count, column));
            sb.append(";");
            sb.append("\n");
            count++;
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public String getProcedureSqlStringPrimaryKeyOnly(String addName) {
        StringBuilder sqlb = new StringBuilder();
        sqlb.append("\"{CALL ");
        sqlb.append(table.getSchema_name());
        sqlb.append(".");
        sqlb.append(table.getName());
        sqlb.append("_").append(addName).append("(");
        sqlb.append(" ");
        sqlb.append(getSqlProcedureMarksPrimary());
        sqlb.append(" ");
        sqlb.append(")}\"");
        return sqlb.toString();
    }

    public String getSqlProcedureMarksPrimary() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();

            if (column.isPrimaryKey()) {
                sb.append("?");
                sb.append(",");
                sb.append(" ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getProcedureSqlStringAll(String addName) {
        StringBuilder sqlb = new StringBuilder();
        sqlb.append("\"{CALL ");
        sqlb.append(table.getSchema_name());
        sqlb.append(".");
        sqlb.append(table.getName());
        sqlb.append("_").append(addName).append("(");
        sqlb.append(" ");
        sqlb.append(getSqlProcedureMarksAll());
        sqlb.append(" ");
        sqlb.append(")}\"");
        return sqlb.toString();
    }

    public String getSqlProcedureMarksAll() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("?");
            sb.append(",");
            sb.append(" ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getProcedureSqlStringNone(String addName) {
        StringBuilder sqlb = new StringBuilder();
        sqlb.append("\"{CALL ");
        sqlb.append(table.getSchema_name());
        sqlb.append(".");
        sqlb.append(table.getName());
        sqlb.append("_").append(addName).append("(");
        sqlb.append(")}\"");
        return sqlb.toString();
    }
}
