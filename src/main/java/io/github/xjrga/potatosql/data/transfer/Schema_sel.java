package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.functions.R0;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Schema_sel implements R0<List<Schema>> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public List<Schema> apply() {
    List<Schema> list = null;
    BeanListHandler<Schema> beanListHandler = new BeanListHandler<>(Schema.class);
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.Database_schema_select()}"); ) {
      ResultSet rs = proc.executeQuery();
      list = beanListHandler.handle(rs);
    } catch (SQLException ex) {
    }
    return list;
  }
}
