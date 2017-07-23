package Executable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by Bryan on 7/17/2017.
 */
public class AddRow {

    public AddRow(MainWindow mw, JTable table, JComboBox cbe, String componentParameter){

        //builds the object data
        Object [] columnValueSet = new Object[table.getColumnCount()];

        columnValueSet[0] = Integer.toString(table.getRowCount()+1); //Sets the right index value
        columnValueSet[1] = cbe; //populates the column with the combobox database

        //rest of the values are filled as zeros until combobox item is chosen
        for(int i = 2;i<table.getColumnCount();i++){

            columnValueSet[i] = "0";
        }



        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(columnValueSet);
        table.setValueAt("Select "+componentParameter,table.getRowCount()-1,1); //Sets the default select row prompt
    }
}
