package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import driverFactory.BrowserManager;

public class CheckoutPage extends BasePage{

    private final String errorMessageXpath = "//h3[@data-test='error']";
    private final String continueBtnXpath = "//input[@value='CONTINUE']";
    private final String headerName = "//div[@id='header_container']//div[@data-test='secondary-header']";
    public CheckoutPage(Page browserManager) {
        super(browserManager);
    }

//    public void waitForThePage(){
//        try {
//            Thread.sleep(10000);
//        }catch(Exception e){
//            e.getMessage();
//        }
//    }
//    public HeaderPage getHeader(){
//        return new HeaderPage(browserManager);
//    }
    public CheckoutPage enterUserName(String userName){

        browserManager.getByPlaceholder("First Name").fill(userName);
        return this;
    }
    public CheckoutPage enterLastName(String lastName){
        browserManager.getByPlaceholder("Last Name").fill(lastName);
        return this;
    }
    public CheckoutPage enterZipCode(String zipCode){
        browserManager.getByPlaceholder("Zip/Postal Code").fill(zipCode);
        return this;
    }
    public CartPage clickOnCancelBtn(){
        browserManager.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CANCEL")).click();
        return new CartPage(browserManager);
    }

    public CheckoutOverviewPage clickOnTheContinueBtn(){
        browserManager.locator(continueBtnXpath).click();
        return new CheckoutOverviewPage(browserManager);
    }
    public boolean checkoutPageIsVisible(){
       return browserManager.getByPlaceholder("First Name").isVisible();
    }

    public String clickOnTheContinueBtnToGetTheErrorMessage(){
        browserManager.locator(continueBtnXpath).click();
        return browserManager.locator(errorMessageXpath).textContent().trim();
    }
    public String headerPageNameIsVisibleAndEqualTo(){

        try{
            return  browserManager.getByText("Checkout: Your Information").textContent();
        }catch(TimeoutError e){
            throw new RuntimeException("The header on the checkout Your information page is not visible");

        }




    }







}
