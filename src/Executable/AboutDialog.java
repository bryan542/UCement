package Executable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class AboutDialog extends JDialog {
    private JPanel contentPane;
    private JButton OKButton;
    private JLabel EGIAboutIconLabel;
    private JLabel versionLabel;
    private JLabel createdLabel;

    public AboutDialog(String versionNumber) {

        setResizable(false); // removes the default java imageicon

        //Set Image Icon
        URL urlEGIAbout = MainWindow.class.getResource("/Images/PES Logo.png");
        ImageIcon bgEGIAbout = new ImageIcon(urlEGIAbout);
        ImageResize Test1 = new ImageResize();
        bgEGIAbout =Test1.getScaledImage(bgEGIAbout,450,115);
        EGIAboutIconLabel.setIcon(bgEGIAbout);
        EGIAboutIconLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        //closes the JDialog panel
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });

        //grabs the version number defined in mainWindow class
        versionLabel.setText("Version: "+versionNumber);

        //sets the created date of this verison. Need to manually update this
        String createdDate = "7.13.2017";
        createdLabel.setText("Created: "+createdDate);


    }



    public void initialize(){

        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
