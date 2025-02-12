package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HomePageTests extends BaseTest{




     @Test
     public void standardUserLoginInventoryItemsAreVisible(){
         LoginPage loginPage = new LoginPage(browserManager);
         boolean itemsAreVisible = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().pageIsLoaded();
         Assert.assertTrue(itemsAreVisible);
     }
     @Test
     public void onHomePageHeaderIsVisible(){
         LoginPage loginPage = new LoginPage(browserManager);

        boolean headerIsVisible = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().getHeader().headerisVisble();
         Assert.assertTrue(headerIsVisible);
     }
     @Test
     public void onHomePageFooterIsVisible(){
         LoginPage loginPage = new LoginPage(browserManager);
         boolean footerIsVisible = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().getFooter().theFooterSocialMediaLinksAreVisible();
         Assert.assertTrue(footerIsVisible, "The footer social links are not visible");
     }
     @Test
     public void addInventoryItemsToTheCartTheAmountOnTheCartIsUpdated(){
         LoginPage loginPage = new LoginPage(browserManager);
         int amount = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce")
                 .clickOnLoginButton().userAddsToCartMultipleItems().getHeader().getTheNumberShowingOnTheCartItem();
         Assert.assertEquals(amount, 3);
     }
     @Test
    public void removingItemFromTheCartTheIAmountOnTheCartIsReduced(){
         LoginPage loginPage = new LoginPage(browserManager);
         int amount = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce")
                 .clickOnLoginButton().userAddsToCartMultipleItems().
                 removeItemFromCartAfterAddingIt("Sauce Labs Bolt T-Shirt").getHeader().getTheNumberShowingOnTheCartItem();
         Assert.assertEquals(amount, 2);
     }
     @Test
     public void orderingFilterPriceHighToLow(){
         LoginPage loginPage = new LoginPage(browserManager);
          boolean check = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().checkTheOrderingByPriceHighToLow();
          Assert.assertTrue(check);
     }
     @Test
    public void orderingFilterPriceLowToHign(){
         LoginPage loginPage = new LoginPage(browserManager);
         boolean check = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().checkTheOrderingByPriceLowToHigh();
         Assert.assertTrue(check);
     }
     @Test
    public void orderingFilterByNameZtoA(){
         LoginPage loginPage = new LoginPage(browserManager);
         boolean check = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().checkOrderingByProductNameZtoA();
         Assert.assertTrue(check);
     }
     @Test
    public void orderingFilterByNameAtoZ(){
         LoginPage loginPage = new LoginPage(browserManager);
         boolean check = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().checkOrderingByProductNameAtoZ();
         Assert.assertTrue(check);
     }




}
