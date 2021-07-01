package io.github.xjrga.potatosql.generator;

import java.util.Iterator;

public class ProcedureStuff {

    private Table table;

    public ProcedureStuff(Table table) {
        this.table = table;
    }

    public String getSetParametersAllMinusIdentity() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        int count = 1;

        while (it.hasNext()) {
            Column column = (Column) it.next();

            if (!column.isIdentity()) {
                sb.append(setParam(count, column));
            } else {
                sb.append("proc.registerOutParameter(");
                sb.append(count);
                sb.append(",Types.INTEGER)");
            }

            sb.append(";");
            sb.append("\n");

            count++;
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }

    private String setParam(int count, Column column) {
        StringBuilder sb = new StringBuilder();

        switch (column.getTypeName()) {
            case "INTEGER":
            case "IDENTITY":
                sb.append("proc.setInt(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "DOUBLE":
                sb.append("proc.setDouble(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "VARCHAR":
                sb.append("proc.setString(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "BOOLEAN":
                sb.append("proc.setBoolean(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "BINARY":
                sb.append("proc.setBytes(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "DATE":
                sb.append("proc.setDate(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "TIME":
                sb.append("proc.setTime(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "TIMESTAMP":
                sb.append("proc.setTimestamp(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "DECIMAL":
                sb.append("proc.setBigDecimal(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            default:
                sb.append("proc.setObject(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
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

    public String getIdent() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        int count = 1;

        while (it.hasNext()) {
            Column column = (Column) it.next();

            if (column.isIdentity()) {
                sb.append("ident = proc.getInt(");
                sb.append(count);
                sb.append(")");
                sb.append(";");
            }

            count++;
        }

        return sb.toString();
    }

    public String getProcedureSqlStringPrimaryKeyOnly(String addName) {

        StringBuilder sqlb = new StringBuilder();
        sqlb.append("\"{CALL ");
        sqlb.append(table.getName());
        sqlb.append("_" + addName + "(");
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

    public String getProcedureSqlStringAllMinusIdent(String addName) {

        StringBuilder sqlb = new StringBuilder();
        sqlb.append("\"{CALL ");
        sqlb.append(table.getName());
        sqlb.append("_" + addName + "(");
        sqlb.append(" ");
        sqlb.append(getSqlProcedureMarksAllMinusIdent());
        sqlb.append(" ");
        sqlb.append(")}\"");
        return sqlb.toString();
    }

    public String getSqlProcedureMarksAllMinusIdent() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext()) {
            Column column = (Column) it.next();

            if (!column.isIdentity()) {
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
        sqlb.append(table.getName());
        sqlb.append("_" + addName + "(");
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
        sqlb.append(table.getName());
        sqlb.append("_" + addName + "(");
        sqlb.append(")}\"");
        return sqlb.toString();
    }
}
