package org.xjrga.potatosql.load;

import java.sql.*;

public class LoadDatabase
{
    private Connection connection;

    public LoadDatabase()
    {
        try
        {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:data/database/potatosql;shutdown=true;hsqldb.tx_level=read_uncommitted;hsqldb.tx=locks", "SA", "");
            //connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/database", "SA", "");
        }
        catch (ClassNotFoundException | SQLException ex)
        {

        }
    }

    public Integer Schema_Insert(String schemaName) throws SQLException
    {
        Integer ident = null;

        String sql = "{CALL public.DatabaseSchema_Insert( ?, ? )}";
        CallableStatement proc = connection.prepareCall(sql);
        proc.registerOutParameter(1, Types.INTEGER);
        proc.setString(2, schemaName);
        proc.execute();
        ident = proc.getInt(1);
        proc.close();

        return ident;
    }

    public Integer Table_Insert(Integer schemaId, String tableName) throws SQLException
    {
        Integer ident = null;

        String sql = "{CALL public.DatabaseTable_Insert(?, ?, ? )}";
        CallableStatement proc = connection.prepareCall(sql);
        proc.setInt(1, schemaId);
        proc.registerOutParameter(2, Types.INTEGER);
        proc.setString(3, tableName);
        proc.execute();
        ident = proc.getInt(2);
        proc.close();

        return ident;
    }

    public Integer TableKey_Insert(Integer schemaId, Integer tableId, String columnName, String columnLabel, Boolean IsPk, Integer typeId, Integer precision, Integer scale, Integer order) throws SQLException
    {
        Integer ident = null;

        String sql = "{CALL public.TableKey_Insert(?, ?, ?,?,?,?,?,?,?,? )}";
        CallableStatement proc = connection.prepareCall(sql);
        proc.registerOutParameter(1, Types.INTEGER);
        proc.setInt(2, schemaId);
        proc.setInt(3, tableId);
        proc.setString(4, columnName);
        proc.setString(5, columnLabel);
        proc.setBoolean(6, IsPk);
        proc.setInt(7, typeId);
        proc.setInt(8, precision);
        proc.setInt(9, scale);
        proc.setInt(10, order);
        proc.execute();
        ident = proc.getInt(1);
        proc.close();

        return ident;
    }

    public Integer Relationship_Insert(Integer schemaId, Integer parent_TableId, Integer child_TableId, Integer relationshipTypeId, String relationshipName, String forwardVerbPhrase, String reverseVerbPhrase) throws SQLException
    {
        Integer ident = null;

        String sql = "{CALL public.Relationship_Insert(?, ?, ?,?,?,?,?,? )}";
        CallableStatement proc = connection.prepareCall(sql);
        proc.registerOutParameter(1, Types.INTEGER);
        proc.setInt(2, schemaId);
        proc.setInt(3, parent_TableId);
        proc.setInt(4, child_TableId);
        proc.setInt(5, relationshipTypeId);
        proc.setString(6, relationshipName);
        proc.setString(7, forwardVerbPhrase);
        proc.setString(8, reverseVerbPhrase);
        proc.execute();
        ident = proc.getInt(1);
        proc.close();

        return ident;
    }

    public void RelationshipKeyPair_Insert(Integer schemaId, Integer parent_TableId, Integer child_TableId, Integer relationshipId, Integer parent_KeyId, Integer child_KeyId) throws SQLException
    {
        String sql = "{CALL public.RelationshipKeyPair_Insert(?, ?, ?,?,?,?)}";
        CallableStatement proc = connection.prepareCall(sql);
        proc.setInt(1, schemaId);
        proc.setInt(2, parent_TableId);
        proc.setInt(3, child_TableId);
        proc.setInt(4, relationshipId);
        proc.setInt(5, parent_KeyId);
        proc.setInt(6, child_KeyId);
        proc.execute();
        proc.close();

    }

    public void turnOffAutocommit()
    {
        try
        {
            connection.setAutoCommit(false);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void turnOnAutocommit()
    {
        try
        {
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void commit()
    {
        try
        {
            connection.commit();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void close()
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void shutdown()
    {
        String sql = "SHUTDOWN COMPACT;";
        Statement stmt;
        try
        {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void rollback()
    {
        try
        {
            connection.rollback();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
