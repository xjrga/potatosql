package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Key;
import io.github.xjrga.potatosql.functions.V1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Tablekeys_ins implements V1<Key> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public void apply(Key key) {
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.Table_key_insert(?,?,?,?,?,?,?,?)}")) {
      proc.setInt(1, key.getSchema_id());
      proc.setInt(2, key.getTable_id());
      proc.setInt(3, key.getKey_id());
      proc.setString(4, key.getKey_name());
      proc.setInt(5, key.getDatatype_id());
      proc.setInt(6, key.getKey_order());
      proc.setBoolean(7, key.getIs_primary_key());
      proc.setBoolean(8, key.getIs_foreign_key());
      proc.execute();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
