package Executable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * Created by bryan on 7/18/2017.
 */
public class TableDialogSetup {

    public TableDialogSetup(JPanel JPanelAdded, JTable tableToAdd){


        tableToAdd.setCellSelectionEnabled(true);
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
    public TableDialogSetup(JPanel JPanelAdded, JTable tableToAdd, String[] comboValues, String paramater){


        tableToAdd.setCellSelectionEnabled(true);
        tableToAdd.setShowGrid(true);

        for(int i = 0;i<tableToAdd.getColumnCount();i++){

            tableToAdd.getColumnModel().getColumn(i).setPreferredWidth(100);
        }

        tableToAdd.setCellSelectionEnabled(true); //Allows the user to select the cells they want. Easy for copy/past with excel
        //populates the cement column with the combobox database

        if(paramater.equalsIgnoreCase("Salt")){

            TableColumn column = tableToAdd.getColumnModel().getColumn(2);
            column.setCellEditor(new ComboBoxEditor(new JComboBox(comboValues))); //adds items to the combobox
            column.setCellRenderer(new ComboBoxRenderer(comboValues)); //finds the index
            column.setPreferredWidth(100);
        }
        else if(paramater.equalsIgnoreCase("Dry") || paramater.equalsIgnoreCase("Liquid")){

            TableColumn column = tableToAdd.getColumnModel().getColumn(3);
            column.setCellEditor(new ComboBoxEditor(new JComboBox(comboValues))); //adds items to the combobox
            column.setCellRenderer(new ComboBoxRenderer(comboValues)); //finds the index
            column.setPreferredWidth(100);
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
        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setResizable(false);
        }
    }
}
