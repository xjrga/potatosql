package io.github.xjrga.potatosql.generator;

import freemarker.template.Configuration;
import io.github.xjrga.potatosql.data.Dblink;
import io.github.xjrga.potatosql.data.dto.O_schema;
import io.github.xjrga.potatosql.data.dto.O_select_only_names;
import io.github.xjrga.potatosql.data.dto.O_table;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_function_count;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_procedure_delete;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_procedure_delete_all;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_procedure_insert;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_procedure_merge;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_procedure_select;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_procedure_select_all;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_procedure_update;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_relationship;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_schema;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_statement_count;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_statement_delete;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_statement_delete_all;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_statement_insert;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_statement_insert_using_select;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_statement_merge;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_statement_select;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_statement_select_all;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_statement_update;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_table;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_table_with_select;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_trigger_row_level_after_delete_event;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_trigger_row_level_after_insert_event;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_trigger_row_level_after_update_event;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_trigger_row_level_before_delete_event;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_trigger_row_level_before_insert_event;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_trigger_row_level_before_update_event;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_trigger_statement_level_after_delete_event;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_trigger_statement_level_after_insert_event;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_trigger_statement_level_after_update_event;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_create_view;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_drop_schema;
import io.github.xjrga.potatosql.generator.hsqldb.Hsqldb_set_schema;
import io.github.xjrga.potatosql.generator.java.Create_data_transfer_object;
import io.github.xjrga.potatosql.generator.java.Create_generic_dao;
import io.github.xjrga.potatosql.generator.java.Create_method_count;
import io.github.xjrga.potatosql.generator.java.Create_method_count_for_hsqldb;
import io.github.xjrga.potatosql.generator.java.Create_method_delete;
import io.github.xjrga.potatosql.generator.java.Create_method_delete_all;
import io.github.xjrga.potatosql.generator.java.Create_method_insert;
import io.github.xjrga.potatosql.generator.java.Create_method_merge;
import io.github.xjrga.potatosql.generator.java.Create_method_print_count;
import io.github.xjrga.potatosql.generator.java.Create_method_print_select;
import io.github.xjrga.potatosql.generator.java.Create_method_print_select_all;
import io.github.xjrga.potatosql.generator.java.Create_method_select;
import io.github.xjrga.potatosql.generator.java.Create_method_select_all;
import io.github.xjrga.potatosql.generator.java.Create_method_update;
import io.github.xjrga.potatosql.generator.java.Create_test_class;
import io.github.xjrga.potatosql.generator.java.Create_typed_dao_interface;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_function_count;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_procedure_delete;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_procedure_delete_all;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_procedure_insert;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_procedure_merge;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_procedure_select;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_procedure_select_all;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_procedure_update;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_relationship;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_schema;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_statement_count;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_statement_delete;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_statement_delete_all;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_statement_insert;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_statement_insert_using_select;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_statement_merge;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_statement_select;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_statement_select_all;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_statement_update;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_table;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_table_with_select;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_trigger_row_level_after_delete_event;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_trigger_row_level_after_insert_event;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_trigger_row_level_after_update_event;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_trigger_row_level_before_delete_event;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_trigger_row_level_before_insert_event;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_trigger_row_level_before_update_event;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_create_view;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_drop_schema;
import io.github.xjrga.potatosql.generator.mariadb.Mariadb_set_schema;
import java.util.List;

public class DialectBuilder implements Code {

