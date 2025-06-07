package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import driverFactory.BrowserManager;

public class SingleProductPage extends BasePage{
    private final String backToProductsBtn = "Back to products";

    private final String productTitleXpath = "//div[contains(@class,'inventory_details_name')]";

    private final String descriptionXpath = "//div[contains(@class,'inventory_details_desc ')]";

    private final String priceXpath = "//div[contains(@class,'inventory_details_price')]";
    private final String imageXpath = "//*[contains(@class,'inventory_details_img')]";



    public SingleProductPage(Page page) {
        super(page);
    }

    public HomePage clickOnBackToProducts(){
        page.getByText(backToProductsBtn).click();
        return new HomePage(page);

    }
    public String getProductTitle(){
        return page.locator(productTitleXpath).textContent();
    }
    public String getDescription(){
        return page.locator(descriptionXpath).textContent();
    }
    public boolean imageIsVisible(){
        return page.locator(imageXpath).isVisible();
    }
    public double getPrice(){
        return  Double.parseDouble(page.locator(priceXpath).textContent().replace('$',' ').trim());
    }
    public SingleProductPage addTheItemToTheCart(){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).click();
        return this;
    }
    public void removeItemFromCart(){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove"));
    }
    public CartPage clickOnTheCartItem(){
        HeaderPage headerPage = new HeaderPage(page);
        return  headerPage.clickOnTheCartItemInTheHeader();
    }
    public boolean burgerListIsVisible(){
        HeaderPage headerPage = new HeaderPage(page);
        return
        headerPage.burgerListIsVisible();
    }
    public boolean allProductComponentsAreVisible(){


        boolean productTitleIsVisible = false;
        boolean productDescription = false;
        boolean productPrice = false;
        boolean productImage = false;
        if (page.waitForSelector(productTitleXpath).isVisible()){
            productTitleIsVisible = true;
        }else{
            throw new RuntimeException("The single page product title is not visible");
        }
        if(page.waitForSelector(descriptionXpath).isVisible()){
            productDescription = true;
        }else{

            throw new RuntimeException("The single product page product description is not visible");
        }
        if (page.waitForSelector(imageXpath).isVisible()){
            productImage = true;

        }else {
            throw new RuntimeException("The single product page image is not visible");
        }
        if (page.waitForSelector(priceXpath).isVisible()){
            productPrice = true;
        }else{
            throw  new RuntimeException("The single product page price is not visible");
        }
        return productDescription && productImage && productPrice && productTitleIsVisible;



    }
    public SingleProductPage clickOnTheAddProduct(){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).click();
        return this;
    }
    public boolean productISAddedToTheCart(){
        HeaderPage headerPage = new HeaderPage(page);
        return headerPage.getTheNumberShowingOnTheCartItem()>0;
    }



}
