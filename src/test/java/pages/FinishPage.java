package pages;

import com.microsoft.playwright.Page;
import driverFactory.BrowserManager;

public class FinishPage extends BasePage{
    public FinishPage(Page browserManager) {
        super(browserManager);
    }

    public boolean finishPageIsLoaded(){
        return browserManager.getByText("THANK YOU FOR YOUR ORDER").isVisible();

    }
}
