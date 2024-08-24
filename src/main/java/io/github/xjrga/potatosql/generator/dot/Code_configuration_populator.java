package io.github.xjrga.potatosql.generator.dot;

import io.github.xjrga.potatosql.data.object.Code;
import io.github.xjrga.potatosql.functions.R1;
import java.util.List;
import javax.swing.DefaultListModel;

public class Code_configuration_populator implements R1<List<Code>, DefaultListModel> {
  @Override
  public DefaultListModel apply(List<Code> list) {
    DefaultListModel model = new DefaultListModel();
    list.forEach(
        element -> {
          model.addElement(element);
        });
    return model;
  }
}
