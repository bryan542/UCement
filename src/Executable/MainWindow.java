package Executable;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;

import com.thehowtotutorial.splashscreen.JSplash;
/**
 * Created by Bryan on 7/13/2017.
 */
public class MainWindow extends JFrame {


    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField waterDensityTextField;
    private JPanel cementComponentJPanel;
    private JPanel saltComponentsJPanel;
    private JPanel dryComponentsJPanel;
    private JPanel liquidComponentsJPanel;
    private JButton addRowCementButton;
    private JButton addSaltComponentButton;
    private JButton addDryComponentButton;
    private JButton addLiquidComponentButton;
    private JButton cementImportDatabaseJButton;
    private JButton saltImportDatabaseJButton;
    private JButton dryImportDatabaseJButton;
    private JButton liquidImportDatabaseJButton;
    private JButton addNewCementButton;
    private JButton exportCementDatabaseButton;
    private JButton addRowSaltButton;
    private JButton addRowDryButton;
    private JButton exportSaltDatabaseButton;
    private JButton exportDryDatabaseButton;
    private JButton addRowLiquidButton;
    private JButton exportLiquidDatabaseButton;
    private JButton calculateButton;
    private JButton deleteRowCementButton;
    private JButton deleteRowLiquidButton;
    private JButton deleteRowDryButton;
    private JButton deleteRowSaltButton;
    private JScrollPane mainScrollPane;



    ArrayList cementNames = new ArrayList();
    ArrayList cementNumberPerSack = new ArrayList();
    ArrayList cementAbsVolume = new ArrayList();
    ArrayList cementBulkWeight = new ArrayList();
    ArrayList cementPrice = new ArrayList();

    ArrayList saltBWOW = new ArrayList();
    ArrayList saltAbsVolume = new ArrayList();
    ArrayList KCLAbsVolume = new ArrayList();
    ArrayList saltPrice = new ArrayList();
    ArrayList KCLPrice = new ArrayList();

    ArrayList dryNames = new ArrayList();
    ArrayList dryAbsVolume = new ArrayList();
    ArrayList dryBulkWeight = new ArrayList();
    ArrayList dryPrice = new ArrayList();

    ArrayList liquidNames = new ArrayList();
    ArrayList liquidAbsVolume = new ArrayList();
    ArrayList liquidBulkWeight = new ArrayList();
    ArrayList liquidPrice = new ArrayList();

    public ArrayList getLiquidNames() {
        return liquidNames;
    }

    public ArrayList getLiquidAbsVolume() {
        return liquidAbsVolume;
    }

    public ArrayList getLiquidBulkWeight() {
        return liquidBulkWeight;
    }

    public ArrayList getLiquidPrice() {
        return liquidPrice;
    }

    public ArrayList getDryNames() {
        return dryNames;
    }

    public ArrayList getDryAbsVolume() {
        return dryAbsVolume;
    }

    public ArrayList getDryBulkWeight() {
        return dryBulkWeight;
    }

    public ArrayList getDryPrice() {
        return dryPrice;
    }



    public ArrayList getSaltBWOW() {
        return saltBWOW;
    }

    public ArrayList getSaltAbsVolume() {
        return saltAbsVolume;
    }

    public ArrayList getKCLAbsVolume() {
        return KCLAbsVolume;
    }

    public ArrayList getSaltPrice() {
        return saltPrice;
    }

    public ArrayList getKCLPrice() {
        return KCLPrice;
    }



    public ArrayList getCementNames() {
        return cementNames;
    }

    public ArrayList getCementNumberPerSack() {
        return cementNumberPerSack;
    }

    public ArrayList getCementAbsVolume() {
        return cementAbsVolume;
    }

    public ArrayList getCementBulkWeight() {
        return cementBulkWeight;
    }

    public ArrayList getCementPrice() {
        return cementPrice;
    }

    public void removeSelectedRows(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int[] rows = table.getSelectedRows();
        for(int i=0;i<rows.length;i++){
            model.removeRow(rows[i]-i);
        }
    }

