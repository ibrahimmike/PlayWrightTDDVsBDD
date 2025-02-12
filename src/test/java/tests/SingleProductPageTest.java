package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class SingleProductPageTest extends BaseTest {

    @Test
    public void singleProductPageContainsProductData(){
        LoginPage loginPage = new LoginPage(browserManager);
        boolean check =loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton()
                .userClicksOnOneOfTheItems().allProductComponentsAreVisible();
        Assert.assertTrue( check);
    }
    @Test
    public void userCanAddProductToTheCartFromTheSingleProductPage(){
        LoginPage loginPage = new LoginPage(browserManager);
        Assert.assertTrue(loginPage.enterUserName("standard_user").enterUserPassword("secret_sauce").clickOnLoginButton()
                .userClicksOnOneOfTheItems().addTheItemToTheCart().productISAddedToTheCart());
    }

}
