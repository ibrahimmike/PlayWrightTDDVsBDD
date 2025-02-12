package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import driverFactory.BrowserManager;

public class SingleProductPage extends BasePage{
    private final String backToProductsBtn = "Back to products";

    private final String productTitleXpath = "//div[@class='inventory_details_name']";

    private final String descriptionXpath = "//div[@class='inventory_details_desc']";

    private final String priceXpath = "//div[@class='inventory_details_price']";
    private final String imageXpath = "//*[contains(@class,'inventory_details_img')]";



    public SingleProductPage(Page browserManager) {
        super(browserManager);
    }

    public HomePage clickOnBackToProducts(){
        browserManager.getByText(backToProductsBtn).click();
        return new HomePage(browserManager);

    }
    public String getProductTitle(){
        return browserManager.locator(productTitleXpath).textContent();
    }
    public String getDescription(){
        return browserManager.locator(descriptionXpath).textContent();
    }
    public boolean imageIsVisible(){
        return browserManager.locator(imageXpath).isVisible();
    }
    public double getPrice(){
        return  Double.parseDouble(browserManager.locator(priceXpath).textContent().replace('$',' ').trim());
    }
    public SingleProductPage addTheItemToTheCart(){
        browserManager.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).click();
        return this;
    }
    public void removeItemFromCart(){
        browserManager.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove"));
    }
    public CartPage clickOnTheCartItem(){
        HeaderPage headerPage = new HeaderPage(browserManager);
        return  headerPage.clickOnTheCartItemInTheHeader();
    }
    public boolean burgerListIsVisible(){
        HeaderPage headerPage = new HeaderPage(browserManager);
        return
        headerPage.burgerListIsVisible();
    }
    public boolean allProductComponentsAreVisible(){


        boolean productTitleIsVisible = false;
        boolean productDescription = false;
        boolean productPrice = false;
        boolean productImage = false;
        if (browserManager.waitForSelector(productTitleXpath).isVisible()){
            productTitleIsVisible = true;
        }else{
            throw new RuntimeException("The single page product title is not visible");
        }
        if(browserManager.waitForSelector(descriptionXpath).isVisible()){
            productDescription = true;
        }else{

            throw new RuntimeException("The single product page product description is not visible");
        }
        if (browserManager.waitForSelector(imageXpath).isVisible()){
            productImage = true;

        }else {
            throw new RuntimeException("The single product page image is not visible");
        }
        if (browserManager.waitForSelector(priceXpath).isVisible()){
            productPrice = true;
        }else{
            throw  new RuntimeException("The single product page price is not visible");
        }
        return productDescription && productImage && productPrice && productTitleIsVisible;



    }
    public SingleProductPage clickOnTheAddProduct(){
        browserManager.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).click();
        return this;
    }
    public boolean productISAddedToTheCart(){
        HeaderPage headerPage = new HeaderPage(browserManager);
        return headerPage.getTheNumberShowingOnTheCartItem()>0;
    }



}
