package Executable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

public class CasingSizeJDialog extends JDialog {
    private JPanel contentPane;
    private JButton applyButton;
    private JButton buttonCancel;
    private JButton addRowButton;
    private JPanel mainJPanel;

    public CasingSizeJDialog(MainWindow mw) {
        //JTable column names
        String[] casingComponentColumns = {"Index","OD (in.)","ID (in.)","Weight (lb/ft)"};

        Object [][] casingComponentData = {{1,"4","3.732","5.65"}};

        String[] casingOD = {"4","4","4","4","4","4","4.5","4.5","4.5","4.5","4.5","4.75","5","5","5","5","5",
                "5.5","5.5","5.5","5.5","5.5","5.5","5.5","6","6","6","6","6","6","6","6","6","6","6","6.625",
                "6.625","6.625","6.625","6.625","6.625","6.625","6.625","7","7","7","7","7","7","7","7","7","7","7","7","7",
                "7.625","7.625","7.625","7.625","7.625","7.625","8","8.125","8.125","8.125","8.125","8.625","8.625","8.625",
                "8.625","8.625","8.625","8.625","8.625","8.625","9","9","9","9","9.625","9.625","9.625","9.625","9.625","9.625",
                "9.625","9.625","10","10.75","10.75","10.75","10.75","10.75","10.75","10.75","10.75","10.75","11.75",
                "11.75","11.75","11.75","11.75","12","13","13","13","13","13.375","13.375","13.375","13.375","13.375","13.375",
                "14","16","16","16","16","16","18","18","18","18.625","18.625","18.625","20","20","21.5","21.5","21.5",
                "22","22","22","24","24","24.5","24.5","24.5"
        };
        String[] casingID = {"3.550","3.548","3.476","3.480","3.428","3.364","4.090","4.052","4.000","3.920","3.826",
                "4.082","4.560","4.494","4.408","4.276","4.154","5.044","5.012","4.974","4.950","4.892","4.778","4.670","5.290",
                "5.190","5.090","4.990","5.524","5.500","5.450","5.424","5.352","5.240","5.132","6.135","6.049","5.989","5.921",
                "5.855","5.791","5.761","5.675","6.538","6.456","6.398","6.336","6.276","6.214","6.184","6.154","6.094","6.040",
                "6.004","5.920","5.836","7.125","7.025","6.969","6.875","6.765","6.625","7.386","7.485","7.385","7.285","7.185",
                "8.097","8.017","7.921","7.825","7.775","7.725","7.651","7.625","7.511","8.290","8.196","8.150","8.032","9.063",
                "9.001","8.921","8.877","8.835","8.755","8.681","8.535","9.384","10.192","10.136","10.050","9.950","9.850","9.784",
                "9.760","9.660","9.560","11.150","11.084","11.000","10.880","10.772","11.384","12.438","12.360","12.282","12.220",
                "12.715","12.615","12.515","12.415","12.347","12.175","13.344","15.376","15.250","15.198","15.124","15.010","17.194",
                "17.088","16.986","17.855","17.755","17.655","19.166","19.124","20.710","20.610","20.510","21.222","21.128","21.028","23.226","23.124","23.850","23.750","23.650"};

        String[] casingWeight = {"9.26","9.50","10.46","11.00","11.60","12.60","9.50","10.50","11.60","13.50","15.10","16.00","11.50","13.00",
                "15.00","18.00","21.00","13.00","14.00","15","15.5","17","20","23","14","17","19.5","22.5","15","16","17","18","20","23","26","17",
                "20","22","24","26","28","29","32","17","20","22","24","26","28","29","30","32","34","35","38","40","20","24","26.4","29.7","33.7","39",
                "26","28","32","35.5","39.5","24","28","32","36","38","40","43","44","49","34","38","40","45","29.3","32.3","36","38","40","43.5","47",
                "53.5","33","32.75","35.75","40.5","45.5","51","54","55.5","60.7","65.7","38","42","47","54","60","40","40","45","50","54","48","54.5",
                "61","68","72","83","50","55","65","70","75","84","78","87.5","96.5","78","87.5","96.5","90","94","92.5","103","114","92.5","103","114","100.5","113","88","100.5","113"};


        JTable casingSizeComponentsJTable = new JTable(new DefaultTableModel(casingComponentData,casingComponentColumns)){

            //Selects the whole cell when the user edits and allows for overwriting
            @Override // Always selectAll()
            public boolean editCellAt(int row, int column, EventObject e) {
                boolean result = super.editCellAt(row, column, e);
                final Component editor = getEditorComponent();
                if (editor == null || !(editor instanceof JTextComponent)) {
                    return result;
                }
                if (e instanceof MouseEvent) {
                    EventQueue.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            ((JTextComponent) editor).selectAll();
                        }
                    });
                } else {
                    ((JTextComponent) editor).selectAll();
                }
                return result;
            }

            //Prevents the user from changing cells that will be populated form the databae when a dropdown item
            //is selected or when the calculation is done.
            public boolean isCellEditable(int row, int col) {
                return col >0;
            }
        };

        tableSetup(mainJPanel,casingSizeComponentsJTable);
        populateTable(casingSizeComponentsJTable,casingID,casingOD,casingWeight);

        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               addNewComponentRow(casingSizeComponentsJTable);
            }
        });

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                //Allows the program to access the last cell in the last column/row when focused then pressing apply
                if (casingSizeComponentsJTable.getCellEditor() != null) {
                    casingSizeComponentsJTable.getCellEditor().stopCellEditing();
                }

                int rowIndex = casingSizeComponentsJTable.getSelectedRow();

                if (rowIndex == -1) {

                    JOptionPane.showMessageDialog(new JDialog(), "Select a Row.");
                } else {


                    mw.getCasingODJTextField().setText(casingSizeComponentsJTable.getValueAt(rowIndex, 1).toString());
                    mw.getCasingIDJTextField().setText(casingSizeComponentsJTable.getValueAt(rowIndex, 2).toString());
                    mw.getCasingWeightJTextField().setText(casingSizeComponentsJTable.getValueAt(rowIndex, 3).toString());

                    onOK();
                 }
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }




    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void tableSetup(JPanel JPanelAdded, JTable tableToAdd){



        tableToAdd.setShowGrid(true);

        for(int i = 0;i<tableToAdd.getColumnCount();i++){

            tableToAdd.getColumnModel().getColumn(i).setPreferredWidth(100);
        }


        tableToAdd.getTableHeader().setReorderingAllowed(false); //prevents reordering
        columnSettings(tableToAdd); //prevents user resizing
        JScrollPane componentJScrollPane = new JScrollPane(tableToAdd);
        JPanelAdded.setLayout(new BorderLayout());
        JPanelAdded.add(componentJScrollPane,BorderLayout.CENTER); //adds table to jpanel
        ExcelAdapter myCementAdapter = new ExcelAdapter(tableToAdd); //allows excel copy/paste cells
    }

    //Edits the resizable options of the tables
    private void columnSettings(JTable table){

        TableColumn column = null;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setResizable(false);
        }
    }

    private void populateTable(JTable table, String[] casingID, String[] casingOD, String[] casingWeight){

        for(int i = 0;i<casingID.length;i++){

            //builds the object data
            Object [] columnValueSet = new Object[table.getColumnCount()];

            columnValueSet[0] = Integer.toString(table.getRowCount()+1); //Sets the right index value
            columnValueSet[1] = casingOD[i];
            columnValueSet[2] = casingID[i];
            columnValueSet[3] = casingWeight[i];


            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(columnValueSet);


        }

    }

    private void addNewComponentRow(JTable table){


        //builds the object data
        Object [] columnValueSet = new Object[table.getColumnCount()];

        columnValueSet[0] = Integer.toString(table.getRowCount()+1); //Sets the right index value


        //rest of the values are filled as zeros until combobox item is chosen
        for(int i = 1;i<table.getColumnCount();i++){

            columnValueSet[i] = "0";
        }



        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(columnValueSet);
        table.setValueAt("0",table.getRowCount()-1,1); //Sets the default select row prompt
    }
    public void initialize(){

        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
