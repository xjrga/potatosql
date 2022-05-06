package io.github.xjrga.potatosql.generator.java;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.xjrga.potatosql.generator.Code;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Create_generic_dao implements Code {

    private StringBuilder sqlbuild;
    private Configuration cfg;

    public Create_generic_dao(Configuration cfg) {
        this.cfg = cfg;
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
            Template temp = cfg.getTemplate("create_generic_dao_interface.ftl");
            StringWriter stringWriter = new StringWriter();
            temp.process(root, stringWriter);
            sqlbuild.append(stringWriter.toString());
        } catch (TemplateException | IOException ex) {

        }
        return sqlbuild.toString();
    }

}
