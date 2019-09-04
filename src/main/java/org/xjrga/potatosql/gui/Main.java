package org.xjrga.potatosql.gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.xjrga.potatosql.data.DbLink;
import org.xjrga.potatosql.dataobject.KeyDataObject;
import org.xjrga.potatosql.dataobject.KeyTypeDataObject;
import org.xjrga.potatosql.dataobject.SchemaDataObject;
import org.xjrga.potatosql.dataobject.TableDataObject;
import org.xjrga.potatosql.generator.*;
import org.xjrga.potatosql.model.*;
import org.xjrga.potatosql.other.Replacer;
import org.xjrga.potatosql.other.Write;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Main
{
    private CellConstraints cc;
    private DbLink dbLink;
    private JButton clear;
    private JCheckBox cboxTablesDup;
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
    private JCheckBox cboxDataObject;
    private JCheckBox cboxMethodSelectPrint;
    private JCheckBox cboxMethodSelectAllPrint;
    private JFrame frame;
    private JList listKeyTypes;
    private JList<Object> listChild;
    private JList<Object> listChildNPK;
    private JList<Object> listChildPK;
    private JList<Object> listParent;
    private JList<Object> listParentNPK;
    private JList<Object> listParentPK;
    private JList<Object> listSchema;
    private JList<Object> listTable;
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
    private ListModelTable listModelTableOne;
    private ListModelTable listModelTable;
    private TableModelKeys tableModelKeys;
    private TableModelRelationshipKeyPair tableModelRelationshipKeyPair;
    private TableModelRelationship tableModelRelationship;
    private JTextField txtForward;
    private JTextField txtReverse;
    private JTextField txtRelationshipName;
    private JRadioButtonMenuItem mnuiDialectHsqldb;
    private JRadioButtonMenuItem mnuiDialectMysql;
    private ListModelKeyTypes listModelKeyTypes;

    public Main()
    {
        setLookAndFeel();
        initialize();
    }


    public static void main(String[] args)
    {
        Main main = new Main();
    }


    private void setLookAndFeel()
    {
        try
        {
            String operating_system = System.getProperty("os.name").toLowerCase();
            String java_version = System.getProperty("java.version");
            //UIManager.setLookAndFeel ("com.seaglasslookandfeel.SeaGlassLookAndFeel");
            //UIManager.setLookAndFeel ("com.alee.laf.WebLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel ("com.jgoodies.looks.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel ("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
            //UIManager.setLookAndFeel ("com.jgoodies.looks.plastic.PlasticLookAndFeel");
            //UIManager.setLookAndFeel ("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        }
        catch (ClassNotFoundException e)
        {
            try
            {
                UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
            }
            catch (ClassNotFoundException ex)
            {
                ex.printStackTrace();
            }
            catch (InstantiationException ex)
            {
                ex.printStackTrace();
            }
            catch (IllegalAccessException ex)
            {
                ex.printStackTrace();
            }
            catch (UnsupportedLookAndFeelException ex)
            {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
    }


    private void initialize()
    {
        cboxTablesDup = new JCheckBox("Duplicate Table To Test Trigger");
        cboxStmtCreateSelect = new JCheckBox("Create Table As Select");
        cboxStmtInsertSelect = new JCheckBox("Insert Using Select");
        cboxStmtCount = new JCheckBox("");
        cboxFunctionCount = new JCheckBox("");
        cboxMethodFunctionCount = new JCheckBox("");
        cboxTriggerStatementAfterInsert = new JCheckBox("");
        cboxTriggerStatementAfterUpdate = new JCheckBox("");
        cboxTriggerStatementAfterDelete = new JCheckBox("");
        cboxTriggerRowBeforeInsert = new JCheckBox("");
        cboxTriggerRowBeforeUpdate = new JCheckBox("");
        cboxTriggerRowBeforeDelete = new JCheckBox("");
        cboxTriggerRowAfterInsert = new JCheckBox("");
        cboxTriggerRowAfterUpdate = new JCheckBox("");
        cboxTriggerRowAfterDelete = new JCheckBox("");
        cboxViews = new JCheckBox("View");
        cboxTestClass = new JCheckBox("Test Class");
        cboxDataObject = new JCheckBox("Data Object");
        cboxTables = new JCheckBox("Tables and Relationships");
        cboxStmtInsert = new JCheckBox("");
        cboxStmtUpdate = new JCheckBox("");
        cboxStmtDelete = new JCheckBox("");
        cboxStmtMerge = new JCheckBox("");
        cboxStmtSelect = new JCheckBox("");
        cboxStmtDeleteAll = new JCheckBox("");
        cboxStmtSelectAll = new JCheckBox("");
        cboxProcInsert = new JCheckBox("");
        cboxProcUpdate = new JCheckBox("");
        cboxProcDelete = new JCheckBox("");
        cboxProcMerge = new JCheckBox("");
        cboxProcSelect = new JCheckBox("");
        cboxProcDeleteAll = new JCheckBox("");
        cboxProcSelectAll = new JCheckBox("");
        cboxMethodInsert = new JCheckBox("");
        cboxMethodUpdate = new JCheckBox("");
        cboxMethodDelete = new JCheckBox("");
        cboxMethodMerge = new JCheckBox("");
        cboxMethodSelect = new JCheckBox("");
        cboxMethodDeleteAll = new JCheckBox("");
        cboxMethodSelectAll = new JCheckBox("");
        cboxMethodSelectPrint = new JCheckBox("");
        cboxMethodSelectAllPrint = new JCheckBox("");
        cc = new CellConstraints();
        dbLink = new DbLink();
        clear = new JButton("Clear");
        keyName = new JTextField();
        listKeyTypes = new JList<>();
        btnPK_Yes = new JRadioButton();
        btnPK_No = new JRadioButton();
        keyOrder = new JTextField();
        txtRelationshipName = new JTextField();
        txtForward = new JTextField();
        txtReverse = new JTextField();
        listParent = new JList<>();
        listChild = new JList<>();
        btnIdentifying = new JRadioButton();
        btnNonIdentifying = new JRadioButton();
        listParentPK = new JList<>();
        listParentNPK = new JList();
        listChildPK = new JList<>();
        listChildNPK = new JList<>();
        mnuiDialectHsqldb = new JRadioButtonMenuItem();
        mnuiDialectMysql = new JRadioButtonMenuItem();
        listModelParentPK = new ListModelParentPK(dbLink);
        listModelParentNPK = new ListModelParentNPK(dbLink);
        listModelChildPK = new ListModelChildPK(dbLink);
        listModelChildNPK = new ListModelChildNPK(dbLink);
        listModelKeyTypes = new ListModelKeyTypes(dbLink);
        listModelKeyTypes.reload();
        listKeyTypes.setModel(listModelKeyTypes);
        listSchema = new JList<>();
        listModelSchema = new ListModelSchema(dbLink);
        listSchema.setModel(listModelSchema);
        listSchema.setValueIsAdjusting(true);
        listModelSchema.reload();
        mnuiOptions = new JMenuItem();
        textArea = new JTextArea();
        listTable = new JList<>();
        listTable.setValueIsAdjusting(true);
        listModelTableOne = new ListModelTable(dbLink);
        listModelTable = new ListModelTable(dbLink);
        listParent.setModel(listModelTableOne);
        listChild.setModel(listModelTable);
        listTable.setModel(listModelTable);
        tableKeys = new JTable();
        tableModelKeys = new TableModelKeys(dbLink);
        tableKeys.setModel(tableModelKeys);
        tableRelationshipFacts = new JTable();
        tableModelRelationship = new TableModelRelationship(dbLink);
        tableRelationshipFacts.setModel(tableModelRelationship);
        listParentPK.setModel(listModelParentPK);
        listParentNPK.setModel(listModelParentNPK);
        listChildPK.setModel(listModelChildPK);
        listChildNPK.setModel(listModelChildNPK);
        tableRelationshipPairKeys = new JTable();
        tableModelRelationshipKeyPair = new TableModelRelationshipKeyPair(dbLink);
        tableRelationshipPairKeys.setModel(tableModelRelationshipKeyPair);
        frame = this.getFrame();
        //needed this code for gui to show properly on windows
        Dimension size = frame.getSize();
        Dimension newsize = new Dimension(size.width + 1, size.height + 1);
        frame.setSize(newsize);
    }


    private JFrame getFrame()
    {
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(1132, 700));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
        frame.setTitle("PotatoSQL: Software for Database Design");
        frame.setJMenuBar(getMenuBar());
        frame.add(getMainPanel());

        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                frame.dispose();
                dbLink.Shutdown();
            }
        });

        frame.addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent e)
            {
                Dimension size = frame.getSize();
            }
        });

        return frame;
    }


    private JPanel getMainPanel()
    {
        FormLayout layout = new FormLayout("min,min,min:grow", //columns
                "fill:min:grow" //rows
        );
        FormLayout panelWrapperLayout = new FormLayout("min:grow,min", //columns
                "fill:min:grow,min,4dlu" //rows
        );
        JPanel panel = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panelWrapper = new JPanel();
        JButton btnGenerate = new JButton("Generate");
        btnGenerate.setToolTipText("Generate source code");
        panelWrapper.setLayout(panelWrapperLayout);
        panel.setLayout(layout);
        tabbedPane.setTabPlacement(SwingConstants.BOTTOM);

        tabbedPane.add(getPanelTableKeys());
        tabbedPane.add(getPanelInput2());
        tabbedPane.add(getPanelOutput());
        tabbedPane.setTitleAt(0, "Keys");
        tabbedPane.setTitleAt(1, "Relationships");
        tabbedPane.setTitleAt(2, "Output");
        panelWrapper.add(tabbedPane, cc.xyw(1, 1, 2));
        panelWrapper.add(btnGenerate, cc.xy(2, 2));
        panel.add(getPanelSchema(), cc.xy(1, 1));
        panel.add(getPanelTable(), cc.xy(2, 1));
        panel.add(panelWrapper, cc.xy(3, 1));

        btnGenerate.addActionListener(e -> btnGenerate_actionPerformed());

        return panel;
    }


    private void btnGenerate_actionPerformed()
    {
        StringBuilder sb = new StringBuilder();

        Generator0 generator0 = new Generator0();
        generator0.setHsqldb(mnuiDialectHsqldb.isSelected());
        generator0.setTestClassSelected(cboxTestClass.isSelected());
        sb.append(generator0.getCode());

        if (isSchemaSelected())
        {
            Generator1 generator1 = new Generator1(dbLink);
            SchemaDataObject schemaDataObject = getSelectedSchema();
            int numberOfTables = listModelTable.getSize();
            int[] tableIds = new int[numberOfTables];
            generator1.setHsqldb(mnuiDialectHsqldb.isSelected());
            generator1.setMysql(mnuiDialectMysql.isSelected());
            generator1.setTablesAndRelationshipsSelected(cboxTables.isSelected());
            generator1.setSchemaDataObject(schemaDataObject);

            for (int i = 0; i < numberOfTables; i++)
            {
                TableDataObject tableDataObject = (TableDataObject) listModelTable.getElementAt(i);
                tableIds[i] = tableDataObject.getTableId();
            }

            generator1.setTableIds(tableIds);
            sb.append(generator1.getCode());

            if (isTableSelected())
            {
                TableDataObject tableDataObject = getSelectedTable();
                Generator2 generator2 = new Generator2(dbLink);
                generator2.setSchemaDataObject(schemaDataObject);
                generator2.setTableDataObject(tableDataObject);
                generator2.setHsqldb(mnuiDialectHsqldb.isSelected());
                generator2.setMysql(mnuiDialectMysql.isSelected());
                generator2.setStmtCreateSelect(cboxStmtCreateSelect.isSelected());
                generator2.setStmtInsertSelect(cboxStmtInsertSelect.isSelected());
                generator2.setStmtCount(cboxStmtCount.isSelected());
                generator2.setFunctionCount(cboxFunctionCount.isSelected());
                generator2.setMethodFunctionCount(cboxMethodFunctionCount.isSelected());
                generator2.setTriggerStatementAfterInsert(cboxTriggerStatementAfterInsert.isSelected());
                generator2.setTriggerStatementAfterUpdate(cboxTriggerStatementAfterUpdate.isSelected());
                generator2.setTriggerStatementAfterDelete(cboxTriggerStatementAfterDelete.isSelected());
                generator2.setTriggerRowBeforeInsert(cboxTriggerRowBeforeInsert.isSelected());
                generator2.setTriggerRowBeforeUpdate(cboxTriggerRowBeforeUpdate.isSelected());
                generator2.setTriggerRowBeforeDelete(cboxTriggerRowBeforeDelete.isSelected());
                generator2.setTriggerRowAfterInsert(cboxTriggerRowAfterInsert.isSelected());
                generator2.setTriggerRowAfterUpdate(cboxTriggerRowAfterUpdate.isSelected());
                generator2.setTriggerRowAfterDelete(cboxTriggerRowAfterDelete.isSelected());
                generator2.setTablesDup(cboxTablesDup.isSelected());
                generator2.setViews(cboxViews.isSelected());
                generator2.setTables(cboxTables.isSelected());
                generator2.setStmtInsert(cboxStmtInsert.isSelected());
                generator2.setStmtUpdate(cboxStmtUpdate.isSelected());
                generator2.setStmtDelete(cboxStmtDelete.isSelected());
                generator2.setStmtMerge(cboxStmtMerge.isSelected());
                generator2.setStmtSelect(cboxStmtSelect.isSelected());
                generator2.setStmtDeleteAll(cboxStmtDeleteAll.isSelected());
                generator2.setStmtSelectAll(cboxStmtSelectAll.isSelected());
                generator2.setProcInsert(cboxProcInsert.isSelected());
                generator2.setProcUpdate(cboxProcUpdate.isSelected());
                generator2.setProcDelete(cboxProcDelete.isSelected());
                generator2.setProcMerge(cboxProcMerge.isSelected());
                generator2.setProcSelect(cboxProcSelect.isSelected());
                generator2.setProcDeleteAll(cboxProcDeleteAll.isSelected());
                generator2.setProcSelectAll(cboxProcSelectAll.isSelected());
                generator2.setMethodInsert(cboxMethodInsert.isSelected());
                generator2.setMethodUpdate(cboxMethodUpdate.isSelected());
                generator2.setMethodDelete(cboxMethodDelete.isSelected());
                generator2.setMethodMerge(cboxMethodMerge.isSelected());
                generator2.setMethodSelect(cboxMethodSelect.isSelected());
                generator2.setMethodDeleteAll(cboxMethodDeleteAll.isSelected());
                generator2.setMethodSelectAll(cboxMethodSelectAll.isSelected());
                generator2.setDataObject(cboxDataObject.isSelected());
                generator2.setMethodSelectPrint(cboxMethodSelectPrint.isSelected());
                generator2.setMethodSelectAllPrint(cboxMethodSelectAllPrint.isSelected());
                sb.append(generator2.getCode());
            }

        }

        String toString = sb.toString();
        textArea.setText(toString);
        Write.writeToFile(toString, "script.sql");

    }

    private SchemaDataObject getSelectedSchema()
    {
        return (SchemaDataObject) listSchema.getSelectedValue();
    }


    private boolean isSchemaSelected()
    {
        return !listSchema.isSelectionEmpty();
    }

    private TableDataObject getSelectedTable()
    {
        return (TableDataObject) listTable.getSelectedValue();
    }

    private boolean isTableSelected()
    {
        return !listTable.isSelectionEmpty();
    }

    private JMenuBar getMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu mnuProgram = new JMenu();
        JMenu mnuSchema = new JMenu();
        JMenu mnuTable = new JMenu();
        JMenu mnuKey = new JMenu();
        JMenu mnuOutput = new JMenu();
        JMenu mnuDialect = new JMenu();
        JMenu mnuHelp = new JMenu();
        JMenuItem mnuiExit = new JMenuItem();
        JMenuItem mnuiSchemaCopy = new JMenuItem();
        JMenuItem mnuiTableCopy = new JMenuItem();
        JMenuItem mnuiTableImportData = new JMenuItem();
        JMenuItem mnuiKeyCopy = new JMenuItem();
        ButtonGroup dialectGroup = new ButtonGroup();
        dialectGroup.add(mnuiDialectHsqldb);
        dialectGroup.add(mnuiDialectMysql);
        JMenuItem mnuiGuide = new JMenuItem();
        JMenuItem mnuiCredits = new JMenuItem();
        JMenuItem mnuiAbout = new JMenuItem();

        menuBar.add(mnuProgram);
        menuBar.add(mnuSchema);
        menuBar.add(mnuTable);
        menuBar.add(mnuKey);
        menuBar.add(mnuOutput);
        menuBar.add(mnuDialect);
        menuBar.add(mnuHelp);

        mnuProgram.add(mnuiExit);
        mnuSchema.add(mnuiSchemaCopy);
        mnuTable.add(mnuiTableCopy);
        //mnuTable.add(mnuiTableImportData);
        mnuKey.add(mnuiKeyCopy);
        mnuOutput.add(mnuiOptions);
        mnuDialect.add(mnuiDialectHsqldb);
        mnuDialect.add(mnuiDialectMysql);
        mnuHelp.add(mnuiGuide);
        mnuHelp.add(mnuiCredits);
        mnuHelp.add(mnuiAbout);

        mnuProgram.setText("Program");
        mnuSchema.setText("Schema");
        mnuTable.setText("Table");
        mnuKey.setText("Key");
        mnuOutput.setText("Output");
        mnuDialect.setText("Dialect");
        mnuHelp.setText("Help");

        mnuiExit.setText("Exit");
        mnuiSchemaCopy.setText("Duplicate");
        mnuiTableCopy.setText("Duplicate");
        mnuiTableImportData.setText("Create Insert Calls");
        mnuiKeyCopy.setText("Duplicate");
        mnuiOptions.setText("Options");
        mnuiDialectHsqldb.setText("Hsqldb");
        mnuiDialectMysql.setText("Mysql");
        mnuiDialectHsqldb.setSelected(true);
        mnuiGuide.setText("Guide");
        mnuiCredits.setText("Credits");
        mnuiAbout.setText("About");

        mnuiOptions.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                selectOptions();
            }
        });

        mnuiExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_mnuiExit(e);
            }
        });

        mnuiSchemaCopy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_mnuiSchemaCopy(e);
            }
        });

        mnuiTableCopy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_mnuiTableCopy(e);
            }
        });

        mnuiTableImportData.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //todo eventActionPerformed_mnuiTableImportData(e);
                eventActionPerformed_mnuiTableImportData(e);
            }
        });

        mnuiKeyCopy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_mnuiKeyCopy(e);
            }
        });

        mnuiGuide.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_mnuiGuide(e);
            }
        });

        mnuiCredits.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_mnuiCredits(e);
            }
        });

        mnuiAbout.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_mnuiAbout(e);
            }
        });

        return menuBar;
    }

    private void eventActionPerformed_mnuiAbout(ActionEvent e)
    {
        StringBuffer sb = new StringBuffer();

        sb.append("This software:");
        sb.append("\n\n");
        sb.append("- allows me to design database schemas");
        sb.append("\n");
        sb.append("- generates ddl and dml code");
        sb.append("\n");
        sb.append("- allows me to build prototypes faster and less expensively");
        sb.append("\n");
        sb.append("- can be used as a learning/teaching tool");
        sb.append("\n");
        sb.append("- is available as free software");
        sb.append("\n\n");
        sb.append("This is version 0.10");
        sb.append("\n\n");
        sb.append("Please send your comments and suggestions to jorge.r.garciadealba+potatosql@gmail.com");
        sb.append("\n\n");
        sb.append("Software can be downloaded at https://sourceforge.net/projects/potatosql/");

        JTextArea textArea = new JTextArea(15, 90);
        textArea.setText(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "PotatoSQL: Software for Database Design", JOptionPane.PLAIN_MESSAGE);
    }

    private void eventActionPerformed_mnuiCredits(ActionEvent e)
    {
        StringBuffer sb = new StringBuffer();

        sb.append("PotatoSQL uses the following libraries:");
        sb.append("\n\n");
        sb.append("hsqldb.jar (v2.5.0)");
        sb.append("\n");
        sb.append("jgoodies-common-1.8.1.jar");
        sb.append("\n");
        sb.append("jgoodies-forms-1.8.0.jar");
        sb.append("\n");
        sb.append("jgoodies-looks-2.7.0.jar");
        sb.append("\n\n");
        sb.append("It was written using IntelliJ IDEA (Community Edition), SQL Workbench/J and PotatoSQL.");

        JTextArea textArea = new JTextArea(15, 90);
        textArea.setText(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "PotatoSQL: Software for Database Design", JOptionPane.PLAIN_MESSAGE);

    }

    private void eventActionPerformed_mnuiGuide(ActionEvent e)
    {
        openUrl("http://x-jrga.github.io/potatosql");
    }

    private void eventActionPerformed_mnuiTableImportData(ActionEvent e)
    {
        if (isTableSelected())
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int returnval = fileChooser.showOpenDialog((JMenuItem) e.getSource());

            if (returnval == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();

                new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            long startTime = System.nanoTime();
                            readFile(file);
                            long endTime = System.nanoTime();
                            long duration = (endTime - startTime);
                            System.out.println("miliseconds: " + duration / 1000000);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }

        }
    }

    private void readFile(File file)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str = "";
            TableMaker tableMaker = new TableMaker(dbLink);
            TableDataObject tableDataObject = getSelectedTable();
            int schemaId = tableDataObject.getSchemaId();
            int tableId = tableDataObject.getTableId();
            Table table = tableMaker.getTable(schemaId, tableId);
            Replacer replacer = new Replacer();
            StringBuilder sb = new StringBuilder();

            while ((str = in.readLine()) != null)
            {
                replacer.replace(str);

                PrintProcedureInsertCall printProcedureInsertCall = new PrintProcedureInsertCall(table);
                printProcedureInsertCall.setStr(replacer.replace(str));
                String code = printProcedureInsertCall.getCode();
                sb.append(code);

            }

            StringBuilder sbFilename = new StringBuilder();
            sbFilename.append(table.getName().toLowerCase());
            sbFilename.append("_insert_calls.sql");
            Write.writeToFile(sb.toString(), sbFilename.toString());
            textArea.append(sb.toString());

            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void eventActionPerformed_mnuiKeyCopy(ActionEvent e)
    {
        int selectedRow = tableKeys.getSelectedRow();

        if (selectedRow != -1)
        {
            int schemaid = (int) tableModelKeys.getValueAt(selectedRow, 0);
            int tableid = (int) tableModelKeys.getValueAt(selectedRow, 1);
            int keyid = (int) tableModelKeys.getValueAt(selectedRow, 2);
            String keyname = (String) tableModelKeys.getValueAt(selectedRow, 3);

            String s = (String) JOptionPane.showInputDialog(null, "What is your key name?", "New Key", -1, null, null, keyname);

            if (isAlphaNumeric(s))
            {
                dbLink.PotatoSql_TableKey_Copy(schemaid, tableid, keyid, s);
                tableModelKeys.reload(schemaid, tableid);
                hideTableKeysColumns();
            }

        }
    }


    private void eventActionPerformed_mnuiSchemaCopy(ActionEvent e)
    {
        if (isSchemaSelected())
        {
            SchemaDataObject schemaDataObject = getSelectedSchema();
            Integer schemaId = schemaDataObject.getSchemaId();
            String schemaName = schemaDataObject.getSchemaName();
            String s = (String) JOptionPane.showInputDialog(null, "What is your new schema name?", "New Schema", -1, null, null, schemaName);

            if (isAlphaNumeric(s))
            {
                dbLink.DatabaseSchema_Copy(schemaId, s);
                listModelSchema.reload();
            }
        }
    }


    private void eventActionPerformed_mnuiTableCopy(ActionEvent e)
    {
        if (isSchemaSelected())
        {
            if (isTableSelected())
            {
                SchemaDataObject schemaDataObject = getSelectedSchema();
                Integer schemaId = schemaDataObject.getSchemaId();
                String schemaName = schemaDataObject.getSchemaName();
                TableDataObject tableDataObject = (TableDataObject) listTable.getSelectedValue();
                schemaId = tableDataObject.getSchemaId();
                Integer tableId_Old = tableDataObject.getTableId();
                String tableName = tableDataObject.getTableName();
                String s = (String) JOptionPane.showInputDialog(null, "What is your new table name?", "New Table", -1, null, null, tableName);

                if (isAlphaNumeric(s))
                {
                    dbLink.PotatoSql_Table_Copy(schemaId, tableId_Old, s);
                    listModelTable.reload(schemaId);
                }
            }
        }
    }

    private JPanel getPanelInput2()
    {
        FormLayout layout = new FormLayout("min:grow", //columns
                "fill:min:grow,fill:min:grow" //rows
        );
        JPanel panel = new JPanel();

        panel.setLayout(layout);

        panel.add(getPanelRelationship(), cc.xy(1, 1));
        panel.add(getPanelRelationshipPairKeys(), cc.xy(1, 2));

        return panel;
    }


    private JPanel getPanelRelationshipKeyInput(String parent_name, String child_name)
    {
        FormLayout layout = new FormLayout("min:grow,min:grow", //columns
                "fill:min:grow" //rows
        );
        JPanel panel = new JPanel();

        panel.setLayout(layout);

        panel.add(getPanelParentKeys(parent_name), cc.xy(1, 1));
        panel.add(getPanelChildKeys(child_name), cc.xy(2, 1));

        return panel;
    }


    private JPanel getPanelRelationshipInput()
    {
        FormLayout layout = new FormLayout("min:grow,min:grow", //columns
                "fill:min:grow,min,min" //rows
        );
        JPanel panel = new JPanel();

        panel.setLayout(layout);
        panel.setBorder(new TitledBorder("Relationship"));

        panel.add(getPanelParent(), cc.xy(1, 1));
        panel.add(getPanelChild(), cc.xy(2, 1));
        panel.add(getPanelVerbPhrase(), cc.xyw(1, 2, 2));
        panel.add(getPanelType(), cc.xyw(1, 3, 2));

        return panel;
    }


    private JPanel getPanelVerbPhrase()
    {
        FormLayout layout = new FormLayout("min:grow", //columns
                "min,min,min" //rows
        );
        FormLayout layout1 = new FormLayout("min,min:grow", //columns
                "min" //rows
        );
        JPanel panel = new JPanel();
        JPanel panelName = new JPanel();
        JPanel panelForwardVerb = new JPanel();
        JPanel panelInverseVerb = new JPanel();

        panel.setLayout(layout);
        panelName.setLayout(layout1);
        panelForwardVerb.setLayout(layout1);
        panelInverseVerb.setLayout(layout1);

        JLabel labelRelationshipName = new JLabel("Name:");
        JLabel labelForwardVerbPhrase = new JLabel("Forward:");
        JLabel labelInverseVerbPhrase = new JLabel("Inverse:");

        labelRelationshipName.setHorizontalAlignment(JLabel.RIGHT);
        labelForwardVerbPhrase.setHorizontalAlignment(JLabel.RIGHT);
        labelInverseVerbPhrase.setHorizontalAlignment(JLabel.RIGHT);

        panel.setToolTipText("Verb phrases explain how tables relate to each other");
        labelForwardVerbPhrase.setToolTipText("Read relationship from parent table to child table using an active verb phrase");
        labelInverseVerbPhrase.setToolTipText("Read relationship from child table to parent table using a passive verb phrase");

        panel.setBorder(new TitledBorder("Description"));

        panelName.add(labelRelationshipName, cc.xy(1, 1));
        panelName.add(txtRelationshipName, cc.xy(2, 1));

        panelForwardVerb.add(labelForwardVerbPhrase, cc.xy(1, 1));
        panelForwardVerb.add(txtForward, cc.xy(2, 1));

        panelInverseVerb.add(labelInverseVerbPhrase, cc.xy(1, 1));
        panelInverseVerb.add(txtReverse, cc.xy(2, 1));

        panel.add(panelName, cc.xy(1, 1));
        panel.add(panelForwardVerb, cc.xy(1, 2));
        panel.add(panelInverseVerb, cc.xy(1, 3));

        return panel;
    }


    private JPanel getPanelType()
    {
        JPanel panel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();

        panel.setBorder(new TitledBorder("Type"));

        btnIdentifying.setText("Identifying");
        btnNonIdentifying.setText("Non-Identifying");

        buttonGroup.add(btnIdentifying);
        buttonGroup.add(btnNonIdentifying);

        panel.add(btnIdentifying);
        panel.add(btnNonIdentifying);

        return panel;
    }


    private JPanel getPanelOutput()
    {
        FormLayout layout = new FormLayout("min:grow,min", //columns
                "fill:min:grow,min" //rows
        );
        JPanel panel = new JPanel();
        JButton btnCopy = new JButton("Copy");
        btnCopy.setToolTipText("Copy output to clipboard");
        panel.setLayout(layout);
        panel.setBorder(new TitledBorder("Output"));

        panel.add(new JScrollPane(textArea), cc.xyw(1, 1, 2));
        panel.add(btnCopy, cc.xy(2, 2));

        btnCopy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                String text = textArea.getText();
                StringSelection stringSelection = new StringSelection(text);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

        return panel;
    }


    private JPanel getPanelTable()
    {
        FormLayout layout = new FormLayout("min:grow", //columns
                "fill:min:grow,min" //rows
        );
        JPanel panel = new JPanel();
        JScrollPane scrollTable = new JScrollPane(listTable);

        panel.setLayout(layout);
        panel.setBorder(new TitledBorder("Table"));
        panel.setMinimumSize(new Dimension(250, 0));

        panel.add(scrollTable, cc.xy(1, 1));
        panel.add(getPanelTableButtons(), cc.xy(1, 2));

        listTable.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {

                eventListSelection_listTable(e);
            }
        });

        return panel;
    }


    private void eventListSelection_listTable(ListSelectionEvent e)
    {
        if (isTableSelected())
        {
            TableDataObject tableDataObject = (TableDataObject) listTable.getSelectedValue();
            Integer schemaId = tableDataObject.getSchemaId();
            Integer tableId = tableDataObject.getTableId();
            tableModelKeys.reload(schemaId, tableId);
            hideTableKeysColumns();
            tableModelRelationship.reload(schemaId,tableId);
            hideTableRelationshipColumns();
            tableModelRelationshipKeyPair.clear();
            hideTableRelationshipKeyPairColumns();
        }
    }


    private JPanel getPanelTableKeyInput()
    {
        FormLayout layout = new FormLayout("min,min:grow", //columns
                "min,min,min,min,4dlu,min" //rows
        );
        JPanel panel = new JPanel();
        Label labelName = new Label("Name: ");
        Label labelType = new Label("Type: ");
        Label labelIsPrimaryKey = new Label("Primary Key? ");
        Label labelOrder = new Label("Order: ");
        JScrollPane scrollPaneForListAttributeTypes = new JScrollPane(listKeyTypes);
        ButtonGroup primaryKeyGroup = new ButtonGroup();
        JPanel panelForPrimaryKey = new JPanel();

        panel.setLayout(layout);
        panel.setBorder(new TitledBorder("Definition"));

        labelName.setAlignment(Label.RIGHT);
        labelType.setAlignment(Label.RIGHT);
        labelIsPrimaryKey.setAlignment(Label.RIGHT);
        labelOrder.setAlignment(Label.RIGHT);

        keyName.setMinimumSize(new Dimension(100, 26));
        scrollPaneForListAttributeTypes.setMinimumSize(new Dimension(0, 100));
        keyOrder.setMinimumSize(new Dimension(100, 26));

        btnPK_Yes.setText("Yes");
        btnPK_No.setText("No");

        primaryKeyGroup.add(btnPK_Yes);
        primaryKeyGroup.add(btnPK_No);

        panelForPrimaryKey.add(btnPK_Yes);
        panelForPrimaryKey.add(btnPK_No);

        panel.add(labelName, cc.xy(1, 1));
        panel.add(keyName, cc.xy(2, 1));
        panel.add(labelType, cc.xy(1, 2));
        panel.add(scrollPaneForListAttributeTypes, cc.xy(2, 2));
        panel.add(labelIsPrimaryKey, cc.xy(1, 3));
        panel.add(panelForPrimaryKey, cc.xy(2, 3));
        panel.add(labelOrder, cc.xy(1, 4));
        panel.add(keyOrder, cc.xy(2, 4));

        keyName.getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent e)
            {
                //setText(e);
            }


            public void removeUpdate(DocumentEvent e)
            {
                setText(e);
            }


            public void setText(DocumentEvent e)
            {
                checkForAlphaNumeric(e);
            }


            public void insertUpdate(DocumentEvent e)
            {
                setText(e);
            }

        });

        return panel;

    }

    private void checkForAlphaNumeric(DocumentEvent e)
    {
        StringBuilder sb = new StringBuilder();

        if (!keyName.getText().matches("^$|^[a-zA-Z][a-zA-Z0-9_]*"))
        {
            try
            {
                sb.append("• Takes alphanumeric characters\n\n");
                sb.append("  Character is ");
                sb.append(e.getDocument().getText(e.getOffset(), 1));
                JOptionPane.showMessageDialog(null, sb.toString(), "Error Message", JOptionPane.PLAIN_MESSAGE);
            }
            catch (BadLocationException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private JPanel getPanelTableKeys()
    {
        FormLayout layout = new FormLayout("min:grow", //columns
                "fill:min:grow,min" //rows
        );
        JPanel panel = new JPanel();

        panel.setLayout(layout);
        panel.setBorder(new TitledBorder("Table Keys"));

        panel.add(new JScrollPane(tableKeys), cc.xy(1, 1));
        panel.add(getPanelTableKeyButtons(), cc.xy(1, 2));

        hideTableKeysColumns();

        return panel;
    }


    private void fillPanelTableKeyInput()
    {
        Integer selectedRow = tableKeys.getSelectedRow();

        if (selectedRow != -1)
        {
            String keyname = (String) tableModelKeys.getValueAt(selectedRow, 3);
            //String keylabel = (String) tableModelKeys.getValueAt(selectedRow, 4);
            Boolean ispk = (Boolean) tableModelKeys.getValueAt(selectedRow, 5);
            Integer typeid = (Integer) tableModelKeys.getValueAt(selectedRow, 6);
            String typename = (String) tableModelKeys.getValueAt(selectedRow, 7);
            //Integer typeprecision = (Integer) tableModelKeys.getValueAt(selectedRow, 8);
            //String typePrecisionInText = String.valueOf(typeprecision);
            //Integer typescale = (Integer) tableModelKeys.getValueAt(selectedRow, 9);
            //String typeScaleInText = String.valueOf(typescale);
            Integer keyorder = (Integer) tableModelKeys.getValueAt(selectedRow, 10);
            String keyOrderInText = String.valueOf(keyorder);
            KeyTypeDataObject keyTypeDataObject = new KeyTypeDataObject(typeid, typename, true);

            keyName.setText(keyname);
            listKeyTypes.setSelectedValue(keyTypeDataObject, true);
            btnPK_Yes.setSelected(ispk);
            btnPK_No.setSelected(!ispk);
            keyOrder.setText(keyOrderInText);
        }
    }

    private void clearPanelTableKeyInput()
    {
        keyName.setText("");
        KeyTypeDataObject keyTypeDataObject = new KeyTypeDataObject(1, "INTEGER", false);
        listKeyTypes.setSelectedValue(keyTypeDataObject, true);
        btnPK_Yes.setSelected(false);
        btnPK_No.setSelected(true);
        keyOrder.setText("");
    }


    private JPanel getPanelParent()
    {
        FormLayout layout = new FormLayout("min:grow", //columns
                "fill:min:grow" //rows
        );
        JPanel panel = new JPanel();

        panel.setLayout(layout);
        panel.setBorder(new TitledBorder("Parent Table"));

        panel.add(new JScrollPane(listParent), cc.xy(1, 1));

        return panel;
    }


    private JPanel getPanelChild()
    {
        FormLayout layout = new FormLayout("min:grow", //columns
                "fill:min:grow" //rows
        );
        JPanel panel = new JPanel();

        panel.setBorder(new TitledBorder("Child Table"));
        panel.setLayout(layout);

        panel.add(new JScrollPane(listChild), cc.xy(1, 1));

        return panel;
    }


    private JPanel getPanelRelationship()
    {
        FormLayout layout = new FormLayout("min:grow", //columns
                "fill:min:grow,min" //rows
        );
        JPanel panel = new JPanel();
        JScrollPane panelRelationshipFacts = new JScrollPane(tableRelationshipFacts);
        JPanel panelRelationshipButtons = new JPanel();
        JButton btnRelationshipAdd = new JButton("+");
        JButton btnRelationshipDelete = new JButton("-");
        JButton btnRelationshipUpdate = new JButton("u");
        JButton btnRelationshipShow = new JButton("a");

        btnRelationshipAdd.setToolTipText("Add table relationship");
        btnRelationshipDelete.setToolTipText("Delete table relationship");
        btnRelationshipUpdate.setToolTipText("Update table relationship");
        btnRelationshipShow.setToolTipText("Show all relationships");

        panel.setLayout(layout);
        panel.setBorder(new TitledBorder("Relationship"));

        panel.add(panelRelationshipFacts, cc.xy(1, 1));
        panel.add(panelRelationshipButtons, cc.xy(1, 2));
        panelRelationshipButtons.add(btnRelationshipAdd);
        panelRelationshipButtons.add(btnRelationshipDelete);
        panelRelationshipButtons.add(btnRelationshipUpdate);
        panelRelationshipButtons.add(btnRelationshipShow);

        hideTableRelationshipColumns();

        tableRelationshipFacts.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {

                eventListSelection_tableRelationship(e);
            }
        });

        btnRelationshipAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                eventActionPerformed_btnRelationshipAdd(e);
            }
        });

        btnRelationshipDelete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                eventActionPerformed_btnRelationshipDelete(e);
            }
        });

        btnRelationshipUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                eventActionPerformed_btnRelationshipUpdate(e);
            }
        });

        btnRelationshipShow.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                eventActionPerformed_btnRelationshipShow(e);
            }
        });

        return panel;
    }

    private void eventActionPerformed_btnRelationshipShow(ActionEvent e)
    {
        SchemaDataObject schemaDataObject = getSelectedSchema();
        tableModelRelationship.reload(schemaDataObject.getSchemaId());
        hideTableRelationshipColumns();
        tableModelRelationshipKeyPair.clear();
        hideTableRelationshipKeyPairColumns();
    }

    private void eventListSelection_tableRelationship(ListSelectionEvent e)
    {
        if (e.getValueIsAdjusting())
        {
            return;
        }

        Integer selectedRow = tableRelationshipFacts.getSelectedRow();

        if (selectedRow != -1)
        {
            Integer schemaid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 1);
            Integer parent_tableid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 2);
            Integer child_tableid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 4);
            Integer relationshipid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 0);

            tableModelRelationshipKeyPair.reload(schemaid, parent_tableid, child_tableid, relationshipid);

            hideTableRelationshipKeyPairColumns();
        }
    }


    private void eventActionPerformed_btnRelationshipAdd(ActionEvent e)
    {
        if(isTableSelected()){

            clearPanelRelationshipInput();

            SchemaDataObject schemaDataObject = getSelectedSchema();
            TableDataObject tableDataObject = getSelectedTable();
            listModelTableOne.reload(schemaDataObject.getSchemaId(),tableDataObject.getTableId());
            listParent.setSelectedValue(tableDataObject, true);
            listChild.clearSelection();
            JPanel panelRelationshipInput = getPanelRelationshipInput();

            panelRelationshipInput.setPreferredSize(new Dimension(700, 433));

            int i = JOptionPane.showOptionDialog(null, panelRelationshipInput, "Relationship", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Cancel", "  Ok  "}, null);

            if (i == 1)
            {
                TableDataObject parent = (TableDataObject) listParent.getSelectedValue();
                TableDataObject child = (TableDataObject) listChild.getSelectedValue();
                Integer schemaid = parent.getSchemaId();
                Integer parent_tableid = parent.getTableId();
                Integer child_tableid = child.getTableId();
                Integer relationshiptypeid;
                String forwardVerbPhrase = txtForward.getText();
                String reverseVerbPhrase = txtReverse.getText();
                String relationShipName = txtRelationshipName.getText();

                if (btnIdentifying.isSelected())
                {
                    relationshiptypeid = 0;
                } else
                {
                    relationshiptypeid = 1;
                }

                dbLink.Relationship_Insert(schemaid, parent_tableid, child_tableid, relationshiptypeid, relationShipName, forwardVerbPhrase, reverseVerbPhrase);
                tableModelRelationship.reload(schemaid,parent_tableid);
                hideTableRelationshipColumns();
            }
        }else
        {
            showMessage("Please select table.");
        }

    }

    private void clearPanelRelationshipInput()
    {
        txtRelationshipName.setText("");
        txtForward.setText("");
        txtReverse.setText("");
        btnIdentifying.setSelected(false);
        btnNonIdentifying.setSelected(true);
    }


    private void eventActionPerformed_btnRelationshipUpdate(ActionEvent e)
    {
        Integer selectedRow = tableRelationshipFacts.getSelectedRow();

        if (selectedRow != -1)
        {
            Integer schemaid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 1);
            Integer parent_tableid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 2);
            String parent = (String) tableRelationshipFacts.getValueAt(selectedRow, 3);
            Integer child_tableid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 4);
            String child = (String) tableRelationshipFacts.getValueAt(selectedRow, 5);
            Integer relationshiptypeid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 6);
            Integer relationshipid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 0);
            String relationshipname = (String) tableRelationshipFacts.getValueAt(selectedRow, 8);
            String forwardVerbPhrase = (String) tableRelationshipFacts.getValueAt(selectedRow, 9);
            String reverseVerbPhrase = (String) tableRelationshipFacts.getValueAt(selectedRow, 10);
            TableDataObject tableDataObject_Parent = new TableDataObject(schemaid, parent_tableid, parent);
            TableDataObject tableDataObject_Child = new TableDataObject(schemaid, child_tableid, child);
            listModelTableOne.reload(schemaid,parent_tableid);
            listParent.setSelectedValue(tableDataObject_Parent, true);
            listChild.setSelectedValue(tableDataObject_Child, true);
            txtRelationshipName.setText(relationshipname);
            txtForward.setText(forwardVerbPhrase);
            txtReverse.setText(reverseVerbPhrase);

            if (relationshiptypeid == 0)
            {
                btnIdentifying.setSelected(true);
            } else
            {
                btnNonIdentifying.setSelected(true);
            }

            JPanel panelRelationshipInput = getPanelRelationshipInput();

            panelRelationshipInput.setPreferredSize(new Dimension(700, 433));

            int i = JOptionPane.showOptionDialog(null, panelRelationshipInput, "Relationship", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Cancel", "  Ok  "}, null);

            if (i == 1)
            {
                relationshipname = txtRelationshipName.getText();
                forwardVerbPhrase = txtForward.getText();
                reverseVerbPhrase = txtReverse.getText();

                Integer relationshiptypeid_new;

                if (btnIdentifying.isSelected())
                {
                    relationshiptypeid_new = 0;
                } else
                {
                    relationshiptypeid_new = 1;
                }

                if (relationshiptypeid != relationshiptypeid_new)
                {
                    dbLink.RelationshipKeyPair_Delete(schemaid, parent_tableid, child_tableid, relationshipid);
                }

                dbLink.Relationship_Update(schemaid, parent_tableid, child_tableid, relationshipid, relationshiptypeid_new, relationshipname, forwardVerbPhrase, reverseVerbPhrase);
                tableModelRelationship.reload(schemaid);
                tableModelRelationshipKeyPair.reload(schemaid, parent_tableid, child_tableid, relationshipid);
                hideTableRelationshipColumns();
                hideTableRelationshipKeyPairColumns();
            }
        } else
        {
            showMessage("Please select row to update.");
        }

    }

    private void eventActionPerformed_btnRelationshipDelete(ActionEvent e)
    {
        Integer selectedRow = tableRelationshipFacts.getSelectedRow();

        if (selectedRow != -1)
        {
            Integer relationshipid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 0);
            Integer schemaid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 1);
            Integer parent_tableid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 2);
            Integer child_tableid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 4);
            Integer relationshiptypeid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 6);

            dbLink.Relationship_Delete(schemaid, parent_tableid, child_tableid, relationshipid);
            tableModelRelationship.reload(schemaid,parent_tableid);
            tableModelRelationshipKeyPair.reload(schemaid, parent_tableid, child_tableid, relationshipid);
            hideTableRelationshipColumns();
            hideTableRelationshipKeyPairColumns();
        }else
        {
            showMessage("Please select row to delete.");
        }
    }


    private JPanel getPanelParentKeys(String parent_name)
    {
        FormLayout layout = new FormLayout("min:grow", //columns
                "fill:min:grow,fill:min:grow" //rows
        );
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(listParentPK);
        JScrollPane scrollPane1 = new JScrollPane(listParentNPK);

        panel.setLayout(layout);
        panel.setBorder(new TitledBorder("Parent Table: " + parent_name));
        scrollPane.setBorder(new TitledBorder("Primary Keys"));
        scrollPane1.setBorder(new TitledBorder("Non-Primary Keys"));

        panel.add(scrollPane, cc.xy(1, 1));
        panel.add(scrollPane1, cc.xy(1, 2));

        return panel;
    }


    private JPanel getPanelChildKeys(String child_name)
    {
        FormLayout layout = new FormLayout("min:grow", //columns
                "fill:min:grow,fill:min:grow" //rows
        );
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(listChildPK);
        JScrollPane scrollPane1 = new JScrollPane(listChildNPK);

        panel.setLayout(layout);
        panel.setBorder(new TitledBorder("Child Table: " + child_name));
        scrollPane.setBorder(new TitledBorder("Primary Keys"));
        scrollPane1.setBorder(new TitledBorder("Non-Primary Keys"));

        panel.add(scrollPane, cc.xy(1, 1));
        panel.add(scrollPane1, cc.xy(1, 2));

        return panel;
    }


    private JPanel getPanelRelationshipPairKeys()
    {
        FormLayout layout = new FormLayout("min:grow", //columns
                "fill:min:grow,fill:min" //rows
        );
        JPanel panel = new JPanel();
        JPanel btnPanel = new JPanel();
        JScrollPane scrollPane2 = new JScrollPane(tableRelationshipPairKeys);
        JButton btnRelationshipKeyAdd = new JButton();
        JButton btnRelationshipKeyDelete = new JButton();

        btnRelationshipKeyAdd.setToolTipText("Add foreign key");
        btnRelationshipKeyDelete.setToolTipText("Delete foreign key");

        panel.setLayout(layout);
        panel.setBorder(new TitledBorder("Relationship Key Pair"));

        btnRelationshipKeyAdd.setText("+");
        btnRelationshipKeyDelete.setText("-");

        panel.add(scrollPane2, cc.xy(1, 1));
        panel.add(btnPanel, cc.xy(1, 2));

        btnPanel.add(btnRelationshipKeyAdd);
        btnPanel.add(btnRelationshipKeyDelete);

        hideTableRelationshipKeyPairColumns();

        btnRelationshipKeyAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                eventActionPerformed_btnRelationshipKeyAdd(e);
            }
        });

        btnRelationshipKeyDelete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                eventActionPerformed_btnRelationshipKeyDelete(e);
            }
        });

        return panel;
    }


    private void eventActionPerformed_btnRelationshipKeyAdd(ActionEvent e)
    {
        Integer selectedRow = tableRelationshipFacts.getSelectedRow();

        if (selectedRow != -1)
        {
            Integer relationshipid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 0);
            Integer schemaid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 1);
            Integer parent_tableid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 2);
            String parent_name = (String) tableRelationshipFacts.getValueAt(selectedRow, 3);
            Integer child_tableid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 4);
            String child_name = (String) tableRelationshipFacts.getValueAt(selectedRow, 5);
            Integer relationshiptypeid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 6);
            JPanel panel = getPanelRelationshipKeyInput(parent_name, child_name);

            panel.setPreferredSize(new Dimension(700, 433));

            listModelParentPK.reload(schemaid, parent_tableid);
            listModelParentNPK.reload(schemaid, parent_tableid);
            listModelChildPK.reload(schemaid, child_tableid);
            listModelChildNPK.reload(schemaid, child_tableid);

            if (relationshiptypeid == 0)
            {
                listParentNPK.setBackground(Color.lightGray);
                listChildNPK.setBackground(Color.lightGray);
            } else
            {
                listParentNPK.setBackground(Color.lightGray);
                listChildPK.setBackground(Color.lightGray);
            }

            int i = JOptionPane.showOptionDialog(null, panel, "Relationship Key Pair", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Cancel", "  Ok  "}, null);

            listParentNPK.setBackground(Color.white);
            listChildPK.setBackground(Color.white);
            listChildNPK.setBackground(Color.white);

            if (i == 1)
            {
                if (relationshiptypeid == 0)
                {
                    KeyDataObject keyDataObjectParent = (KeyDataObject) listParentPK.getSelectedValue();
                    KeyDataObject keyDataObjectChild = (KeyDataObject) listChildPK.getSelectedValue();
                    Integer parent_keyid = keyDataObjectParent.getKeyId();
                    Integer child_keyid = keyDataObjectChild.getKeyId();

                    dbLink.RelationshipKeyPair_Insert(schemaid, parent_tableid, child_tableid, relationshipid, parent_keyid, child_keyid);
                    tableModelRelationshipKeyPair.reload(schemaid, parent_tableid, child_tableid, relationshipid);
                    hideTableRelationshipKeyPairColumns();
                } else
                {
                    KeyDataObject keyDataObjectParent = (KeyDataObject) listParentPK.getSelectedValue();
                    KeyDataObject keyDataObjectChild = (KeyDataObject) listChildNPK.getSelectedValue();
                    Integer parent_keyid = keyDataObjectParent.getKeyId();
                    Integer child_keyid = keyDataObjectChild.getKeyId();

                    dbLink.RelationshipKeyPair_Insert(schemaid, parent_tableid, child_tableid, relationshipid, parent_keyid, child_keyid);
                    tableModelRelationshipKeyPair.reload(schemaid, parent_tableid, child_tableid, relationshipid);
                    hideTableRelationshipKeyPairColumns();
                }
            }
        }else{
            showMessage("Select relationship.");
        }
    }


    private void eventActionPerformed_btnRelationshipKeyDelete(ActionEvent e)
    {
        Integer selectedRow = tableRelationshipFacts.getSelectedRow();

        if (selectedRow != -1)
        {
            Integer relationshipid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 0);
            Integer schemaid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 1);
            Integer parent_tableid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 2);
            Integer child_tableid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 4);
            Integer relationshiptypeid = (Integer) tableRelationshipFacts.getValueAt(selectedRow, 6);
            selectedRow = tableRelationshipPairKeys.getSelectedRow();

            if (selectedRow != -1)
            {
                Integer parent_keyid = (Integer) tableRelationshipPairKeys.getValueAt(selectedRow, 0);
                Integer child_keyid = (Integer) tableRelationshipPairKeys.getValueAt(selectedRow, 1);

                dbLink.RelationshipKeyPair_Delete(schemaid, parent_tableid, child_tableid, relationshipid, parent_keyid, child_keyid);
                tableModelRelationshipKeyPair.reload(schemaid, parent_tableid, child_tableid, relationshipid);
                hideTableRelationshipKeyPairColumns();
            }
        }
    }


    private JPanel getPanelSchema()
    {
        FormLayout layout = new FormLayout("min:grow", //columns
                "fill:min:grow,min" //rows
        );
        JPanel panel = new JPanel();

        panel.setLayout(layout);
        panel.setMinimumSize(new Dimension(250, 0));
        panel.setBorder(new TitledBorder("Schema"));

        panel.add(new JScrollPane(listSchema), cc.xy(1, 1));
        panel.add(getPanelSchemaButtons(), cc.xy(1, 2));

        listSchema.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {

                eventListSelection_listSchema(e);
            }
        });

        return panel;
    }


    private void eventListSelection_listSchema(ListSelectionEvent e)
    {
        if (isSchemaSelected())
        {

            SchemaDataObject schemaDataObject = getSelectedSchema();
            Integer schemaId = schemaDataObject.getSchemaId();

            listModelTable.reload(schemaId);
            tableModelKeys.clear();
            hideTableKeysColumns();
            tableModelRelationship.reload(schemaId);
            hideTableRelationshipColumns();
            tableModelRelationshipKeyPair.clear();
            hideTableRelationshipKeyPairColumns();

        }
    }


    private JPanel getPanelSchemaButtons()
    {
        FormLayout layout = new FormLayout("min:grow,min,min,min,min:grow", //columns
                "min" //rows
        );
        JPanel panel = new JPanel();
        JButton btnSchemaAdd = new JButton("+");
        JButton btnSchemaDelete = new JButton("-");
        JButton btnSchemaUpdate = new JButton("u");
        btnSchemaAdd.setToolTipText("Add database schema");
        btnSchemaDelete.setToolTipText("Delete database schema");
        btnSchemaUpdate.setToolTipText("Rename database schema");
        panel.setLayout(layout);
        panel.add(btnSchemaAdd, cc.xy(2, 1));
        panel.add(btnSchemaDelete, cc.xy(3, 1));
        panel.add(btnSchemaUpdate, cc.xy(4, 1));

        btnSchemaAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_btnSchemaAdd(e);
            }
        });

        btnSchemaDelete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_btnSchemaDelete(e);
            }
        });

        btnSchemaUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_btnSchemaUpdate(e);
            }
        });

        return panel;
    }


    private void eventActionPerformed_btnSchemaAdd(ActionEvent e)
    {
        String s = (String) JOptionPane.showInputDialog(null, "What is your new schema name?", "New Schema", -1, null, null, "");

        if (isAlphaNumeric(s))
        {
            dbLink.DatabaseSchema_Insert(s);
            listModelSchema.reload();
        }
    }


    private void eventActionPerformed_btnSchemaUpdate(ActionEvent e)
    {
        if (isSchemaSelected())
        {
            SchemaDataObject schemaDataObject = getSelectedSchema();
            String s = (String) JOptionPane.showInputDialog(null, "What is your new schema name?", "New Schema", -1, null, null, schemaDataObject.getSchemaName());
            if (isAlphaNumeric(s))
            {
                Integer schemaId = schemaDataObject.getSchemaId();
                dbLink.DatabaseSchema_Update(schemaId, s);
                listModelSchema.reload();
            }
        }

    }


    private void eventActionPerformed_btnSchemaDelete(ActionEvent e)
    {
        if (isSchemaSelected())
        {
            SchemaDataObject schemaDataObject = getSelectedSchema();
            Integer schemaId = schemaDataObject.getSchemaId();
            dbLink.DatabaseSchema_Delete(schemaId);
            listModelSchema.reload();
            listModelTable.clear();
            tableModelKeys.clear();
            hideTableKeysColumns();
            tableModelRelationship.clear();
            hideTableRelationshipColumns();
            tableModelRelationshipKeyPair.clear();
            hideTableRelationshipKeyPairColumns();
        }
    }


    private JPanel getPanelTableButtons()
    {
        FormLayout layout = new FormLayout("min:grow,min,min,min,min:grow", //columns
                "min" //rows
        );
        JPanel panel = new JPanel();
        JButton btnTableAdd = new JButton("+");
        JButton btnTableDelete = new JButton("-");
        JButton btnTableUpdate = new JButton("u");
        btnTableAdd.setToolTipText("Add database table");
        btnTableDelete.setToolTipText("Delete database table");
        btnTableUpdate.setToolTipText("Rename database table");
        panel.setLayout(layout);
        panel.add(btnTableAdd, cc.xy(2, 1));
        panel.add(btnTableDelete, cc.xy(3, 1));
        panel.add(btnTableUpdate, cc.xy(4, 1));

        btnTableAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_btnTableAdd(e);
            }
        });

        btnTableDelete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_btnTableDelete(e);
            }
        });

        btnTableUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_btnTableUpdate(e);
            }
        });

        return panel;
    }


    private void eventActionPerformed_btnTableUpdate(ActionEvent e)
    {
        if (isSchemaSelected())
        {
            TableDataObject tableDataObject = (TableDataObject) listTable.getSelectedValue();
            String s = (String) JOptionPane.showInputDialog(null, "What is your new table name?", "New Table", -1, null, null, tableDataObject.getTableName());
            if (isAlphaNumeric(s))
            {
                Integer schemaId = tableDataObject.getSchemaId();
                Integer tableId = tableDataObject.getTableId();

                dbLink.DatabaseTable_Update(schemaId, tableId, s);
                listModelTable.reload(schemaId);
                tableModelRelationship.reload(schemaId);
                hideTableRelationshipColumns();
            }
        }
    }


    private void eventActionPerformed_btnTableDelete(ActionEvent e)
    {
        if (isSchemaSelected())
        {
            TableDataObject tableDataObject = (TableDataObject) listTable.getSelectedValue();
            Integer schemaId = tableDataObject.getSchemaId();
            Integer tableId = tableDataObject.getTableId();

            dbLink.DatabaseTable_Delete(schemaId, tableId);
            listModelTable.reload(schemaId);
            tableModelKeys.clear();
            hideTableKeysColumns();
            tableModelRelationship.reload(schemaId);
            hideTableRelationshipColumns();
            tableModelRelationshipKeyPair.clear();
            hideTableRelationshipKeyPairColumns();
        }
    }


    private void eventActionPerformed_btnTableAdd(ActionEvent e)
    {
        if (isSchemaSelected())
        {
            String s = (String) JOptionPane.showInputDialog(null, "What is your new table name?", "New Table", -1, null, null, "");

            if (isAlphaNumeric(s))
            {
                SchemaDataObject schemaDataObject = getSelectedSchema();
                Integer schemaId = schemaDataObject.getSchemaId();

                dbLink.DatabaseTable_Insert(schemaId, s);
                listModelTable.reload(schemaId);
                tableModelKeys.clear();
                hideTableKeysColumns();
            }
        }
    }


    private boolean isAlphaNumeric(String s)
    {
        return s != null && s.length() > 0 && s.matches("^$|^[a-zA-Z][a-zA-Z0-9_]*");
    }

    private JPanel getPanelTableKeyButtons()
    {
        FormLayout layout = new FormLayout("min:grow,min,min,min,min:grow", //columns
                "min" //rows
        );
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(layout);
        JButton btnKeyAdd = new JButton("+");
        JButton btnKeyDelete = new JButton("-");
        JButton btnKeyUpdate = new JButton("u");
        btnKeyAdd.setToolTipText("Add table key");
        btnKeyDelete.setToolTipText("Delete table key");
        btnKeyUpdate.setToolTipText("Update table key");
        buttonPanel.add(btnKeyAdd, cc.xy(2, 1));
        buttonPanel.add(btnKeyDelete, cc.xy(3, 1));
        buttonPanel.add(btnKeyUpdate, cc.xy(4, 1));

        btnKeyAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_btnKeyAdd(e);
            }
        });

        btnKeyDelete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_btnKeyDelete(e);
            }
        });

        btnKeyUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventActionPerformed_btnKeyUpdate(e);
            }
        });

        return buttonPanel;
    }


    private void eventActionPerformed_btnKeyAdd(ActionEvent e)
    {
        if (isTableSelected())
        {
            clearPanelTableKeyInput();
            JPanel panelTableKeyInput = getPanelTableKeyInput();
            panelTableKeyInput.setPreferredSize(new Dimension(404, 250));

            int i = JOptionPane.showOptionDialog(null, panelTableKeyInput, "Table Key", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Cancel", "  Ok  "}, null);

            if (i == 1)
            {
                TableDataObject tableDataObject = (TableDataObject) listTable.getSelectedValue();

                if (hasKeyTypesListBeenSelected())
                {
                    KeyTypeDataObject keyTypeDataObject = (KeyTypeDataObject) listKeyTypes.getSelectedValue();
                    String keyOrderText = keyOrder.getText();
                    Integer schemaid = tableDataObject.getSchemaId();
                    Integer tableid = tableDataObject.getTableId();
                    String keyname = keyName.getText();
                    String keylabel = keyname;
                    Boolean keyispk = btnPK_Yes.isSelected();
                    Integer keytypeid = keyTypeDataObject.getTypeId();
                    Integer keyprecision = getKeyPrecision(keytypeid);
                    Integer keyscale = getKeyScale(keytypeid);
                    Integer keyorder = Integer.parseInt(keyOrderText);

                    dbLink.TableKey_Insert(schemaid, tableid, keyname, keylabel, keyispk, keytypeid, keyprecision, keyscale, keyorder);
                    tableModelKeys.reload(schemaid, tableid);
                    hideTableKeysColumns();
                }
            } else
            {
                hideTableKeysColumns();
            }
        }else
        {
            showMessage("Please select table.");
        }
    }

    private void eventActionPerformed_btnKeyUpdate(ActionEvent e)
    {
        if (isTableSelected())
        {
            fillPanelTableKeyInput();
            JPanel panelTableKeyInput = getPanelTableKeyInput();
            panelTableKeyInput.setPreferredSize(new Dimension(404, 250));

            int i = JOptionPane.showOptionDialog(null, panelTableKeyInput, "Table Key", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Cancel", "  Ok  "}, null);

            if (i == 1)
            {
                TableDataObject tableDataObject = (TableDataObject) listTable.getSelectedValue();
                if (hasKeyTypesListBeenSelected())
                {
                    KeyTypeDataObject keyTypeDataObject = (KeyTypeDataObject) listKeyTypes.getSelectedValue();
                    String keyOrderText = keyOrder.getText();
                    Integer schemaid = tableDataObject.getSchemaId();
                    Integer tableid = tableDataObject.getTableId();
                    Integer selectedRow = tableKeys.getSelectedRow();
                    if (selectedRow != -1)
                    {
                        Integer keyid = (Integer) tableModelKeys.getValueAt(selectedRow, 2);
                        String keyname = keyName.getText();
                        String keylabel = keyname;
                        Boolean keyispk = btnPK_Yes.isSelected();
                        Integer keytypeid = keyTypeDataObject.getTypeId();
                        Integer keyprecision = getKeyPrecision(keytypeid);
                        Integer keyscale = getKeyScale(keytypeid);
                        Integer keyorder = Integer.parseInt(keyOrderText);

                        dbLink.TableKey_Update(schemaid, tableid, keyid, keyname, keylabel, keyispk, keytypeid, keyprecision, keyscale, keyorder);
                        tableModelKeys.reload(schemaid, tableid);
                        hideTableKeysColumns();
                    }
                }
            } else
            {
                hideTableKeysColumns();
            }
        }
    }


    private boolean hasKeyTypesListBeenSelected()
    {
        return !listKeyTypes.isSelectionEmpty();
    }


    private void eventActionPerformed_btnKeyDelete(ActionEvent e)
    {
        if (isTableSelected())
        {
            TableDataObject tableDataObject = (TableDataObject) listTable.getSelectedValue();
            Integer schemaid = tableDataObject.getSchemaId();
            Integer tableid = tableDataObject.getTableId();
            Integer selectedRow = tableKeys.getSelectedRow();

            if (selectedRow != -1)
            {
                Integer keyid = (Integer) tableModelKeys.getValueAt(selectedRow, 2);

                dbLink.TableKey_Delete(schemaid, tableid, keyid);
                tableModelKeys.reload(schemaid, tableid);
                hideTableKeysColumns();
            }
        }
    }


    private Integer getKeyPrecision(Integer keytypeid)
    {
        /* *
            call keytype_insert(0,'IDENTITY',false,false);
            call keytype_insert(1,'INTEGER',false,false);
            call keytype_insert(2,'DOUBLE',false,false);
            call keytype_insert(3,'VARCHAR',true,false);
            call keytype_insert(4,'BOOLEAN',false,false);
            call keytype_insert(5,'BLOB',false,false);
            call keytype_insert(6,'DATE',false,false);
            call keytype_insert(7,'TIME',false,false);
            call keytype_insert(8,'TIMESTAMP',false,false);
            call keytype_insert(9,'DECIMAL',true,true);
        * */

        Integer precision = null;

        switch (keytypeid)
        {
            case 3:
                precision = 8000;
                break;
            case 9:
                precision = 10;
                break;
            default:
                precision = 0;
                break;
        }

        return precision;
    }


    private Integer getKeyScale(Integer keytypeid)
    {
        /* *
            call keytype_insert(0,'IDENTITY',false,false);
            call keytype_insert(1,'INTEGER',false,false);
            call keytype_insert(2,'DOUBLE',false,false);
            call keytype_insert(3,'VARCHAR',true,false);
            call keytype_insert(4,'BOOLEAN',false,false);
            call keytype_insert(5,'BLOB',false,false);
            call keytype_insert(6,'DATE',false,false);
            call keytype_insert(7,'TIME',false,false);
            call keytype_insert(8,'TIMESTAMP',false,false);
            call keytype_insert(9,'DECIMAL',true,true);
        * */

        Integer scale = null;

        switch (keytypeid)
        {
            case 9:
                scale = 2;
                break;
            default:
                scale = 0;
                break;
        }

        return scale;
    }


    private void eventActionPerformed_mnuiExit(ActionEvent e)
    {
        frame.dispose();
        dbLink.Shutdown();
    }


    private void hideTableKeysColumns()
    {
        tableKeys.getColumnModel().getColumn(0).setMinWidth(0);
        tableKeys.getColumnModel().getColumn(0).setMaxWidth(0);
        tableKeys.getColumnModel().getColumn(0).setWidth(0);
        tableKeys.getColumnModel().getColumn(1).setMinWidth(0);
        tableKeys.getColumnModel().getColumn(1).setMaxWidth(0);
        tableKeys.getColumnModel().getColumn(1).setWidth(0);
        tableKeys.getColumnModel().getColumn(2).setMinWidth(0);
        tableKeys.getColumnModel().getColumn(2).setMaxWidth(0);
        tableKeys.getColumnModel().getColumn(2).setWidth(0);
        tableKeys.getColumnModel().getColumn(4).setMinWidth(0);
        tableKeys.getColumnModel().getColumn(4).setMaxWidth(0);
        tableKeys.getColumnModel().getColumn(4).setWidth(0);
        tableKeys.getColumnModel().getColumn(6).setMinWidth(0);
        tableKeys.getColumnModel().getColumn(6).setMaxWidth(0);
        tableKeys.getColumnModel().getColumn(6).setWidth(0);
        tableKeys.getColumnModel().getColumn(8).setMinWidth(0);
        tableKeys.getColumnModel().getColumn(8).setMaxWidth(0);
        tableKeys.getColumnModel().getColumn(8).setWidth(0);
        tableKeys.getColumnModel().getColumn(9).setMinWidth(0);
        tableKeys.getColumnModel().getColumn(9).setMaxWidth(0);
        tableKeys.getColumnModel().getColumn(9).setWidth(0);
    }


    private void hideTableRelationshipColumns()
    {
        tableRelationshipFacts.getColumnModel().getColumn(0).setMinWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(0).setMaxWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(0).setWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(1).setMinWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(1).setMaxWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(1).setWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(2).setMinWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(2).setMaxWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(2).setWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(4).setMinWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(4).setMaxWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(4).setWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(6).setMinWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(6).setMaxWidth(0);
        tableRelationshipFacts.getColumnModel().getColumn(6).setWidth(0);
    }


    private void hideTableRelationshipKeyPairColumns()
    {
        tableRelationshipPairKeys.getColumnModel().getColumn(0).setMinWidth(0);
        tableRelationshipPairKeys.getColumnModel().getColumn(0).setMaxWidth(0);
        tableRelationshipPairKeys.getColumnModel().getColumn(0).setWidth(0);
        tableRelationshipPairKeys.getColumnModel().getColumn(1).setMinWidth(0);
        tableRelationshipPairKeys.getColumnModel().getColumn(1).setMaxWidth(0);
        tableRelationshipPairKeys.getColumnModel().getColumn(1).setWidth(0);

    }


    private void showMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "PotatoSQL", JOptionPane.INFORMATION_MESSAGE);
    }


    private void selectOptions()
    {

        FormLayout layoutJava01 = new FormLayout("min", //columns
                "min,10dlu,min,10dlu" //rows
        );

        FormLayout layoutJava02 = new FormLayout("min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu", //columns
                "min,10dlu,min,10dlu,min,10dlu" //rows
        );

        FormLayout layoutSql01 = new FormLayout("min", //columns
                "min,min,min,min,min" //rows
        );

        FormLayout layoutSql02 = new FormLayout("min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu", //columns
                "min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu,min,10dlu" //rows
        );

        FormLayout layoutSchemaSql = new FormLayout("min", //columns
                "min,min,min,min,min,min" //rows
        );

        FormLayout layoutOtherJava = new FormLayout("min", //columns
                "min,10dlu,min,10dlu" //rows
        );

        FormLayout layoutJava = new FormLayout("20dlu,min:grow,20dlu", //columns
                "20dlu,min,5dlu,min,20dlu" //rows
        );


        FormLayout layout = new FormLayout("min:grow,min", //columns
                "min,min" //rows
        );

        JPanel pnlSchemaSql = new JPanel();
        JPanel pnlTableSql01 = new JPanel();
        JPanel pnlTableSql02 = new JPanel();
        JPanel pnlJava01 = new JPanel();
        JPanel pnlJava02 = new JPanel();
        JPanel pnlTableJava = new JPanel();
        JPanel pnlOtherJava = new JPanel();
        JPanel panel = new JPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);

        pnlSchemaSql.setLayout(layoutSchemaSql);
        pnlJava01.setLayout(layoutJava01);
        pnlJava02.setLayout(layoutJava02);
        pnlTableSql01.setLayout(layoutSql01);
        pnlTableSql02.setLayout(layoutSql02);
        pnlTableJava.setLayout(layoutJava);
        pnlOtherJava.setLayout(layoutOtherJava);
        panel.setLayout(layout);

        pnlTableJava.add(pnlJava01, cc.xy(2, 2));
        pnlTableJava.add(pnlJava02, cc.xy(2, 4));

        tabbedPane.add(pnlSchemaSql, "Schema / Sql");
        tabbedPane.add(pnlTableSql01, "Table / Sql (1)");
        tabbedPane.add(pnlTableSql02, "Table / Sql (2)");
        tabbedPane.add(pnlTableJava, "Table / Java");
        tabbedPane.add(pnlOtherJava, "Other / Java");

        panel.add(tabbedPane, cc.xyw(1, 1, 2));
        panel.add(clear, cc.xy(2, 2));

        JLabel label_Insert = new JLabel("Insert");
        JLabel label_Update = new JLabel("Update");
        JLabel label_Delete = new JLabel("Delete");
        JLabel label_Merge = new JLabel("Merge");
        JLabel label_Select = new JLabel("Select");
        JLabel label_DeleteAll = new JLabel("Delete All");
        JLabel label_SelectAll = new JLabel("Select All");
        JLabel label_Count = new JLabel("Count");
        JLabel label_Statement = new JLabel("Statement");
        JLabel label_Procedure = new JLabel("Procedure/Function");
        JLabel label_Method = new JLabel("Method");
        JLabel label_MethodPrint = new JLabel("Method Print");
        JLabel label_Trigger_Statement_After = new JLabel("(Statement Level, After Event) Trigger");
        JLabel label_Trigger_Row_Before = new JLabel("(Row Level, Before Event) Trigger");
        JLabel label_Trigger_Row_After = new JLabel(" (Row Level, After Event) Trigger");

        label_Statement.setHorizontalAlignment(JLabel.RIGHT);
        label_Procedure.setHorizontalAlignment(JLabel.RIGHT);
        label_Method.setHorizontalAlignment(JLabel.RIGHT);
        label_MethodPrint.setHorizontalAlignment(JLabel.RIGHT);
        label_Trigger_Statement_After.setHorizontalAlignment(JLabel.RIGHT);
        label_Trigger_Row_Before.setHorizontalAlignment(JLabel.RIGHT);
        label_Trigger_Row_After.setHorizontalAlignment(JLabel.RIGHT);

        JLabel label_Insert2 = new JLabel("Insert");
        JLabel label_Update2 = new JLabel("Update");
        JLabel label_Delete2 = new JLabel("Delete");
        JLabel label_Merge2 = new JLabel("Merge");
        JLabel label_Select2 = new JLabel("Select");
        JLabel label_DeleteAll2 = new JLabel("Delete All");
        JLabel label_SelectAll2 = new JLabel("Select All");
        JLabel label_Count2 = new JLabel("Count");
        JLabel label_Method2 = new JLabel("Method");
        JLabel label_MethodPrint2 = new JLabel("Method Print");

        pnlSchemaSql.add(cboxTables, cc.xy(1, 1));

        pnlOtherJava.add(cboxTestClass, cc.xy(1, 1));

        pnlTableSql01.add(cboxTablesDup, cc.xy(1, 1));
        pnlTableSql01.add(cboxViews, cc.xy(1, 2));
        pnlTableSql01.add(cboxStmtCreateSelect, cc.xy(1, 3));
        pnlTableSql01.add(cboxStmtInsertSelect, cc.xy(1, 4));

        pnlTableSql02.add(label_Insert, cc.xy(3, 1));
        pnlTableSql02.add(label_Update, cc.xy(5, 1));
        pnlTableSql02.add(label_Delete, cc.xy(7, 1));
        pnlTableSql02.add(label_Merge, cc.xy(9, 1));
        pnlTableSql02.add(label_Select, cc.xy(11, 1));
        pnlTableSql02.add(label_DeleteAll, cc.xy(13, 1));
        pnlTableSql02.add(label_SelectAll, cc.xy(15, 1));
        pnlTableSql02.add(label_Count, cc.xy(17, 1));
        pnlTableSql02.add(label_Statement, cc.xy(1, 3));
        pnlTableSql02.add(label_Procedure, cc.xy(1, 5));
        pnlTableSql02.add(label_Trigger_Statement_After, cc.xy(1, 7));
        pnlTableSql02.add(label_Trigger_Row_Before, cc.xy(1, 9));
        pnlTableSql02.add(label_Trigger_Row_After, cc.xy(1, 11));
        pnlTableSql02.add(cboxStmtInsert, cc.xy(3, 3));
        pnlTableSql02.add(cboxStmtUpdate, cc.xy(5, 3));
        pnlTableSql02.add(cboxStmtDelete, cc.xy(7, 3));
        pnlTableSql02.add(cboxStmtMerge, cc.xy(9, 3));
        pnlTableSql02.add(cboxStmtSelect, cc.xy(11, 3));
        pnlTableSql02.add(cboxStmtDeleteAll, cc.xy(13, 3));
        pnlTableSql02.add(cboxStmtSelectAll, cc.xy(15, 3));
        pnlTableSql02.add(cboxStmtCount, cc.xy(17, 3));
        pnlTableSql02.add(cboxProcInsert, cc.xy(3, 5));
        pnlTableSql02.add(cboxProcUpdate, cc.xy(5, 5));
        pnlTableSql02.add(cboxProcDelete, cc.xy(7, 5));
        pnlTableSql02.add(cboxProcMerge, cc.xy(9, 5));
        pnlTableSql02.add(cboxProcSelect, cc.xy(11, 5));
        pnlTableSql02.add(cboxProcDeleteAll, cc.xy(13, 5));
        pnlTableSql02.add(cboxProcSelectAll, cc.xy(15, 5));
        pnlTableSql02.add(cboxFunctionCount, cc.xy(17, 5));
        pnlTableSql02.add(cboxTriggerStatementAfterInsert, cc.xy(3, 7));
        pnlTableSql02.add(cboxTriggerStatementAfterUpdate, cc.xy(5, 7));
        pnlTableSql02.add(cboxTriggerStatementAfterDelete, cc.xy(7, 7));
        pnlTableSql02.add(cboxTriggerRowBeforeInsert, cc.xy(3, 9));
        pnlTableSql02.add(cboxTriggerRowBeforeUpdate, cc.xy(5, 9));
        pnlTableSql02.add(cboxTriggerRowBeforeDelete, cc.xy(7, 9));
        pnlTableSql02.add(cboxTriggerRowAfterInsert, cc.xy(3, 11));
        pnlTableSql02.add(cboxTriggerRowAfterUpdate, cc.xy(5, 11));
        pnlTableSql02.add(cboxTriggerRowAfterDelete, cc.xy(7, 11));

        pnlJava01.add(cboxDataObject, cc.xy(1, 3));

        //first row
        pnlJava02.add(label_Insert2, cc.xy(3, 1));
        pnlJava02.add(label_Update2, cc.xy(5, 1));
        pnlJava02.add(label_Delete2, cc.xy(7, 1));
        pnlJava02.add(label_Merge2, cc.xy(9, 1));
        pnlJava02.add(label_Select2, cc.xy(11, 1));
        pnlJava02.add(label_DeleteAll2, cc.xy(13, 1));
        pnlJava02.add(label_SelectAll2, cc.xy(15, 1));
        pnlJava02.add(label_Count2, cc.xy(17, 1));
        //third row
        pnlJava02.add(label_Method2, cc.xy(1, 3));
        pnlJava02.add(cboxMethodInsert, cc.xy(3, 3));
        pnlJava02.add(cboxMethodUpdate, cc.xy(5, 3));
        pnlJava02.add(cboxMethodDelete, cc.xy(7, 3));
        pnlJava02.add(cboxMethodMerge, cc.xy(9, 3));
        pnlJava02.add(cboxMethodSelect, cc.xy(11, 3));
        pnlJava02.add(cboxMethodDeleteAll, cc.xy(13, 3));
        pnlJava02.add(cboxMethodSelectAll, cc.xy(15, 3));
        pnlJava02.add(cboxMethodFunctionCount, cc.xy(17, 3));
        //fifth row
        pnlJava02.add(label_MethodPrint2, cc.xy(1, 5));
        pnlJava02.add(cboxMethodSelectPrint, cc.xy(11, 5));
        pnlJava02.add(cboxMethodSelectAllPrint, cc.xy(15, 5));


        int i = JOptionPane.showOptionDialog(null, panel, "Output", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Cancel", "  Ok  "}, null);

        clear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Boolean flag = false;

                cboxTriggerStatementAfterInsert.setSelected(flag);
                cboxTriggerStatementAfterUpdate.setSelected(flag);
                cboxTriggerStatementAfterDelete.setSelected(flag);
                cboxTriggerRowBeforeInsert.setSelected(flag);
                cboxTriggerRowBeforeUpdate.setSelected(flag);
                cboxTriggerRowBeforeDelete.setSelected(flag);
                cboxTriggerRowAfterInsert.setSelected(flag);
                cboxTriggerRowAfterUpdate.setSelected(flag);
                cboxTriggerRowAfterDelete.setSelected(flag);
                cboxTablesDup.setSelected(flag);
                cboxViews.setSelected(flag);
                cboxTestClass.setSelected(flag);
                cboxDataObject.setSelected(flag);
                cboxTables.setSelected(flag);
                cboxStmtInsert.setSelected(flag);
                cboxStmtUpdate.setSelected(flag);
                cboxStmtDelete.setSelected(flag);
                cboxStmtMerge.setSelected(flag);
                cboxStmtSelect.setSelected(flag);
                cboxStmtDeleteAll.setSelected(flag);
                cboxStmtSelectAll.setSelected(flag);
                cboxStmtCount.setSelected(flag);
                cboxStmtCreateSelect.setSelected(flag);
                cboxStmtInsertSelect.setSelected(flag);
                cboxProcInsert.setSelected(flag);
                cboxProcUpdate.setSelected(flag);
                cboxProcDelete.setSelected(flag);
                cboxProcMerge.setSelected(flag);
                cboxProcSelect.setSelected(flag);
                cboxProcDeleteAll.setSelected(flag);
                cboxProcSelectAll.setSelected(flag);
                cboxFunctionCount.setSelected(flag);
                cboxMethodInsert.setSelected(flag);
                cboxMethodUpdate.setSelected(flag);
                cboxMethodDelete.setSelected(flag);
                cboxMethodMerge.setSelected(flag);
                cboxMethodSelect.setSelected(flag);
                cboxMethodDeleteAll.setSelected(flag);
                cboxMethodSelectAll.setSelected(flag);
                cboxMethodFunctionCount.setSelected(flag);
                cboxMethodSelectPrint.setSelected(flag);
                cboxMethodSelectAllPrint.setSelected(flag);
                textArea.setText("");
            }
        });
    }

    private void openUrl(String url)
    {
        try
        {
            Desktop.getDesktop().browse(new URL(url).toURI());
        }
        catch (Exception ex)
        {
        }
    }
}
