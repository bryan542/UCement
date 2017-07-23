package Executable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVDatabaseExporter {

    public CSVDatabaseExporter(MainWindow mw, String filename, String exportComponentType){

        try {
            FileWriter writer = new FileWriter(filename);

            //for header
            if (exportComponentType.equalsIgnoreCase("Cement")) {
                CSVUtils.writeLine(writer, Arrays.asList("Cement Type", "#/SK", "Absolute Volume", "Bulk Weight", "Price/lb"));

                for (int i = 0; i < mw.getCementNames().size(); i++) {

                    List<String> stringList = new ArrayList<>();
                    stringList.add(mw.getCementNames().get(i).toString());
                    stringList.add(mw.getCementNumberPerSack().get(i).toString());
                    stringList.add(mw.getCementAbsVolume().get(i).toString());
                    stringList.add(mw.getCementBulkWeight().get(i).toString());
                    stringList.add(mw.getCementPrice().get(i).toString());

                    CSVUtils.writeLine(writer, stringList);
                }
            }
            else if(exportComponentType.equalsIgnoreCase("Salt")) {
                CSVUtils.writeLine(writer, Arrays.asList("Salts % BWOW","Salt Abs. Volume lbs/gal","KCl Abs. Volume lbs/gal","Salt $Cost/lb","KCl $Cost/lb"));

                for (int i = 0; i < mw.getSaltBWOW().size(); i++) {

                    List<String> stringList = new ArrayList<>();
                    stringList.add(mw.getSaltBWOW().get(i).toString());
                    stringList.add(mw.getSaltAbsVolume().get(i).toString());
                    stringList.add(mw.getKCLAbsVolume().get(i).toString());
                    stringList.add(mw.getSaltPrice().get(i).toString());
                    stringList.add(mw.getKCLPrice().get(i).toString());


                    CSVUtils.writeLine(writer, stringList);
                }
            }
            else if(exportComponentType.equalsIgnoreCase("Dry")) {
                CSVUtils.writeLine(writer, Arrays.asList("Dry Component","Absolute Volume lbs/gal","Bulk Weight lbs/cuft","$ Cost/lb"));

                for (int i = 0; i < mw.getDryNames().size(); i++) {

                    List<String> stringList = new ArrayList<>();
                    stringList.add(mw.getDryNames().get(i).toString());
                    stringList.add(mw.getDryAbsVolume().get(i).toString());
                    stringList.add(mw.getDryBulkWeight().get(i).toString());
                    stringList.add(mw.getDryPrice().get(i).toString());

                    CSVUtils.writeLine(writer, stringList);
                }
            }
            else if(exportComponentType.equalsIgnoreCase("Liquid")) {
                CSVUtils.writeLine(writer, Arrays.asList("Liquid Component","Absolute Volume lbs/gal","Bulk Weight lbs/cuft","$ Cost/lb"));

                for (int i = 0; i < mw.getLiquidNames().size(); i++) {

                    List<String> stringList = new ArrayList<>();
                    stringList.add(mw.getLiquidNames().get(i).toString());
                    stringList.add(mw.getLiquidAbsVolume().get(i).toString());
                    stringList.add(mw.getLiquidBulkWeight().get(i).toString());
                    stringList.add(mw.getLiquidPrice().get(i).toString());

                    CSVUtils.writeLine(writer, stringList);
                }
            }
            writer.flush();
            writer.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
