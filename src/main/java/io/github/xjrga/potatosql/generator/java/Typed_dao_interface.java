package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;
import io.github.xjrga.potatosql.other.Utilities;

public class Typed_dao_interface implements Code {

    private final Table table;
    private final StringBuilder sqlbuild;

    public Typed_dao_interface(Table table) {
        this.table = table;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        String table_name = table.getName();
        String data_transfer_object = "O_" + table.getName();
        sqlbuild.append("public interface ");
        sqlbuild.append(Utilities.capitalize(table_name));
        sqlbuild.append("_dao extends Generic_dao<");
        sqlbuild.append(data_transfer_object).append("> {}\n\n");
        return sqlbuild.toString();
    }

}
