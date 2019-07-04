package org.xjrga.potatosql;

import java.sql.*;
import java.util.*;

public class Test
{
    private Connection connection;

    public Test()
    {
        try
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
            this.Project_Select_All_Print();

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
        }finally
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
    }

    public static void main(String[] args)
    {
        Test test = new Test();
    }

    //paste generated methodNames here

    public List<Map<String, Object>> Project_Select_All() throws SQLException
    {
        LinkedList<Map<String, Object>> list = new LinkedList<>();
        CallableStatement proc;
        proc = connection.prepareCall("{CALL public.Project_Select_All()}");
        ResultSet rs = proc.executeQuery();
        while (rs.next())
        {
            Map<String, Object> row = new HashMap<>();
            for (int columnPos = 0; columnPos < 2; columnPos++)
            {
                row.put("PROJECTID", rs.getObject(1));
                row.put("PROJECTNAME", rs.getObject(2));
            }
            list.add(row);
        }
        proc.close();
        return list;
    }


    public void Project_Select_All_Print() throws SQLException
    {
        LinkedList list = (LinkedList) this.Project_Select_All();
        Iterator it = list.listIterator();
        while(it.hasNext()){
            HashMap row = (HashMap) it.next();
            Integer ProjectId = (Integer)row.get("PROJECTID");
            String ProjectName = (String)row.get("PROJECTNAME");
            System.out.println(ProjectId+","+ProjectName);
        }
    }

}

