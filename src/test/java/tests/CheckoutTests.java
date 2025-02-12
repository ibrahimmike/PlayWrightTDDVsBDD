package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class CheckoutTests  extends BaseTest{

    @Test
    public void userEntersHisDataAndClicksOnContinueToBeRedirectedTo(){
        LoginPage loginPage = new LoginPage(browserManager);
       boolean check =  loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().getHeader()
               .clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().enterUserName("ibrahim").
                enterLastName("Cute").enterZipCode("12345").clickOnTheContinueBtn().checkIfTheCheckoutOverviewIsLoaded();
       Assert.assertTrue(check);

    }
    @Test
    public void noValuesEnteredInTheCheckoutPage(){
        LoginPage loginPage = new LoginPage(browserManager);
       String errorMessage = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().getHeader().clickOnTheCartItemInTheHeader()
               .clickOnTheCheckoutBtn().clickOnTheContinueBtnToGetTheErrorMessage();
        System.out.println(errorMessage);
        Assert.assertEquals(errorMessage,"Error: First Name is required","The error message if the user didn't add the first name the message is wrong");
    }
    @Test
    public void theUserAddsOnlyFirstNameClicksContinueButton(){
        LoginPage loginPage = new LoginPage(browserManager);
        String errorMessage = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().
        getHeader().clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().enterUserName("ibrahim").clickOnTheContinueBtnToGetTheErrorMessage();
        System.out.println(errorMessage);
        Assert.assertEquals(errorMessage,"Error: Last Name is required", "If the last name is not added the error message is not as expected");
    }
    @Test
    public void theUserAddsTheFirstNameAndLastNameButNotThePostalCode(){
        LoginPage loginPage = new LoginPage(browserManager);
        String errorMessage = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().getHeader()
                .clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().enterUserName("ibrahim").enterLastName("Cute").clickOnTheContinueBtnToGetTheErrorMessage();
        System.out.println(errorMessage);
        Assert.assertEquals(errorMessage, "Error: Postal Code is required", "The postal code missing error is not as expected");
    }
    @Test
    public void headerNamePageIsVisible(){
        LoginPage loginPage = new LoginPage(browserManager);
        String headerIsCorrect = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().getHeader().clickOnTheCartItemInTheHeader()
                .clickOnTheCheckoutBtn().headerPageNameIsVisibleAndEqualTo();
        Assert.assertEquals(headerIsCorrect,"Checkout: Your Information" ,"Header is not visible or doesn't equal the expected value");
    }
    @Test
    public void userClicksOnCancelAndReturnsToCartPage(){
        LoginPage loginPage = new LoginPage(browserManager);
       boolean cartPageIsVisible = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems()
               .getHeader()
                .clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().clickOnCancelBtn().cartPageIsVisible();
       Assert.assertTrue(cartPageIsVisible);
    }
    @Test
    public void footerIsVisible(){
        LoginPage loginPage = new LoginPage(browserManager);
      boolean footerIsVisible =  loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems()
                .getHeader().clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().getFooter().footerISLoaded();
      Assert.assertTrue(footerIsVisible);
    }


}
