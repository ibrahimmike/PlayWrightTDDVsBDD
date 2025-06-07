package pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import driverFactory.BrowserManager;

import java.util.ArrayList;
import java.util.List;

public class BasePage {


    protected Page page;
    public BasePage(Page browserManager){
        this.page = browserManager;

    }
    public Page getBrowserManager(){
        return page;
    }

    public HeaderPage getHeader(){
        return new HeaderPage(page);
    }
    public FooterPage getFooter(){
        return new FooterPage(page);
    }


}
