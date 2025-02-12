package tests;

import com.microsoft.playwright.Page;
import driverFactory.PageThreadLocal;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

public class SanityTest {
    private Page browserManager;
    private LoginPage loginPage;

   private HomePage homePage;
   private CartPage cartPage;
   private CheckoutPage checkoutPage;
   private CheckoutOverviewPage checkoutOverviewPage;



    @BeforeTest
    public void initializeTests(){
        browserManager = PageThreadLocal.initPage();
        loginPage = new LoginPage(browserManager);

    }
    @Test(priority = 1)
    public void userLogin(){

        homePage = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton();
       // homePage.userAddsToCartMultipleItems();

        Assert.assertTrue(homePage.pageIsLoaded());
    }
    @Test(priority = 2)
    public void userAddsProductsToTheHomePage(){
        homePage.userAddsToCartMultipleItems();

       Assert.assertTrue(homePage.pageIsLoaded());
    }
    @Test(priority = 3)
    public void userIsRedirectedToTheCartPageWhenClicksOnTheCartItem(){
       cartPage = homePage.getHeader().clickOnTheCartItemInTheHeader();
       Assert.assertTrue(cartPage.cartPageIsVisible());
    }
    @Test(priority = 4)
    public void productsChosenByTheUserAreTheOnesAddedToTheCart(){
      Assert.assertTrue(cartPage.compareTheItemsOnTheCartToTheItemsChosenByTheUser());
    }
    @Test(priority = 5)
    public void userClicksOnCheckoutAndTheCheckoutPageIsVisible(){
        checkoutPage = cartPage.clickOnTheCheckoutBtn();
      Assert.assertTrue(checkoutPage.checkoutPageIsVisible());
    }
    @Test(priority = 6)
    public void userEntersHisDataOnTheCheckoutPageAndTheCheckOutPageIsVisible(){
        checkoutOverviewPage = checkoutPage.enterUserName("Baba").enterLastName("Yaga").enterZipCode("1234").clickOnTheContinueBtn();
        Assert.assertTrue(checkoutOverviewPage.checkIfTheCheckoutOverviewIsLoaded());
    }
    @Test(priority = 7)
    public void  theOrderDisplayedIsTheSameAsTheUserChose(){
      Assert.assertTrue(checkoutOverviewPage.compareTheItemsOnTheOverviewPageAreTheSameAsTheOnesChosenByTheUser(),"The order in the checkout overview page is not the same order " +
              "chosen by the user on the home page");
    }
    @Test(priority = 8)
    public void theAmountBeforeTaxIsCalculatedCorrectly(){
        Assert.assertTrue(checkoutOverviewPage.checkIfTheAmountBeforeTaxIsEqualToTheActualAmountPresented(), "The amount on the checkout overview page is not calculated correctly");
    }
    @Test(priority = 9)
    public void theAmountOfTaxIsCalculatedCorrectly(){
        Assert.assertTrue(checkoutOverviewPage.checkIfTheTaxAmountIsCalculatedCorrectly(), "The amount of tax is not calculated correctly");
    }
    @Test(priority = 10)
    public void theAmountAfterTaxIsCalculatedCorrectly(){
        Assert.assertTrue(checkoutOverviewPage.checkIfTheTotalAmountAfterTaxIsEqualToTheAmountPresented(), "The amount after tax is not calculated correctly");
    }
    @Test(priority = 11)
    public void userClicksOnTheFinishButtonOnTheCheckoutPageAndTheDeliveryPageIsVisible(){
        Assert.assertTrue(checkoutOverviewPage.clickOnTheFinishBtn().finishPageIsLoaded());
    }




    @AfterTest
    public void takeDownTests(){
        PageThreadLocal.removePage();
    }
}
