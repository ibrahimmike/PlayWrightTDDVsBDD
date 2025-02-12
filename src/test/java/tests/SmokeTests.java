package tests;

import com.microsoft.playwright.Page;
import driverFactory.PageThreadLocal;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SmokeTests {
    private Page browserManager;

    @BeforeTest
    public void setUp(){
       browserManager =  PageThreadLocal.initPage();
    }



    @AfterTest
    public void takeDownTests(){
        PageThreadLocal.removePage();
    }
}
