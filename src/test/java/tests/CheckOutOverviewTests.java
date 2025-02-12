package tests;

import com.google.errorprone.annotations.FormatString;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class CheckOutOverviewTests extends BaseTest {


    @Test
    public void checkOverviewTitleIsVisible(){
        LoginPage loginPage = new LoginPage(browserManager);
      boolean titleIsShowing =  loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().
              getHeader().clickOnTheCartItemInTheHeader()
                .clickOnTheCheckoutBtn().enterUserName("ibrahim").enterLastName("Baba").enterZipCode("1234").clickOnTheContinueBtn().checkIfTheCheckoutOverviewIsLoaded();
      Assert.assertTrue(titleIsShowing);
    }

    @Test
    public void checkTheProductsOnTheOverviewPageIsTheSameAsTheChosenByTheUser(){
        LoginPage loginPage = new LoginPage(browserManager);
        boolean theSameProducts =  loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().
                getHeader().clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().enterUserName("ibrahim").enterLastName("Baba").enterZipCode("1234")
                .clickOnTheContinueBtn().compareTheItemsOnTheOverviewPageAreTheSameAsTheOnesChosenByTheUser();
        Assert.assertTrue(theSameProducts);
    }
    @Test
    public void amountBeforeTaxIsCorrect(){
        LoginPage loginPage = new LoginPage(browserManager);
        boolean amountBeforeTaxIsCorrect =  loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().
                getHeader().clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().enterUserName("ibrahim").enterLastName("Baba").enterZipCode("1234")
                .clickOnTheContinueBtn().checkIfTheAmountBeforeTaxIsEqualToTheActualAmountPresented();
        Assert.assertTrue(amountBeforeTaxIsCorrect);
    }
    @Test
    public void amountAfterTaxIsTheSameAsExpected(){
        LoginPage loginPage = new LoginPage(browserManager);
        boolean amountAfterTaxIsCorrect = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().
                getHeader().clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().enterUserName("ibrahim").enterLastName("Baba").enterZipCode("1234")
                .clickOnTheContinueBtn().checkIfTheTotalAmountAfterTaxIsEqualToTheAmountPresented();
        Assert.assertTrue(amountAfterTaxIsCorrect);
    }
    @Test
    public void compareAmountBeforeTax(){
        LoginPage loginPage = new LoginPage(browserManager);
        boolean checkAmountBeforeTax = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems().
                getHeader().clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().enterUserName("ibrahim").enterLastName("Baba").enterZipCode("1234")
                .clickOnTheContinueBtn().checkIfTheTaxAmountIsCalculatedCorrectly();
        Assert.assertTrue(checkAmountBeforeTax);
    }






}
