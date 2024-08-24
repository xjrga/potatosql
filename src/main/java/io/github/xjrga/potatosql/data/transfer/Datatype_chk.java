package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Keypair;
import io.github.xjrga.potatosql.functions.R1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Datatype_chk implements R1<Keypair, Boolean> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public Boolean apply(Keypair pair) {
    Boolean check = false;
    try {
      try (CallableStatement proc =
          connection.prepareCall("{call potatosql.compare_datatype(?,?,?,?,?)}")) {
        proc.setInt(1, pair.getSchema_id());
        proc.setInt(2, pair.getTable_id_parent());
        proc.setInt(3, pair.getKey_id_parent());
        proc.setInt(4, pair.getTable_id_child());
        proc.setInt(5, pair.getKey_id_child());
        ResultSet rs = proc.executeQuery();
        rs.next();
        check = rs.getBoolean(1);
      }
    } catch (SQLException ex) {
      System.out.println(ex);
    }
    return check;
  }
}
