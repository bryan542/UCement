package Executable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectLoadReader {

    public void ProjectLoadReader(String filepath, MainWindow mw){



        String csvFile = filepath;
        File inputFile = new File(csvFile); //convert to file

        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] databaseValues = line.split(cvsSplitBy);
                int indexLength = databaseValues.length;

                int largestArray = 0;



                if (databaseValues[0].equalsIgnoreCase("Cement Type") || databaseValues[0].equalsIgnoreCase(" ")) {

                } else {


                    boolean nameChecker = duplicateChecker(mw.getCementNames(),databaseValues[0]);

                    //makes sure duplicate names are not added
                    if(nameChecker){

                        boolean numberChecker = true;
                        try{
                            if (Double.parseDouble(databaseValues[0]) == -1){

                                numberChecker = false;
                            }
                        }
                        catch(NumberFormatException nfe){

                            numberChecker = true;
                        }

                        if(numberChecker){

                            mw.getCementNames().add(databaseValues[0]);
                            mw.getCementNumberPerSack().add(databaseValues[1]);
                            mw.getCementAbsVolume().add(databaseValues[2]);
                            mw.getCementBulkWeight().add(databaseValues[3]);
                            mw.getCementPrice().add(databaseValues[4]);
                        }




                    }

                }

                if (databaseValues[5].equalsIgnoreCase("Salt % BWOW") || databaseValues[5].equalsIgnoreCase(" ")){

                }
                else{

                    boolean nameChecker = duplicateChecker(mw.getSaltBWOW(),databaseValues[5]);

                    //makes sure duplicate names are not added
                    if(nameChecker){

                        boolean numberChecker = true;
                        try{
                            if (Double.parseDouble(databaseValues[5]) == -1){

                                numberChecker = false;
                            }
                        }
                        catch(NumberFormatException nfe){

                            numberChecker = true;
                        }

                        if(numberChecker){


                            mw.getSaltBWOW().add(databaseValues[5]);
                            mw.getSaltAbsVolume().add(databaseValues[6]);
                            mw.getKCLAbsVolume().add(databaseValues[7]);
                            mw.getSaltPrice().add(databaseValues[8]);
                            mw.getKCLPrice().add(databaseValues[9]);
                        }

                    }
                }

                if (databaseValues[10].equalsIgnoreCase("Dry Addative") || databaseValues[10].equalsIgnoreCase(" ")){

                }
                else{

                    boolean nameChecker = duplicateChecker(mw.getSaltBWOW(),databaseValues[10]);

                    //makes sure duplicate names are not added
                    if(nameChecker){


                        boolean numberChecker = true;
                        try{
                            if (Double.parseDouble(databaseValues[10]) == -1){

                                numberChecker = false;
                            }
                        }
                        catch(NumberFormatException nfe){

                            numberChecker = true;
                        }

                        if(numberChecker){

                                mw.getDryNames().add(databaseValues[10]);
                                mw.getDryAbsVolume().add(databaseValues[11]);
                                mw.getDryBulkWeight().add(databaseValues[12]);
                                mw.getDryPrice().add(databaseValues[13]);
                        }
                    }
                }


                if (databaseValues[14].equalsIgnoreCase("Liquid Component") || databaseValues[14].equalsIgnoreCase(" ")){

                }
                else{

                    boolean nameChecker = duplicateChecker(mw.getSaltBWOW(),databaseValues[14]);

                    //makes sure duplicate names are not added
                    if(nameChecker){

                        boolean numberChecker = true;
                        try{
                            if (Double.parseDouble(databaseValues[14]) == -1){

                                numberChecker = false;
                            }
                        }
                        catch(NumberFormatException nfe){

                            numberChecker = true;
                        }

                        if(numberChecker){

                                mw.getLiquidNames().add(databaseValues[14]);
                                mw.getLiquidAbsVolume().add(databaseValues[15]);
                                mw.getLiquidBulkWeight().add(databaseValues[16]);
                                mw.getLiquidPrice().add(databaseValues[17]);
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