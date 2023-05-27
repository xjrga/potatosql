package io.github.xjrga.potatosql.generator.dot;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.xjrga.potatosql.data.dto.O_select_only_names;
import io.github.xjrga.potatosql.generator.Code;
import io.github.xjrga.potatosql.generator.Table;
import io.github.xjrga.potatosql.generator.java.Java_data_type;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dot_create_doc
        implements Code {

    private StringBuilder sqlbuild;
    private Configuration cfg;
    private final String schema_name;
    private List<Table> tables;
    private List<O_select_only_names> relationships;

    public Dot_create_doc( Configuration cfg, String schema_name, List<Table> tables, List<O_select_only_names> relationships ) {
        this.cfg = cfg;
        this.schema_name = schema_name;
        this.tables = tables;
        this.relationships = relationships;
        this.sqlbuild = new StringBuilder();
        try {
            cfg.setDirectoryForTemplateLoading( new File( "resources/templates/dot" ) );
        } catch ( IOException ex ) {
        }
    }

    @Override
    public String get_code() {
        try {
            Map root = new HashMap();
            root.put( "schema_name", schema_name );
            root.put( "tables", tables );
            root.put( "relationships", relationships );
            root.put( "java_data_type", new Java_data_type() );
            Template temp = cfg.getTemplate( "dot.ftl" );
            StringWriter stringWriter = new StringWriter();
            temp.process( root, stringWriter );
            sqlbuild.append( stringWriter.toString() );
        } catch ( TemplateException | IOException ex ) {

        }
        return sqlbuild.toString();
    }

}
