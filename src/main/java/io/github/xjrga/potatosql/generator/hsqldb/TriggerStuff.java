package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Column;
import io.github.xjrga.potatosql.generator.Table;
import java.util.Iterator;

public class TriggerStuff {

    private final Table table;

    public TriggerStuff(Table table) {
        this.table = table;
    }

    public String getTriggerNewRowFields() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            sb.append("newrow.");
            sb.append(column.get_name());
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public String getTriggerSetNewRowNPKVariables() {
        StringBuilder sb = new StringBuilder();
        sb.append("SET");
        sb.append("\n");
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            if (!column.isPrimaryKey()) {
                sb.append("row.");
                sb.append(column.get_name());
                sb.append(" = ");
                sb.append("row.");
                sb.append(column.get_name());
                sb.append(",");
                sb.append("\n");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(";");
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
                sb.append("row.").append(column.get_name());
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

    public String getSqlSetStuff_NonPrimary() {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            if (!column.isPrimaryKey()) {
                sb.append(column.get_name());
                sb.append(" = ");
                sb.append("row.").append(column.get_name());
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
