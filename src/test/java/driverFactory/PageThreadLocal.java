package driverFactory;

import com.microsoft.playwright.Page;

import javax.swing.plaf.TableHeaderUI;
import java.util.Objects;

public class PageThreadLocal extends ThreadLocal{
//
    private static ThreadLocal<Page> pages  = new ThreadLocal<>();
    private static ThreadLocal<BrowserManager> browserManagerThreadLocal = new ThreadLocal<>();

    public static Page  initPage(){

        BrowserManager.setBrowserManager();
        pages.set(BrowserManager.getPage());
       return pages.get();
    }
    public static void removePage(){

        BrowserManager.tearDown();
       pages.remove();

    }
    public static Page getPage(){
        return pages.get();
    }





}
