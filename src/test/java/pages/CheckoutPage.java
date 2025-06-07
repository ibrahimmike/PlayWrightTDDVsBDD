package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import driverFactory.BrowserManager;
import extentReports.ExtentLogger;
import extentReports.ExtentReport;

public class CheckoutPage extends BasePage{

    private final String errorMessageXpath = "//h3[@data-test='error']";
    private final String continueBtnXpath = "//input[@id='continue']";
    private final String headerName = "//div[@id='header_container']//div[@data-test='secondary-header']";
    public CheckoutPage(Page browserManager) {
        super(browserManager);
    }


    public CheckoutPage enterUserName(String userName){

        page.getByPlaceholder("First Name").fill(userName);
        return this;
    }
    public CheckoutPage enterLastName(String lastName){
//        if ( page.getByPlaceholder("Last Name").){
//            ExtentLogger.fail("The last text box is not enabled");
//        }
        page.getByPlaceholder("Last Name").fill(lastName);
        return this;
    }
    public CheckoutPage enterZipCode(String zipCode){
        page.getByPlaceholder("Zip/Postal Code").fill(zipCode);
        return this;
    }
    public CartPage clickOnCancelBtn(){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel")).click();
        return new CartPage(page);
    }

    public CheckoutOverviewPage clickOnTheContinueBtn(){
        page.locator(continueBtnXpath).click();
        return new CheckoutOverviewPage(page);
    }
    public boolean checkoutPageIsVisible(){
       return page.getByPlaceholder("First Name").isVisible();
    }

    public String clickOnTheContinueBtnToGetTheErrorMessage(){
        page.locator(continueBtnXpath).click();
        return page.locator(errorMessageXpath).textContent().trim();
    }
    public String headerPageNameIsVisibleAndEqualTo(){

        try{
            return  page.getByText("Checkout: Your Information").textContent();
        }catch(TimeoutError e){
            throw new RuntimeException("The header on the checkout Your information page is not visible");

        }




    }







}