    //Main design window with the JFrame controls implimented
    public MainWindow() {

        mainScrollPane.getVerticalScrollBar().setUnitIncrement(15);
        //sets EGI image icon
        URL EGIURL = MainWindow.class.getResource("/Images/PES Logo.png");
        ImageIcon EGIImageIcon = new ImageIcon(EGIURL);
        Image EGIImage = EGIImageIcon.getImage();
        MainWindow.this.setIconImage(EGIImage);

        //builds all the menus selected at the top of the application
        DropDownMenu menu = new DropDownMenu();
        menu.buildMenuBar(MainWindow.this);



        String[] cementItems = {"Select Cement"};
        //JTable column names
        String[] cementComponentColumns = {"Index","Cement","% CU.FT.","LBS/Sack","Absolute Volume LBS/Gal","Bulk Weight lbs/cuft","$ Cost/lb"};

        //Adds the initializer items that will be updated once components or a database is inserted
        JComboBox cementEditorComboBox = new JComboBox(cementItems);
        Object [][] cementComponentData = {{1,cementEditorComboBox,"","","","",""}};



        JTable cementComponentsJTable = new JTable(new DefaultTableModel(cementComponentData,cementComponentColumns)){

            //Prevents the user from changing cells that will be populated form the databae when a dropdown item
            //is selected or when the calculation is done.
            public boolean isCellEditable(int row, int col) {

                    return col >0;
            }
        };


        cementComponentsJTable.setShowGrid(true);

        //Makes sure the jcombobox updates properly and updates if the mouse focus is on the combobox updates properly when importing or adding
        InitialJTableComboBoxSetup ICBS = new InitialJTableComboBoxSetup(cementComponentsJTable,cementEditorComboBox,cementItems);


        TableSetup setupCement = new TableSetup(MainWindow.this,cementComponentsJTable, cementComponentJPanel,cementItems, "Cement Table"); //sets table editable/layout values
        JScrollPane cementComponentJScrollPane = new JScrollPane(cementComponentsJTable);
        cementComponentJPanel.setLayout(new BorderLayout());
        cementComponentJPanel.add(cementComponentJScrollPane,BorderLayout.CENTER); //adds table to jpanel
        ExcelAdapter myCementAdapter = new ExcelAdapter(cementComponentsJTable); //allows excel copy/paste cells

        //Deletes the selected row
        deleteRowCementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                removeSelectedRows(cementComponentsJTable);
            }
        });
        //adds a new row when selected
        addRowCementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                AddRow addCementRow = new AddRow(MainWindow.this,cementComponentsJTable,cementEditorComboBox,"Cement");

            }
        });

        //
        addNewCementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waterDensityTextField.requestFocus();
                CementComponentAdder cca = new CementComponentAdder(MainWindow.this, cementComponentsJTable,cementEditorComboBox);
                cca.initialize();

                String[] updatedNames = new String[getCementNames().size()];
                for (int i = 0;i<updatedNames.length;i++){

                    updatedNames[i] = getCementNames().get(0).toString();
                }



            }
        });

        cementImportDatabaseJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DatabaseComboBoxPopulator dcbp = new DatabaseComboBoxPopulator(MainWindow.this,cementComponentsJTable,cementEditorComboBox,"Cement");
            }
        });

        //Exports a CSV file with the current cement database
        exportCementDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SaveFilePathLocation sdLoad = new SaveFilePathLocation();
                String filename = sdLoad.getSaveLocation(MainWindow.this);

               CSVDatabaseExporter cde = new CSVDatabaseExporter(MainWindow.this,filename,"Cement");

            }
        });


        //populates the cement cells when a combobox is selected
        Action cementAction = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                TableCellListener tcl = (TableCellListener)e.getSource();

                //finds the index in the arras that the chosen name is at
                int indexValue = getCementNames().indexOf(tcl.getNewValue().toString());

                //used that index value to locate the proper items when a combobox item is selected and populates
                //the correct cells
                double lbSack = Double.parseDouble(getCementNumberPerSack().get(indexValue).toString());
                double absVolume = Double.parseDouble(getCementAbsVolume().get(indexValue).toString());
                double bulkWeight = Double.parseDouble(getCementBulkWeight().get(indexValue).toString());
                double price = Double.parseDouble(getCementPrice().get(indexValue).toString());

                DefaultTableModel tableValueSetter = (DefaultTableModel) cementComponentsJTable.getModel();
                tableValueSetter.setValueAt(lbSack, tcl.getRow(), 3);
                tableValueSetter.setValueAt(absVolume, tcl.getRow(), 4);
                tableValueSetter.setValueAt(bulkWeight,tcl.getRow(),5);
                tableValueSetter.setValueAt(price, tcl.getRow(), 6);


            }
        };

        //updates the other cement cells when the combobox item in that row is selected
        TableCellListener tclCement = new TableCellListener(cementComponentsJTable, cementAction);


        String[] saltTypes = {"Select Salt"};
        JComboBox saltTypeComboBox = new JComboBox(saltTypes);
        String[] saltComponentItems = {"NaCl","KCl"};
        JComboBox saltEditorComboBox = new JComboBox(saltComponentItems);

        String[] saltComponentColumns = {"Index","Salts % BWOW","Salt Component","Salt Abs. Volume lbs/gal","KCl Abs. Volume lbs/gal","Salt $Cost/lb","KCl $Cost/lb"};


         //Adds the initializer items that will be updated once components or a database is inserted
        Object [][] saltComponentData = {{1,saltTypeComboBox,saltEditorComboBox,"","","","",""}};
        JTable saltComponentsJTable = new JTable(new DefaultTableModel(saltComponentData,saltComponentColumns)){

            //Prevents the user from changing cells that will be populated form the databae when a dropdown item
            //is selected or when the calculation is done.
            public boolean isCellEditable(int row, int col) {
                return col >0;
            }
        };



        saltComponentsJTable.setShowGrid(true);

        //Makes sure the jcombobox updates properly and updates if the mouse focus is on the combobox updates properly when importing or adding
        InitialJTableComboBoxSetup ICBSSalt = new InitialJTableComboBoxSetup(saltComponentsJTable,saltTypeComboBox,saltTypes,saltEditorComboBox,saltComponentItems,"Salt");

        TableSetup setupSalt = new TableSetup(MainWindow.this,saltComponentsJTable,saltComponentsJPanel,saltComponentItems,saltTypes, "Salt Table"); //sets table editable/layout values
        JScrollPane saltComponentJScrollPane = new JScrollPane(saltComponentsJTable);
        saltComponentsJPanel.setLayout(new BorderLayout());
        saltComponentsJPanel.add(saltComponentJScrollPane,BorderLayout.CENTER); //adds table to JPanel
        ExcelAdapter mySaltAdapter = new ExcelAdapter(saltComponentsJTable);//allows excel copy/paste cells


        //Deletes the selected row
        deleteRowSaltButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                removeSelectedRows(saltComponentsJTable);
            }
        });
        //adds a new row when selected
        addRowSaltButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddRow addSaltRow = new AddRow(MainWindow.this,saltComponentsJTable,saltTypeComboBox,"Salt");
            }
        });

        addSaltComponentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SaltComponentAdder sca = new SaltComponentAdder(MainWindow.this, saltComponentsJTable,saltTypeComboBox);
                sca.initialize();

            }
        });

        //imports the salt components from a user chosen database into the combobox
        saltImportDatabaseJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DatabaseComboBoxPopulator dcbp = new DatabaseComboBoxPopulator(MainWindow.this,saltComponentsJTable,saltTypeComboBox,"Salt");
            }
        });

        //Exports a CSV file with the current salt database
        exportSaltDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SaveFilePathLocation sdLoad = new SaveFilePathLocation();
                String filename = sdLoad.getSaveLocation(MainWindow.this);

                CSVDatabaseExporter cde = new CSVDatabaseExporter(MainWindow.this,filename,"Salt");
            }
        });

        //populates the salt cells when a combobox is selected
        Action saltAction = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                TableCellListener tcl = (TableCellListener)e.getSource();

                //finds the index in the arras that the chosen name is at
                int indexValue = getSaltBWOW().indexOf(tcl.getNewValue().toString());

                //used that index value to locate the proper items when a combobox item is selected and populates
                //the correct cells
                double saltAbsVolumeAction = Double.parseDouble(getSaltAbsVolume().get(indexValue).toString());
                double KCLabsVolumeAction = Double.parseDouble(getKCLAbsVolume().get(indexValue).toString());
                double saltPriceAction = Double.parseDouble(getSaltPrice().get(indexValue).toString());
                double KCLpriceAction = Double.parseDouble(getKCLPrice().get(indexValue).toString());

                DefaultTableModel tableValueSetter = (DefaultTableModel) saltComponentsJTable.getModel();
                tableValueSetter.setValueAt(saltAbsVolumeAction, tcl.getRow(), 3);
                tableValueSetter.setValueAt(KCLabsVolumeAction, tcl.getRow(), 4);
                tableValueSetter.setValueAt(saltPriceAction,tcl.getRow(),5);
                tableValueSetter.setValueAt(KCLpriceAction, tcl.getRow(), 6);

            }
        };

        //updates the other salt cells when the combobox item in that row is selected
        TableCellListener tclSalt = new TableCellListener(saltComponentsJTable,saltAction);


        String[] dryComponentItems = {"Select Dry Addative"};
        String[] dryUnitOfMeasurementValues = {"%BWOC","LBS/Sack","%BWOW"};
        String[] dryComponentColumns = {"Index","Dry Component","Concentration","UOM","Absolute Volume lbs/gal","Bulk Weight lbs/cuft","$ Cost/lb"};
        JComboBox dryEditorComboBox = new JComboBox(dryComponentItems);
        JComboBox dryUOMComboBox = new JComboBox(dryUnitOfMeasurementValues);

        //Adds the initializer items that will be updated once components or a database is inserted
        Object [][] dryComponentData = {{1,dryEditorComboBox,"",dryUOMComboBox,"","",""}};
        JTable dryComponentsJTable = new JTable(new DefaultTableModel(dryComponentData,dryComponentColumns)){

            //Prevents the user from changing cells that will be populated form the databae when a dropdown item
            //is selected or when the calculation is done.
            public boolean isCellEditable(int row, int col) {
                return col >0;
            }
        };

        dryComponentsJTable.setShowGrid(true);

        //Makes sure the jcombobox updates properly and updates if the mouse focus is on the combobox updates properly when importing or adding
        InitialJTableComboBoxSetup ICBSDry = new InitialJTableComboBoxSetup(dryComponentsJTable,dryEditorComboBox,dryComponentItems,dryUOMComboBox,dryUnitOfMeasurementValues,"Dry");

        //Initial table setup
        TableSetup setupdry = new TableSetup(MainWindow.this,dryComponentsJTable, dryComponentsJPanel,dryComponentItems,dryUnitOfMeasurementValues, "Dry Table"); //sets table editable/layout values
        JScrollPane dryComponentJScrollPane = new JScrollPane(dryComponentsJTable);
        dryComponentsJPanel.setLayout(new BorderLayout());
        dryComponentsJPanel.add(dryComponentJScrollPane,BorderLayout.CENTER); //adds table to JPanel
        ExcelAdapter myDryAdapter = new ExcelAdapter(dryComponentsJTable);//allows excel copy/paste cells


        //Deletes the selected row
        deleteRowDryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                removeSelectedRows(dryComponentsJTable);
            }
        });
        //adds a new row when selected
        addRowDryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddRow addDryRow = new AddRow(MainWindow.this,dryComponentsJTable,dryEditorComboBox,"Dry Addative");
            }
        });

        addDryComponentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DryComponentAdder dca = new DryComponentAdder(MainWindow.this, dryComponentsJTable,dryEditorComboBox);
                dca.initialize();
            }
        });

        //imports the dry components from a user chosen database into the combobox
        dryImportDatabaseJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DatabaseComboBoxPopulator dcbp = new DatabaseComboBoxPopulator(MainWindow.this,dryComponentsJTable,dryEditorComboBox,"Dry Addative");
            }
        });

        //Exports a CSV file with the current dry database
        exportDryDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SaveFilePathLocation sdLoad = new SaveFilePathLocation();
                String filename = sdLoad.getSaveLocation(MainWindow.this);

                CSVDatabaseExporter cde = new CSVDatabaseExporter(MainWindow.this,filename,"Dry");
            }
        });
        //populates the dry cells when a combobox is selected
        Action dryAction = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                TableCellListener tcl = (TableCellListener)e.getSource();

                //finds the index in the arras that the chosen name is at
                int indexValue = getDryNames().indexOf(tcl.getNewValue().toString());

                //used that index value to locate the proper items when a combobox item is selected and populates
                //the correct cells
                double dryAbsVolumeAction = Double.parseDouble(getDryAbsVolume().get(indexValue).toString());
                double dryBulkWeightAction = Double.parseDouble(getDryBulkWeight().get(indexValue).toString());
                double dryPriceAction = Double.parseDouble(getDryPrice().get(indexValue).toString());


                DefaultTableModel tableValueSetter = (DefaultTableModel) dryComponentsJTable.getModel();
                tableValueSetter.setValueAt(dryAbsVolumeAction, tcl.getRow(), 4);
                tableValueSetter.setValueAt(dryBulkWeightAction, tcl.getRow(), 5);
                tableValueSetter.setValueAt(dryPriceAction,tcl.getRow(),6);

            }
        };

        //updates the other dry cells when the combobox item in that row is selected
        TableCellListener tcldry = new TableCellListener(dryComponentsJTable,dryAction);


        String[] liquidComponentItems = {"Select Liquid"};
        String[] liquidUnitOfMeasurementValues = {"Gal/Sack","Gal/bbl"};
        String[] liquidComponentColumns = {"Index","Liquid Component","Concentration","UOM","Absolute Volume lbs/gal","Bulk Weight lbs/cuft","$ Cost/lb"};
        JComboBox liquidEditorComboBox = new JComboBox(liquidComponentItems);
        JComboBox liquidUOMComboBox = new JComboBox(liquidUnitOfMeasurementValues);

        //Adds the initializer items that will be updated once components or a database is inserted
        Object [][] liquidComponentData = {{1,liquidEditorComboBox,"",liquidUOMComboBox,"","",""}};
        JTable liquidComponentsJTable = new JTable(new DefaultTableModel(liquidComponentData,liquidComponentColumns)){

            //Prevents the user from changing cells that will be populated form the databae when a dropdown item
            //is selected or when the calculation is done.
            public boolean isCellEditable(int row, int col) {
                return col >0;
            }
        };

        liquidComponentsJTable.setShowGrid(true);

        //Makes sure the jcombobox updates properly and updates if the mouse focus is on the combobox updates properly when importing or adding
        InitialJTableComboBoxSetup ICBSLiquid = new InitialJTableComboBoxSetup(liquidComponentsJTable,liquidEditorComboBox,liquidComponentItems,liquidUOMComboBox,liquidUnitOfMeasurementValues,"Liquid");

        //Initial table setup
         TableSetup setupLiquid = new TableSetup(MainWindow.this,liquidComponentsJTable, liquidComponentsJPanel,liquidComponentItems,liquidUnitOfMeasurementValues, "Liquid Table"); //sets table editable/layout values
        JScrollPane liquidComponentJScrollPane = new JScrollPane(liquidComponentsJTable);
        liquidComponentsJPanel.setLayout(new BorderLayout());
        liquidComponentsJPanel.add(liquidComponentJScrollPane,BorderLayout.CENTER); //adds table to JPanel
        ExcelAdapter myLiquidAdapter = new ExcelAdapter(liquidComponentsJTable);//allows excel copy/paste cells


        //Deletes the selected row
        deleteRowLiquidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                removeSelectedRows(liquidComponentsJTable);
            }
        });
        //adds a new row when selected
        addRowLiquidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddRow addLiquidRow = new AddRow(MainWindow.this,liquidComponentsJTable,liquidEditorComboBox,"Liquid");
            }
        });

        addLiquidComponentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            LiquidComponentAdder lca = new LiquidComponentAdder(MainWindow.this, liquidComponentsJTable,liquidEditorComboBox);
            lca.initialize();

            }
        });

        //imports the dry components from a user chosen database into the combobox
        liquidImportDatabaseJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DatabaseComboBoxPopulator dcbp = new DatabaseComboBoxPopulator(MainWindow.this,liquidComponentsJTable,liquidEditorComboBox,"Liquid");
            }
        });

        //Exports a CSV file with the current liquids database
        exportLiquidDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SaveFilePathLocation sdLoad = new SaveFilePathLocation();
                String filename = sdLoad.getSaveLocation(MainWindow.this);

                CSVDatabaseExporter cde = new CSVDatabaseExporter(MainWindow.this,filename,"Liquid");
            }
        });
        //populates the dry cells when a combobox is selected
        Action liquidAction = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                TableCellListener tcl = (TableCellListener)e.getSource();

                //finds the index in the arras that the chosen name is at
                int indexValue = getLiquidNames().indexOf(tcl.getNewValue().toString());

                //used that index value to locate the proper items when a combobox item is selected and populates
                //the correct cells
                double liquidAbsVolumeAction = Double.parseDouble(getLiquidAbsVolume().get(indexValue).toString());
                double liquidBulkWeightAction = Double.parseDouble(getLiquidBulkWeight().get(indexValue).toString());
                double liquidPriceAction = Double.parseDouble(getLiquidPrice().get(indexValue).toString());


                DefaultTableModel tableValueSetter = (DefaultTableModel) liquidComponentsJTable.getModel();
                tableValueSetter.setValueAt(liquidAbsVolumeAction, tcl.getRow(), 4);
                tableValueSetter.setValueAt(liquidBulkWeightAction, tcl.getRow(), 5);
                tableValueSetter.setValueAt(liquidPriceAction,tcl.getRow(),6);

            }
        };

        //updates the other dry cells when the combobox item in that row is selected
        TableCellListener tclLiquid = new TableCellListener(liquidComponentsJTable,liquidAction);

    }




    //grabs the current version number to be assigned to the splash screen
    public String getVersionNumber(){

        String versionNumber = "1.0";
        return versionNumber;
    }





    //Builds the EGI splash screen
    public void buildSplashScreen(){


        URL splashURL = MainWindow.class.getResource("/Images/UCement Logo.png");
        ImageIcon splashImage = new ImageIcon(splashURL);
        //Image URL, progres bool, message bool, percent bool, version string, setlocation, textcolor, progress text color
        JSplash splash = new JSplash(splashURL, true, false, true, "Beta Version: " + getVersionNumber() ,null, Color.WHITE,Color.GREEN );

        splash.splashOn();
        splash.setProgress(25);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splash.setProgress(50);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splash.setProgress(75);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splash.setProgress(100);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splash.splashOff();


    }

    //initializer method that builds the frame
    public void initialize(){

        MainWindow.this.setContentPane(this.mainPanel);
        MainWindow.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainWindow.this.setTitle("UCement - "+getVersionNumber());
        MainWindow.this.pack();
        MainWindow.this.setLocationRelativeTo(null);
        MainWindow.this.setVisible(true);
    }


    public static void main(String[] args) {

        MainWindow mw = new MainWindow();
        mw.buildSplashScreen();

        //sets the look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (Exception e) {
        }

        //builds the GUI and all it's functionality
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new MainWindow().initialize();
            }
        });
    }
}
