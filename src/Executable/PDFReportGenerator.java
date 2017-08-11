package Executable;

import com.qoppa.pdfWriter.DocumentInfo;
import com.qoppa.pdfWriter.PDFDocument;
import com.qoppa.pdfWriter.PDFPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.print.PageFormat;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class PDFReportGenerator {


    public PDFReportGenerator(){


        DocumentInfo di = new DocumentInfo();
        di.setAuthor("Bryan");
        di.setCreationDate(new Date());
        di.setTitle("Slurry Bid");

        PDFDocument pdfDoc = new PDFDocument();
        pdfDoc.setDocumentInfo(di);

        PDFPage page = pdfDoc.createPage(null);
        PageFormat pf = new PageFormat();
        double pageWidth = pf.getWidth();
        double pageHeight = pf.getHeight();


        URL url = MainWindow.class.getResource("/Images/ProPetroLogo.png");

        //page margins 1 inch
        int marginTop = 36;
        int marginBot = 36;
        int marginLeft = 36;
        int marginRight = 36;

        double resizeWidthRatio = .5;
        double resizeHeightRatio = 1;
        BufferedImage bufferedLogo = resizedBufferedImage(url,resizeWidthRatio,resizeHeightRatio);


        pdfDoc.addPage(page);

        //pages for testing///////////
        PDFPage page2 = pdfDoc.createPage(null);
        PDFPage page3 = pdfDoc.createPage(null);
        PDFPage page4 = pdfDoc.createPage(null);

        pdfDoc.addPage(page2);
        pdfDoc.addPage(page3);
        pdfDoc.addPage(page4);
        ///////////////

        reocurringPageItemsSetter(pdfDoc,bufferedLogo,resizeWidthRatio,resizeHeightRatio,pageWidth,pageHeight,marginBot,marginTop,marginLeft,marginRight);


        String filename = getSaveLocation();

        File outFile = new File(filename);
        if (outFile != null)
        {
            // save the document
            try {
                pdfDoc.saveDocument (outFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Has the user choose the file save location of the pdf
    public String getSaveLocation() {

        File file = null;
        String filename = null;
        JFileChooser chooser =null;

        //this portion is for setting the windows look and feel for the save pane and preventing
        //the other panels' look and feel from changing
        LookAndFeel previousLF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            chooser = new JFileChooser();
            UIManager.setLookAndFeel(previousLF);
        } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}


        //choose the start user path
        chooser.setCurrentDirectory(new java.io.File("C:/"));
        chooser.setDialogTitle("Choose Save Location");

        //sets the file extensions that you can see
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF File Save Path", "pdf");
        chooser.setFileFilter(filter);

        int result = chooser.showSaveDialog(null);

        try {
            if (result == chooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();

            } else {
                file = null;
            }

            filename = file.toString();
            if (filename.endsWith(".pdf")) {

            }
            else{

                filename = filename + ".pdf";
            }


            //Checks to see if file pathname already exists and gives override option
            File fileOverwriteCondition = new File(filename);
            if(fileOverwriteCondition.exists()){

                int a = JOptionPane.showConfirmDialog(null,"Do you want to overwrite an existing file?",null,JOptionPane.YES_NO_OPTION);

                if(a == JOptionPane.YES_OPTION){

                    return filename;

                }
                else if (a == JOptionPane.NO_OPTION){

                    getSaveLocation();
                }


            }
            else{
            }

        }
        catch(Exception er){}

        return filename;
    }

    //resizes the logo image
    public BufferedImage resizedBufferedImage(URL url, double resizeWidthRatio, double resizeHeightRatio){


        BufferedImage bufferedImage = null;
        BufferedImage bufferedResized = null;
        try {
            bufferedImage =ImageIO.read(url);
            bufferedResized = scale(bufferedImage,BufferedImage.TYPE_INT_ARGB,bufferedImage.getWidth(),bufferedImage.getHeight(),resizeWidthRatio,resizeHeightRatio);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferedResized;

    }

    //scaling tool
    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }


    //The items here are to be displayed on every page generated
    public void reocurringPageItemsSetter(PDFDocument pdfDoc, BufferedImage bufferedLogo, double resizeWidthRatio, double resizeHeightRatio, double pageWidth, double pageHeight, int marginBottom, int marginTop, int marginLeft, int marginRight){

        int pageCount = pdfDoc.getPageCount();
        Graphics2D g2dLogo = null;
        Graphics2D g2dLine = null;
        Graphics2D g2dPage = null;
        String pageNumber = null;
        BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB), null);

        for(int i = 0;i<pageCount;i++){

            //adds the company logo near the top right of the page
            g2dLogo = pdfDoc.getPage(i).createGraphics();
            g2dLogo.drawImage(bufferedLogo, op,(int) (Math.round(pageWidth)-marginRight-Math.round(bufferedLogo.getWidth()*resizeWidthRatio)),(int) Math.round(marginTop-bufferedLogo.getHeight()*resizeHeightRatio/2-8) );
            // g2dLogo.setStroke(new BasicStroke(1));
            //g2dLogo.setColor(Color.black);
            //g2dLogo.drawRect((int) (Math.round(pageWidth)-marginRight-Math.round(bufferedLogo.getWidth()*resizeWidthRatio))-1,(int) Math.round(marginTop-bufferedLogo.getHeight()*resizeHeightRatio/2-8),(int) Math.round(bufferedLogo.getWidth()*resizeWidthRatio)+2,(int) Math.round(bufferedLogo.getHeight()*resizeHeightRatio)+2);


            //draws the page number of each page in the document
            pageNumber = "Page "+Integer.toString(i+1);

            g2dLine = pdfDoc.getPage(i).createGraphics();
            g2dPage = pdfDoc.getPage(i).createGraphics();
            g2dPage.setFont(new Font("Times New Roman",Font.PLAIN,12));
            g2dPage.setColor(Color.blue);
            g2dPage.drawString(pageNumber,(int) Math.round(pageWidth/2),(int) Math.round(pageHeight-marginBottom+5));

            //draws the default lines near the bottom and top margins
            g2dLine.drawLine(marginLeft,51,(int) Math.round(pageWidth)-marginRight,51);
            g2dLine.drawLine(marginLeft,741,(int) Math.round(pageWidth)-marginRight,741);

        }

    }
    public static void main(String[] args) {


        PDFReportGenerator pdt = new PDFReportGenerator();

    }





}