    private final Dblink dblink;
    private boolean is_generate_generic_data_access_object_selected;
    private boolean is_generate_data_access_object_option_selected;
    private boolean is_generate_data_transfer_object_option_selected;
    private boolean is_statement_level_after_insert_event_trigger_selected;
    private boolean is_statement_level_after_update_event_trigger_selected;
    private boolean is_statement_level_after_delete_event_trigger_selected;
    private boolean is_row_level_before_insert_event_trigger_selected;
    private boolean is_row_level_before_update_event_trigger_selected;
    private boolean is_row_level_before_delete_event_trigger_selected;
    private boolean is_row_level_after_insert_event_trigger_selected;
    private boolean is_row_level_after_update_event_trigger_selected;
    private boolean is_row_level_after_delete_event_trigger_selected;
    private boolean is_view_selected;
    private boolean is_test_class_selected;
    private boolean is_tables_and_relationships_selected;
    private boolean is_insert_statement_selected;
    private boolean is_update_statement_selected;
    private boolean is_delete_statement_selected;
    private boolean is_merge_statement_selected;
    private boolean is_select_statement_selected;
    private boolean is_delete_all_statement_selected;
    private boolean is_select_all_statement_selected;
    private boolean is_count_statement_selected;
    private boolean is_create_table_with_select_selected;
    private boolean is_insert_using_select_statement_selected;
    private boolean is_insert_procedure_selected;
    private boolean is_update_procedure_selected;
    private boolean is_delete_procedure_selected;
    private boolean is_merge_procedure_selected;
    private boolean is_select_procedure_selected;
    private boolean is_delete_all_procedure_selected;
    private boolean is_select_all_procedure_selected;
    private boolean is_count_function_selected;
    private boolean is_insert_method_selected;
    private boolean is_update_method_selected;
    private boolean is_delete_method_selected;
    private boolean is_merge_method_selected;
    private boolean is_select_method_selected;
    private boolean is_delete_all_method_selected;
    private boolean is_select_all_method_selected;
    private boolean is_count_function_method_selected;
    private boolean is_print_select_method_selected;
    private boolean is_print_select_all_method_selected;
    private boolean is_print_function_count_method_selected;
    private boolean is_hsqldb_selected;
    private boolean is_mariadb_selected;
    private Create_test_class create_test_class;
    private String schema_name;
    private O_schema schema;
    private List<O_table> table_list;
    private final Configuration cfg;

    public DialectBuilder(Dblink dbLink) {
        this.dblink = dbLink;
        cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDefaultEncoding("UTF-8");
    }

    @Override
    public String get_code() {
        StringBuilder sb = new StringBuilder();

        if (is_hsqldb_selected) {
            sb.append(get_sql_code_generated_for_hsqldb());
            sb.append(get_java_code_generated());

        } else if (is_mariadb_selected) {
            sb.append(get_sql_code_generated_for_mariadb());
            sb.append(get_java_code_generated());
        }
        return sb.toString();
    }

    public void set_oschema(O_schema schema) {
        this.schema = schema;
        this.schema_name = schema.getSchema_name();
    }

    public void is_statement_level_after_update_event_trigger_selected(boolean triggerStatementAfterUpdate) {
        is_statement_level_after_update_event_trigger_selected = triggerStatementAfterUpdate;
    }

    public void is_statement_level_after_delete_event_trigger_selected(boolean triggerStatementAfterDelete) {
        is_statement_level_after_delete_event_trigger_selected = triggerStatementAfterDelete;
    }

    public void is_row_level_before_insert_event_trigger_selected(boolean triggerRowBeforeInsert) {
        is_row_level_before_insert_event_trigger_selected = triggerRowBeforeInsert;
    }

    public void is_row_level_before_update_event_trigger_selected(boolean triggerRowBeforeUpdate) {
        is_row_level_before_update_event_trigger_selected = triggerRowBeforeUpdate;
    }

    public void is_row_level_before_delete_event_trigger_selected(boolean triggerRowBeforeDelete) {
        is_row_level_before_delete_event_trigger_selected = triggerRowBeforeDelete;
    }

    public void is_row_level_after_insert_event_trigger_selected(boolean triggerRowAfterInsert) {
        is_row_level_after_insert_event_trigger_selected = triggerRowAfterInsert;
    }

    public void is_row_level_after_update_event_trigger_selected(boolean triggerRowAfterUpdate) {
        is_row_level_after_update_event_trigger_selected = triggerRowAfterUpdate;
    }

    public void is_row_level_after_delete_event_trigger_selected(boolean triggerRowAfterDelete) {
        is_row_level_after_delete_event_trigger_selected = triggerRowAfterDelete;
    }

    public void is_view_selected(boolean views) {
        is_view_selected = views;
    }

    public void is_test_class_selected(boolean testClass) {
        is_test_class_selected = testClass;
    }

    public void is_tables_and_relationships_selected(boolean tables) {
        is_tables_and_relationships_selected = tables;
    }

    public void is_insert_statement_selected(boolean stmtInsert) {
        is_insert_statement_selected = stmtInsert;
    }

    public void is_update_statement_selected(boolean stmtUpdate) {
        is_update_statement_selected = stmtUpdate;
    }

    public void is_delete_statement_selected(boolean stmtDelete) {
        is_delete_statement_selected = stmtDelete;
    }

