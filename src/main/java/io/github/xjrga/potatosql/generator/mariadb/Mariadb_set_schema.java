package io.github.xjrga.potatosql.generator.mariadb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.xjrga.potatosql.generator.Code;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Mariadb_set_schema implements Code {

    private StringBuilder sqlbuild;
    private final String schemaName;
    private Configuration cfg;

    public Mariadb_set_schema(Configuration cfg, String schemaName) {
        this.cfg = cfg;
        this.schemaName = schemaName;
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
            root.put("schema_name", schemaName);
            Template temp = cfg.getTemplate("set_schema.ftl");
            StringWriter stringWriter = new StringWriter();
            temp.process(root, stringWriter);
            sqlbuild.append(stringWriter.toString());
        } catch (TemplateException | IOException ex) {

        }
        return sqlbuild.toString();
    }

}
