package Executable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Created by Bryan on 7/17/2017.
 */
public class DatabaseComboBoxPopulator {


    public DatabaseComboBoxPopulator(MainWindow mw,  JTable table, JComboBox combobox,String componentTypeRetriever){

        //opens a file searcher tool that the user manually finds the csv file on the desktop and gets
        //the string pathname
        FilePathLocationRetriever fplr = new FilePathLocationRetriever();
        String filepath =  fplr.getLoadLocation(mw);

        //parses out the csv file using a csv reader
        CSVDatabaseReader dr = new CSVDatabaseReader();
        dr.readDatabase(filepath,mw, componentTypeRetriever);

        if(componentTypeRetriever.equalsIgnoreCase("Cement")){

            String[] nameData = new String[mw.getCementNames().size()];
            for(int i = 0; i<mw.getCementNames().size();i++){

                nameData[i] = mw.getCementNames().get(i).toString();
            }

            for(int i = 0; i<nameData.length;i++){

                boolean nameChecker = duplicateChecker(combobox,nameData[i]);

                if(nameChecker){
                    combobox.addItem(nameData[i]);

                }
            }


            for(int i = 0;i<table.getRowCount();i++){

                table.setValueAt(combobox,i,1);

            }
        }
        else if( componentTypeRetriever.equalsIgnoreCase("Salt")){

            String[] nameData = new String[mw.getSaltBWOW().size()];
            for(int i = 0; i<mw.getSaltBWOW().size();i++){

                nameData[i] = mw.getSaltBWOW().get(i).toString();
            }



            for(int i = 0; i<nameData.length;i++){

                boolean nameChecker = duplicateChecker(combobox,nameData[i]);

                if(nameChecker){
                    combobox.addItem(nameData[i]);

                }
            }
            for(int i = 0;i<table.getRowCount();i++){

                table.setValueAt(combobox,i,1);

            }

            String[] saltUnitOfMeasurementValues = {"NaCl","KCl"};
            JComboBox saltComboBox = new JComboBox(saltUnitOfMeasurementValues);
            TableColumn th = table.getColumnModel().getColumn(2);
            th.setCellEditor(new DefaultCellEditor(saltComboBox));
            DefaultTableCellRenderer renderer =
                    new DefaultTableCellRenderer();
            th.setCellRenderer(renderer);
        }
        else if( componentTypeRetriever.equalsIgnoreCase("Dry Addative")){

            String[] nameData = new String[mw.getDryNames().size()];
            for(int i = 0; i<mw.getDryNames().size();i++){

                nameData[i] = mw.getDryNames().get(i).toString();
            }

            for(int i = 0; i<nameData.length;i++){

                boolean nameChecker = duplicateChecker(combobox,nameData[i]);

                if(nameChecker){
                    combobox.addItem(nameData[i]);

                }
            }
            for(int i = 0;i<table.getRowCount();i++){

                table.setValueAt(combobox,i,1);

            }

            String[] dryUnitOfMeasurementValues = {"%BWOC","LBS/Sack","%BWOW"};
            JComboBox dryComboBox = new JComboBox(dryUnitOfMeasurementValues);
            TableColumn th = table.getColumnModel().getColumn(3);
            th.setCellEditor(new DefaultCellEditor(dryComboBox));
            DefaultTableCellRenderer renderer =
                    new DefaultTableCellRenderer();
            th.setCellRenderer(renderer);
        }

        else if( componentTypeRetriever.equalsIgnoreCase("Liquid")){

            String[] nameData = new String[mw.getLiquidNames().size()];
            for(int i = 0; i<mw.getLiquidNames().size();i++){

                nameData[i] = mw.getLiquidNames().get(i).toString();
            }

            for(int i = 0; i<nameData.length;i++){

                boolean nameChecker = duplicateChecker(combobox,nameData[i]);

                if(nameChecker){
                    combobox.addItem(nameData[i]);

                }
            }
            for(int i = 0;i<table.getRowCount();i++){

                table.setValueAt(combobox,i,1);

            }

            String[] liquidUnitOfMeasurementValues = {"Gal/Sack"};
            JComboBox liquidComboBox = new JComboBox(liquidUnitOfMeasurementValues);
            TableColumn th = table.getColumnModel().getColumn(3);
            th.setCellEditor(new DefaultCellEditor(liquidComboBox));
            DefaultTableCellRenderer renderer =
                    new DefaultTableCellRenderer();
            th.setCellRenderer(renderer);
        }


        TableColumn th = table.getColumnModel().getColumn(1);
        th.setCellEditor(new DefaultCellEditor(combobox));
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        th.setCellRenderer(renderer);

        //sets the combobox text when it dynamically updates to a value telling the user to make a selection
        for(int i = 0;i<table.getRowCount();i++){

            table.setValueAt("Select "+componentTypeRetriever,i,1);

        }

        if( componentTypeRetriever.equalsIgnoreCase("Salt")){

            for(int i = 0;i<table.getRowCount();i++){

                table.setValueAt("NaCl",i,2);

            }
        }
        else if( componentTypeRetriever.equalsIgnoreCase("Dry Addative")){

            for(int i = 0;i<table.getRowCount();i++){

                table.setValueAt("%BWOC",i,3);

            }
        }

        else if( componentTypeRetriever.equalsIgnoreCase("Liquid")){

            for(int i = 0;i<table.getRowCount();i++){

                table.setValueAt("Gal/Sack",i,3);

            }
        }
    }


    //Double checks for duplicate values imported
    boolean duplicateChecker(JComboBox originalCombobox, String name) {


        for(int i = 0; i<originalCombobox.getItemCount();i++){

            if(originalCombobox.getItemAt(i).toString().equalsIgnoreCase(name)){

                return false;
            }
            else{

            }
        }
        return true;
    }
}
