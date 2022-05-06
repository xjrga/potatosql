package io.github.xjrga.potatosql.generator;

import io.github.xjrga.potatosql.data.Dblink;
import io.github.xjrga.potatosql.data.dto.O_key_with_name;
import io.github.xjrga.potatosql.data.dto.O_schema;
import io.github.xjrga.potatosql.data.dto.O_table;
import java.util.Iterator;
import java.util.List;

public class TableMaker {

    private final Dblink dblink;
    private Table table;

    public TableMaker(Dblink dbLink) {
        this.dblink = dbLink;
    }

    public Table get_table(O_schema schema, Integer tableid) {
        O_table table_t = new O_table();
        table_t.setSchema_id(schema.getSchema_id());
        table_t.setTable_id(tableid);
        List<O_table> list_t = (List<O_table>) dblink.table_select_02(table_t);
        Iterator<O_table> it = list_t.iterator();
        O_table row = it.next();
        table = new Table((String) row.getTable_name());
        table.setSchema_name(schema.getSchema_name());
        O_key_with_name kwn = new O_key_with_name();
        kwn.setSchema_id(schema.getSchema_id());
        kwn.setTable_id(tableid);
        List<O_key_with_name> list = dblink.key_type_select(kwn);
        for (O_key_with_name next : list) {
            String name = next.getTable_key_name();
            String label = next.getTable_key_label();
            Boolean ispk = next.getTable_key_is_pk();
            Integer typeid = next.getTable_key_type_id();
            String typename = next.getKey_type_name();
            Integer order = next.getTable_key_order();
            Column column = new Column(name);
            column.setName(name);
            column.setLabel(label);
            column.setPrimaryKey(ispk);
            column.setTypeid(typeid);
            column.setTypename(typename);
            column.setOrder(order);
            table.addColumn(column);
        }
        return table;
    }

}
