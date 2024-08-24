package io.github.xjrga.potatosql.gui;

import io.github.xjrga.looks.themes.Dawn_150;
import io.github.xjrga.potatosql.other.ImageUtilities;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class App {
  private JFrame frame;
  private Mediator_concrete mediator;
  private final BufferedImage logo = ImageUtilities.readImage("resources/images/logo.png");

  public App() {
    initialize();
    setup();
    frame.setTitle("Potatosql");
    frame.setIconImage(logo);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setJMenuBar(mediator.get_menu_bar());
    frame.add(mediator.get_main_panel());
    frame.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            frame.setVisible(false);
            frame.dispose();
            // System.exit(0);
          }
        });
    frame.setPreferredSize(new Dimension(1150, 800));
    frame.pack();
    frame.setVisible(true);
  }

  private void initialize() {
    frame = new JFrame();
    mediator = new Mediator_concrete();
    var menu_bar = new Menu_bar(mediator);
    var schema_panel = new Schema_pnl(mediator);
    var table_keys_panel = new Table_keys_pnl(mediator);
    var table_panel = new Table_pnl(mediator);
    var table_panel_composite = new Table_pnl_composite(mediator);
    var relationship_pair_keys_panel = new Relationship_key_pairs_pnl(mediator);
    var relationship_panel = new Relationship_pnl(mediator);
    var relationship_panel_composite = new Relationship_pnl_composite(mediator);
    var output_panel_composite = new Output_pnl_composite(mediator);
    var main_panel = new Main_pnl(mediator);
    var options_panel = new Options_pnl(mediator);
  }

  private void setup() {}

  public static void main(String[] args) {
    try {
      Font font = new Font(Font.DIALOG, Font.PLAIN, 12);
      MetalLookAndFeel.setCurrentTheme(new Dawn_150(font));
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } catch (ClassNotFoundException
        | InstantiationException
        | IllegalAccessException
        | UnsupportedLookAndFeelException ex) {
    }
    App app = new App();
  }
}
