package Products;

import utils.ReadProperties;

import java.util.ArrayList;
import java.util.List;

public  class InitialCart {

    private static List<Product> cartItems = new ArrayList<>();



    public InitialCart(){

    }

    public  void addToCart(Product product){
        cartItems.add(product);
    }

    public  void removeFromCart(Product product){
        cartItems.remove(product);
    }

    public  double amountToPayBeforeTaxes(){
        double amount = 0.0;
        for(Product product : cartItems){
            amount += product.getProductPrice();
        }
        return amount;
    }

    public  double taxOnTheItemsChosen(){
        double amountBeforeTax = amountToPayBeforeTaxes();
        double taxRate = Double.parseDouble(ReadProperties.getPropertyValue("taxRate").trim());
        System.out.println(taxRate);
        return Double.parseDouble(String.format("%.2d",(amountBeforeTax *taxRate)));
    }

    public   double getTotalPricePaid(){
        return  Double.parseDouble(String.format("%.2d", amountToPayBeforeTaxes() + taxOnTheItemsChosen()));
    }
    public static int getCartSize(){
        return cartItems.size();
    }

    public static   List<Product> getItemsInCart(){
        return cartItems;
    }

    public static   void emptyCart(){
        cartItems.clear();
    }
    public static void removeItemFromTheCart(){

    }
}
