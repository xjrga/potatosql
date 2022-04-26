package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.data.Dblink;
import io.github.xjrga.potatosql.data.dto.O_schema;
import io.github.xjrga.potatosql.data.dto.O_select_only_names;
import io.github.xjrga.potatosql.generator.Code;
import java.util.Iterator;
import java.util.List;

public class CreateRelationship implements Code {

    private final StringBuilder sqlbuild;

    public CreateRelationship(Dblink dbLink, Integer schemaid) {
        sqlbuild = new StringBuilder();
        RelationshipConstraint relationshipConstraint = new RelationshipConstraint();
        O_schema schema = new O_schema();
        schema.setSchema_id(schemaid);
        List<O_select_only_names> list = (List<O_select_only_names>) dbLink.relationship_select_only_names(schema);
        Iterator<O_select_only_names> it = list.iterator();
        int count = 0;
        int relationshipid_old = -1;
        while (it.hasNext()) {
            O_select_only_names row = it.next();
            String parent = row.getParent();
            String child = row.getChild();
            Integer relationshipid = row.getRelationship_id();
            Integer relationshiptypeid = row.getRelationship_type_id();
            String parent_key = row.getParent_key_name();
            String child_key = row.getChild_key_name();
            if (relationshipid != relationshipid_old) {
                if (!relationshipConstraint.isEmpty()) {
                    sqlbuild.append(relationshipConstraint.get_code());
                    sqlbuild.append("/\n");
                }
                relationshipConstraint = new RelationshipConstraint();
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
            sqlbuild.append(relationshipConstraint.get_code());
            sqlbuild.append("/\n\n");
        }
    }

    @Override
    public String get_code() {
        return sqlbuild.toString();
    }

}
