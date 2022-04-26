package io.github.xjrga.potatosql.other;

import io.github.xjrga.potatosql.data.Dblink;
import io.github.xjrga.potatosql.data.dto.O_schema;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Xml_send {

    public Xml_send(Dblink dbLink, O_schema schema) {
        try {
            StringBuilder path = new StringBuilder();
            path.append("model/");
            path.append(schema.getSchema_id());
            path.append(".xml");
            String doc = dbLink.export_schema(schema);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()));
            writer.write(Utilities.format_xml_doc(doc));
            writer.close();
        } catch (IOException ex) {

        }
    }
}
