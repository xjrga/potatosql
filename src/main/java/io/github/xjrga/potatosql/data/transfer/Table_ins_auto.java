package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Table;
import io.github.xjrga.potatosql.functions.R1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.hsqldb.types.Types;

public class Table_ins_auto implements R1<Table, Integer> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public Integer apply(Table table) {
    Integer id = null;
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.Database_table_insert_auto(?,?,?,?)}")) {
      proc.registerOutParameter(1, Types.INTEGER);
      proc.setInt(2, table.getSchema_id());
      proc.setString(3, table.getTable_name());
      proc.setBoolean(4, table.getIs_dependent());
      proc.execute();
      id = proc.getInt(1);
    } catch (SQLException ex) {
    }
    return id;
  }
}
