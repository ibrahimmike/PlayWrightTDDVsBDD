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

  protected Page browserManager;
//  protected   LoginPage  loginPage;
  protected BrowserManager br;
  //protected BrowserManager bm ;

    @BeforeMethod
    public void setUP(){
////         bm =  new BrowserManager();
////         PageThreadLocal.setBm(bm.setBrowserManager());
//        PageThreadLocal.setBm();
        //BrowserManager bro = new BrowserManager();
       // PageThreadLocal.setBrowserManager();
//        PageThreadLocal.setPages();
//
//         browserManager = PageThreadLocal.getPage();
        // br = new BrowserManager();
        // PageThreadLocal.setBrowserManager();
     //    PageThreadLocal.setPage();
      //   br = PageThreadLocal.getBrowserManager();
         // browserManager = PageThreadLocal.getPage() ;

//        loginPage = new LoginPage(browserManager);
       // loginPage.navigateToLoginPage();
        //BrowserManager.setBrowserManager();
//        PageThreadLocal.setBrowserManager();
//        PageThreadLocal.setPage();
    //    br = new BrowserManager();
       // BrowserManager.setBrowserManager();




       // browserManager = br.getPage();
        browserManager = PageThreadLocal.initPage();





    }
    @AfterMethod
    public void tearDown(){

       // bm.tearDown();
       // PageThreadLocal.clearThread();
      //  br.tearDown();
        // PageThreadLocal.removePage();

       // br.tearDown();
       // BrowserManager.tearDown();
    //    br.tearDown();
        PageThreadLocal.removePage();

    }
//    @AfterTest
//    public void removeThread(){
//        PageThreadLocal.pages.remove();
//    }
}
