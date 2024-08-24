package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import io.github.xjrga.potatosql.data.object.Datatype;
import io.github.xjrga.potatosql.data.object.Key;
import io.github.xjrga.potatosql.data.object.Table;
import io.github.xjrga.potatosql.data.transfer.Datakeys_ins;
import io.github.xjrga.potatosql.data.transfer.Primarykeys_ins;
import io.github.xjrga.potatosql.data.transfer.Tablekeys_del;
import io.github.xjrga.potatosql.data.transfer.Tablekeys_ins_auto;
import io.github.xjrga.potatosql.data.transfer.Tablekeys_pop;
import io.github.xjrga.potatosql.data.transfer.Tablekeys_sel;
import io.github.xjrga.potatosql.data.transfer.Tablekeys_upd;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Table_keys_pnl extends JPanel {
  private final CellConstraints cc = new CellConstraints();
  private JTable table = new JTable();
  private final Mediator_concrete mediator;

  public Table_keys_pnl(Mediator_concrete mediator) {
    this.mediator = mediator;
    initialize();
    setup();
    FormLayout layout =
        new FormLayout(
            "min:grow", // columns
            "fill:min:grow,min" // rows
            );
    setLayout(layout);
    setBorder(new TitledBorder("Table Keys"));
    add(new JScrollPane(table), cc.xy(1, 1));
    add(get_panel_table_key_buttons(), cc.xy(1, 2));
  }

  private void initialize() {}

  private void setup() {
    mediator.register_table_key_panel(this);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.getTableHeader().setReorderingAllowed(false);
  }

  private JPanel get_panel_table_key_buttons() {
    FormLayout layout =
        new FormLayout(
            "min:grow,min,min,min,min:grow", // columns
            "min" // rows
            );
    JPanel panel = new JPanel();
    panel.setLayout(layout);
    JButton btn_add = new JButton("+");
    JButton btn_delete = new JButton("-");
    JButton btn_update = new JButton("u");
    btn_add.setToolTipText("Add table key attribute");
    btn_delete.setToolTipText("Delete table key attribute");
    btn_update.setToolTipText("Update table key attribute");
    panel.add(btn_add, cc.xy(2, 1));
    panel.add(btn_delete, cc.xy(3, 1));
    panel.add(btn_update, cc.xy(4, 1));
    btn_add.addActionListener(
        (ActionEvent e) -> {
          event_action_performed_btn_add();
        });
    btn_delete.addActionListener(
        (ActionEvent e) -> {
          event_action_performed_btn_delete();
        });
    btn_update.addActionListener(
        (ActionEvent e) -> {
          event_action_performed_btn_update();
        });
    return panel;
  }

  private void event_action_performed_btn_add() {
    if (mediator.is_table_list_selection_empty()) {
      return;
    }
    Table_key_input_add_pnl table_key_input_add_panel = new Table_key_input_add_pnl();
    JComponent[] inputs = new JComponent[] {table_key_input_add_panel};
    int i = Message.showOptionDialogOkCancel(inputs, "Table Key");
    if (i == 0) {
      Table tableDataObject = (Table) mediator.get_table_selected_value();
      Datatype datatype = (Datatype) table_key_input_add_panel.get_list_selected_value();
      Tablekeys_ins_auto insert_auto = new Tablekeys_ins_auto();
      Primarykeys_ins pinsert = new Primarykeys_ins();
      Datakeys_ins dinsert = new Datakeys_ins();
      Integer schemaid = tableDataObject.getSchema_id();
      Integer tableid = tableDataObject.getTable_id();
      String keyname = table_key_input_add_panel.get_key_name();
      Boolean keyispk = table_key_input_add_panel.is_btn_pk_yes_selected();
      Integer keytypeid = datatype.getDatatype_id();
      Integer keyorder = table_key_input_add_panel.get_key_order();
      Key key = new Key();
      key.setSchema_id(schemaid);
      key.setTable_id(tableid);
      key.setKey_name(keyname);
      key.setIs_primary_key(keyispk);
      key.setDatatype_id(keytypeid);
      key.setKey_order(keyorder);
      Integer id = insert_auto.apply(key);
      key.setKey_id(id);
      if (key.getIs_primary_key()) {
        pinsert.apply(key.getTable_key_pk());
      } else {
        dinsert.apply(key.getTable_key_pk());
      }
      reload();
      beautify();
    }
  }

  private void event_action_performed_btn_delete() {
    if (mediator.is_table_list_selection_empty()) {
      return;
    }
    Integer selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
      return;
    }
    Table o_table = (Table) mediator.get_table_selected_value();
    Tablekeys_del delete = new Tablekeys_del();
    Integer schemaid = o_table.getSchema_id();
    Integer tableid = o_table.getTable_id();
    Integer keyid = (Integer) table.getValueAt(selectedRow, 2);
    Key key = new Key();
    key.setSchema_id(schemaid);
    key.setTable_id(tableid);
    key.setKey_id(keyid);
    delete.apply(key);
    reload();
    beautify();
  }

  private void event_action_performed_btn_update() {
    if (mediator.is_table_list_selection_empty()) {
      return;
    }
    Integer selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
      return;
    }
    Table_key_input_update_pnl table_key_input_update_panel = new Table_key_input_update_pnl();
    Datatype datatype = new Datatype();
    datatype.setDatatype_id((Integer) table.getValueAt(selectedRow, 3));
    datatype.setDatatype_name((String) table.getValueAt(selectedRow, 6));
    Integer keyid = (Integer) table.getValueAt(selectedRow, 2);
    String keyname = (String) table.getValueAt(selectedRow, 4);
    Integer keyorder = (Integer) table.getValueAt(selectedRow, 7);
    table_key_input_update_panel.set_list_selected_value(datatype);
    table_key_input_update_panel.set_key_name(keyname);
    table_key_input_update_panel.set_key_order(keyorder);
    JComponent[] inputs = new JComponent[] {table_key_input_update_panel};
    int i = Message.showOptionDialogOkCancel(inputs, "Table Key");
    if (i == 0) {
      Table tableDataObject = (Table) mediator.get_table_selected_value();
      datatype = (Datatype) table_key_input_update_panel.get_list_selected_value();
      Tablekeys_upd update = new Tablekeys_upd();
      Integer schemaid = tableDataObject.getSchema_id();
      Integer tableid = tableDataObject.getTable_id();
      keyname = table_key_input_update_panel.get_key_name();
      Integer keytypeid = datatype.getDatatype_id();
      keyorder = table_key_input_update_panel.get_key_order();
      Key key = new Key();
      key.setSchema_id(schemaid);
      key.setTable_id(tableid);
      key.setKey_id(keyid);
      key.setKey_name(keyname);
      key.setDatatype_id(keytypeid);
      key.setKey_order(keyorder);
      update.apply(key);
      reload();
      beautify();
    }
  }

  public void reload() {
    Tablekeys_sel select = new Tablekeys_sel();
    Tablekeys_pop populate = new Tablekeys_pop();
    table.setModel(populate.apply(select.apply(mediator.get_table_selected_value())));
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
  }
}
