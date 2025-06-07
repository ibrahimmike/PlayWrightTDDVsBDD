package pages;

import Products.CartThread;
import Products.InitialCart;
import Products.Product;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import driverFactory.BrowserManager;
import extentReports.ExtentLogger;
import extentReports.ExtentReport;
import extentReports.ExtentReportManager;
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




    public CheckoutOverviewPage(Page page) {

        super(page);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        if (!page.locator(taxValueXpath).isVisible()){
            ExtentLogger.fail("The checkout page did not load");
        }
        cartComponentsPage = new CartComponentsPage(page);
    }

    public CheckoutPage clickOnCancelBtn(){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel")).click();
        return  new CheckoutPage(page);
    }
    public FinishPage clickOnTheFinishBtn(){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Finish")).click();
        return new FinishPage(page);
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
        double amount = 0.0;
        try{
            amount = Double.parseDouble(page.locator(subtotalBeforeTax).textContent().substring(13).trim());
        }catch(Exception error){
            ExtentLogger.fail("The amount of sales before tax is not visible ");
        }
        return amount;
    }
    private double taxAmountExtractedValue(){
        return Double.parseDouble(page.locator(taxValueXpath).textContent().substring(6).trim());
    }
    private double calculatedTaxAmount(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);
        return Double.parseDouble(df.format( calculatedSalesBeforeTax()* Double.parseDouble(ReadProperties.getPropertyValue("taxRate"))));
    }
    private double calculatedAmountAfterTax(){

        DecimalFormat df = new DecimalFormat("#.##");
       // df.setRoundingMode(RoundingMode.UP);
        return Double.parseDouble(df.format( calculatedTaxAmount()+ calculatedSalesBeforeTax()));
    }
    private double actualExtractedTotalAmountAfterTax(){
        return Double.parseDouble(page.locator(totalAfterTaxXpath).textContent().substring(8).trim());
    }

    public boolean checkIfTheAmountBeforeTaxIsEqualToTheActualAmountPresented(){

        return  actualExtractedSalesBeforeTax()==calculatedSalesBeforeTax();
    }
    public boolean checkIfTheTaxAmountIsCalculatedCorrectly(){

        return calculatedTaxAmount() == taxAmountExtractedValue() && taxAmountExtractedValue()!=0.00;
    }
    public boolean checkIfTheTotalAmountAfterTaxIsEqualToTheAmountPresented(){

        return  calculatedAmountAfterTax()==actualExtractedTotalAmountAfterTax();
    }
    public  boolean checkIfTheCheckoutOverviewIsLoaded(){
        return  page.getByText("Checkout: Overview").isVisible();
    }
    public boolean compareTheItemsOnTheOverviewPageAreTheSameAsTheOnesChosenByTheUser(){
        List<Product> checkoutOverviewCartComponent = cartComponentsPage.getProductList();
        List<Product> initialProducts = CartThread.getInitialCart().getItemsInCart();

        List<Integer> checks = new ArrayList<>();
        for (int i =0; i< checkoutOverviewCartComponent.size(); i++){
          checks.add(initialProducts.get(i).compareTo(checkoutOverviewCartComponent.get(i)));
        }
        return !checks.contains(0);

    }











}
