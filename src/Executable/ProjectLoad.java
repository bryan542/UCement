package Executable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.util.ArrayList;

public class ProjectLoad {


    public ProjectLoad(MainWindow mw,  JTable cementTable, JTable saltTable, JTable dryTable, JTable liquidTable,
                       JComboBox cementCombobox, JComboBox saltCombobox, JComboBox dryCombobox, JComboBox liquidCombobox,
                       JComboBox saltUOMCombobox, JComboBox dryUOMCombobox, JComboBox liquidUOMCombobox){

        ArrayList previousCementComboNameSelections = new ArrayList();


        ArrayList previousSaltComboNameSelections = new ArrayList();
        ArrayList previousSaltComboUOMSelection = new ArrayList();

        ArrayList previousDryComboNameSelections = new ArrayList();
        ArrayList previousDryComboUOMSelection = new ArrayList();

        ArrayList previousLiquidComboNameSelections = new ArrayList();
        ArrayList previousLiquidComboUOMSelection = new ArrayList();

        //opens a file searcher tool that the user manually finds the csv file on the desktop and gets
        //the string pathname
        FilePathLocationRetriever fplr = new FilePathLocationRetriever();
        String filepath =  fplr.getLoadLocation(mw);

        //parses out the csv file using a csv reader
        ProjectLoadReader dr = new ProjectLoadReader();
        dr.ProjectLoadReader(filepath,mw);

        String[] nameData = new String[mw.getCementNames().size()];
        for(int i = 0; i<mw.getCementNames().size();i++){

            nameData[i] = mw.getCementNames().get(i).toString();
        }

        for(int i = 0; i<nameData.length;i++){

            boolean nameChecker = duplicateChecker(cementCombobox,nameData[i]);

            if(nameChecker){
                cementCombobox.addItem(nameData[i]);

            }
        }

        //makes sure the previous selected comboboxes stay the same
        for(int i = 0;i<cementTable.getRowCount();i++){

            if(cementTable.getRowCount() < 2 && cementCombobox.getSelectedItem().toString().equalsIgnoreCase("Select Cement")){

                previousCementComboNameSelections.add(cementCombobox.getSelectedItem().toString());
            }
            else{

                previousCementComboNameSelections.add(cementTable.getValueAt(i,1).toString());
            }

        }

        //set cell editor and renderer for selection item
        setEditorAndRenderer(cementTable,cementCombobox,1);

        //sets the combobox text when it dynamically updates to a value telling the user to make a selection
        for(int i = 0;i<cementTable.getRowCount();i++){

            cementTable.setValueAt(previousCementComboNameSelections.get(i).toString(),i,1);

        }

        nameData = new String[mw.getSaltBWOW().size()];
        for(int i = 0; i<mw.getSaltBWOW().size();i++){

            nameData[i] = mw.getSaltBWOW().get(i).toString();
        }


        for(int i = 0; i<nameData.length;i++){

            boolean nameChecker = duplicateChecker(saltCombobox,nameData[i]);

            if(nameChecker){
                saltCombobox.addItem(nameData[i]);

            }
        }

        //makes sure the previous selected comboboxes stay the same
        for(int i = 0;i<saltTable.getRowCount();i++){

            if(saltTable.getRowCount() < 2 && saltCombobox.getSelectedItem().toString().equalsIgnoreCase("Select Salt")){

                previousSaltComboNameSelections.add(saltCombobox.getSelectedItem().toString());
                previousSaltComboUOMSelection.add(saltUOMCombobox.getSelectedItem().toString());
            }
            else{

                previousSaltComboNameSelections.add(saltTable.getValueAt(i,1).toString());
                previousSaltComboUOMSelection.add(saltTable.getValueAt(i,2).toString());
            }
        }


        String[] saltUnitOfMeasurementValues = {"NaCl","KCl"};
        JComboBox newSaltComboBox = new JComboBox(saltUnitOfMeasurementValues);
        //set cell editor and renderer for salt UOM
        setEditorAndRenderer(saltTable,newSaltComboBox,2);


        nameData = new String[mw.getDryNames().size()];
        for(int i = 0; i<mw.getDryNames().size();i++){

            nameData[i] = mw.getDryNames().get(i).toString();
        }

        for(int i = 0; i<nameData.length;i++){

            boolean nameChecker = duplicateChecker(dryCombobox,nameData[i]);

            if(nameChecker){
                dryCombobox.addItem(nameData[i]);

            }
        }

        //makes sure the previous selected comboboxes stay the same
        for(int i = 0;i<dryTable.getRowCount();i++){

            if(dryTable.getRowCount() < 2 && dryCombobox.getSelectedItem().toString().equalsIgnoreCase("Select Dry Addative")){

                previousDryComboNameSelections.add(dryCombobox.getSelectedItem().toString());
                previousDryComboUOMSelection.add(dryUOMCombobox.getSelectedItem().toString());
            }
            else{

                previousDryComboNameSelections.add(dryTable.getValueAt(i,1).toString());
                previousDryComboUOMSelection.add(dryTable.getValueAt(i,3).toString());

            }
        }


        String[] dryUnitOfMeasurementValues = {"%BWOC","LBS/Sack","%BWOW"};
        JComboBox newDryComboBox = new JComboBox(dryUnitOfMeasurementValues);
        //set cell editor and renderer for salt UOM
        setEditorAndRenderer(dryTable,newDryComboBox,3);



        nameData = new String[mw.getLiquidNames().size()];
        for(int i = 0; i<mw.getLiquidNames().size();i++){

            nameData[i] = mw.getLiquidNames().get(i).toString();
        }

        for(int i = 0; i<nameData.length;i++){

            boolean nameChecker = duplicateChecker(liquidCombobox,nameData[i]);

            if(nameChecker){
                liquidCombobox.addItem(nameData[i]);

            }
        }

        //makes sure the previous selected comboboxes stay the same
        for(int i = 0;i<liquidTable.getRowCount();i++){

            if(liquidTable.getRowCount() < 2 && liquidCombobox.getSelectedItem().toString().equalsIgnoreCase("Select Liquid")){

                previousLiquidComboNameSelections.add(liquidCombobox.getSelectedItem().toString());
                previousLiquidComboUOMSelection.add(liquidUOMCombobox.getSelectedItem().toString());
            }
            else{

                previousLiquidComboNameSelections.add(liquidTable.getValueAt(i,1).toString());
                previousLiquidComboUOMSelection.add(liquidTable.getValueAt(i,3).toString());
            }
        }

        String[] liquidUnitOfMeasurementValues = {"Gal/Sack"};
        JComboBox newLiquidComboBox = new JComboBox(liquidUnitOfMeasurementValues);
        //set cell editor and renderer for salt UOM
        setEditorAndRenderer(liquidTable,newLiquidComboBox,3);




        //set cell editor and renderer for selection item
        setEditorAndRenderer(saltTable,saltCombobox,1);


        //set cell editor and renderer for selection item
        setEditorAndRenderer(dryTable,dryCombobox,1);


        //set cell editor and renderer for selection item
        setEditorAndRenderer(liquidTable,liquidCombobox,1);

        //sets the combobox text when it dynamically updates to a value telling the user to make a selection
        for(int i = 0;i<saltTable.getRowCount();i++){

            saltTable.setValueAt(previousSaltComboNameSelections.get(i).toString(),i,1);

        }

        for(int i = 0;i<dryTable.getRowCount();i++){

            dryTable.setValueAt(previousDryComboNameSelections.get(i).toString(),i,1);

        }

        for(int i = 0;i<liquidTable.getRowCount();i++){

            liquidTable.setValueAt(previousLiquidComboNameSelections.get(i).toString(),i,1);

        }

        for(int i = 0;i<saltTable.getRowCount();i++){

            saltTable.setValueAt(previousSaltComboUOMSelection.get(i),i,2);
        }

        for(int i = 0;i<dryTable.getRowCount();i++){

            dryTable.setValueAt(previousDryComboUOMSelection.get(i),i,3);
        }


        for(int i = 0;i<liquidTable.getRowCount();i++){

            liquidTable.setValueAt(previousLiquidComboUOMSelection.get(i),i,3);
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
