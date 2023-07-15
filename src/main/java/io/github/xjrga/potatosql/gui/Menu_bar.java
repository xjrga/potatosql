package io.github.xjrga.potatosql.gui;

import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.data.transfer.Schema_exporter;
import io.github.xjrga.potatosql.data.transfer.Schema_importer;
import io.github.xjrga.potatosql.other.Utilities;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu_bar
        extends JMenuBar {
    private final Mediator_concrete mediator;
    private final JFileChooser fc_import = new JFileChooser();
    private final JFileChooser fc_export = new JFileChooser();
    public Menu_bar( Mediator_concrete mediator ) {
        this.mediator = mediator;
        initialize();
        setup();
        add( get_mnu_program() );
        add( get_mnu_exchange() );
        add( get_mnu_output() );
        add( get_mnu_help() );
    }
    private void initialize() {
    }
    private void setup() {
        mediator.register_menu_bar( this );
    }
    private JMenu get_mnu_program() {
        JMenu menu = new JMenu();
        JMenuItem mnui_exit = new JMenuItem();
        menu.setText( "Program" );
        mnui_exit.setText( "Exit" );
        menu.add( mnui_exit );
        mnui_exit.addActionListener( ( ActionEvent e )
                -> {
            event_action_performed_mnui_exit();
        } );
        return menu;
    }
    private JMenu get_mnu_exchange() {
        JMenu menu = new JMenu();
        JMenuItem mnui_export = new JMenuItem();
        JMenuItem mnui_import = new JMenuItem();
        menu.setText( "Model" );
        mnui_import.setText( "Import" );
        mnui_export.setText( "Export" );
        menu.add( mnui_import );
        menu.add( mnui_export );
        mnui_import.addActionListener( ( ActionEvent e )
                -> {
            event_action_performed_import_xml();
        } );
        mnui_export.addActionListener( ( ActionEvent e )
                -> {
            event_action_performed_export_xml();
        } );
        return menu;
    }
    private JMenu get_mnu_output() {
        JMenu menu = new JMenu();
        JMenuItem mnui_options = new JMenuItem();
        menu.add( mnui_options );
        menu.setText( "Output" );
        mnui_options.setText( "Options" );
        mnui_options.addActionListener( ( ActionEvent e )
                -> {
            event_action_performed_options();
        } );
        return menu;
    }
    private JMenu get_mnu_help() {
        JMenu menu = new JMenu();
        JMenuItem mnui_project = new JMenuItem();
        JMenuItem mnui_credits = new JMenuItem();
        JMenuItem mnui_about = new JMenuItem();
        mnui_project.setText( "Project" );
        mnui_credits.setText( "Credits" );
        mnui_about.setText( "About" );
        menu.add( mnui_project );
        menu.add( mnui_credits );
        menu.add( mnui_about );
        menu.setText( "Help" );
        mnui_project.addActionListener( ( ActionEvent e )
                -> {
            event_action_mnu_project();
        } );
        mnui_credits.addActionListener( ( ActionEvent e )
                -> {
            event_action_mnui_credits();
        } );
        mnui_about.addActionListener( ( ActionEvent e )
                -> {
            event_action_mnui_about();
        } );
        return menu;
    }
    private void event_action_performed_mnui_exit() {
        //frame.dispose();
        System.exit( 0 );
    }
    private void event_action_performed_import_xml() {
        fc_import.setAcceptAllFileFilterUsed( false );
        fc_import.addChoosableFileFilter( new FileNameExtensionFilter( "Xml Document", "xml" ) );
        int returnVal = fc_import.showOpenDialog( this );
        if ( returnVal != JFileChooser.APPROVE_OPTION ) {
            return;
        }
        File file = fc_import.getSelectedFile();
        String path = file.getAbsolutePath();
        fc_import.setCurrentDirectory( new File( path ) );
        Schema_importer importer = new Schema_importer();
        Schema schema = importer.apply( path );
        mediator.reload_schema_panel();
        mediator.set_selected_schema( schema );
    }
    private void event_action_performed_export_xml() {
        if ( mediator.is_schema_list_selection_empty() ) {
            Message.showMessage( "Please select schema" );
            return;
        }
        fc_export.setAcceptAllFileFilterUsed( false );
        fc_export.addChoosableFileFilter( new FileNameExtensionFilter( "Xml Document", "xml" ) );
        int returnVal = fc_export.showSaveDialog( this );
        if ( returnVal != JFileChooser.APPROVE_OPTION ) {
            return;
        }
        File file = fc_export.getSelectedFile();
        String path = file.getAbsolutePath();
        fc_export.setCurrentDirectory( new File( path ) );
        Schema_exporter exporter = new Schema_exporter();
        String format_xml_doc = Utilities.format_xml_doc( exporter.apply( mediator.get_schema_selected_value(), path ) );
        try ( BufferedWriter writer = new BufferedWriter( new FileWriter( path ) ) ) {
            writer.write( format_xml_doc );
        } catch ( IOException ex ) {
        }
    }
    private void event_action_performed_options() {
        JComponent[] inputs = new JComponent[] {
            mediator.get_options_panel()
        };
        Message.showOptionDialogOkCancel( inputs, "Options" );
    }
    private void event_action_mnu_project() {
        JTextPane project = new JTextPane();
        project.setEditorKit( JTextPane.createEditorKitForContentType( "text/html" ) );
        project.setText( Utilities.convert_file_to_string( "resources/html/project.html" ) );
        project.setEditable( false );
        project.addHyperlinkListener( ( HyperlinkEvent e ) -> {
            if ( e.getEventType() == HyperlinkEvent.EventType.ACTIVATED ) {
                if ( Desktop.isDesktopSupported() ) {
                    Utilities.openUrl( e.getURL().toString() );
                }
            }
        } );
        JScrollPane scrollPane = new JScrollPane( project );
        scrollPane.setPreferredSize( new Dimension( 700, 433 ) );
        JComponent[] inputs = new JComponent[] {
            scrollPane
        };
        Message.showOptionDialog( inputs, "About" );
    }
    private void event_action_mnui_about() {
        JTextPane about = new JTextPane();
        about.setEditorKit( JTextPane.createEditorKitForContentType( "text/html" ) );
        about.setText( Utilities.convert_file_to_string( "resources/html/about.html" ) );
        about.setEditable( false );
        about.addHyperlinkListener( ( HyperlinkEvent e ) -> {
            if ( e.getEventType() == HyperlinkEvent.EventType.ACTIVATED ) {
                if ( Desktop.isDesktopSupported() ) {
                    Utilities.openUrl( e.getURL().toString() );
                }
            }
        } );
        JScrollPane scrollPane = new JScrollPane( about );
        scrollPane.setPreferredSize( new Dimension( 700, 433 ) );
        JComponent[] inputs = new JComponent[] {
            scrollPane
        };
        Message.showOptionDialog( inputs, "About" );
    }
    private void event_action_mnui_credits() {
        JTextPane credits = new JTextPane();
        credits.setEditorKit( JTextPane.createEditorKitForContentType( "text/html" ) );
        credits.setText( Utilities.convert_file_to_string( "resources/html/credits.html" ) );
        credits.setEditable( false );
        credits.addHyperlinkListener( ( HyperlinkEvent e ) -> {
            if ( e.getEventType() == HyperlinkEvent.EventType.ACTIVATED ) {
                if ( Desktop.isDesktopSupported() ) {
                    Utilities.openUrl( e.getURL().toString() );
                }
            }
        } );
        JScrollPane scrollPane = new JScrollPane( credits );
        scrollPane.setPreferredSize( new Dimension( 700, 433 ) );
        JComponent[] inputs = new JComponent[] {
            scrollPane
        };
        Message.showOptionDialog( inputs, "Credits" );
    }
}
