package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Datatype;
import io.github.xjrga.potatosql.functions.R0;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Datatype_sel implements R0<List<Datatype>> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public List<Datatype> apply() {
    List<Datatype> list = null;
    BeanListHandler<Datatype> beanListHandler = new BeanListHandler<>(Datatype.class);
    try (CallableStatement proc = connection.prepareCall("{CALL potatosql.Datatype_select()}"); ) {
      ResultSet rs = proc.executeQuery();
      list = beanListHandler.handle(rs);
    } catch (SQLException ex) {
    }
    return list;
  }
}
