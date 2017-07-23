package Executable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;

public class SaltComponentAdder extends JDialog {
    private JPanel contentPane;
    private JButton applyButton;
    private JButton buttonCancel;
    private JPanel mainJPanel;
    private JButton addRowButton;



    public SaltComponentAdder(MainWindow mw, JTable mainTable, JComboBox mainComboBox) {

        //JTable column names
        String[] saltAdderComponentColumns = {"Index","Salts % BWOW","Salt Component","Salt Abs. Volume lbs/gal","KCl Abs. Volume lbs/gal","Salt $Cost/lb","KCl $Cost/lb"};
        String[] saltAdderComponentItems = {"NaCl","KCl"};
        JComboBox saltAdderEditorComboBox = new JComboBox(saltAdderComponentItems);
        Object [][] saltAdderComponentData = {{1,"",saltAdderEditorComboBox,"","","","",""}};

        JTable saltAdderComponentsJTable = new JTable(new DefaultTableModel(saltAdderComponentData,saltAdderComponentColumns)){

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
        TableDialogSetup tds = new TableDialogSetup(mainJPanel, saltAdderComponentsJTable, saltAdderComponentItems,"Salt");

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Allows the program to access the last cell in the last column/row when focused then pressing apply
                if(saltAdderComponentsJTable.getCellEditor() != null){
                    saltAdderComponentsJTable.getCellEditor().stopCellEditing();
                }
                for (int i = 0;i<saltAdderComponentsJTable.getRowCount();i++){



                    mainComboBox.addItem(saltAdderComponentsJTable.getValueAt(i,1));
                    mw.getSaltBWOW().add(saltAdderComponentsJTable.getValueAt(i,1).toString());
                    mw.getSaltAbsVolume().add(saltAdderComponentsJTable.getValueAt(i,3).toString());
                    mw.getKCLAbsVolume().add(saltAdderComponentsJTable.getValueAt(i,4).toString());
                    mw.getSaltPrice().add(saltAdderComponentsJTable.getValueAt(i,5).toString());
                    mw.getKCLPrice().add(saltAdderComponentsJTable.getValueAt(i,6).toString());


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

                    mainTable.setValueAt("Select Salt",i,1);

                }


                onOK();
            }
        });
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddNewComponentRow ancr = new AddNewComponentRow(mw,saltAdderComponentsJTable);
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
