package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import io.github.xjrga.potatosql.data.object.Relationship;
import io.github.xjrga.potatosql.data.object.Table;
import io.github.xjrga.potatosql.data.transfer.Identifying_relationship_ins;
import io.github.xjrga.potatosql.data.transfer.Nonidentifying_relationship_ins;
import io.github.xjrga.potatosql.data.transfer.Relationship_del;
import io.github.xjrga.potatosql.data.transfer.Relationship_ins_auto;
import io.github.xjrga.potatosql.data.transfer.Relationship_pop;
import io.github.xjrga.potatosql.data.transfer.Relationship_sel;
import io.github.xjrga.potatosql.other.Relationship_finder;
import io.github.xjrga.potatosql.other.Table_scroller;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class Relationship_pnl
        extends JPanel {
    private final Mediator_concrete mediator;
    private final CellConstraints cc = new CellConstraints();
    public JTable table = new JTable();
    public Relationship_pnl( Mediator_concrete mediator ) {
        this.mediator = mediator;
        initialize();
        setup();
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,min" //rows
        );
        setLayout( layout );
        setBorder( new TitledBorder( "Relationship" ) );
        add( new JScrollPane( table ), cc.xy( 1, 1 ) );
        add( get_relationship_buttons(), cc.xy( 1, 2 ) );
        table.getSelectionModel().addListSelectionListener( (
                ListSelectionEvent e )
                -> {
            if ( e.getValueIsAdjusting() ) {
                return;
            }
            event_table_selection( e );
        } );
    }
    private void initialize() {
    }
    private void setup() {
        mediator.register_relationship_panel( this );
        table.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    }
    private void event_table_selection( ListSelectionEvent e ) {
        if ( is_selection_empty() ) {
            return;
        }
        mediator.reload_relationship_pair_keys_table_panel();
        mediator.beautify_relationship_pair_keys_table_panel();
    }
    private JPanel get_relationship_buttons() {
        JPanel panel = new JPanel();
        JButton btn_add = new JButton( "+" );
        JButton btn_delete = new JButton( "-" );
        btn_add.setToolTipText( "Add relationship that connects parent to child" );
        btn_delete.setToolTipText( "Delete relationship" );
        panel.add( btn_add );
        panel.add( btn_delete );
        btn_add.addActionListener( ( ActionEvent e )
                -> {
            event_action_performed_btn_add();
        } );
        btn_delete.addActionListener( ( ActionEvent e )
                -> {
            event_action_performed_btn_delete();
        } );
        return panel;
    }
    private void event_action_performed_btn_add() {
        Relationship_input_pnl relationship_input_panel = new Relationship_input_pnl( mediator.get_schema_selected_value() );
        JComponent[] inputs = new JComponent[] {
            relationship_input_panel
        };
        int i = Message.showOptionDialogOkCancel( inputs, "Relationship" );
        if ( i == 0 ) {
            Table parent = ( Table ) relationship_input_panel.get_list_parent_selected_value();
            Table child = ( Table ) relationship_input_panel.get_list_child_selected_value();
            if ( parent == null ) {
                return;
            }
            if ( child == null ) {
                return;
            }
            Integer schemaid = parent.getSchema_id();
            Integer parent_tableid = parent.getTable_id();
            Integer child_tableid = child.getTable_id();
            Relationship relationship = new Relationship();
            relationship.setSchema_id( schemaid );
            relationship.setTable_id_parent( parent_tableid );
            relationship.setTable_id_child( child_tableid );
            relationship.setIs_identifying( relationship_input_panel.get_btn_identifying_selected_value() );
            Relationship_ins_auto insert_auto = new Relationship_ins_auto();
            Integer id = insert_auto.apply( relationship );
            relationship.setRelationship_id( id );
            if ( relationship.getIs_identifying() ) {
                Identifying_relationship_ins identifying_insert = new Identifying_relationship_ins();
                identifying_insert.apply( relationship.get_Relationship_pk() );
            } else {
                Nonidentifying_relationship_ins nonidentifying_insert = new Nonidentifying_relationship_ins();
                nonidentifying_insert.apply( relationship.get_Relationship_pk() );
            }
            reload();
            beautify();
            Relationship_finder find = new Relationship_finder();
            Integer index = find.find( table.getModel(), relationship );
            Table_scroller scroller = new Table_scroller();
            scroller.scroll( table, index );
        }
    }
    private void event_action_performed_btn_delete() {
        Integer selectedRow = table.getSelectedRow();
        if ( is_selection_empty() ) {
            return;
        }
        Integer relationshipid = ( Integer ) table.getValueAt( selectedRow, 0 );
        Integer schemaid = ( Integer ) table.getValueAt( selectedRow, 1 );
        Integer parent_tableid = ( Integer ) table.getValueAt( selectedRow, 2 );
        Integer child_tableid = ( Integer ) table.getValueAt( selectedRow, 4 );
        Relationship relationship = new Relationship();
        relationship.setSchema_id( schemaid );
        relationship.setTable_id_parent( parent_tableid );
        relationship.setTable_id_child( child_tableid );
        relationship.setRelationship_id( relationshipid );
        Relationship_del delete = new Relationship_del();
        delete.apply( relationship );
        reload();
        beautify();
        mediator.clear_relationship_pair_keys_table_panel();
    }
    public boolean is_selection_empty() {
        return table.getSelectedRow() == -1;
    }
    public Relationship get_relationship_selected_value() {
        int selectedRow = table.getSelectedRow();
        Integer relationshipid = ( Integer ) table.getValueAt( selectedRow, 0 );
        Integer schemaid = ( Integer ) table.getValueAt( selectedRow, 1 );
        Integer parent_tableid = ( Integer ) table.getValueAt( selectedRow, 2 );
        Integer child_tableid = ( Integer ) table.getValueAt( selectedRow, 4 );
        Boolean identifying = ( Boolean ) table.getValueAt( selectedRow, 6 );
        Relationship relationship = new Relationship();
        relationship.setRelationship_id( relationshipid );
        relationship.setSchema_id( schemaid );
        relationship.setTable_id_parent( parent_tableid );
        relationship.setTable_id_child( child_tableid );
        relationship.setIs_identifying( identifying );
        return relationship;
    }
    public void reload() {
        Relationship_sel select = new Relationship_sel();
        Relationship_pop populate = new Relationship_pop();
        table.setModel( populate.apply( select.apply( mediator.get_schema_selected_value() ) ) );
    }
    public void clear() {
        table.setModel( new DefaultTableModel() );
    }
    public void beautify() {
        table.getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        table.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );
        table.getColumnModel().getColumn( 1 ).setMinWidth( 0 );
        table.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );
        table.getColumnModel().getColumn( 2 ).setMinWidth( 0 );
        table.getColumnModel().getColumn( 2 ).setMaxWidth( 0 );
        table.getColumnModel().getColumn( 4 ).setMinWidth( 0 );
        table.getColumnModel().getColumn( 4 ).setMaxWidth( 0 );
    }
}
