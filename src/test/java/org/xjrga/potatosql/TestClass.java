package org.xjrga.potatosql;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestClass
{

    private LinkedList<String> columnLabelList;


    public TestClass()
    {

        columnLabelList = new LinkedList<>();
    }


    public static void main(String[] args)
    {

        TestClass testClass = new TestClass();
    }


    public List<Map<String, Object>> executeProcedureAndGet(String sql, Object[] parameters)
    {

        LinkedList<Map<String, Object>> list = new LinkedList<>();
        CallableStatement proc;
        columnLabelList.clear();
        try
        {
            proc = getConnection().prepareCall(sql);
            setParameters(parameters, proc);
            ResultSet rs = proc.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            //storeColumnLabel
            for (int columnPos = 0; columnPos < columnCount; columnPos++)
            {
                String columnName = metaData.getColumnLabel(columnPos + 1);
                columnLabelList.add(columnName);
            }
            //storeRowValue
            while (rs.next())
            {
                Map<String, Object> row = new HashMap<>();
                for (int columnPos = 0; columnPos < columnCount; columnPos++)
                {
                    Object columnValue = rs.getObject(columnPos + 1);
                    row.put(columnLabelList.get(columnPos), columnValue);
                }
                list.add(row);
            }
            proc.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }


    public static Connection getConnection()
    {

        Connection connection = null;
        String driver = "org.hsqldb.jdbcDriver";
        String url = "jdbc:hsqldb:mem:database";
        String username = "SA";
        String password = "";
        try
        {
            Class.forName(driver);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }


    private void setParameters(Object[] parameters, CallableStatement proc) throws SQLException
    {

        for (int i = 0; i < parameters.length; i++)
        {
            Object parameter = parameters[i];
            if (parameter != null)
            {
                String classType = parameter.getClass().getSimpleName();
                switch (classType)
                {
                    case "String":
                        proc.setString(i + 1, (String) parameter);
                        break;
                    case "BigDecimal":
                        proc.setBigDecimal(i + 1, (BigDecimal) parameter);
                        break;
                    case "Boolean":
                        proc.setBoolean(i + 1, (boolean) parameter);
                        break;
                    case "Integer":
                        proc.setInt(i + 1, (int) parameter);
                        break;
                    case "Long":
                        proc.setLong(i + 1, (long) parameter);
                        break;
                    case "Float":
                        proc.setFloat(i + 1, (float) parameter);
                        break;
                    case "Double":
                        proc.setDouble(i + 1, (double) parameter);
                        break;
                    case "Byte":
                        proc.setBytes(i + 1, (byte[]) parameter);
                        break;
                    case "Date":
                        proc.setDate(i + 1, (Date) parameter);
                        break;
                    case "Time":
                        proc.setTime(i + 1, (Time) parameter);
                        break;
                    case "Timestamp":
                        proc.setTimestamp(i + 1, (Timestamp) parameter);
                        break;
                    default:
                        proc.setObject(i + 1, parameter);
                }
            } else
            {
                proc.setNull(i + 1, Types.NULL);
            }
        }
    }


    public void executeProcedure(String sql, Object[] parameters)
    {

        CallableStatement proc;
        try
        {
            proc = getConnection().prepareCall(sql);
            setParameters(parameters, proc);
            proc.execute();
            proc.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public Object executeFunctionAndGet(String sql, Object[] parameters)
    {

        CallableStatement proc;
        Object out = null;
        try
        {
            proc = getConnection().prepareCall(sql);
            setParameters(parameters, proc);
            proc.execute();
            ResultSet resultSet = proc.getResultSet();
            resultSet.next();
            out = resultSet.getObject(1);
            proc.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return out;
    }


    public void schemaTruncate()
    {

        //TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK - clear all data, restart identities, keep tables, bypass referential integrity

        String sql = "TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK;";
        Statement stmt;
        try
        {
            stmt = getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void executeUpdate(String sql)
    {

        Statement stmt;
        try
        {
            stmt = getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void schemaDrop()
    {

        //DROP SCHEMA PUBLIC CASCADE - clear all data and drop all tables

        String sql = "DROP SCHEMA PUBLIC CASCADE;";
        Statement stmt;
        try
        {
            stmt = getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    //DROP SCHEMA PUBLIC CASCADE - clear all data and drop all tables
    //TRUNCATE SCHEMA PUBLIC AND COMMIT - clear all data and keep tables
    //TRUNCATE SCHEMA PUBLIC AND COMMIT NO CHECK - clear all data, keep tables, bypass referential integrity
    //TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK - clear all data, restart identities, keep tables, bypass referential integrity

}
