package stepDefinitions;

import com.microsoft.playwright.Page;
import driverFactory.PageThreadLocal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

public class LoginPage_Steps {
    protected Page page;
    private LoginPage loginPage;
    private HomePage homePage;

    public LoginPage_Steps(){}

    @Given("User is on login page")
    public void user_is_on_loginPage(){
        page = PageThreadLocal.getPage();
        loginPage = new LoginPage(page);
    }
    @And("User enters user name {word}")
    public  void user_enters_user_name(String userName){
        loginPage.enterUserName(userName);
    }
    @And("user enters password {word}")
    public void user_enters_password(String password){
        loginPage.enterUserPassword(password);
    }
    @And("user clicks on login button")
    public void user_clicks_on_login_button(){
       homePage = loginPage.clickOnLoginButton();
    }
    @Then("Home page is loaded")
    public void home_page_is_loaded(){
        Assert.assertTrue(homePage.pageIsLoaded());
    }
    @Then("The error message is showing {string}")
    public void login_error_is_showing(String errorMessage){
        SoftAssert sf = new SoftAssert();
        sf.assertFalse(homePage.pageIsLoaded(),"Home page is loaded while it shouldn't");
      //  Assert.assertFalse(homePage.pageIsLoaded());
        sf.assertEquals(loginPage.clickOnLoginBtnErrorLogin(), errorMessage);
        sf.assertAll();
    }


}
