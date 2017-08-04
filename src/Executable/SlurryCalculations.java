package Executable;

import javax.swing.*;
import java.util.ArrayList;

public class SlurryCalculations {



    public SlurryCalculations(MainWindow mw, JTable cementTable, JTable saltTable, JTable dryTable, JTable liquidTable, double waterDensity, double cementDensity){

        //ArrayLists listed as variables contain the x variable that needs to be solved. You need to find the sum of the weights and volumes
        //and solve for x. This is a built in way for solving the system of equations without importing any libraries.

        //ArrayLists without the variable tag are the true weights and volumes required including the water mixed in component

        double gallonsWaterPerSack = 0;
        double weightSlurry = 0;
        double volumeSlurry = 0;

        double weightCementTotal = 0;
        double volumeCementTotal = 0;

        double weightSaltTotalVariable = 0;
        double volumeSaltTotalVariable = 0;

        double weightDryTotalVariable = 0;
        double weightDryTotal = 0;
        double volumeDryTotalVariable = 0;
        double volumeDryTotal = 0;

        double weightLiquidTotal = 0;
        double volumeLiquidTotal = 0;

        double totalWeight = 0;
        double totalWeightVariable = 0;

        double totalVolume = 0;
        double totalVolumeVariable = 0;

        ArrayList cementComponentNames = new ArrayList();
        ArrayList cementComponentWeights = new ArrayList();
        ArrayList cementComponentVolumes = new ArrayList();

        ArrayList saltComponentNamesVariable = new ArrayList();
        ArrayList saltComponentWeightVariable = new ArrayList();
        ArrayList saltComponentVolumesVariable = new ArrayList();

        ArrayList dryComponentNamesVariable = new ArrayList();
        ArrayList dryComponentWeightVariable = new ArrayList();
        ArrayList dryComponentVolumesVariable = new ArrayList();
        ArrayList dryComponentNames = new ArrayList();
        ArrayList dryComponentWeight = new ArrayList();
        ArrayList dryComponentVolumes = new ArrayList();

        ArrayList liquidComponentNames = new ArrayList();
        ArrayList liquidComponentWeight = new ArrayList();
        ArrayList liquidComponentVolumes = new ArrayList();

        //goes through the cement table rows and performs weight and volume calculations of the components
        // also finds the total cement weight
        for(int i = 0;i<cementTable.getRowCount();i++){

            for(int j = 0;j<mw.getCementNames().size();j++){

                String componentName = cementTable.getValueAt(i,1).toString();

                if(componentName.equalsIgnoreCase(mw.getCementNames().get(j).toString())){

                    double weightCalculationHolder = Double.parseDouble(mw.getCementNumberPerSack().get(j).toString())*Double.parseDouble(cementTable.getValueAt(i,2).toString())/100;
                    double volumeCalculationHolder = weightCalculationHolder*Double.parseDouble(mw.getCementAbsVolume().get(j).toString());

                    volumeCementTotal = volumeCementTotal +volumeCalculationHolder;
                    weightCementTotal = weightCementTotal+ weightCalculationHolder;

                    cementComponentNames.add(componentName);
                    cementComponentWeights.add(weightCalculationHolder);
                    cementComponentVolumes.add(volumeCalculationHolder);

                }
            }
        }

        //goes through the salt table rows and performs weight and volume calculations of the components
        for(int i = 0;i<saltTable.getRowCount();i++){

            for(int j = 0;j<mw.getSaltBWOW().size();j++){

                String componentName = saltTable.getValueAt(i,1).toString();

                if(componentName.equalsIgnoreCase(mw.getSaltBWOW().get(j).toString())){

                    String saltUOM = saltTable.getValueAt(i,2).toString();
                    double weightCalculationHolder = 0;
                    double volumeCalculationHolder = 0;

                    if(saltUOM.equalsIgnoreCase("NaCl")) {

                        weightCalculationHolder = waterDensity * Double.parseDouble(saltTable.getValueAt(i, 1).toString()) / 100;
                        volumeCalculationHolder = weightCalculationHolder * Double.parseDouble(mw.getSaltAbsVolume().get(j).toString());

                        weightSaltTotalVariable = weightSaltTotalVariable + weightCalculationHolder;
                        volumeSaltTotalVariable = volumeSaltTotalVariable + volumeCalculationHolder;

                        saltComponentNamesVariable.add(componentName);
                        saltComponentWeightVariable.add(weightCalculationHolder);
                        saltComponentVolumesVariable.add(volumeCalculationHolder);
                    }
                    else if(saltUOM.equalsIgnoreCase("KCl")) {

                        weightCalculationHolder = waterDensity * Double.parseDouble(saltTable.getValueAt(i, 1).toString()) / 100;
                        volumeCalculationHolder = weightCalculationHolder * Double.parseDouble(mw.getKCLAbsVolume().get(j).toString());

                        weightSaltTotalVariable = weightSaltTotalVariable + weightCalculationHolder;
                        volumeSaltTotalVariable = volumeSaltTotalVariable + volumeCalculationHolder;

                        saltComponentNamesVariable.add(componentName);
                        saltComponentWeightVariable.add(weightCalculationHolder);
                        saltComponentVolumesVariable.add(volumeCalculationHolder);
                    }

                }
            }
        }

        //goes through the dry table rows and performs weight and volume calculations of the components
        for(int i = 0;i<dryTable.getRowCount();i++){

            for(int j = 0;j<mw.getDryNames().size();j++){

                String componentName = dryTable.getValueAt(i,1).toString();

                if(componentName.equalsIgnoreCase(mw.getDryNames().get(j).toString())){

                    String dryUOM = dryTable.getValueAt(i,3).toString();
                    double weightCalculationHolder = 0;
                    double volumeCalculationHolder = 0;

                    if(dryUOM.equalsIgnoreCase("%BWOW")){

                        weightCalculationHolder = waterDensity*Double.parseDouble(dryTable.getValueAt(i,2).toString())/100;
                        volumeCalculationHolder = weightCalculationHolder*Double.parseDouble(mw.getDryAbsVolume().get(j).toString());

                        weightDryTotalVariable = weightDryTotalVariable+ weightCalculationHolder;
                        volumeDryTotalVariable = volumeDryTotalVariable +volumeCalculationHolder;


                        dryComponentNamesVariable.add(componentName);
                        dryComponentWeightVariable.add(weightCalculationHolder);
                        dryComponentVolumesVariable.add(volumeCalculationHolder);
                    }
                    else if(dryUOM.equalsIgnoreCase("%BWOC")){

                        weightCalculationHolder = weightCementTotal*Double.parseDouble(dryTable.getValueAt(i,2).toString())/100;
                        volumeCalculationHolder = weightCalculationHolder*Double.parseDouble(mw.getDryAbsVolume().get(j).toString());

                        weightDryTotal = weightDryTotal+ weightCalculationHolder;
                        volumeDryTotal = volumeDryTotal +volumeCalculationHolder;

                        dryComponentNames.add(componentName);
                        dryComponentWeight.add(weightCalculationHolder);
                        dryComponentVolumes.add(volumeCalculationHolder);
                    }
                    else if(dryUOM.equalsIgnoreCase("LBS/Sack")){

                        weightCalculationHolder = Double.parseDouble(dryTable.getValueAt(i,2).toString());
                        volumeCalculationHolder = weightCalculationHolder*Double.parseDouble(mw.getDryAbsVolume().get(j).toString());


                        weightDryTotal = weightDryTotal+ weightCalculationHolder;
                        volumeDryTotal = volumeDryTotal +volumeCalculationHolder;

                        dryComponentNames.add(componentName);
                        dryComponentWeight.add(weightCalculationHolder);
                        dryComponentVolumes.add(volumeCalculationHolder);
                    }

                }
            }
        }

        //goes through the liquid table rows and performs weight and volume calculations of the components
        for(int i = 0;i<liquidTable.getRowCount();i++){

            for(int j = 0;j<mw.getLiquidNames().size();j++){

                String componentName = liquidTable.getValueAt(i,1).toString();

                if(componentName.equalsIgnoreCase(mw.getLiquidNames().get(j).toString())){

                    double liquidAbsoluteVolume = Double.parseDouble(mw.getLiquidAbsVolume().get(j).toString());
                    double liquidConcentration = Double.parseDouble(liquidTable.getValueAt(i,2).toString());

                    double weightCalculationHolder =(liquidConcentration/liquidAbsoluteVolume);
                    double volumeCalculationHolder = liquidConcentration;

                    weightLiquidTotal = weightLiquidTotal+ weightCalculationHolder;
                    volumeLiquidTotal = volumeLiquidTotal +volumeCalculationHolder;

                    liquidComponentNames.add(componentName);
                    liquidComponentWeight.add(weightCalculationHolder);
                    liquidComponentVolumes.add(volumeCalculationHolder);

                }
            }
        }

        totalWeight = weightCementTotal+weightDryTotal+weightLiquidTotal;
        totalWeightVariable = weightSaltTotalVariable+weightDryTotalVariable+waterDensity;

        totalVolume = volumeCementTotal+volumeDryTotal+volumeLiquidTotal;
        totalVolumeVariable = volumeSaltTotalVariable+volumeDryTotalVariable+1;

        gallonsWaterPerSack = linearSolver(cementDensity,totalWeight,totalWeightVariable,totalVolume,totalVolumeVariable);
        weightSlurry = totalWeight+totalWeightVariable*gallonsWaterPerSack;
        volumeSlurry = totalVolume + totalVolumeVariable*gallonsWaterPerSack;

        mw.getWaterVolumeSackJTextField().setText(Double.toString(gallonsWaterPerSack));
        mw.getSlurryWeightJTextField().setText(Double.toString(weightSlurry));
        mw.getSlurryVolumeJTextField().setText(Double.toString(volumeSlurry));

    }

    public double linearSolver(double cementDensity, double totalWeight, double totalWeightVariable, double totalVolume, double totalVolumeVariable){

        double xvalue = 0;

        double nonvariable = 0;
        double variable = 0;

        totalVolume = totalVolume*cementDensity;
        totalVolumeVariable = totalVolumeVariable*cementDensity;

        nonvariable = totalWeight - totalVolume;
        variable = totalVolumeVariable - totalWeightVariable;
        xvalue = nonvariable/variable;



        return xvalue;
    }

}
