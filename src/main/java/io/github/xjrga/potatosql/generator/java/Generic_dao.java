package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;

public class Generic_dao implements Code {

    private final StringBuilder sqlbuild;

    public Generic_dao() {
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        sqlbuild.append("package x;\n"
                + "import java.sql.SQLException;\n"
                + "import java.util.List;\n"
                + "public interface Generic_dao<T>{\n"
                + "    void insert(T instance) throws SQLException;    \n"
                + "    void update(T instance) throws SQLException;\n"
                + "    void delete(T instance) throws SQLException;\n"
                + "    List<T> find(T instance) throws SQLException;\n"
                + "    List<T> find_all(T instance) throws SQLException;\n"
                + "    Integer count(T instance) throws SQLException;"
                + "}\n\n");
        return sqlbuild.toString();
    }

}
