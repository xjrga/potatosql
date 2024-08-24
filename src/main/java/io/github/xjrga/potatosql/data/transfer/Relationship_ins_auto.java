package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Relationship;
import io.github.xjrga.potatosql.functions.R1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Relationship_ins_auto implements R1<Relationship, Integer> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public Integer apply(Relationship key) {
    Integer id = null;
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.Relationship_insert_auto(?,?,?,?,?)}")) {
      proc.registerOutParameter(1, Types.INTEGER);
      proc.setInt(2, key.getSchema_id());
      proc.setInt(3, key.getTable_id_parent());
      proc.setInt(4, key.getTable_id_child());
      proc.setBoolean(5, key.getIs_identifying());
      proc.execute();
      id = proc.getInt(1);
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return id;
  }
}
