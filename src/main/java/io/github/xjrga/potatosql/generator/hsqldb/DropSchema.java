package io.github.xjrga.potatosql.generator.hsqldb;

import io.github.xjrga.potatosql.generator.Code;

public class DropSchema implements Code {

    private StringBuilder sqlbuild;
    private final String schemaName;

    public DropSchema(String schemaName) {
        this.schemaName = schemaName;
        initialize();
    }

    private void initialize() {
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
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
