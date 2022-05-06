<#list relationships as relationship>
ALTER TABLE ${relationship.child} ADD CONSTRAINT R${relationship?index}_${relationship.child}_${relationship.parent} FOREIGN KEY (${relationship.child_key_name}) REFERENCES ${relationship.parent} (${relationship.parent_key_name})<#if relationship.relationship_type_id == 0> ON DELETE CASCADE;<#else> ON DELETE SET NULL;</#if>
/
</#list>