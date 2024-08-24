<#list data.table_iterator as table>
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class ${table.table_name?cap_first}_pop_dtm {
  private final Connection connection;
  
  public ${table.table_name?cap_first}_pop_dtm() {
    this.connection = null;
  }

  public DefaultTableModel execute() {
    Vector data = new Vector<>();
    Vector columns = new Vector();
    <#list table.key_iterator as key>
    columns.add( "${key.key_name?cap_first}" );
    </#list>
    DefaultTableModel model =
        new DefaultTableModel() {
          @Override
          public Class getColumnClass(int i) {
            Class returnValue = Object.class;            
            switch (i) {
            <#list table.key_iterator as key>            
              case ${key?index} -> // ${key.key_name?cap_first}
                  returnValue = ${dtype.getJava(key.datatype)}.class;
            </#list>              
            }
            return returnValue;
          }

          @Override
          public boolean isCellEditable(int i, int j) {
            return false;
          }
        };
    try (CallableStatement proc = connection.prepareCall("{CALL schema.procedure()}")) {
      ResultSet rs = proc.executeQuery();
      while (rs.next()) {
        Vector row = new Vector<>();
        <#list table.key_iterator as key>
        row.add(rs.get${dtype.getProc(key.datatype)}(${key?counter}));
        </#list>        
        data.add(row);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    model.setDataVector(data, columns);
    return model;
  }
}
</#list>
