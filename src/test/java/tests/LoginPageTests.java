package tests;

import driverFactory.BrowserManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import utils.ReadProperties;

public class LoginPageTests extends BaseTest {


    @Test
    public void badUserNameCorrectPassword(){
        LoginPage loginPage = new LoginPage(browserManager);


      String errorMessage =  loginPage.enterUserName("ibrahim").enterUserPassword("secret_sauce").clickOnLoginBtnErrorLogin();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }


    @Test
    public void userCorrectLogin(){
        LoginPage loginPage = new LoginPage(browserManager);


      boolean pageIsLoaded =  loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().pageIsLoaded();
      Assert.assertTrue(pageIsLoaded);

    }
    @Test
    public void lockedOutUser(){
        LoginPage loginPage = new LoginPage(browserManager);

        String response = loginPage.enterUserName("locked_out_user").enterUserPassword("secret_sauce").clickOnLoginBtnErrorLogin();
        Assert.assertEquals(response, "Epic sadface: Sorry, this user has been locked out.", "The locked out user is not showing");

    }
    @Test
    public void problemUser(){
        LoginPage loginPage = new LoginPage(browserManager);

        loginPage.enterUserName("problem_user").enterUserPassword("secret_sauce").clickOnLoginButton();


    }
    @Test
    public void performanceGlitchUser(){
        LoginPage loginPage = new LoginPage(browserManager);

        loginPage.enterUserName("performance_glitch_user").enterUserName("secret_sauce").clickOnLoginButton();
    }
    @Test
    public void logoutStandardUser(){
        LoginPage loginPage = new LoginPage(browserManager);
      boolean loginPageVisible =   loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().getHeader().clickOnTheBurgerListBtn().clickOnLogoutButton()
                .loginPageIsLoaded();
      Assert.assertTrue(loginPageVisible, "When user clicks on the logout button the user is not redirected to the login page.");
    }






}
