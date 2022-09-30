package io.github.xjrga.potatosql.other;

import io.github.xjrga.potatosql.data.dto.O_select_only_names;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jr
 */
public class Filter_00 {

    private List<O_select_only_names> fixed_list = null;

    public Filter_00(List<O_select_only_names> list) {
        fixed_list = new ArrayList();
        //print(list);
        System.out.println("");
        String parent = "";
        String child = "";
        int relationship_id_old = -1;
        int relationship_id = -1;
        Integer relationship_type_id = -1;
        StringBuilder parent_key_name = new StringBuilder();
        StringBuilder child_key_name = new StringBuilder();
        for (O_select_only_names o : list) {
            relationship_id = o.getRelationship_id();
            if (relationship_id_old != relationship_id) {
                if (relationship_id_old != -1) {
                    O_select_only_names o_new = new O_select_only_names();
                    o_new.setParent(parent);
                    o_new.setChild(child);
                    o_new.setRelationship_type_id(relationship_type_id);
                    o_new.setRelationship_id(relationship_id_old);
                    o_new.setParent_key_name(parent_key_name.toString());
                    o_new.setChild_key_name(child_key_name.toString());
                    fixed_list.add(o_new);
                }
                parent_key_name.setLength(0);
                child_key_name.setLength(0);
                parent_key_name.append(o.getParent_key_name());
                child_key_name.append(o.getChild_key_name());
                relationship_id_old = relationship_id;
                parent = o.getParent();
                child = o.getChild();
                relationship_type_id = o.getRelationship_type_id();
            } else {
                parent_key_name.append(", ");
                child_key_name.append(", ");
                parent_key_name.append(o.getParent_key_name());
                child_key_name.append(o.getChild_key_name());
                parent = o.getParent();
                child = o.getChild();
                relationship_type_id = o.getRelationship_type_id();
            }
        }
        O_select_only_names o_new = new O_select_only_names();
        o_new.setParent(parent);
        o_new.setChild(child);
        o_new.setRelationship_type_id(relationship_type_id);
        o_new.setRelationship_id(relationship_id_old);
        o_new.setParent_key_name(parent_key_name.toString());
        o_new.setChild_key_name(child_key_name.toString());
        fixed_list.add(o_new);
        //print(fixed_list);
    }

    private void print(List<O_select_only_names> list) {
        for (O_select_only_names o : list) {
            System.out.println(o.getParent() + ":" + o.getChild() + ":" + o.getRelationship_type_id() + ":" + o.getRelationship_id() + ":" + o.getParent_key_name() + ":" + o.getChild_key_name());
        }
    }

    public List<O_select_only_names> getFixed_list() {
        return fixed_list;
    }

}
