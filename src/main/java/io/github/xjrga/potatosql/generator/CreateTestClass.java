package io.github.xjrga.potatosql.generator;

public class CreateTestClass implements Code {

    public CreateTestClass() {
    }

    @Override
    public String getCode() {
        StringBuilder sb = new StringBuilder();
        String code = "import java.sql.SQLException;\n"
                + "\n"
                + "\n"
                + "\n"
                + "public class TestClass extends TestTemplate {\n"
                + "\n"
                + "    @Override\n"
                + "    String getName() {\n"
                + "        throw new UnsupportedOperationException(\"Not supported yet.\");\n"
                + "    }\n"
                + "\n"
                + "    @Override\n"
                + "    void test() throws SQLException {\n"
                + "        throw new UnsupportedOperationException(\"Not supported yet.\");\n"
                + "    }\n"
                + "\n"
                + "}\n"
                + "";
        sb.append(code);
        sb.append("\n");
        sb.append("\n");
        return sb.toString();
    }

}
