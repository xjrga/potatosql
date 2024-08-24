package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import io.github.xjrga.potatosql.data.object.Datatype;
import io.github.xjrga.potatosql.data.transfer.Datatype_pop;
import io.github.xjrga.potatosql.data.transfer.Datatype_sel;
import java.awt.Dimension;
import java.awt.Label;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

public class Table_key_input_add_pnl extends JPanel {
  private final CellConstraints cc = new CellConstraints();
  private final JTextField txt_key_order = new JTextField();
  private final JTextField txt_key_name = new JTextField();
  private final JRadioButton btn_pk_no = new JRadioButton();
  private final JRadioButton btn_pk_yes = new JRadioButton();
  private final JList list = new JList();
  private JScrollPane scrollpane;

  public Table_key_input_add_pnl() {
    initialize();
    setup();
    FormLayout layout =
        new FormLayout(
            "min,min:grow", // columns
            "min,4px,min,min,min" // rows
            );
    Label lbl_name = new Label("Name: ");
    Label lbl_type = new Label("Type: ");
    Label lbl_is_pk = new Label("Primary Key? ");
    Label lbl_order = new Label("Order: ");
    ButtonGroup btngroup = new ButtonGroup();
    setLayout(layout);
    setBorder(new TitledBorder("Definition"));
    lbl_name.setAlignment(Label.RIGHT);
    lbl_type.setAlignment(Label.RIGHT);
    lbl_is_pk.setAlignment(Label.RIGHT);
    lbl_order.setAlignment(Label.RIGHT);
    btngroup.add(btn_pk_yes);
    btngroup.add(btn_pk_no);
    add(lbl_name, cc.xy(1, 1));
    add(txt_key_name, cc.xy(2, 1));
    add(lbl_type, cc.xy(1, 3));
    add(scrollpane, cc.xy(2, 3));
    add(lbl_is_pk, cc.xy(1, 4));
    add(get_pk_panel(), cc.xy(2, 4));
    add(lbl_order, cc.xy(1, 5));
    add(txt_key_order, cc.xy(2, 5));
  }

  private JPanel get_pk_panel() {
    JPanel panel = new JPanel();
    panel.add(btn_pk_yes);
    panel.add(btn_pk_no);
    return panel;
  }

  private void initialize() {
    scrollpane = new JScrollPane(list);
  }

  private void setup() {
    setPreferredSize(new Dimension(600, 400));
    Datatype_sel select = new Datatype_sel();
    Datatype_pop populate = new Datatype_pop();
    list.setModel(populate.apply(select.apply()));
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    btn_pk_yes.setText("Yes");
    btn_pk_no.setText("No");
    btn_pk_yes.setSelected(false);
    btn_pk_no.setSelected(true);
    txt_key_name.setMinimumSize(new Dimension(100, 26));
    txt_key_order.setMinimumSize(new Dimension(100, 26));
    scrollpane.setMinimumSize(new Dimension(0, 270));
  }

  public Datatype get_list_selected_value() {
    return (Datatype) list.getSelectedValue();
  }

  public Integer get_key_order() {
    return Integer.valueOf(txt_key_order.getText());
  }

  public String get_key_name() {
    return txt_key_name.getText();
  }

  public Boolean is_btn_pk_yes_selected() {
    return btn_pk_yes.isSelected();
  }
}
