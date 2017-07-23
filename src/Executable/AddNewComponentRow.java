package Executable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by Bryan on 7/17/2017.
 */
public class AddNewComponentRow {

    public AddNewComponentRow (MainWindow mw, JTable table){

        //builds the object data
        Object [] columnValueSet = new Object[table.getColumnCount()];

        columnValueSet[0] = Integer.toString(table.getRowCount()+1); //Sets the right index value


        //rest of the values are filled as zeros until combobox item is chosen
        for(int i = 1;i<table.getColumnCount();i++){

            columnValueSet[i] = "0";
        }



        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(columnValueSet);
        table.setValueAt("Enter Component",table.getRowCount()-1,1); //Sets the default select row prompt
    }
}

