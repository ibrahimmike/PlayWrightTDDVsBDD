package pages;

import Products.CartThread;
import Products.InitialCart;
import Products.Product;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import driverFactory.BrowserManager;
import extentReports.ExtentLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class CartPage extends BasePage{

    private final String cartItemsListXpath = "//div[@class='cart_item']";

    private CartComponentsPage cartComponentsPage;


    public CartPage(Page browserManager) {

        super(browserManager);
        cartComponentsPage = new CartComponentsPage(browserManager);

    }

    private List<Locator> getTheAmountOfItemsInTheCart(){
        List<Locator> cartItems = new ArrayList<>();
        try{
            cartItems = page.locator(cartItemsListXpath).all();

        }catch(Exception e){
          //  System.out.println("The items were not added to the cart");
            ExtentLogger.fail("The amount of chosen products is not showing on the cart ");
        }

        return cartItems;

    }
   public boolean removeItemFromTheCartPage(){
        boolean check = false;
        List<Locator> itemsInCart = getTheAmountOfItemsInTheCart();
//       System.out.println("Items in the cart from the remove from cart method"+itemsInCart.size());
//       System.out.println("Items initial Items in the cart from the remove from cart method"+ InitialCart.getCartSize());
        if (!itemsInCart.isEmpty()){
            itemsInCart.get(1).getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Remove")).click();
            if (itemsInCart.size() -1 == getTheAmountOfItemsInTheCart().size()){
                check = true;
            }

        }else{
            check = false;
        }

        return check;
   }
    public HomePage clickOnTheContinueShoppingBtn(){


            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue Shopping")).click();


        return new HomePage(page);
    }
    public CheckoutPage clickOnTheCheckoutBtn(){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Checkout")).click();


        return new CheckoutPage(page);
    }

    public boolean compareTheItemsOnTheCartToTheItemsChosenByTheUser(){
      List<Product>  products = CartThread.getInitialCart().getItemsInCart();
              //InitialCart.getItemsInCart();

      List<Product> cartComponents = cartComponentsPage.getProductList();
      List<Integer> checks = new ArrayList<>();

      if (products.size()==cartComponents.size()){

          for (int i=0; i < products.size(); i++){
              if (products.get(i).compareTo(cartComponents.get(i))>0){
                  checks.add(1);
              }else {
                  checks.add(0);
              }

          }
      }
      return !checks.contains(0);
    }


    public boolean cartPageIsVisible(){
        return page.getByText("Your Cart").isVisible();
    }







}
