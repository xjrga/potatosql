package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import io.github.xjrga.looks.themes.Dawn_150;
import io.github.xjrga.potatosql.data.Dblink;
import io.github.xjrga.potatosql.data.dto.O_key;
import io.github.xjrga.potatosql.data.dto.O_key_pair_multiple_select;
import io.github.xjrga.potatosql.data.dto.O_key_type;
import io.github.xjrga.potatosql.data.dto.O_key_with_name;
import io.github.xjrga.potatosql.data.dto.O_relationship;
import io.github.xjrga.potatosql.data.dto.O_relationship_key_pair;
import io.github.xjrga.potatosql.data.dto.O_schema;
import io.github.xjrga.potatosql.data.dto.O_table;
import io.github.xjrga.potatosql.generator.DialectBuilder;
import io.github.xjrga.potatosql.model.ListModelChildNPK;
import io.github.xjrga.potatosql.model.ListModelChildPK;
import io.github.xjrga.potatosql.model.ListModelKeyTypes;
import io.github.xjrga.potatosql.model.ListModelParentNPK;
import io.github.xjrga.potatosql.model.ListModelParentPK;
import io.github.xjrga.potatosql.model.ListModelSchema;
import io.github.xjrga.potatosql.model.ListModelTable;
import io.github.xjrga.potatosql.model.TableModelKeys;
import io.github.xjrga.potatosql.model.TableModelRelationship;
import io.github.xjrga.potatosql.model.TableModelRelationshipKeyPair;
import io.github.xjrga.potatosql.model.Unselectable_selection_model;
import io.github.xjrga.potatosql.other.ImageUtilities;
import io.github.xjrga.potatosql.other.String_check;
import io.github.xjrga.potatosql.other.Write;
import io.github.xjrga.potatosql.other.Xml_receive;
import io.github.xjrga.potatosql.other.Xml_send;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

public class Main {

    private CellConstraints cc;
    private Dblink dblink;
    private JButton reset;
    private JButton clear;
    private JCheckBox cboxStmtCreateSelect;
    private JCheckBox cboxStmtInsertSelect;
    private JCheckBox cboxStmtCount;
    private JCheckBox cboxFunctionCount;
    private JCheckBox cboxMethodFunctionCount;
    private JCheckBox cboxTriggerStatementAfterInsert;
    private JCheckBox cboxTriggerStatementAfterUpdate;
    private JCheckBox cboxTriggerStatementAfterDelete;
    private JCheckBox cboxTriggerRowBeforeInsert;
    private JCheckBox cboxTriggerRowBeforeUpdate;
    private JCheckBox cboxTriggerRowBeforeDelete;
    private JCheckBox cboxTriggerRowAfterInsert;
    private JCheckBox cboxTriggerRowAfterUpdate;
    private JCheckBox cboxTriggerRowAfterDelete;
    private JCheckBox cboxViews;
    private JCheckBox cboxTestClass;
    private JCheckBox cboxTables;
    private JCheckBox cboxProcUpdate;
    private JCheckBox cboxProcDelete;
    private JCheckBox cboxProcMerge;
    private JCheckBox cboxProcSelect;
    private JCheckBox cboxProcDeleteAll;
    private JCheckBox cboxProcSelectAll;
    private JCheckBox cboxMethodInsert;
    private JCheckBox cboxMethodUpdate;
    private JCheckBox cboxMethodDelete;
    private JCheckBox cboxMethodMerge;
    private JCheckBox cboxStmtInsert;
    private JCheckBox cboxMethodSelect;
    private JCheckBox cboxMethodDeleteAll;
    private JCheckBox cboxMethodSelectAll;
    private JCheckBox cboxStmtUpdate;
    private JCheckBox cboxStmtDelete;
    private JCheckBox cboxStmtMerge;
    private JCheckBox cboxStmtSelect;
    private JCheckBox cboxStmtDeleteAll;
    private JCheckBox cboxStmtSelectAll;
    private JCheckBox cboxProcInsert;
    private JCheckBox cbox_data_transfer_object;
    private JCheckBox cbox_generic_data_access_object;
    private JCheckBox cbox_data_access_object_interface;
    private JCheckBox cboxMethodSelectPrint;
    private JCheckBox cboxMethodSelectAllPrint;
    private JCheckBox cboxMethodFunctionCountPrint;
    private JFrame frame;
    private JList listKeyTypes;
    private JList<O_table> listChild;
    private JList<O_key> listChildNPK;
    private JList<O_key> listChildPK;
    private JList<O_table> listParent;
    private JList<O_key> listParentNPK;
    private JList<O_key> listParentPK;
    private JList<O_schema> listSchema;
    private JList<O_table> listTable;
    private JMenuItem mnuiOptions;
    private JRadioButton btnIdentifying;
    private JRadioButton btnNonIdentifying;
    private JRadioButton btnPK_No;
    private JRadioButton btnPK_Yes;
    private JTable tableKeys;
    private JTable tableRelationshipFacts;
    private JTable tableRelationshipPairKeys;
    private JTextArea textArea;
    private JTextField keyName;
    private JTextField keyOrder;
    private ListModelChildNPK listModelChildNPK;
    private ListModelChildPK listModelChildPK;
    private ListModelParentNPK listModelParentNPK;
    private ListModelParentPK listModelParentPK;
    private ListModelSchema listModelSchema;
    private ListModelTable listModelTable;
    private TableModelKeys tableModelKeys;
    private TableModelRelationshipKeyPair tableModelRelationshipKeyPair;
    private TableModelRelationship tableModelRelationship;
    private JRadioButtonMenuItem mnuiDialectHsqldb;
    private JRadioButtonMenuItem mnuiDialectMariadb;
    private JRadioButtonMenuItem mnuiDialectDot;
    private ListModelKeyTypes listModelKeyTypes;
    private final BufferedImage logo = ImageUtilities.readImage( "resources/logo.png" );
    private Color desktop_color;
    private JFileChooser fileChooser;

    public Main() {
        setLookAndFeel();
        initialize();
    }

    public static void main( String[] args ) {
        Main main = new Main();
    }

    private void setLookAndFeel() {
        try {
            Font font = new Font( Font.DIALOG, Font.PLAIN, 12 );
            MetalTheme dawn = new Dawn_150( font );
            desktop_color = dawn.getDesktopColor();
            MetalLookAndFeel.setCurrentTheme( dawn );
            UIManager.setLookAndFeel( "javax.swing.plaf.metal.MetalLookAndFeel" );
        } catch ( ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e ) {
        }
    }

    private void initialize() {
        fileChooser = new JFileChooser();
        cboxStmtCreateSelect = new JCheckBox( "Generate table using select" );
        cboxStmtInsertSelect = new JCheckBox( "Generate insert using select" );
        cboxStmtCount = new JCheckBox( "" );
        cboxFunctionCount = new JCheckBox( "" );
        cboxFunctionCount.setSelected( true );
        cboxMethodFunctionCount = new JCheckBox( "" );
        cboxTriggerStatementAfterInsert = new JCheckBox( "" );
        cboxTriggerStatementAfterUpdate = new JCheckBox( "" );
        cboxTriggerStatementAfterDelete = new JCheckBox( "" );
        cboxTriggerRowBeforeInsert = new JCheckBox( "" );
        cboxTriggerRowBeforeUpdate = new JCheckBox( "" );
        cboxTriggerRowBeforeDelete = new JCheckBox( "" );
        cboxTriggerRowAfterInsert = new JCheckBox( "" );
        cboxTriggerRowAfterUpdate = new JCheckBox( "" );
        cboxTriggerRowAfterDelete = new JCheckBox( "" );
        cboxViews = new JCheckBox( "Generate views" );
        cboxTestClass = new JCheckBox( "Generate test class" );
        cbox_generic_data_access_object = new JCheckBox( "Generate generic data access object interface" );
        cbox_data_access_object_interface = new JCheckBox( "Generate data access object interface" );
        cbox_data_transfer_object = new JCheckBox( "Generate data transfer object" );
        cboxTables = new JCheckBox( "Generate tables and relationships" );
        cboxTables.setSelected( true );
        cboxStmtInsert = new JCheckBox( "" );
        cboxStmtUpdate = new JCheckBox( "" );
        cboxStmtDelete = new JCheckBox( "" );
        cboxStmtMerge = new JCheckBox( "" );
        cboxStmtSelect = new JCheckBox( "" );
        cboxStmtDeleteAll = new JCheckBox( "" );
        cboxStmtSelectAll = new JCheckBox( "" );
        cboxProcInsert = new JCheckBox( "" );
        cboxProcInsert.setSelected( true );
        cboxProcUpdate = new JCheckBox( "" );
        cboxProcUpdate.setSelected( true );
        cboxProcDelete = new JCheckBox( "" );
        cboxProcDelete.setSelected( true );
        cboxProcMerge = new JCheckBox( "" );
        cboxProcMerge.setSelected( true );
        cboxProcSelect = new JCheckBox( "" );
        cboxProcSelect.setSelected( true );
        cboxProcDeleteAll = new JCheckBox( "" );
        cboxProcDeleteAll.setSelected( true );
        cboxProcSelectAll = new JCheckBox( "" );
        cboxProcSelectAll.setSelected( true );
        cboxMethodInsert = new JCheckBox( "" );
        cboxMethodUpdate = new JCheckBox( "" );
        cboxMethodDelete = new JCheckBox( "" );
        cboxMethodMerge = new JCheckBox( "" );
        cboxMethodSelect = new JCheckBox( "" );
        cboxMethodDeleteAll = new JCheckBox( "" );
        cboxMethodSelectAll = new JCheckBox( "" );
        cboxMethodSelectPrint = new JCheckBox( "" );
        cboxMethodSelectAllPrint = new JCheckBox( "" );
        cboxMethodFunctionCountPrint = new JCheckBox( "" );
        cc = new CellConstraints();
        dblink = new Dblink();
        reset = new JButton( "Reset" );
        clear = new JButton( "Clear" );
        keyName = new JTextField();
        listKeyTypes = new JList<>();
        btnPK_Yes = new JRadioButton();
        btnPK_No = new JRadioButton();
        keyOrder = new JTextField();
        listParent = new JList<>();
        listChild = new JList<>();
        btnIdentifying = new JRadioButton();
        btnNonIdentifying = new JRadioButton();
        listParentPK = new JList<>();
        listParentNPK = new JList();
        listChildPK = new JList<>();
        listChildNPK = new JList<>();
        mnuiDialectHsqldb = new JRadioButtonMenuItem();
        mnuiDialectMariadb = new JRadioButtonMenuItem();
        mnuiDialectDot = new JRadioButtonMenuItem();
        listModelParentPK = new ListModelParentPK( dblink );
        listModelParentNPK = new ListModelParentNPK( dblink );
        listModelChildPK = new ListModelChildPK( dblink );
        listModelChildNPK = new ListModelChildNPK( dblink );
        listModelKeyTypes = new ListModelKeyTypes( dblink );
        listModelKeyTypes.reload();
        listKeyTypes.setModel( listModelKeyTypes );
        listSchema = new JList<>();
        listModelSchema = new ListModelSchema( dblink );
        listSchema.setModel( listModelSchema );
        listSchema.setValueIsAdjusting( true );
        listModelSchema.reload();
        mnuiOptions = new JMenuItem();
        textArea = new JTextArea();
        listTable = new JList<>();
        listTable.setValueIsAdjusting( true );
        listModelTable = new ListModelTable( dblink );
        listParent.setModel( listModelTable );
        listChild.setModel( listModelTable );
        listTable.setModel( listModelTable );
        tableKeys = new JTable();
        tableModelKeys = new TableModelKeys( dblink );
        tableKeys.setModel( tableModelKeys );
        tableRelationshipFacts = new JTable();
        tableModelRelationship = new TableModelRelationship( dblink );
        tableRelationshipFacts.setModel( tableModelRelationship );
        listParentPK.setModel( listModelParentPK );
        listParentNPK.setModel( listModelParentNPK );
        listChildPK.setModel( listModelChildPK );
        listChildNPK.setModel( listModelChildNPK );
        tableRelationshipPairKeys = new JTable();
        tableModelRelationshipKeyPair = new TableModelRelationshipKeyPair( dblink );
        tableRelationshipPairKeys.setModel( tableModelRelationshipKeyPair );
        frame = get_frame();
        frame.setSize( new Dimension( 1150, 800 ) );
        frame.setVisible( true );
        frame.setIconImage( logo );
        reset.addActionListener( ( ActionEvent e ) ->
        {
            clear_options();
            set_default_options();
        } );
        clear.addActionListener( ( ActionEvent e ) ->
        {
            clear_options();
        } );
        mnuiDialectMariadb.addActionListener( ( ActionEvent e ) ->
        {
            disable_trigger_statement_options();
        } );
        mnuiDialectHsqldb.addActionListener( ( ActionEvent e ) ->
        {
            enable_trigger_statements_options();
        } );
    }

