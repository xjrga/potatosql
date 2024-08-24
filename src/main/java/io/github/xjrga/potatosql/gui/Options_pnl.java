package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import io.github.xjrga.potatosql.data.object.Code;
import io.github.xjrga.potatosql.data.object.Code_sub;
import io.github.xjrga.potatosql.generator.dot.Code_configuration_importer;
import io.github.xjrga.potatosql.generator.dot.Code_populator;
import io.github.xjrga.potatosql.generator.dot.Code_sub_populator;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;

public class Options_pnl extends JPanel {
  private final Mediator mediator;
  public CellConstraints cc = new CellConstraints();
  private final JList lst_dialect = new JList();
  private final JList lst_category = new JList();
  private final JList lst_action = new JList();
  private final JList lst_queue = new JList();
  private final DefaultListModel model_queue = new DefaultListModel();
  private List<Code> collection_config;
  private List<String> collection_dialect;
  private String selected_dialect;
  private String selected_category;
  private List<Code_sub> selected_actions;

  public Options_pnl(Mediator mediator) {
    this.mediator = mediator;
    before();
    setup();
    add(get_new_pnl(lst_dialect, "Dialect", new Dimension(150, 400)));
    add(get_new_pnl(lst_category, "Category", new Dimension(150, 400)));
    add(get_new_pnl(lst_action, "Action", new Dimension(280, 400)));
    add(get_btn_pnl());
    add(get_code_queue_pnl(new Dimension(280, 400)));
    after();
  }

  private void before() {}

  private void setup() {
    mediator.register_options_panel(this);
    Code_configuration_importer importer = new Code_configuration_importer();
    Code_populator populator = new Code_populator();
    lst_queue.setModel(model_queue);
    collection_config = importer.apply("template/config.xml");
    collection_dialect =
        collection_config.stream()
            .map(dcode -> dcode.getDialect())
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    lst_dialect.setModel(populator.apply(collection_dialect));
    lst_dialect.addListSelectionListener(
        (ListSelectionEvent e) -> {
          if (e.getValueIsAdjusting()) {
            return;
          }
          event_list_selection_dialect();
        });
    lst_category.addListSelectionListener(
        (ListSelectionEvent e) -> {
          if (e.getValueIsAdjusting()) {
            return;
          }
          event_list_selection_category();
        });
    lst_action.addListSelectionListener(
        (ListSelectionEvent e) -> {
          if (e.getValueIsAdjusting()) {
            return;
          }
          event_list_selection_action();
        });
  }

  private void after() {
    set_default_action("dot, diagram, erd");
  }

  private void event_list_selection_dialect() {
    selected_dialect = (String) lst_dialect.getSelectedValue();
    Comparator<Code> comparator = Comparator.comparing(dcode -> dcode.getCategory());
    List<Code> collection_code =
        collection_config.stream()
            .filter((Code code) -> code.getDialect().equals(selected_dialect))
            .sorted(comparator)
            .collect(Collectors.toList());
    List<String> collection_category =
        collection_code.stream()
            .map(code -> code.getCategory())
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    Code_populator populator = new Code_populator();
    lst_category.setModel(populator.apply(collection_category));
  }

  private void event_list_selection_category() {
    selected_category = (String) lst_category.getSelectedValue();
    Comparator<Code> comparator_code = Comparator.comparing(code -> code.getOrder());
    List<Code> collection_code =
        collection_config.stream()
            .filter(
                (Code code) ->
                    (code.getCategory().equals(selected_category)
                        & code.getDialect().equals(selected_dialect)))
            .sorted(comparator_code)
            .collect(Collectors.toList());
    Comparator<Code_sub> comparator_code_sub =
        Comparator.comparing(code_sub -> code_sub.getAction());
    List<Code_sub> collection_action =
        collection_code.stream()
            .map(code -> code.getCode_sub())
            .distinct()
            .sorted(comparator_code_sub)
            .collect(Collectors.toList());
    Code_sub_populator populator = new Code_sub_populator();
    lst_action.setModel(populator.apply(collection_action));
  }

