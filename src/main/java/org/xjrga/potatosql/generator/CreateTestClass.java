package org.xjrga.potatosql.generator;

public class CreateTestClass implements Code
{
    public CreateTestClass()
    {
    }

    @Override
    public String getCode()
    {
        StringBuilder sb = new StringBuilder();
        String code =
                "import java.io.IOException;\n" +
                "import java.nio.file.Files;\n" +
                "import java.nio.file.Paths;\n" +
                "import java.sql.*;\n" +
                "import java.text.ParseException;\n" +
                "import java.text.SimpleDateFormat;\n" +
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
                "            connection = DriverManager.getConnection(\"jdbc:hsqldb:hsql://localhost/database\", \"SA\", \"\");\n" +
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
                "        }\n" +
                "        finally\n" +
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
                "\n" +
                "    //paste generated methodNames here\n" +
                "\n" +
                "\n" +
                "    public static void main(String[] args)\n" +
                "    {\n" +
                "        Test test = new Test();\n" +
                "    }\n" +
                "\n" +
                "    private Date createDate(String dateastxt) throws ParseException\n" +
                "    {\n" +
                "        //\"2019/09/06 19:00:00\"\n" +
                "        long millis = getMillis(dateastxt);\n" +
                "        Date date = new Date(millis);\n" +
                "        return date;\n" +
                "    }\n" +
                "\n" +
                "    private Time createTime(String dateastxt) throws ParseException\n" +
                "    {\n" +
                "        //\"2019/09/06 19:00:00\"\n" +
                "        long millis = getMillis(dateastxt);\n" +
                "        Time time = new Time(millis);\n" +
                "        return time;\n" +
                "    }\n" +
                "\n" +
                "    private Timestamp createTimestamp(String dateastxt) throws ParseException\n" +
                "    {\n" +
                "        //\"2019/09/06 19:00:00\"\n" +
                "        long millis = getMillis(dateastxt);\n" +
                "        Timestamp timestamp = new Timestamp(millis);\n" +
                "        return timestamp;\n" +
                "    }\n" +
                "\n" +
                "    private long getMillis(String timestamp) throws ParseException\n" +
                "    {\n" +
                "        SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy/MM/dd HH:mm:ss\");\n" +
                "        return sdf.parse(timestamp).getTime();\n" +
                "    }\n" +
                "\n" +
                "    private Blob createBlob(String filepath) throws SQLException, IOException\n" +
                "    {\n" +
                "        Blob blob = connection.createBlob();\n" +
                "        byte[] bytes = Files.readAllBytes(Paths.get(filepath));\n" +
                "        blob.setBytes(1, bytes);\n" +
                "        return blob;\n" +
                "    }\n" +
                "\n" +
                "    private Clob createClob(String filepath) throws SQLException, IOException\n" +
                "    {\n" +
                "        Clob clob = connection.createClob();\n" +
                "        byte[] bytes = Files.readAllBytes(Paths.get(filepath));\n" +
                "        clob.setString(1, new String(bytes));\n" +
                "        return clob;\n" +
                "    }\n" +
                "}\n" +
                "\n";
        sb.append(code);
        return sb.toString();
    }
}