    private JFrame get_frame() {
        JFrame local_frame = new JFrame();
        local_frame.setDefaultCloseOperation( 3 );
        local_frame.setTitle( "Potatosql" );
        local_frame.setJMenuBar( get_menu_bar() );
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView( get_main_panel() );
        local_frame.add( scrollPane );
        local_frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e ) {
                eventActionPerformed_mnuiExit();
            }
        } );
        return local_frame;
    }

    private JPanel get_main_panel() {
        FormLayout layout = new FormLayout( "min,min:grow", //columns
                                            "fill:min:grow" //rows
        );
        JPanel panel = new JPanel();
        panel.setPreferredSize( new Dimension( 1132, 700 ) );
        JTabbedPane tabbedPane = new JTabbedPane();
        panel.setLayout( layout );
        tabbedPane.setTabPlacement( SwingConstants.BOTTOM );
        tabbedPane.add( get_panel_input_1() );
        tabbedPane.add( get_panel_input_2() );
        tabbedPane.add( get_panel_output() );
        tabbedPane.setTitleAt( 0, "Table" );
        tabbedPane.setTitleAt( 1, "Relationship" );
        tabbedPane.setTitleAt( 2, "Output" );
        panel.add( get_panel_schema(), cc.xy( 1, 1 ) );
        panel.add( tabbedPane, cc.xy( 2, 1 ) );
        return panel;
    }

    private void btnGenerate_actionPerformed() {
        if ( has_schema_list_been_selected() ) {
            StringBuilder sb = new StringBuilder();
            DialectBuilder dialectBuilder = new DialectBuilder( dblink );
            O_schema schema = ( O_schema ) listSchema.getSelectedValue();
            int numberOfTables = listModelTable.getSize();
            ArrayList<O_table> table_list = new ArrayList();
            for ( int i = 0; i < numberOfTables; i++ ) {
                O_table table = ( O_table ) listModelTable.getElementAt( i );
                table_list.add( table );
            }
            dialectBuilder.set_oschema( schema );
            dialectBuilder.set_otables( table_list );
            dialectBuilder.is_hsqldb_selected( mnuiDialectHsqldb.isSelected() );
            dialectBuilder.is_mariadb_selected( mnuiDialectMariadb.isSelected() );
            dialectBuilder.is_dot_selected( mnuiDialectDot.isSelected() );
            dialectBuilder.is_create_table_with_select_selected( cboxStmtCreateSelect.isSelected() );
            dialectBuilder.is_insert_using_select_statement_selected( cboxStmtInsertSelect.isSelected() );
            dialectBuilder.is_count_statement_selected( cboxStmtCount.isSelected() );
            dialectBuilder.is_count_function_selected( cboxFunctionCount.isSelected() );
            dialectBuilder.is_count_function_method_selected( cboxMethodFunctionCount.isSelected() );
            dialectBuilder.is_statement_level_after_insert_event_trigger_selected( cboxTriggerStatementAfterInsert.isSelected() );
            dialectBuilder.is_statement_level_after_update_event_trigger_selected( cboxTriggerStatementAfterUpdate.isSelected() );
            dialectBuilder.is_statement_level_after_delete_event_trigger_selected( cboxTriggerStatementAfterDelete.isSelected() );
            dialectBuilder.is_row_level_before_insert_event_trigger_selected( cboxTriggerRowBeforeInsert.isSelected() );
            dialectBuilder.is_row_level_before_update_event_trigger_selected( cboxTriggerRowBeforeUpdate.isSelected() );
            dialectBuilder.is_row_level_before_delete_event_trigger_selected( cboxTriggerRowBeforeDelete.isSelected() );
            dialectBuilder.is_row_level_after_insert_event_trigger_selected( cboxTriggerRowAfterInsert.isSelected() );
            dialectBuilder.is_row_level_after_update_event_trigger_selected( cboxTriggerRowAfterUpdate.isSelected() );
            dialectBuilder.is_row_level_after_delete_event_trigger_selected( cboxTriggerRowAfterDelete.isSelected() );
            dialectBuilder.is_view_selected( cboxViews.isSelected() );
            dialectBuilder.is_test_class_selected( cboxTestClass.isSelected() );
            dialectBuilder.is_tables_and_relationships_selected( cboxTables.isSelected() );
            dialectBuilder.is_insert_statement_selected( cboxStmtInsert.isSelected() );
            dialectBuilder.is_update_statement_selected( cboxStmtUpdate.isSelected() );
            dialectBuilder.is_delete_statement_selected( cboxStmtDelete.isSelected() );
            dialectBuilder.is_merge_statement_selected( cboxStmtMerge.isSelected() );
            dialectBuilder.is_select_statement_selected( cboxStmtSelect.isSelected() );
            dialectBuilder.is_delete_all_statement_selected( cboxStmtDeleteAll.isSelected() );
            dialectBuilder.is_select_all_statement_selected( cboxStmtSelectAll.isSelected() );
            dialectBuilder.is_insert_procedure_selected( cboxProcInsert.isSelected() );
            dialectBuilder.is_update_procedure_selected( cboxProcUpdate.isSelected() );
            dialectBuilder.is_delete_procedure_selected( cboxProcDelete.isSelected() );
            dialectBuilder.is_merge_procedure_selected( cboxProcMerge.isSelected() );
            dialectBuilder.is_select_procedure_selected( cboxProcSelect.isSelected() );
            dialectBuilder.is_delete_all_procedure_selected( cboxProcDeleteAll.isSelected() );
            dialectBuilder.is_select_all_procedure_selected( cboxProcSelectAll.isSelected() );
            dialectBuilder.is_insert_method_selected( cboxMethodInsert.isSelected() );
            dialectBuilder.is_update_method_selected( cboxMethodUpdate.isSelected() );
            dialectBuilder.is_delete_method_selected( cboxMethodDelete.isSelected() );
            dialectBuilder.is_merge_method_selected( cboxMethodMerge.isSelected() );
            dialectBuilder.is_select_method_selected( cboxMethodSelect.isSelected() );
            dialectBuilder.is_delete_all_method_selected( cboxMethodDeleteAll.isSelected() );
            dialectBuilder.is_select_all_method_selected( cboxMethodSelectAll.isSelected() );
            dialectBuilder.is_generate_generic_data_access_object_selected( cbox_generic_data_access_object.isSelected() );
            dialectBuilder.is_generate_data_transfer_object_option_selected( cbox_data_transfer_object.isSelected() );
            dialectBuilder.is_generate_data_access_object_option_selected( cbox_data_access_object_interface.isSelected() );
            dialectBuilder.is_print_select_method_selected( cboxMethodSelectPrint.isSelected() );
            dialectBuilder.is_print_select_all_method_selected( cboxMethodSelectAllPrint.isSelected() );
            dialectBuilder.is_print_function_count_method_selected( cboxMethodFunctionCountPrint.isSelected() );
            sb.append( dialectBuilder.get_code() );
            String toString = sb.toString();
            textArea.setText( toString );
            Write.writeToFile( toString, "code.txt" );
        } else {
            Message.showMessage( "Please select schema" );
        }
    }

    private boolean has_schema_list_been_selected() {
        return !listSchema.isSelectionEmpty();
    }

    private JMenuBar get_menu_bar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu mnuProgram = new JMenu();
        JMenu mnuKey = new JMenu();
        JMenu mnuOutput = new JMenu();
        JMenu mnuDialect = new JMenu();
        JMenu mnuHelp = new JMenu();
        JMenu menu_exchange = new JMenu();
        JMenuItem mnuiExit = new JMenuItem();
        ButtonGroup dialectGroup = new ButtonGroup();
        JMenuItem mnuItemProject = new JMenuItem();
        JMenuItem mnuItemQuickStart = new JMenuItem();
        JMenuItem mnuItemCredits = new JMenuItem();
        JMenuItem mnuItemAbout = new JMenuItem();
        JMenuItem menui_export_message = new JMenuItem();
        JMenuItem menui_import_message = new JMenuItem();
        dialectGroup.add( mnuiDialectHsqldb );
        dialectGroup.add( mnuiDialectMariadb );
        dialectGroup.add( mnuiDialectDot );
        menuBar.add( mnuProgram );
        menuBar.add( menu_exchange );
        menuBar.add( mnuOutput );
        menuBar.add( mnuDialect );
        menuBar.add( mnuHelp );
        mnuProgram.add( mnuiExit );
        mnuOutput.add( mnuiOptions );
        mnuDialect.add( mnuiDialectHsqldb );
        mnuDialect.add( mnuiDialectMariadb );
        mnuDialect.add( mnuiDialectDot );
        mnuHelp.add( mnuItemProject );
        mnuHelp.add( mnuItemQuickStart );
        mnuHelp.add( mnuItemCredits );
        mnuHelp.add( mnuItemAbout );
        menu_exchange.add( menui_import_message );
        menu_exchange.add( menui_export_message );
        mnuProgram.setText( "Program" );
        mnuKey.setText( "Key" );
        mnuOutput.setText( "Output" );
        mnuDialect.setText( "Dialect" );
        menu_exchange.setText( "Data" );
        mnuHelp.setText( "Help" );
        mnuiExit.setText( "Exit" );
        mnuiOptions.setText( "Options" );
        mnuiDialectHsqldb.setText( "Hsqldb" );
        mnuiDialectMariadb.setText( "Mariadb" );
        mnuItemProject.setText( "Project" );
        mnuItemQuickStart.setText( "Quick Start" );
        mnuItemCredits.setText( "Credits" );
        mnuItemAbout.setText( "About" );
        menui_import_message.setText( "Import" );
        menui_export_message.setText( "Export" );
        mnuiDialectDot.setText( "Dot" );
        mnuiDialectDot.setSelected( true );
        mnuiOptions.addActionListener( ( ActionEvent e ) ->
        {
            select_options();
        } );
        mnuiExit.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_mnuiExit();
        } );
        mnuItemProject.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_menuItemProject();
        } );
        mnuItemQuickStart.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_menuItemQuickStart();
        } );
        mnuItemCredits.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_menuItemCredits();
        } );
        mnuItemAbout.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_menuItemAbout();
        } );
        menui_export_message.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_send_message();
        } );
        menui_import_message.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_receive_message();
        } );
        return menuBar;
    }

    private void eventActionPerformed_mnuiSchemaCopy() {
        if ( has_schema_list_been_selected() ) {
            O_schema o_schema = ( O_schema ) listSchema.getSelectedValue();
            StringBuilder sb = new StringBuilder();
            sb.append( o_schema.getSchema_name() );
            sb.append( "_copy" );
            o_schema.setSchema_name( sb.toString() );
            dblink.schema_copy( o_schema );
            listModelSchema.reload();
        } else {
            Message.showMessage( "Please select schema" );
        }
    }

    private void eventActionPerformed_mnuiTableCopy() {
        if ( has_schema_list_been_selected() ) {
            if ( has_table_list_been_selected() ) {
                O_schema o_schema = ( O_schema ) listSchema.getSelectedValue();
                O_table o_table = ( O_table ) listTable.getSelectedValue();
                Integer schema_id = o_schema.getSchema_id();
                Integer table_id = o_table.getTable_id();
                String table_name = o_table.getTable_name();
                StringBuilder sb = new StringBuilder();
                sb.append( table_name );
                sb.append( "_copy" );
                O_table table = new O_table();
                table.setSchema_id( schema_id );
                table.setTable_id( table_id );
                table.setTable_name( sb.toString() );
                dblink.table_copy( table );
                listModelTable.reload( o_schema );
            } else {
                Message.showMessage( "Please select table" );
            }
        } else {
            Message.showMessage( "Please select schema" );
        }
    }

    private JPanel get_panel_input_1() {
        FormLayout layout = new FormLayout( "min,min:grow", //columns
                                            "fill:min:grow" //rows
        );
        JPanel panel = new JPanel();
        panel.setLayout( layout );
        panel.add( get_panel_table(), cc.xy( 1, 1 ) );
        panel.add( get_panel_table_keys(), cc.xy( 2, 1 ) );
        return panel;
    }

    private JPanel get_panel_input_2() {
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,fill:min:grow" //rows
        );
        JPanel panel = new JPanel();
        panel.setLayout( layout );
        panel.add( get_panel_relationship(), cc.xy( 1, 1 ) );
        panel.add( get_panel_relationship_pair_keys(), cc.xy( 1, 2 ) );
        return panel;
    }

    private JPanel get_panel_relationship_key_input( String parent_name, String child_name ) {
        FormLayout layout = new FormLayout( "min:grow,min:grow", //columns
                                            "fill:min:grow" //rows
        );
        JPanel panel = new JPanel();
        panel.setLayout( layout );
        panel.add( get_panel_parent_keys( parent_name ), cc.xy( 1, 1 ) );
        panel.add( get_panel_child_keys( child_name ), cc.xy( 2, 1 ) );
        return panel;
    }

    private JPanel get_panel_relationship_input() {
        FormLayout layout = new FormLayout( "min:grow,min:grow", //columns
                                            "fill:min:grow,min" //rows
        );
        JPanel panel = new JPanel();
        panel.setLayout( layout );
        panel.setBorder( new TitledBorder( "Relationship" ) );
        panel.add( get_panel_parent(), cc.xy( 1, 1 ) );
        panel.add( get_panel_child(), cc.xy( 2, 1 ) );
        panel.add( get_panel_type(), cc.xyw( 1, 2, 2 ) );
        return panel;
    }

    private JPanel get_panel_type() {
        JPanel panel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        panel.setBorder( new TitledBorder( "Relationship Type" ) );
        btnIdentifying.setText( "Identifying" );
        btnIdentifying.setToolTipText( "<html>Child entity is dependent on parent entity for identification and existence.<br>All primary key attributes of parent table become primary key attributes of child table.<html>" );
        btnNonIdentifying.setText( "Nonidentifying" );
        btnNonIdentifying.setToolTipText( "<html>Child entity is not dependent on parent entity for identification.<<br>All primary key attributes of parent table become non-primary-key attributes of child table</html>" );
        buttonGroup.add( btnIdentifying );
        buttonGroup.add( btnNonIdentifying );
        panel.add( btnIdentifying );
        panel.add( btnNonIdentifying );
        return panel;
    }

    private JPanel get_panel_output() {
        FormLayout layout = new FormLayout( "min:grow,min,min,min", //columns
                                            "fill:min:grow,min" //rows
        );
        JPanel panel = new JPanel();
        JButton btnGenerate = new JButton( "Generate" );
        JButton btnCopy = new JButton( "  Copy  " );
        JButton btnClear = new JButton( "  Clear " );
        btnGenerate.setToolTipText( "Generate code" );
        btnCopy.setToolTipText( "Copy to clipboard" );
        btnClear.setToolTipText( "Clear text area" );
        panel.setLayout( layout );
        panel.setBorder( new TitledBorder( "Output" ) );
        panel.add( new JScrollPane( textArea ), cc.xyw( 1, 1, 4 ) );
        panel.add( btnGenerate, cc.xy( 2, 2 ) );
        panel.add( btnClear, cc.xy( 3, 2 ) );
        panel.add( btnCopy, cc.xy( 4, 2 ) );
        btnGenerate.addActionListener( e ->
                btnGenerate_actionPerformed() );
        btnCopy.addActionListener( ( ActionEvent e ) ->
        {
            String text = textArea.getText();
            StringSelection stringSelection = new StringSelection( text );
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents( stringSelection, null );
        } );
        btnClear.addActionListener( e ->
        {
            textArea.setText( "" );
        } );
        return panel;
    }

    private JPanel get_panel_table() {
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,min" //rows
        );
        JPanel panel = new JPanel();
        JScrollPane scrollTable = new JScrollPane( listTable );
        panel.setLayout( layout );
        panel.setBorder( new TitledBorder( "Table" ) );
        panel.setMinimumSize( new Dimension( 250, 0 ) );
        panel.add( scrollTable, cc.xy( 1, 1 ) );
        panel.add( get_panel_table_buttons(), cc.xy( 1, 2 ) );
        listTable.addListSelectionListener( ( ListSelectionEvent e ) ->
        {
            eventListSelection_listTable();
        } );
        return panel;
    }

    private void eventListSelection_listTable() {
        if ( has_table_list_been_selected() ) {
            O_table table = ( O_table ) listTable.getSelectedValue();
            O_key_with_name key = new O_key_with_name();
            key.setSchema_id( table.getSchema_id() );
            key.setTable_id( table.getTable_id() );
            tableModelKeys.reload( key );
            hideTableKeysColumns();
        }
    }

    private JPanel get_panel_table_key_input_add() {
        FormLayout layout = new FormLayout( "min,min:grow", //columns
                                            "min,min,min,min,4dlu,min" //rows
        );
        JPanel panel = new JPanel();
        Label labelName = new Label( "Name: " );
        Label labelType = new Label( "Type: " );
        Label labelIsPrimaryKey = new Label( "Primary Key? " );
        Label labelOrder = new Label( "Order: " );
        JScrollPane scrollPaneForListAttributeTypes = new JScrollPane( listKeyTypes );
        ButtonGroup primaryKeyGroup = new ButtonGroup();
        JPanel panelForPrimaryKey = new JPanel();
        panel.setLayout( layout );
        panel.setBorder( new TitledBorder( "Definition" ) );
        labelName.setAlignment( Label.RIGHT );
        labelType.setAlignment( Label.RIGHT );
        labelIsPrimaryKey.setAlignment( Label.RIGHT );
        labelOrder.setAlignment( Label.RIGHT );
        keyName.setMinimumSize( new Dimension( 100, 26 ) );
        scrollPaneForListAttributeTypes.setMinimumSize( new Dimension( 0, 100 ) );
        keyOrder.setMinimumSize( new Dimension( 100, 26 ) );
        btnPK_Yes.setText( "Yes" );
        btnPK_No.setText( "No" );
        primaryKeyGroup.add( btnPK_Yes );
        primaryKeyGroup.add( btnPK_No );
        panelForPrimaryKey.add( btnPK_Yes );
        panelForPrimaryKey.add( btnPK_No );
        panel.add( labelName, cc.xy( 1, 1 ) );
        panel.add( keyName, cc.xy( 2, 1 ) );
        panel.add( labelType, cc.xy( 1, 2 ) );
        panel.add( scrollPaneForListAttributeTypes, cc.xy( 2, 2 ) );
        panel.add( labelIsPrimaryKey, cc.xy( 1, 3 ) );
        panel.add( panelForPrimaryKey, cc.xy( 2, 3 ) );
        panel.add( labelOrder, cc.xy( 1, 4 ) );
        panel.add( keyOrder, cc.xy( 2, 4 ) );
        return panel;
    }

    private JPanel get_panel_table_key_input_update() {
        FormLayout layout = new FormLayout( "min,min:grow", //columns
                                            "min,2dlu,min,2dlu,min,2dlu" //rows
        );
        JPanel panel = new JPanel();
        Label labelName = new Label( "Name: " );
        Label labelType = new Label( "Type: " );
        Label labelOrder = new Label( "Order: " );
        JScrollPane scrollPaneForListAttributeTypes = new JScrollPane( listKeyTypes );
        panel.setLayout( layout );
        panel.setBorder( new TitledBorder( "Definition" ) );
        labelName.setAlignment( Label.RIGHT );
        labelType.setAlignment( Label.RIGHT );
        labelOrder.setAlignment( Label.RIGHT );
        keyName.setMinimumSize( new Dimension( 100, 26 ) );
        scrollPaneForListAttributeTypes.setMinimumSize( new Dimension( 0, 100 ) );
        keyOrder.setMinimumSize( new Dimension( 100, 26 ) );
        btnPK_Yes.setText( "Yes" );
        btnPK_No.setText( "No" );
        panel.add( labelName, cc.xy( 1, 1 ) );
        panel.add( keyName, cc.xy( 2, 1 ) );
        panel.add( labelType, cc.xy( 1, 3 ) );
        panel.add( scrollPaneForListAttributeTypes, cc.xy( 2, 3 ) );
        panel.add( labelOrder, cc.xy( 1, 5 ) );
        panel.add( keyOrder, cc.xy( 2, 5 ) );
        return panel;
    }

    private JPanel get_panel_table_keys() {
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,min" //rows
        );
        JPanel panel = new JPanel();
        panel.setLayout( layout );
        panel.setBorder( new TitledBorder( "Table Keys" ) );
        panel.add( new JScrollPane( tableKeys ), cc.xy( 1, 1 ) );
        panel.add( get_panel_table_key_buttons(), cc.xy( 1, 2 ) );
        hideTableKeysColumns();
        return panel;
    }

    private void clearPanelTableKeyInput() {
        keyName.setText( "" );
        listKeyTypes.setSelectedIndex( 0 );
        btnPK_Yes.setSelected( false );
        btnPK_No.setSelected( true );
        keyOrder.setText( "" );
    }

    private JPanel get_panel_parent() {
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow" //rows
        );
        JPanel panel = new JPanel();
        panel.setLayout( layout );
        panel.setBorder( new TitledBorder( "Parent Table" ) );
        panel.add( new JScrollPane( listParent ), cc.xy( 1, 1 ) );
        return panel;
    }

    private JPanel get_panel_child() {
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow" //rows
        );
        JPanel panel = new JPanel();
        panel.setBorder( new TitledBorder( "Child Table" ) );
        panel.setLayout( layout );
        panel.add( new JScrollPane( listChild ), cc.xy( 1, 1 ) );
        return panel;
    }

    private JPanel get_panel_relationship() {
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,min" //rows
        );
        JPanel panel = new JPanel();
        JScrollPane panelRelationshipFacts = new JScrollPane( tableRelationshipFacts );
        JPanel panelRelationshipButtons = new JPanel();
        JButton btnRelationshipAdd = new JButton( "+" );
        JButton btnRelationshipDelete = new JButton( "-" );
        btnRelationshipAdd.setToolTipText( "Add relationship that connects parent to child" );
        btnRelationshipDelete.setToolTipText( "Delete relationship" );
        panel.setLayout( layout );
        panel.setBorder( new TitledBorder( "Relationship" ) );
        panel.add( panelRelationshipFacts, cc.xy( 1, 1 ) );
        panel.add( panelRelationshipButtons, cc.xy( 1, 2 ) );
        panelRelationshipButtons.add( btnRelationshipAdd );
        panelRelationshipButtons.add( btnRelationshipDelete );
        hideTableRelationshipColumns();
        tableRelationshipFacts.getSelectionModel().addListSelectionListener( (
                ListSelectionEvent e ) ->
        {
            eventListSelection_tableRelationship( e );
        } );
        btnRelationshipAdd.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnRelationshipAdd();
        } );
        btnRelationshipDelete.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnRelationshipDelete();
        } );
        return panel;
    }

    private void eventListSelection_tableRelationship( ListSelectionEvent e ) {
        if ( e.getValueIsAdjusting() ) {
            return;
        }
        Integer selectedRow = tableRelationshipFacts.getSelectedRow();
        if ( selectedRow != -1 ) {
            Integer relationshipid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 0 );
            Integer schemaid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 1 );
            Integer parent_tableid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 2 );
            Integer child_tableid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 4 );
            O_relationship relationship = new O_relationship();
            relationship.setSchema_id( schemaid );
            relationship.setParent_table_id( parent_tableid );
            relationship.setChild_table_id( child_tableid );
            relationship.setRelationship_id( relationshipid );
            tableModelRelationshipKeyPair.reload( relationship );
            hideTableRelationshipKeyPairColumns();
        }
    }

    private void eventActionPerformed_btnRelationshipAdd() {
        clearPanelRelationshipInput();
        JPanel panelRelationshipInput = get_panel_relationship_input();
        panelRelationshipInput.setPreferredSize( new Dimension( 700, 433 ) );
        JComponent[] inputs = new JComponent[] {
            panelRelationshipInput
        };
        int i = Message.showOptionDialogOkCancel( inputs, "Relationship" );
        if ( i == 0 ) {
            O_table parent = ( O_table ) listParent.getSelectedValue();
            O_table child = ( O_table ) listChild.getSelectedValue();
            Integer schemaid = parent.getSchema_id();
            Integer parent_tableid = parent.getTable_id();
            Integer child_tableid = child.getTable_id();
            Integer relationshiptypeid;

            if ( btnIdentifying.isSelected() ) {
                relationshiptypeid = 0;
            } else {
                relationshiptypeid = 1;
            }
            O_relationship relationship = new O_relationship();
            relationship.setSchema_id( schemaid );
            relationship.setParent_table_id( parent_tableid );
            relationship.setChild_table_id( child_tableid );
            relationship.setRelationship_id( dblink.generate_id() );
            relationship.setRelationship_type_id( relationshiptypeid );
            dblink.relationship_insert( relationship );
            O_schema schema = new O_schema();
            schema.setSchema_id( schemaid );
            tableModelRelationship.reload( schema );
            hideTableRelationshipColumns();
        }
    }

    private void clearPanelRelationshipInput() {
        listParent.setSelectedValue( listParent.getModel().getElementAt( 0 ), true );
        listChild.setSelectedValue( listChild.getModel().getElementAt( 0 ), true );
        btnIdentifying.setSelected( true );
        btnNonIdentifying.setSelected( false );
    }

    private void eventActionPerformed_btnRelationshipDelete() {
        Integer selectedRow = tableRelationshipFacts.getSelectedRow();
        if ( selectedRow != -1 ) {
            Integer relationshipid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 0 );
            Integer schemaid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 1 );
            Integer parent_tableid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 2 );
            Integer child_tableid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 4 );
            O_relationship relationship = new O_relationship();
            relationship.setSchema_id( schemaid );
            relationship.setParent_table_id( parent_tableid );
            relationship.setChild_table_id( child_tableid );
            relationship.setRelationship_id( relationshipid );
            dblink.relationship_delete( relationship );
            O_schema schema = new O_schema();
            schema.setSchema_id( schemaid );
            tableModelRelationship.reload( schema );
            tableModelRelationshipKeyPair.reload( relationship );
            hideTableRelationshipColumns();
            hideTableRelationshipKeyPairColumns();
        }
    }

    private JPanel get_panel_parent_keys( String parent_name ) {
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,fill:min:grow" //rows
        );
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane( listParentPK );
        JScrollPane scrollPane1 = new JScrollPane( listParentNPK );
        panel.setLayout( layout );
        panel.setBorder( new TitledBorder( "Parent Table: " + parent_name ) );
        scrollPane.setBorder( new TitledBorder( "Primary Key Area" ) );
        scrollPane1.setBorder( new TitledBorder( "Non Primary Key Area (Data Area)" ) );
        panel.add( scrollPane, cc.xy( 1, 1 ) );
        panel.add( scrollPane1, cc.xy( 1, 2 ) );
        return panel;
    }

    private JPanel get_panel_child_keys( String child_name ) {
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,fill:min:grow" //rows
        );
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane( listChildPK );
        JScrollPane scrollPane1 = new JScrollPane( listChildNPK );
        panel.setLayout( layout );
        panel.setBorder( new TitledBorder( "Child Table: " + child_name ) );
        scrollPane.setBorder( new TitledBorder( "Primary Key Area" ) );
        scrollPane1.setBorder( new TitledBorder( "Non Primary Key Area (Data Area)" ) );
        panel.add( scrollPane, cc.xy( 1, 1 ) );
        panel.add( scrollPane1, cc.xy( 1, 2 ) );
        return panel;
    }

    private JPanel get_panel_relationship_pair_keys() {
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,fill:min" //rows
        );
        JPanel panel = new JPanel();
        JPanel btnPanel = new JPanel();
        JScrollPane scrollPane2 = new JScrollPane( tableRelationshipPairKeys );
        JButton btnRelationshipKeyAdd = new JButton();
        JButton btnRelationshipKeyDelete = new JButton();
        btnRelationshipKeyAdd.setToolTipText( "Migrate primary key attribute from parent to child. A primary key attribute is or is part of primary key." );
        btnRelationshipKeyDelete.setToolTipText( "Delete primary key attribute migration" );
        panel.setLayout( layout );
        panel.setBorder( new TitledBorder( "Relationship Key Pair" ) );
        btnRelationshipKeyAdd.setText( "+" );
        btnRelationshipKeyDelete.setText( "-" );
        panel.add( scrollPane2, cc.xy( 1, 1 ) );
        panel.add( btnPanel, cc.xy( 1, 2 ) );
        btnPanel.add( btnRelationshipKeyAdd );
        btnPanel.add( btnRelationshipKeyDelete );
        hideTableRelationshipKeyPairColumns();
        btnRelationshipKeyAdd.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnRelationshipKeyAdd();
        } );
        btnRelationshipKeyDelete.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnRelationshipKeyDelete();
        } );
        return panel;
    }

    private void eventActionPerformed_btnRelationshipKeyAdd() {
        Integer selectedRow = tableRelationshipFacts.getSelectedRow();
        if ( selectedRow != -1 ) {
            Integer relationshipid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 0 );
            Integer schemaid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 1 );
            Integer parent_tableid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 2 );
            String parent_name = ( String ) tableRelationshipFacts.getValueAt( selectedRow, 3 );
            Integer child_tableid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 4 );
            String child_name = ( String ) tableRelationshipFacts.getValueAt( selectedRow, 5 );
            Integer relationshiptypeid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 6 );
            O_key_pair_multiple_select p = new O_key_pair_multiple_select();
            p.setSchema_id( schemaid );
            p.setParent_table_id( parent_tableid );
            p.setChild_table_id( child_tableid );
            p.setParent( parent_name );
            p.setChild( child_name );
            p.setRelationship_id( relationshipid );
            O_table parent = new O_table();
            parent.setSchema_id( schemaid );
            parent.setTable_id( parent_tableid );
            parent.setTable_name( parent_name );
            O_table child = new O_table();
            child.setSchema_id( schemaid );
            child.setTable_id( child_tableid );
            child.setTable_name( child_name );
            JPanel panel = get_panel_relationship_key_input( parent_name, child_name );
            panel.setPreferredSize( new Dimension( 700, 433 ) );
            listModelParentPK.reload( parent );
            listModelParentNPK.reload( parent );
            listModelChildPK.reload( child );
            listModelChildNPK.reload( child );
            listParentPK.setSelectionModel( new DefaultListSelectionModel() );
            listParentNPK.setSelectionModel( new DefaultListSelectionModel() );
            listChildPK.setSelectionModel( new DefaultListSelectionModel() );
            listChildNPK.setSelectionModel( new DefaultListSelectionModel() );
            if ( relationshiptypeid == 0 ) {
                listParentPK.setBackground( desktop_color );
                listParentNPK.setBackground( Color.white );
                listChildPK.setBackground( desktop_color );
                listChildNPK.setBackground( Color.white );
                listParentNPK.setSelectionModel( new Unselectable_selection_model() );
                listChildNPK.setSelectionModel( new Unselectable_selection_model() );
            } else {
                listParentPK.setBackground( desktop_color );
                listParentNPK.setBackground( Color.white );
                listChildPK.setBackground( Color.white );
                listChildNPK.setBackground( desktop_color );
                listParentNPK.setSelectionModel( new Unselectable_selection_model() );
                listChildPK.setSelectionModel( new Unselectable_selection_model() );
            }
            JComponent[] inputs = new JComponent[] {
                panel
            };
            int i = Message.showOptionDialogOkCancel( inputs, "Relationship Key Pair" );
            if ( i == 0 ) {
                if ( relationshiptypeid == 0 ) {
                    if ( has_listParentPK_been_selected() && has_listChildPK_been_selected() ) {
                        O_key parent_key = ( O_key ) listParentPK.getSelectedValue();
                        O_key child_key = ( O_key ) listChildPK.getSelectedValue();
                        Integer parent_keyid = parent_key.getTable_key_id();
                        Integer child_keyid = child_key.getTable_key_id();
                        O_relationship_key_pair pair = new O_relationship_key_pair();
                        pair.setSchema_id( schemaid );
                        pair.setParent_table_id( parent_tableid );
                        pair.setChild_table_id( child_tableid );
                        pair.setRelationship_id( relationshipid );
                        pair.setParent_key_id( parent_keyid );
                        pair.setChild_key_id( child_keyid );
                        dblink.relationship_key_pair_insert( pair );
                        O_relationship relationship = new O_relationship();
                        relationship.setSchema_id( schemaid );
                        relationship.setParent_table_id( parent_tableid );
                        relationship.setChild_table_id( child_tableid );
                        relationship.setRelationship_id( relationshipid );
                        tableModelRelationshipKeyPair.reload( relationship );
                        hideTableRelationshipKeyPairColumns();
                    }
                } else {
                    if ( has_listParentPK_been_selected() && has_listChildNPK_been_selected() ) {
                        O_key parent_key = ( O_key ) listParentPK.getSelectedValue();
                        O_key child_key = ( O_key ) listChildNPK.getSelectedValue();
                        Integer parent_keyid = parent_key.getTable_key_id();
                        Integer child_keyid = child_key.getTable_key_id();
                        O_relationship_key_pair pair = new O_relationship_key_pair();
                        pair.setSchema_id( schemaid );
                        pair.setParent_table_id( parent_tableid );
                        pair.setChild_table_id( child_tableid );
                        pair.setRelationship_id( relationshipid );
                        pair.setParent_key_id( parent_keyid );
                        pair.setChild_key_id( child_keyid );
                        dblink.relationship_key_pair_insert( pair );
                        O_relationship relationship = new O_relationship();
                        relationship.setSchema_id( schemaid );
                        relationship.setParent_table_id( parent_tableid );
                        relationship.setChild_table_id( child_tableid );
                        relationship.setRelationship_id( relationshipid );
                        tableModelRelationshipKeyPair.reload( relationship );
                        hideTableRelationshipKeyPairColumns();
                    }
                }
            }
        }
    }

    private boolean has_listParentPK_been_selected() {
        return !listParentPK.isSelectionEmpty();
    }

    private boolean has_listChildPK_been_selected() {
        return !listChildPK.isSelectionEmpty();
    }

    private boolean has_listChildNPK_been_selected() {
        return !listChildNPK.isSelectionEmpty();
    }

    private void eventActionPerformed_btnRelationshipKeyDelete() {
        Integer selectedRow = tableRelationshipFacts.getSelectedRow();
        if ( selectedRow != -1 ) {
            Integer relationshipid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 0 );
            Integer schemaid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 1 );
            Integer parent_tableid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 2 );
            Integer child_tableid = ( Integer ) tableRelationshipFacts.getValueAt( selectedRow, 4 );
            //Integer relationshiptypeid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 6);
            selectedRow = tableRelationshipPairKeys.getSelectedRow();
            if ( selectedRow != -1 ) {
                Integer parent_keyid = ( Integer ) tableRelationshipPairKeys.getValueAt( selectedRow, 0 );
                Integer child_keyid = ( Integer ) tableRelationshipPairKeys.getValueAt( selectedRow, 1 );
                O_relationship_key_pair pair = new O_relationship_key_pair();
                pair.setSchema_id( schemaid );
                pair.setParent_table_id( parent_tableid );
                pair.setChild_table_id( child_tableid );
                pair.setRelationship_id( relationshipid );
                pair.setParent_key_id( parent_keyid );
                pair.setChild_key_id( child_keyid );
                dblink.relationship_key_pair_delete( pair );
                O_relationship relationship = new O_relationship();
                relationship.setSchema_id( schemaid );
                relationship.setParent_table_id( parent_tableid );
                relationship.setChild_table_id( child_tableid );
                relationship.setRelationship_id( relationshipid );
                tableModelRelationshipKeyPair.reload( relationship );
                hideTableRelationshipKeyPairColumns();
            }
        }
    }

    private JPanel get_panel_schema() {
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,min" //rows
        );
        JPanel panel = new JPanel();
        panel.setLayout( layout );
        panel.setMinimumSize( new Dimension( 250, 0 ) );
        panel.setBorder( new TitledBorder( "Schema" ) );
        panel.add( new JScrollPane( listSchema ), cc.xy( 1, 1 ) );
        panel.add( get_panel_schema_buttons(), cc.xy( 1, 2 ) );
        listSchema.addListSelectionListener( ( ListSelectionEvent e ) ->
        {
            eventListSelection_listSchema();
        } );
        return panel;
    }

    private void eventListSelection_listSchema() {
        if ( has_schema_list_been_selected() ) {
            O_schema schema = ( O_schema ) listSchema.getSelectedValue();
            listModelTable.reload( schema );
            tableModelKeys.clear();
            hideTableKeysColumns();
            tableModelRelationship.reload( schema );
            hideTableRelationshipColumns();
            tableModelRelationshipKeyPair.clear();
            hideTableRelationshipKeyPairColumns();
        }
    }

    private JPanel get_panel_schema_buttons() {
        FormLayout layout = new FormLayout( "min:grow,min,min,min,min,min:grow", //columns
                                            "min" //rows
        );
        JPanel panel = new JPanel();
        JButton btnSchemaAdd = new JButton( "+" );
        JButton btnSchemaDelete = new JButton( "-" );
        JButton btnSchemaUpdate = new JButton( "r" );
        JButton btnSchemaDuplicate = new JButton( "d" );
        btnSchemaAdd.setToolTipText( "Add schema" );
        btnSchemaDelete.setToolTipText( "Delete schema" );
        btnSchemaUpdate.setToolTipText( "Rename schema" );
        btnSchemaDuplicate.setToolTipText( "Duplicate schema" );
        panel.setLayout( layout );
        panel.add( btnSchemaAdd, cc.xy( 2, 1 ) );
        panel.add( btnSchemaDelete, cc.xy( 3, 1 ) );
        panel.add( btnSchemaUpdate, cc.xy( 4, 1 ) );
        panel.add( btnSchemaDuplicate, cc.xy( 5, 1 ) );
        btnSchemaAdd.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnSchemaAdd();
        } );
        btnSchemaDelete.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnSchemaDelete();
        } );
        btnSchemaUpdate.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnSchemaUpdate();
        } );
        btnSchemaDuplicate.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_mnuiSchemaCopy();
        } );
        return panel;
    }

    private void eventActionPerformed_btnSchemaAdd() {
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
            if ( containsText( s ) ) {
                O_schema schema = new O_schema();
                schema.setSchema_id( dblink.generate_id() );
                schema.setSchema_name( s );
                dblink.schema_insert( schema );
                listModelSchema.reload();
            } else {
                Message.showMessage( "Please use alphanumeric or underscore characters: " + s );
            }
        }
    }

    private void eventActionPerformed_btnSchemaUpdate() {
        if ( has_schema_list_been_selected() ) {
            O_schema schema = ( O_schema ) listSchema.getSelectedValue();
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
                if ( containsText( s ) ) {
                    schema.setSchema_name( s );
                    dblink.schema_update( schema );
                    listModelSchema.reload();
                } else {
                    Message.showMessage( "Please use alphanumeric or underscore characters: " + s );
                }
            }
        } else {
            Message.showMessage( "Please select schema" );
        }
    }

    private void eventActionPerformed_btnSchemaDelete() {
        if ( has_schema_list_been_selected() ) {
            O_schema schema = ( O_schema ) listSchema.getSelectedValue();
            dblink.schema_delete( schema );
            listModelSchema.reload();
            listModelTable.clear();
            tableModelKeys.clear();
            hideTableKeysColumns();
            tableModelRelationship.clear();
            hideTableRelationshipColumns();
            tableModelRelationshipKeyPair.clear();
            hideTableRelationshipKeyPairColumns();
        } else {
            Message.showMessage( "Please select schema" );
        }
    }

    private JPanel get_panel_table_buttons() {
        FormLayout layout = new FormLayout( "min:grow,min,min,min,min,min:grow", //columns
                                            "min" //rows
        );
        JPanel panel = new JPanel();
        JButton btnTableAdd = new JButton( "+" );
        JButton btnTableDelete = new JButton( "-" );
        JButton btnTableUpdate = new JButton( "r" );
        JButton btnTableDuplicate = new JButton( "d" );
        btnTableAdd.setToolTipText( "Add table" );
        btnTableDelete.setToolTipText( "Delete table" );
        btnTableUpdate.setToolTipText( "Rename table" );
        btnTableDuplicate.setToolTipText( "Duplicate table" );
        panel.setLayout( layout );
        panel.add( btnTableAdd, cc.xy( 2, 1 ) );
        panel.add( btnTableDelete, cc.xy( 3, 1 ) );
        panel.add( btnTableUpdate, cc.xy( 4, 1 ) );
        panel.add( btnTableDuplicate, cc.xy( 5, 1 ) );
        btnTableAdd.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnTableAdd();
        } );
        btnTableDelete.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnTableDelete();
        } );
        btnTableUpdate.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnTableUpdate();
        } );
        btnTableDuplicate.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_mnuiTableCopy();
        } );
        return panel;
    }

    private void eventActionPerformed_btnTableUpdate() {
        if ( has_schema_list_been_selected() ) {
            O_table t = ( O_table ) listTable.getSelectedValue();
            JLabel question = new JLabel();
            JTextField field = new JTextField();
            JComponent[] inputs = new JComponent[] {
                question,
                field
            };
            question.setText( "What is your new table name?" );
            field.setText( t.getTable_name() );
            int i = Message.showOptionDialogOkCancel( inputs, "New Table" );
            if ( i == 0 ) {
                String s = field.getText();
                if ( containsText( s ) ) {
                    Integer schemaId = t.getSchema_id();
                    t.setTable_name( s );
                    dblink.table_update( t );
                    O_schema schema = new O_schema();
                    schema.setSchema_id( schemaId );
                    listModelTable.reload( schema );
                    tableModelRelationship.reload( schema );
                    hideTableRelationshipColumns();
                } else {
                    Message.showMessage( "Please use alphanumeric or underscore characters: " + s );
                }
            }
        } else {
            Message.showMessage( "Please select schema" );
        }
    }

    private void eventActionPerformed_btnTableDelete() {
        if ( has_schema_list_been_selected() ) {
            O_table t = ( O_table ) listTable.getSelectedValue();
            dblink.table_delete( t );
            O_schema schema = new O_schema();
            schema.setSchema_id( t.getSchema_id() );
            listModelTable.reload( schema );
            tableModelKeys.clear();
            hideTableKeysColumns();
            tableModelRelationship.reload( schema );
            hideTableRelationshipColumns();
            tableModelRelationshipKeyPair.clear();
            hideTableRelationshipKeyPairColumns();
        } else {
            Message.showMessage( "Please select schema" );
        }
    }

    private void eventActionPerformed_btnTableAdd() {
        if ( has_schema_list_been_selected() ) {
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
                if ( containsText( s ) ) {
                    O_schema schema = ( O_schema ) listSchema.getSelectedValue();
                    O_table t = new O_table();
                    t.setSchema_id( schema.getSchema_id() );
                    Integer generate_id = dblink.generate_id();
                    t.setTable_id( generate_id );
                    t.setTable_name( s );
                    dblink.table_insert( t );
                    listModelTable.reload( schema );
                    tableModelKeys.clear();
                    hideTableKeysColumns();
                } else {
                    Message.showMessage( "Please use alphanumeric or underscore characters: " + s );
                }
            }
        } else {
            Message.showMessage( "Please select schema" );
        }
    }

    private boolean containsText( String s ) {
        String_check check = new String_check();
        check.add( s );
        return check.pass();
    }

    private JPanel get_panel_table_key_buttons() {
        FormLayout layout = new FormLayout( "min:grow,min,min,min,min:grow", //columns
                                            "min" //rows
        );
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( layout );
        JButton btnKeyAdd = new JButton( "+" );
        JButton btnKeyDelete = new JButton( "-" );
        JButton btnKeyUpdate = new JButton( "u" );
        btnKeyAdd.setToolTipText( "Add table key attribute" );
        btnKeyDelete.setToolTipText( "Delete table key attribute" );
        btnKeyUpdate.setToolTipText( "Update table key attribute" );
        buttonPanel.add( btnKeyAdd, cc.xy( 2, 1 ) );
        buttonPanel.add( btnKeyDelete, cc.xy( 3, 1 ) );
        buttonPanel.add( btnKeyUpdate, cc.xy( 4, 1 ) );
        btnKeyAdd.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnKeyAdd();
        } );
        btnKeyDelete.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnKeyDelete();
        } );
        btnKeyUpdate.addActionListener( ( ActionEvent e ) ->
        {
            eventActionPerformed_btnKeyUpdate();
        } );
        return buttonPanel;
    }

    private void eventActionPerformed_btnKeyAdd() {
        if ( has_table_list_been_selected() ) {
            clearPanelTableKeyInput();
            JPanel panelTableKeyInput = get_panel_table_key_input_add();
            panelTableKeyInput.setPreferredSize( new Dimension( 400, 210 ) );
            JComponent[] inputs = new JComponent[] {
                panelTableKeyInput
            };
            int i = Message.showOptionDialogOkCancel( inputs, "Table Key" );
            if ( i == 0 ) {
                O_table tableDataObject = ( O_table ) listTable.getSelectedValue();
                if ( hasKeyTypesListBeenSelected() ) {
                    try {
                        O_key_type keyTypeDataObject = ( O_key_type ) listKeyTypes.getSelectedValue();
                        String keyOrderText = keyOrder.getText();
                        Integer schemaid = tableDataObject.getSchema_id();
                        Integer tableid = tableDataObject.getTable_id();
                        String keyname = keyName.getText();
                        String keylabel = keyname;
                        Boolean keyispk = btnPK_Yes.isSelected();
                        Integer keytypeid = keyTypeDataObject.getKey_type_id();
                        Integer keyorder = Integer.parseInt( keyOrderText );
                        O_key key = new O_key();
                        key.setSchema_id( schemaid );
                        key.setTable_id( tableid );
                        key.setTable_key_id( dblink.generate_id() );
                        key.setTable_key_name( keyname );
                        key.setTable_key_label( keylabel );
                        key.setTable_key_is_pk( keyispk );
                        key.setTable_key_type_id( keytypeid );
                        key.setTable_key_order( keyorder );
                        dblink.key_insert( key );
                        O_key_with_name key_02 = new O_key_with_name();
                        key_02.setSchema_id( schemaid );
                        key_02.setTable_id( tableid );
                        tableModelKeys.reload( key_02 );
                        hideTableKeysColumns();
                    } catch ( NumberFormatException ne ) {
                        Message.showMessage( "Key ordering number must be a positive integer" );
                    }
                }
            } else {
                hideTableKeysColumns();
            }
        }
    }

    private boolean has_table_list_been_selected() {
        return !listTable.isSelectionEmpty();
    }

    private boolean has_table_keys_been_selected() {
        return tableKeys.getSelectedRow() != -1;
    }

    private void eventActionPerformed_btnKeyUpdate() {
        if ( has_table_list_been_selected() ) {
            if ( has_table_keys_been_selected() ) {
                Integer selectedRow = tableKeys.convertRowIndexToModel( tableKeys.getSelectedRow() );
                Integer keyid = ( Integer ) tableModelKeys.getValueAt( selectedRow, 2 );
                String keyname = ( String ) tableModelKeys.getValueAt( selectedRow, 3 );
                //String keylabel = (String) tableModelKeys.getValueAt(selectedRow, 4);
                Boolean keyispk = ( Boolean ) tableModelKeys.getValueAt( selectedRow, 5 );
                Integer keytypeid = ( Integer ) tableModelKeys.getValueAt( selectedRow, 6 );
                String typename = ( String ) tableModelKeys.getValueAt( selectedRow, 7 );
                Integer keyorder = ( Integer ) tableModelKeys.getValueAt( selectedRow, 8 );
                String keyOrderInText = String.valueOf( keyorder );
                O_key_type key_type = new O_key_type();
                key_type.setKey_type_id( keytypeid );
                key_type.setKey_type_name( typename );
                keyName.setText( keyname );
                listKeyTypes.setSelectedValue( key_type, true );
                btnPK_Yes.setSelected( keyispk );
                btnPK_No.setSelected( !keyispk );
                keyOrder.setText( keyOrderInText );
                //
                JPanel panelTableKeyInput = get_panel_table_key_input_update();
                panelTableKeyInput.setPreferredSize( new Dimension( 400, 180 ) );
                JComponent[] inputs = new JComponent[] {
                    panelTableKeyInput
                };
                int i = Message.showOptionDialogOkCancel( inputs, "Table Key" );
                if ( i == 0 ) {
                    O_table tableDataObject = ( O_table ) listTable.getSelectedValue();
                    Integer schemaid = tableDataObject.getSchema_id();
                    Integer tableid = tableDataObject.getTable_id();
                    if ( !listKeyTypes.isSelectionEmpty() ) {
                        O_key_type keyTypeDataObject = ( O_key_type ) listKeyTypes.getSelectedValue();
                        keytypeid = keyTypeDataObject.getKey_type_id();
                    }
                    O_key key = new O_key();
                    key.setSchema_id( schemaid );
                    key.setTable_id( tableid );
                    key.setTable_key_id( keyid );
                    key.setTable_key_name( keyName.getText() );
                    key.setTable_key_label( keyName.getText() );
                    key.setTable_key_is_pk( btnPK_Yes.isSelected() );
                    key.setTable_key_type_id( keytypeid );
                    key.setTable_key_order( Integer.parseInt( keyOrder.getText() ) );
                    dblink.key_update( key );
                    O_key_with_name key_02 = new O_key_with_name();
                    key_02.setSchema_id( schemaid );
                    key_02.setTable_id( tableid );
                    tableModelKeys.reload( key_02 );
                    hideTableKeysColumns();
                } else {
                    hideTableKeysColumns();
                }
            }
        }
    }

    private boolean hasKeyTypesListBeenSelected() {
        return !listKeyTypes.isSelectionEmpty();
    }

    private void eventActionPerformed_btnKeyDelete() {
        if ( has_table_list_been_selected() ) {
            O_table o_table = ( O_table ) listTable.getSelectedValue();
            Integer schemaid = o_table.getSchema_id();
            Integer tableid = o_table.getTable_id();
            Integer selectedRow = tableKeys.getSelectedRow();
            if ( selectedRow != -1 ) {
                Integer keyid = ( Integer ) tableModelKeys.getValueAt( selectedRow, 2 );
                O_key o_key = new O_key();
                o_key.setSchema_id( schemaid );
                o_key.setTable_id( tableid );
                o_key.setTable_key_id( keyid );
                dblink.key_delete( o_key );
                O_key_with_name key_02 = new O_key_with_name();
                key_02.setSchema_id( schemaid );
                key_02.setTable_id( tableid );
                tableModelKeys.reload( key_02 );
                hideTableKeysColumns();
            }
        }
    }

    private void eventActionPerformed_mnuiExit() {
        dblink.shutdown();
        //frame.dispose();
        System.exit( 0 );
    }

    private void hideTableKeysColumns() {
        tableKeys.getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        tableKeys.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );
        tableKeys.getColumnModel().getColumn( 0 ).setWidth( 0 );
        tableKeys.getColumnModel().getColumn( 1 ).setMinWidth( 0 );
        tableKeys.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );
        tableKeys.getColumnModel().getColumn( 1 ).setWidth( 0 );
        tableKeys.getColumnModel().getColumn( 2 ).setMinWidth( 0 );
        tableKeys.getColumnModel().getColumn( 2 ).setMaxWidth( 0 );
        tableKeys.getColumnModel().getColumn( 2 ).setWidth( 0 );
        tableKeys.getColumnModel().getColumn( 4 ).setMinWidth( 0 );
        tableKeys.getColumnModel().getColumn( 4 ).setMaxWidth( 0 );
        tableKeys.getColumnModel().getColumn( 4 ).setWidth( 0 );
        tableKeys.getColumnModel().getColumn( 6 ).setMinWidth( 0 );
        tableKeys.getColumnModel().getColumn( 6 ).setMaxWidth( 0 );
        tableKeys.getColumnModel().getColumn( 6 ).setWidth( 0 );
    }

    private void hideTableRelationshipColumns() {
        tableRelationshipFacts.getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 0 ).setWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 1 ).setMinWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 1 ).setWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 2 ).setMinWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 2 ).setMaxWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 2 ).setWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 4 ).setMinWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 4 ).setMaxWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 4 ).setWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 6 ).setMinWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 6 ).setMaxWidth( 0 );
        tableRelationshipFacts.getColumnModel().getColumn( 6 ).setWidth( 0 );
    }

    private void hideTableRelationshipKeyPairColumns() {
        tableRelationshipPairKeys.getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        tableRelationshipPairKeys.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );
        tableRelationshipPairKeys.getColumnModel().getColumn( 0 ).setWidth( 0 );
        tableRelationshipPairKeys.getColumnModel().getColumn( 1 ).setMinWidth( 0 );
        tableRelationshipPairKeys.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );
        tableRelationshipPairKeys.getColumnModel().getColumn( 1 ).setWidth( 0 );

    }

    private void select_options() {
        FormLayout layoutJava01 = new FormLayout( "min", //columns
                                                  "min,10dlu,min,10dlu,min,10dlu,min,10dlu" //rows
        );
        FormLayout layoutJava02 = new FormLayout( "min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu", //columns
                                                  "min,10dlu,min,10dlu,min,10dlu" //rows
        );
        FormLayout layoutSql01 = new FormLayout( "min", //columns
                                                 "min,10dlu,min,10dlu,min,10dlu,min,10dlu" //rows
        );
        FormLayout layoutSql02 = new FormLayout( "min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu", //columns
                                                 "min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu" //rows
        );
        FormLayout layoutJava = new FormLayout( "20dlu,min:grow,20dlu", //columns
                                                "20dlu,min,5dlu,min,20dlu" //rows
        );
        FormLayout layoutSql = new FormLayout( "20dlu,min:grow,20dlu", //columns
                                               "20dlu,min,5dlu,min,20dlu" //rows
        );
        FormLayout layout = new FormLayout( "min:grow,min,min", //columns
                                            "min,min" //rows
        );
        JPanel paneJava01 = new JPanel();
        JPanel paneJava02 = new JPanel();
        JPanel paneSql01 = new JPanel();
        JPanel paneSql02 = new JPanel();
        JPanel paneJava = new JPanel();
        JPanel paneSql = new JPanel();
        JPanel panel = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        paneJava01.setLayout( layoutJava01 );
        paneJava02.setLayout( layoutJava02 );
        paneSql01.setLayout( layoutSql01 );
        paneSql02.setLayout( layoutSql02 );
        paneJava.setLayout( layoutJava );
        paneSql.setLayout( layoutSql );
        panel.setLayout( layout );
        paneJava.add( paneJava01, cc.xy( 2, 2 ) );
        paneJava.add( paneJava02, cc.xy( 2, 4 ) );
        paneSql.add( paneSql01, cc.xy( 2, 2 ) );
        paneSql.add( paneSql02, cc.xy( 2, 4 ) );
        tabbedPane.add( paneSql, "Pl/Sql" );
        tabbedPane.add( paneJava, "Java" );
        panel.add( tabbedPane, cc.xyw( 1, 1, 3 ) );
        panel.add( reset, cc.xy( 2, 2 ) );
        panel.add( clear, cc.xy( 3, 2 ) );
        layout.setColumnGroup( 2, 3 );
        JLabel label_Insert = new JLabel( "Insert" );
        JLabel label_Update = new JLabel( "Update" );
        JLabel label_Delete = new JLabel( "Delete" );
        JLabel label_Merge = new JLabel( "Merge" );
        JLabel label_Select = new JLabel( "Select" );
        JLabel label_DeleteAll = new JLabel( "Delete All" );
        JLabel label_SelectAll = new JLabel( "Select All" );
        JLabel label_Count = new JLabel( "Count" );
        JLabel label_Statement = new JLabel( "Statement" );
        JLabel label_Procedure = new JLabel( "Procedure/Function" );
        JLabel label_Method = new JLabel( "Method" );
        JLabel label_MethodPrint = new JLabel( "Method Print" );
        JLabel label_Trigger_Statement_After = new JLabel( "(Statement Level, After Event) Trigger" );
        JLabel label_Trigger_Row_Before = new JLabel( "(Row Level, Before Event) Trigger" );
        JLabel label_Trigger_Row_After = new JLabel( " (Row Level, After Event) Trigger" );
        tabbedPane.setTabPlacement( JTabbedPane.BOTTOM );
        label_Statement.setHorizontalAlignment( JLabel.RIGHT );
        label_Procedure.setHorizontalAlignment( JLabel.RIGHT );
        label_Method.setHorizontalAlignment( JLabel.RIGHT );
        label_MethodPrint.setHorizontalAlignment( JLabel.RIGHT );
        label_Trigger_Statement_After.setHorizontalAlignment( JLabel.RIGHT );
        label_Trigger_Row_Before.setHorizontalAlignment( JLabel.RIGHT );
        label_Trigger_Row_After.setHorizontalAlignment( JLabel.RIGHT );
        JLabel label_Insert2 = new JLabel( "Insert" );
        JLabel label_Update2 = new JLabel( "Update" );
        JLabel label_Delete2 = new JLabel( "Delete" );
        JLabel label_Merge2 = new JLabel( "Merge" );
        JLabel label_Select2 = new JLabel( "Select" );
        JLabel label_DeleteAll2 = new JLabel( "Delete All" );
        JLabel label_SelectAll2 = new JLabel( "Select All" );
        JLabel label_Count2 = new JLabel( "Count" );
        JLabel label_Method2 = new JLabel( "Method" );
        JLabel label_MethodPrint2 = new JLabel( "Method Print" );
        paneSql01.add( cboxTables, cc.xy( 1, 1 ) );
        paneSql01.add( cboxViews, cc.xy( 1, 3 ) );
        paneSql01.add( cboxStmtCreateSelect, cc.xy( 1, 5 ) );
        paneSql01.add( cboxStmtInsertSelect, cc.xy( 1, 7 ) );
        paneSql02.add( label_Insert, cc.xy( 3, 1 ) );
        paneSql02.add( label_Update, cc.xy( 5, 1 ) );
        paneSql02.add( label_Delete, cc.xy( 7, 1 ) );
        paneSql02.add( label_Merge, cc.xy( 9, 1 ) );
        paneSql02.add( label_Select, cc.xy( 11, 1 ) );
        paneSql02.add( label_DeleteAll, cc.xy( 13, 1 ) );
        paneSql02.add( label_SelectAll, cc.xy( 15, 1 ) );
        paneSql02.add( label_Count, cc.xy( 17, 1 ) );
        paneSql02.add( label_Statement, cc.xy( 1, 3 ) );
        paneSql02.add( label_Procedure, cc.xy( 1, 5 ) );
        paneSql02.add( label_Trigger_Statement_After, cc.xy( 1, 7 ) );
        paneSql02.add( label_Trigger_Row_Before, cc.xy( 1, 9 ) );
        paneSql02.add( label_Trigger_Row_After, cc.xy( 1, 11 ) );
        paneSql02.add( cboxStmtInsert, cc.xy( 3, 3 ) );
        paneSql02.add( cboxStmtUpdate, cc.xy( 5, 3 ) );
        paneSql02.add( cboxStmtDelete, cc.xy( 7, 3 ) );
        paneSql02.add( cboxStmtMerge, cc.xy( 9, 3 ) );
        paneSql02.add( cboxStmtSelect, cc.xy( 11, 3 ) );
        paneSql02.add( cboxStmtDeleteAll, cc.xy( 13, 3 ) );
        paneSql02.add( cboxStmtSelectAll, cc.xy( 15, 3 ) );
        paneSql02.add( cboxStmtCount, cc.xy( 17, 3 ) );
        paneSql02.add( cboxProcInsert, cc.xy( 3, 5 ) );
        paneSql02.add( cboxProcUpdate, cc.xy( 5, 5 ) );
        paneSql02.add( cboxProcDelete, cc.xy( 7, 5 ) );
        paneSql02.add( cboxProcMerge, cc.xy( 9, 5 ) );
        paneSql02.add( cboxProcSelect, cc.xy( 11, 5 ) );
        paneSql02.add( cboxProcDeleteAll, cc.xy( 13, 5 ) );
        paneSql02.add( cboxProcSelectAll, cc.xy( 15, 5 ) );
        paneSql02.add( cboxFunctionCount, cc.xy( 17, 5 ) );
        paneSql02.add( cboxTriggerStatementAfterInsert, cc.xy( 3, 7 ) );
        paneSql02.add( cboxTriggerStatementAfterUpdate, cc.xy( 5, 7 ) );
        paneSql02.add( cboxTriggerStatementAfterDelete, cc.xy( 7, 7 ) );
        paneSql02.add( cboxTriggerRowBeforeInsert, cc.xy( 3, 9 ) );
        paneSql02.add( cboxTriggerRowBeforeUpdate, cc.xy( 5, 9 ) );
        paneSql02.add( cboxTriggerRowBeforeDelete, cc.xy( 7, 9 ) );
        paneSql02.add( cboxTriggerRowAfterInsert, cc.xy( 3, 11 ) );
        paneSql02.add( cboxTriggerRowAfterUpdate, cc.xy( 5, 11 ) );
        paneSql02.add( cboxTriggerRowAfterDelete, cc.xy( 7, 11 ) );
        paneJava01.add( cboxTestClass, cc.xy( 1, 1 ) );
        paneJava01.add( cbox_data_transfer_object, cc.xy( 1, 3 ) );
        paneJava01.add( cbox_generic_data_access_object, cc.xy( 1, 5 ) );
        paneJava01.add( cbox_data_access_object_interface, cc.xy( 1, 7 ) );
        //first row
        paneJava02.add( label_Insert2, cc.xy( 3, 1 ) );
        paneJava02.add( label_Update2, cc.xy( 5, 1 ) );
        paneJava02.add( label_Delete2, cc.xy( 7, 1 ) );
        paneJava02.add( label_Merge2, cc.xy( 9, 1 ) );
        paneJava02.add( label_Select2, cc.xy( 11, 1 ) );
        paneJava02.add( label_DeleteAll2, cc.xy( 13, 1 ) );
        paneJava02.add( label_SelectAll2, cc.xy( 15, 1 ) );
        paneJava02.add( label_Count2, cc.xy( 17, 1 ) );
        //third row
        paneJava02.add( label_Method2, cc.xy( 1, 3 ) );
        paneJava02.add( cboxMethodInsert, cc.xy( 3, 3 ) );
        paneJava02.add( cboxMethodUpdate, cc.xy( 5, 3 ) );
        paneJava02.add( cboxMethodDelete, cc.xy( 7, 3 ) );
        paneJava02.add( cboxMethodMerge, cc.xy( 9, 3 ) );
        paneJava02.add( cboxMethodSelect, cc.xy( 11, 3 ) );
        paneJava02.add( cboxMethodDeleteAll, cc.xy( 13, 3 ) );
        paneJava02.add( cboxMethodSelectAll, cc.xy( 15, 3 ) );
        paneJava02.add( cboxMethodFunctionCount, cc.xy( 17, 3 ) );
        //fifth row
        paneJava02.add( label_MethodPrint2, cc.xy( 1, 5 ) );
        paneJava02.add( cboxMethodSelectPrint, cc.xy( 11, 5 ) );
        paneJava02.add( cboxMethodSelectAllPrint, cc.xy( 15, 5 ) );
        paneJava02.add( cboxMethodFunctionCountPrint, cc.xy( 17, 5 ) );
        //
        JComponent[] inputs = new JComponent[] {
            panel
        };
        Message.showOptionDialogOkCancel( inputs, "Output" );
    }

    private void eventActionPerformed_menuItemProject() {
        openUrl( "https://xjrga.github.io/main/#potatosql-learning-software-for-database-design" );
    }

    private void eventActionPerformed_menuItemCredits() {
        StringBuilder sb = new StringBuilder();
        sb.append( "Potatosql uses the following libraries:" );
        sb.append( "\n\n" );
        sb.append( "commons-dbutils-1.7.jar" );
        sb.append( "\n" );
        sb.append( "hsqldb-2.7.1.jar" );
        sb.append( "\n" );
        sb.append( "jgoodies-common-1.8.1.jar" );
        sb.append( "\n" );
        sb.append( "jgoodies-forms-1.9.0.jar" );
        JTextArea credits = new JTextArea();
        credits.setText( sb.toString() );
        credits.setEditable( false );
        credits.setLineWrap( true );
        JScrollPane scrollPane = new JScrollPane( credits );
        scrollPane.setPreferredSize( new Dimension( 700, 433 ) );
        JComponent[] inputs = new JComponent[] {
            scrollPane
        };
        Message.showOptionDialog( inputs, "Credits" );
    }

    private void openUrl( String url ) {
        try {
            Desktop.getDesktop().browse( new URL( url ).toURI() );
        } catch ( IOException | URISyntaxException e ) {
        }
    }

    private void eventActionPerformed_menuItemAbout() {
        StringBuilder sb = new StringBuilder();
        String txt = "Features:\n\n"
                + "        - Create database schema designs\n"
                + "        - Build database schema design repository\n"
                + "        - Share your favorite schema designs easily\n"
                + "        - Create java database applications\n"
                + "        - Build prototypes faster and less expensively\n"
                + "        - Accelerate database and java learning\n"
                + "        - Generate ddl, dml and java code\n"
                + "        - Generate entity relationship diagram in dot code (graphviz)\n"
                + "        - Is free and open source software"
                + "    \n\n"
                + "    Requirements:\n\n"
                + "       - Java 11";
        sb.append( txt );
        sb.append( "\n\n" );
        sb.append( "This is build 90" );
        sb.append( "\n\n" );
        sb.append( "Please send your comments and suggestions to jorge.r.garciadealba+snack@gmail.com" );
        JTextArea about = new JTextArea();
        about.setText( sb.toString() );
        about.setEditable( false );
        about.setLineWrap( true );
        JScrollPane scrollPane = new JScrollPane( about );
        scrollPane.setPreferredSize( new Dimension( 700, 433 ) );
        JComponent[] inputs = new JComponent[] {
            scrollPane
        };
        Message.showOptionDialog( inputs, "About" );
    }

    private void eventActionPerformed_send_message() {
        if ( has_schema_list_been_selected() ) {
            try {
                O_schema schema = ( O_schema ) listSchema.getSelectedValue();
                Xml_send send = new Xml_send( dblink, schema );
                show_message_sent();
            } catch ( Exception e ) {
            }
        } else {
            Message.showMessage( "Please select schema" );
        }
    }

    private void show_message_sent() {
        JComponent[] inputs = new JComponent[] {
            new JLabel( "Data exchange document is in model directory." )
        };
        Message.showOptionDialog( inputs, "Data Exchange" );
    }

    private void eventActionPerformed_receive_message() {
        fileChooser.setAcceptAllFileFilterUsed( false );
        fileChooser.addChoosableFileFilter( new FileNameExtensionFilter( "Xml Document", "xml" ) );
        int returnVal = fileChooser.showOpenDialog( frame );
        if ( returnVal == JFileChooser.APPROVE_OPTION ) {
            File file = fileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            fileChooser.setCurrentDirectory( new File( path ) );
            HashSet<Integer> set01 = new HashSet<>();
            HashSet<Integer> set02 = new HashSet<>();
            final int old_size = listModelSchema.size();
            for ( int i = 0; i < old_size; i++ ) {
                O_schema o = ( O_schema ) listModelSchema.get( i );
                set01.add( o.getSchema_id() );
            }
            Xml_receive receive = new Xml_receive();
            receive.import_potatosql_data( path );
            listModelSchema.reload();
            for ( int i = 0; i < listModelSchema.size(); i++ ) {
                O_schema o = ( O_schema ) listModelSchema.get( i );
                set02.add( o.getSchema_id() );
            }
            if ( old_size > 0 ) {
                if ( set02.removeAll( set01 ) ) {
                    int index = listModelSchema.find_by_schema_id( ( Integer ) set02.toArray()[ 0 ] );
                    listSchema.setSelectedIndex( index );
                }
            } else {
                listSchema.setSelectedIndex( 0 );
            }
        }
    }

    private void eventActionPerformed_menuItemQuickStart() {
        StringBuilder sb = new StringBuilder();
        String txt = "Database creation steps\n"
                + "1. Generate database schema sql code\n"
                + "2. Generate procedure sql code\n"
                + "3. Set alternate delimiter (/)\n"
                + "4. Run sql script in your favorite sql ide\n"
                + "   \n"
                + "\n"
                + "Java database application creation steps\n"
                + "1. Generate test class\n"
                + "2. Generate data transfer objects\n"
                + "3. Generate generic data access object\n"
                + "4. Generate typed data access object interfaces\n"
                + "5. Ask your favorite java ide to implement typed data access object interfaces\n"
                + "6. Generate typed data access object methods and replace stub methods.\n"
                + "\n"
                + "//Find methods use commons-dbutils library BeanListHandler class\n"
                + "//https://commons.apache.org/proper/commons-dbutils";
        sb.append( txt );
        sb.append( "\n\n" );
        sb.append( "Generated sql and java code was tested on: \n" );
        sb.append( "* hsqldb 2.7.1\n" );
        sb.append( "* mariadb 10.5.19\n" );
        JTextArea quick_start_text_area = new JTextArea();
        quick_start_text_area.setText( sb.toString() );
        quick_start_text_area.setEditable( false );
        quick_start_text_area.setLineWrap( true );
        JScrollPane scrollPane = new JScrollPane( quick_start_text_area );
        scrollPane.setPreferredSize( new Dimension( 700, 433 ) );
        JComponent[] inputs = new JComponent[] {
            scrollPane
        };
        Message.showOptionDialog( inputs, "Quick Start" );
    }

    private void enable_trigger_statements_options() {
        cboxTriggerStatementAfterInsert.setSelected( false );
        cboxTriggerStatementAfterUpdate.setSelected( false );
        cboxTriggerStatementAfterDelete.setSelected( false );
        cboxTriggerStatementAfterInsert.setEnabled( true );
        cboxTriggerStatementAfterUpdate.setEnabled( true );
        cboxTriggerStatementAfterDelete.setEnabled( true );
    }

    private void disable_trigger_statement_options() {
        cboxTriggerStatementAfterInsert.setSelected( false );
        cboxTriggerStatementAfterUpdate.setSelected( false );
        cboxTriggerStatementAfterDelete.setSelected( false );
        cboxTriggerStatementAfterInsert.setEnabled( false );
        cboxTriggerStatementAfterUpdate.setEnabled( false );
        cboxTriggerStatementAfterDelete.setEnabled( false );
    }

    private void clear_options() {
        Boolean flag = false;
        cboxTriggerStatementAfterInsert.setSelected( flag );
        cboxTriggerStatementAfterUpdate.setSelected( flag );
        cboxTriggerStatementAfterDelete.setSelected( flag );
        cboxTriggerRowBeforeInsert.setSelected( flag );
        cboxTriggerRowBeforeUpdate.setSelected( flag );
        cboxTriggerRowBeforeDelete.setSelected( flag );
        cboxTriggerRowAfterInsert.setSelected( flag );
        cboxTriggerRowAfterUpdate.setSelected( flag );
        cboxTriggerRowAfterDelete.setSelected( flag );
        cboxViews.setSelected( flag );
        cboxTestClass.setSelected( flag );
        cbox_generic_data_access_object.setSelected( flag );
        cbox_data_transfer_object.setSelected( flag );
        cbox_data_access_object_interface.setSelected( flag );
        cboxTables.setSelected( flag );
        cboxStmtInsert.setSelected( flag );
        cboxStmtUpdate.setSelected( flag );
        cboxStmtDelete.setSelected( flag );
        cboxStmtMerge.setSelected( flag );
        cboxStmtSelect.setSelected( flag );
        cboxStmtDeleteAll.setSelected( flag );
        cboxStmtSelectAll.setSelected( flag );
        cboxStmtCount.setSelected( flag );
        cboxStmtCreateSelect.setSelected( flag );
        cboxStmtInsertSelect.setSelected( flag );
        cboxProcInsert.setSelected( flag );
        cboxProcUpdate.setSelected( flag );
        cboxProcDelete.setSelected( flag );
        cboxProcMerge.setSelected( flag );
        cboxProcSelect.setSelected( flag );
        cboxProcDeleteAll.setSelected( flag );
        cboxProcSelectAll.setSelected( flag );
        cboxFunctionCount.setSelected( flag );
        cboxMethodInsert.setSelected( flag );
        cboxMethodUpdate.setSelected( flag );
        cboxMethodDelete.setSelected( flag );
        cboxMethodMerge.setSelected( flag );
        cboxMethodSelect.setSelected( flag );
        cboxMethodDeleteAll.setSelected( flag );
        cboxMethodSelectAll.setSelected( flag );
        cboxMethodFunctionCount.setSelected( flag );
        cboxMethodSelectPrint.setSelected( flag );
        cboxMethodSelectAllPrint.setSelected( flag );
        cboxMethodFunctionCountPrint.setSelected( flag );
    }

    private void set_default_options() {
        Boolean flag = true;
        cboxTables.setSelected( flag );
        cboxProcInsert.setSelected( flag );
        cboxProcUpdate.setSelected( flag );
        cboxProcDelete.setSelected( flag );
        cboxProcMerge.setSelected( flag );
        cboxProcSelect.setSelected( flag );
        cboxProcDeleteAll.setSelected( flag );
        cboxProcSelectAll.setSelected( flag );
        cboxFunctionCount.setSelected( flag );
    }

}

//todo - fix constraint creating when compound key
//ALTER TABLE cart ADD CONSTRAINT R2_cart_aisle FOREIGN KEY (store_id, product_id) REFERENCES aisle (store_id, product_id) ON DELETE CASCADE;

