package Executable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;

public class DryComponentAdder extends JDialog {
    private JPanel contentPane;
    private JButton applyButton;
    private JButton buttonCancel;
    private JPanel mainJPanel;
    private JButton addRowButton;



    public DryComponentAdder(MainWindow mw, JTable mainTable, JComboBox mainComboBox) {

        //JTable column names
        String[] dryAdderComponentColumns = {"Index","Dry Component","Concentration","UOM","Absolute Volume lbs/gal","Bulk Weight lbs/cuft","$ Cost/lb"};
        String[] dryAdderComponentItems = {"%BWOC","LBS/Sack","%BWOW"};
        JComboBox dryAdderUOMEditorComboBox = new JComboBox(dryAdderComponentItems);
        Object [][] dryAdderComponentData = {{1,"","",dryAdderUOMEditorComboBox,"","",""}};

        JTable dryAdderComponentsJTable = new JTable(new DefaultTableModel(dryAdderComponentData,dryAdderComponentColumns)){

            //Prevents the user from changing cells that will be populated form the databae when a dropdown item
            //is selected or when the calculation is done.
            public boolean isCellEditable(int row, int col) {
                if (col >0) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        //Adds the table to the jscrollpane and builds the data/cell editing options
        TableDialogSetup tds = new TableDialogSetup(mainJPanel, dryAdderComponentsJTable, dryAdderComponentItems,"Dry");

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Allows the program to access the last cell in the last column/row when focused then pressing apply
                if(dryAdderComponentsJTable.getCellEditor() != null){
                    dryAdderComponentsJTable.getCellEditor().stopCellEditing();
                }
                for (int i = 0;i<dryAdderComponentsJTable.getRowCount();i++){


                    mainComboBox.addItem(dryAdderComponentsJTable.getValueAt(i,1));
                    mw.getDryNames().add(dryAdderComponentsJTable.getValueAt(i,1));
                    mw.getDryAbsVolume().add(dryAdderComponentsJTable.getValueAt(i,4));
                    mw.getDryBulkWeight().add(dryAdderComponentsJTable.getValueAt(i,5));
                    mw.getDryPrice().add(dryAdderComponentsJTable.getValueAt(i,6));


                }

                for(int i = 0;i<mainTable.getRowCount();i++){

                    mainTable.setValueAt(mainComboBox,i,1);

                }

                mainTable.getColumnModel().getColumn(1);
                TableColumn column = mainTable.getColumnModel().getColumn(1);
                ComboBoxEditor ce = new ComboBoxEditor(mainComboBox);
                column.setCellEditor(ce); //adds items to the combobox

                //sets the combobox text when it dynamically updates to a value telling the user to make a selection
                for(int i = 0;i<mainTable.getRowCount();i++){

                    mainTable.setValueAt("Select Dry Addative",i,1);

                }
                onOK();
            }
        });
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddNewComponentRow ancr = new AddNewComponentRow(mw,dryAdderComponentsJTable);
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

    public void initialize(){

        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }



}
