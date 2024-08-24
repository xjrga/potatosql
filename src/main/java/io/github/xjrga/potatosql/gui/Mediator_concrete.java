package io.github.xjrga.potatosql.gui;

import io.github.xjrga.potatosql.data.object.Code;
import io.github.xjrga.potatosql.data.object.Keypair;
import io.github.xjrga.potatosql.data.object.Relationship;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.data.object.Table;
import java.util.stream.Stream;

public class Mediator_concrete implements Mediator {
  private Main_pnl main_pnl;
  private Menu_bar menu_bar;
  private Output_pnl_composite output_pnl;
  private Options_pnl options_pnl;
  private Relationship_key_pairs_pnl relationship_pair_keys_pnl;
  private Relationship_pnl_composite relationship_pnl_composite;
  private Relationship_pnl relationship_pnl;
  private Schema_pnl schema_pnl;
  private Table_keys_pnl table_keys_pnl;
  private Table_pnl_composite table_pnl_composite;
  private Table_pnl table_pnl;

  Mediator_concrete() {
    initialize();
    setup();
  }

  private void initialize() {}

  private void setup() {}

  // register methods
  @Override
  public void register_menu_bar(Menu_bar menu) {
    this.menu_bar = menu;
  }

  @Override
  public void register_main_panel(Main_pnl panel) {
    this.main_pnl = panel;
  }

  @Override
  public void register_schema_panel(Schema_pnl panel) {
    this.schema_pnl = panel;
  }

  @Override
  public void register_table_panel(Table_pnl panel) {
    this.table_pnl = panel;
  }

  @Override
  public void register_table_key_panel(Table_keys_pnl panel) {
    this.table_keys_pnl = panel;
  }

  @Override
  public void register_table_panel_assemble_panel(Table_pnl_composite panel) {
    this.table_pnl_composite = panel;
  }

  @Override
  public void register_relationship_panel(Relationship_pnl panel) {
    this.relationship_pnl = panel;
  }

  @Override
  public void register_relationship_pair_keys_panel(Relationship_key_pairs_pnl panel) {
    this.relationship_pair_keys_pnl = panel;
  }

  @Override
  public void register_relationship_panel_composite(Relationship_pnl_composite panel) {
    this.relationship_pnl_composite = panel;
  }

  @Override
  public void register_output_panel(Output_pnl_composite panel) {
    this.output_pnl = panel;
  }

  @Override
  public void register_options_panel(Options_pnl panel) {
    this.options_pnl = panel;
  }

  // get selected value methods
  @Override
  public Schema get_schema_selected_value() {
    return schema_pnl.get_selected_value();
  }

  @Override
  public Table get_table_selected_value() {
    return table_pnl.get_selected_value();
  }

  @Override
  public Relationship get_relationship_selected_value() {
    return relationship_pnl.get_relationship_selected_value();
  }

  @Override
  public Stream<Code> get_code_list_stream() {
    return options_pnl.get_code_list_stream();
  }

  @Override
  public Keypair get_relationship_key_pair_selected_value() {
    return relationship_pair_keys_pnl.get_relationship_key_pair_selected_value();
  }

  // is selected methods
  @Override
  public Boolean is_schema_list_selection_empty() {
    return schema_pnl.is_selection_empty();
  }

  @Override
  public Boolean is_table_list_selection_empty() {
    return table_pnl.is_selection_empty();
  }

  // set selected methods
  @Override
  public void set_selected_schema(Schema schema) {
    schema_pnl.set_selected(schema);
  }

  // get panel methods
  @Override
  public Menu_bar get_menu_bar() {
    return menu_bar;
  }

  @Override
  public Main_pnl get_main_panel() {
    return main_pnl;
  }

  @Override
  public Schema_pnl get_schema_panel() {
    return schema_pnl;
  }

  @Override
  public Table_pnl get_table_panel() {
    return table_pnl;
  }

  @Override
  public Table_keys_pnl get_table_key_panel() {
    return table_keys_pnl;
  }

  @Override
  public Table_pnl_composite get_table_panel_composite() {
    return table_pnl_composite;
  }

  @Override
  public Relationship_pnl get_relationship_panel() {
    return relationship_pnl;
  }

  @Override
  public Relationship_key_pairs_pnl get_relationship_pair_keys_panel() {
    return relationship_pair_keys_pnl;
  }

  @Override
  public Relationship_pnl_composite get_relationship_panel_composite() {
    return relationship_pnl_composite;
  }

  @Override
  public Output_pnl_composite get_output_panel_composite() {
    return output_pnl;
  }

  public Options_pnl get_options_panel() {
    return options_pnl;
  }

  // reload methods
  @Override
  public void reload_schema_panel() {
    schema_pnl.reload();
  }

  @Override
  public void reload_table_panel() {
    table_pnl.reload();
  }

  @Override
  public void reload_table_keys_panel() {
    table_keys_pnl.reload();
  }

  @Override
  public void reload_relationship_table_panel() {
    relationship_pnl.reload();
  }

  @Override
  public void reload_relationship_pair_keys_table_panel() {
    relationship_pair_keys_pnl.reload();
  }

  // clear methods
  @Override
  public void clear_table_panel() {
    table_pnl.clear();
  }

  @Override
  public void clear_table_keys_panel() {
    table_keys_pnl.clear();
  }

  @Override
  public void clear_relationship_table_panel() {
    relationship_pnl.clear();
  }

  @Override
  public void clear_relationship_pair_keys_table_panel() {
    relationship_pair_keys_pnl.clear();
  }

  // beautify methods
  @Override
  public void beautify_table_keys_panel() {
    table_keys_pnl.beautify();
  }

  @Override
  public void beautify_relationship_pair_keys_table_panel() {
    relationship_pair_keys_pnl.beautify();
  }

  @Override
  public void beautify_relationship_table_panel() {
    relationship_pnl.beautify();
  }
}
