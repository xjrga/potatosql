package io.github.xjrga.potatosql.gui;

import io.github.xjrga.potatosql.data.object.Code;
import io.github.xjrga.potatosql.data.object.Keypair;
import io.github.xjrga.potatosql.data.object.Relationship;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.data.object.Table;
import java.util.stream.Stream;

public interface Mediator {
    //register methods
    void register_menu_bar( Menu_bar menu );
    void register_main_panel( Main_pnl panel );
    void register_schema_panel( Schema_pnl panel );
    void register_table_panel( Table_pnl panel );
    void register_table_key_panel( Table_keys_pnl panel );
    void register_table_panel_assemble_panel( Table_pnl_composite panel );
    void register_relationship_panel( Relationship_pnl panel );
    void register_relationship_pair_keys_panel( Relationship_key_pairs_pnl panel );
    void register_relationship_panel_composite( Relationship_pnl_composite panel );
    void register_output_panel( Output_pnl_composite panel );
    void register_options_panel( Options_pnl panel );
    //get selected value methods
    Schema get_schema_selected_value();
    Table get_table_selected_value();
    Relationship get_relationship_selected_value();
    Keypair get_relationship_key_pair_selected_value();
    Stream<Code> get_code_list_stream();
    //is selected methods
    Boolean is_schema_list_selection_empty();
    Boolean is_table_list_selection_empty();
    //set selected methods
    void set_selected_schema( Schema schema );
    //get panel methods
    Main_pnl get_main_panel();
    Menu_bar get_menu_bar();
    Schema_pnl get_schema_panel();
    Table_pnl get_table_panel();
    Table_keys_pnl get_table_key_panel();
    Table_pnl_composite get_table_panel_composite();
    Relationship_pnl get_relationship_panel();
    Relationship_key_pairs_pnl get_relationship_pair_keys_panel();
    Relationship_pnl_composite get_relationship_panel_composite();
    Output_pnl_composite get_output_panel_composite();
    //reload methods
    void reload_schema_panel();
    void reload_table_panel();
    void reload_table_keys_panel();
    void reload_relationship_table_panel();
    void reload_relationship_pair_keys_table_panel();
    //clear methods
    void clear_table_panel();
    void clear_table_keys_panel();
    void clear_relationship_table_panel();
    void clear_relationship_pair_keys_table_panel();
    //beautify methods
    void beautify_table_keys_panel();
    void beautify_relationship_table_panel();
    void beautify_relationship_pair_keys_table_panel();
}
