package io.github.xjrga.potatosql.generator.mariadb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.xjrga.potatosql.data.dto.O_select_only_names;
import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.other.Filter_00;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mariadb_create_relationship implements Code {

    private StringBuilder sqlbuild;
    private Configuration cfg;
    private final List<O_select_only_names> list;

    public Mariadb_create_relationship(Configuration cfg, List<O_select_only_names> list) {
        this.cfg = cfg;
        this.list = list;
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
            Filter_00 filter_00 = new Filter_00(list);
            root.put("relationships", filter_00.getFixed_list());
            Template temp = cfg.getTemplate("create_relationship.ftl");
            StringWriter stringWriter = new StringWriter();
            temp.process(root, stringWriter);
            sqlbuild.append(stringWriter.toString());
        } catch (TemplateException | IOException ex) {

        }
        return sqlbuild.toString();
    }

}
