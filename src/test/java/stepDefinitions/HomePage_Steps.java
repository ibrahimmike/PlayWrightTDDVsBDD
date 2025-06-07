package stepDefinitions;

import com.microsoft.playwright.Page;
import driverFactory.PageThreadLocal;
import io.cucumber.java.en.When;
import pages.HomePage;

public class HomePage_Steps {

    private Page page = PageThreadLocal.getPage();
    private HomePage homePage = new HomePage(page);

    @When("User adds items to cart")
    public void user_adds_items_to_cart(){
        homePage.userAddsToCartMultipleItems();
    }
}
