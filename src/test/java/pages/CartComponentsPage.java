package pages;

import Products.Product;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import driverFactory.BrowserManager;

import java.util.ArrayList;
import java.util.List;

public class CartComponentsPage extends BasePage{

    private final String  itemsListXpath = "//div[@class='cart_item']";
    private final String  quantityRelativeXpath = "//div[@class='cart_quantity']"; // The xpath here is relative to each element Item
    private final String inventoryItemNameXpath = "//div[@class='inventory_item_name']"; // The xpath here is relative to each element Item

    private final String inventoryItemDescriptionXpath = "//div[@class='inventory_item_desc']"; // The xpath here is relative

    private final String priceXpath  = "//div[@class='inventory_item_price']";
    private final String removeBtnXpath = "//button[text()='Remove']";

    private List<Product> productList;
    private  List<Locator> items;
    public CartComponentsPage(Page page) {

        super(page);
        initialiseProductList();



    }


    private void initialiseProductList(){
        items = page.locator(itemsListXpath).all();
        productList = new ArrayList<>();
      for(Locator item : items){
         String itemName =   item.locator(inventoryItemNameXpath ).textContent();
         double  productPrice = Double.parseDouble(item.locator(priceXpath).textContent().replace('$', ' ')
                 .trim());

         String productDescription = item.locator(inventoryItemDescriptionXpath).textContent();
         Product product = new Product(itemName,productDescription,productPrice);
         productList.add(product);



      }

    }

    public List<Product> getProductList(){
        return productList;
    }
    public boolean removeItem(String productName){
        if(items.size()>1){

            for (Locator item: items){
              if(item.locator(inventoryItemNameXpath).textContent().equalsIgnoreCase(productName)){
                  item.locator(removeBtnXpath).dblclick();
                  try{
                      item.locator(inventoryItemDescriptionXpath).isVisible();
                  }catch(TimeoutError error){
                      return true;
                  }

              }else {
                  throw new RuntimeException("The product requested to remove doesn't exist already");
              }
            }

        }
        return false;

    }

}
