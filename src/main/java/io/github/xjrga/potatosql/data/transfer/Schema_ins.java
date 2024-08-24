package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.functions.V1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Schema_ins implements V1<Schema> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public void apply(Schema schema) {
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.Database_schema_insert(?,?)}")) {
      proc.setInt(1, schema.getSchema_id());
      proc.setString(2, schema.getSchema_name());
      proc.execute();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
