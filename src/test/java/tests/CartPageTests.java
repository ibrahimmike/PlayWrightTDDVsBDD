package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class CartPageTests extends BaseTest{

    @Test
    public void cartPageContainsTheChosenItemsByTheUser(){
        LoginPage loginPage = new LoginPage(page);
        boolean theItemsAddedByTheUserAreAddedToTheCart = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce")
                .clickOnLoginButton().userAddsToCartMultipleItems().getHeader().clickOnTheCartItemInTheHeader().compareTheItemsOnTheCartToTheItemsChosenByTheUser();
        Assert.assertTrue(theItemsAddedByTheUserAreAddedToTheCart);
    }
    @Test
    public void userCanRemoveItemsFromCartPage(){
        LoginPage loginPage = new LoginPage(page);
        boolean itemRemoved = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce")
                .clickOnLoginButton().userAddsToCartMultipleItems().getHeader().clickOnTheCartItemInTheHeader().removeItemFromTheCartPage();
        Assert.assertTrue(itemRemoved);
    }
    @Test
    public void userClicksOnTheContinueShoppingButtonAndReturnsToHomePage(){
        LoginPage loginPage = new LoginPage(page);
        boolean homePageIsLoaded = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems()
                .getHeader().clickOnTheCartItemInTheHeader().clickOnTheContinueShoppingBtn().pageIsLoaded();
        Assert.assertTrue(homePageIsLoaded);
    }
    @Test
    public void userClicksOnTheCheckOutButtonButtonAndCheckoutPageIsLoaded(){
        LoginPage loginPage = new LoginPage(page);
        boolean checkOutPageIsLoaded = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems()
                .getHeader().clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().checkoutPageIsVisible();
        Assert.assertTrue(checkOutPageIsLoaded);
    }
    @Test
    public void footerIsVisible(){
        LoginPage loginPage = new LoginPage(page);
        boolean footerIsVisible = loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton().userAddsToCartMultipleItems()
                .getHeader().clickOnTheCartItemInTheHeader().clickOnTheCheckoutBtn().getFooter().footerISLoaded();
        Assert.assertTrue(footerIsVisible);
    }



}
