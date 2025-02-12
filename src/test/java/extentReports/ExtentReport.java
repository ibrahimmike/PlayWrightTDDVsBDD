package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
    private ExtentReport(){}

    private static ExtentTest test;

    private static ExtentReports extentReports;

    public static void extentInit() {
       // System.out.println(Constants.getExtentReportPath());

        extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("./build/"+"test-output"+"/"+ "extentReport.html");

        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("SauceLabsTest");
        spark.config().setReportName("First Report");
        extentReports.attachReporter(spark);

    }

    public static void createTest(String testName){
        test = extentReports.createTest(testName);
        ExtentReportManager.setTest(test);
    }

    public static void closeTest(){
        ExtentReportManager.removeTest();
        extentReports.flush();
//        try{
//          //  Desktop.getDesktop().browse(new File(Constants.getExtentReportPath()).toURI());
//        }catch(IOException e){
//            throw new RuntimeException("The file to the extent report cannot be created nor opened");
//        }

    }
}
