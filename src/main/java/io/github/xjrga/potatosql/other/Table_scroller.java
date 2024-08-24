package io.github.xjrga.potatosql.other;

import java.awt.Rectangle;
import javax.swing.JTable;

public class Table_scroller {
  public void scroll(JTable table, Integer index) {
    table.setRowSelectionInterval(index, index);
    Rectangle rect = table.getCellRect(index, 0, true);
    table.scrollRectToVisible(rect);
  }
}
