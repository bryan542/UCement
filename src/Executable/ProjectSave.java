package Executable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Exports all of the databases into one large project save file
public class ProjectSave {

    public ProjectSave(MainWindow mw, String filename){

        ArrayList holder = new ArrayList();
        holder.add(mw.getCementNames());
        holder.add(mw.getSaltBWOW());
        holder.add(mw.getDryNames());
        holder.add(mw.getLiquidNames());

        int largestArray = 0;

        //figure out which of the databases has the most items in it
        //the smaller database lines will be filled with a blank space ""
        for (int i = 0; i< holder.size();i++){

            if(largestArray == 0 && i == 0){

                largestArray = ((ArrayList) holder.get(i)).size();
            }
            if(largestArray < ((ArrayList) holder.get(i)).size()){

                largestArray = ((ArrayList) holder.get(i)).size();
            }
        }

        try {
            FileWriter writer = new FileWriter(filename);

            //for header
            CSVUtils.writeLine(writer, Arrays.asList("Cement Type", "#/SK", "Absolute Volume", "Bulk Weight", "Price/lb",
            "Salt % BWOW","Salt Abs. Volume lbs/gal","KCl Abs. Volume lbs/gal","Salt $Cost/lb","KCl $Cost/lb",
            "Dry Addative","Absolute Volume lbs/gal","Bulk Weight lbs/cuft","$ Cost/lb",
            "Liquid Component","Absolute Volume lbs/gal","Bulk Weight lbs/cuft","$ Cost/lb"));

            for (int i = 0; i < largestArray; i++) {

                //Have to reinitialize the array each time
                List<String> stringList = new ArrayList<>();

                //makes sure that lines keep being added and the array is filled
                //when a project is loaded, -1 means ignore the line when reading and the database combobox section
                //is already filled
                if (i >= mw.getCementNames().size()){
                    stringList.add("-1");
                    stringList.add("-1");
                    stringList.add("-1");
                    stringList.add("-1");
                    stringList.add("-1");
                }
                else{

                    stringList.add(mw.getCementNames().get(i).toString());
                    stringList.add(mw.getCementNumberPerSack().get(i).toString());
                    stringList.add(mw.getCementAbsVolume().get(i).toString());
                    stringList.add(mw.getCementBulkWeight().get(i).toString());
                    stringList.add(mw.getCementPrice().get(i).toString());
                }

                if (i >= mw.getSaltBWOW().size()){
                    stringList.add("-1");
                    stringList.add("-1");
                    stringList.add("-1");
                    stringList.add("-1");
                    stringList.add("-1");
                }
                else{
                    stringList.add(mw.getSaltBWOW().get(i).toString());
                    stringList.add(mw.getSaltAbsVolume().get(i).toString());
                    stringList.add(mw.getKCLAbsVolume().get(i).toString());
                    stringList.add(mw.getSaltPrice().get(i).toString());
                    stringList.add(mw.getKCLPrice().get(i).toString());
                }

                if (i >= mw.getDryNames().size()){
                    stringList.add("-1");
                    stringList.add("-1");
                    stringList.add("-1");
                    stringList.add("-1");

                }
                else{

                    stringList.add(mw.getDryNames().get(i).toString());
                    stringList.add(mw.getDryAbsVolume().get(i).toString());
                    stringList.add(mw.getDryBulkWeight().get(i).toString());
                    stringList.add(mw.getDryPrice().get(i).toString());
                }
                if (i >= mw.getLiquidNames().size()){
                    stringList.add("-1");
                    stringList.add("-1");
                    stringList.add("-1");
                    stringList.add("-1");

                }
                else{

                    stringList.add(mw.getLiquidNames().get(i).toString());
                    stringList.add(mw.getLiquidAbsVolume().get(i).toString());
                    stringList.add(mw.getLiquidBulkWeight().get(i).toString());
                    stringList.add(mw.getLiquidPrice().get(i).toString());
                }

                CSVUtils.writeLine(writer, stringList);
            }

            writer.flush();
            writer.close();

            }
        catch (IOException e1) {
                 e1.printStackTrace();
        }
    }
}
