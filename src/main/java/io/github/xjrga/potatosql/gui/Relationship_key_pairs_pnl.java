package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import io.github.xjrga.potatosql.data.object.Keypair;
import io.github.xjrga.potatosql.data.object.Relationship;
import io.github.xjrga.potatosql.data.transfer.Datatype_chk;
import io.github.xjrga.potatosql.data.transfer.Identifying_relationship_keypair_del;
import io.github.xjrga.potatosql.data.transfer.Identifying_relationship_keypair_ins;
import io.github.xjrga.potatosql.data.transfer.Nonidentifying_relationship_keypair_del;
import io.github.xjrga.potatosql.data.transfer.Nonidentifying_relationship_keypair_ins;
import io.github.xjrga.potatosql.data.transfer.Relationship_keypair_pop;
import io.github.xjrga.potatosql.data.transfer.Relationship_keypair_sel;
import io.github.xjrga.potatosql.other.Relationship_keypair_finder;
import io.github.xjrga.potatosql.other.Table_scroller;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class Relationship_key_pairs_pnl extends JPanel {
  private final Mediator_concrete mediator;
  private final CellConstraints cc = new CellConstraints();
  public JTable table = new JTable();

  public Relationship_key_pairs_pnl(Mediator_concrete mediator) {
    this.mediator = mediator;
    initialize();
    setup();
    FormLayout layout =
        new FormLayout(
            "min:grow", // columns
            "fill:min:grow,fill:min" // rows
            );
    setLayout(layout);
    setBorder(new TitledBorder("Relationship Key Pair"));
    add(new JScrollPane(table), cc.xy(1, 1));
    add(get_relationship_keys_buttons(), cc.xy(1, 2));
    table
        .getSelectionModel()
        .addListSelectionListener(
            (ListSelectionEvent e) -> {
              if (e.getValueIsAdjusting()) {
                return;
              }
              event_table_selection();
            });
  }

  private void initialize() {}

  private void setup() {
    mediator.register_relationship_pair_keys_panel(this);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.getTableHeader().setReorderingAllowed(false);
  }

  private void event_table_selection() {
    if (is_selection_empty()) {}
  }

  private JPanel get_relationship_keys_buttons() {
    JPanel panel = new JPanel();
    JButton btn_add = new JButton();
    JButton bnt_delete = new JButton();
    btn_add.setText("+");
    bnt_delete.setText("-");
    btn_add.setToolTipText(
        "Migrate primary key attribute from parent to child. A primary key attribute is or is part of primary key.");
    bnt_delete.setToolTipText("Delete primary key attribute migration");
    panel.add(btn_add);
    panel.add(bnt_delete);
    btn_add.addActionListener(
        (ActionEvent e) -> {
          event_action_performed_btn_add();
        });
    bnt_delete.addActionListener(
        (ActionEvent e) -> {
          event_action_performed_btn_delete();
        });
    return panel;
  }

  private void event_action_performed_btn_add() {
    Relationship relationship_selected_value = mediator.get_relationship_selected_value();
    Pair_keys_link_pnl panel = new Pair_keys_link_pnl(relationship_selected_value);
    JComponent[] inputs = new JComponent[] {panel};
    int i = Message.showOptionDialogOkCancel(inputs, "Relationship Key Pair");
    if (i == 0) {
      if (panel.get_parent_table_key_selected_value() == null) {
        return;
      }
      if (panel.get_child_table_key_selected_value() == null) {
        return;
      }
      Datatype_chk datatype_check = new Datatype_chk();
      Keypair key_pair = new Keypair();
      key_pair.setSchema_id(relationship_selected_value.getSchema_id());
      key_pair.setRelationship_id(relationship_selected_value.getRelationship_id());
      key_pair.setTable_id_parent(relationship_selected_value.getTable_id_parent());
      key_pair.setKey_id_parent(panel.get_parent_table_key_selected_value().getKey_id());
      key_pair.setTable_id_child(relationship_selected_value.getTable_id_child());
      key_pair.setKey_id_child(panel.get_child_table_key_selected_value().getKey_id());
      if (!datatype_check.apply(key_pair)) {
        Message.showMessage("Referencing key must be of same data type.");
        return;
      }
      if (relationship_selected_value.getIs_identifying()) {
        Identifying_relationship_keypair_ins identifying_insert =
            new Identifying_relationship_keypair_ins();
        identifying_insert.apply(key_pair);
      } else {
        Nonidentifying_relationship_keypair_ins nonidentifying_insert =
            new Nonidentifying_relationship_keypair_ins();
        nonidentifying_insert.apply(key_pair);
      }
      reload();
      beautify();
      Relationship_keypair_finder find = new Relationship_keypair_finder();
      Integer index = find.find(table.getModel(), key_pair);
      Table_scroller scroller = new Table_scroller();
      scroller.scroll(table, index);
    }
  }

  private void event_action_performed_btn_delete() {
    if (is_selection_empty()) {
      return;
    }
    Relationship relationship_selected_value = mediator.get_relationship_selected_value();
    if (relationship_selected_value.getIs_identifying()) {
      Identifying_relationship_keypair_del delete = new Identifying_relationship_keypair_del();
      delete.apply(get_relationship_key_pair_selected_value());
    } else {
      Nonidentifying_relationship_keypair_del delete =
          new Nonidentifying_relationship_keypair_del();
      delete.apply(get_relationship_key_pair_selected_value());
    }
    reload();
    beautify();
  }

  public Keypair get_relationship_key_pair_selected_value() {
    int selectedRow = table.getSelectedRow();
    Integer schemaid = (Integer) table.getValueAt(selectedRow, 0);
    Integer relationshipid = (Integer) table.getValueAt(selectedRow, 1);
    Integer table_id_parent = (Integer) table.getValueAt(selectedRow, 2);
    Integer key_id_parent = (Integer) table.getValueAt(selectedRow, 3);
    Integer table_id_child = (Integer) table.getValueAt(selectedRow, 4);
    Integer key_id_child = (Integer) table.getValueAt(selectedRow, 5);
    Keypair o = new Keypair();
    o.setSchema_id(schemaid);
    o.setRelationship_id(relationshipid);
    o.setTable_id_parent(table_id_parent);
    o.setKey_id_parent(key_id_parent);
    o.setTable_id_child(table_id_child);
    o.setKey_id_child(key_id_child);
    return o;
  }

  private Boolean is_selection_empty() {
    return table.getSelectedRow() == -1;
  }

  public void reload() {
    Relationship relationship_selected_value = mediator.get_relationship_selected_value();
    Relationship_keypair_sel select = new Relationship_keypair_sel();
    Relationship_keypair_pop populate = new Relationship_keypair_pop();
    table.setModel(populate.apply(select.apply(relationship_selected_value)));
  }

  public void clear() {
    table.setModel(new DefaultTableModel());
  }

  public void beautify() {
    table.getColumnModel().getColumn(0).setMinWidth(0);
    table.getColumnModel().getColumn(0).setMaxWidth(0);
    table.getColumnModel().getColumn(1).setMinWidth(0);
    table.getColumnModel().getColumn(1).setMaxWidth(0);
    table.getColumnModel().getColumn(2).setMinWidth(0);
    table.getColumnModel().getColumn(2).setMaxWidth(0);
    table.getColumnModel().getColumn(3).setMinWidth(0);
    table.getColumnModel().getColumn(3).setMaxWidth(0);
    table.getColumnModel().getColumn(4).setMinWidth(0);
    table.getColumnModel().getColumn(4).setMaxWidth(0);
    table.getColumnModel().getColumn(5).setMinWidth(0);
    table.getColumnModel().getColumn(5).setMaxWidth(0);
  }
}
