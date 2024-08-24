package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.data.object.Table;
import io.github.xjrga.potatosql.functions.R1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Table_sel implements R1<Schema, List<Table>> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public List<Table> apply(Schema schema) {
    List<Table> list = null;
    BeanListHandler<Table> beanListHandler = new BeanListHandler<>(Table.class);
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.Database_table_select(?)}"); ) {
      proc.setInt(1, schema.getSchema_id());
      ResultSet rs = proc.executeQuery();
      list = beanListHandler.handle(rs);
    } catch (SQLException ex) {
    }
    return list;
  }
}
