package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.object.Key;
import io.github.xjrga.potatosql.data.object.Key_pk;
import io.github.xjrga.potatosql.data.object.Keypair;
import io.github.xjrga.potatosql.data.object.Relationship;
import io.github.xjrga.potatosql.data.object.Relationship_pk;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.data.object.Table;
import io.github.xjrga.potatosql.functions.R1;
import io.github.xjrga.potatosql.other.Utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class Schema_importer implements R1<String, Schema> {
  @Override
  public Schema apply(String path) {
    XMLInputFactory input_factory = XMLInputFactory.newInstance();
    Key_pk datakey = null;
    Schema schema = null;
    Table table = null;
    Relationship_pk identifying_relationship = null;
    Keypair identifying_relationship_keypair = null;
    Relationship_pk nonidentifying_relationship = null;
    Keypair nonidentifying_relationship_keypair = null;
    Key_pk primarykey = null;
    Relationship relationship = null;
    Key tablekey = null;
    try {
      if (!Utilities.validate_xml_doc("schema/potatosql.xsd", path)) {
        return schema;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    File file = new File(path);
    String start_event = "";
    String data_event = "";
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      XMLEventReader eventReader = input_factory.createXMLEventReader(reader);
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        switch (event.getEventType()) {
          case XMLEvent.START_ELEMENT:
            start_event = event.asStartElement().getName().getLocalPart();
            switch (start_event) {
              case "data_key":
                data_event = start_event;
                datakey = new Key_pk();
                // p( "START: " + start_event );
                break;
              case "database_schema":
                data_event = start_event;
                schema = new Schema();
                // p( "START: " + start_event );
                break;
              case "database_table":
                data_event = start_event;
                table = new Table();
                // p( "START: " + start_event );
                break;
              case "identifying_relationship":
                data_event = start_event;
                identifying_relationship = new Relationship_pk();
                // p( "START: " + start_event );
                break;
              case "identifying_relationship_key_pair":
                data_event = start_event;
                identifying_relationship_keypair = new Keypair();
                // p( "START: " + start_event );
                break;
              case "nonidentifying_relationship":
                data_event = start_event;
                nonidentifying_relationship = new Relationship_pk();
                // p( "START: " + start_event );
                break;
              case "nonidentifying_relationship_key_pair":
                data_event = start_event;
                nonidentifying_relationship_keypair = new Keypair();
                // p( "START: " + start_event );
                break;
              case "primary_key":
                data_event = start_event;
                primarykey = new Key_pk();
                // p( "START: " + start_event );
                break;
              case "relationship":
                data_event = start_event;
                relationship = new Relationship();
                // p( "START: " + start_event );
                break;
              case "table_key":
                data_event = start_event;
                tablekey = new Key();
                // p( "START: " + start_event );
                break;
            }
            break;
          case XMLEvent.CHARACTERS:
            String data = event.asCharacters().getData().strip();
            // *****
            if (!data.isBlank()) {
              switch (start_event) {
                case "datatype_id":
                  switch (data_event) {
                    case "table_key":
                      tablekey.setDatatype_id(Integer.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "is_foreign_key":
                  switch (data_event) {
                    case "table_key":
                      tablekey.setIs_foreign_key(Boolean.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "is_identifying":
                  switch (data_event) {
                    case "relationship":
                      relationship.setIs_identifying(Boolean.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "key_id":
                  switch (data_event) {
                    case "data_key":
                      datakey.setKey_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "primary_key":
                      primarykey.setKey_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "table_key":
                      tablekey.setKey_id(Integer.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "key_id_child":
                  switch (data_event) {
                    case "identifying_relationship_key_pair":
                      identifying_relationship_keypair.setKey_id_child(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "nonidentifying_relationship_key_pair":
                      nonidentifying_relationship_keypair.setKey_id_child(Integer.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "key_id_parent":
                  switch (data_event) {
                    case "identifying_relationship_key_pair":
                      identifying_relationship_keypair.setKey_id_parent(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "nonidentifying_relationship_key_pair":
                      nonidentifying_relationship_keypair.setKey_id_parent(Integer.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "key_name":
                  switch (data_event) {
                    case "table_key":
                      tablekey.setKey_name(data);
                      // p( data );
                      break;
                  }
                  break;
                case "key_order":
                  switch (data_event) {
                    case "table_key":
                      tablekey.setKey_order(Integer.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "is_primary_key":
                  switch (data_event) {
                    case "table_key":
                      tablekey.setIs_primary_key(Boolean.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "relationship_id":
                  switch (data_event) {
                    case "identifying_relationship":
                      identifying_relationship.setRelationship_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "identifying_relationship_key_pair":
                      identifying_relationship_keypair.setRelationship_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "nonidentifying_relationship":
                      nonidentifying_relationship.setRelationship_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "nonidentifying_relationship_key_pair":
                      nonidentifying_relationship_keypair.setRelationship_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "relationship":
                      relationship.setRelationship_id(Integer.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "schema_id":
                  switch (data_event) {
                    case "data_key":
                      datakey.setSchema_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "database_schema":
                      schema.setSchema_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "database_table":
                      table.setSchema_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "identifying_relationship":
                      identifying_relationship.setSchema_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "identifying_relationship_key_pair":
                      identifying_relationship_keypair.setSchema_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "nonidentifying_relationship":
                      nonidentifying_relationship.setSchema_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "nonidentifying_relationship_key_pair":
                      nonidentifying_relationship_keypair.setSchema_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "primary_key":
                      primarykey.setSchema_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "relationship":
                      relationship.setSchema_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "table_key":
                      tablekey.setSchema_id(Integer.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "schema_name":
                  switch (data_event) {
                    case "database_schema":
                      schema.setSchema_name(data);
                      // p( data );
                      break;
                  }
                  break;
                case "table_id":
                  switch (data_event) {
                    case "data_key":
                      datakey.setTable_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "database_table":
                      table.setTable_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "primary_key":
                      primarykey.setTable_id(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "table_key":
                      tablekey.setTable_id(Integer.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "table_id_child":
                  switch (data_event) {
                    case "identifying_relationship":
                      identifying_relationship.setTable_id_child(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "identifying_relationship_key_pair":
                      identifying_relationship_keypair.setTable_id_child(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "nonidentifying_relationship":
                      nonidentifying_relationship.setTable_id_child(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "nonidentifying_relationship_key_pair":
                      nonidentifying_relationship_keypair.setTable_id_child(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "relationship":
                      relationship.setTable_id_child(Integer.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "table_id_parent":
                  switch (data_event) {
                    case "identifying_relationship":
                      identifying_relationship.setTable_id_parent(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "identifying_relationship_key_pair":
                      identifying_relationship_keypair.setTable_id_parent(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "nonidentifying_relationship":
                      nonidentifying_relationship.setTable_id_parent(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "nonidentifying_relationship_key_pair":
                      nonidentifying_relationship_keypair.setTable_id_parent(Integer.valueOf(data));
                      // p( data );
                      break;
                    case "relationship":
                      relationship.setTable_id_parent(Integer.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
                case "table_name":
                  switch (data_event) {
                    case "database_table":
                      table.setTable_name(data);
                      // p( data );
                      break;
                  }
                  break;
                case "is_dependent":
                  switch (data_event) {
                    case "database_table":
                      table.setIs_dependent(Boolean.valueOf(data));
                      // p( data );
                      break;
                  }
                  break;
              }
            }
            // *****
            break;
          case XMLEvent.END_ELEMENT:
            String end_event = event.asEndElement().getName().getLocalPart();
            switch (end_event) {
              case "data_key":
                Datakeys_ins datakeys_inserter = new Datakeys_ins();
                datakeys_inserter.apply(datakey);
                // p( data_key.print() );
                // p( "END: " + end_event );
                break;
              case "database_schema":
                Schema_ins schema_inserter = new Schema_ins();
                schema_inserter.apply(schema);
                // p( schema.print() );
                // p( "END: " + end_event );
                break;
              case "database_table":
                Table_ins table_inserter = new Table_ins();
                table_inserter.apply(table);
                // p( table.print() );
                // p( "END: " + end_event );
                break;
              case "identifying_relationship":
                Identifying_relationship_ins identifying_relationship_inserter =
                    new Identifying_relationship_ins();
                identifying_relationship_inserter.apply(identifying_relationship);
                // p( identifying_relationship.print() );
                // p( "END: " + end_event );
                break;
              case "identifying_relationship_key_pair":
                Identifying_relationship_keypair_ins identifying_relatioship_key_pair_inserter =
                    new Identifying_relationship_keypair_ins();
                identifying_relatioship_key_pair_inserter.apply(identifying_relationship_keypair);
                // p( identifying_relationship_key_pair.print() );
                // p( "END: " + end_event );
                break;
              case "nonidentifying_relationship":
                Nonidentifying_relationship_ins nonidentifying_relationship_inserter =
                    new Nonidentifying_relationship_ins();
                nonidentifying_relationship_inserter.apply(nonidentifying_relationship);
                // p( nonidentifying_relationship.print() );
                // p( "END: " + end_event );
                break;
              case "nonidentifying_relationship_key_pair":
                Nonidentifying_relationship_keypair_ins
                    nonidentifying_relatioship_key_pair_inserter =
                        new Nonidentifying_relationship_keypair_ins();
                nonidentifying_relatioship_key_pair_inserter.apply(
                    nonidentifying_relationship_keypair);
                // p( nonidentifying_relationship_key_pair.print() );
                // p( "END: " + end_event );
                break;
              case "primary_key":
                Primarykeys_ins primarykeys_inserter = new Primarykeys_ins();
                primarykeys_inserter.apply(primarykey);
                // p( primary_key.print() );
                // p( "END: " + end_event );
                break;
              case "relationship":
                Relationship_ins relationship_inserter = new Relationship_ins();
                relationship_inserter.apply(relationship);
                // p( relationship.print() );
                // p( "END: " + end_event );
                break;
              case "table_key":
                Tablekeys_ins tablekeys_inserter = new Tablekeys_ins();
                tablekeys_inserter.apply(tablekey);
                // p( tablekey.print() );
                // p( "END: " + end_event );
                break;
            }
            break;
        }
      }
    } catch (IOException | NumberFormatException | XMLStreamException ex) {
      ex.printStackTrace();
    }
    return schema;
  }
  //    public void p( String txt ) {
  //        System.out.println( txt );
  //    }
}