  private void event_list_selection_action() {
    selected_actions = lst_action.getSelectedValuesList();
  }

  public Stream<Code> get_code_list_stream() {
    ArrayList<Code> collection_queue = new ArrayList();
    model_queue.elements().asIterator().forEachRemaining((x) -> collection_queue.add((Code) x));
    return collection_queue.stream();
  }

  private JPanel get_code_queue_pnl(Dimension dim) {
    FormLayout layout =
        new FormLayout(
            "min:grow", // columns
            "fill:min:grow,min" // rows
            );
    JPanel pnl = new JPanel();
    pnl.setLayout(layout);
    pnl.setBorder(new TitledBorder("Action Queue"));
    pnl.setPreferredSize(dim);
    pnl.add(new JScrollPane(lst_queue), cc.xy(1, 1));
    JPanel btn_pnl = new JPanel();
    pnl.add(btn_pnl, cc.xy(1, 2));
    JButton btn_up = new JButton(" Up ");
    JButton btn_down = new JButton("Down");
    JButton btn_clear = new JButton("Clear");
    btn_pnl.add(btn_up);
    btn_pnl.add(btn_down);
    btn_pnl.add(btn_clear);
    btn_up.addActionListener(
        e -> {
          Code o = (Code) lst_queue.getSelectedValue();
          int index = lst_queue.getSelectedIndex();
          if (index == 0) {
            return;
          }
          model_queue.remove(index);
          index--;
          model_queue.insertElementAt(o, index);
          lst_queue.setSelectedIndex(index);
        });
    btn_down.addActionListener(
        e -> {
          Code o = (Code) lst_queue.getSelectedValue();
          int index = lst_queue.getSelectedIndex();
          if (index == model_queue.getSize() - 1) {
            return;
          }
          model_queue.remove(index);
          index++;
          model_queue.insertElementAt(o, index);
          lst_queue.setSelectedIndex(index);
        });
    btn_clear.addActionListener(
        e -> {
          model_queue.clear();
        });
    return pnl;
  }

  private void set_default_action(String name) {
    Stream<Code> stream = collection_config.stream();
    Stream<Code> filter = stream.filter(action -> action.getName().equals(name));
    List<Code> collect = filter.collect(Collectors.toList());
    if (collect.isEmpty()) {
      return;
    }
    Code action = collect.get(0);
    model_queue.addElement(action);
  }

  private JPanel get_new_pnl(JList lst, String title, Dimension dim) {
    FormLayout layout =
        new FormLayout(
            "min:grow", // columns
            "fill:min:grow" // rows
            );
    JPanel pnl = new JPanel();
    pnl.setLayout(layout);
    pnl.setBorder(new TitledBorder(title));
    pnl.setPreferredSize(dim);
    pnl.add(new JScrollPane(lst), cc.xy(1, 1));
    return pnl;
  }

  private JPanel get_btn_pnl() {
    FormLayout layout =
        new FormLayout(
            "min:grow", // columns
            "fill:min:grow,min,min,fill:min:grow" // rows
            );
    JPanel pnl = new JPanel();
    JButton btn_add = new JButton();
    JButton btn_remove = new JButton();
    pnl.setLayout(layout);
    btn_add.setText("->");
    btn_remove.setText("<-");
    pnl.add(btn_add, cc.xy(1, 2));
    pnl.add(btn_remove, cc.xy(1, 3));
    btn_add.addActionListener(
        e -> {
          event_action_add();
        });
    btn_remove.addActionListener(
        e -> {
          event_action_delete();
        });
    return pnl;
  }

  private void event_action_add() {
    selected_actions.forEach(
        (selected_action) ->
            model_queue.addElement(
                collection_config.stream()
                    .filter((Code code) -> (code.hashCode() == selected_action.hashCode()))
                    .collect(Collectors.toList())
                    .get(0)));
  }

  private void event_action_delete() {
    model_queue.removeElement(lst_queue.getSelectedValue());
  }
}
