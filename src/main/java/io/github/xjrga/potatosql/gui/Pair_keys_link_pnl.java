package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import io.github.xjrga.potatosql.data.object.Key;
import io.github.xjrga.potatosql.data.object.Relationship;
import io.github.xjrga.potatosql.data.transfer.Table_child_key_sel;
import io.github.xjrga.potatosql.data.transfer.Table_key_pop;
import io.github.xjrga.potatosql.data.transfer.Table_parent_key_sel;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

public class Pair_keys_link_pnl extends JPanel {
  private final CellConstraints cc = new CellConstraints();
  private final JList<Key> lst_child = new JList();
  private final JList<Key> lst_parent = new JList();
  private final Relationship relationship;
  public final JPanel pnl_child = new JPanel();

  public Pair_keys_link_pnl(Relationship relationship) {
    this.relationship = relationship;
    initialize();
    setup();
    FormLayout layout =
        new FormLayout(
            "min:grow,min:grow", // columns
            "fill:min:grow,min" // rows
            );
    setLayout(layout);
    if (relationship.getIs_identifying()) {
      setBorder(new TitledBorder("Identifying Relationship"));
      pnl_child.setBorder(new TitledBorder("Child Table: Primary Key"));
    } else {
      setBorder(new TitledBorder("Nonidentifying Relationship"));
      pnl_child.setBorder(new TitledBorder("Child Table: Data Key"));
    }
    add(get_panel_parent(), cc.xy(1, 1));
    add(get_panel_child(), cc.xy(2, 1));
  }

  private void initialize() {}

  private void setup() {
    setPreferredSize(new Dimension(600, 400));
    Table_parent_key_sel select_parent_keys = new Table_parent_key_sel();
    Table_child_key_sel select_child_keys = new Table_child_key_sel();
    Table_key_pop populate = new Table_key_pop();
    lst_parent.setModel(populate.apply(select_parent_keys.apply(relationship)));
    lst_child.setModel(populate.apply(select_child_keys.apply(relationship)));
    lst_parent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    lst_child.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }

  private JPanel get_panel_parent() {
    FormLayout layout =
        new FormLayout(
            "min:grow", // columns
            "fill:min:grow" // rows
            );
    JPanel panel = new JPanel();
    panel.setLayout(layout);
    panel.setBorder(new TitledBorder("Parent Table: Primary Key"));
    panel.add(new JScrollPane(lst_parent), cc.xy(1, 1));
    return panel;
  }

  private JPanel get_panel_child() {
    FormLayout layout =
        new FormLayout(
            "min:grow", // columns
            "fill:min:grow" // rows
            );
    pnl_child.setLayout(layout);
    pnl_child.add(new JScrollPane(lst_child), cc.xy(1, 1));
    return pnl_child;
  }

  Key get_parent_table_key_selected_value() {
    return lst_parent.getSelectedValue();
  }

  Key get_child_table_key_selected_value() {
    return lst_child.getSelectedValue();
  }
}