    public void is_merge_statement_selected(boolean stmtMerge) {
        is_merge_statement_selected = stmtMerge;
    }

    public void is_select_statement_selected(boolean stmtSelect) {
        is_select_statement_selected = stmtSelect;
    }

    public void is_delete_all_statement_selected(boolean stmtDeleteAll) {
        is_delete_all_statement_selected = stmtDeleteAll;
    }

    public void is_select_all_statement_selected(boolean stmtSelectAll) {
        is_select_all_statement_selected = stmtSelectAll;
    }

    public void is_count_statement_selected(boolean stmtCount) {
        is_count_statement_selected = stmtCount;
    }

    public void is_create_table_with_select_selected(boolean stmtCreateSelect) {
        is_create_table_with_select_selected = stmtCreateSelect;
    }

    public void is_insert_procedure_selected(boolean procInsert) {
        is_insert_procedure_selected = procInsert;
    }

    public void is_update_procedure_selected(boolean procUpdate) {
        is_update_procedure_selected = procUpdate;
    }

    public void is_delete_procedure_selected(boolean procDelete) {
        is_delete_procedure_selected = procDelete;
    }

    public void is_merge_procedure_selected(boolean procMerge) {
        is_merge_procedure_selected = procMerge;
    }

    public void is_select_procedure_selected(boolean procSelect) {
        is_select_procedure_selected = procSelect;
    }

    public void is_delete_all_procedure_selected(boolean procDeleteAll) {
        is_delete_all_procedure_selected = procDeleteAll;
    }

    public void is_select_all_procedure_selected(boolean procSelectAll) {
        is_select_all_procedure_selected = procSelectAll;
    }

    public void is_count_function_selected(boolean functionCount) {
        is_count_function_selected = functionCount;
    }

    public void is_insert_method_selected(boolean methodInsert) {
        is_insert_method_selected = methodInsert;
    }

    public void is_update_method_selected(boolean methodUpdate) {
        is_update_method_selected = methodUpdate;
    }

    public void is_delete_method_selected(boolean methodDelete) {
        is_delete_method_selected = methodDelete;
    }

    public void is_merge_method_selected(boolean methodMerge) {
        is_merge_method_selected = methodMerge;
    }

    public void is_select_method_selected(boolean methodSelect) {
        is_select_method_selected = methodSelect;
    }

    public void is_delete_all_method_selected(boolean methodDeleteAll) {
        is_delete_all_method_selected = methodDeleteAll;
    }

    public void is_select_all_method_selected(boolean methodSelectAll) {
        is_select_all_method_selected = methodSelectAll;
    }

    public void is_count_function_method_selected(boolean methodFunctionCount) {
        is_count_function_method_selected = methodFunctionCount;
    }

    public void is_statement_level_after_insert_event_trigger_selected(boolean triggerStatementAfterInsert) {
        is_statement_level_after_insert_event_trigger_selected = triggerStatementAfterInsert;
    }

    public void is_generate_data_transfer_object_option_selected(boolean dataObject) {
        is_generate_data_transfer_object_option_selected = dataObject;
    }

    public void is_generate_generic_data_access_object_selected(boolean generic_data_access_object) {
        is_generate_generic_data_access_object_selected = generic_data_access_object;
    }

    public void is_generate_data_access_object_option_selected(boolean data_access_object) {
        is_generate_data_access_object_option_selected = data_access_object;
    }

    public void is_mariadb_selected(boolean mariadb) {
        is_mariadb_selected = mariadb;
    }

    public void is_hsqldb_selected(boolean hsqldb) {
        is_hsqldb_selected = hsqldb;
    }

    public void is_print_select_all_method_selected(boolean selected) {
        is_print_select_all_method_selected = selected;
    }

    public void is_print_select_method_selected(boolean selected) {
        is_print_select_method_selected = selected;
    }

    public void is_print_function_count_method_selected(boolean selected) {
        is_print_function_count_method_selected = selected;
    }

    public void is_insert_using_select_statement_selected(boolean stmtInsertSelect) {
        is_insert_using_select_statement_selected = stmtInsertSelect;
    }

    public void setSchema_name(String schema_name) {
        this.schema_name = schema_name;
    }

    public String getSchema_name() {
        return schema_name;
    }

    public void set_otables(List<O_table> list) {
        this.table_list = list;
    }

