package io.github.xjrga.potatosql.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jr
 */
public class Dbactions {

    public Dbactions() {
    }

    public static void shutdown(Connection connection) {
        String sql = "SHUTDOWN COMPACT;";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void backup(Connection connection) {
        String sql = "SCRIPT 'backup.script';";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
