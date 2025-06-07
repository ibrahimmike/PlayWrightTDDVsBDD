package pages;

import com.microsoft.playwright.Page;
import driverFactory.BrowserManager;

public class FinishPage extends BasePage{
    public FinishPage(Page page) {
        super(page);
    }

    public boolean finishPageIsLoaded(){
        return page.getByText("THANK YOU FOR YOUR ORDER").isVisible();

    }
}
