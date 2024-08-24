package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.data.object.Template_relationship;
import io.github.xjrga.potatosql.functions.R1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Template_relationship_sel implements R1<Schema, List<Template_relationship>> {
  private final Connection connection = Connect.getInstance().getConnection();

  @Override
  public List<Template_relationship> apply(Schema schema) {
    List<Template_relationship> list = null;
    BeanListHandler<Template_relationship> beanListHandler =
        new BeanListHandler<>(Template_relationship.class);
    try (CallableStatement proc =
        connection.prepareCall("{CALL potatosql.find_relationships(?)}"); ) {
      proc.setInt(1, schema.getSchema_id());
      ResultSet rs = proc.executeQuery();
      list = beanListHandler.handle(rs);
    } catch (SQLException ex) {
    }
    return list;
  }
}
