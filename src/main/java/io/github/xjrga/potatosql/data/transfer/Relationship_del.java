package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Relationship;
import io.github.xjrga.potatosql.functions.V1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Relationship_del implements V1<Relationship> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public void apply(Relationship relationship) {
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.Relationship_delete(?,?,?,?)}")) {
      proc.setInt(1, relationship.getSchema_id());
      proc.setInt(2, relationship.getRelationship_id());
      proc.setInt(3, relationship.getTable_id_parent());
      proc.setInt(4, relationship.getTable_id_child());
      proc.execute();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
