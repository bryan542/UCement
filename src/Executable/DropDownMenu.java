package Executable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Reader;

/**
 * Created by Bryan on 7/13/2017.
 */
public class DropDownMenu {

    public void buildMenuBar(MainWindow mw){

        //build file menu

        JMenuBar menubar = new JMenuBar();

        //build file JMenu and JMenuItems
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");
        file.add(open);
        file.add(save);
        file.add(exit);
        open.setPreferredSize(new Dimension(100,20));

        //build help menu
        JMenu help = new JMenu("Help");
        JMenuItem helpCenter = new JMenuItem("Help Center");
        JMenuItem about = new JMenuItem("About");
        help.add(about);
        help.add(helpCenter);
        about.setPreferredSize(new Dimension(100,20));

        //add menu's to menubar
        menubar.add(file);
        menubar.add(help);

        //Launches the about JDialog
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AboutDialog ad = new AboutDialog(mw.getVersionNumber());
                ad.initialize();

            }
        });

        //file->exit action
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //work on this
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                   // SaveLoadFileData sdSave = new SaveLoadFileData();
                  //  String filename = sdSave.getSaveLocation(mw);
                  //  sdSave.writeCSVFile(mw,filename);
                }
                catch (Exception ex2){

                }

            }
        });
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              //  SaveLoadFileData sdLoad = new SaveLoadFileData();
              //  String filename = sdLoad.getLoadLocation(mw);
                try {
                  //  Reader read = null;
                  //  read = sdLoad.readCSV(filename, mw);

                } catch (Exception e1) {

                }
            }
        });

        mw.setJMenuBar(menubar);
    }

}
