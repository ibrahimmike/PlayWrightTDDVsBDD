package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import driverFactory.BrowserManager;
import driverFactory.PageThreadLocal;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import javax.sql.rowset.BaseRowSet;

public class BaseTest {

  protected Page page;



    @BeforeMethod
    public void setUP(){

        page = PageThreadLocal.initPage();





    }
    @AfterMethod
    public void tearDown(){


        PageThreadLocal.removePage();

    }

}