    public String get_sql_code_generated_for_hsqldb() {
        StringBuilder sb = new StringBuilder();
        TableMaker tableMaker = new TableMaker(dblink);
        if (is_tables_and_relationships_selected) {
            Hsqldb_drop_schema hsqldb_drop_schema = new Hsqldb_drop_schema(cfg, getSchema_name());
            sb.append(hsqldb_drop_schema.get_code());
            Hsqldb_create_schema hsqldb_create_schema = new Hsqldb_create_schema(cfg, getSchema_name());
            sb.append(hsqldb_create_schema.get_code());
            Hsqldb_set_schema hsqldb_set_schema = new Hsqldb_set_schema(cfg, getSchema_name());
            sb.append(hsqldb_set_schema.get_code());
            table_list.forEach(element -> {
                Integer tableId = element.getTable_id();
                Table table = tableMaker.get_table(schema, tableId);
                Hsqldb_create_table hsqldb_create_table = new Hsqldb_create_table(cfg, table);
                sb.append(hsqldb_create_table.get_code());
            });
            List<O_select_only_names> list = (List<O_select_only_names>) dblink.relationship_select_only_names(schema);
            Hsqldb_create_relationship hsqldb_create_relationship = new Hsqldb_create_relationship(cfg, list);
            sb.append(hsqldb_create_relationship.get_code());
        }
        table_list.forEach(element -> {
            Integer tableId = element.getTable_id();
            Table table = tableMaker.get_table(schema, tableId);
            if (is_view_selected) {
                Hsqldb_create_view hsqldb_create_view = new Hsqldb_create_view(cfg, table);
                sb.append(hsqldb_create_view.get_code());
            }
            if (is_create_table_with_select_selected) {
                Hsqldb_create_table_with_select hsqldb_create_table_with_select = new Hsqldb_create_table_with_select(cfg, table);
                sb.append(hsqldb_create_table_with_select.get_code());
            }
            if (is_insert_procedure_selected) {
                Hsqldb_create_procedure_insert hsqldb_create_procedure_insert = new Hsqldb_create_procedure_insert(cfg, table);
                sb.append(hsqldb_create_procedure_insert.get_code());
            }
            if (is_update_procedure_selected && table.containsPrimaryKeys() && table.containsNonPrimaryKeys()) {
                Hsqldb_create_procedure_update hsqldb_create_procedure_update = new Hsqldb_create_procedure_update(cfg, table);
                sb.append(hsqldb_create_procedure_update.get_code());
            }
            if (is_delete_procedure_selected && table.containsPrimaryKeys()) {
                Hsqldb_create_procedure_delete hsqldb_create_procedure_delete = new Hsqldb_create_procedure_delete(cfg, table);
                sb.append(hsqldb_create_procedure_delete.get_code());
            }
            if (is_merge_procedure_selected && table.containsPrimaryKeys() && table.containsNonPrimaryKeys()) {
                Hsqldb_create_procedure_merge hsqldb_create_procedure_merge = new Hsqldb_create_procedure_merge(cfg, table);
                sb.append(hsqldb_create_procedure_merge.get_code());
            }
            if (is_select_procedure_selected && table.containsPrimaryKeys()) {
                Hsqldb_create_procedure_select hsqldb_create_procedure_select = new Hsqldb_create_procedure_select(cfg, table);
                sb.append(hsqldb_create_procedure_select.get_code());
            }
            if (is_delete_all_procedure_selected) {
                Hsqldb_create_procedure_delete_all hsqldb_create_procedure_delete_all = new Hsqldb_create_procedure_delete_all(cfg, table);
                sb.append(hsqldb_create_procedure_delete_all.get_code());
            }
            if (is_select_all_procedure_selected) {
                Hsqldb_create_procedure_select_all hsqldb_create_procedure_select_all = new Hsqldb_create_procedure_select_all(cfg, table);
                sb.append(hsqldb_create_procedure_select_all.get_code());
            }
            if (is_count_function_selected) {
                Hsqldb_create_function_count hsqldb_create_function_count = new Hsqldb_create_function_count(cfg, table);
                sb.append(hsqldb_create_function_count.get_code());
            }
            if (is_insert_using_select_statement_selected) {
                Hsqldb_create_statement_insert_using_select hsqldb_create_statement_insert_using_select = new Hsqldb_create_statement_insert_using_select(cfg, table);
                sb.append(hsqldb_create_statement_insert_using_select.get_code());
            }
            if (is_row_level_before_insert_event_trigger_selected) {
                Hsqldb_create_trigger_row_level_before_insert_event hsqldb_create_trigger_row_level_before_insert_event = new Hsqldb_create_trigger_row_level_before_insert_event(cfg, table);
                sb.append(hsqldb_create_trigger_row_level_before_insert_event.get_code());
            }
            if (is_row_level_before_update_event_trigger_selected) {
                Hsqldb_create_trigger_row_level_before_update_event hsqldb_create_trigger_row_level_before_update_event = new Hsqldb_create_trigger_row_level_before_update_event(cfg, table);
                sb.append(hsqldb_create_trigger_row_level_before_update_event.get_code());
            }
            if (is_row_level_before_delete_event_trigger_selected) {
                Hsqldb_create_trigger_row_level_before_delete_event hsqldb_create_trigger_row_level_before_delete_event = new Hsqldb_create_trigger_row_level_before_delete_event(cfg, table);
                sb.append(hsqldb_create_trigger_row_level_before_delete_event.get_code());
            }
            if (is_row_level_after_insert_event_trigger_selected) {
                Hsqldb_create_trigger_row_level_after_insert_event hsqldb_create_trigger_row_level_after_insert_event = new Hsqldb_create_trigger_row_level_after_insert_event(cfg, table);
                sb.append(hsqldb_create_trigger_row_level_after_insert_event.get_code());
            }
            if (is_row_level_after_update_event_trigger_selected) {
                Hsqldb_create_trigger_row_level_after_update_event hsqldb_create_trigger_row_level_after_update_event = new Hsqldb_create_trigger_row_level_after_update_event(cfg, table);
                sb.append(hsqldb_create_trigger_row_level_after_update_event.get_code());
            }
            if (is_row_level_after_delete_event_trigger_selected) {
                Hsqldb_create_trigger_row_level_after_delete_event hsqldb_create_trigger_row_level_after_delete_event = new Hsqldb_create_trigger_row_level_after_delete_event(cfg, table);
                sb.append(hsqldb_create_trigger_row_level_after_delete_event.get_code());
            }
            if (is_statement_level_after_insert_event_trigger_selected) {
                Hsqldb_create_trigger_statement_level_after_insert_event hsqldb_create_trigger_statement_level_after_insert_event = new Hsqldb_create_trigger_statement_level_after_insert_event(cfg, table);
                sb.append(hsqldb_create_trigger_statement_level_after_insert_event.get_code());
            }
            if (is_statement_level_after_update_event_trigger_selected) {
                Hsqldb_create_trigger_statement_level_after_update_event hsqldb_create_trigger_statement_level_after_update_event = new Hsqldb_create_trigger_statement_level_after_update_event(cfg, table);
                sb.append(hsqldb_create_trigger_statement_level_after_update_event.get_code());
            }
            if (is_statement_level_after_delete_event_trigger_selected) {
                Hsqldb_create_trigger_statement_level_after_delete_event hsqldb_create_trigger_statement_level_after_delete_event = new Hsqldb_create_trigger_statement_level_after_delete_event(cfg, table);
                sb.append(hsqldb_create_trigger_statement_level_after_delete_event.get_code());
            }
            if (is_insert_statement_selected) {
                Hsqldb_create_statement_insert hsqldb_create_statement_insert = new Hsqldb_create_statement_insert(cfg, table);
                sb.append(hsqldb_create_statement_insert.get_code());
            }
            if (is_update_statement_selected && table.containsPrimaryKeys() && table.containsNonPrimaryKeys()) {
                Hsqldb_create_statement_update hsqldb_create_statement_update = new Hsqldb_create_statement_update(cfg, table);
                sb.append(hsqldb_create_statement_update.get_code());
            }
            if (is_delete_statement_selected && table.containsPrimaryKeys()) {
                Hsqldb_create_statement_delete hsqldb_create_statement_delete = new Hsqldb_create_statement_delete(cfg, table);
                sb.append(hsqldb_create_statement_delete.get_code());
            }
            if (is_merge_statement_selected && table.containsPrimaryKeys() && table.containsNonPrimaryKeys()) {
                Hsqldb_create_statement_merge hsqldb_create_statement_merge = new Hsqldb_create_statement_merge(cfg, table);
                sb.append(hsqldb_create_statement_merge.get_code());
            }
            if (is_select_statement_selected && table.containsPrimaryKeys()) {
                Hsqldb_create_statement_select hsqldb_create_statement_select = new Hsqldb_create_statement_select(cfg, table);
                sb.append(hsqldb_create_statement_select.get_code());
            }
            if (is_delete_all_statement_selected) {
                Hsqldb_create_statement_delete_all hsqldb_create_statement_delete_all = new Hsqldb_create_statement_delete_all(cfg, table);
                sb.append(hsqldb_create_statement_delete_all.get_code());
            }
            if (is_select_all_statement_selected) {
                Hsqldb_create_statement_select_all hsqldb_create_statement_select_all = new Hsqldb_create_statement_select_all(cfg, table);
                sb.append(hsqldb_create_statement_select_all.get_code());
            }
            if (is_count_statement_selected) {
                Hsqldb_create_statement_count hsqldb_create_statement_count = new Hsqldb_create_statement_count(cfg, table);
                sb.append(hsqldb_create_statement_count.get_code());
            }
        });
        return sb.toString();
    }

