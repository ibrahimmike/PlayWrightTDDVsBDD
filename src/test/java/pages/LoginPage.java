package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import driverFactory.BrowserManager;

public class LoginPage extends BasePage{
    private final String userNameId = "//input[@id='user-name']";
    private final String passwordId = "//input[@id='password']";
    private final String loginBtnId = "//input[@id='login-button']";



    public LoginPage(Page browserManager) {

        super(browserManager);
       // browserManager.waitForLoadState(LoadState.DOMCONTENTLOADED);
        browserManager.waitForSelector(loginBtnId);
    }
    public LoginPage enterUserName(String name){
        page.locator(userNameId).clear();

        page.locator(userNameId).fill(name);
        return this;
    }
    public LoginPage enterUserPassword(String password){
        page.locator(passwordId).clear();
        page.locator(passwordId).fill(password);
        return this;
    }
    public HomePage clickOnLoginButton() {
        page.locator(loginBtnId).click();
        return new HomePage(page);

    }
    public String clickOnLoginBtnErrorLogin(){

        page.locator(loginBtnId).click();
        return page.locator("//h3[@data-test='error']").textContent();
    }
//    public void navigateToLoginPage(){
//        browserManager.navigate("https://www.saucedemo.com/v1");
//    }

    public boolean loginPageIsLoaded(){
         page.locator(loginBtnId).click();
         return true;
    }



}
