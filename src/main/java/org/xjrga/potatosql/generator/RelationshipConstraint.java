package org.xjrga.potatosql.generator;

import java.util.Iterator;
import java.util.LinkedList;

public class RelationshipConstraint implements Code {
    private final LinkedList<String> parentkeys;
    private final LinkedList<String> childkeys;
    private String schema;
    private String parent;
    private String child;
    private Integer count;
    private int relationshiptypeid;

    public RelationshipConstraint() {
        parent = "";
        parentkeys = new LinkedList();
        child = "";
        childkeys = new LinkedList();
        relationshiptypeid = 0;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public void addParentKey(String parent_key) {
        parentkeys.add(parent_key);
    }

    public void addChildKey(String child_key) {
        childkeys.add(child_key);
    }

    public Boolean isEmpty() {
        Boolean flag = false;
        if (parent == "") {
            flag = true;
        }
        return flag;
    }

    @Override
    public String getCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE ");
        sb.append(getSchema());
        sb.append(".");
        sb.append(child);
        sb.append(" ");
        sb.append("ADD ");
        sb.append("CONSTRAINT");
        sb.append(" ");
        sb.append("R");
        sb.append(getCount());
        sb.append("_");
        sb.append(parent);
        sb.append("_");
        sb.append(child);
        sb.append(" ");
        sb.append("FOREIGN KEY ( ");
        Iterator it = childkeys.iterator();
        while (it.hasNext()) {
            String childkey = (String) it.next();
            sb.append(childkey);
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(" ) ");
        sb.append("REFERENCES ");
        sb.append(getSchema());
        sb.append(".");
        sb.append(parent);
        sb.append(" ( ");
        Iterator it2 = parentkeys.iterator();
        while (it2.hasNext()) {
            String parentkey = (String) it2.next();
            sb.append(parentkey);
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(" ) ");
        sb.append(getAction());
        sb.append("\n");
        return sb.toString();
/* *
            ALTER TABLE Child ADD CONSTRAINT Child_Relationship_1
            FOREIGN KEY (Child_TableId, Child_SchemaId)
            REFERENCES DatabaseTable (Parent_TableId, Parent_SchemaId)
            ON DELETE CASCADE;
            ON DELETE SET NULL;
            * */
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getAction() {
        StringBuilder sb = new StringBuilder();
        switch (relationshiptypeid) {
            case 0:
                sb.append("ON DELETE CASCADE;");
                break;
            case 1:
                sb.append("ON DELETE SET NULL;");
                break;
        }
        return sb.toString();
    }

    public void setRelationshipType(Integer relationshiptypeid) {
        this.relationshiptypeid = relationshiptypeid;
    }
}
