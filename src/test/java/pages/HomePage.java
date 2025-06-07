package pages;

import Products.CartThread;
import Products.InitialCart;
import Products.Product;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import driverFactory.BrowserManager;
import extentReports.ExtentLogger;

import javax.sound.sampled.FloatControl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BooleanSupplier;

public class HomePage extends BasePage{

    private final String productItemsXpath = "//div[@class='inventory_item']";

    protected InitialCart createdInitialCart;
    List<Locator> addedItems;

    private final String inventoryFilterXpath = "//div[@id='header_container']//select";
            //"//div[@id='inventory_filter_container']//select";





    public HomePage(Page browserManager) {
        super(browserManager);
        browserManager.waitForLoadState(LoadState.DOMCONTENTLOADED);
        getProductsWebElements();
    }

    public List<Locator> getProductsWebElements(){
        List<Locator>
        products = page.locator(productItemsXpath).all();


        return products;
    }
    public SingleProductPage userClicksOnOneOfTheItems(){
        getProductsWebElements().get(1).locator("//div[@class='inventory_item_name ']").click();
        return new SingleProductPage(page);
    }


    public HomePage userAddsToCartMultipleItems(){
        page.waitForSelector(inventoryFilterXpath);
        CartThread.setCart();
         createdInitialCart = CartThread.getInitialCart();

         InitialCart.emptyCart();


        addedItems = getProductsWebElements().subList(0,6);
        for (Locator item :addedItems){
          String productName = item.locator("//div[@class='inventory_item_name ']").textContent();
          String productDescription = item.locator("//div[@class='inventory_item_label']//div[@class='inventory_item_desc']").textContent();
          double productPrice = Double.parseDouble(item.locator("//div[@class='pricebar']//div[@class='inventory_item_price']").textContent().replace('$',' ').trim());
          if (item.locator("//button[contains(@class,'btn_small btn_inventory ')]").isDisabled()){
              ExtentLogger.fail(productName + " the add to cart element is disabled ");
          }
          item.locator("//button[contains(@class,'btn_small btn_inventory ')]").click();
          Product product = new Product(productName, productDescription, productPrice);

          createdInitialCart.addToCart(product);


        }

        return this;
    }

    public HomePage removeItemFromCartAfterAddingIt(){


        List<Locator> removeBtns = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove")).all();

        removeBtns.get(1).click();



        return this;

    }
    private List<InventoryItems> getInventoryItems(){
      List<Locator>  listOFInventoryItem = page.locator(productItemsXpath).all();
      List<InventoryItems> inventoryItems = new ArrayList<>();
      for (Locator item: listOFInventoryItem){
          String inventoryItemName = item.locator("//div[@class='inventory_item_name ']").textContent();
          double inventoryItemPrice = Double.parseDouble(item.locator("//div[@class='pricebar']//div[@class='inventory_item_price']").textContent().replace('$',' ').trim());
          String productDescription = item.locator("//div[@class='inventory_item_label']//div[@class='inventory_item_desc']").textContent();
          String itemImage = item.locator("//div[@class='inventory_item_img']//img").getAttribute("alt");
          InventoryItems inventory = new InventoryItems(inventoryItemName, productDescription, inventoryItemPrice, itemImage);
          inventoryItems.add(inventory);

        }
      return inventoryItems;

    }
    public boolean checkTheOrderingByPriceLowToHigh(){
        page.locator(inventoryFilterXpath).selectOption("lohi");

        List<Integer> check = new ArrayList<>();


        List<InventoryItems> inv = getInventoryItems();
        double lastItemPrice = inv.get(inv.size()-1).getInventoryItemPrice();

        for(int i=0; i< inv.size(); i++){
            if (lastItemPrice < inv.get(i).getInventoryItemPrice()){
                check.add(0);
            }else {
                check.add(1);
            }
        }
        return !check.contains(0);
    }
    public boolean checkTheOrderingByPriceHighToLow(){
        page.locator(inventoryFilterXpath).selectOption("hilo");
        List<Integer> check = new ArrayList<>();
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){

        }


        List<InventoryItems> inv = getInventoryItems();
        double lastItemPrice = inv.get(inv.size()-1).getInventoryItemPrice();

        for(int i=0; i< inv.size(); i++){
            if (lastItemPrice > inv.get(i).getInventoryItemPrice()){
                check.add(0);
            }else {
                check.add(1);
            }
        }
        return !check.contains(0);
    }
    public boolean checkOrderingByProductNameAtoZ(){
        page.locator(inventoryFilterXpath).selectOption("az");
        List<Integer> check = new ArrayList<>();
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){

        }



        List<InventoryItems> inv = getInventoryItems();
        String lastItemName = inv.get(inv.size()-1).getInventoryItemName();

        for(int i=0; i<inv.size(); i++){
            if (lastItemName.compareTo(inv.get(i).getInventoryItemName()) < 0 ){
                check.add(0);
            }else {
                check.add(1);
            }
        }
        return !check.contains(0);
    }

    public boolean checkOrderingByProductNameZtoA(){
        page.locator(inventoryFilterXpath).selectOption("za");
        List<Integer> check = new ArrayList<>();
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){

        }


        List<InventoryItems> inv = getInventoryItems();
        String lastItemName = inv.get(inv.size()-1).getInventoryItemName();

        for(int i=0; i<inv.size(); i++){
            if (lastItemName.compareTo(inv.get(i).getInventoryItemName())>0){
                check.add(0);
            }else {
                check.add(1);
            }
        }
        return !check.contains(0);
    }






    public boolean pageIsLoaded(){

        return page.locator(productItemsXpath).all().size()>1;
    }



    public class InventoryItems {

       // private final String listOfItemsXpath = "//div[@class='inventory_item']";

        private String inventoryItemName;
        private String inventoryItemDescription;
        private double inventoryItemPrice;

        private String inventoryItemImage;
      //  private List<Locator> listOFInventoryItem;

        public InventoryItems(String name, String description, double price, String image) {
            this.inventoryItemName = name;
            this.inventoryItemDescription = description;
            this.inventoryItemPrice = price;
            this.inventoryItemImage = image;


        }

        public String getInventoryItemName() {
            return inventoryItemName;
        }

        public String getInventoryItemDescription() {
            return inventoryItemDescription;
        }

        public double getInventoryItemPrice() {
            return inventoryItemPrice;
        }

        public String getInventoryItemImage() {
            return inventoryItemImage;
        }
    }













}
