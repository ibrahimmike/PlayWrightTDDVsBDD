package driverFactory;

import com.microsoft.playwright.Page;

import javax.swing.plaf.TableHeaderUI;
import java.util.Objects;

public class PageThreadLocal extends ThreadLocal{
//
    private static ThreadLocal<Page> pages  = new ThreadLocal<>();
    private static ThreadLocal<BrowserManager> browserManagerThreadLocal = new ThreadLocal<>();
  //  private static BrowserManager br;

//    private static void setBrowserManagerThreadLocal(){
//      //  BrowserManager  br = new BrowserManager();
//        browserManagerThreadLocal.set(BrowserManager.setBrowser());
//    }
  //  private static BrowserManager getBrowserManager(){
//      return   browserManagerThreadLocal.get();
//    }
  //  private static void removeBrowserManager(){
//        browserManagerThreadLocal.remove();
//    }

   // private static void setPage(){
//        pages.set(getBrowserManager().getPage());
//    }
    public static Page  initPage(){
       // if (Objects.isNull(browserManagerThreadLocal.get())){
      //      setBrowserManagerThreadLocal();

//        if (Objects.isNull(pages.get())){
//            setPage();
        BrowserManager.setBrowserManager();
        pages.set(BrowserManager.getPage());





       return pages.get();
    }
    public static void removePage(){
        //getBrowserManager().tearDown();
        //getPage().close();
      //  removeBrowserManager();

    //    pages.remove();
        BrowserManager.tearDown();
        pages.remove();

    }
    public static Page getPage(){
        return pages.get();
    }





}
