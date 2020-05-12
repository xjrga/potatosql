package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.data.DbLink;
import org.xjrga.potatosql.dataobject.SchemaDataObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class CreateRelationship implements Code {

    private final StringBuilder sqlbuild;

    public CreateRelationship(DbLink dbLink, SchemaDataObject schemaDataObject) {
        sqlbuild = new StringBuilder();
        RelationshipConstraint relationshipConstraint = new RelationshipConstraint();
        LinkedList list = (LinkedList) dbLink.Relationship_SelectOnlyNames(schemaDataObject.getSchemaId());
        Iterator it = list.iterator();
        int count = 0;
        int relationshipid_old = -1;

        while (it.hasNext()) {
            HashMap row_hashmap = (HashMap) it.next();
            String parent = (String) row_hashmap.get("PARENT");
            String child = (String) row_hashmap.get("CHILD");
            Integer relationshipid = (Integer) row_hashmap.get("RELATIONSHIPID");
            Integer relationshiptypeid = (Integer) row_hashmap.get("RELATIONSHIPTYPEID");
            String parent_key = (String) row_hashmap.get("PARENT_KEY");
            String child_key = (String) row_hashmap.get("CHILD_KEY");

            if (relationshipid != relationshipid_old) {
                if (!relationshipConstraint.isEmpty()) {
                    sqlbuild.append(relationshipConstraint.getCode());
                    sqlbuild.append("\n");
                }
                relationshipConstraint = new RelationshipConstraint();
                relationshipConstraint.setSchema(schemaDataObject.getSchemaName());
                relationshipConstraint.setParent(parent);
                relationshipConstraint.setChild(child);
                relationshipConstraint.setRelationshipType(relationshiptypeid);
                relationshipConstraint.setCount(count++);
            }

            relationshipConstraint.addParentKey(parent_key);
            relationshipConstraint.addChildKey(child_key);
            relationshipid_old = relationshipid;
        }

        if (count > 0) {
            sqlbuild.append(relationshipConstraint.getCode());
            sqlbuild.append("\n");
        }
    }


    @Override
    public String getCode() {
        return sqlbuild.toString();
    }

}
