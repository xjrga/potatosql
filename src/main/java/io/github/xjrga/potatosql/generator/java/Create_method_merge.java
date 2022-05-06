package io.github.xjrga.potatosql.generator.java;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Static_model;
import io.github.xjrga.potatosql.generator.Table;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Create_method_merge implements Code {

    private final StringBuilder sqlbuild;
    private final Configuration cfg;
    private final Table table;

    public Create_method_merge(Configuration cfg, Table table) {
        this.cfg = cfg;
        this.table = table;
        this.sqlbuild = new StringBuilder();
        try {
            cfg.setDirectoryForTemplateLoading(new File("resources/templates/java"));
        } catch (IOException ex) {
        }
    }

    @Override
    public String get_code() {
        try {
            Map root = new HashMap();
            root.put("table", table);
            root.put("java_data_type", new Java_data_type());
            root.put("util", Static_model.string_utils.instance());
            Template temp = cfg.getTemplate("create_method_merge.ftl");
            StringWriter stringWriter = new StringWriter();
            temp.process(root, stringWriter);
            sqlbuild.append(stringWriter.toString());
        } catch (TemplateException | IOException ex) {

        }
        return sqlbuild.toString();
    }

}
