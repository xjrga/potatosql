<#list data.relationship_iterator as relationship>
ALTER TABLE ${relationship.child} ADD CONSTRAINT R${relationship?index}_${relationship.child} FOREIGN KEY (${relationship.child_key}) REFERENCES ${relationship.parent} (${relationship.parent_key})<#if relationship.is_identifying> ON DELETE CASCADE;<#else> ON DELETE SET NULL;</#if>
/
</#list>
