package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;

public class CreateSchema implements Code {

    private StringBuilder sqlbuild;
    private final String schemaName;

    public CreateSchema(String schemaName) {
        this.schemaName = schemaName;
        initialize();
    }

    private void initialize() {
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        sqlbuild.append("CREATE SCHEMA");
        sqlbuild.append(" ");
        sqlbuild.append(schemaName);
        sqlbuild.append(";");
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
