package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Table;
import io.github.xjrga.potatosql.functions.V1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Table_del implements V1<Table> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public void apply(Table table) {
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.Database_table_delete(?,?)}")) {
      proc.setInt(1, table.getSchema_id());
      proc.setInt(2, table.getTable_id());
      proc.execute();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
