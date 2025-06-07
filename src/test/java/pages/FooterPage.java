package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import driverFactory.BrowserManager;

import java.util.ArrayList;
import java.util.List;

public class FooterPage extends BasePage {

    private final String footerXpath = "//footer[@class='footer']";
    private final String socialMediaLinks = "//footer[@class='footer']//ul/li";


    public FooterPage(Page browserManager) {
        super(browserManager);
    }
    public boolean footerISLoaded(){
      ElementHandle footer = page.waitForSelector(footerXpath);
      footer.scrollIntoViewIfNeeded();
      return footer.isVisible() ;

    }
    public boolean theFooterSocialMediaLinksAreVisible(){


        footerISLoaded();
        return page.locator("//footer[@class='footer']//ul/li[text()='Twitter']").isVisible();
    }


}
