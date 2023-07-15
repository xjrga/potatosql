package io.github.xjrga.potatosql.data.object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Template_data {
    private final List<Template_relationship> relationships;
    private final Schema schema;
    private final List<Template_table> tables;
    public Template_data( Schema schema, List<Template_table> tables, List<Template_relationship> relationships ) {
        this.schema = schema;
        this.tables = tables;
        this.relationships = relationships;
    }
    public Template_data( Schema schema, List<Template_table> tables ) {
        this.schema = schema;
        this.tables = tables;
        this.relationships = new ArrayList<>();
    }
    public Template_data( List<Template_table> tables ) {
        this.schema = new Schema();
        this.tables = tables;
        this.relationships = new ArrayList<>();
    }
    public Iterator getRelationship_iterator() {
        return relationships.iterator();
    }
    public String getSchema_name() {
        return schema.getSchema_name();
    }
    public Iterator getTable_iterator() {
        return tables.iterator();
    }
    @Override
    public String toString() {
        return "Template_data{" + "schema=" + schema + ", tables=" + tables + "relationships=" + relationships + '}';
    }
}
