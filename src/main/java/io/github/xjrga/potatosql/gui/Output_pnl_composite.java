package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import io.github.xjrga.potatosql.data.object.Template_data;
import io.github.xjrga.potatosql.data.object.Template_relationship;
import io.github.xjrga.potatosql.data.object.Template_table;
import io.github.xjrga.potatosql.data.transfer.Template_key_sel;
import io.github.xjrga.potatosql.data.transfer.Template_relationship_sel;
import io.github.xjrga.potatosql.data.transfer.Template_table_sel;
import io.github.xjrga.potatosql.generator.dot.Coder;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Output_pnl_composite extends JPanel {
  private final Mediator_concrete mediator;
  private JTextArea txt_output;
  public CellConstraints cc = new CellConstraints();

  public Output_pnl_composite(Mediator_concrete mediator) {
    this.mediator = mediator;
    initialize();
    setup();
    FormLayout layout =
        new FormLayout(
            "min:grow,min,min,min", // columns
            "fill:min:grow,min" // rows
            );
    JButton btn_generate = new JButton("Generate");
    JButton btn_copy = new JButton("  Copy  ");
    JButton btn_clear = new JButton("  Clear ");
    btn_generate.setToolTipText("Generate code");
    btn_copy.setToolTipText("Copy to clipboard");
    btn_clear.setToolTipText("Clear text area");
    setLayout(layout);
    setBorder(new TitledBorder("Output"));
    add(new JScrollPane(txt_output), cc.xyw(1, 1, 4));
    add(btn_generate, cc.xy(2, 2));
    add(btn_clear, cc.xy(3, 2));
    add(btn_copy, cc.xy(4, 2));
    btn_generate.addActionListener(
        e -> {
          event_action_performed_btn_generate();
        });
    btn_copy.addActionListener(
        (ActionEvent e) -> {
          StringSelection stringSelection = new StringSelection(txt_output.getText());
          Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
          clipboard.setContents(stringSelection, null);
        });
    btn_clear.addActionListener(
        e -> {
          txt_output.setText("");
        });
  }

  private void initialize() {
    txt_output = new JTextArea();
  }

  private void setup() {
    mediator.register_output_panel(this);
  }

  private void event_action_performed_btn_generate() {
    if (mediator.is_schema_list_selection_empty()) {
      return;
    }
    StringBuilder sb = new StringBuilder();
    Template_data data =
        new Template_data(
            mediator.get_schema_selected_value(),
            get_template_tables(),
            get_template_relationships());
    Coder coder = new Coder();
    mediator
        .get_code_list_stream()
        .forEach((x) -> sb.append(coder.apply(new File(x.getPath()), data)));
    txt_output.setText(sb.toString());
  }

  public List<Template_table> get_template_tables() {
    Template_table_sel table_selector = new Template_table_sel();
    Template_key_sel key_selector = new Template_key_sel();
    return table_selector.apply(mediator.get_schema_selected_value()).stream()
        .map((t_in) -> (key_selector.apply(mediator.get_schema_selected_value(), t_in)))
        .collect(Collectors.toList());
  }

  public List<Template_relationship> get_template_relationships() {
    Template_relationship_sel relationship_selector = new Template_relationship_sel();
    return relationship_selector.apply(mediator.get_schema_selected_value());
  }
}
