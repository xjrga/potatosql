package org.xjrga.potatosql.generator;

import java.util.Iterator;
import java.util.LinkedList;

public class CreateTestClass implements Code
{
    private LinkedList methodNames;
    private LinkedList methods;

    public CreateTestClass()
    {
        methodNames = new LinkedList();
        methods = new LinkedList();
    }

    @Override
    public String getCode()
    {
        StringBuilder sb = new StringBuilder();

        Iterator it = methodNames.iterator();
        StringBuilder sb1 = new StringBuilder();
        while (it.hasNext())
        {
            sb1.append(it.next());
            sb1.append("\n");
        }
        String methodNameList = sb1.toString();

        Iterator it2 = methods.iterator();
        StringBuilder sb2 = new StringBuilder();
        while (it2.hasNext())
        {
            sb2.append(it2.next());
            sb2.append("\n");
        }
        String methodList = sb2.toString();


        String code = "import java.sql.*;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.LinkedList;\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "public class Test\n" +
                "{\n" +
                "    private Connection connection;\n" +
                "\n" +
                "    public Test()\n" +
                "    {\n" +
                "        try\n" +
                "        {\n" +
                "            Class.forName(\"org.hsqldb.jdbcDriver\");\n" +
                "            connection = DriverManager.getConnection(\"jdbc:hsqldb:hsql://localhost/db\", \"SA\", \"\");\n" +
                "            //connection = DriverManager.getConnection(\"jdbc:hsqldb:file:data/databases/database1;shutdown=true\", \"SA\", \"\");\n" +
                "            //connection = DriverManager.getConnection(\"jdbc:hsqldb:mem:db\", \"SA\", \"\");\n" +
                "        }\n" +
                "        catch (ClassNotFoundException | SQLException ex)\n" +
                "        {\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        try\n" +
                "        {\n" +
                "            connection.setAutoCommit(false);\n" +
                "\n" +
                "            //call insert, update, delete or merge methodNames here\n" +
                methodNameList
                +
                "\n" +
                "            connection.commit();\n" +
                "\n" +
                "        }\n" +
                "        catch (Exception e)\n" +
                "        {\n" +
                "            e.printStackTrace();\n" +
                "            try\n" +
                "            {\n" +
                "                connection.rollback();\n" +
                "            }\n" +
                "            catch (SQLException ex)\n" +
                "            {\n" +
                "                ex.printStackTrace();\n" +
                "            }\n" +
                "        }finally\n" +
                "        {\n" +
                "            try\n" +
                "            {\n" +
                "                connection.close();\n" +
                "            }\n" +
                "            catch (SQLException e)\n" +
                "            {\n" +
                "                e.printStackTrace();\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] args)\n" +
                "    {\n" +
                "        Test test = new Test();\n" +
                "    }\n" +
                "\n" +
                "    //paste generated methodNames here\n" +
                methodList
                +
                "}\n";
        sb.append(code);
        return sb.toString();
    }

    public void addCallMethod(String methodName)
    {
        methodNames.add(methodName);
    }

    public void addMethod(String method)
    {
        methods.add(method);
    }
}
