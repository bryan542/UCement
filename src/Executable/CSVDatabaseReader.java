package Executable;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Bryan on 7/14/2017.
 */
public class CSVDatabaseReader {


    public void readDatabase(String filepath, MainWindow mw, String retrieveName){



            String csvFile = filepath;
            File inputFile = new File(csvFile); //convert to file

            String line = "";
            String cvsSplitBy = ",";

            try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] databaseValues = line.split(cvsSplitBy);
                    int indexLength = databaseValues.length;
                    if(retrieveName.equalsIgnoreCase("Cement")) {
                        if (databaseValues[0].equalsIgnoreCase("Cement Type") || databaseValues[0].equalsIgnoreCase(" ")) {

                        } else {


                                boolean nameChecker = duplicateChecker(mw.getCementNames(),databaseValues[0]);

                                //makes sure duplicate names are not added
                                if(nameChecker){

                                    mw.getCementNames().add(databaseValues[0]);
                                    mw.getCementNumberPerSack().add(databaseValues[1]);
                                    mw.getCementAbsVolume().add(databaseValues[2]);
                                    mw.getCementBulkWeight().add(databaseValues[3]);

                                    //some of the database fields are entered as blanks and the index comes out as a length of 3
                                    //this adds a zero for those fields that should be characterized as 0
                                    if (indexLength < 5) {

                                        mw.getCementPrice().add("0");
                                    } else {

                                        mw.getCementPrice().add(databaseValues[4]);
                                    }
                                }

                        }
                    }
                    else if (retrieveName.equalsIgnoreCase("Salt")){

                        if (databaseValues[0].equalsIgnoreCase("Salt BWOW") || databaseValues[0].equalsIgnoreCase(" ")){

                        }
                        else{

                                boolean nameChecker = duplicateChecker(mw.getSaltBWOW(),databaseValues[0]);

                                //makes sure duplicate names are not added
                                if(nameChecker){

                                    mw.getSaltBWOW().add(databaseValues[0]);
                                    mw.getSaltAbsVolume().add(databaseValues[1]);
                                    mw.getKCLAbsVolume().add(databaseValues[2]);
                                    mw.getSaltPrice().add(databaseValues[3]);
                                    mw.getKCLPrice().add(databaseValues[4]);

                                }
                        }
                    }

                    else if (retrieveName.equalsIgnoreCase("Dry Addative")){

                        if (databaseValues[0].equalsIgnoreCase("Product Name") || databaseValues[0].equalsIgnoreCase(" ")){

                        }
                        else{

                                boolean nameChecker = duplicateChecker(mw.getSaltBWOW(),databaseValues[0]);

                                //makes sure duplicate names are not added
                                if(nameChecker){

                                    mw.getDryNames().add(databaseValues[0]);
                                    mw.getDryAbsVolume().add(databaseValues[1]);
                                    mw.getDryBulkWeight().add(databaseValues[2]);
                                    mw.getDryPrice().add(databaseValues[3]);

                                }

                        }
                    }
                    else if (retrieveName.equalsIgnoreCase("Liquid")){

                        if (databaseValues[0].equalsIgnoreCase("Liquid Product Name") || databaseValues[0].equalsIgnoreCase(" ")){

                        }
                        else{

                                boolean nameChecker = duplicateChecker(mw.getSaltBWOW(),databaseValues[0]);

                                //makes sure duplicate names are not added
                                if(nameChecker){

                                    mw.getLiquidNames().add(databaseValues[0]);
                                    mw.getLiquidAbsVolume().add(databaseValues[1]);
                                    mw.getLiquidBulkWeight().add(databaseValues[2]);
                                    mw.getLiquidPrice().add(databaseValues[3]);
                                }

                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

     }

    //Double checks for duplicate values imported
    boolean duplicateChecker(ArrayList namesArrayList, String name) {


        for(int i = 0; i<namesArrayList.size();i++){

            if(namesArrayList.get(i).toString().equalsIgnoreCase(name)){

                return false;
            }
            else{

            }
        }
        return true;
    }

}

