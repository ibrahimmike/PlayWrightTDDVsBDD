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
      ElementHandle footer = browserManager.waitForSelector(footerXpath);
      footer.scrollIntoViewIfNeeded();
      return footer.isVisible() ;

    }
    public boolean theFooterSocialMediaLinksAreVisible(){
//       List<Locator> footerSocialMediaLinks = browserManager.querySelectorAll(socialMediaLinks);
//        ArrayList<Integer> check = new ArrayList<>();
//       for(Locator lo : footerSocialMediaLinks){
//           if (lo.isVisible()){
//               check.add(1);
//           }else{
//               check.add(0);
//           }
//
//       }
//       return !check.contains(0);
        return browserManager.locator("//footer[@class='footer']//ul/li[text()='Twitter']").isVisible();
    }


}
