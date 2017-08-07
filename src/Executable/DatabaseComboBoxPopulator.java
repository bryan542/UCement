package Executable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.util.ArrayList;

/**
 * Created by Bryan on 7/17/2017.
 */
public class DatabaseComboBoxPopulator {


    public DatabaseComboBoxPopulator(MainWindow mw,  JTable table, JComboBox combobox,String componentTypeRetriever){

        ArrayList previousComboNameSelections = new ArrayList();
        ArrayList previousComboUOMSelection = new ArrayList();

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

            //makes sure the previous selected comboboxes stay the same
            for(int i = 0;i<table.getRowCount();i++){

                if(i < 1 && combobox.getSelectedItem().toString().equalsIgnoreCase("Select Cement")){

                    previousComboNameSelections.add(combobox.getSelectedItem().toString());
                }
                else{

                    previousComboNameSelections.add(table.getValueAt(i,1).toString());
                }

            }
        }

        //set cell editor and renderer for selection item
        setEditorAndRenderer(table,combobox,1);

        //sets the combobox text when it dynamically updates to a value telling the user to make a selection
        for(int i = 0;i<table.getRowCount();i++){

            table.setValueAt(previousComboNameSelections.get(i).toString(),i,1);

        }
    }

    public DatabaseComboBoxPopulator(MainWindow mw,  JTable table, JComboBox combobox,JComboBox comboboxUOM, String componentTypeRetriever){

        ArrayList previousComboNameSelections = new ArrayList();
        ArrayList previousComboUOMSelection = new ArrayList();

        //opens a file searcher tool that the user manually finds the csv file on the desktop and gets
        //the string pathname
        FilePathLocationRetriever fplr = new FilePathLocationRetriever();
        String filepath =  fplr.getLoadLocation(mw);

        //parses out the csv file using a csv reader
        CSVDatabaseReader dr = new CSVDatabaseReader();
        dr.readDatabase(filepath,mw, componentTypeRetriever);


        if( componentTypeRetriever.equalsIgnoreCase("Salt")){

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

            //makes sure the previous selected comboboxes stay the same
            for(int i = 0;i<table.getRowCount();i++){

                if(i < 1 && combobox.getSelectedItem().toString().equalsIgnoreCase("Select Salt")){

                    previousComboNameSelections.add(combobox.getSelectedItem().toString());
                    previousComboUOMSelection.add(comboboxUOM.getSelectedItem().toString());
                }
                else{

                    previousComboNameSelections.add(table.getValueAt(i,1).toString());
                    previousComboUOMSelection.add(table.getValueAt(i,2).toString());
                }
            }


            String[] saltUnitOfMeasurementValues = {"NaCl","KCl"};
            JComboBox saltComboBox = new JComboBox(saltUnitOfMeasurementValues);
            //set cell editor and renderer for salt UOM
            setEditorAndRenderer(table,saltComboBox,2);


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

            //makes sure the previous selected comboboxes stay the same
            for(int i = 0;i<table.getRowCount();i++){

                if(i < 1 && combobox.getSelectedItem().toString().equalsIgnoreCase("Select Dry Addative")){

                    previousComboNameSelections.add(combobox.getSelectedItem().toString());
                    previousComboUOMSelection.add(comboboxUOM.getSelectedItem().toString());
                }
                else{

                    previousComboNameSelections.add(table.getValueAt(i,1).toString());
                    previousComboUOMSelection.add(table.getValueAt(i,3).toString());

                }
            }


            String[] dryUnitOfMeasurementValues = {"%BWOC","LBS/Sack","%BWOW"};
            JComboBox dryComboBox = new JComboBox(dryUnitOfMeasurementValues);
            //set cell editor and renderer for salt UOM
            setEditorAndRenderer(table,dryComboBox,3);

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

            //makes sure the previous selected comboboxes stay the same
            for(int i = 0;i<table.getRowCount();i++){

                if(i < 1 && combobox.getSelectedItem().toString().equalsIgnoreCase("Select Liquid")){

                    previousComboNameSelections.add(combobox.getSelectedItem().toString());
                    previousComboUOMSelection.add(comboboxUOM.getSelectedItem().toString());
                }
                else{

                    previousComboNameSelections.add(table.getValueAt(i,1).toString());
                    previousComboUOMSelection.add(table.getValueAt(i,3).toString());
                }
            }

            String[] liquidUnitOfMeasurementValues = {"Gal/Sack"};
            JComboBox liquidComboBox = new JComboBox(liquidUnitOfMeasurementValues);
            //set cell editor and renderer for salt UOM
            setEditorAndRenderer(table,liquidComboBox,3);
        }


        TableColumn th = table.getColumnModel().getColumn(1);
        //set cell editor and renderer for selection item
        setEditorAndRenderer(table,combobox,1);

        //sets the combobox text when it dynamically updates to a value telling the user to make a selection
        for(int i = 0;i<table.getRowCount();i++){

            table.setValueAt(previousComboNameSelections.get(i).toString(),i,1);

        }

        if( componentTypeRetriever.equalsIgnoreCase("Salt")){

            for(int i = 0;i<table.getRowCount();i++){

                table.setValueAt(previousComboUOMSelection.get(i),i,2);

            }
        }
        else if( componentTypeRetriever.equalsIgnoreCase("Dry Addative")){

            for(int i = 0;i<table.getRowCount();i++){

                table.setValueAt(previousComboUOMSelection.get(i),i,3);

            }
        }

        else if( componentTypeRetriever.equalsIgnoreCase("Liquid")){

            for(int i = 0;i<table.getRowCount();i++){

                table.setValueAt(previousComboUOMSelection.get(i),i,3);

            }
        }
    }

    //sets teh celleditor and cellrenderer method
    public void setEditorAndRenderer(JTable table, JComboBox comboBox, int collumn){

        TableColumn th = table.getColumnModel().getColumn(collumn);
        th.setCellEditor(new DefaultCellEditor(comboBox));
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        th.setCellRenderer(renderer);


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
