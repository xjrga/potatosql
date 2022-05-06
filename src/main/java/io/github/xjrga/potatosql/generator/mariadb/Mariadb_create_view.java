package io.github.xjrga.potatosql.generator.mariadb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Mariadb_create_view implements Code {

    private StringBuilder sqlbuild;
    private Configuration cfg;
    private Table table;

    public Mariadb_create_view(Configuration cfg, Table table) {
        this.cfg = cfg;
        this.table = table;
        this.sqlbuild = new StringBuilder();
        try {            
            cfg.setDirectoryForTemplateLoading(new File("resources/templates/mariadb"));            
        } catch (IOException ex) {
        }
    }

    @Override
    public String get_code() {
        try {
            Map root = new HashMap();
            root.put("table", table);
            Template temp = cfg.getTemplate("create_view.ftl");
            StringWriter stringWriter = new StringWriter();
            temp.process(root, stringWriter);
            sqlbuild.append(stringWriter.toString());
        } catch (TemplateException | IOException ex) {

        }
        return sqlbuild.toString();
    }

}
