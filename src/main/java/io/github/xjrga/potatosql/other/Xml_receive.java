/*
 * Copyright (C) 2021 Jorge R Garcia de Alba &lt;jorge.r.garciadealba@gmail.com&gt;
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package io.github.xjrga.potatosql.other;

import io.github.xjrga.potatosql.data.Dblink;
import io.github.xjrga.potatosql.data.dto.O_key;
import io.github.xjrga.potatosql.data.dto.O_relationship;
import io.github.xjrga.potatosql.data.dto.O_relationship_key_pair;
import io.github.xjrga.potatosql.data.dto.O_schema;
import io.github.xjrga.potatosql.data.dto.O_table;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/**
 * This class allows import and export of color data in xml format
 *
 * @author Jorge R Garcia de Alba &lt;jorge.r.garciadealba@gmail.com&gt;
 */
public class Xml_receive {

    private final XMLInputFactory inputFactory;
    private XMLEventReader eventReader;
    private String start_event;
    private String end_event;
    private String main_event;
    private final Dblink dblink;

    /**
     * Constructs DataTransfer class
     */
    public Xml_receive() {
        inputFactory = XMLInputFactory.newInstance();
        dblink = new Dblink();
    }

    public void import_potatosql_data(String path) {
        try {
            if (Utilities.validate_xml_doc("resources/schemas/potatosql.xsd", path)) {
                File file = new File(path);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                eventReader = inputFactory.createXMLEventReader(reader);
                O_schema schema = null;
                O_table table = null;
                O_relationship relationship = null;
                O_key key = null;
                O_relationship_key_pair key_pair = null;
                while (eventReader.hasNext()) {
                    XMLEvent event = eventReader.nextEvent();
                    switch (event.getEventType()) {
                        case XMLEvent.START_ELEMENT:
                            start_event = event.asStartElement().getName().getLocalPart();
                            switch (start_event) {
                                case "schema":
                                    schema = new O_schema();
                                    main_event = start_event;
                                    break;
                                case "table":
                                    table = new O_table();
                                    main_event = start_event;
                                    break;
                                case "relationship":
                                    relationship = new O_relationship();
                                    main_event = start_event;
                                    break;
                                case "key":
                                    key = new O_key();
                                    main_event = start_event;
                                    break;
                                case "key_pair":
                                    key_pair = new O_relationship_key_pair();
                                    main_event = start_event;
                                    break;
                            }
                            break;
                        case XMLEvent.CHARACTERS:
                            String data = event.asCharacters().getData().strip();
                            if (!data.isBlank()) {
                                switch (start_event) {
                                    case "schema_id":
                                        switch (main_event) {
                                            case "schema":
                                                schema.setSchema_id(Integer.valueOf(data));
                                                break;
                                            case "table":
                                                table.setSchema_id(Integer.valueOf(data));
                                                break;
                                            case "relationship":
                                                relationship.setSchema_id(Integer.valueOf(data));
                                                break;
                                            case "key":
                                                key.setSchema_id(Integer.valueOf(data));
                                                break;
                                            case "key_pair":
                                                key_pair.setSchema_id(Integer.valueOf(data));
                                                break;
                                        }
                                        break;
                                    case "schema_name":
                                        schema.setSchema_name(data);
                                        break;
                                    case "table_id":
                                        switch (main_event) {
                                            case "table":
                                                table.setTable_id(Integer.valueOf(data));
                                                break;
                                            case "key":
                                                key.setTable_id(Integer.valueOf(data));
                                                break;
                                        }
                                        break;
                                    case "table_name":
                                        table.setTable_name(data);
                                        break;
                                    case "parent_table_id":
                                        switch (main_event) {
                                            case "relationship":
                                                relationship.setParent_table_id(Integer.valueOf(data));
                                                break;
                                            case "key_pair":
                                                key_pair.setParent_table_id(Integer.valueOf(data));
                                                break;
                                        }
                                        break;
                                    case "child_table_id":
                                        switch (main_event) {
                                            case "relationship":
                                                relationship.setChild_table_id(Integer.valueOf(data));
                                                break;
                                            case "key_pair":
                                                key_pair.setChild_table_id(Integer.valueOf(data));
                                                break;
                                        }
                                        break;
                                    case "relationship_id":
                                        switch (main_event) {
                                            case "relationship":
                                                relationship.setRelationship_id(Integer.valueOf(data));
                                                break;
                                            case "key_pair":
                                                key_pair.setRelationship_id(Integer.valueOf(data));
                                                break;
                                        }
                                        break;
                                    case "relationship_type_id":
                                        relationship.setRelationship_type_id(Integer.valueOf(data));
                                        break;
                                    case "key_id":
                                        key.setTable_key_id(Integer.valueOf(data));
                                        break;
                                    case "key_name":
                                        key.setTable_key_name(data);
                                        break;
                                    case "key_label":
                                        key.setTable_key_label(data);
                                        break;
                                    case "key_is_pk":
                                        key.setTable_key_is_pk(Boolean.valueOf(data));
                                        break;
                                    case "key_type_id":
                                        key.setTable_key_type_id(Integer.valueOf(data));
                                        break;
                                    case "key_order":
                                        key.setTable_key_order(Integer.valueOf(data));
                                        break;
                                    case "parent_key_id":
                                        key_pair.setParent_key_id(Integer.valueOf(data));
                                        break;
                                    case "child_key_id":
                                        key_pair.setChild_key_id(Integer.valueOf(data));
                                        break;
                                }
                            }
                            break;
                        case XMLEvent.END_ELEMENT:
                            end_event = event.asEndElement().getName().getLocalPart();
                            switch (end_event) {
                                case "schema":
                                    dblink.schema_insert(schema);
                                    break;
                                case "table":
                                    dblink.table_insert(table);
                                    break;
                                case "relationship":
                                    dblink.relationship_insert(relationship);
                                    break;
                                case "key":
                                    dblink.key_insert(key);
                                    break;
                                case "key_pair":
                                    dblink.relationship_key_pair_insert(key_pair);
                                    break;
                            }
                            break;
                    }
                }
                reader.close();
            } else {

            }
        } catch (IOException | NumberFormatException | XMLStreamException ex) {
            ex.printStackTrace();
        }
    }
}
