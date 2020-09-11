package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.data.DbLink;
import org.xjrga.potatosql.dataobject.DatabaseSchemaDataObject;
import org.xjrga.potatosql.dataobject.TableKeyRelationshipKeyPairDataObject;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CreateRelationship implements Code {
    private final StringBuilder sqlbuild;

    public CreateRelationship(DbLink dbLink, DatabaseSchemaDataObject databaseSchemaDataObject) {
        sqlbuild = new StringBuilder();
        RelationshipConstraint relationshipConstraint = new RelationshipConstraint();
        List<TableKeyRelationshipKeyPairDataObject> list = null;
        try {
            list = dbLink.Relationship_SelectOnlyNames(databaseSchemaDataObject);
            Iterator it = list.iterator();
            int count = 0;
            int relationshipid_old = -1;
            while (it.hasNext()) {
                TableKeyRelationshipKeyPairDataObject next = (TableKeyRelationshipKeyPairDataObject) it.next();
                if (next.getRelationshipId() != relationshipid_old) {
                    if (!relationshipConstraint.isEmpty()) {
                        sqlbuild.append(relationshipConstraint.getCode());
                        sqlbuild.append("\n");
                    }
                    relationshipConstraint = new RelationshipConstraint();
                    relationshipConstraint.setSchema(databaseSchemaDataObject.getName());
                    relationshipConstraint.setParent(next.getParentName());
                    relationshipConstraint.setChild(next.getChildName());
                    relationshipConstraint.setRelationshipType(next.getRelationshipId());
                    relationshipConstraint.setCount(count++);
                }
                relationshipConstraint.addParentKey(next.getParentKeyName());
                relationshipConstraint.addChildKey(next.getChildKeyName());
                relationshipid_old = next.getRelationshipId();
            }
            if (count > 0) {
                sqlbuild.append(relationshipConstraint.getCode());
                sqlbuild.append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getCode() {
        return sqlbuild.toString();
    }
}
