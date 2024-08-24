package io.github.xjrga.potatosql.gui;

import io.github.xjrga.potatosql.other.ImageUtilities;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Message {
  private static final BufferedImage logo = ImageUtilities.readImage("resources/logo.png");

  public Message() {}

  public static void showMessage(String message) {
    JOptionPane optionPane =
        new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
    JDialog dialog = optionPane.createDialog("PotatoSql");
    dialog.setIconImage(logo);
    dialog.setVisible(true);
  }

  public static void showMessage(String title, String message) {
    JOptionPane optionPane =
        new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
    JDialog dialog = optionPane.createDialog(title);
    dialog.setIconImage(logo);
    dialog.setVisible(true);
  }

  public static int showOptionDialog(JComponent[] inputs, String title) {
    JOptionPane optionPane =
        new JOptionPane(inputs, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
    JDialog dialog = optionPane.createDialog(title);
    dialog.setIconImage(logo);
    dialog.setVisible(true);
    // 0 - Ok
    // 2 - Cancel
    // null - x
    Object optionValue = optionPane.getValue();
    int value = -1;
    if (optionValue != null) {
      value = (int) optionValue;
    }
    return value;
  }

  public static int showOptionDialogYesNo(JComponent[] inputs, String title) {
    JOptionPane optionPane =
        new JOptionPane(inputs, JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION);
    JDialog dialog = optionPane.createDialog(title);
    dialog.setIconImage(logo);
    dialog.setVisible(true);
    // 0 - Ok
    // 2 - Cancel
    // null - x
    Object optionValue = optionPane.getValue();
    int value = -1;
    if (optionValue != null) {
      value = (int) optionValue;
    }
    return value;
  }

  public static int showOptionDialogOkCancel(JComponent[] inputs, String title) {
    JOptionPane optionPane =
        new JOptionPane(inputs, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
    JDialog dialog = optionPane.createDialog(title);
    dialog.setIconImage(logo);
    dialog.setVisible(true);
    // 0 - Ok
    // 2 - Cancel
    // null - x
    Object optionValue = optionPane.getValue();
    int value = -1;
    if (optionValue != null) {
      value = (int) optionValue;
    }
    return value;
  }

  public static int showOptionDialogOk(JComponent[] inputs, String title) {
    JOptionPane optionPane =
        new JOptionPane(inputs, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
    JDialog dialog = optionPane.createDialog(title);
    dialog.setIconImage(logo);
    dialog.setVisible(true);
    // 0 - Ok
    Object optionValue = optionPane.getValue();
    int value = -1;
    if (optionValue != null) {
      value = (int) optionValue;
    }
    return value;
  }
}
