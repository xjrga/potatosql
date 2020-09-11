package org.xjrga.potatosql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test {
    private Connection connection;

    public Test() {
        /*try
        {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/database", "SA", "");
            //connection = DriverManager.getConnection("jdbc:hsqldb:file:data/databases/database1;shutdown=true", "SA", "");
            //connection = DriverManager.getConnection("jdbc:hsqldb:mem:db", "SA", "");
        }
        catch (ClassNotFoundException | SQLException ex)
        {
}
try
        {
            connection.setAutoCommit(false);
//call insert, update, delete or merge methodNames here
connection.commit();
}
        catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                connection.rollback();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }*/
    }

    //paste generated methodNames here
    public static void main(String[] args) {
        Test test = new Test();
    }

    private Date createDate(String dateastxt) throws ParseException {
        //"2019/09/06 19:00:00"
        long millis = getMillis(dateastxt);
        Date date = new Date(millis);
        return date;
    }

    private Time createTime(String dateastxt) throws ParseException {
        //"2019/09/06 19:00:00"
        long millis = getMillis(dateastxt);
        Time time = new Time(millis);
        return time;
    }

    private Timestamp createTimestamp(String dateastxt) throws ParseException {
        //"2019/09/06 19:00:00"
        long millis = getMillis(dateastxt);
        Timestamp timestamp = new Timestamp(millis);
        return timestamp;
    }

    private long getMillis(String timestamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.parse(timestamp).getTime();
    }

    private Blob createBlob(String filepath) throws SQLException, IOException {
        Blob blob = connection.createBlob();
        byte[] bytes = Files.readAllBytes(Paths.get(filepath));
        blob.setBytes(1, bytes);
        return blob;
    }

    private Clob createClob(String filepath) throws SQLException, IOException {
        Clob clob = connection.createClob();
        byte[] bytes = Files.readAllBytes(Paths.get(filepath));
        clob.setString(1, new String(bytes));
        return clob;
    }
}
