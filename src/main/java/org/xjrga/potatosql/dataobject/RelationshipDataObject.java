package org.xjrga.potatosql.dataobject;

public class RelationshipDataObject {

    private Integer SchemaId = -1;
    private Integer Parent_TableId = -1;
    private Integer Child_TableId = -1;
    private Integer RelationshipId = -1;
    private Integer RelationshipTypeId = -1;
    private String Name;
    private String ForwardVerbPhrase;
    private String ReverseVerbPhrase;

    public RelationshipDataObject() {

    }

    public Integer getSchemaId() {
        return SchemaId;
    }

    public void setSchemaId(Integer SchemaId) {
        this.SchemaId = SchemaId;
    }

    public Integer getParent_TableId() {
        return Parent_TableId;
    }

    public void setParent_TableId(Integer Parent_TableId) {
        this.Parent_TableId = Parent_TableId;
    }

    public Integer getChild_TableId() {
        return Child_TableId;
    }

    public void setChild_TableId(Integer Child_TableId) {
        this.Child_TableId = Child_TableId;
    }

    public Integer getRelationshipId() {
        return RelationshipId;
    }

    public void setRelationshipId(Integer RelationshipId) {
        this.RelationshipId = RelationshipId;
    }

    public Integer getRelationshipTypeId() {
        return RelationshipTypeId;
    }

    public void setRelationshipTypeId(Integer RelationshipTypeId) {
        this.RelationshipTypeId = RelationshipTypeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getForwardVerbPhrase() {
        return ForwardVerbPhrase;
    }

    public void setForwardVerbPhrase(String ForwardVerbPhrase) {
        this.ForwardVerbPhrase = ForwardVerbPhrase;
    }

    public String getReverseVerbPhrase() {
        return ReverseVerbPhrase;
    }

    public void setReverseVerbPhrase(String ReverseVerbPhrase) {
        this.ReverseVerbPhrase = ReverseVerbPhrase;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SchemaId);
        sb.append(":");
        sb.append(Parent_TableId);
        sb.append(":");
        sb.append(Child_TableId);
        sb.append(":");
        sb.append(RelationshipId);
        sb.append(":");
        sb.append(RelationshipTypeId);
        sb.append(":");
        sb.append(Name);
        sb.append(":");
        sb.append(ForwardVerbPhrase);
        sb.append(":");
        sb.append(ReverseVerbPhrase);
        return sb.toString();
    }

}


