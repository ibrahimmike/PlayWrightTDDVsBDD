package pages;

import Products.InitialCart;
import Products.Product;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import driverFactory.BrowserManager;
import utils.ReadProperties;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage{

    private final String cartItemsXpath = "//div[@class='cart_item']";

    private final String subtotalBeforeTax = "//div[@class='summary_subtotal_label']";
    private final String taxValueXpath = "//div[@class='summary_tax_label']";

    private final String totalAfterTaxXpath = "//div[@class='summary_total_label']";

    private List<Double> priceAndQuantity = new ArrayList<>();

    private CartComponentsPage cartComponentsPage;




    public CheckoutOverviewPage(Page browserManager) {

        super(browserManager);
        browserManager.waitForLoadState(LoadState.DOMCONTENTLOADED);
        cartComponentsPage = new CartComponentsPage(browserManager);
    }

    public CheckoutPage clickOnCancelBtn(){
        browserManager.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CANCEL")).click();
        return  new CheckoutPage(browserManager);
    }
    public FinishPage clickOnTheFinishBtn(){
        browserManager.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("FINISH")).click();
        return new FinishPage(browserManager);
    }
//    public List<Double> extractThePriceAndTheQuantity(){
//        List<Double> priceAndQuantity = new ArrayList<>();
//
//        List<Locator> items = browserManager.getPage().locator(cartItemsXpath).all();
//        for (Locator item : items){
//          double itemPrice = Double.parseDouble(item.locator("//div[@class='inventory_item_price']").textContent().replace('$', ' ').trim());
//          int quantity = Integer.parseInt(item.locator("//div[@class='summary_quantity']").textContent().trim());
//          priceAndQuantity.add(itemPrice*quantity);
//        }
//      //  double calculatedSum = priceAndQuantity.stream().mapToDouble(e->e).sum();
//      //  double actualSubtotalPrice =  Double.parseDouble(browserManager.getPage().locator(subtotalBeforeTax).textContent().substring(13).trim());
//
//        return priceAndQuantity;
//    }

    private double calculatedSalesBeforeTax(){

      return   cartComponentsPage.getProductList().stream().mapToDouble(e->e.getProductPrice()).sum();
    }
    private double actualExtractedSalesBeforeTax(){
        return Double.parseDouble(browserManager.locator(subtotalBeforeTax).textContent().substring(13).trim());
    }
    private double taxAmountExtractedValue(){
        return Double.parseDouble(browserManager.locator(taxValueXpath).textContent().substring(6).trim());
    }
    private double calculatedTaxAmount(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);
        return Double.parseDouble(df.format( calculatedSalesBeforeTax()* Double.parseDouble(ReadProperties.getPropertyValue("taxRate"))));
    }
    private double calculatedAmountAfterTax(){
       // System.out.println("Calculated amount after tax : " + calculatedTaxAmount());
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);
        return Double.parseDouble(df.format( calculatedTaxAmount()+ calculatedSalesBeforeTax()));
    }
    private double actualExtractedTotalAmountAfterTax(){
        return Double.parseDouble(browserManager.locator(totalAfterTaxXpath).textContent().substring(8).trim());
    }

    public boolean checkIfTheAmountBeforeTaxIsEqualToTheActualAmountPresented(){

        return  actualExtractedSalesBeforeTax()==calculatedSalesBeforeTax();
    }
    public boolean checkIfTheTaxAmountIsCalculatedCorrectly(){
        System.out.println("check if the tax Amount is calculated correctly : " + calculatedTaxAmount() );
        System.out.println("Check if the tax amount is extracted correctly : " + taxAmountExtractedValue() );
        return calculatedTaxAmount() == taxAmountExtractedValue();
    }
    public boolean checkIfTheTotalAmountAfterTaxIsEqualToTheAmountPresented(){
        System.out.println( "Calculated amount after tax : " + calculatedAmountAfterTax());
        System.out.println("Actual extracted total amount after tax : " + actualExtractedTotalAmountAfterTax());
        return  calculatedAmountAfterTax()==actualExtractedTotalAmountAfterTax();
    }
    public  boolean checkIfTheCheckoutOverviewIsLoaded(){
        return  browserManager.getByText("Checkout: Overview").isVisible();
    }
    public boolean compareTheItemsOnTheOverviewPageAreTheSameAsTheOnesChosenByTheUser(){
        List<Product> checkoutOverviewCartComponent = cartComponentsPage.getProductList();
        List<Product> initialProducts = InitialCart.getItemsInCart();
        System.out.println();
        List<Integer> checks = new ArrayList<>();
        for (int i =0; i< checkoutOverviewCartComponent.size(); i++){
          checks.add(initialProducts.get(i).compareTo(checkoutOverviewCartComponent.get(i)));
        }
        return !checks.contains(0);

    }




   // DecimalFormat format =new DecimalFormat("#.##");
    //  double amount = Double.parseDouble(format.format(amountToBePaid));







}
