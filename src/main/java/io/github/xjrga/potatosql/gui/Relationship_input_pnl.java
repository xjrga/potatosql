package io.github.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.data.object.Table;
import io.github.xjrga.potatosql.data.transfer.Table_pop;
import io.github.xjrga.potatosql.data.transfer.Table_sel;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class Relationship_input_pnl
        extends JPanel {
    private final CellConstraints cc = new CellConstraints();
    private final JList<Table> listParent = new JList<>();
    private final JList<Table> listChild = new JList<>();
    private final JRadioButton btnIdentifying = new JRadioButton();
    private final JRadioButton btnNonIdentifying = new JRadioButton();
    private final Schema schema;
    public Relationship_input_pnl( Schema schema ) {
        this.schema = schema;
        initialize();
        setup();
        FormLayout layout = new FormLayout( "min:grow,min:grow", //columns
                                            "fill:min:grow,min" //rows
        );
        setLayout( layout );
        setBorder( new TitledBorder( "Relationship" ) );
        add( get_panel_parent(), cc.xy( 1, 1 ) );
        add( get_panel_child(), cc.xy( 2, 1 ) );
        add( get_panel_type(), cc.xyw( 1, 2, 2 ) );
    }
    private void initialize() {
    }
    private void setup() {
        setPreferredSize( new Dimension( 600, 400 ) );
        btnIdentifying.setSelected( true );
        Table_sel select = new Table_sel();
        Table_pop populate = new Table_pop();
        listParent.setModel( populate.apply( select.apply( schema ) ) );
        listChild.setModel( populate.apply( select.apply( schema ) ) );
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
    public boolean get_btn_identifying_selected_value() {
        return btnIdentifying.isSelected();
    }
    public Table get_list_child_selected_value() {
        return listChild.getSelectedValue();
    }
    public Table get_list_parent_selected_value() {
        return listParent.getSelectedValue();
    }
}