    public String get_sql_code_generated_for_mariadb() {
        StringBuilder sb = new StringBuilder();
        TableMaker tableMaker = new TableMaker(dblink);
        if (is_tables_and_relationships_selected) {
            Mariadb_drop_schema mariadb_drop_schema = new Mariadb_drop_schema(cfg, getSchema_name());
            sb.append(mariadb_drop_schema.get_code());
            Mariadb_create_schema mariadb_create_schema = new Mariadb_create_schema(cfg, getSchema_name());
            sb.append(mariadb_create_schema.get_code());
            Mariadb_set_schema mariadb_set_schema = new Mariadb_set_schema(cfg, getSchema_name());
            sb.append(mariadb_set_schema.get_code());
            table_list.forEach(element -> {
                Integer tableId = element.getTable_id();
                Table table = tableMaker.get_table(schema, tableId);
                Mariadb_create_table mariadb_create_table = new Mariadb_create_table(cfg, table);
                sb.append(mariadb_create_table.get_code());
            });
            List<O_select_only_names> list = (List<O_select_only_names>) dblink.relationship_select_only_names(schema);
            Mariadb_create_relationship mariadb_create_relationship = new Mariadb_create_relationship(cfg, list);
            sb.append(mariadb_create_relationship.get_code());
        }
        table_list.forEach(element -> {
            Integer tableId = element.getTable_id();
            Table table = tableMaker.get_table(schema, tableId);
            if (is_view_selected) {
                Mariadb_create_view mariadb_create_view = new Mariadb_create_view(cfg, table);
                sb.append(mariadb_create_view.get_code());
            }
            if (is_create_table_with_select_selected) {
                Mariadb_create_table_with_select mariadb_create_table_with_select = new Mariadb_create_table_with_select(cfg, table);
                sb.append(mariadb_create_table_with_select.get_code());
            }
            if (is_insert_procedure_selected) {
                Mariadb_create_procedure_insert mariadb_create_insert_procedure = new Mariadb_create_procedure_insert(cfg, table);
                sb.append(mariadb_create_insert_procedure.get_code());
            }
            if (is_update_procedure_selected && table.containsPrimaryKeys() && table.containsNonPrimaryKeys()) {
                Mariadb_create_procedure_update mariadb_create_update_procedure = new Mariadb_create_procedure_update(cfg, table);
                sb.append(mariadb_create_update_procedure.get_code());
            }
            if (is_delete_procedure_selected && table.containsPrimaryKeys()) {
                Mariadb_create_procedure_delete mariadb_create_delete_procedure = new Mariadb_create_procedure_delete(cfg, table);
                sb.append(mariadb_create_delete_procedure.get_code());
            }
            if (is_merge_procedure_selected && table.containsPrimaryKeys() && table.containsNonPrimaryKeys()) {
                Mariadb_create_procedure_merge mariadb_create_merge_procedure = new Mariadb_create_procedure_merge(cfg, table);
                sb.append(mariadb_create_merge_procedure.get_code());
            }
            if (is_select_procedure_selected && table.containsPrimaryKeys()) {
                Mariadb_create_procedure_select mariadb_create_select_procedure = new Mariadb_create_procedure_select(cfg, table);
                sb.append(mariadb_create_select_procedure.get_code());
            }
            if (is_delete_all_procedure_selected) {
                Mariadb_create_procedure_delete_all mariadb_create_delete_all_procedure = new Mariadb_create_procedure_delete_all(cfg, table);
                sb.append(mariadb_create_delete_all_procedure.get_code());
            }
            if (is_select_all_procedure_selected) {
                Mariadb_create_procedure_select_all mariadb_create_select_all_procedure = new Mariadb_create_procedure_select_all(cfg, table);
                sb.append(mariadb_create_select_all_procedure.get_code());
            }
            if (is_count_function_selected) {
                Mariadb_create_function_count mariadb_create_count_function = new Mariadb_create_function_count(cfg, table);
                sb.append(mariadb_create_count_function.get_code());
            }
            if (is_insert_using_select_statement_selected) {
                Mariadb_create_statement_insert_using_select mariadb_create_statement_insert_using_select = new Mariadb_create_statement_insert_using_select(cfg, table);
                sb.append(mariadb_create_statement_insert_using_select.get_code());
            }
            if (is_statement_level_after_insert_event_trigger_selected) {
                sb.append("Mariadb does not support triggers using FOR EACH STATEMENT\n");
            }
            if (is_statement_level_after_update_event_trigger_selected) {
                sb.append("Mariadb does not support triggers using FOR EACH STATEMENT\n");
            }
            if (is_statement_level_after_delete_event_trigger_selected) {
                sb.append("Mariadb does not support triggers using FOR EACH STATEMENT\n");
            }
            if (is_row_level_before_insert_event_trigger_selected) {
                Mariadb_create_trigger_row_level_before_insert_event mariadb_create_row_level_before_insert_event_trigger = new Mariadb_create_trigger_row_level_before_insert_event(cfg, table);
                sb.append(mariadb_create_row_level_before_insert_event_trigger.get_code());
            }
            if (is_row_level_before_update_event_trigger_selected) {
                Mariadb_create_trigger_row_level_before_update_event mariadb_create_row_level_before_update_event_trigger = new Mariadb_create_trigger_row_level_before_update_event(cfg, table);
                sb.append(mariadb_create_row_level_before_update_event_trigger.get_code());
            }
            if (is_row_level_before_delete_event_trigger_selected) {
                Mariadb_create_trigger_row_level_before_delete_event mariadb_create_row_level_before_delete_event_trigger = new Mariadb_create_trigger_row_level_before_delete_event(cfg, table);
                sb.append(mariadb_create_row_level_before_delete_event_trigger.get_code());
            }
            if (is_row_level_after_insert_event_trigger_selected) {
                Mariadb_create_trigger_row_level_after_insert_event mariadb_create_row_level_after_insert_event_trigger = new Mariadb_create_trigger_row_level_after_insert_event(cfg, table);
                sb.append(mariadb_create_row_level_after_insert_event_trigger.get_code());
            }
            if (is_row_level_after_update_event_trigger_selected) {
                Mariadb_create_trigger_row_level_after_update_event mariadb_create_row_level_after_update_event_trigger = new Mariadb_create_trigger_row_level_after_update_event(cfg, table);
                sb.append(mariadb_create_row_level_after_update_event_trigger.get_code());
            }
            if (is_row_level_after_delete_event_trigger_selected) {
                Mariadb_create_trigger_row_level_after_delete_event mariadb_create_row_level_after_delete_event_trigger = new Mariadb_create_trigger_row_level_after_delete_event(cfg, table);
                sb.append(mariadb_create_row_level_after_delete_event_trigger.get_code());
            }
            if (is_insert_statement_selected) {
                Mariadb_create_statement_insert mariadb_create_statement_insert = new Mariadb_create_statement_insert(cfg, table);
                sb.append(mariadb_create_statement_insert.get_code());
            }
            if (is_update_statement_selected && table.containsPrimaryKeys() && table.containsNonPrimaryKeys()) {
                Mariadb_create_statement_update mariadb_create_statement_update = new Mariadb_create_statement_update(cfg, table);
                sb.append(mariadb_create_statement_update.get_code());
            }
            if (is_delete_statement_selected && table.containsPrimaryKeys()) {
                Mariadb_create_statement_delete mariadb_create_statement_delete = new Mariadb_create_statement_delete(cfg, table);
                sb.append(mariadb_create_statement_delete.get_code());
            }
            if (is_merge_statement_selected && table.containsPrimaryKeys() && table.containsNonPrimaryKeys()) {
                Mariadb_create_statement_merge mariadb_create_statement_merge = new Mariadb_create_statement_merge(cfg, table);
                sb.append(mariadb_create_statement_merge.get_code());
            }
            if (is_select_statement_selected && table.containsPrimaryKeys()) {
                Mariadb_create_statement_select mariadb_create_statement_select = new Mariadb_create_statement_select(cfg, table);
                sb.append(mariadb_create_statement_select.get_code());
            }
            if (is_delete_all_statement_selected) {
                Mariadb_create_statement_delete_all mariadb_create_statement_delete_all = new Mariadb_create_statement_delete_all(cfg, table);
                sb.append(mariadb_create_statement_delete_all.get_code());
            }
            if (is_select_all_statement_selected) {
                Mariadb_create_statement_select_all mariadb_create_statement_select_all = new Mariadb_create_statement_select_all(cfg, table);
                sb.append(mariadb_create_statement_select_all.get_code());
            }
            if (is_count_statement_selected) {
                Mariadb_create_statement_count mariadb_create_statement_count = new Mariadb_create_statement_count(cfg, table);
                sb.append(mariadb_create_statement_count.get_code());
            }
        });
        return sb.toString();
    }

