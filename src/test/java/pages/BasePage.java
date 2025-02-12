package pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import driverFactory.BrowserManager;

import java.util.ArrayList;
import java.util.List;

public class BasePage {


    protected Page browserManager;
    public BasePage(Page browserManager){
        this.browserManager = browserManager;

    }
    public Page getBrowserManager(){
        return browserManager;
    }

    public HeaderPage getHeader(){
        return new HeaderPage(browserManager);
    }
    public FooterPage getFooter(){
        return new FooterPage(browserManager);
    }


}
