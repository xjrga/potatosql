package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.object.Datatype;
import io.github.xjrga.potatosql.functions.R1;
import java.util.List;
import javax.swing.DefaultListModel;

public class Datatype_pop implements R1<List<Datatype>, DefaultListModel> {
  @Override
  public DefaultListModel apply(List<Datatype> list) {
    DefaultListModel model = new DefaultListModel();
    list.forEach(
        element -> {
          model.addElement(element);
        });
    return model;
  }
}