    private String get_java_code_generated() {
        StringBuilder sb = new StringBuilder();
        TableMaker tableMaker = new TableMaker(dblink);
        if (is_test_class_selected) {
            create_test_class = new Create_test_class(cfg);
            sb.append(create_test_class.get_code());
        }
        table_list.forEach(element -> {
            Integer tableId = element.getTable_id();
            Table table = tableMaker.get_table(schema, tableId);
            if (is_insert_method_selected) {
                Create_method_insert create_method_insert = new Create_method_insert(cfg, table);
                sb.append(create_method_insert.get_code());
            }
            if (is_update_method_selected) {
                Create_method_update create_method_update = new Create_method_update(cfg, table);
                sb.append(create_method_update.get_code());
            }
            if (is_delete_method_selected) {
                Create_method_delete create_method_delete = new Create_method_delete(cfg, table);
                sb.append(create_method_delete.get_code());
            }
            if (is_merge_method_selected) {
                Create_method_merge create_method_merge = new Create_method_merge(cfg, table);
                sb.append(create_method_merge.get_code());
            }
            if (is_select_method_selected) {
                Create_method_select create_method_select = new Create_method_select(cfg, table);
                sb.append(create_method_select.get_code());
            }
            if (is_print_select_method_selected) {
                Create_method_print_select create_method_print_select = new Create_method_print_select(cfg, table);
                sb.append(create_method_print_select.get_code());
            }
            if (is_delete_all_method_selected) {
                Create_method_delete_all create_method_delete_all = new Create_method_delete_all(cfg, table);
                sb.append(create_method_delete_all.get_code());
            }
            if (is_select_all_method_selected) {
                Create_method_select_all create_method_select_all = new Create_method_select_all(cfg, table);
                sb.append(create_method_select_all.get_code());
            }
            if (is_print_select_all_method_selected) {
                Create_method_print_select_all create_method_print_select_all = new Create_method_print_select_all(cfg, table);
                sb.append(create_method_print_select_all.get_code());
            }
            if (is_count_function_method_selected) {
                if (!is_hsqldb_selected) {
                    Create_method_count create_method_count = new Create_method_count(cfg, table);
                    sb.append(create_method_count.get_code());
                } else {
                    Create_method_count_for_hsqldb create_method_count_for_hsqldb = new Create_method_count_for_hsqldb(cfg, table);
                    sb.append(create_method_count_for_hsqldb.get_code());
                }

            }
            if (is_print_function_count_method_selected) {
                Create_method_print_count create_method_print_count = new Create_method_print_count(cfg, table);
                sb.append(create_method_print_count.get_code());
            }
            if (is_generate_data_transfer_object_option_selected) {
                Create_data_transfer_object create_data_transfer_object = new Create_data_transfer_object(cfg, table);
                sb.append(create_data_transfer_object.get_code());
            }
            if (is_generate_data_access_object_option_selected) {
                Create_typed_dao_interface dao = new Create_typed_dao_interface(cfg, table);
                sb.append(dao.get_code());
            }
        });
        if (is_generate_generic_data_access_object_selected) {
            Create_generic_dao dao = new Create_generic_dao(cfg);
            sb.append(dao.get_code());
        }
        return sb.toString();
    }

}
