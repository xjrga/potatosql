package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.data.object.Table;
import io.github.xjrga.potatosql.data.transfer.Table_del;
import io.github.xjrga.potatosql.data.transfer.Table_dup;
import io.github.xjrga.potatosql.data.transfer.Table_ins_auto;
import io.github.xjrga.potatosql.data.transfer.Table_pop;
import io.github.xjrga.potatosql.data.transfer.Table_sel;
import io.github.xjrga.potatosql.data.transfer.Table_upd;
import io.github.xjrga.potatosql.other.Utilities;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;

public class Table_pnl
        extends JPanel {
    private final Mediator_concrete mediator;
    private final CellConstraints cc = new CellConstraints();
    public JList list = new JList();
    public JTable table = new JTable();
    public Table_pnl( Mediator_concrete mediator ) {
        this.mediator = mediator;
        initialize();
        setup();
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,min" //rows
        );
        setLayout( layout );
        setBorder( new TitledBorder( "Table" ) );
        setMinimumSize( new Dimension( 250, 0 ) );
        add( new JScrollPane( list ), cc.xy( 1, 1 ) );
        add( get_panel_table_buttons(), cc.xy( 1, 2 ) );
        list.addListSelectionListener( ( ListSelectionEvent e )
                -> {
            if ( e.getValueIsAdjusting() ) {
                return;
            }
            event_list_selection();
        } );
    }
    private void initialize() {
    }
    private void setup() {
        mediator.register_table_panel( this );
        list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        table.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    }
    private void event_list_selection() {
        if ( is_selection_empty() ) {
            return;
        }
        mediator.reload_table_keys_panel();
        mediator.beautify_table_keys_panel();
    }
    private JPanel get_panel_table_buttons() {
        FormLayout layout = new FormLayout( "min:grow,min,min,min,min,min:grow", //columns
                                            "min" //rows
        );
        JPanel panel = new JPanel();
        JButton btn_add = new JButton( "+" );
        JButton btn_delete = new JButton( "-" );
        JButton btn_update = new JButton( "r" );
        JButton btn_duplicate = new JButton( "d" );
        btn_add.setToolTipText( "Add table" );
        btn_delete.setToolTipText( "Delete table" );
        btn_update.setToolTipText( "Rename table" );
        btn_duplicate.setToolTipText( "Duplicate table" );
        panel.setLayout( layout );
        panel.add( btn_add, cc.xy( 2, 1 ) );
        panel.add( btn_delete, cc.xy( 3, 1 ) );
        panel.add( btn_update, cc.xy( 4, 1 ) );
        panel.add( btn_duplicate, cc.xy( 5, 1 ) );
        btn_add.addActionListener( ( ActionEvent e )
                -> {
            event_action_performed_btn_add();
        } );
        btn_delete.addActionListener( ( ActionEvent e )
                -> {
            event_action_performed_btn_delete();
        } );
        btn_update.addActionListener( ( ActionEvent e )
                -> {
            event_action_performed_btn_update();
        } );
        btn_duplicate.addActionListener( ( ActionEvent e )
                -> {
            event_action_performed_btn_duplicate();
        } );
        return panel;
    }
    private void event_action_performed_btn_add() {
        if ( mediator.is_schema_list_selection_empty() ) {
            Message.showMessage( "Please select schema" );
            return;
        }
        Schema schema = ( Schema ) mediator.get_schema_selected_value();
        JLabel question = new JLabel();
        JTextField field = new JTextField();
        JComponent[] inputs = new JComponent[] {
            question,
            field
        };
        question.setText( "What is your new table name?" );
        int i = Message.showOptionDialogOkCancel( inputs, "New Table" );
        if ( i == 0 ) {
            String s = field.getText();
            if ( Utilities.contains_text( s ) ) {
                Table_ins_auto insert_auto = new Table_ins_auto();
                var otable = new Table( schema.getSchema_id(), s );
                Integer table_id = insert_auto.apply( otable );
                reload();
                otable.setTable_id( table_id );
                list.setSelectedValue( otable, true );
            } else {
                Message.showMessage( "Please use alphanumeric or underscore characters: " + s );
            }
        }
    }
    private void event_action_performed_btn_delete() {
        if ( mediator.is_schema_list_selection_empty() ) {
            Message.showMessage( "Please select schema" );
            return;
        }
        if ( is_selection_empty() ) {
            Message.showMessage( "Please select table" );
            return;
        }
        Table otable = ( Table ) list.getSelectedValue();
        Table_del delete = new Table_del();
        delete.apply( otable );
        reload();
        mediator.clear_table_keys_panel();
        mediator.reload_relationship_table_panel();
    }
    private void event_action_performed_btn_update() {
        if ( mediator.is_schema_list_selection_empty() ) {
            Message.showMessage( "Please select schema" );
            return;
        }
        if ( is_selection_empty() ) {
            Message.showMessage( "Please select table" );
            return;
        }
        var otable = ( Table ) list.getSelectedValue();
        JLabel question = new JLabel();
        JTextField field = new JTextField();
        JComponent[] inputs = new JComponent[] {
            question,
            field
        };
        question.setText( "What is your new table name?" );
        field.setText( otable.getTable_name() );
        int i = Message.showOptionDialogOkCancel( inputs, "New Table" );
        if ( i == 0 ) {
            String name = field.getText();
            if ( Utilities.contains_text( name ) ) {
                otable = new Table( otable.getSchema_id(), otable.getTable_id(), name );
                Table_upd update = new Table_upd();
                update.apply( otable );
                reload();
                list.setSelectedValue( otable, true );
                mediator.reload_relationship_table_panel();
                mediator.beautify_relationship_table_panel();
            } else {
                Message.showMessage( "Please use alphanumeric or underscore characters: " + name );
            }
        }
    }
    private void event_action_performed_btn_duplicate() {
        if ( mediator.is_schema_list_selection_empty() ) {
            Message.showMessage( "Please select schema" );
            return;
        }
        if ( is_selection_empty() ) {
            Message.showMessage( "Please select table" );
            return;
        }
        Table otable = ( Table ) list.getSelectedValue();
        Table_dup duplicator = new Table_dup();
        Integer table_id = duplicator.duplicate( otable );
        otable.setTable_id( table_id );
        reload();
        list.setSelectedValue( otable, true );
    }
    public boolean is_selection_empty() {
        return list.isSelectionEmpty();
    }
    public Table get_selected_value() {
        return ( Table ) list.getSelectedValue();
    }
    public void reload() {
        Schema schema = ( Schema ) mediator.get_schema_selected_value();
        Table_sel select = new Table_sel();
        Table_pop populate = new Table_pop();
        list.setModel( populate.apply( select.apply( schema ) ) );
    }
    public void clear() {
        list.setModel( new DefaultListModel() );
    }
}
