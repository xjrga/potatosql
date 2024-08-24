package io.github.xjrga.potatosql.generator.dot;

import io.github.xjrga.potatosql.data.object.Code_sub;
import io.github.xjrga.potatosql.functions.R1;
import java.util.List;
import javax.swing.DefaultListModel;

public class Code_sub_populator implements R1<List<Code_sub>, DefaultListModel> {
  @Override
  public DefaultListModel apply(List<Code_sub> list) {
    DefaultListModel model = new DefaultListModel();
    list.forEach(
        element -> {
          model.addElement(element);
        });
    return model;
  }
}
