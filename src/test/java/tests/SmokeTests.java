package tests;

import com.microsoft.playwright.Page;
import driverFactory.PageThreadLocal;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ReadUserDataFromCSV;

public class SmokeTests {
    private Page page;

    @BeforeTest
    public void setUp(){
       page =  PageThreadLocal.initPage();
        LoginPage loginPage = new LoginPage(page);
    }


    @Test
    public void test(){
        System.out.println(ReadUserDataFromCSV.getTestData());
    }



    @AfterTest
    public void takeDownTests(){
        PageThreadLocal.removePage();
    }
}
