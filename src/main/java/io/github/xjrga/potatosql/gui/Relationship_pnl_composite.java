package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import javax.swing.JPanel;

public class Relationship_pnl_composite
        extends JPanel {
    private final Mediator_concrete mediator;
    private final CellConstraints cc = new CellConstraints();
    public Relationship_pnl_composite( Mediator_concrete mediator ) {
        this.mediator = mediator;
        initialize();
        setup();
        FormLayout layout = new FormLayout( "min:grow", //columns
                                            "fill:min:grow,fill:min:grow" //rows
        );
        setLayout( layout );
        add( mediator.get_relationship_panel(), cc.xy( 1, 1 ) );
        add( mediator.get_relationship_pair_keys_panel(), cc.xy( 1, 2 ) );
    }
    private void initialize() {
        mediator.register_relationship_panel_composite( this );
    }
    private void setup() {
    }
}
