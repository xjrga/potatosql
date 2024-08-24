package io.github.xjrga.potatosql.generator.dot;

import io.github.xjrga.potatosql.data.object.Code;
import io.github.xjrga.potatosql.functions.R1;
import io.github.xjrga.potatosql.other.Utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class Code_configuration_importer implements R1<String, List<Code>> {
  @Override
  public List<Code> apply(String path) {
    XMLInputFactory input_factory = XMLInputFactory.newInstance();
    List<Code> list = new ArrayList();
    Code code = new Code();
    try {
      if (!Utilities.validate_xml_doc("schema/config.xsd", path)) {
        return list;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    File file = new File(path);
    String start_event = "";
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      XMLEventReader eventReader = input_factory.createXMLEventReader(reader);
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        switch (event.getEventType()) {
          case XMLEvent.START_ELEMENT:
            start_event = event.asStartElement().getName().getLocalPart();
            switch (start_event) {
              case "code":
                code = new Code();
                // p( "START: " + start_event );
                break;
            }
            break;
          case XMLEvent.CHARACTERS:
            String data = event.asCharacters().getData().strip();
            if (!data.isBlank()) {
              switch (start_event) {
                case "dialect":
                  code.setDialect(data);
                  break;
                case "category":
                  code.setCategory(data);
                  break;
                case "action":
                  code.setAction(data);
                  break;
                case "name":
                  code.setName(data);
                  break;
                case "path":
                  code.setPath(data);
                  break;
                case "order":
                  code.setOrder(data);
                  break;
              }
            }
            break;
          case XMLEvent.END_ELEMENT:
            String end_event = event.asEndElement().getName().getLocalPart();
            switch (end_event) {
              case "code":
                list.add(code);
                break;
            }
            break;
        }
      }
    } catch (IOException | NumberFormatException | XMLStreamException ex) {
      ex.printStackTrace();
    }
    return list;
  }

  private void p(String txt) {
    System.out.println(txt);
  }
}
