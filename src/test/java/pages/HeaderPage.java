package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import driverFactory.BrowserManager;

import java.util.concurrent.TimeoutException;

public class HeaderPage extends BasePage{

    private final String burgerListXpath = "//div[@class='bm-burger-button']//button";
    private final String linkForTheCartXpath = "//a[@class='shopping_cart_link']";
            //"//a[@href='./cart.html']";
    private final String theAmountOfItemOnTheCartLogo = "//span[@class='shopping_cart_badge']";
                    //"//span[@class='fa-layers-counter shopping_cart_badge']";


    private final String logoXpath = "//div[@class='app_logo']";
    public HeaderPage(Page browserManager) {
        super(browserManager);
    }

    public CartPage clickOnTheCartItemInTheHeader(){
     //  ElementHandle linkForCart = browserManager.waitForSelector(linkForTheCartXpath);
     //  linkForCart.asElement().click();
        page.waitForSelector(linkForTheCartXpath).click();
        return new CartPage(page);
    }


    public  BurgerListPage clickOnTheBurgerListBtn(){
        page.locator(burgerListXpath).click();
        return new BurgerListPage(page);
    }
    public boolean burgerListIsVisible(){
        return  page.locator(burgerListXpath).isVisible();
    }
    public boolean headerisVisble(){
        return page.locator(logoXpath).isVisible();
    }
    public int getTheNumberShowingOnTheCartItem(){
        try {

          int amount = Integer.parseInt(page.locator(theAmountOfItemOnTheCartLogo).textContent());
            System.out.println("Amount on the cart" + amount);
          return amount;
        }catch(TimeoutError e){
            System.out.println("Time out fired");
            return 0;
        }
    }

    public class  BurgerListPage extends BasePage{

        private final String allItemsHeader = "//nav[@class='bm-item-list']/a[@id='inventory_sidebar_link']";
        private final String aboutButtonXpath = "//nav[@class='bm-item-list']/a[@id='about_sidebar_link']";
        private final String logoutBtn = "//nav[@class='bm-item-list']/a[@id='logout_sidebar_link']";
        private final String resetBtnXpath = "//nav[@class='bm-item-list']/a[@id='reset_sidebar_link']";
        private final String closeButton = "//div[@class='bm-menu-wrap']//button";

        public BurgerListPage(Page browserManager) {

            super(browserManager);
            browserManager.waitForSelector(allItemsHeader);
        }

        //"//button[@id='react-burger-cross-btn']";
        public HomePage clickOnAllItems(){
            page.locator(allItemsHeader).click();
            return new HomePage(page);
        }
        public String clickOnAboutButton(){
            page.locator(aboutButtonXpath).click();
            return page.url();
        }

        public LoginPage clickOnLogoutButton(){
            page.locator(logoutBtn).click();

            return new LoginPage(page);
        }

        private void clickOnTheCloseBtn(){
            page.locator(closeButton).dblclick();
        }

        public boolean clickOnResetBtn(){
            boolean theAmountOnTheCartIsRemoved = false;
            page.locator(resetBtnXpath).click();
           // browserManager.locator(closeButton).click();

            try{
                clickOnTheCloseBtn();
              //  int amount = Integer.parseInt(browserManager.locator("//div[@class='shopping_cart_container']").textContent());
                return !page.locator("//div[@class='shopping_cart_container']//span").isVisible();
             //   return browserManager.waitForSelector(theAmountOfItemOnTheCartLogo).isVisible();
              // theAmountOnTheCartIsRemoved =  amount > 0;
            }catch(TimeoutError error){
                theAmountOnTheCartIsRemoved = true;

            }

            return theAmountOnTheCartIsRemoved;
        }




    }

}
