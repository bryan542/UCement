package Executable;

import javax.swing.*;
import javax.swing.table.TableColumn;

//Sets row selection highlights, editable columns allowed, column resizing, and populates the combobxes
public class TableSetup {

    public TableSetup(MainWindow mw, JTable table, JPanel panel, String[] comboValues, String tableName){


        table.setCellSelectionEnabled(true); //Allows the user to select the cells they want. Easy for copy/past with excel
        //populates the cement column with the combobox database
        TableColumn column = table.getColumnModel().getColumn(1);

        column.setPreferredWidth(100);

        if(tableName.equalsIgnoreCase("Cement Table")) {
            //sets the other column widths to prefered sizes
            table.getColumnModel().getColumn(0).setPreferredWidth(25);
            table.getColumnModel().getColumn(2).setPreferredWidth(25);
            table.getColumnModel().getColumn(3).setPreferredWidth(20);
            table.getColumnModel().getColumn(4).setPreferredWidth(100);
            table.getColumnModel().getColumn(5).setPreferredWidth(70);
            table.getColumnModel().getColumn(6).setPreferredWidth(10);
        }

        table.getTableHeader().setReorderingAllowed(false); //prevents reordering
        columnSettings(table); //prevents user resizing

    }

    public TableSetup(MainWindow mw, JTable table, JPanel panel, String[] comboValues,String[] comboUOM, String tableName){


        table.setCellSelectionEnabled(true); //Allows the user to select the cells they want. Easy for copy/past with excel
        //populates the cement column with the combobox database
        if(tableName.equalsIgnoreCase("Salt Table")){
            TableColumn columnNameValues = table.getColumnModel().getColumn(2);

            columnNameValues.setPreferredWidth(100);
        }
        else{

            TableColumn columnNameValues = table.getColumnModel().getColumn(1);

            columnNameValues.setPreferredWidth(100);
        }


        if(tableName.equalsIgnoreCase("Dry Table") || tableName.equalsIgnoreCase("Liquid Table")){

            //populates the UOM column with the combobox database
            TableColumn columnUOMValues = table.getColumnModel().getColumn(3);

            columnUOMValues .setPreferredWidth(50);
        }
        else if(tableName.equalsIgnoreCase("Salt Table")){

            //populates the UOM column with the combobox database
            TableColumn columnUOMValues = table.getColumnModel().getColumn(1);

            columnUOMValues .setPreferredWidth(50);
        }

        if(tableName.equalsIgnoreCase("Salt Table")) {
            //sets the other column widths to prefered sizes
            table.getColumnModel().getColumn(0).setPreferredWidth(25);
            table.getColumnModel().getColumn(3).setPreferredWidth(75);
            table.getColumnModel().getColumn(4).setPreferredWidth(75);
            table.getColumnModel().getColumn(5).setPreferredWidth(10);
            table.getColumnModel().getColumn(6).setPreferredWidth(10);
        }
        else if(tableName.equalsIgnoreCase("Dry Table")) {
            //sets the other column widths to prefered sizes
            table.getColumnModel().getColumn(0).setPreferredWidth(25);
            table.getColumnModel().getColumn(4).setPreferredWidth(75);
            table.getColumnModel().getColumn(5).setPreferredWidth(75);
            table.getColumnModel().getColumn(6).setPreferredWidth(70);
        }
        else if(tableName.equalsIgnoreCase("Liquid Table")) {
            //sets the other column widths to prefered sizes
            table.getColumnModel().getColumn(0).setPreferredWidth(25);
            table.getColumnModel().getColumn(4).setPreferredWidth(75);
            table.getColumnModel().getColumn(5).setPreferredWidth(75);
            table.getColumnModel().getColumn(6).setPreferredWidth(70);
        }
        table.getTableHeader().setReorderingAllowed(false); //prevents reordering
        columnSettings(table); //prevents user resizing

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
