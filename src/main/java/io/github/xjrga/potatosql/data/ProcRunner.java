/*
 * Snack: Learning Software for Nutrition
 * Copyright (C) 2018 Jorge R Garcia de Alba
 * License: http://www.gnu.org/licenses/gpl.html GPL version 2 or higher
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package io.github.xjrga.potatosql.data;

import io.github.xjrga.potatosql.other.Print;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProcRunner {

    private Connection connection;
    private LinkedList<String> columnLabelList;

    public ProcRunner() {

        connection = Connect.getInstance().getConnection();
        columnLabelList = new LinkedList<>();
    }

    /**
     * @param sql , sql = "{ call public.procedure( ?, ? )}";
     * @param parameters - input parameters
     * @return - list containing table rows
     */
    public List<Map<String, Object>> callProcedureR(String sql, Object[] parameters) {

        //Print.printCall(sql, parameters);
        LinkedList<Map<String, Object>> list = new LinkedList<>();
        CallableStatement proc;
        columnLabelList.clear();
        try {
            proc = connection.prepareCall(sql);
            setParameters(parameters, proc);
            ResultSet rs = proc.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int columnPos = 0; columnPos < columnCount; columnPos++) {
                String columnName = metaData.getColumnLabel(columnPos + 1);
                columnLabelList.add(columnName);
            }
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int columnPos = 0; columnPos < columnCount; columnPos++) {
                    Object columnValue = rs.getObject(columnPos + 1);
                    row.put(columnLabelList.get(columnPos), columnValue);
                }
                list.add(row);
            }
            proc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void setParameters(Object[] parameters, CallableStatement proc) throws SQLException {

        for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            if (parameter != null) {
                String classType = parameter.getClass().getSimpleName();
                switch (classType) {
                    case "String":
                        proc.setString(i + 1, (String) parameter);
                        break;
                    case "Integer":
                        proc.setInt(i + 1, (int) parameter);
                        break;
                    case "Double":
                        proc.setDouble(i + 1, (Double) parameter);
                        break;
                    case "Date":
                        proc.setDate(i + 1, (Date) parameter);
                        break;
                    default:
                        proc.setObject(i + 1, parameter);
                }
            } else {
                proc.setNull(i + 1, Types.NULL);
            }
        }
    }

    /**
     * @param sql , sql = "{ call public.procedure( ?, ? )}";
     * @param parameters - input parameters
     */
    public void callProcedure(String sql, Object[] parameters) {

        //Print.printCall(sql, parameters);
        CallableStatement proc;
        try {
            proc = connection.prepareCall(sql);
            setParameters(parameters, proc);
            proc.execute();
            proc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param sql , sql = "{ call public.function( ? ) }"
     * @param parameters - input parameters
     * @return - object containing function result
     */
    public Object callFunction(String sql, Object[] parameters) {

        CallableStatement proc;
        Object out = null;
        try {
            proc = connection.prepareCall(sql);
            setParameters(parameters, proc);
            proc.execute();
            ResultSet resultSet = proc.getResultSet();
            resultSet.next();
            out = resultSet.getObject(1);
            proc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public void resetDB() {

        //TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK - clear all data, restart identities, keep tables, bypass referential integrity
        String sql = "TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK;";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeUpdate(String sql) {

        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTables() {

        //DROP SCHEMA PUBLIC CASCADE - clear all data and drop all tables
        String sql = "DROP SCHEMA PUBLIC CASCADE;";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Shutdown() {

        //SHUTDOWN COMPACT
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

    //DROP SCHEMA PUBLIC CASCADE - clear all data and drop all tables
    //TRUNCATE SCHEMA PUBLIC AND COMMIT - clear all data and keep tables
    //TRUNCATE SCHEMA PUBLIC AND COMMIT NO CHECK - clear all data, keep tables, bypass referential integrity
    //? TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK - clear all data, restart identities, keep tables, bypass referential integrity
}
