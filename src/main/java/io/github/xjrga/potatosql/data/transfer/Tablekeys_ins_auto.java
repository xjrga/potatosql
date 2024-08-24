package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Key;
import io.github.xjrga.potatosql.functions.R1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.hsqldb.types.Types;

public class Tablekeys_ins_auto implements R1<Key, Integer> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public Integer apply(Key key) {
    Integer id = null;
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.Table_key_insert_auto(?,?,?,?,?,?,?)}")) {
      proc.registerOutParameter(1, Types.INTEGER);
      proc.setInt(2, key.getSchema_id());
      proc.setInt(3, key.getTable_id());
      proc.setString(4, key.getKey_name());
      proc.setInt(5, key.getDatatype_id());
      proc.setInt(6, key.getKey_order());
      proc.setBoolean(7, key.getIs_primary_key());
      proc.execute();
      id = proc.getInt(1);
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return id;
  }
}
