<#list data.table_iterator as table>
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;

public class ${table.table_name?cap_first}_pop_dlm {
  private final Connection connection;

  public ${table.table_name?cap_first}_pop_dlm() {
    this.connection = null;
  }

  public DefaultListModel execute() {
    DefaultListModel model = new DefaultListModel();
    try (CallableStatement proc = connection.prepareCall("{CALL schema.procedure()}")) {
      ResultSet rs = proc.executeQuery();
      while (rs.next()) {
        ${table.table_name?cap_first} ${table.table_name?c_lower_case} = new ${table.table_name}(<#list table.key_iterator as key>
        rs.get${dtype.getProc(key.datatype)}(${key?counter})<#if key?has_next>,</#if></#list>
        );
        model.addElement(${table.table_name?c_lower_case});
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return model;
  }
}
</#list>


