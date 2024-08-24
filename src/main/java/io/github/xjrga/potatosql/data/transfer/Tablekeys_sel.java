package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Key_mod;
import io.github.xjrga.potatosql.data.object.Table;
import io.github.xjrga.potatosql.functions.R1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Tablekeys_sel implements R1<Table, List<Key_mod>> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public List<Key_mod> apply(Table table) {
    List<Key_mod> list = null;
    BeanListHandler<Key_mod> beanListHandler = new BeanListHandler<>(Key_mod.class);
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.Table_key_select(?,?)}"); ) {
      proc.setInt(1, table.getSchema_id());
      proc.setInt(2, table.getTable_id());
      ResultSet rs = proc.executeQuery();
      list = beanListHandler.handle(rs);
    } catch (SQLException ex) {
    }
    return list;
  }
}
