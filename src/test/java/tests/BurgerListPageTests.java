package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;

public class BurgerListPageTests extends BaseTest{


    @Test
    public void allItemsClickIsReturningTheHomePage(){
        LoginPage loginPage = new LoginPage(page);
        boolean check  = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().
                getHeader().clickOnTheCartItemInTheHeader()
                .clickOnTheCheckoutBtn().enterUserName("ibrahim").enterLastName("Baba").enterZipCode("1234")
                .clickOnTheContinueBtn().getHeader().clickOnTheBurgerListBtn().clickOnAllItems().pageIsLoaded();
        Assert.assertTrue(check);
    }
    @Test
    public void aboutButtonIsRedirectingToTheCorrectPage(){
        LoginPage loginPage = new LoginPage(page);
        String theUrl = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().getHeader().clickOnTheCartItemInTheHeader()
                .clickOnTheCheckoutBtn().enterUserName("ibrahim").enterLastName("Baba").enterZipCode("1234")
                .getHeader().clickOnTheBurgerListBtn().clickOnAboutButton();

        Assert.assertEquals(theUrl, "https://saucelabs.com/", "The About button didn't redirect to the correct url");
    }
    @Test
    public void resetAppStateCartIsDeletedAndHomePageIsUpdated(){
        LoginPage loginPage = new LoginPage(page);
        boolean reseted = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().
                getHeader().clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().enterUserName("ibrahim").enterLastName("Baba").enterZipCode("1234")
                .getHeader().clickOnTheBurgerListBtn().clickOnResetBtn();
        Assert.assertTrue(reseted);
    }
}
