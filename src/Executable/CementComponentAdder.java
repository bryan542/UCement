package Executable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CementComponentAdder extends JDialog {
    private JPanel contentPane;
    private JButton applyButton;
    private JButton buttonCancel;
    private JPanel mainJPanel;
    private JButton addRowButton;



    public CementComponentAdder(MainWindow mw, JTable mainTable, JComboBox mainComboBox) {

        ArrayList previousComboNames = new ArrayList();

        //JTable column names
        String[] cementAdderComponentColumns = {"Index","Cement","% CU.FT.","LBS/Sack","Absolute Volume LBS/Gal","Bulk Weight lbs/cuft","$ Cost/lb"};
        Object [][] cementAdderComponentData = {{1,"","","","","",""}};
        JTable cementAdderComponentsJTable = new JTable(new DefaultTableModel(cementAdderComponentData,cementAdderComponentColumns)){

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
        TableDialogSetup tds = new TableDialogSetup(mainJPanel, cementAdderComponentsJTable);

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Allows the program to access the last cell in the last column/row when focused then pressing apply
                if(cementAdderComponentsJTable.getCellEditor() != null){
                    cementAdderComponentsJTable.getCellEditor().stopCellEditing();
                }

                //makes sure the previous selected comboboxes stay the same
                for(int i = 0;i<mainTable.getRowCount();i++){

                    if(mainTable.getRowCount() < 2 && mainComboBox.getSelectedItem().toString().equalsIgnoreCase("Select Cement")){

                        previousComboNames.add(mainComboBox.getSelectedItem().toString());

                    }
                    else{

                        previousComboNames.add(mainTable.getValueAt(i,1));

                    }
                }

                String[] updatedNames = new String[cementAdderComponentsJTable.getRowCount()];
                for (int i = 0;i<cementAdderComponentsJTable.getRowCount();i++){

                    mainComboBox.addItem(cementAdderComponentsJTable.getValueAt(i,1));
                    mw.getCementNames().add(cementAdderComponentsJTable.getValueAt(i,1));
                    mw.getCementNumberPerSack().add(cementAdderComponentsJTable.getValueAt(i,2));
                    mw.getCementAbsVolume().add(cementAdderComponentsJTable.getValueAt(i,3));
                    mw.getCementBulkWeight().add(cementAdderComponentsJTable.getValueAt(i,4));
                    mw.getCementPrice().add(cementAdderComponentsJTable.getValueAt(i,5));
                    updatedNames[i] = cementAdderComponentsJTable.getValueAt(i,1).toString();
                }

                TableColumn th = mainTable.getColumnModel().getColumn(1);
                th.setCellEditor(new DefaultCellEditor(mainComboBox));
                DefaultTableCellRenderer renderer =
                        new DefaultTableCellRenderer();
                th.setCellRenderer(renderer);

                //sets the combobox text when it dynamically updates to a value telling the user to make a selection
                for(int i = 0;i<mainTable.getRowCount();i++){

                    mainTable.setValueAt(previousComboNames.get(i),i,1);

                }


                onOK();
            }
        });
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddNewComponentRow ancr = new AddNewComponentRow(mw,cementAdderComponentsJTable);
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
