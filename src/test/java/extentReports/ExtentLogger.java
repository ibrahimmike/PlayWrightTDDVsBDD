package extentReports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.Page;
import driverFactory.PageThreadLocal;
import freemarker.core.OutputFormat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ExtentLogger {
    private static void extentReportPass() {
    }

    public static void pass(String message){

        ExtentReportManager.getReport().pass(message);
    }

    public static void fail(String message){
        byte[] buffer = PageThreadLocal.getPage().screenshot();
        ExtentReportManager.getReport().fail(message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(buffer)).build());
    }
    public static void skip(String message){
        ExtentReportManager.getReport().skip(message);
    }
    public static void log(String message){
        ExtentReportManager.getReport().info(message);

    }




}
