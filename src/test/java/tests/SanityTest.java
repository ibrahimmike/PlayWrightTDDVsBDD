package tests;

import com.microsoft.playwright.Page;
import driverFactory.PageThreadLocal;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

public class SanityTest {
    private Page page;
    private LoginPage loginPage;

   private HomePage homePage;
   private CartPage cartPage;
   private CheckoutPage checkoutPage;
   private CheckoutOverviewPage checkoutOverviewPage;




    @Parameters({"userName", "password"})
    @BeforeTest()
    public void initializeTests(String userName, String password){
        page = PageThreadLocal.initPage();
        loginPage = new LoginPage(page);
        homePage = loginPage.enterUserName(userName).enterUserPassword(password).clickOnLoginButton();

    }
    @Test(priority = 1)
    public void userLogin(){


       // homePage.userAddsToCartMultipleItems();

        Assert.assertTrue(homePage.pageIsLoaded());
    }
    @Test(priority = 2)
    public void userAddsProductsToTheHomePage(){
        homePage.userAddsToCartMultipleItems();

       Assert.assertTrue(homePage.getHeader().getTheNumberShowingOnTheCartItem()==6, " The amount on the cart is not the same amount of products chosen by the user ");
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
        Assert.assertTrue(checkoutOverviewPage.checkIfTheCheckoutOverviewIsLoaded(),"The checkout overview did not load");
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
