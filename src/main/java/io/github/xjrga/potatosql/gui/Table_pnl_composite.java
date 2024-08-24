package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import javax.swing.JPanel;

public class Table_pnl_composite extends JPanel {
  private final Mediator_concrete mediator;
  private final CellConstraints cc = new CellConstraints();

  public Table_pnl_composite(Mediator_concrete mediator) {
    this.mediator = mediator;
    initialize();
    setup();
    FormLayout layout =
        new FormLayout(
            "min,min:grow", // columns
            "fill:min:grow" // rows
            );
    setLayout(layout);
    add(mediator.get_table_panel(), cc.xy(1, 1));
    add(mediator.get_table_key_panel(), cc.xy(2, 1));
  }

  private void initialize() {}

  private void setup() {
    mediator.register_table_panel_assemble_panel(this);
  }
}
