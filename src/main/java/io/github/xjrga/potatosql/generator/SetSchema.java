package io.github.xjrga.potatosql.generator;

public class SetSchema implements Code {

    private StringBuilder sqlbuild;
    private String schemaName;

    public SetSchema(String schemaName) {
        this.schemaName = schemaName;
        initialize();
    }

    private void initialize() {
        sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode() {
        sqlbuild.append("SET SCHEMA");
        sqlbuild.append(" ");
        sqlbuild.append(schemaName);
        sqlbuild.append(";");
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
