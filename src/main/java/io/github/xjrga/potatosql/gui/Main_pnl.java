package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Main_pnl
        extends JPanel {
    private final Mediator_concrete mediator;
    public Main_pnl( Mediator_concrete mediator ) {
        this.mediator = mediator;
        initialize();
        setup();
        FormLayout layout = new FormLayout( "min,min:grow", //columns
                                            "fill:min:grow" //rows
        );
        CellConstraints cc = new CellConstraints();
        setLayout( layout );
        add( mediator.get_schema_panel(), cc.xy( 1, 1 ) );
        add( get_tabbed_panel(), cc.xy( 2, 1 ) );
    }
    private void initialize() {
        mediator.register_main_panel( this );
    }
    private void setup() {
        setPreferredSize( new Dimension( 1132, 700 ) );
    }
    private JTabbedPane get_tabbed_panel() {
        JTabbedPane panel = new JTabbedPane();
        panel.setTabPlacement( SwingConstants.BOTTOM );
        panel.add( mediator.get_table_panel_composite() );
        panel.add( mediator.get_relationship_panel_composite() );
        panel.add( mediator.get_output_panel_composite() );
        panel.setTitleAt( 0, "Table" );
        panel.setTitleAt( 1, "Relationship" );
        panel.setTitleAt( 2, "Output" );
        return panel;
    }
}
