package Executable;

import javax.swing.*;
import java.util.ArrayList;

public class SlurryCalculations {



    //yield lb/sk total dry cement and component calculation
    public double totalDryWeightYield(JTable cementTable,JTable dryComponentTable, MainWindow mw){

        double totalDryWeightYield = 0;

        double cementSum = 0;
        double dryAddativeSum = 0;
        int cementValueIndex = -1;


        //finds the array index to retrieve the cement component values and adds to the arraylist
        for(int i = 0;i<cementTable.getRowCount();i++){

            for(int j = 0;j<mw.getCementNames().size();j++){

                String componentName = cementTable.getValueAt(i,1).toString();

                if(componentName.equalsIgnoreCase(mw.getCementNames().get(j).toString())){

                    cementValueIndex = j;
                    cementSum = cementSum +Double.parseDouble(mw.getCementNumberPerSack().get(cementValueIndex).toString())*Double.parseDouble(cementTable.getValueAt(i,2).toString())/100;

                }
            }
        }

        //Find lbs/sack sum yield of dry components
        for(int i = 0;i<dryComponentTable.getRowCount();i++){


            if(dryComponentTable.getValueAt(i,3).toString().equalsIgnoreCase("LBS/Sack")){

                dryAddativeSum = dryAddativeSum + (Double.parseDouble(dryComponentTable.getValueAt(i,2).toString()));
            }
            else if(dryComponentTable.getValueAt(i,3).toString().equalsIgnoreCase("%BWOC")){

                dryAddativeSum = dryAddativeSum + cementSum*(Double.parseDouble(dryComponentTable.getValueAt(i,2).toString())/100);
            }



        }


        totalDryWeightYield = cementSum+dryAddativeSum;
        System.out.println(totalDryWeightYield);
        return totalDryWeightYield;
    }

    //yield lb/sk total liquid addatives calculation
    public double totalLiquidWeightYield(double[] liquidComponentWeightYields){

        double totalWeightYield = 0;

        //summation of the cement component yields
        for (int i = 0;i<liquidComponentWeightYields.length;i++){

            totalWeightYield = totalWeightYield+liquidComponentWeightYields[i];
        }

        return totalWeightYield;
    }

    //Yield gallon/sack total of the dry cement and dry component volumes
    public double totalGallonsDryYield(double[] cementGallonYields, double[] dryComponentGallonYields){

        double totalGallonYield = 0;

        //summation of the cement component yields
        for (int i = 0;i<cementGallonYields.length;i++){

            totalGallonYield = totalGallonYield+cementGallonYields[i];
        }

        //summation of dry component addative yields
        for (int i = 0;i<dryComponentGallonYields.length;i++){

            totalGallonYield = totalGallonYield+dryComponentGallonYields[i];
        }

        return totalGallonYield;
    }

    //yield lb/sk total liquid addatives calculation
    public double totalGallonsLiquidYield(double[] liquidComponentGallonYields){

        double totalGallonYield = 0;

        //summation of the cement component yields
        for (int i = 0;i<liquidComponentGallonYields.length;i++){

            totalGallonYield = totalGallonYield+liquidComponentGallonYields[i];
        }

        return totalGallonYield;
    }

    //Total dry weight yield in ft3/sack



}
