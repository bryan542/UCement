package Executable;

//Makes sure the fields have the correct data types in them before the calculation class is called

import javax.swing.*;
import java.util.ArrayList;
public class TableFieldChecker {


    //method used to check if the input values are correct in the given textfieldsa
    public boolean runInputCheck(JTextField cementDensity, JTextField waterDensity,JTable cementTable, JTable dryTable, JTable liquidTable) {


        //return true of all conditions are satisfied in runInputChecker
        boolean checkResult = false;
        double cementRowSums = 0;

        try {

            if (Double.parseDouble(cementDensity.getText()) >= 0) {

            } else {

                JOptionPane.showMessageDialog(new JDialog(), "Cement Density: Enter non-negative value.");
                return checkResult = true;

            }


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(new JDialog(), "Cement Density: :Enter a valid number value.");
            return checkResult = true;

        }
        try {

            if (Double.parseDouble(waterDensity.getText()) >= 0) {

            } else {

                JOptionPane.showMessageDialog(new JDialog(), "Water Density: Enter non-negative value.");
                return checkResult = true;

            }


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(new JDialog(), "Water Density: :Enter a valid number value.");
            return checkResult = true;

        }

        //checks to see if the cement component sum is 100% and a valid number is entered
        for (int i = 0; i < cementTable.getRowCount(); i++) {

            try{

                cementRowSums = cementRowSums+Double.parseDouble(cementTable.getValueAt(i,2).toString());
            }
            catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(new JDialog(), cementTable.getValueAt(i,1).toString() + ":Enter a valid number value.");
                checkResult = true;
                break;
            }

        }

        if((int) cementRowSums != 100){

            JOptionPane.showMessageDialog(new JDialog(), "Cement Table: Cement Sums must equal 100%");
            return checkResult = true;

        }


        // Dry table valid number value checker
        for (int i = 0; i < dryTable.getRowCount(); i++) {

            try {

                if (Double.parseDouble(dryTable.getValueAt(i,2).toString()) >= 0) {

                } else {

                    JOptionPane.showMessageDialog(new JDialog(), dryTable.getValueAt(i,1) + ": Enter non-negative value.");
                    checkResult = true;
                    break;
                }


            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(new JDialog(), dryTable.getValueAt(i,1) + ":Enter a valid number value.");
                checkResult = true;
                break;
            }


        }

        //Liquid table valid number value checker
        for (int i = 0; i < liquidTable.getRowCount(); i++) {

            try {

                if (Double.parseDouble(liquidTable.getValueAt(i,2).toString()) >= 0) {

                } else {

                    JOptionPane.showMessageDialog(new JDialog(), liquidTable.getValueAt(i,1) + ": Enter non-negative value.");
                    checkResult = true;
                    break;
                }


            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(new JDialog(), liquidTable.getValueAt(i,1) + ":Enter a valid number value.");
                checkResult = true;
                break;
            }
        }

        return checkResult;
    }
}

