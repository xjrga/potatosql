package io.github.xjrga.potatosql.generator;

public class DropSchema implements Code {

    private StringBuilder sqlbuild;
    private String schemaName;

    public DropSchema(String schemaName) {
        this.schemaName = schemaName;
        initialize();
    }

    private void initialize() {
        sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode() {

        sqlbuild.append("DROP SCHEMA");
        sqlbuild.append(" ");
        sqlbuild.append(schemaName);
        sqlbuild.append(" ");
        sqlbuild.append("IF EXISTS CASCADE");
        sqlbuild.append(";");
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
