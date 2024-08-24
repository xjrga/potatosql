package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Key;
import io.github.xjrga.potatosql.functions.V1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Identifying_keypair_ins implements V1<Key> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public void apply(Key key) {
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.Identifying_relationship_insert(?,?,?,?)}")) {
      proc.setInt(1, key.getSchema_id());
      proc.setInt(3, key.getTable_id());
      proc.setInt(4, key.getKey_id());
      proc.execute();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
