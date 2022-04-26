package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;

public class CreateTestClass implements Code {

    public CreateTestClass() {
    }

    @Override
    public String get_code() {
        StringBuilder sb = new StringBuilder();
        String code = "package x;\n"
                + "import java.sql.*;\n"
                + "public class Test {\n"
                + "    private Connection connection;\n"
                + "    public Test() {\n"
                + "        try {\n"
                + "            Class.forName(\"org.hsqldb.jdbcDriver\");\n"
                + "            connection = DriverManager.getConnection(\"jdbc:hsqldb:hsql://localhost/db\", \"sa\", \"\");\n"
                + "            //connection = DriverManager.getConnection(\"jdbc:hsqldb:file:data/database/db;shutdowncompact=true\", \"sa\", \"\");\n"
                + "            //connection = DriverManager.getConnection(\"jdbc:hsqldb:mem:db\", \"sa\", \"\");\n"
                + "            if (connection.isValid(1)) {\n"
                + "                try {\n"
                + "                    //connection.setAutoCommit(false);\n"
                + "                    //call insert, update, delete or select methods here\n"
                + "                    //connection.rollback();\n"
                + "                    //connection.commit();\n"
                + "                } catch (SQLException e) {                    \n"
                + "                    e.printStackTrace();\n"
                + "                } finally {\n"
                + "                    try {\n"
                + "                        connection.close();\n"
                + "                    } catch (SQLException e) {\n"
                + "                        e.printStackTrace();\n"
                + "                    }\n"
                + "                }\n"
                + "            } else {\n"
                + "                System.out.println(\"Connection not valid.\");\n"
                + "            }\n"
                + "        } catch (ClassNotFoundException | SQLException ex) {\n"
                + "            ex.printStackTrace();\n"
                + "        }\n"
                + "    }  \n"
                + "    public static void main(String[] args) {\n"
                + "        Test test = new Test();\n"
                + "    }    \n"
                + "}\n"
                + "";
        sb.append(code);
        sb.append("\n");
        sb.append("\n");
        return sb.toString();
    }

}
