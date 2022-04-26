package io.github.xjrga.potatosql.generator.java;

import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;

public class Data_transfer_object implements Code {

    private final Table table;
    private final Data_transfer_object_stuff dataObjectStuff;
    private final StringBuilder sqlbuild;

    public Data_transfer_object(Table table, Data_transfer_object_stuff dataObjectStuff) {
        this.table = table;
        this.dataObjectStuff = dataObjectStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String get_code() {
        if (!table.isEmpty()) {
            sqlbuild.append("public class ");
            sqlbuild.append("O_").append(table.getName());
            sqlbuild.append("{");
            sqlbuild.append("\n");
            sqlbuild.append(dataObjectStuff.getFields());
            sqlbuild.append(dataObjectStuff.getConstructor());
            sqlbuild.append(dataObjectStuff.getSetters());
            sqlbuild.append(dataObjectStuff.getGetters());
            sqlbuild.append(dataObjectStuff.getToString());
            sqlbuild.append("}");
            sqlbuild.append("}");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }

}
