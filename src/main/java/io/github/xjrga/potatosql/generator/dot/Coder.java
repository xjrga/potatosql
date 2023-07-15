package io.github.xjrga.potatosql.generator.dot;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.xjrga.potatosql.data.object.Template_data;
import io.github.xjrga.potatosql.functions.R2;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Coder
        implements R2<File, Template_data, String> {
    @Override
    public String apply( File file, Template_data data ) {
        Configuration cfg = new Configuration( Configuration.VERSION_2_3_29 );
        cfg.setDefaultEncoding( "UTF-8" );
        try {
            cfg.setDirectoryForTemplateLoading( new File( file.getParent() ) );
        } catch ( IOException ex ) {
        }
        Map root = new HashMap();
        root.put( "dtype", new Syntaxtype() );
        root.put( "data", data );
        StringWriter stringWriter = new StringWriter();
        Template temp;
        try {
            temp = cfg.getTemplate( file.getName() );
            temp.process( root, stringWriter );
        } catch ( TemplateException | IOException ex ) {
        }
        return stringWriter.toString();
    }
}
