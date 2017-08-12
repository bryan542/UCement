package Executable;

public class TimeTest {

    public TimeTest(){

        double pumpTimeMinutes = 108.6296;
        int pumpTimeInt = (int) Math.round(pumpTimeMinutes);

        int hrs = pumpTimeInt/60;
        int min = pumpTimeInt % 60;

        System.out.println(Integer.toString(hrs)+":"+Integer.toString(min)+ "hr:min");
    }

    //estimated bottomhole static temperature
    public int BHST(MainWindow mw){


        double BHST = -1;
        BHST = 80+Double.parseDouble(mw.getTemperatureGradientJTextField().getText())*(Double.parseDouble(mw.getDepthTVDJTextField().getText()))/100;

        return (int) Math.round(BHST);
    }

    //estimated bottomhole circulating temperature
    public int BHCT(MainWindow mw){

        double BHCT = -1;

        double depthTVD = Double.parseDouble(mw.getDepthTVDJTextField().getText());
        double tempGradient = Double.parseDouble(mw.getTemperatureGradientJTextField().getText());
        if(depthTVD >= 10000){

            BHCT = 80+((.006061*depthTVD*tempGradient-10.0915)/(1-1.1502e-5*depthTVD));
        }
        else{
            BHCT = ((.00382*depthTVD*tempGradient-1.48362)/(1-3.97e-5*depthTVD));
        }

        return (int) Math.round(BHCT);
    }

    //determines initial presure bottomhole when slurry reaches it
    public int initialPressure(MainWindow mw){

        double initialPressure = -1;
        double depthTVD = Double.parseDouble(mw.getDepthTVDJTextField().getText());

        if(depthTVD >= 18000){

            initialPressure = depthTVD/1000*111.1;
        }
        else{

            initialPressure = depthTVD/1000*125;
        }
        return (int) Math.round(initialPressure);
    }

    //determines final pressure
    public int finalPressure(MainWindow mw, double initialPressure){

        double finalPressure = -1;
        double depthTVD = Double.parseDouble(mw.getDepthTVDJTextField().getText());
        double mudWeight = Double.parseDouble(mw.getMudWeightJTextField().getText());

        if(initialPressure <= 500.381){

            finalPressure = 500.381;
        }
        else{

            finalPressure = initialPressure+mudWeight*.0529*depthTVD;
        }
        return (int) Math.round(finalPressure);
    }


    public static void main(String[] args) {
        TimeTest tt = new TimeTest();
    }
}
