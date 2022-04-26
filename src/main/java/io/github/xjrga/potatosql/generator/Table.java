package io.github.xjrga.potatosql.generator;

import java.util.Iterator;
import java.util.LinkedList;

public class Table {

    private final String name;
    private final LinkedList columns;
    private int keyCount;
    private int primaryKeyCount;
    private int nonPrimaryKeyCount;
    private String schema_name;

    public Table(String name) {

        this.name = name;
        columns = new LinkedList();
    }

    public void addColumn(Column column) {

        columns.add(column);
    }

    public Column getColumn(int position) {

        return (Column) columns.get(position);
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" ");

        LinkedList list = columns;
        Iterator it = list.iterator();

        while (it.hasNext()) {

            Column column = (Column) it.next();
            sb.append(",");
            sb.append(column.get_name());
        }

        return sb.toString();
    }

    public Integer getLargestColumnSize() {

        Integer largestColumnNameSize = 0;
        Iterator it = columns.listIterator();

        while (it.hasNext()) {

            Column column = (Column) it.next();
            Integer columnNameSize = column.get_name().length();

            if (columnNameSize > largestColumnNameSize) {
                largestColumnNameSize = columnNameSize;
            }

        }

        return largestColumnNameSize;
    }

    public Boolean isEmpty() {

        Boolean flag = false;

        if (getCountKey() == 0) {
            flag = true;
        }

        return flag;
    }

    public int getCountKey() {

        keyCount = columns.size();
        return keyCount;
    }

    public Boolean containsNonPrimaryKeys() {

        Boolean flag = false;

        if (getCountNonPrimaryKey() > 0) {
            flag = true;
        }

        return flag;
    }

    private int getCountNonPrimaryKey() {

        nonPrimaryKeyCount = 0;
        Iterator it = columns.iterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            if (!column.isPrimaryKey()) {
                nonPrimaryKeyCount++;
            }
        }
        return nonPrimaryKeyCount;
    }

    public Iterator getIterator() {

        return columns.iterator();
    }

    public boolean containsPrimaryKeys() {
        Boolean flag = false;

        if (getCountPrimaryKey() > 0) {
            flag = true;
        }

        return flag;
    }

    private int getCountPrimaryKey() {

        primaryKeyCount = 0;
        Iterator it = columns.iterator();
        while (it.hasNext()) {
            Column column = (Column) it.next();
            if (column.isPrimaryKey()) {
                primaryKeyCount++;
            }
        }
        return primaryKeyCount;
    }

    public String getSchema_name() {
        return schema_name;
    }

    public void setSchema_name(String schema_name) {
        this.schema_name = schema_name;
    }
}
