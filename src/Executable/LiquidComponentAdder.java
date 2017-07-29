package Executable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LiquidComponentAdder extends JDialog {
    private JPanel contentPane;
    private JButton applyButton;
    private JButton buttonCancel;
    private JPanel mainJPanel;
    private JButton addRowButton;



    public LiquidComponentAdder(MainWindow mw, JTable mainTable, JComboBox mainComboBox, JComboBox UOMComboBox) {

        ArrayList previousComboNames = new ArrayList();
        ArrayList previousComboUOM = new ArrayList();

        //JTable column names
        String[] liquidAdderComponentColumns = {"Index","Liquid Component","Concentration","UOM","Absolute Volume lbs/gal","Bulk Weight lbs/cuft","$ Cost/lb"};
        String[] liquidAdderComponentItems = {"Gal/Sack"};
        JComboBox liquidAdderUOMEditorComboBox = new JComboBox(liquidAdderComponentItems);
        Object [][] liquidAdderComponentData = {{1,"","",liquidAdderUOMEditorComboBox,"","",""}};

        JTable liquidAdderComponentsJTable = new JTable(new DefaultTableModel(liquidAdderComponentData,liquidAdderComponentColumns)){

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
        TableDialogSetup tds = new TableDialogSetup(mainJPanel, liquidAdderComponentsJTable, liquidAdderComponentItems,"liquid");

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Allows the program to access the last cell in the last column/row when focused then pressing apply
                if(liquidAdderComponentsJTable.getCellEditor() != null){
                    liquidAdderComponentsJTable.getCellEditor().stopCellEditing();
                }


                //makes sure the previous selected comboboxes stay the same
                for(int i = 0;i<mainTable.getRowCount();i++){

                    if(mainTable.getRowCount() < 2 && mainComboBox.getSelectedItem().toString().equalsIgnoreCase("Select Liquid")){

                        previousComboNames.add(mainComboBox.getSelectedItem().toString());
                        previousComboUOM.add(UOMComboBox.getSelectedItem().toString());
                    }
                    else{

                        previousComboNames.add(mainTable.getValueAt(i,1));
                        previousComboUOM.add(mainTable.getValueAt(i,3));
                    }
                }

                for (int i = 0;i<liquidAdderComponentsJTable.getRowCount();i++){


                    mainComboBox.addItem(liquidAdderComponentsJTable.getValueAt(i,1).toString());
                    mw.getLiquidNames().add(liquidAdderComponentsJTable.getValueAt(i,1).toString());
                    mw.getLiquidAbsVolume().add(liquidAdderComponentsJTable.getValueAt(i,4));
                    mw.getLiquidBulkWeight().add(liquidAdderComponentsJTable.getValueAt(i,5));
                    mw.getLiquidPrice().add(liquidAdderComponentsJTable.getValueAt(i,6));

                }

                TableColumn th = mainTable.getColumnModel().getColumn(1);
                th.setCellEditor(new DefaultCellEditor(mainComboBox));
                DefaultTableCellRenderer renderer =
                        new DefaultTableCellRenderer();
                th.setCellRenderer(renderer);

                String[] liquidUnitOfMeasurementValues = {"Gal/Sack"};
                JComboBox liquidComboBox = new JComboBox(liquidUnitOfMeasurementValues);
                TableColumn thUOM = mainTable.getColumnModel().getColumn(3);
                thUOM.setCellEditor(new DefaultCellEditor(liquidComboBox));
                DefaultTableCellRenderer rendererUOM =
                        new DefaultTableCellRenderer();
                thUOM.setCellRenderer(rendererUOM);



                //sets the combobox text when it dynamically updates to a value telling the user to make a selection
                for(int i = 0;i<mainTable.getRowCount();i++){

                    mainTable.setValueAt(previousComboNames.get(i),i,1);
                    mainTable.setValueAt(previousComboUOM.get(i).toString(),i,3);

                }

                onOK();
            }
        });
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddNewComponentRow ancr = new AddNewComponentRow(mw,liquidAdderComponentsJTable);
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
