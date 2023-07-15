package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.data.transfer.Schema_del;
import io.github.xjrga.potatosql.data.transfer.Schema_dup;
import io.github.xjrga.potatosql.data.transfer.Schema_ins_auto;
import io.github.xjrga.potatosql.data.transfer.Schema_pop;
import io.github.xjrga.potatosql.data.transfer.Schema_sel;
import io.github.xjrga.potatosql.data.transfer.Schema_upd;
import io.github.xjrga.potatosql.other.Utilities;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;

public class Schema_pnl
        extends JPanel {
    private final CellConstraints cc = new CellConstraints();
    private final JList list = new JList();
    private final Mediator_concrete mediator;
    public Schema_pnl( Mediator_concrete mediator ) {
        this.mediator = mediator;
        initialize();
        setup();
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,min" //rows
        );
        setLayout( layout );
        setMinimumSize( new Dimension( 250, 0 ) );
        setBorder( new TitledBorder( "Schema" ) );
        add( new JScrollPane( list ), cc.xy( 1, 1 ) );
        add( get_panel_buttons(), cc.xy( 1, 2 ) );
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
        mediator.register_schema_panel( this );
        list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        reload();
    }
    private void event_list_selection() {
        if ( is_selection_empty() ) {
            return;
        }
        mediator.reload_table_panel();
        mediator.reload_relationship_table_panel();
        mediator.clear_table_keys_panel();
        mediator.clear_relationship_pair_keys_table_panel();
        mediator.beautify_relationship_table_panel();
    }
    private JPanel get_panel_buttons() {
        FormLayout layout = new FormLayout( "min:grow,min,min,min,min,min:grow", //columns
                                            "min" //rows
        );
        JPanel panel = new JPanel();
        JButton btn_add = new JButton( "+" );
        JButton btn_delete = new JButton( "-" );
        JButton btn_update = new JButton( "r" );
        JButton btn_duplicate = new JButton( "d" );
        btn_add.setToolTipText( "Add schema" );
        btn_delete.setToolTipText( "Delete schema" );
        btn_update.setToolTipText( "Rename schema" );
        btn_duplicate.setToolTipText( "Duplicate schema" );
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
        JLabel question = new JLabel();
        JTextField field = new JTextField();
        JComponent[] inputs = new JComponent[] {
            question,
            field
        };
        question.setText( "What is your new schema name?" );
        int i = Message.showOptionDialogOkCancel( inputs, "New Schema" );
        if ( i == 0 ) {
            String s = field.getText();
            if ( Utilities.contains_text( s ) ) {
                Schema_ins_auto insert_auto = new Schema_ins_auto();
                Schema schema = new Schema( s );
                Integer schema_id = insert_auto.apply( schema );
                reload();
                schema.setSchema_id( schema_id );
                set_selected( schema );
            } else {
                Message.showMessage( "Please use alphanumeric or underscore characters: " + s );
            }
        }
    }
    private void event_action_performed_btn_delete() {
        if ( is_selection_empty() ) {
            Message.showMessage( "Please select schema" );
            return;
        }
        Schema schema = ( Schema ) list.getSelectedValue();
        Schema_del delete = new Schema_del();
        delete.apply( schema );
        reload();
        mediator.clear_table_panel();
        mediator.clear_table_keys_panel();
        mediator.clear_relationship_table_panel();
        mediator.clear_relationship_pair_keys_table_panel();
    }
    private void event_action_performed_btn_update() {
        if ( is_selection_empty() ) {
            Message.showMessage( "Please select schema" );
            return;
        }
        Schema schema = ( Schema ) list.getSelectedValue();
        JLabel question = new JLabel();
        JTextField field = new JTextField();
        JComponent[] inputs = new JComponent[] {
            question,
            field
        };
        field.setText( schema.getSchema_name() );
        question.setText( "What is your new schema name?" );
        int i = Message.showOptionDialogOkCancel( inputs, "New Schema" );
        if ( i == 0 ) {
            String s = field.getText();
            if ( Utilities.contains_text( s ) ) {
                schema = new Schema( schema.getSchema_id(), s );
                Schema_upd update = new Schema_upd();
                update.apply( schema );
                reload();
                set_selected( schema );
            } else {
                Message.showMessage( "Please use alphanumeric or underscore characters: " + s );
            }
        }
    }
    private void event_action_performed_btn_duplicate() {
        if ( is_selection_empty() ) {
            Message.showMessage( "Please select schema" );
            return;
        }
        Schema schema = ( Schema ) list.getSelectedValue();
        Schema_dup duplicator = new Schema_dup();
        Integer schema_id = duplicator.duplicate( schema );
        schema.setSchema_id( schema_id );
        reload();
        set_selected( schema );
        mediator.clear_table_keys_panel();
        mediator.clear_relationship_pair_keys_table_panel();
    }
    public Boolean is_selection_empty() {
        return list.isSelectionEmpty();
    }
    public Schema get_selected_value() {
        return ( Schema ) list.getSelectedValue();
    }
    public void reload() {
        Schema_sel select = new Schema_sel();
        Schema_pop populate = new Schema_pop();
        list.setModel( populate.apply( select.apply() ) );
    }
    public void set_selected( Schema schema ) {
        list.setSelectedValue( schema, true );
    }
}
