package org.xjrga.potatosql.dataobject;

public class TableKeyRelationshipKeyPairDataObject {

    private Integer SchemaId = -1;
    private Integer Parent_TableId = -1;
    private Integer Child_TableId = -1;
    private Integer RelationshipTypeId = -1;
    private Integer RelationshipId = -1;
    private Integer Parent_KeyId = -1;
    private Integer Child_KeyId = -1;
    private String ParentName;
    private String ChildName;
    private String ParentKeyName;
    private String ChildKeyName;

    public TableKeyRelationshipKeyPairDataObject() {
    }

    public Integer getSchemaId() {
        return SchemaId;
    }

    public void setSchemaId(Integer schemaId) {
        SchemaId = schemaId;
    }

    public Integer getParent_TableId() {
        return Parent_TableId;
    }

    public void setParent_TableId(Integer parent_TableId) {
        Parent_TableId = parent_TableId;
    }

    public Integer getChild_TableId() {
        return Child_TableId;
    }

    public void setChild_TableId(Integer child_TableId) {
        Child_TableId = child_TableId;
    }

    public Integer getRelationshipTypeId() {
        return RelationshipTypeId;
    }

    public void setRelationshipTypeId(Integer relationshipTypeId) {
        RelationshipTypeId = relationshipTypeId;
    }

    public Integer getRelationshipId() {
        return RelationshipId;
    }

    public void setRelationshipId(Integer relationshipId) {
        RelationshipId = relationshipId;
    }

    public Integer getParent_KeyId() {
        return Parent_KeyId;
    }

    public void setParent_KeyId(Integer parent_KeyId) {
        Parent_KeyId = parent_KeyId;
    }

    public Integer getChild_KeyId() {
        return Child_KeyId;
    }

    public void setChild_KeyId(Integer child_KeyId) {
        Child_KeyId = child_KeyId;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public String getChildName() {
        return ChildName;
    }

    public void setChildName(String childName) {
        ChildName = childName;
    }

    public String getParentKeyName() {
        return ParentKeyName;
    }

    public void setParentKeyName(String parentKeyName) {
        ParentKeyName = parentKeyName;
    }

    public String getChildKeyName() {
        return ChildKeyName;
    }

    public void setChildKeyName(String childKeyName) {
        ChildKeyName = childKeyName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ParentName);
        sb.append(":");
        sb.append(ChildName);
        return sb.toString();
    }
}
